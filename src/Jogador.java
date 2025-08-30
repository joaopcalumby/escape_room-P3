public class Jogador {
    private String nome;
    private ObjetoInterativo[] inventario;
    private int itensNoInventario;

    public Jogador(String nome, int tamanhoInventario) {
        this.nome = nome;
        this.inventario = new ObjetoInterativo[tamanhoInventario];
        this.itensNoInventario = 0;
    }

    public String getNome() {
        return nome;
    }

    public boolean pegarItem(ObjetoInterativo item) {
        if (this.itensNoInventario < this.inventario.length) {
            this.inventario[this.itensNoInventario] = item;
            this.itensNoInventario++;
            return true;
        } else {
            return false;
        }
    }

    public void mostrarInventario() {
        System.out.println("\n---\nJogador: " + this.nome);
        System.out.println("Inventário: ");
        if (this.itensNoInventario == 0){
            System.out.println(" - Vazio");
        } else {
            for (int i = 0; i < this.itensNoInventario; i++) {
                System.out.println(" - " + this.inventario[i].getNome());
            }
        }
    }

    public ObjetoInterativo buscarItem(String nomeDoItem) {
        for (int i = 0; i < this.itensNoInventario; i++) {
            if (this.inventario[i].getNome().equalsIgnoreCase(nomeDoItem)) {
                return this.inventario[i];
            }
        }
        return null;
    }
}
