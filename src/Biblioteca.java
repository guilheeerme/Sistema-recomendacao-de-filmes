public class Biblioteca {
    public static void listarComSeparador(String[] vetor, String separador)
    {
        for (int contador = 0; contador < vetor.length; contador++)
        {
            System.out.print(vetor[contador]);
            if (contador + 1 < vetor.length)
            {
                System.out.print(separador);
            }
        }
    }

    public static void listarGeneros()
    {
        for (Genero genero : Genero.values()) 
        {
            System.out.printf("\t\t %d. %s\n", genero.getNumeroGenero(), genero.getDescricaoGenero());
        }
    }

    public static void listarDuracoes()
    {
        for (Duracao duracao : Duracao.values()) 
        {
            System.out.printf("\t\t %d. %s\n", duracao.getNumeroDuracao(), duracao.getDescricaoDuracao());
        }
    }

    public static void exibirMensagemErro(String mensagem)
    {
        System.out.println("\n" + mensagem);
    }

    public static Duracao converterMinutosParaDuracao(double minutos)
    {
        if (minutos <= 40.0)
        {
            return Duracao.CURTOS;
        }
        else if (minutos > 40 && minutos <= 60)
        {
            return Duracao.INTERMEDIARIOS;
        }
        else if (minutos > 60 && minutos <= 130)
        {
            return Duracao.LONGA_METRAGEM;
        }
        else
        {
            return Duracao.EPICOS;
        }
    }

    public static String converterParaHorasMinutos(double valor) 
    {
        double totalHoras = valor / 60;
        double totalMinutos = valor % 60;
        return (int)totalHoras + "h" + (int)totalMinutos + "min";
    }

    public static String[] converterStringParaArray(String string, String separador)
    {
        String[] arrayString = string.split(separador, -1);

        int tamArrayString = arrayString.length;
        String[] array = new String[tamArrayString];
        for (int i = 0; i < tamArrayString; i++)
        {
            array[i] = arrayString[i].trim();
        }
        return array;
    }
}