public class Tapete extends ObjetoInterativo {
    
    private boolean chaveRevelada;

    public Tapete(String nome, String descricao) {
        super(nome, descricao);
        this.chaveRevelada = false;
    }

    @Override
    public String interagir(Sala sala) {
        if (!chaveRevelada) {
            chaveRevelada = true;
            sala.adicionarObjeto(new ItemPegavel("chave", "\nUma pequena chave de ferro, recém descoberta."));
            return "---\nVocê levanta o tapete e encontra uma chave!";
        } else {
            return "---\nVocê já olhou debaixo do tapete.";
        }
    }
}