import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Menu {

    static final String caminho_formulario = "data\\forms\\formulario.txt";
    static final String campoEmBranco = "NÃO INFORMADO"; // Constante parar ser utilizando em campos nulos

    public static int menuInicial() {
        Scanner leitura = new Scanner(System.in);

        System.out.println("1. Cadastrar novo pet");
        System.out.println("2. Alterar os dados do pet cadastrado");
        System.out.println("3. Deletar um pet cadastrado");
        System.out.println("4. Listar todos os pets cadastrados");
        System.out.println("5. Listar pets por algum criterio (idade, nome, raça)");
        System.out.println("6. Sair");
        System.out.print("-> ");
        if (leitura.hasNextInt()) return leitura.nextInt();
        else return 0;
    }

    public static Pet cadastrarPet() {
        File arquivo_formulario = new File(caminho_formulario);
        String dados[] = new String[7];
        Pet novoPet = new Pet();

        if (!arquivo_formulario.exists()) {
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

                switch (perguntaAtual) {
                    case 0: // Pergunta sobre nome e sobrenome
                        System.out.print("-> ");
                        dados[perguntaAtual] = leitura.nextLine();

                        // Verifica se o usuário não informou nome ou sobrenome ou
                        // Verifica se o nome e sobrenome possui caracter especial e apenas letras
                        if (dados[perguntaAtual].isBlank()) {
                            dados[perguntaAtual] = campoEmBranco;
                        } else if (!dados[perguntaAtual].matches("^[A-Za-z ]+")) {
                            System.out.println(
                                    "Apenas letras são permitidas e sem caracteres especiais."
                            );
                            return null;
                        }

                        // Verificar se possui nome e sobrenome
                        String[] partes = dados[perguntaAtual].split("\\s");
                        if (partes.length == 1) {
                            System.out.println("Por favor, inclua nome e sobrenome do pet.");
                            return null;
                        }

                        break;
                    case 3: // Pergunta sobre o endereço
                        System.out.print("i. Digite o número da casa: ");
                        String numeroCasa = leitura.nextLine();

                        if (!numeroCasa.isBlank()){
                            if (numeroCasa.matches("^[0-9]+")){
                                dados[perguntaAtual] = numeroCasa + ",";
                            }
                        } else {
                            dados[perguntaAtual] = campoEmBranco + ",";
                        }

                        System.out.print("ii. Digite a cidade: ");
                        dados[perguntaAtual] += leitura.nextLine() + ",";

                        System.out.print("iii. Digite a rua: ");
                        dados[perguntaAtual] += leitura.nextLine();

                        break;
                    case 4: // Pergunta sobre idade
                        System.out.print("-> ");
                        String idade = leitura.nextLine();

                        if (!idade.isBlank()){
                            if (idade.matches("^[0-9]+") && Integer.parseInt(idade) < 21){
                                dados[perguntaAtual] = idade;
                            } else {
                                System.out.println("Valor inválido. Digite apenas números ou valores menor ou igual a 20.");
                                return null;
                            }
                        } else {
                            dados[perguntaAtual] = campoEmBranco;
                        }

                        break;
                    case 5: // Pergunta sobre peso
                        System.out.print("-> ");
                        String peso = leitura.nextLine();

                        if (!peso.isBlank()){
                            if (peso.matches("^[0-9]*\\.?[0-9]+") &&
                                    (Double.parseDouble(peso) < 60.0 && Double.parseDouble(peso) > 0.5)){
                                dados[perguntaAtual] = peso.replace(',', '.');
                            } else {
                                System.out.println("Valor inválido. Digite apenas números ou valores entre 0.5 e 60 kg.");
                                return null;
                            }
                        } else {
                            dados[perguntaAtual] = campoEmBranco;
                        }

                        break;
                    case 6: // Pergunta sobre raça
                        System.out.print("-> ");
                        dados[perguntaAtual] = leitura.nextLine();

                        if (!dados[perguntaAtual].isBlank()){
                            if (!dados[perguntaAtual].matches("^[A-Za-z]+")) {
                                System.out.println(
                                        "Apenas letras são permitidas e sem caracteres especiais."
                                );
                                return null;
                            }
                        } else {
                            dados[perguntaAtual] = campoEmBranco;
                        }

                        break;
                    default:
                        System.out.print("-> ");
                        dados[perguntaAtual] = leitura.nextLine();
                        break;
                }

                perguntaAtual++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        novoPet.salvarPet(dados);

        novoPet.exibirDados();

        return novoPet;
    }
}
