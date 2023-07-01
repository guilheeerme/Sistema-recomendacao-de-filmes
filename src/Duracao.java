public enum Duracao {

    // ENUM que será utilizado para duração dos filmes
    CURTOS(1, "Curtos"),
    INTERMEDIARIOS(2, "Intermediários"),
    LONGA_METRAGEM(3, "Longa-metragem"),
    EPICOS(4, "Épicos");
    
    private int numero;
    private String descricao;

    private Duracao(int numero, String descricao) 
    {
        this.numero = numero;
        this.descricao = descricao;
    }

    public int getNumeroDuracao() 
    {
        return this.numero;
    }

    public String getDescricaoDuracao()
    {
        return this.descricao;
    }
}
