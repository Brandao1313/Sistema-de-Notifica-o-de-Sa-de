package modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NotificacaoMalaria extends Notificacao {
    private LocalDate dataPrimeirosSintomas;
    private String tipoLamina; // Lâmina de Verificação de Cura (LVC), Lâmina de Diagnóstico (LD)
    private String resultadoExame; // F (Falciparum), V (Vivax), F+V, Negativo
    private String parasitemia; // + (meia cruz), ++ (uma cruz), +++ (duas cruzes), ++++ (três cruzes)
    private boolean gestante;
    private String tratamento; // Esquema utilizado

    public NotificacaoMalaria(LocalDate dataNotificacao, Paciente paciente,
                              LocalDate dataPrimeirosSintomas, String tipoLamina,
                              String resultadoExame, String parasitemia,
                              boolean gestante, String tratamento) {
        super(dataNotificacao, paciente, "MALARIA");
        this.dataPrimeirosSintomas = dataPrimeirosSintomas;
        this.tipoLamina = tipoLamina;
        this.resultadoExame = resultadoExame;
        this.parasitemia = parasitemia;
        this.gestante = gestante;
        this.tratamento = tratamento;
    }

    public NotificacaoMalaria(int id, LocalDate dataNotificacao, Paciente paciente,
                              LocalDate dataPrimeirosSintomas, String tipoLamina,
                              String resultadoExame, String parasitemia,
                              boolean gestante, String tratamento) {
        super(id, dataNotificacao, paciente, "MALARIA");
        this.dataPrimeirosSintomas = dataPrimeirosSintomas;
        this.tipoLamina = tipoLamina;
        this.resultadoExame = resultadoExame;
        this.parasitemia = parasitemia;
        this.gestante = gestante;
        this.tratamento = tratamento;
    }

    // Getters e Setters
    public LocalDate getDataPrimeirosSintomas() {
        return dataPrimeirosSintomas;
    }

    public void setDataPrimeirosSintomas(LocalDate dataPrimeirosSintomas) {
        this.dataPrimeirosSintomas = dataPrimeirosSintomas;
    }

    public String getTipoLamina() {
        return tipoLamina;
    }

    public void setTipoLamina(String tipoLamina) {
        this.tipoLamina = tipoLamina;
    }

    public String getResultadoExame() {
        return resultadoExame;
    }

    public void setResultadoExame(String resultadoExame) {
        this.resultadoExame = resultadoExame;
    }

    public String getParasitemia() {
        return parasitemia;
    }

    public void setParasitemia(String parasitemia) {
        this.parasitemia = parasitemia;
    }

    public boolean isGestante() {
        return gestante;
    }

    public void setGestante(boolean gestante) {
        this.gestante = gestante;
    }

    public String getTratamento() {
        return tratamento;
    }

    public void setTratamento(String tratamento) {
        this.tratamento = tratamento;
    }

    @Override
    public String toFileString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return getBaseFileString() + "|" + 
               dataPrimeirosSintomas.format(formatter) + "|" + 
               tipoLamina + "|" + 
               resultadoExame + "|" + 
               parasitemia + "|" + 
               gestante + "|" + 
               tratamento;
    }

    public static NotificacaoMalaria fromFileString(String linha) {
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

        return new NotificacaoMalaria(
            Integer.parseInt(dados[0]), // id
            LocalDate.parse(dados[1], formatter), // dataNotificacao
            paciente,
            LocalDate.parse(dados[13], formatter), // dataPrimeirosSintomas
            dados[14], // tipoLamina
            dados[15], // resultadoExame
            dados[16], // parasitemia
            Boolean.parseBoolean(dados[17]), // gestante
            dados[18] // tratamento
        );
    }

    @Override
    public String toString() {
        return super.toString() + 
               " | Resultado: " + resultadoExame + 
               " | Tipo Lâmina: " + tipoLamina;
    }
}
