import java.util.Scanner;

public class App 
{
    public static void sair()
    {
        System.out.println("\nVocê encerrou o sistema. Volte sempre!");
    }

    public static void main(String[] args) 
    {
        System.out.println("--- Bem vindo ao sistema de recomendação de filmes! --- \n");

        Scanner in = new Scanner(System.in);

        Filmes filmes = new Filmes();
        Usuario usuario = new Usuario();
        Menu menu = new Menu();

        boolean repetidor = true;

        do 
        {
            menu.exibirMenu();
            System.out.print("Opção: ");
            int opcao = in.nextInt();
    
            switch (opcao) 
            {
                case 1 : 
                    filmes.listarFilmes(filmes.getFilmes(), filmes.getTotalFilmes(), "Lista de filmes disponíveis:");
                    break;
                case 2 : 
                    filmes.cadastrarFilme();
                    break;
                case 3 : 
                    usuario.configurarPreferencias();
                    break;
                case 4 :
                    Recomendador recomendador = new Recomendador(filmes, usuario);
                    recomendador.recomendarFilmes();
                    break; 
                case 5 : 
                    filmes.listarEstatisticas();
                    break;
                case 6 : 
                    repetidor = false;
                    sair();
                    break;
                default :
                    Biblioteca.exibirMensagemErro("Ops! Opção inválida, tente novamente.");
            }
            System.out.println();
        } while (repetidor);
    }
}
