import java.util.Scanner;

public class Usuario 
{

// ATRIBUTOS
    private String nome;
    private Genero[] generosPreferidos;
    private String[] atoresPreferidos;
    private String[] diretoresPreferidos;
    private Duracao duracao;

// MÉTODOS

    // CONSTRUTOR  
    public Usuario()
    {

    }

    // exibe uma série de perguntas ao usuário, armazenando os dados de retorno
    public void configurarPreferencias() 
    {
        String separador = ",";

        System.out.println("\n--- \tConfigurar preferências do usuário: \t---");
        Scanner in = new Scanner(System.in);
        
        // generos
        System.out.print("\n\t - Selecione os gêneros preferidos separados por \",\":\n");
        Biblioteca.listarGeneros();
        System.out.print("\t   Generos escolhidos separados por \",\": ");

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

        // atores
        System.out.print("\n\t - Informe o nome dos atores preferidos separados por \",\": ");
        String[] atoresPreferidos = Biblioteca.converterStringParaArray(in.nextLine(), separador);

        // diretores
        System.out.print("\n\t - Informe o nome dos diretores preferidos separados por \",\": ");
        String[] diretoresPreferidos = Biblioteca.converterStringParaArray(in.nextLine(), separador);

        // duracao
        System.out.print("\n\t - Informe a duração dos filmes: \n");
        Biblioteca.listarDuracoes();
        System.out.print("\t   Duração escolhida: ");
        double duracao = in.nextDouble();

        this.setGenerosPreferidos(generos);
        this.setAtoresPreferidos(atoresPreferidos);
        this.setDiretoresPreferidos(diretoresPreferidos);
        this.setDuracao(duracao);

        System.out.println("\n--- \tPreferências do usuário cadastradas com sucesso! \t---");
    }

    // lista as preferências dos usuário
    public void listarPreferenciasUsuario()
    {
        System.out.println("\n--- \tLista de preferências do usuário: \t---\n");

        String separador = ", ";

        System.out.print("\t - Gêneros preferidos: ");
        Genero[] generos = this.getGenerosPreferidos();
        for (int numGenero = 0; numGenero < generos.length; numGenero++)
        {
            System.out.print(generos[numGenero].getDescricaoGenero());
            if (numGenero + 1 < generos.length)
            {
                System.out.print(separador);
            }
        }

        // atores
        System.out.print("\n\t - Atores preferidos: ");
        Biblioteca.listarComSeparador(this.getAtoresPreferidos(), separador);

        // diretores
        System.out.print("\n\t - Diretores preferidos: ");
        Biblioteca.listarComSeparador(this.getDiretoresPreferidos(), separador);

        // duracao
        System.out.printf("\n\t - Duração preferida: %s\n", this.getDuracao().getDescricaoDuracao());

        System.out.println("\n--- \t--- \t---");
    }
    
    // nome
    public String getNome() 
    {
        return this.nome;
    }

    public boolean setNome(String novoNome) 
    {
        if (novoNome.length() > 0) 
        {
            this.nome = novoNome;
            return true;
        }

        return false;
    }

    // generosPreferidos
    public Genero[] getGenerosPreferidos() 
    {
        return this.generosPreferidos;
    }

    public boolean setGenerosPreferidos(Genero[] novosGenerosPreferidos) 
    {
        if (novosGenerosPreferidos.length > 0) 
        {
            this.generosPreferidos = novosGenerosPreferidos;
            return true;
        }

        return false;
    }

    // atoresPreferidos
    public String[] getAtoresPreferidos() 
    {
        return this.atoresPreferidos;
    }

    public boolean setAtoresPreferidos(String[] novosAtoresPreferidos) 
    {
        if (novosAtoresPreferidos.length > 0) 
        {
            this.atoresPreferidos = novosAtoresPreferidos;
            return true;
        }

        return false;
    }

    // diretoresPreferidos
    public String[] getDiretoresPreferidos() 
    {
        return this.diretoresPreferidos;
    }

    public boolean setDiretoresPreferidos(String[] novosDiretoresPreferidos) 
    {
        if (novosDiretoresPreferidos.length > 0) 
        {
            this.diretoresPreferidos = novosDiretoresPreferidos;
            return true;
        }

        return false;
    }

    // duracao
    public Duracao getDuracao() 
    {
        return this.duracao;
    }

    public boolean setDuracao(double novaDuracao) 
    {
        if (novaDuracao > 0.0 && novaDuracao < 5.0) 
        {
            this.duracao = Duracao.values()[(int)novaDuracao-1];
            return true;
        }

        return false;
    }
}
