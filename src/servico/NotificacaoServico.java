package servico;

import modelo.*;
import persistencia.ArquivoNotificacoes;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class NotificacaoServico {
    private ArquivoNotificacoes arquivo;

    public NotificacaoServico() {
        this.arquivo = new ArquivoNotificacoes();
    }

    // Registrar notificações
    public void registrarNotificacaoHanseniase(NotificacaoHanseniase notificacao) throws IOException {
        arquivo.salvarNotificacaoHanseniase(notificacao);
    }

    public void registrarNotificacaoTuberculose(NotificacaoTuberculose notificacao) throws IOException {
        arquivo.salvarNotificacaoTuberculose(notificacao);
    }

    public void registrarNotificacaoMalaria(NotificacaoMalaria notificacao) throws IOException {
        arquivo.salvarNotificacaoMalaria(notificacao);
    }

    // Consultas
    public List<Notificacao> consultarPorNomePaciente(String nome) throws IOException {
        List<Notificacao> todasNotificacoes = arquivo.carregarTodasNotificacoes();
        return todasNotificacoes.stream()
                .filter(n -> n.getPaciente().getNome().toLowerCase().contains(nome.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Notificacao> consultarPorBairro(String bairro) throws IOException {
        List<Notificacao> todasNotificacoes = arquivo.carregarTodasNotificacoes();
        return todasNotificacoes.stream()
                .filter(n -> n.getPaciente().getBairro().equalsIgnoreCase(bairro))
                .collect(Collectors.toList());
    }

    public List<Notificacao> consultarPorPeriodo(LocalDate dataInicio, LocalDate dataFim) throws IOException {
        List<Notificacao> todasNotificacoes = arquivo.carregarTodasNotificacoes();
        return todasNotificacoes.stream()
                .filter(n -> !n.getDataNotificacao().isBefore(dataInicio) && 
                            !n.getDataNotificacao().isAfter(dataFim))
                .collect(Collectors.toList());
    }

    public List<Notificacao> consultarPorAgravo(String tipoAgravo) throws IOException {
        List<Notificacao> todasNotificacoes = arquivo.carregarTodasNotificacoes();
        return todasNotificacoes.stream()
                .filter(n -> n.getTipoAgravo().equalsIgnoreCase(tipoAgravo))
                .collect(Collectors.toList());
    }

    public List<Notificacao> listarTodasNotificacoes() throws IOException {
        return arquivo.carregarTodasNotificacoes();
    }
}
