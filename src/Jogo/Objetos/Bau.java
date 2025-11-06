package Jogo.Objetos;
import Jogo.Jogador;
import Jogo.Sala;

public class Bau extends ObjetoInterativo {
    private boolean trancado;
    private boolean itensPegos;

    public Bau(String nome, String descricao) {
        super(nome, descricao);
        this.trancado = true;
        this.itensPegos = false;
    }

    @Override
    public String interagir(Sala sala) {
        if (trancado) {
            return "---\nO baú está trancado com um fecho pequeno.";
        } 
        
        if (!itensPegos) {
            sala.adicionarObjeto(new Tocha("tocha", "Uma tocha de madeira com a ponta encharcada em piche."));
            sala.adicionarObjeto(new ItemPegavel("pederneira", "Um conjunto de pederneira e sílex, para fazer fogo."));
            
            this.itensPegos = true;

            this.setDescricao("Um baú de madeira aberto. Dentro, você vê uma 'tocha' e uma 'pederneira'.");
            
            return "---\nDentro do baú agora aberto, você encontra uma 'tocha' apagada e uma 'pederneira'.";
        } 
        
        this.setDescricao("Um baú de madeira aberto. Está vazio.");
            
        return "---\nO baú está vazio.";
    }

    @Override
    public String usarCom(ItemPegavel item, Sala sala, Jogador jogador) {
        if (item.getNome().equalsIgnoreCase("chave_pequena") && trancado) {
            this.trancado = false;

            this.setDescricao("Um baú de madeira. O fecho está destrancado.");
            
            return "---\nO fecho do baú se abre com um clique.";
        } else if (!trancado) {
            return "---\nO baú já está destrancado.";
        }
        return "---\nEssa chave não serve no fecho.";
    }
}