package Jogo.Objetos;
import Jogo.Jogador;
import Jogo.Sala;

public class ObjetoInterativo{
    private String nome;
    private String descricao;

    public ObjetoInterativo(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
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

    public String interagir(Sala sala) {
        if (this instanceof ItemPegavel) {
            return this.getDescricao();
        }
        return "---\nNão acontece nada de interessante.";
    }

    public String usarCom(ItemPegavel item, Sala sala, Jogador jogador) {
        return "---\nIsso nao parece ter efeito.\n";
    }
}