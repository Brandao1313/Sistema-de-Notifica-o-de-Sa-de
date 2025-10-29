package modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NotificacaoTuberculose extends Notificacao {
    private String formaClinica; // Pulmonar, Extrapulmonar, Pulmonar + Extrapulmonar
    private String tipoEntrada; // Caso novo, Recidiva, Reingresso após abandono, Transferência
    private String baciloscopiaDireta; // Positiva, Negativa, Não realizada
    private String raioXTorax; // Suspeito, Normal, Outra patologia
    private String testeTuberculinico; // Reator, Não reator, Não realizado
    private String testeMolecular; // Detectável, Não detectável, Não realizado

    public NotificacaoTuberculose(LocalDate dataNotificacao, Paciente paciente,
                                  String formaClinica, String tipoEntrada,
                                  String baciloscopiaDireta, String raioXTorax,
                                  String testeTuberculinico, String testeMolecular) {
        super(dataNotificacao, paciente, "TUBERCULOSE");
        this.formaClinica = formaClinica;
        this.tipoEntrada = tipoEntrada;
        this.baciloscopiaDireta = baciloscopiaDireta;
        this.raioXTorax = raioXTorax;
        this.testeTuberculinico = testeTuberculinico;
        this.testeMolecular = testeMolecular;
    }

    public NotificacaoTuberculose(int id, LocalDate dataNotificacao, Paciente paciente,
                                  String formaClinica, String tipoEntrada,
                                  String baciloscopiaDireta, String raioXTorax,
                                  String testeTuberculinico, String testeMolecular) {
        super(id, dataNotificacao, paciente, "TUBERCULOSE");
        this.formaClinica = formaClinica;
        this.tipoEntrada = tipoEntrada;
        this.baciloscopiaDireta = baciloscopiaDireta;
        this.raioXTorax = raioXTorax;
        this.testeTuberculinico = testeTuberculinico;
        this.testeMolecular = testeMolecular;
    }

    // Getters e Setters
    public String getFormaClinica() {
        return formaClinica;
    }

    public void setFormaClinica(String formaClinica) {
        this.formaClinica = formaClinica;
    }

    public String getTipoEntrada() {
        return tipoEntrada;
    }

    public void setTipoEntrada(String tipoEntrada) {
        this.tipoEntrada = tipoEntrada;
    }

    public String getBaciloscopiaDireta() {
        return baciloscopiaDireta;
    }

    public void setBaciloscopiaDireta(String baciloscopiaDireta) {
        this.baciloscopiaDireta = baciloscopiaDireta;
    }

    public String getRaioXTorax() {
        return raioXTorax;
    }

    public void setRaioXTorax(String raioXTorax) {
        this.raioXTorax = raioXTorax;
    }

    public String getTesteTuberculinico() {
        return testeTuberculinico;
    }

    public void setTesteTuberculinico(String testeTuberculinico) {
        this.testeTuberculinico = testeTuberculinico;
    }

    public String getTesteMolecular() {
        return testeMolecular;
    }

    public void setTesteMolecular(String testeMolecular) {
        this.testeMolecular = testeMolecular;
    }

    @Override
    public String toFileString() {
        return getBaseFileString() + "|" + 
               formaClinica + "|" + 
               tipoEntrada + "|" + 
               baciloscopiaDireta + "|" + 
               raioXTorax + "|" + 
               testeTuberculinico + "|" + 
               testeMolecular;
    }

    public static NotificacaoTuberculose fromFileString(String linha) {
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

        return new NotificacaoTuberculose(
            Integer.parseInt(dados[0]), // id
            LocalDate.parse(dados[1], formatter), // dataNotificacao
            paciente,
            dados[13], // formaClinica
            dados[14], // tipoEntrada
            dados[15], // baciloscopiaDireta
            dados[16], // raioXTorax
            dados[17], // testeTuberculinico
            dados[18] // testeMolecular
        );
    }

    @Override
    public String toString() {
        return super.toString() + 
               " | Forma Clínica: " + formaClinica + 
               " | Tipo: " + tipoEntrada;
    }
}
