package servico;

import modelo.Notificacao;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RelatorioServico {
    private NotificacaoServico notificacaoServico;

    public RelatorioServico() {
        this.notificacaoServico = new NotificacaoServico();
    }

    public Map<String, Integer> totalPorAgravo() throws IOException {
        List<Notificacao> notificacoes = notificacaoServico.listarTodasNotificacoes();
        Map<String, Integer> relatorio = new HashMap<>();
        
        for (Notificacao n : notificacoes) {
            String agravo = n.getTipoAgravo();
            relatorio.put(agravo, relatorio.getOrDefault(agravo, 0) + 1);
        }
        
        return relatorio;
    }

    public Map<String, Integer> totalPorBairro() throws IOException {
        List<Notificacao> notificacoes = notificacaoServico.listarTodasNotificacoes();
        Map<String, Integer> relatorio = new HashMap<>();
        
        for (Notificacao n : notificacoes) {
            String bairro = n.getPaciente().getBairro();
            relatorio.put(bairro, relatorio.getOrDefault(bairro, 0) + 1);
        }
        
        return relatorio;
    }

    public Map<String, Integer> totalPorMesAno() throws IOException {
        List<Notificacao> notificacoes = notificacaoServico.listarTodasNotificacoes();
        Map<String, Integer> relatorio = new HashMap<>();
        
        for (Notificacao n : notificacoes) {
            String mesAno = String.format("%02d/%d", n.getMes(), n.getAno());
            relatorio.put(mesAno, relatorio.getOrDefault(mesAno, 0) + 1);
        }
        
        return relatorio;
    }

    public Map<String, Integer> totalPorFaixaEtaria() throws IOException {
        List<Notificacao> notificacoes = notificacaoServico.listarTodasNotificacoes();
        Map<String, Integer> relatorio = new HashMap<>();
        
        for (Notificacao n : notificacoes) {
            String faixaEtaria = n.getPaciente().getFaixaEtaria();
            relatorio.put(faixaEtaria, relatorio.getOrDefault(faixaEtaria, 0) + 1);
        }
        
        return relatorio;
    }

    public Map<String, Integer> totalPorSexo() throws IOException {
        List<Notificacao> notificacoes = notificacaoServico.listarTodasNotificacoes();
        Map<String, Integer> relatorio = new HashMap<>();
        
        for (Notificacao n : notificacoes) {
            String sexo = n.getPaciente().getSexo();
            relatorio.put(sexo, relatorio.getOrDefault(sexo, 0) + 1);
        }
        
        return relatorio;
    }

    public Map<String, Integer> totalPorRacaCor() throws IOException {
        List<Notificacao> notificacoes = notificacaoServico.listarTodasNotificacoes();
        Map<String, Integer> relatorio = new HashMap<>();
        
        for (Notificacao n : notificacoes) {
            String racaCor = n.getPaciente().getRacaCor();
            relatorio.put(racaCor, relatorio.getOrDefault(racaCor, 0) + 1);
        }
        
        return relatorio;
    }

    public Map<String, Integer> totalPorEscolaridade() throws IOException {
        List<Notificacao> notificacoes = notificacaoServico.listarTodasNotificacoes();
        Map<String, Integer> relatorio = new HashMap<>();
        
        for (Notificacao n : notificacoes) {
            String escolaridade = n.getPaciente().getEscolaridade();
            relatorio.put(escolaridade, relatorio.getOrDefault(escolaridade, 0) + 1);
        }
        
        return relatorio;
    }

    public void imprimirRelatorioCompleto() throws IOException {
        System.out.println("\n========================================");
        System.out.println("     RELATÓRIO DE NOTIFICAÇÕES");
        System.out.println("========================================\n");

        // Total por agravo
        System.out.println("--- TOTAL POR AGRAVO ---");
        Map<String, Integer> porAgravo = totalPorAgravo();
        for (Map.Entry<String, Integer> entry : porAgravo.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // Total por bairro
        System.out.println("\n--- TOTAL POR BAIRRO ---");
        Map<String, Integer> porBairro = totalPorBairro();
        for (Map.Entry<String, Integer> entry : porBairro.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // Total por mês/ano
        System.out.println("\n--- TOTAL POR MÊS/ANO ---");
        Map<String, Integer> porMesAno = totalPorMesAno();
        for (Map.Entry<String, Integer> entry : porMesAno.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // Total por faixa etária
        System.out.println("\n--- TOTAL POR FAIXA ETÁRIA ---");
        Map<String, Integer> porFaixaEtaria = totalPorFaixaEtaria();
        for (Map.Entry<String, Integer> entry : porFaixaEtaria.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // Total por sexo
        System.out.println("\n--- TOTAL POR SEXO ---");
        Map<String, Integer> porSexo = totalPorSexo();
        for (Map.Entry<String, Integer> entry : porSexo.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // Total por raça/cor
        System.out.println("\n--- TOTAL POR RAÇA/COR ---");
        Map<String, Integer> porRacaCor = totalPorRacaCor();
        for (Map.Entry<String, Integer> entry : porRacaCor.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // Total por escolaridade
        System.out.println("\n--- TOTAL POR ESCOLARIDADE ---");
        Map<String, Integer> porEscolaridade = totalPorEscolaridade();
        for (Map.Entry<String, Integer> entry : porEscolaridade.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("\n========================================\n");
    }
}
