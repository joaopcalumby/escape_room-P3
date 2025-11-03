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

    public boolean pegarItem(ObjetoInterativo item) {
        if (this.inventario.size() < this.tamanhoInventario) {
            this.inventario.add(item);
            return true;
        } else {
            return false;
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

    public ObjetoInterativo buscarItem(String nomeDoItem) {
        for (ObjetoInterativo item : this.inventario) {
            if (item.getNome().equalsIgnoreCase(nomeDoItem)) {
                return item;
            }
        }
        return null;
    }
}