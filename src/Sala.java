public class Sala {
    private String nome;
    private String descricao;
    private ObjetoInterativo[] objetosNaSala;
    private int objetosPresentes;
    private boolean saidaLiberada;

    public Sala(String nome) {
        this.nome = nome;
        this.descricao = "Você está em uma sala fria com paredes de pedra.\nNo centro, há uma MESA de madeira com um bilhete em cima dela.\nAo norte, uma grande PORTA de ferro.\nNo chao, um TAPETE empoeirado que cobre parte do piso da Camara.";
        this.objetosNaSala = new ObjetoInterativo[5];
        this.objetosPresentes = 0;
        this.saidaLiberada = false;

        adicionarObjeto(new ObjetoInterativo("porta", "\nÉ uma porta de ferro maciço. Parece bem trancada.", false));
        adicionarObjeto(new ObjetoInterativo("mesa", "\nUma mesa de madeira. Sobre ela, há um BILHETE.", false));
        adicionarObjeto(new ObjetoInterativo("bilhete", "\nNo bilhete amarelado está escrito: 'A liberdade está sob os pés dos que descansam'.", true));
        adicionarObjeto(new ObjetoInterativo("tapete", "\nUm tapete velho. Parece que há algo embaixo.", false));
    //    adicionarObjeto(new ObjetoInterativo("chave", "\Numa pequena chave de ferro. Estava debaixo do tapete.", true));
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }


    private void adicionarObjeto(ObjetoInterativo objeto) {
        if (this.objetosPresentes < this.objetosNaSala.length) {
            this.objetosNaSala[this.objetosPresentes] = objeto;
            this.objetosPresentes++;
        }
    }

    public void mostrarSala() {
        System.out.println("\n=========================================================================================");
        System.out.println("Sala: " + this.nome + "\n");
        System.out.println(this.descricao + "\n");
        System.out.println("Objetos na sala:");
        for (int i = 0; i < this.objetosPresentes; i++) {
            System.out.println(" - " + this.objetosNaSala[i].getNome());
        }
    }

    public ObjetoInterativo buscarObjeto(String nomeDoObjeto) {
        for (int i = 0; i < this.objetosPresentes; i++) {
            if (this.objetosNaSala[i].getNome().equalsIgnoreCase(nomeDoObjeto)) {
                return this.objetosNaSala[i];
            }
        } return null;
    }

    public boolean resolverEnigma(String nomeDoItem, String nomeDoAlvo) {
        if (nomeDoItem.equalsIgnoreCase("Chave") && nomeDoAlvo.equalsIgnoreCase("Porta")) {
            this.saidaLiberada = true;
            return true;
        } return false;
    }

    public boolean isSaidaLiberada() {
        return this.saidaLiberada;
    }

    public void removerObjeto(ObjetoInterativo objetoParaRemover) {
        int indiceEncontrado = -1;
            for (int i = 0; i < this.objetosPresentes; i++) {
                if (this.objetosNaSala[i].getNome().equals(objetoParaRemover.getNome())) {
                    indiceEncontrado = i;
                    break;
                }
            }

            if (indiceEncontrado != -1) {
                for (int i = indiceEncontrado; i < this.objetosPresentes - 1; i++) {
                    this.objetosNaSala[i] = this.objetosNaSala[i + 1];
                }
                this.objetosPresentes--;
            }
    }

    public void revelarChave() {
        if (buscarObjeto("chave") == null) {
            System.out.println("---\nVocê levanta o tapete e encontra uma chave!");
            adicionarObjeto(new ObjetoInterativo("chave", "\nUma pequena chave de ferro, recém descoberta.", true));
        } else {
        System.out.println("---\nVocê já pegou a chave que estava aqui.");
        }
    }
}