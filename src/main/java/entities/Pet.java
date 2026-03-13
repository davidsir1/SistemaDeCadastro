package entities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class Pet {
    // Atributos
    String nome;
    String sobrenome;
    String endereco;
    String peso;
    String idade;
    PetSexo sexo;
    PetTipo tipoAnimal;
    String raca;

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
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

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
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

        String dataAtual = LocalDate.now().toString().replace("-", "");
        String horaAtual = String.valueOf(LocalTime.now().getHour()) +
                String.valueOf(LocalTime.now().getMinute());
        String nome_completo = this.getNome().toUpperCase() + 
                this.getSobrenome().toUpperCase();
        String arquivoNome = dataAtual+"T"+horaAtual+nome_completo+".txt";

        File arquivo = new File("data\\petsCadastrados\\"+arquivoNome);

        try (FileWriter fr = new FileWriter(arquivo)){
            fr.write("1 - " + this.nome + " " + this.sobrenome + "\n");
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
        System.out.println("Sobrenome: " + this.sobrenome);
        System.out.println("Tipo Animal: " + this.tipoAnimal);
        System.out.println("Sexo: " + this.sexo);
        System.out.println("Endereço: " + this.endereco);
        System.out.println("Idade: " + this.idade);
        System.out.println("Peso: " + this.peso);
        System.out.println("Raça: " + this.raca);
    }
}
