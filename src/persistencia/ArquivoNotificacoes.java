package persistencia;

import modelo.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArquivoNotificacoes {
    private static final String ARQUIVO_HANSENIASE = "dados/notificacoes_hanseniase.txt";
    private static final String ARQUIVO_TUBERCULOSE = "dados/notificacoes_tuberculose.txt";
    private static final String ARQUIVO_MALARIA = "dados/notificacoes_malaria.txt";

    public ArquivoNotificacoes() {
        criarDiretorioDados();
    }

    private void criarDiretorioDados() {
        File diretorio = new File("dados");
        if (!diretorio.exists()) {
            diretorio.mkdir();
        }
    }

    public void salvarNotificacaoHanseniase(NotificacaoHanseniase notificacao) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_HANSENIASE, true))) {
            writer.write(notificacao.toFileString());
            writer.newLine();
        }
    }

    public void salvarNotificacaoTuberculose(NotificacaoTuberculose notificacao) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_TUBERCULOSE, true))) {
            writer.write(notificacao.toFileString());
            writer.newLine();
        }
    }

    public void salvarNotificacaoMalaria(NotificacaoMalaria notificacao) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_MALARIA, true))) {
            writer.write(notificacao.toFileString());
            writer.newLine();
        }
    }

    public List<NotificacaoHanseniase> carregarNotificacoesHanseniase() throws IOException {
        List<NotificacaoHanseniase> notificacoes = new ArrayList<>();
        File arquivo = new File(ARQUIVO_HANSENIASE);
        
        if (!arquivo.exists()) {
            return notificacoes;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (!linha.trim().isEmpty()) {
                    notificacoes.add(NotificacaoHanseniase.fromFileString(linha));
                }
            }
        }
        return notificacoes;
    }

    public List<NotificacaoTuberculose> carregarNotificacoesTuberculose() throws IOException {
        List<NotificacaoTuberculose> notificacoes = new ArrayList<>();
        File arquivo = new File(ARQUIVO_TUBERCULOSE);
        
        if (!arquivo.exists()) {
            return notificacoes;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (!linha.trim().isEmpty()) {
                    notificacoes.add(NotificacaoTuberculose.fromFileString(linha));
                }
            }
        }
        return notificacoes;
    }

    public List<NotificacaoMalaria> carregarNotificacoesMalaria() throws IOException {
        List<NotificacaoMalaria> notificacoes = new ArrayList<>();
        File arquivo = new File(ARQUIVO_MALARIA);
        
        if (!arquivo.exists()) {
            return notificacoes;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (!linha.trim().isEmpty()) {
                    notificacoes.add(NotificacaoMalaria.fromFileString(linha));
                }
            }
        }
        return notificacoes;
    }

    public List<Notificacao> carregarTodasNotificacoes() throws IOException {
        List<Notificacao> todasNotificacoes = new ArrayList<>();
        todasNotificacoes.addAll(carregarNotificacoesHanseniase());
        todasNotificacoes.addAll(carregarNotificacoesTuberculose());
        todasNotificacoes.addAll(carregarNotificacoesMalaria());
        return todasNotificacoes;
    }
}
