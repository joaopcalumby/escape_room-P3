package Jogo.Objetos;
import Jogo.Exceptions.InventarioCheioException;
import Jogo.Exceptions.ObjetoNaoEncontradoException;
import Jogo.Jogador;
import Jogo.Sala;

public class Tocha extends ItemPegavel {

    public Tocha(String nome, String descricao) {
        super(nome, descricao);
    }

    @Override
    public String usarCom(ItemPegavel item, Sala sala, Jogador jogador) {
        if (item.getNome().equalsIgnoreCase("pederneira")) {
            try {
                jogador.removerItem(this);
                jogador.removerItem(item);
                
                ItemPegavel tochaAcesa = new ItemPegavel("tocha_acesa", "Uma tocha queimando com uma chama forte. Ilumina bem.");
                jogador.pegarItem(tochaAcesa);
                
                return "\n---\nVocê raspa a pederneira, e faíscas acendem a ponta da tocha. Agora você tem luz!";
            
            } catch (InventarioCheioException e) {
                try {
                    jogador.pegarItem(this);
                    jogador.pegarItem(item);
                } catch (Exception e2) {  }
                
                return "\n---\nVocê tenta acender a tocha, mas nao há espaço no seu inventário para o item aceso.";

            } catch (ObjetoNaoEncontradoException e) {
                return "\n---\nAlgo estranho aconteceu. Tente novamente.";
            }
        }
        return super.usarCom(item, sala, jogador);
    }
}