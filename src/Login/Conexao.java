package Login;

import java.sql.Connection;
//gera conexão com o banco
import java.sql.DriverManager;
//driver de conexão
import java.sql.ResultSet;
//exibe os resultados do banco de dados
import java.sql.SQLException;
//geração de comandos contra exceções
import java.sql.Statement;
//interpretação dos comandos CRUD

public class Conexao {
    public Connection con = null;
    public Statement stmt = null;
    public ResultSet resultset = null;
    
    private final String servidor = "jdbc:mysql://127.0.0.1:3306/bd_login";
//    servidor do banco de dados
     
    private final String usuario = "root";
//    usuário do banco de dados
    private final String senha = "";
//    senha do banco de dados
    private final String driver = "com.mysql.cj.jdbc.Driver";
//    driver de conexão

    public Connection abrirConexao(){
        try{
            Class.forName(driver); //driver de utilização
            // Atributos de conexão abaixo
            con = DriverManager.getConnection(servidor, usuario, senha);
            stmt = con.createStatement(); // Criando a conexão com o banco de dados
            // Exibindo uma mensagem de acerto na linha abaixo
            System.out.println("Conexão com o banco realizada com sucesso");
        } catch (ClassNotFoundException | SQLException e){
            //mensagem de erro caso a conexão falhe
            System.out.println("Conexão não realizada com o banco. Verifique " + e.getMessage());
        }
        return con; // Retorna a conexão
    }
    
    public void fecharConexao(){
        try{
            con.close(); // Conexão é fechada
            // Mensagem abaixo confirma a desconexão
            System.out.println("Conexão finalizada com sucesso!");
        } catch (SQLException e){
            // Mensagem de erro ao encerrar a conexão
            System.out.println("Conexão não pôde ser encerrada! Verifique: " + e.getMessage());
        }
    }
}

