public class SistemaDeCadastro {
    public static void main(String args[]){
        while (true){
            int opt;
            opt = Menu.menuInicial();

            switch (opt){
                case 1:
                    Menu.cadastrarPet();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Valor inválido. Digite os valores de 1 até 6.");
                    break;
            }
        }
    }
}
