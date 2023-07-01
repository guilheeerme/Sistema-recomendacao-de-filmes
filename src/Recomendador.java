public class Recomendador {
    
// ATRIBUTOS
    Filmes filmes;
    Usuario usuario;
    Filme[] filmesOrdenados;

// CONSTRUTOR
    public Recomendador(Filmes filmes, Usuario usuario)
    {
        this.filmes = filmes;
        this.usuario = usuario;
        this.filmesOrdenados = new Filme[filmes.getFilmes().length];
    }

// MÉTODOS

    // O sistema de recomendação de filmes é feito com base na pontuação de recomendação
    // Se o filme atender uma preferência do usuário, ele ganha um ponto
    // Ex: o usuário gosta de dois atores, se um filme tiver esses dois atores ele ganha 2 pontos
    public void recomendarFilmes()
    {
        Genero[] generosPreferidosUsuario = usuario.getGenerosPreferidos();
        String[] atoresPreferidoUsuario = usuario.getAtoresPreferidos();
        String[] diretoresPreferidosUsuario = usuario.getDiretoresPreferidos();
        Duracao duracaoUsuario = usuario.getDuracao();

        // se o usuário tiver configurado suas preferências
        if (generosPreferidosUsuario != null &&
            atoresPreferidoUsuario != null &&
            diretoresPreferidosUsuario != null &&
            duracaoUsuario != null)
        {
            Filme[] filmes = this.filmes.getFilmes();

            // para cada filme, calcula os pontos de recomendação
            for (int index = 0; index < this.filmes.getTotalFilmes(); index++)
            {
                Filme filme = filmes[index];

                if (filme.getPontosRecomendacao() == 0)
                {
                    this.calcularPontosGeneros(filme, generosPreferidosUsuario);
                    this.calcularPontosAtores(filme, atoresPreferidoUsuario);
                    this.calcularPontosDiretores(filme, diretoresPreferidosUsuario);
                    this.calcularPontosDuracao(filme, duracaoUsuario);
                }
            }
            
            // ordena os filmes com base na pontuação, da maior para a menor
            this.ordenarFilmesPelaPontuacao(filmes);
            this.filmes.listarFilmes(filmesOrdenados, 10, "Aqui está uma lista de filmes de acordo com seu gosto!");
        }
        else
        {
            Biblioteca.exibirMensagemErro("Ops! Você precisa cadastrar todas as preferências de usuário para que o sistema possa recomendar os melhores filmes!");
        }
    }

    // calcula pontos com base nos gêneros
    private void calcularPontosGeneros(Filme filme, Genero[] generosPreferidosUsuario)
    {
        for (Genero generoPreferidoUsuario : generosPreferidosUsuario)
        {
            for (Genero generoFilme : filme.getGeneros())
            {
                if (generoPreferidoUsuario == generoFilme)
                {
                    filme.setPontosRecomendacao(1);
                }
            }
        }
    }

    // calcula pontos com base nos atores
    private void calcularPontosAtores(Filme filme, String[] atoresPreferidoUsuario)
    {
        for (String atorPreferidoUsuario : atoresPreferidoUsuario)
        {
            for (String atorFilme : filme.getAtoresPrincipais())
            {
                if (atorPreferidoUsuario.equals(atorFilme))
                {
                    filme.setPontosRecomendacao(1);
                }
            }
        }
    }

    // calcula pontos com base nos diretores
    private void calcularPontosDiretores(Filme filme, String[] diretoresPreferidosUsuario)
    {
        for (String diretorPreferidoUsuario : diretoresPreferidosUsuario)
        {
            for (String diretorFilme : filme.getDiretores())
            {
                if (diretorPreferidoUsuario.equals(diretorFilme))
                {
                    filme.setPontosRecomendacao(1);
                }
            }
        }
    }

    // calcula pontos com base na duração
    private void calcularPontosDuracao(Filme filme, Duracao duracaoUsuario)
    {
        if (duracaoUsuario == Biblioteca.converterMinutosParaDuracao(filme.getDuracao()))
        {
            filme.setPontosRecomendacao(1);
        }
    }

    // ordena os filmes de acordo com os seguintes critérios:
    // 1º critério: ordena os filmes com maiores pontos de recomendação, do maior para o menor
    // 2º critério: se dois filmes tiverem a mesma quantidade de pontos de recomendação, ordena os filmes de acordo com a avaliação/pontuação, da maior para menor
    private void ordenarFilmesPelaPontuacao(Filme[] filmes)
    {
        Filme[] filmesOrdenados = filmes;
        int totalFilmes = this.filmes.getTotalFilmes();

        for (int indiceX = 1; indiceX < totalFilmes; indiceX++)
        {
            int pontosRecomendacaoFilmeX = filmesOrdenados[indiceX].getPontosRecomendacao();
            for (int indiceY = 0; indiceY < indiceX; indiceY++)
            {
                int pontosRecomendacaoFilmeY = filmesOrdenados[indiceY].getPontosRecomendacao();
                if (pontosRecomendacaoFilmeX > pontosRecomendacaoFilmeY ||
                   (pontosRecomendacaoFilmeX == pontosRecomendacaoFilmeY && filmesOrdenados[indiceX].getPontuacao() > filmesOrdenados[indiceY].getPontuacao()))
                {
                    Filme temp = filmesOrdenados[indiceX];
                    filmesOrdenados[indiceX] = filmesOrdenados[indiceY];
                    filmesOrdenados[indiceY] = temp;
                }
            }
        }

        this.filmesOrdenados = filmesOrdenados;
    }
}
