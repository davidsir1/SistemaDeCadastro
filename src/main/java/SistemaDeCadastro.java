public class SistemaDeCadastro {
    public void main(){
        while (true){
            int opt;
            opt = Menu.menuInicial();

            Pet pet;

            switch (opt){
                case 1:
                    pet = Menu.cadastrarPet();
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
                    break;
                default:
                    System.out.println("Valor inválido. Digite os valores de 1 até 6.");
                    break;
            }
        }
    }
}
