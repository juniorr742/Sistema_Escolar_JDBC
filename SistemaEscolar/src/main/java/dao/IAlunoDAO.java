package dao;

import model.Aluno;

import java.util.List;

public interface IAlunoDAO {

    void salvarAluno(Aluno aluno);

    List<Aluno> listarTodosAlunos();

    void atualizarAluno(Aluno aluno);

    void deletarAluno(int id);

    Aluno buscarPorId(int id);
}
