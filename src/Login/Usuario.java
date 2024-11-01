/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Login;

import java.sql.SQLException; //Tratar exceções do banco de dados

public class Usuario {
    // Criação dos atributos privados da classe
    private String usuario;
    private String nome;
    private String senha;
    
    //Atributos estáticos do sistema
    static String nomeUsuario;
    static String usuarioSistema;
    
    //atributo que armazenará o retorno do banco de dados
    private boolean resultUsuario;
    private boolean resultCadastro;
    private boolean resultDelete;
    private boolean resultEdit;
    
    //Getters e Setters

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
    //Método para verificar autenticidade do usuário
    public boolean verificaUsuario (String usuario, String senha){
        // Fazer instância de conexão com banco de dados
        Conexao banco = new Conexao();
        
        try{
            
           //Abrindo a conexão
           banco.abrirConexao();
           
           //criando o parâmetro de retorno
           banco.stmt = banco.con.createStatement();
           String sql = "SELECT * FROM usuario "
                                                     + " WHERE usuario = '" + usuario
                                                     + "' AND senha = md5('" + senha + "')";
            System.out.println(sql);
           //executando a consulta no banco de dados
           banco.resultset = banco.stmt.executeQuery("SELECT * FROM usuario "
                                                     + " WHERE usuario = '" + usuario
                                                     + "' AND senha = md5('" + senha + "')");
           
           //verificando se há retorno no banco de dados
           if(banco.resultset.next()){
               resultUsuario = true; // caso tenha no banco
               
               //Setters de nomeUsuario e usuarioSistema
               setUsuario(banco.resultset.getString(1));
               setNome(banco.resultset.getString(2));
               
               //Atribuição aos atributos estáticos
               nomeUsuario = getNome();
               usuarioSistema = getUsuario();
           }else{
               resultUsuario = false; // caso não tenha no banco
           }
           
           banco.fecharConexao();
           
        }catch (SQLException e){
            
            System.out.println("Erro ao buscar usuário: " + e.getMessage());
            
        }
        return resultUsuario;
    }
    
    // Método apenas para verificar o usuário
    public boolean verificaUsuario (String usuario){
        // Fazer instância de conexão com banco de dados
        Conexao banco = new Conexao();
        
        try{
            
           //Abrindo a conexão
           banco.abrirConexao();
           
           //criando o parâmetro de retorno
           banco.stmt = banco.con.createStatement();
           
           //executando a consulta no banco de dados
           banco.resultset = banco.stmt.executeQuery("SELECT * FROM usuario "
                                                     + " WHERE usuario = '" + usuario
                                                     + "' AND senha = md5('" + senha + "')");
           
           //verificando se há retorno no banco de dados
           if(banco.resultset.next()){
               resultUsuario = true; // caso tenha no banco
           }else{
               resultUsuario = false; // caso não tenha no banco
           }
           
           banco.fecharConexao();
           
        }catch (SQLException e){
            
            System.out.println("Erro ao buscar usuário: " + e.getMessage());
            
        }
        return resultUsuario;
    }
    
    //Método para validar o cadastro de usuários
    public boolean cadastraUsuario (String nome, String usuario, String senha){
        //Iniciar a conexão com o banco
        Conexao banco = new Conexao();
        
        try{
            banco.abrirConexao();
            
            //criando o parâmetro de retorno
            banco.stmt = banco.con.createStatement();
            
            //executando a consulta no banco de dados
            banco.stmt.execute("INSERT INTO usuario (nome, usuario, senha)"
                                                       + "VALUES ('" + nome + "', '" + usuario + "', md5('"
                                                       + senha + "'))");
            
            resultCadastro = true;
        }catch(SQLException ex){
            System.out.println("Erro ao inserir usuário: " + ex.getMessage());
            resultCadastro = false;
        }
        banco.fecharConexao();
        
        return resultCadastro;
    }
    
    //Método para deletar o usuário da base de dados
    public boolean deletaUsuario (String usuario){
        //Iniciando a conexão
        Conexao banco = new Conexao();
        
        try{
            banco.abrirConexao();
            
            //criando os parâmetros de conexão
            banco.stmt = banco.con.createStatement();
            
            //executando a consulta no banco de dados
            banco.stmt.execute("DELETE FROM usuario WHERE usuario = '" + usuario + "'");
            
            //exclusão bem sucedida
            resultDelete = true;
        }catch(SQLException ec){
            System.out.println("Erro ao deletar o usuário: " + ec.getMessage());
            resultDelete = false;
        }
        banco.fecharConexao();
        
        return resultDelete;
    }
    
    //Método para alterar dados do usuário na base de dados
    public boolean alteraUsuario(String nome, String usuario, String senha){
        //iniciando a conexão
        Conexao banco = new Conexao();
        
        try{
            //Abrindo a conexão
            banco.abrirConexao();
            
            //Definindo os parâmetros
            banco.stmt = banco.con.createStatement();
            
            //Realizando a conexão com o banco de dados
            banco.stmt.execute("UPDATE usuario SET nome = '"
                                                    + nome + "', senha = md5('" + senha
                                                    + "') WHERE usuario = '" + usuario
                                                    + "'");
            //Alteração feita com sucesso
            resultEdit = true;
        }catch(SQLException ea){
            //Erro na alteração de dados
            System.out.println("Erro ao alterar dados: " + ea.getMessage());
            resultEdit = false;
        }
        banco.fecharConexao();
        
        return resultEdit;
    }
}
