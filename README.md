# Sistema de Notificação de Agravos de Saúde Pública

Sistema desenvolvido em Java para registro, consulta e geração de relatórios de notificações de agravos de saúde pública.

## Agravos Contemplados

- **Hanseníase**
- **Tuberculose**
- **Malária**

## Funcionalidades

### 1. Registrar Notificações
- Registro completo de dados do paciente
- Registro de dados clínicos específicos de cada agravo
- Persistência automática em arquivos de texto

### 2. Consultar Notificações
- Consulta por nome do paciente
- Consulta por bairro
- Consulta por período (data inicial e final)
- Consulta por tipo de agravo
- Listar todas as notificações

### 3. Gerar Relatórios
- Total de notificações por agravo
- Total de notificações por bairro
- Total de notificações por mês/ano
- Total de notificações por faixa etária
- Total de notificações por sexo
- Total de notificações por raça/cor
- Total de notificações por escolaridade
- Relatório completo com todos os dados

## Estrutura do Projeto

```
src/
├── Main.java                           # Classe principal
├── modelo/                             # Pacote de modelos de dados
│   ├── Paciente.java                   # Classe Paciente
│   ├── Notificacao.java                # Classe abstrata Notificacao
│   ├── NotificacaoHanseniase.java      # Notificação específica
│   ├── NotificacaoTuberculose.java     # Notificação específica
│   └── NotificacaoMalaria.java         # Notificação específica
├── persistencia/                       # Pacote de persistência
│   └── ArquivoNotificacoes.java        # Gerenciamento de arquivos
├── servico/                            # Pacote de serviços
│   ├── NotificacaoServico.java         # Serviços de notificação
│   └── RelatorioServico.java           # Serviços de relatório
└── ui/                                 # Pacote de interface
    └── MenuCLI.java                    # Interface de linha de comando

dados/                                  # Diretório de armazenamento
├── notificacoes_hanseniase.txt         # Dados de Hanseníase
├── notificacoes_tuberculose.txt        # Dados de Tuberculose
└── notificacoes_malaria.txt            # Dados de Malária
```

## Como Compilar e Executar

### Pré-requisitos
- Java JDK 11 ou superior instalado

### Compilação

No diretório raiz do projeto, execute:

```bash
# Compilar todos os arquivos .java
javac -d bin src/**/*.java src/*.java
```

Ou compile individualmente:

```bash
# Criar diretório para classes compiladas
mkdir -p bin

# Compilar
javac -d bin src/modelo/*.java
javac -d bin -cp bin src/persistencia/*.java
javac -d bin -cp bin src/servico/*.java
javac -d bin -cp bin src/ui/*.java
javac -d bin -cp bin src/Main.java
```

### Execução

```bash
# Executar o sistema
java -cp bin Main
```

## Como Usar o Sistema

1. **Ao iniciar**, será exibido o menu principal com as opções:
   - [1] Registrar Notificação
   - [2] Consultar Notificações
   - [3] Gerar Relatórios
   - [0] Sair

2. **Para registrar uma notificação**:
   - Escolha a opção 1
   - Selecione o tipo de agravo (Hanseníase, Tuberculose ou Malária)
   - Preencha os dados solicitados do paciente
   - Preencha os dados clínicos específicos do agravo

3. **Para consultar notificações**:
   - Escolha a opção 2
   - Selecione o tipo de consulta desejada
   - Informe os critérios de busca

4. **Para gerar relatórios**:
   - Escolha a opção 3
   - Selecione o tipo de relatório desejado

## Formato de Dados

### Datas
Todas as datas devem ser informadas no formato: `dd/MM/yyyy`
Exemplo: `26/10/2025`

### Sexo
- M (Masculino)
- F (Feminino)

### Raça/Cor
- Branca
- Preta
- Amarela
- Parda
- Indígena

## Armazenamento de Dados

Os dados são armazenados em arquivos de texto separados por tipo de agravo no diretório `dados/`:
- `notificacoes_hanseniase.txt`
- `notificacoes_tuberculose.txt`
- `notificacoes_malaria.txt`

Os arquivos são criados automaticamente quando a primeira notificação é registrada.

## Conceitos de POO Aplicados

- **Encapsulamento**: Atributos privados com getters e setters
- **Herança**: Classes de notificação herdam de Notificacao
- **Polimorfismo**: Método abstrato toFileString() implementado pelas subclasses
- **Abstração**: Classe abstrata Notificacao
- **Composição**: Paciente composto dentro de Notificacao
- **Separação de responsabilidades**: Pacotes distintos (modelo, servico, persistencia, ui)

## Tratamento de Erros

O sistema inclui tratamento de exceções para:
- Erros de entrada de dados
- Erros de leitura/escrita de arquivos
- Erros de formato de data
- Erros de conversão de tipos

## Autor

Sistema desenvolvido para a disciplina de Programação Orientada a Objetos.
