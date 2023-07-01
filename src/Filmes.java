import java.util.Scanner;

public class Filmes 
{

// ATRIBUTOS
    private Filme[] filmes;
    private int totalFilmes;
    private int totalEstrelas;
    private double pontuacaoMedia;

// MÉTODOS

    //CONSTRUTOR
    public Filmes() 
    {
        this.totalFilmes = 0;
        this.totalEstrelas = 0;
        this.pontuacaoMedia = 0.0;
        this.carregarFilmes();
    }

    // filmes
    public Filme[] getFilmes()
    {
        return this.filmes;
    }

    // faz uma série de perguntas ao usuário para realizar o cadastro de um filme, armazenando seus dados
    public void cadastrarFilme()
    {
        String separador = ",";

        System.out.println("\n--- \tCadastrar novo filme: \t---\n");
        Scanner in = new Scanner(System.in);

        // titulo
        System.out.print("\t - Digite o título do filme: ");
        String titulo = in.nextLine();

        // generos
        System.out.print("\n\t - Selecione os gêneros separados por \",\"\n");
        Biblioteca.listarGeneros();
        System.out.print("\t   Generos escolhidos: ");

        // "replaceAll" remove espaços em branco
        String[] arrayStringGeneros = in.nextLine()
                                        .replaceAll("\\s+","")
                                        .split(",", -1);

        int tamArrayStringGeneros = arrayStringGeneros.length;
        Genero[] generos = new Genero[tamArrayStringGeneros];
        for (int i = 0; i < tamArrayStringGeneros; i++)
        {
            generos[i] = Genero.values()[Integer.parseInt(arrayStringGeneros[i])-1];
        }

        // diretores
        System.out.print("\n\t - Informe o nome do(s) diretores(s) separados por \",\": ");
        String[] diretores = Biblioteca.converterStringParaArray(in.nextLine(), separador);

        // atores
        System.out.print("\n\t - Informe o nome dos atores principais separados por \",\": ");
        String[] atores = Biblioteca.converterStringParaArray(in.nextLine(), separador);

        // duracao
        System.out.print("\n\t - Informe a duração do filme: ");
        double duracao = in.nextDouble();

        // pontuacao
        System.out.print("\n\t - Informe a pontuação do filme: ");
        int pontuacao = in.nextInt();

        this.adicionarFilme(titulo, generos, duracao, diretores, atores, pontuacao);
        System.out.println("\n--- \tFilme cadastrado com sucesso! \t---");
    }

    // adiciona um filme e calcula variáveis como total de filmes, total de estrelas e pontuação média dos filmes
    public void adicionarFilme(String titulo, Genero[] generos, double duracao, String[] diretores, String[] atores, int pontuacao)
    {
        Filme filme = new Filme(
            titulo, 
            generos, 
            duracao,
            diretores,
            atores,
            pontuacao
        );

        this.filmes[this.totalFilmes] = filme;
        this.setTotalFilmes();
        this.setTotalEstrelas(pontuacao);
        this.calcularPontuacaoMedia();
    }

    // lista os filmes de acordo com os filmes passados e a quantidade de filmes desejada na listagem, além de um título personalizado
    public void listarFilmes(Filme[] filmes, int totalFilmesListagem, String titulo) 
    {
        System.out.println("\n--- \t"+ titulo +" \t---\n");

        for (int numFilme = 0; numFilme < totalFilmesListagem; numFilme++) 
        {
            Filme filme = filmes[numFilme];
            String separador = ", ";

            // título e gênero
            System.out.printf("\t%d. \"%s\" (", numFilme+1, filme.getTitulo());

            Genero[] generos = filme.getGeneros();
            for (int numGenero = 0; numGenero < generos.length; numGenero++)
            {
                System.out.print(generos[numGenero].getDescricaoGenero());
                if (numGenero + 1 < generos.length)
                {
                    System.out.print(separador);
                }
                else
                {
                    System.out.print(")");
                }
            }

            // diretores
            System.out.print("\n\t - Diretor(es): ");
            Biblioteca.listarComSeparador(filme.getDiretores(), separador);

            // atores
            System.out.print("\n\t - Principais atores: ");
            Biblioteca.listarComSeparador(filme.getAtoresPrincipais(), separador);

            // duracao
            System.out.printf("\n\t - Duração: %s", Biblioteca.converterParaHorasMinutos(filme.getDuracao()));

            // pontuacao
            int estrelas = filme.getPontuacao();
            System.out.printf("\n\t - Pontuação: %d estrelas\n\n", estrelas);
            System.out.printf("\t - Pontos: %d\n\n", filme.getPontosRecomendacao());
        }
        
        System.out.printf("--- \t PONTUAÇÃO MÉDIA DE TODOS OS FILMES CADASTRADOS: %.2f \t---\n", this.pontuacaoMedia);
    }

    // lista as estatísticas
    public void listarEstatisticas()
    {
        System.out.println("\n--- \tEstatísticas: \t---");

        System.out.printf("\n\t - Total de filmes cadastrados: %d", this.totalFilmes);

        System.out.printf("\n\t - Pontuação média dos filmes: %.2f", this.pontuacaoMedia);

        int curtos = 0;
        int intermediarios = 0;
        int longaMetragem = 0;
        int epicos = 0;

        for (int numFilme = 0; numFilme < totalFilmes; numFilme++)
        {
            double duracao = this.filmes[numFilme].getDuracao();
            if (duracao <= 40.0)
            {
                curtos++;
            }
            else if (duracao > 40 && duracao <= 60)
            {
                intermediarios++;
            }
            else if (duracao > 60 && duracao <= 130)
            {
                longaMetragem++;
            }
            else
            {
                epicos++;
            }
        }

        System.out.println("\n\t - Percentual de cada duração:");
        System.out.printf("\t\t - %s: %.0f%%\n", Duracao.CURTOS.getDescricaoDuracao(), this.calculaPercentualFilmes(curtos));
        System.out.printf("\t\t - %s: %.0f%%\n", Duracao.INTERMEDIARIOS.getDescricaoDuracao(), this.calculaPercentualFilmes(intermediarios));
        System.out.printf("\t\t - %s: %.0f%%\n", Duracao.LONGA_METRAGEM.getDescricaoDuracao(), this.calculaPercentualFilmes(longaMetragem));
        System.out.printf("\t\t - %s: %.0f%%\n", Duracao.EPICOS.getDescricaoDuracao(), this.calculaPercentualFilmes(epicos));

        System.out.println("\n--- \t--- \t---");
    }

    // carrega uma série de dados de filmes e faz o cadastro
    private void carregarFilmes() 
    {
        // até 100000 filmes
        this.filmes = new Filme[100000];
        
        String[] titulos = { "O Poderoso Chefão",
            "Pulp Fiction: Tempo de Violência",
            "Interestelar",
            "Clube da Luta",
            "Cidade de Deus",
            "A Origem",
            "O Senhor dos Anéis: O Retorno do Rei",
            "A Lista de Schindler",
            "Seven: Os Sete Crimes Capitais",
            "Forrest Gump: O Contador de Histórias",
            "O Grande Lebowski",
            "Gladiador",
            "A Viagem de Chihiro",
            "Ponte dos Espiões",
            "Matrix",
            "O Silêncio dos Inocentes",
            "O Labirinto do Fauno",
            "O Iluminado",
            "Era uma Vez no Oeste",
            "Os Infiltrados",
            "Blade Runner 2049",
            "A Vida é Bela",
            "Laranja Mecânica",
            "O Sexto Sentido",
            "A Chegada",
            "Cidade dos Sonhos",
            "O Rei Leão",
            "Psicose",
            "O Fabuloso Destino de Amélie Poulain",
            "O Senhor dos Anéis: A Sociedade do Anel"
        };
        Genero[][] generos = { {Genero.CRIME, Genero.DRAMA},
            {Genero.CRIME, Genero.DRAMA},
            {Genero.FICCAO, Genero.AVENTURA, Genero.DRAMA},
            {Genero.DRAMA, Genero.SUSPENSE},
            {Genero.CRIME, Genero.DRAMA},
            {Genero.FICCAO, Genero.ACAO, Genero.AVENTURA},
            {Genero.ACAO, Genero.AVENTURA, Genero.FANTASIA},
            {Genero.BIOGRAFIA, Genero.DRAMA, Genero.HISTORIA},
            {Genero.CRIME, Genero.DRAMA, Genero.SUSPENSE},
            {Genero.DRAMA, Genero.ROMANCE},
            {Genero.COMEDIA, Genero.CRIME},
            {Genero.ACAO, Genero.AVENTURA, Genero.DRAMA},
            {Genero.AVENTURA, Genero.FANTASIA},
            {Genero.DRAMA, Genero.SUSPENSE},
            {Genero.ACAO, Genero.FICCAO},
            {Genero.CRIME, Genero.DRAMA, Genero.SUSPENSE},
            {Genero.DRAMA, Genero.FANTASIA},
            {Genero.DRAMA, Genero.TERROR},
            {Genero.FAROESTE},
            {Genero.CRIME, Genero.DRAMA, Genero.SUSPENSE},
            {Genero.FICCAO, Genero.DRAMA},
            {Genero.COMEDIA, Genero.DRAMA, Genero.ROMANCE},
            {Genero.CRIME, Genero.DRAMA, Genero.FICCAO},
            {Genero.DRAMA, Genero.MISTERIO, Genero.SUSPENSE},
            {Genero.DRAMA, Genero.FICCAO},
            {Genero.DRAMA, Genero.MISTERIO},
            {Genero.AVENTURA, Genero.DRAMA},
            {Genero.CRIME, Genero.DRAMA, Genero.TERROR},
            {Genero.COMEDIA, Genero.ROMANCE},
            {Genero.ACAO, Genero.AVENTURA, Genero.FANTASIA} 
        };
        String[][] atores = { {"Al Pacino", "Marlon Brando", "James Caan"},
            {"Robert De Niro", "Al Pacino", "Joe Pesci"},
            {"Harrison Ford", "Mark Hamill", "Carrie Fisher"},
            {"Tim Robbins", "Morgan Freeman"},
            {"Christian Bale", "Heath Ledger", "Aaron Eckhart"},
            {"Leonardo DiCaprio", "Joseph Gordon-Levitt", "Ellen Page"},
            {"Elijah Wood", "Viggo Mortensen", "Ian McKellen"},
            {"Andrew Garfield", "Emma Stone", "Rhys Ifans"},
            {"Kevin Spacey", "Gabriel Byrne", "Chazz Palminteri"},
            {"Tom Hanks", "Robin Wright", "Gary Sinise"},
            {"Kevin Spacey", "Gabriel Byrne", "Stephen Baldwin"},
            {"Jim Carrey", "Ed Harris", "Laura Linney"},
            {"Matthew Broderick", "Jeremy Irons", "James Earl Jones"},
            {"Anthony Perkins", "Janet Leigh", "Vera Miles"},
            {"Audrey Tautou", "Mathieu Kassovitz", "Rufus"},
            {"Edward Norton", "Brad Pitt", "Helena Bonham Carter"},
            {"Matthew McConaughey", "Anne Hathaway", "Jessica Chastain"},
            {"Johnny Depp", "Geoffrey Rush", "Orlando Bloom"},
            {"Daniel Craig", "Judi Dench", "Javier Bardem"},
            {"Tom Hanks", "Tom Sizemore", "Edward Burns"},
            {"Keanu Reeves", "Carrie-Anne Moss", "Laurence Fishburne"},
            {"Jodie Foster", "Anthony Hopkins", "Scott Glenn"},
            {"Ivana Baquero", "Sergi López", "Maribel Verdú"},
            {"Jack Nicholson", "Shelley Duvall", "Danny Lloyd"},
            {"Henry Fonda", "Charles Bronson", "Claudia Cardinale"},
            {"Leonardo DiCaprio", "Matt Damon", "Jack Nicholson"},
            {"Ryan Gosling", "Harrison Ford", "Ana de Armas"},
            {"Roberto Benigni", "Nicoletta Braschi", "Giorgio Cantarini"},
            {"Malcolm McDowell", "Patrick Magee", "Michael Bates"},
            {"Bruce Willis", "Haley Joel Osment", "Toni Collette"},
            {"Amy Adams", "Jeremy Renner", "Forest Whitaker"},
            {"Naomi Watts", "Laura Harring", "Justin Theroux"},
            {"Matthew Broderick", "Jeremy Irons", "James Earl Jones"}
        };
        String[][] diretores = { {"Francis Ford Coppola"},
            {"Martin Scorsese"},
            {"George Lucas"},
            {"Frank Darabont"},
            {"Christopher Nolan"},
            {"Christopher Nolan"},
            {"Peter Jackson"},
            {"Marc Webb"},
            {"Bryan Singer"},
            {"Robert Zemeckis"},
            {"Bryan Singer"},
            {"Frank Darabont"},
            {"Roger Allers", "Rob Minkoff"},
            {"Alfred Hitchcock"},
            {"Jean-Pierre Jeunet"},
            {"David Fincher"},
            {"Christopher Nolan"},
            {"Gore Verbinski"},
            {"Sam Mendes"},
            {"Lana Wachowski", "Lilly Wachowski"},
            {"Jonathan Demme"},
            {"Guillermo del Toro"},
            {"Stanley Kubrick"},
            {"Sergio Leone"},
            {"Martin Scorsese"},
            {"Denis Villeneuve"},
            {"Ryan Gosling"},
            {"Roberto Benigni"},
            {"Stanley Kubrick"},
            {"M. Night Shyamalan"},
            {"Denis Villeneuve"},
            {"David Lynch"},
            {"Roger Allers", "Rob Minkoff"}
        };
        double[] duracoes = { 175.0,
            202.0,
            121.0,
            142.0,
            152.0,
            148.0,
            201.0,
            136.0,
            106.0,
            142.0,
            106.0,
            142.0,
            89.0,
            109.0,
            122.0,
            139.0,
            169.0,
            143.0,
            123.0,
            136.0,
            136.0,
            118.0,
            142.0,
            136.0,
            137.0,
            164.0,
            163.0,
            116.0,
            146.0,
            118.0,
            157.0,
            89.0,
            89.0
        };
        int[] pontuacoes = { 5, 4, 5, 4, 5, 4, 5, 5, 4, 5, 4, 4, 5, 4, 5, 4, 5, 4, 5, 4, 5, 5, 4, 4, 5, 4, 5, 4, 5, 5 };

        for (int i = 0; i < titulos.length; i++) 
        {
            this.adicionarFilme(titulos[i], generos[i], duracoes[i], diretores[i], atores[i], pontuacoes[i]);
        }
    }

    // totalFilmes
    public int getTotalFilmes()
    {
        return this.totalFilmes;
    }

    private void setTotalFilmes() 
    {
        this.totalFilmes++;
    }

    // totalEstrelas
    public int getTotalEstrelas()
    {
        return this.totalEstrelas;
    }

    private void setTotalEstrelas(int estrelas)
    {
        this.totalEstrelas += estrelas;
    }

    // pontuacaoMedia
    public double getPontuacaoMedia()
    {
        return this.pontuacaoMedia;
    }
    
    private double calculaPercentualFilmes(int numFilmes)
    {
        return numFilmes * 100 / this.totalFilmes;
    }

    private void calcularPontuacaoMedia()
    {
        this.pontuacaoMedia = (double)this.totalEstrelas / (double)this.totalFilmes;
    }
}