import java.util.ArrayList;
import java.util.List;

public class Sala {
    private String nome;
    private String descricao;
    private List<ObjetoInterativo> objetosNaSala;
    private boolean saidaLiberada;

    public Sala(String nome) {
        this.nome = nome;
        this.descricao = "Você está em uma sala fria com paredes de pedra.\nNo centro, há uma MESA de madeira com um bilhete em cima dela.\nAo norte, uma grande PORTA de ferro.\nNo chao, um TAPETE empoeirado que cobre parte do piso da Camara.";
        
        this.objetosNaSala = new ArrayList<>();
        this.saidaLiberada = false;

        adicionarObjeto(new ObjetoFixo("porta", "\nÉ uma porta de ferro maciço. Parece bem trancada."));
        adicionarObjeto(new ObjetoFixo("mesa", "\nUma mesa de madeira. Sobre ela, há um BILHETE."));
        adicionarObjeto(new ItemPegavel("bilhete", "\nNo bilhete amarelado está escrito: 'A liberdade está sob os pés dos que descansam'."));
        adicionarObjeto(new ObjetoFixo("tapete", "\nUm tapete velho. Parece que há algo embaixo."));
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    private void adicionarObjeto(ObjetoInterativo objeto) {
        this.objetosNaSala.add(objeto);
    }

    public void mostrarSala() {
        System.out.println("\n=========================================================================================");
        System.out.println("Sala: " + this.nome + "\n");
        System.out.println(this.descricao + "\n");
        System.out.println("Objetos na sala:");
        for (ObjetoInterativo obj : this.objetosNaSala) {
            System.out.println(" - " + obj.getNome());
        }
    }

    public ObjetoInterativo buscarObjeto(String nomeDoObjeto) {
        for (ObjetoInterativo obj : this.objetosNaSala) {
            if (obj.getNome().equalsIgnoreCase(nomeDoObjeto)) {
                return obj;
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
        this.objetosNaSala.remove(objetoParaRemover);
    }

    public void revelarChave() {
        if (buscarObjeto("chave") == null) {
            System.out.println("---\nVocê levanta o tapete e encontra uma chave!");
            adicionarObjeto(new ItemPegavel("chave", "\nUma pequena chave de ferro, recém descoberta."));
        } else {
        System.out.println("---\nVocê já pegou a chave que estava aqui.");
        }
    }
}