import java.util.ArrayList;
import java.util.List;

public class Sala {
    private String nome;
    private String descricao;
    private List<ObjetoInterativo> objetosNaSala;
    private boolean saidaLiberada;

    public Sala(String nome) {
        this.nome = nome;
        this.descricao = "Você está em uma sala fria com paredes de pedra.\nNo centro, há uma MESA de madeira com um BILHETE em cima dela.\nAo norte, uma grande PORTA de ferro.\nNo chao, um TAPETE empoeirado.";
        this.objetosNaSala = new ArrayList<>();
        this.saidaLiberada = false;

        adicionarObjeto(new Porta("porta", "\nÉ uma porta de ferro maciço. Parece bem trancada.")); // Classe especial
        adicionarObjeto(new ObjetoFixo("mesa", "\nUma mesa de madeira. Sobre ela, há um BILHETE.")); // Classe fixa
        adicionarObjeto(new ItemPegavel("bilhete", "\nNo bilhete amarelado está escrito: 'Onde o conforto repousa, o caminho se revela.'")); // Classe pegável
        adicionarObjeto(new Tapete("tapete", "\nUm tapete velho. Parece esconder algo.")); // Classe especial
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void adicionarObjeto(ObjetoInterativo objeto) {
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

    public ObjetoInterativo buscarObjeto(String nomeDoObjeto) throws ObjetoNaoEncontradoException {
        for (ObjetoInterativo obj : this.objetosNaSala) {
            if (obj.getNome().equalsIgnoreCase(nomeDoObjeto)) {
                return obj;
            }
        } 
        throw new ObjetoNaoEncontradoException("---\nNao ha '" + nomeDoObjeto + "' na sala.");
    }

    public boolean isSaidaLiberada() {
        return this.saidaLiberada;
    }
    
    public void setSaidaLiberada(boolean liberada) {
        this.saidaLiberada = liberada;
    }

    public void removerObjeto(ObjetoInterativo objetoParaRemover) {
        this.objetosNaSala.remove(objetoParaRemover);
    }
}