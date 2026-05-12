package daoImplements;

import dao.IAlunoDAO;
import database.sqlConn;
import model.Aluno;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAOImplements implements IAlunoDAO {
    @Override
    public void salvarAluno(Aluno aluno) {
        String sql = "INSERT INTO aluno (nome, cpf, email, data_nascimento, telefone) VALUES (?, ? , ? , ?, ?)";

        try (Connection conn = sqlConn.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql);

                    stmt.setString(1, aluno.getNome());
                    stmt.setString(2, aluno.getCpf());
                    stmt.setString(3, aluno.getEmail());
                    stmt.setDate(4, Date.valueOf(aluno.getData_nascimento()));
                    stmt.setString(5, aluno.getTelefone());

            int linhas = stmt.executeUpdate();

            if (linhas > 0){
                System.out.println("Aluno cadastrado com sucesso");
            }

        }catch (SQLException e){
            System.err.println("Erro ao salvar aluno.");
        }
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
        String sql = "UPDATE aluno SET nome = ?, cpf = ?, email = ?, data_nascimento = ?, telefone = ? WHERE id = ?";
        try (Connection conn = sqlConn.getConnection()){
            PreparedStatement stmt =conn.prepareStatement(sql);
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getCpf());
            stmt.setString(3, aluno.getEmail());
            stmt.setDate(4, Date.valueOf(aluno.getData_nascimento()));
            stmt.setString(5, aluno.getTelefone());
            stmt.setInt(6, aluno.getId());
            stmt.executeUpdate();
            System.out.println("Objeto atualizado. ");
        }catch (SQLException e){
            System.out.println("Erro ao atualizar" + e.getMessage());
        }
    }

    @Override
    public void deletarAluno(int id) {
        String sql = "DELETE FROM aluno WHERE id = ?";

        try (Connection conn = sqlConn.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, id);
            int linhas = stmt.executeUpdate();

            if (linhas > 0){
                System.out.println("Aluno deletado com sucesso");
            }

        }catch (SQLException e){
            System.err.println("Erro ao deletar aluno");
        }
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
