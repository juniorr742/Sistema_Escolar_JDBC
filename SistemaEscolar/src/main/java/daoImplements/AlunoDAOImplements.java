package daoImplements;

import dao.IAlunoDAO;
import database.sqlConn;
import model.Aluno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAOImplements implements IAlunoDAO {
    @Override
    public void salvarAluno(Aluno aluno) {

    }

    @Override
    public List<Aluno> listarTodosAlunos() {
        String sql = "SELECT * FROM aluno ORDER BY nome ASC";
        List<Aluno> alunos = new ArrayList<>();

        try (Connection conn = sqlConn.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                alunos.add(new Aluno(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("email"),
                        rs.getDate("data_nascimento").toLocalDate(),
                        rs.getString("telefone")
                ));
            }
        }catch (SQLException e){
            System.err.println("Erro ao listar os alunos" + e.getMessage());
        }
        return alunos;
    }

    @Override
    public void atualizarAluno(Aluno aluno) {

    }

    @Override
    public void deletarAluno(int id) {

    }

    @Override
    public Aluno buscarPorId(int id){
        String sql = "SELECT * FROM aluno WHERE id = ?";

        try (Connection conn = sqlConn.getConnection())
        {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                Aluno aluno = new Aluno(rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("email"),
                        rs.getDate("data_nascimento").toLocalDate(),
                        rs.getString("telefone"));
                return aluno;
            }

        }catch (SQLException e){
            System.out.println("Erro ao conectar com banco");
        }
        throw new RuntimeException("Erro ao localizar o usuário");
    }
}
