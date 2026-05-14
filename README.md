Sistema de Gestão Escolar (CLI)
Este é um projeto Java focado em operações de persistência de dados em um banco de dados relacional MySQL. A aplicação utiliza o padrão DAO (Data Access Object) para gerenciar o ciclo de vida de registros de Alunos e Turmas.

🚀 Funcionalidades
O sistema permite realizar as seguintes operações através de uma interface de linha de comando (CLI):

Gestão de Alunos:

Cadastrar novos alunos.

Listar todos os alunos cadastrados (ordenados por nome).

Buscar aluno específico por ID.

Atualizar dados cadastrais (Nome, CPF, Email).

Remover alunos do sistema.

Gestão de Turmas:

Listar todas as turmas cadastradas.

Visualizar todos os alunos matriculados em uma turma específica (utilizando consultas com INNER JOIN).

🛠️ Tecnologias Utilizadas
Java 17+ (ou versão superior).

MySQL: Banco de dados relacional.

JDBC (Java Database Connectivity): Driver para conexão e execução de queries SQL.

Maven: Gerenciador de dependências e build.

📁 Estrutura do Projeto
O projeto segue uma arquitetura em camadas para melhor organização e manutenção:

model: Contém as classes de entidade (Aluno, Turma, Professor).

dao: Interfaces que definem o contrato das operações de banco de dados.

daoImplements: Implementações concretas do JDBC com lógica SQL.

database: Classe sqlConn responsável pela configuração e gerenciamento da conexão com o MySQL.

org.example: Classe App.java contendo o menu principal e a lógica de execução.

📋 Pré-requisitos e Configuração
1. Banco de Dados
Certifique-se de ter um servidor MySQL rodando. Crie um banco de dados chamado sistemaescolar e as tabelas necessárias:

SQL
CREATE DATABASE sistemaescolar;

USE sistemaescolar;

CREATE TABLE aluno (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100),
    cpf VARCHAR(11),
    email VARCHAR(100),
    data_nascimento DATE,
    telefone VARCHAR(15)
);

CREATE TABLE turma (
    id INT PRIMARY KEY AUTO_INCREMENT,
    instituicao_id INT,
    professor_id INT,
    nome VARCHAR(100),
    ano_letivo INT,
    turno VARCHAR(20),
    vagas INT
);

CREATE TABLE matricula (
    aluno_id INT,
    turma_id INT,
    PRIMARY KEY (aluno_id, turma_id),
    FOREIGN KEY (aluno_id) REFERENCES aluno(id),
    FOREIGN KEY (turma_id) REFERENCES turma(id)
);
2. Conexão
No arquivo src/main/java/database/sqlConn.java, ajuste as credenciais de acesso se necessário:

Java
private static final String url = "jdbc:mysql://127.0.0.1:3306/sistemaescolar";
private static final String user = "root";
private static final String password = "SuaSenhaAqui";
⚙️ Como executar
Clone este repositório.

Importe o projeto em sua IDE (IntelliJ, Eclipse, VS Code).

Certifique-se de que a dependência do mysql-connector-j está presente no seu pom.xml.

Execute a classe App.java.

Nota: Ao cadastrar a data de nascimento, utilize o formato ISO (AAAA-MM-DD).
