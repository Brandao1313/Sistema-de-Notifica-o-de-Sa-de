package ui;

import modelo.*;
import servico.NotificacaoServico;
import servico.RelatorioServico;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class MenuCLI {
    private Scanner scanner;
    private NotificacaoServico notificacaoServico;
    private RelatorioServico relatorioServico;
    private DateTimeFormatter dateFormatter;

    public MenuCLI() {
        this.scanner = new Scanner(System.in);
        this.notificacaoServico = new NotificacaoServico();
        this.relatorioServico = new RelatorioServico();
        this.dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    }

    public void exibir() {
        int opcao;
        do {
            exibirMenuPrincipal();
            opcao = lerOpcao();
            processarOpcao(opcao);
        } while (opcao != 0);
    }

    private void exibirMenuPrincipal() {
        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("║   SISTEMA DE NOTIFICAÇÃO DE AGRAVOS DE SAÚDE PÚBLICA  ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
        System.out.println("\n[1] Registrar Notificação");
        System.out.println("[2] Consultar Notificações");
        System.out.println("[3] Gerar Relatórios");
        System.out.println("[0] Sair");
        System.out.print("\nEscolha uma opção: ");
    }

    private int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                menuRegistrarNotificacao();
                break;
            case 2:
                menuConsultarNotificacoes();
                break;
            case 3:
                menuRelatorios();
                break;
            case 0:
                System.out.println("\nEncerrando o sistema. Até logo!");
                break;
            default:
                System.out.println("\n❌ Opção inválida! Tente novamente.");
        }
    }

    private void menuRegistrarNotificacao() {
        System.out.println("\n--- REGISTRAR NOTIFICAÇÃO ---");
        System.out.println("[1] Hanseníase");
        System.out.println("[2] Tuberculose");
        System.out.println("[3] Malária");
        System.out.println("[0] Voltar");
        System.out.print("\nEscolha o tipo de agravo: ");
        
        int opcao = lerOpcao();
        
        switch (opcao) {
            case 1:
                registrarHanseniase();
                break;
            case 2:
                registrarTuberculose();
                break;
            case 3:
                registrarMalaria();
                break;
            case 0:
                break;
            default:
                System.out.println("\n❌ Opção inválida!");
        }
    }

    private Paciente lerDadosPaciente() {
        System.out.println("\n=== DADOS DO PACIENTE ===");
        
        // Nome completo - validação
        String nome = lerNomeCompleto();
        
        // Data de nascimento - validação de formato e data válida
        LocalDate dataNascimento = lerDataNascimento();
        
        // Idade - calculada automaticamente a partir da data de nascimento
        int idade = calcularIdade(dataNascimento);
        System.out.println("Idade calculada: " + idade + " anos");
        
        // Sexo - validação estrita
        String sexo = lerSexo();
        
        // Raça/Cor - validação das opções
        String racaCor = lerRacaCor();
        
        // Escolaridade
        String escolaridade = lerEscolaridade();
        
        // Endereço - validação não vazio
        String endereco = lerCampoObrigatorio("Endereço");
        
        // Bairro - validação não vazio
        String bairro = lerCampoObrigatorio("Bairro");
        
        // Município - validação não vazio
        String municipio = lerCampoObrigatorio("Município");
        
        // Telefone
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine().trim();
        
        return new Paciente(nome, dataNascimento, idade, sexo, racaCor, 
                           escolaridade, endereco, bairro, municipio, telefone);
    }
    
    private String lerNomeCompleto() {
        while (true) {
            System.out.print("Nome completo: ");
            String nome = scanner.nextLine().trim();
            
            if (nome.isEmpty()) {
                System.out.println("❌ Nome não pode estar vazio!");
                continue;
            }
            
            if (nome.length() < 3) {
                System.out.println("❌ Nome deve ter pelo menos 3 caracteres!");
                continue;
            }
            
            if (!nome.matches("[a-zA-ZÀ-ÿ\\s]+")) {
                System.out.println("❌ Nome deve conter apenas letras!");
                continue;
            }
            
            return nome;
        }
    }
    
    private LocalDate lerDataNascimento() {
        while (true) {
            System.out.print("Data de nascimento (dd/MM/yyyy): ");
            try {
                String dataStr = scanner.nextLine().trim();
                LocalDate data = LocalDate.parse(dataStr, dateFormatter);
                
                // Validar se a data não é futura
                if (data.isAfter(LocalDate.now())) {
                    System.out.println("❌ Data de nascimento não pode ser futura!");
                    continue;
                }
                
                // Validar se a pessoa não é muito velha (> 120 anos)
                if (data.isBefore(LocalDate.now().minusYears(120))) {
                    System.out.println("❌ Data de nascimento inválida (pessoa muito velha)!");
                    continue;
                }
                
                return data;
            } catch (DateTimeParseException e) {
                System.out.println("❌ Data inválida! Use o formato dd/MM/yyyy");
            }
        }
    }
    
    private int calcularIdade(LocalDate dataNascimento) {
        return java.time.Period.between(dataNascimento, LocalDate.now()).getYears();
    }
    
    private String lerSexo() {
        while (true) {
            System.out.print("Sexo (M/F): ");
            String sexo = scanner.nextLine().trim().toUpperCase();
            
            if (sexo.equals("M") || sexo.equals("F")) {
                return sexo;
            }
            
            System.out.println("❌ Sexo inválido! Digite apenas M ou F.");
        }
    }
    
    private String lerRacaCor() {
        String[] opcoes = {"Branca", "Preta", "Amarela", "Parda", "Indígena"};
        
        while (true) {
            System.out.print("Raça/Cor (Branca/Preta/Amarela/Parda/Indígena): ");
            String racaCor = scanner.nextLine().trim();
            
            // Verificar se a entrada corresponde a uma das opções (case-insensitive)
            for (String opcao : opcoes) {
                if (racaCor.equalsIgnoreCase(opcao)) {
                    return opcao; // Retorna com a capitalização correta
                }
            }
            
            System.out.println("❌ Raça/Cor inválida! Opções: Branca, Preta, Amarela, Parda, Indígena");
        }
    }
    
    private String lerEscolaridade() {
        String[] opcoes = {
            "Analfabeto",
            "Fundamental incompleto",
            "Fundamental completo",
            "Médio incompleto",
            "Médio completo",
            "Superior incompleto",
            "Superior completo",
            "Não se aplica"
        };
        
        System.out.println("Escolaridade:");
        for (int i = 0; i < opcoes.length; i++) {
            System.out.println("[" + (i + 1) + "] " + opcoes[i]);
        }
        
        while (true) {
            System.out.print("Escolha uma opção (1-" + opcoes.length + "): ");
            try {
                int opcao = Integer.parseInt(scanner.nextLine().trim());
                if (opcao >= 1 && opcao <= opcoes.length) {
                    return opcoes[opcao - 1];
                }
                System.out.println("❌ Opção inválida!");
            } catch (NumberFormatException e) {
                System.out.println("❌ Digite um número válido!");
            }
        }
    }
    
    private String lerCampoObrigatorio(String nomeCampo) {
        while (true) {
            System.out.print(nomeCampo + ": ");
            String valor = scanner.nextLine().trim();
            
            if (!valor.isEmpty()) {
                return valor;
            }
            
            System.out.println("❌ " + nomeCampo + " não pode estar vazio!");
        }
    }

    private void registrarHanseniase() {
        try {
            System.out.println("\n=== NOTIFICAÇÃO DE HANSENÍASE ===");
            
            System.out.print("Data da notificação (dd/MM/yyyy): ");
            LocalDate dataNotificacao = lerDataNotificacao();
            
            Paciente paciente = lerDadosPaciente();
            
            System.out.println("\n=== DADOS CLÍNICOS ===");
            System.out.print("Forma clínica (Indeterminada/Tuberculoide/Dimorfa/Virchowiana): ");
            String formaClinica = lerOpcaoValidada(
                "Forma clínica",
                new String[]{"Indeterminada", "Tuberculoide", "Dimorfa", "Virchowiana"}
            );
            
            System.out.print("Classificação operacional (Paucibacilar/Multibacilar): ");
            String classificacao = lerOpcaoValidada(
                "Classificação operacional",
                new String[]{"Paucibacilar", "Multibacilar"}
            );
            
            System.out.print("Número de lesões: ");
            int numeroLesoes = lerNumeroPositivo("Número de lesões");
            
            System.out.print("Baciloscopia (Positiva/Negativa/Não realizada): ");
            String baciloscopia = lerOpcaoValidada(
                "Baciloscopia",
                new String[]{"Positiva", "Negativa", "Não realizada"}
            );
            
            System.out.print("Grau de incapacidade física (Grau 0/Grau 1/Grau 2): ");
            String grauIncapacidade = lerOpcaoValidada(
                "Grau de incapacidade",
                new String[]{"Grau 0", "Grau 1", "Grau 2"}
            );
            
            NotificacaoHanseniase notificacao = new NotificacaoHanseniase(
                dataNotificacao, paciente, formaClinica, classificacao,
                numeroLesoes, baciloscopia, grauIncapacidade
            );
            
            notificacaoServico.registrarNotificacaoHanseniase(notificacao);
            System.out.println("\n✅ Notificação de Hanseníase registrada com sucesso!");
            
        } catch (Exception e) {
            System.out.println("\n❌ Erro ao registrar notificação: " + e.getMessage());
        }
    }

    private void registrarTuberculose() {
        try {
            System.out.println("\n=== NOTIFICAÇÃO DE TUBERCULOSE ===");
            
            System.out.print("Data da notificação (dd/MM/yyyy): ");
            LocalDate dataNotificacao = lerDataNotificacao();
            
            Paciente paciente = lerDadosPaciente();
            
            System.out.println("\n=== DADOS CLÍNICOS ===");
            System.out.print("Forma clínica (Pulmonar/Extrapulmonar/Pulmonar + Extrapulmonar): ");
            String formaClinica = lerOpcaoValidada(
                "Forma clínica",
                new String[]{"Pulmonar", "Extrapulmonar", "Pulmonar + Extrapulmonar"}
            );
            
            System.out.print("Tipo de entrada (Caso novo/Recidiva/Reingresso após abandono/Transferência): ");
            String tipoEntrada = lerOpcaoValidada(
                "Tipo de entrada",
                new String[]{"Caso novo", "Recidiva", "Reingresso após abandono", "Transferência"}
            );
            
            System.out.print("Baciloscopia direta (Positiva/Negativa/Não realizada): ");
            String baciloscopia = lerOpcaoValidada(
                "Baciloscopia",
                new String[]{"Positiva", "Negativa", "Não realizada"}
            );
            
            System.out.print("Raio-X de tórax (Suspeito/Normal/Outra patologia): ");
            String raioX = lerOpcaoValidada(
                "Raio-X",
                new String[]{"Suspeito", "Normal", "Outra patologia"}
            );
            
            System.out.print("Teste tuberculínico (Reator/Não reator/Não realizado): ");
            String testeTuberculinico = lerOpcaoValidada(
                "Teste tuberculínico",
                new String[]{"Reator", "Não reator", "Não realizado"}
            );
            
            System.out.print("Teste molecular (Detectável/Não detectável/Não realizado): ");
            String testeMolecular = lerOpcaoValidada(
                "Teste molecular",
                new String[]{"Detectável", "Não detectável", "Não realizado"}
            );
            
            NotificacaoTuberculose notificacao = new NotificacaoTuberculose(
                dataNotificacao, paciente, formaClinica, tipoEntrada,
                baciloscopia, raioX, testeTuberculinico, testeMolecular
            );
            
            notificacaoServico.registrarNotificacaoTuberculose(notificacao);
            System.out.println("\n✅ Notificação de Tuberculose registrada com sucesso!");
            
        } catch (Exception e) {
            System.out.println("\n❌ Erro ao registrar notificação: " + e.getMessage());
        }
    }

    private void registrarMalaria() {
        try {
            System.out.println("\n=== NOTIFICAÇÃO DE MALÁRIA ===");
            
            System.out.print("Data da notificação (dd/MM/yyyy): ");
            LocalDate dataNotificacao = lerDataNotificacao();
            
            Paciente paciente = lerDadosPaciente();
            
            System.out.println("\n=== DADOS CLÍNICOS ===");
            System.out.print("Data dos primeiros sintomas (dd/MM/yyyy): ");
            LocalDate dataSintomas = lerDataSintomas(dataNotificacao);
            
            System.out.print("Tipo de lâmina (LVC - Verificação de Cura / LD - Diagnóstico): ");
            String tipoLamina = lerOpcaoValidada(
                "Tipo de lâmina",
                new String[]{"LVC", "LD"}
            );
            
            System.out.print("Resultado do exame (F - Falciparum / V - Vivax / F+V / Negativo): ");
            String resultado = lerOpcaoValidada(
                "Resultado",
                new String[]{"F", "V", "F+V", "Negativo"}
            );
            
            System.out.print("Parasitemia (+/++/+++/++++): ");
            String parasitemia = lerOpcaoValidada(
                "Parasitemia",
                new String[]{"+", "++", "+++", "++++"}
            );
            
            System.out.print("Gestante (S/N): ");
            boolean gestante = lerSimNao();
            
            System.out.print("Tratamento (esquema utilizado): ");
            String tratamento = lerCampoObrigatorio("Tratamento");
            
            NotificacaoMalaria notificacao = new NotificacaoMalaria(
                dataNotificacao, paciente, dataSintomas, tipoLamina,
                resultado, parasitemia, gestante, tratamento
            );
            
            notificacaoServico.registrarNotificacaoMalaria(notificacao);
            System.out.println("\n✅ Notificação de Malária registrada com sucesso!");
            
        } catch (Exception e) {
            System.out.println("\n❌ Erro ao registrar notificação: " + e.getMessage());
        }
    }

    private void menuConsultarNotificacoes() {
        System.out.println("\n--- CONSULTAR NOTIFICAÇÕES ---");
        System.out.println("[1] Por nome do paciente");
        System.out.println("[2] Por bairro");
        System.out.println("[3] Por período");
        System.out.println("[4] Por tipo de agravo");
        System.out.println("[5] Listar todas");
        System.out.println("[0] Voltar");
        System.out.print("\nEscolha uma opção: ");
        
        int opcao = lerOpcao();
        
        try {
            List<Notificacao> resultado = null;
            
            switch (opcao) {
                case 1:
                    System.out.print("\nDigite o nome do paciente: ");
                    String nome = scanner.nextLine();
                    resultado = notificacaoServico.consultarPorNomePaciente(nome);
                    break;
                case 2:
                    System.out.print("\nDigite o nome do bairro: ");
                    String bairro = scanner.nextLine();
                    resultado = notificacaoServico.consultarPorBairro(bairro);
                    break;
                case 3:
                    System.out.print("\nData inicial (dd/MM/yyyy): ");
                    LocalDate dataInicio = lerData();
                    System.out.print("Data final (dd/MM/yyyy): ");
                    LocalDate dataFim = lerData();
                    resultado = notificacaoServico.consultarPorPeriodo(dataInicio, dataFim);
                    break;
                case 4:
                    System.out.print("\nTipo de agravo (HANSENIASE/TUBERCULOSE/MALARIA): ");
                    String agravo = scanner.nextLine().toUpperCase();
                    resultado = notificacaoServico.consultarPorAgravo(agravo);
                    break;
                case 5:
                    resultado = notificacaoServico.listarTodasNotificacoes();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("\n❌ Opção inválida!");
                    return;
            }
            
            exibirResultadoConsulta(resultado);
            
        } catch (Exception e) {
            System.out.println("\n❌ Erro ao consultar: " + e.getMessage());
        }
    }

    private void exibirResultadoConsulta(List<Notificacao> notificacoes) {
        if (notificacoes == null || notificacoes.isEmpty()) {
            System.out.println("\n⚠️  Nenhuma notificação encontrada.");
            return;
        }
        
        System.out.println("\n=== RESULTADO DA CONSULTA ===");
        System.out.println("Total de notificações encontradas: " + notificacoes.size());
        System.out.println();
        
        for (Notificacao n : notificacoes) {
            System.out.println(n);
        }
    }

    private void menuRelatorios() {
        System.out.println("\n--- GERAR RELATÓRIOS ---");
        System.out.println("[1] Total por agravo");
        System.out.println("[2] Total por bairro");
        System.out.println("[3] Total por mês/ano");
        System.out.println("[4] Total por faixa etária");
        System.out.println("[5] Total por sexo");
        System.out.println("[6] Total por raça/cor");
        System.out.println("[7] Total por escolaridade");
        System.out.println("[8] Relatório completo");
        System.out.println("[0] Voltar");
        System.out.print("\nEscolha uma opção: ");
        
        int opcao = lerOpcao();
        
        try {
            switch (opcao) {
                case 1:
                    imprimirRelatorioSimples("TOTAL POR AGRAVO", relatorioServico.totalPorAgravo());
                    break;
                case 2:
                    imprimirRelatorioSimples("TOTAL POR BAIRRO", relatorioServico.totalPorBairro());
                    break;
                case 3:
                    imprimirRelatorioSimples("TOTAL POR MÊS/ANO", relatorioServico.totalPorMesAno());
                    break;
                case 4:
                    imprimirRelatorioSimples("TOTAL POR FAIXA ETÁRIA", relatorioServico.totalPorFaixaEtaria());
                    break;
                case 5:
                    imprimirRelatorioSimples("TOTAL POR SEXO", relatorioServico.totalPorSexo());
                    break;
                case 6:
                    imprimirRelatorioSimples("TOTAL POR RAÇA/COR", relatorioServico.totalPorRacaCor());
                    break;
                case 7:
                    imprimirRelatorioSimples("TOTAL POR ESCOLARIDADE", relatorioServico.totalPorEscolaridade());
                    break;
                case 8:
                    relatorioServico.imprimirRelatorioCompleto();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("\n❌ Opção inválida!");
            }
        } catch (Exception e) {
            System.out.println("\n❌ Erro ao gerar relatório: " + e.getMessage());
        }
    }

    private void imprimirRelatorioSimples(String titulo, java.util.Map<String, Integer> dados) {
        System.out.println("\n=== " + titulo + " ===");
        if (dados.isEmpty()) {
            System.out.println("⚠️  Nenhum dado encontrado.");
        } else {
            for (java.util.Map.Entry<String, Integer> entry : dados.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
        System.out.println();
    }

    private LocalDate lerData() {
        while (true) {
            try {
                String dataStr = scanner.nextLine();
                return LocalDate.parse(dataStr, dateFormatter);
            } catch (DateTimeParseException e) {
                System.out.print("Data inválida! Use o formato dd/MM/yyyy: ");
            }
        }
    }
    
    private LocalDate lerDataNotificacao() {
        while (true) {
            try {
                String dataStr = scanner.nextLine().trim();
                LocalDate data = LocalDate.parse(dataStr, dateFormatter);
                
                // Validar se a data não é futura
                if (data.isAfter(LocalDate.now())) {
                    System.out.println("❌ Data de notificação não pode ser futura!");
                    System.out.print("Digite novamente (dd/MM/yyyy): ");
                    continue;
                }
                
                // Validar se a data não é muito antiga (> 10 anos)
                if (data.isBefore(LocalDate.now().minusYears(10))) {
                    System.out.println("❌ Data de notificação muito antiga (máximo 10 anos)!");
                    System.out.print("Digite novamente (dd/MM/yyyy): ");
                    continue;
                }
                
                return data;
            } catch (DateTimeParseException e) {
                System.out.print("❌ Data inválida! Use o formato dd/MM/yyyy: ");
            }
        }
    }
    
    private LocalDate lerDataSintomas(LocalDate dataNotificacao) {
        while (true) {
            try {
                String dataStr = scanner.nextLine().trim();
                LocalDate data = LocalDate.parse(dataStr, dateFormatter);
                
                // Validar se a data não é futura
                if (data.isAfter(LocalDate.now())) {
                    System.out.println("❌ Data dos sintomas não pode ser futura!");
                    System.out.print("Digite novamente (dd/MM/yyyy): ");
                    continue;
                }
                
                // Validar se a data dos sintomas não é posterior à data de notificação
                if (data.isAfter(dataNotificacao)) {
                    System.out.println("❌ Data dos sintomas não pode ser posterior à data de notificação!");
                    System.out.print("Digite novamente (dd/MM/yyyy): ");
                    continue;
                }
                
                // Validar se a data não é muito antiga (> 1 ano da notificação)
                if (data.isBefore(dataNotificacao.minusYears(1))) {
                    System.out.println("❌ Data dos sintomas muito antiga (máximo 1 ano antes da notificação)!");
                    System.out.print("Digite novamente (dd/MM/yyyy): ");
                    continue;
                }
                
                return data;
            } catch (DateTimeParseException e) {
                System.out.print("❌ Data inválida! Use o formato dd/MM/yyyy: ");
            }
        }
    }
    
    private String lerOpcaoValidada(String nomeCampo, String[] opcoesValidas) {
        while (true) {
            String entrada = scanner.nextLine().trim();
            
            // Verificar se a entrada corresponde a uma das opções (case-insensitive)
            for (String opcao : opcoesValidas) {
                if (entrada.equalsIgnoreCase(opcao)) {
                    return opcao; // Retorna com a capitalização correta
                }
            }
            
            System.out.println("❌ " + nomeCampo + " inválido! Opções: " + String.join(", ", opcoesValidas));
            System.out.print("Digite novamente: ");
        }
    }
    
    private int lerNumeroPositivo(String nomeCampo) {
        while (true) {
            try {
                int numero = Integer.parseInt(scanner.nextLine().trim());
                if (numero >= 0) {
                    return numero;
                }
                System.out.println("❌ " + nomeCampo + " deve ser um número positivo!");
                System.out.print("Digite novamente: ");
            } catch (NumberFormatException e) {
                System.out.println("❌ Digite um número válido!");
                System.out.print("Digite novamente: ");
            }
        }
    }
    
    private boolean lerSimNao() {
        while (true) {
            String resposta = scanner.nextLine().trim().toUpperCase();
            if (resposta.equals("S") || resposta.equals("SIM")) {
                return true;
            }
            if (resposta.equals("N") || resposta.equals("NAO") || resposta.equals("NÃO")) {
                return false;
            }
            System.out.println("❌ Resposta inválida! Digite S para Sim ou N para Não.");
            System.out.print("Digite novamente: ");
        }
    }

    public void fechar() {
        scanner.close();
    }
}
