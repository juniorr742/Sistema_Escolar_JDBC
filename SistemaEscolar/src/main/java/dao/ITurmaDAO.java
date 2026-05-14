package dao;

import model.Aluno;
import model.Professor;

import java.util.List;

public interface IProfessorDAO {

    void salvarAluno(Professor professor);

    List<Professor> listarTodosAlunos();

    void atualizarAluno(Aluno aluno);

    void deletarAluno(int id);
}
