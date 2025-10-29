package modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Paciente {
    private String nome;
    private LocalDate dataNascimento;
    private int idade;
    private String sexo; // M ou F
    private String racaCor; // Branca, Preta, Amarela, Parda, Indígena
    private String escolaridade;
    private String endereco;
    private String bairro;
    private String municipio;
    private String telefone;

    public Paciente(String nome, LocalDate dataNascimento, int idade, String sexo, 
                    String racaCor, String escolaridade, String endereco, 
                    String bairro, String municipio, String telefone) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.idade = idade;
        this.sexo = sexo;
        this.racaCor = racaCor;
        this.escolaridade = escolaridade;
        this.endereco = endereco;
        this.bairro = bairro;
        this.municipio = municipio;
        this.telefone = telefone;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getRacaCor() {
        return racaCor;
    }

    public void setRacaCor(String racaCor) {
        this.racaCor = racaCor;
    }

    public String getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(String escolaridade) {
        this.escolaridade = escolaridade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getFaixaEtaria() {
        if (idade < 1) return "Menor de 1 ano";
        else if (idade <= 4) return "1 a 4 anos";
        else if (idade <= 9) return "5 a 9 anos";
        else if (idade <= 14) return "10 a 14 anos";
        else if (idade <= 19) return "15 a 19 anos";
        else if (idade <= 29) return "20 a 29 anos";
        else if (idade <= 39) return "30 a 39 anos";
        else if (idade <= 49) return "40 a 49 anos";
        else if (idade <= 59) return "50 a 59 anos";
        else if (idade <= 69) return "60 a 69 anos";
        else return "70 anos ou mais";
    }

    public String toFileString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return nome + "|" + 
               dataNascimento.format(formatter) + "|" + 
               idade + "|" + 
               sexo + "|" + 
               racaCor + "|" + 
               escolaridade + "|" + 
               endereco + "|" + 
               bairro + "|" + 
               municipio + "|" + 
               telefone;
    }

    public static Paciente fromFileString(String linha) {
        String[] dados = linha.split("\\|");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return new Paciente(
            dados[0],
            LocalDate.parse(dados[1], formatter),
            Integer.parseInt(dados[2]),
            dados[3],
            dados[4],
            dados[5],
            dados[6],
            dados[7],
            dados[8],
            dados[9]
        );
    }
}
