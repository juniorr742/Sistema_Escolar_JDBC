package database;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class sqlConn {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/sistemaescolar";
    private static final String user = "root";
    private static final String password = "Senai@134";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url, user, password);
    }

    public static void testConnection(){
        try (Connection conn = getConnection()){
            System.out.println("Conexão bem sucedida" + conn);
        }catch (SQLException e){
            System.out.println("Erro ao conectar" + e.getMessage());
        }
    }
}
