public enum Genero
{
    // ENUM que será usado nas classes que precisam de informações referentes aos tipos de generos
    ACAO(1, "Ação"), 
    AVENTURA(2, "Aventura"), 
    DRAMA(3, "Drama"), 
    COMEDIA(4, "Comedia"), 
    ROMANCE(5, "Romance"), 
    FICCAO(6, "Ficção"), 
    TERROR(7, "Terror"), 
    CRIME(8, "Crime"),
    SUSPENSE(9, "Suspense"),
    HISTORIA(10, "História"),
    BIOGRAFIA(11, "Biografia"),
    FANTASIA(12, "Fantasia"),
    FAROESTE(13, "Faroeste"),
    MISTERIO(14, "Mistério");

    private int numero;
    private String descricao;

    private Genero(int numero, String descricao) 
    {
        this.numero = numero;
        this.descricao = descricao;
    }

    public int getNumeroGenero() 
    {
        return this.numero;
    }

    public String getDescricaoGenero()
    {
        return this.descricao;
    }
}
