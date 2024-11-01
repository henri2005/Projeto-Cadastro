/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Login;

/**
 *
 * @author alunofatec
 */
public class TestaConexao {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Conexao c = new Conexao();
        c.abrirConexao();
        
        try{
            Thread.sleep(4000);
            c.fecharConexao();
        } catch(InterruptedException ex){
            // Mensagem de saída em caso de erro
            System.out.println("Houve um problema no teste de conexão." + ex.getMessage());
        }
    }
    
}
