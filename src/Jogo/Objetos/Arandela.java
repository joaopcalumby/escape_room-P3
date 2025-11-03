package Jogo.Objetos;
import Jogo.Exceptions.ObjetoNaoEncontradoException;
import Jogo.Jogador;
import Jogo.Sala;

public class Arandela extends ObjetoInterativo {

    private boolean chavePegada;
    private boolean tochaColocada;

    public Arandela(String nome, String descricao) {
        super(nome, descricao);
        this.chavePegada = false;
        this.tochaColocada = false;
    }

    @Override
    public String interagir(Sala sala) {
        if (!chavePegada) {
            sala.adicionarObjeto(new ItemPegavel("chave_pequena", "Uma chave de ferro minúscula, deve servir em um fecho pequeno."));
            chavePegada = true;
            return "---\nÉ uma arandela de ferro antiga... Ao olhar dentro do encaixe,\n" +
                   "você encontra uma 'chave_pequena'!";
        } else if (!tochaColocada) {
            return "---\nÉ uma arandela de ferro, própria para uma tocha.";
        } else {
            return "---\nA tocha ilumina o salão a partir da arandela.";
        }
    }

    @Override
    public String usarCom(ItemPegavel item, Sala sala, Jogador jogador) {
        if (item.getNome().equalsIgnoreCase("tocha_acesa")) {
            this.tochaColocada = true;
            sala.setDescricao("O salão está agora totalmente iluminado pela tocha na arandela.\n" +
                              "A PORTA_FINAL ao norte está aberta.");
            
            try {
                jogador.removerItem(item);
            } catch (ObjetoNaoEncontradoException e) {
            }

            try {
                ObjetoInterativo porta = sala.buscarObjeto("porta_final");
                porta.setDescricao("A grande porta de pedra está aberta, revelando a saída.");
                sala.setSaidaLiberada(true);
            } catch (ObjetoNaoEncontradoException e) {
            }

            return "---\nVocê encaixa a tocha acesa na arandela. A sala inteira se ilumina!\n" +
                   "Runas antigas ao redor da PORTA_FINAL brilham e, com um som de pedras se arrastando,\n" +
                   "a porta se abre!";
        }
        return "---\nIsso não parece se encaixar na arandela.";
    }
}