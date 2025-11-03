import java.util.ArrayList;
import java.util.List;

public class Sala {
    private String nome;
    private String descricao;
    private List<ObjetoInterativo> objetosNaSala;
    private boolean saidaLiberada;

    public Sala(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
        this.objetosNaSala = new ArrayList<>();
        this.saidaLiberada = false;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
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