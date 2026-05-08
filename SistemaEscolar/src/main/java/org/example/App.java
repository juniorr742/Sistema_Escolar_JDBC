package org.example;


import daoImplements.AlunoDAOImplements;
import database.sqlConn;
import model.Aluno;

import java.util.List;
import java.util.Scanner;

public class App
{
    public static void main( String[] args )
    {
        sqlConn.testConnection();
        AlunoDAOImplements alunoDAOImplements = new AlunoDAOImplements();
        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("===MENU===");
            System.out.println("1. Cadastrar Aluno");
            System.out.println("2. Atualizar Aluno");
            System.out.println("3. Excluir Aluno");
            System.out.println("4. Listar Alunos");
            System.out.println("0. Sair do programa");

            opcao = sc.nextInt();

            switch (opcao){
                case 1:
                    System.out.println("Cadastrar aluno");
                    System.out.println("Digite o id: ");
                    int id = sc.nextInt();
                    Aluno aluno = alunoDAOImplements.buscarPorId(id);
                    System.out.println(aluno);
                    break;
                case 2:
                    System.out.println("Atualizar dados");
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
            }
        }while (opcao != 0);


    }
}
