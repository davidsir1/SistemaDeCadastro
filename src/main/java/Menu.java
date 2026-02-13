import java.util.Scanner;

public class Menu {
    public static int menuInicial(){
        Scanner leitura = new Scanner(System.in);

        System.out.println("1. Cadastrar novo pet");
        System.out.println("2. Alterar os dados do pet cadastrado");
        System.out.println("3. Deletar um pet cadastrado");
        System.out.println("4. Listar todos os pets cadastrados");
        System.out.println("5. Listar pets por algum criterio (idade, nome, raça");
        System.out.println("6. Sair");
        System.out.print("-> ");
        if (leitura.hasNextInt())
            return leitura.nextInt();
        else
            return 0;
    }
}
