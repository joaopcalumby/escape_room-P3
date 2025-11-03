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
}