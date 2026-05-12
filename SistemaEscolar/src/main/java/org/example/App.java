package org.example;


import daoImplements.AlunoDAOImplements;
import database.sqlConn;
import model.Aluno;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class App
{
    public static void main( String[] args )
    {
        sqlConn.testConnection();
        AlunoDAOImplements alunoDAOImplements = new AlunoDAOImplements();
        Scanner sc = new Scanner(System.in);
        // Import correto: import java.time.format.DateTimeFormatter;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        int opcao;

        do {
            System.out.println("===MENU===");
            System.out.println("1. Cadastrar Aluno");
            System.out.println("1. Cadastrar Aluno");
            System.out.println("2. Atualizar Aluno");
            System.out.println("3. Excluir Aluno");
            System.out.println("4. Listar Alunos");
            System.out.println("5. Buscar por id");
            System.out.println("0. Sair do programa");

            opcao = sc.nextInt();

            switch (opcao){
                case 1:
                    System.out.println("Cadastrar aluno");

                    System.out.println("Digite o nome: ");
                    String nomec = sc.nextLine();
                    System.out.println("Digite o cpf: ");
                    String cpfc = sc.nextLine();
                    System.out.println("Digite o email: ");
                    String emailc = sc.nextLine();
                    System.out.println("Digite a data de nascimento: ");
                    String data = sc.nextLine();

                    LocalDate dataNascimento = null;
                    try {
                        dataNascimento = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/mm/yyyy"));
                    }catch (Exception e){
                        System.out.println("Formato de data errado");
                    }

                    System.out.println("Digite o telefone: ");
                    String telefonec= sc.nextLine();

                    Aluno alunoc = new Aluno(nomec, cpfc, emailc, dataNascimento, telefonec);
                    alunoDAOImplements.salvarAluno(alunoc);

                case 2:
                    System.out.println("Atualizar dados");

                    System.out.println("Digite um id: ");
                    int id1 = sc.nextInt();
                    Aluno aluno1 = alunoDAOImplements.buscarPorId(id1);

                    System.out.println("Digite o valor do nome: ");
                    sc.nextLine();
                    String nome = sc.nextLine();
                    aluno1.setNome(nome);

                    System.out.println("Digite o valor do cpf: ");

                        String cpf = sc.nextLine();
                        aluno1.setCpf(cpf);

                    System.out.println("Digite o valor do email: ");

                        String email = sc.nextLine();
                        aluno1.setEmail(email);

                        alunoDAOImplements.atualizarAluno(aluno1);
                    break;
                case 3:
                    System.out.println("Excluir Aluno");
                    break;
                case 4:
                    System.out.println("Listar alunos");
                    List<Aluno> todosAlunos = alunoDAOImplements.listarTodosAlunos();

                    if (todosAlunos.isEmpty()){
                        System.out.println("Lista está vazia");
                    }else {
                        for (Aluno al: todosAlunos){
                            System.out.println(al);
                        }
                    }
                    break;
                case 5:
                    System.out.println("Digite o id: ");
                    int id = sc.nextInt();
                    Aluno aluno = alunoDAOImplements.buscarPorId(id);
                    System.out.println(aluno);
                    break;
            }
        }while (opcao != 0);


    }
}
