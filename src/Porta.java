public class Porta extends ObjetoInterativo {

    public Porta(String nome, String descricao) {
        super(nome, descricao);
    }

    @Override
    public String usarCom(ItemPegavel item, Sala sala) {
        if (item.getNome().equalsIgnoreCase("chave")) {
            sala.setSaidaLiberada(true);
            return "---\nFuncionou!\n";
        } else {
            return "---\nIsso nao parece ter efeito na porta.\n";
        }
    }
}