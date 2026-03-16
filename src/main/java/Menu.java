import entities.Pet;
import entities.PetSexo;
import entities.PetTipo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Menu {

    static final String caminho_formulario = "data\\forms\\formulario.txt";

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

    public static void cadastrarPet() {
        File arquivo_formulario = new File(caminho_formulario);
        Pet novoPet = new Pet();

        if (!arquivo_formulario.exists()) {
            System.out.println("O arquivo não existe!");
            return;
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
                        String nome_completo = leitura.nextLine();

                        // Verifica se o usuário não informou nome ou sobrenome ou
                        // Verifica se o nome e sobrenome possui caracter especial e apenas letras
                        if (nome_completo.isBlank()) {
                            novoPet.setNome(Pet.NAO_INFORMADO);
                        } else if (!nome_completo.matches("^[A-Za-z ]+")) {
                            System.out.println(
                                    "Apenas letras são permitidas e sem caracteres especiais."
                            );
                            return;
                        }

                        // Verificar se possui nome e sobrenome
                        String[] partes = nome_completo.split("\\s");
                        if (partes.length == 1) {
                            System.out.println("Por gentileza, inclua nome e sobrenome do pet.");
                            return;
                        }
                        
                        novoPet.setNome(nome_completo);

                        break;
                    case 1: // Pergunta tipo do animal (Gato/Cachorro)
                        System.out.println("1. Cachorro | 2. Gato");
                        System.out.print("-> ");
                        int tipo = leitura.nextInt();
                        
                        novoPet.setTipoAnimal(tipo == 1 ? PetTipo.CACHORRO : PetTipo.GATO);
                        break;
                    case 2: // Pergunta sobre o sexo do animal
                        System.out.println("1. Macho | 2. Fêmea");
                        System.out.print("-> ");
                        int sexo = leitura.nextInt();
                        
                        novoPet.setSexo(sexo == 1 ? PetSexo.MACHO : PetSexo.FEMEA);
                        
                        leitura.nextLine(); // Limpa o buffer;
                        break;
                    case 3: // Pergunta sobre o endereço
                        String endereco;
                        System.out.print("i. Digite o número da casa: ");
                        String numeroCasa = leitura.nextLine();

                        if (!numeroCasa.isBlank()){
                            if (!numeroCasa.matches("^[0-9]+")){
                                System.out.println("Digite apenas números positivos.");
                                return;
                            } else {
                                endereco = numeroCasa + ",";
                            }
                        } else {
                            endereco = Pet.NAO_INFORMADO + ",";
                        }

                        System.out.print("ii. Digite a cidade: ");
                        endereco += leitura.nextLine() + ",";

                        System.out.print("iii. Digite a rua: ");
                        endereco += leitura.nextLine();
                        
                        novoPet.setEndereco(endereco);
                        
                        break;
                    case 4: // Pergunta sobre idade
                        System.out.print("-> ");
                        String idade = leitura.nextLine();

                        if (!idade.isBlank()){
                            if (idade.matches("^[0-9]+") && Integer.parseInt(idade) < 21){
                                novoPet.setIdade(idade);
                            } else {
                                System.out.println("Valor inválido. Digite apenas números ou valores menor ou igual a 20.");
                                return;
                            }
                        } else {
                           novoPet.setIdade(Pet.NAO_INFORMADO);
                        }

                        break;
                    case 5: // Pergunta sobre peso
                        System.out.print("-> ");
                        String peso = leitura.nextLine();

                        if (!peso.isBlank()){
                            if (peso.matches("^[0-9]*\\.?[0-9]+") &&
                                    (Double.parseDouble(peso) < 60.0 && Double.parseDouble(peso) > 0.5)){
                                novoPet.setPeso(peso.replace(',', '.'));
                            } else {
                                System.out.println("Valor inválido. Digite apenas números ou valores entre 0.5 e 60 kg.");
                                return;
                            }
                        } else {
                            novoPet.setPeso(Pet.NAO_INFORMADO);
                        }

                        break;
                    case 6: // Pergunta sobre raça
                        System.out.print("-> ");
                        String raca = leitura.nextLine();

                        if (!raca.isBlank()){
                            if (raca.matches("^[A-Za-z]+")) {
                                novoPet.setRaca(raca);
                            } else {
                                System.out.println(
                                        "Apenas letras são permitidas e sem caracteres especiais."
                                );
                                return;
                            }
                        } else {
                            novoPet.setRaca(Pet.NAO_INFORMADO);
                        }

                        break;
                    default:
                        break;
                }

                perguntaAtual++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        novoPet.exibirDados();

        novoPet.salvarArquivoPet();
    }
}
