public class Menu 
{

// ATRIBUTOS
    public enum Topico 
    {
        // ENUM utilizado no menu
        LISTAR_FILMES(1, "Listar todos os filmes cadastrados"), 
        INCLUIR_FILME(2, "Incluir um novo filme no cadastro"), 
        CONFIG_PREFS(3, "Configurar as preferências do usuário"), 
        RECOMENDAR_FILMES(4, "Recomendar filmes"), 
        MOSTRAR_ESTATS(5, "Mostrar estatísticas"), 
        SAIR(6, "Sair do sistema");
    
        private int numero;
        private String descricao;
    
        private Topico(int numero, String descricao) 
        {
            this.numero = numero;
            this.descricao = descricao;
        }
    }

// MÉTODOS

    // exibe o menu, com número e descrição de cada tópico
    public void exibirMenu() 
    {
        System.out.println("Escolha uma opção digitando seu respectivo número:");

        for (Topico topico : Topico.values()) 
        {
            System.out.println("\t" + topico.numero + " - " + topico.descricao + ".");
        }
    }
}
