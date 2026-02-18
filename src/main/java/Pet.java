public class Pet {

    String nome, sobrenome;
    String endereco;
    double peso;
    int idade;
    Sexo sexo;
    TipoAnimal tipo;
    String raca;

    // Metodos

    public void salvarPet(String[] dados) {
        /*
        Ordem das informações:
        Nome e Sobrenome,
        Tipo,
        Sexo,
        Endereço e Bairro,
        Idade,
        Peso,
        Raça
         */

        // Lidar com nome e sobrenome
        if (dados[0] != Menu.campoEmBranco) {
            String nome_completo[] = dados[0].split("\\s");
            this.nome = nome_completo[0];
            this.sobrenome = nome_completo[1];
        } else {
            this.nome = Menu.campoEmBranco;
            this.sobrenome = Menu.campoEmBranco;
        }

        // Tipo do animal e sexo
        this.tipo = (dados[1].matches("[Cc]achorro")) ? TipoAnimal.CACHORRO : TipoAnimal.GATO;
        this.sexo = (dados[2].matches("[Mm]acho")) ? Sexo.MACHO : Sexo.FEMEA;

        // Endereço
        this.endereco = dados[3];

        // idade e peso
        if (dados[4] != Menu.campoEmBranco) {
            this.idade = Integer.parseInt(dados[4]);
        } else {
            this.idade = 0;
        }

        if (dados[5] != Menu.campoEmBranco){
            this.peso = Double.valueOf(dados[5]);
        } else {
            this.peso = 0.0;
        }

        // Raça
        if (dados[6] != Menu.campoEmBranco) {
            this.raca = dados[6];
        } else {
            this.raca = Menu.campoEmBranco;
        }
    }

    public void exibirDados() {
        System.out.println("Nome: " + this.nome);
        System.out.println("Sobrenome: " + this.sobrenome);
        System.out.println("Tipo Animal: " + this.tipo);
        System.out.println("Sexo: " + this.sexo);
        System.out.println("Endereço: " + this.endereco);
        System.out.println("Idade: " + this.idade);
        System.out.println("Peso: " + this.peso);
        System.out.println("Raça: " + this.raca);
    }
}
