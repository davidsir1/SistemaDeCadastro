public class Pet {
    String nome, sobrenome;
    String endereco;
    float peso;
    int idade;
    Sexo sexo;
    TipoAnimal tipo;
    String raca;

    final String campoEmBranco = "NÃO INFORMADO"; // Constante parar ser utilizando em campos nulos

    // Metodos

    public void salvarPet(String[] dados){
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
        String nome_completo[] = dados[0].split("\\s");
        this.nome = nome_completo[0];
        this.sobrenome = nome_completo[1];

        // Tipo do animal e sexo
        this.tipo = (dados[1].matches("[Cc]achorro")) ? TipoAnimal.CACHORRO : TipoAnimal.GATO;
        this.sexo = (dados[2].matches("[Mm]acho")) ? Sexo.MACHO : Sexo.FEMEA;

        // Endereço
        this.endereco = dados[3];

        // idade e peso
        this.idade = Integer.parseInt(dados[4]);
        this.peso = Float.valueOf(dados[5]);

        // Raça
        this.raca = dados[6];
    }

    public void exibirDados(){
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
