public class Filme 
{
    
// ATRIBUTOS
    private String titulo;
    private Genero[] generos;
    private double duracao;
    private String[] diretores;
    private String[] atoresPrincipais;
    private int pontuacao;
    private int pontosDeRecomendacao;
        
// MÉTODOS
    
    // CONSTRUTOR
    public Filme(String titulo, Genero[] generos, double duracao, String[] diretores, String[] atoresPrincipais, int pontuacao) 
    {
        this.setTitulo(titulo);
        this.setGeneros(generos);
        this.setDuracao(duracao);
        this.setDiretores(diretores);
        this.setAtoresPrincipais(atoresPrincipais);
        this.setPontuacao(pontuacao);
        this.pontosDeRecomendacao = 0;
    }

    // titulo
    public String getTitulo() 
    {
        return this.titulo;
    }

    public boolean setTitulo(String novoTitulo) 
    {
        if (novoTitulo.length() > 1) 
        {
            this.titulo = novoTitulo;
            return true;
        }

        return false;
    }

    // genero
    public Genero[] getGeneros() 
    {
        return this.generos;
    }

    public boolean setGeneros(Genero[] novosGeneros) 
    {
        this.generos = novosGeneros;
        return true;
    }

    // duracao
    public double getDuracao() 
    {
        return this.duracao;
    }

    public boolean setDuracao(double novaDuracao) 
    {
        if (novaDuracao > 0.0) 
        {
            this.duracao = novaDuracao;
            return true;
        }

        return false;
    }

    // diretor
    public String[] getDiretores() 
    {
        return this.diretores;
    }

    public boolean setDiretores(String[] novosDiretores) 
    {
        if (novosDiretores.length > 0) 
        {
            this.diretores = novosDiretores;
            return true;
        }

        return false;
    }

    // atores
    public String[] getAtoresPrincipais() 
    {
        return this.atoresPrincipais;
    }

    public boolean setAtoresPrincipais(String[] novosAtoresPrincipais) 
    {
        if (novosAtoresPrincipais.length > 0) 
        {
            this.atoresPrincipais = novosAtoresPrincipais;
            return true;
        }

        return false;
    }

    // pontuacao
    public int getPontuacao() 
    {
        return this.pontuacao;
    }

    public boolean setPontuacao(int novaPontuacao) 
    {
        if (novaPontuacao >= 0 && novaPontuacao <= 5) 
        {
            this.pontuacao = novaPontuacao;
            return true;
        }

        return false;
    }

    // pontos de recomendação
    public int getPontosRecomendacao()
    {
        return this.pontosDeRecomendacao;
    }

    public boolean setPontosRecomendacao(int pontos)
    {
        if (pontos > 0)
        {
            this.pontosDeRecomendacao += pontos;
            return true;
        }

        return false;
    }
}
