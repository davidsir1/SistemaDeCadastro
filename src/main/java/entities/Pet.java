package entities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Pet {
    // Atributos
    String nome;
    String endereco;
    String peso;
    String idade;
    PetSexo sexo;
    PetTipo tipoAnimal;
    String raca;
    
    static public final String NAO_INFORMADO = "NÃO INFORMADO"; // Constante para ser utilizando em campos nulos

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getPeso() {
        return peso;
    }

    public String getIdade() {
        return idade;
    }

    public PetSexo getSexo() {
        return sexo;
    }

    public PetTipo getTipoAnimal() {
        return tipoAnimal;
    }

    public String getRaca() {
        return raca;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public void setSexo(PetSexo sexo) {
        this.sexo = sexo;
    }

    public void setTipoAnimal(PetTipo tipoAnimal) {
        this.tipoAnimal = tipoAnimal;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }
    
    // Metodos
    public void salvarArquivoPet(){
        /*
        Formato do arquivo: ano, mês, dia,T, hora, minuto - NOME+SOBRENOME em maiúsculo.
        Resultado final: 20231101T1234-FLORZINHADASILVA.TXT
         */

        /*
        Informações no arquivo:
        1 - Nome Sobrenome
        2 - Tipo Animal
        3 - Sexo
        4 - Endereço
        5 - Idade
        6 - Peso
        7 - Raça
         */

        LocalDateTime dataHoraAtual = LocalDateTime.now();
        DateTimeFormatter formatar = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
        String dataFormatada = dataHoraAtual.format(formatar);
        String nomeFormatado = this.getNome().replace(" ", "").toUpperCase();
        String arquivoNome = dataFormatada+"-"+nomeFormatado+".txt";

        File arquivo = new File("data\\petsCadastrados\\"+arquivoNome);

        try (FileWriter fr = new FileWriter(arquivo)){
            fr.write("1 - " + this.nome + "\n");
            fr.write("2 - " + this.tipoAnimal + "\n");
            fr.write("3 - " + this.sexo + "\n");
            fr.write("4 - " + this.endereco + "\n");
            fr.write("5 - " + this.idade + "\n");
            fr.write("6 - " + this.peso + "\n");
            fr.write("7 - " + this.raca);

            fr.flush();
        } catch (IOException e){
            e.printStackTrace();
        }

        System.out.println(arquivoNome);
    }

    public void exibirDados() {
        System.out.println("Nome: " + this.nome);
        System.out.println("Tipo Animal: " + this.tipoAnimal);
        System.out.println("Sexo: " + this.sexo);
        System.out.println("Endereço: " + this.endereco);
        System.out.println("Idade: " + this.idade);
        System.out.println("Peso: " + this.peso);
        System.out.println("Raça: " + this.raca);
    }
}
