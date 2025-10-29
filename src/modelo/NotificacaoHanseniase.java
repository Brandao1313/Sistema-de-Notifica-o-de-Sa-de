package modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NotificacaoHanseniase extends Notificacao {
    private String formaClinica; // Indeterminada, Tuberculoide, Dimorfa, Virchowiana
    private String classificacaoOperacional; // Paucibacilar, Multibacilar
    private int numeroLesoes;
    private String baciloscopia; // Positiva, Negativa, Não realizada
    private String grauIncapacidadeFisica; // Grau 0, Grau 1, Grau 2

    public NotificacaoHanseniase(LocalDate dataNotificacao, Paciente paciente, 
                                 String formaClinica, String classificacaoOperacional,
                                 int numeroLesoes, String baciloscopia, 
                                 String grauIncapacidadeFisica) {
        super(dataNotificacao, paciente, "HANSENIASE");
        this.formaClinica = formaClinica;
        this.classificacaoOperacional = classificacaoOperacional;
        this.numeroLesoes = numeroLesoes;
        this.baciloscopia = baciloscopia;
        this.grauIncapacidadeFisica = grauIncapacidadeFisica;
    }

    public NotificacaoHanseniase(int id, LocalDate dataNotificacao, Paciente paciente, 
                                 String formaClinica, String classificacaoOperacional,
                                 int numeroLesoes, String baciloscopia, 
                                 String grauIncapacidadeFisica) {
        super(id, dataNotificacao, paciente, "HANSENIASE");
        this.formaClinica = formaClinica;
        this.classificacaoOperacional = classificacaoOperacional;
        this.numeroLesoes = numeroLesoes;
        this.baciloscopia = baciloscopia;
        this.grauIncapacidadeFisica = grauIncapacidadeFisica;
    }

    // Getters e Setters
    public String getFormaClinica() {
        return formaClinica;
    }

    public void setFormaClinica(String formaClinica) {
        this.formaClinica = formaClinica;
    }

    public String getClassificacaoOperacional() {
        return classificacaoOperacional;
    }

    public void setClassificacaoOperacional(String classificacaoOperacional) {
        this.classificacaoOperacional = classificacaoOperacional;
    }

    public int getNumeroLesoes() {
        return numeroLesoes;
    }

    public void setNumeroLesoes(int numeroLesoes) {
        this.numeroLesoes = numeroLesoes;
    }

    public String getBaciloscopia() {
        return baciloscopia;
    }

    public void setBaciloscopia(String baciloscopia) {
        this.baciloscopia = baciloscopia;
    }

    public String getGrauIncapacidadeFisica() {
        return grauIncapacidadeFisica;
    }

    public void setGrauIncapacidadeFisica(String grauIncapacidadeFisica) {
        this.grauIncapacidadeFisica = grauIncapacidadeFisica;
    }

    @Override
    public String toFileString() {
        return getBaseFileString() + "|" + 
               formaClinica + "|" + 
               classificacaoOperacional + "|" + 
               numeroLesoes + "|" + 
               baciloscopia + "|" + 
               grauIncapacidadeFisica;
    }

    public static NotificacaoHanseniase fromFileString(String linha) {
        String[] dados = linha.split("\\|");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        Paciente paciente = new Paciente(
            dados[3], // nome
            LocalDate.parse(dados[4], formatter), // dataNascimento
            Integer.parseInt(dados[5]), // idade
            dados[6], // sexo
            dados[7], // racaCor
            dados[8], // escolaridade
            dados[9], // endereco
            dados[10], // bairro
            dados[11], // municipio
            dados[12] // telefone
        );

        return new NotificacaoHanseniase(
            Integer.parseInt(dados[0]), // id
            LocalDate.parse(dados[1], formatter), // dataNotificacao
            paciente,
            dados[13], // formaClinica
            dados[14], // classificacaoOperacional
            Integer.parseInt(dados[15]), // numeroLesoes
            dados[16], // baciloscopia
            dados[17] // grauIncapacidadeFisica
        );
    }

    @Override
    public String toString() {
        return super.toString() + 
               " | Forma Clínica: " + formaClinica + 
               " | Classificação: " + classificacaoOperacional;
    }
}
