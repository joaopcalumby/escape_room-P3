public class ObjetoInterativo{
    private String nome;
    private String descricao;
    private boolean pegavel;

    public ObjetoInterativo(String nome, String descricao, boolean pegavel) {
        this.nome = nome;
        this.descricao = descricao;
        this.pegavel = pegavel;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isPegavel() {
        return pegavel;
    }
}
