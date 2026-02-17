import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Menu {
    final static String caminho_formulario = "data\\forms\\formulario.txt";

    public static int menuInicial(){
        Scanner leitura = new Scanner(System.in);

        System.out.println("1. Cadastrar novo pet");
        System.out.println("2. Alterar os dados do pet cadastrado");
        System.out.println("3. Deletar um pet cadastrado");
        System.out.println("4. Listar todos os pets cadastrados");
        System.out.println("5. Listar pets por algum criterio (idade, nome, raça)");
        System.out.println("6. Sair");
        System.out.print("-> ");
        if (leitura.hasNextInt())
            return leitura.nextInt();
        else
            return 0;
    }

    public static Pet cadastrarPet(){
        File arquivo_formulario = new File(caminho_formulario);
        String dados[] = new String[7];
        Pet novoPet = new Pet();

        if (!arquivo_formulario.exists()){
            System.out.println("O arquivo não existe!");
            return null;
        }

        try (FileReader fr = new FileReader(caminho_formulario)) {
            BufferedReader br = new BufferedReader(fr);
            Scanner leitura = new Scanner(System.in);

            String linha;
            int perguntaAtual = 0;
            while ((linha = br.readLine()) != null) {
                System.out.println(linha); // Exibe a pergunta do formulario

                if (perguntaAtual == 0) { // Caso de teste na primeira pergunta: nome e sobrenome
                    System.out.print("-> ");
                    dados[perguntaAtual] = leitura.nextLine();

                    // Verifica se o nome e sobrenome possui caracter especial e apenas letras
                    if (!dados[perguntaAtual].matches("^[A-Za-z ]+")) {
                        System.out.println("Apenas letras são permitidas e sem caracteres especiais.");
                        return null;
                    }

                    // Verificar se possui nome e sobrenome
                    String[] partes = dados[perguntaAtual].split("\\s");
                    if (partes.length < 2) {
                        System.out.println("Por favor, inclua nome e sobrenome do pet.");
                        return null;
                    }
                } else if (perguntaAtual == 3) { // Pergunta sobre o endereço
                    System.out.print("i. Digite o número da casa: ");
                    if (leitura.hasNextInt()) {
                        dados[perguntaAtual] = leitura.nextLine() + " ";
                    }
                    System.out.print("ii. Digite a cidade: ");
                    dados[perguntaAtual] += leitura.nextLine() + " ";
                    System.out.print("iii. Digite a rua: ");
                    dados[perguntaAtual] += leitura.nextLine();
                } else if (perguntaAtual == 4) { // Pergunta sobre idade
                    int idade = 0;

                    System.out.print("-> ");
                    if (leitura.hasNextInt()) {
                        idade = leitura.nextInt();
                        leitura.nextLine();
                        if (idade > 21) {
                            System.out.println("Valor inválido, apenas digite valores menores que 20.");
                            return null;
                        } else if (idade < 1) {
                            idade /= 10;
                        }
                    }
                    dados[perguntaAtual] = String.valueOf(idade);
                } else if (perguntaAtual == 5) {
                    double peso = 0.0;

                    System.out.print("-> ");
                    if (leitura.hasNextDouble()) {
                        peso = leitura.nextDouble();
                        leitura.nextLine();
                        if (peso > 61 || peso < 0.5) {
                            System.out.println("Valor inválido, apenas digite valores entre 0.5 e 60 kg.");
                            return null;
                        }
                    }
                    dados[perguntaAtual] = String.valueOf(peso);
                } else if (perguntaAtual == 6){
                    System.out.print("-> ");
                    dados[perguntaAtual] = leitura.nextLine();
                    if (!dados[perguntaAtual].matches("^[A-Za-z]+")){
                        System.out.println("Apenas letras são permitidas e sem caracteres especiais.");
                        return null;
                    }
                } else {
                    System.out.print("-> ");
                    dados[perguntaAtual] = leitura.nextLine();
                }

                perguntaAtual++;
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        novoPet.salvarPet(dados);

        //novoPet.exibirDados();

        return novoPet;
    }
}
