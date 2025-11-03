import java.util.ArrayList;
import java.util.List;

public class Jogador {
    private String nome;
    private List<ObjetoInterativo> inventario;
    private int tamanhoInventario;

    public Jogador(String nome, int tamanhoInventario) {
        this.nome = nome;
        this.tamanhoInventario = tamanhoInventario;
        this.inventario = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void pegarItem(ObjetoInterativo item) throws InventarioCheioException {
        if (this.inventario.size() < this.tamanhoInventario) {
            this.inventario.add(item);
        } else {
            throw new InventarioCheioException("---\nSeu inventario esta cheio!");
        }
    }
    
    public void removerItem(ObjetoInterativo item) throws ObjetoNaoEncontradoException {
        if (!this.inventario.remove(item)) {
             throw new ObjetoNaoEncontradoException("---\n(Erro interno) Item não encontrado para remoção.");
        }
    }


    public void mostrarInventario() {
        System.out.println("\n---\nJogador: " + this.nome);
        System.out.println("Inventário: ");
        
        if (this.inventario.isEmpty()){
            System.out.println(" - Vazio");
        } else {
            for (ObjetoInterativo item : this.inventario) {
                System.out.println(" - " + item.getNome());
            }
        }
    }

    public ObjetoInterativo buscarItem(String nomeDoItem) throws ObjetoNaoEncontradoException {
        for (ObjetoInterativo item : this.inventario) {
            if (item.getNome().equalsIgnoreCase(nomeDoItem)) {
                return item;
            }
        }
        throw new ObjetoNaoEncontradoException("---\nVocê não tem '" + nomeDoItem + "' no seu inventario.");
    }
}