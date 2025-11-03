public class Porta extends ObjetoInterativo {

    public Porta(String nome, String descricao) {
        super(nome, descricao);
    }

    @Override
    public String usarCom(ItemPegavel item, Sala sala, Jogador jogador) {
        if (item.getNome().equalsIgnoreCase("chave")) {
            sala.setSaidaLiberada(true); // Avisa à sala que o enigma foi resolvido
            this.setDescricao("\nÉ uma porta de ferro maciço. A fechadura foi aberta.");
            return "---\nFuncionou!\nA chave gira e você escuta um clique. A porta está destrancada.";
        } else {
            return "---\nIsso nao parece ter efeito na porta.\n";
        }
    }
}