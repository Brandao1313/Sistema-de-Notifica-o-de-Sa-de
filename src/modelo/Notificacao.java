package modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Notificacao {
    private static int contadorId = 1;
    private int id;
    private LocalDate dataNotificacao;
    private Paciente paciente;
    private String tipoAgravo; // HANSENIASE, TUBERCULOSE, MALARIA

    public Notificacao(LocalDate dataNotificacao, Paciente paciente, String tipoAgravo) {
        this.id = contadorId++;
        this.dataNotificacao = dataNotificacao;
        this.paciente = paciente;
        this.tipoAgravo = tipoAgravo;
    }

    public Notificacao(int id, LocalDate dataNotificacao, Paciente paciente, String tipoAgravo) {
        this.id = id;
        this.dataNotificacao = dataNotificacao;
        this.paciente = paciente;
        this.tipoAgravo = tipoAgravo;
        if (id >= contadorId) {
            contadorId = id + 1;
        }
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public LocalDate getDataNotificacao() {
        return dataNotificacao;
    }

    public void setDataNotificacao(LocalDate dataNotificacao) {
        this.dataNotificacao = dataNotificacao;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getTipoAgravo() {
        return tipoAgravo;
    }

    public void setTipoAgravo(String tipoAgravo) {
        this.tipoAgravo = tipoAgravo;
    }

    public int getMes() {
        return dataNotificacao.getMonthValue();
    }

    public int getAno() {
        return dataNotificacao.getYear();
    }

    public abstract String toFileString();
    
    protected String getBaseFileString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return id + "|" + 
               dataNotificacao.format(formatter) + "|" + 
               tipoAgravo + "|" + 
               paciente.toFileString();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "ID: " + id + 
               " | Data: " + dataNotificacao.format(formatter) + 
               " | Agravo: " + tipoAgravo + 
               " | Paciente: " + paciente.getNome() + 
               " | Bairro: " + paciente.getBairro();
    }
}
