/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Login;

import java.awt.HeadlessException;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

public class TelaInicio extends JFrame{
    private final JPanel tela;
    
    //atributo para validar a exclusão
    private boolean exclusaoValida;
    
    //Atributos de mensagem
    private String mensagemJOption;
    private int mensagemTipo = 0; // 0 no caso seria o ERROR_MESSAGE; e o 1 seria o INFORMATION_MESSAGE
    
    public TelaInicio(){
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Fatec - São Roque");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 200, 426, 212); // parâmetros de altura, largura e tamanho
        
        //Tela
        tela = new JPanel();
        tela.setBackground(SystemColor.red);
        setContentPane(tela);
        tela.setLayout(null);
        
        //Concatenando o label com a variável nome usuario
        JLabel lblUsuario = new JLabel("Bem-vindo, " + Usuario.nomeUsuario);
        lblUsuario.setBounds(24,65, 200, 15);
        tela.add(lblUsuario);
        
        //Adicionando os botões
        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(20, 130,  117, 25);
        tela.add(btnExcluir);
        
        JButton btnAlterar = new JButton("Alterar");
        btnAlterar.setBounds(150, 130,  117, 25);
        tela.add(btnAlterar);
        
        JButton btnSair = new JButton("Sair");
        btnSair.setBounds(280, 130,  117, 25);
        tela.add(btnSair);
        
        btnSair.addActionListener((ActionEvent e) ->{
            try{
                TelaLogin tlLogin = new TelaLogin();
                tlLogin.AbreTela();
                
                dispose(); //Fechar a tela de início e voltar à tela de Login
            }catch (HeadlessException ex){
                System.out.println("Erro ao acessar a página de login: " + ex.getMessage());
            }
        });
        
        btnAlterar.addActionListener((ActionEvent e) -> {
            try{
                TelaAlteracao tlAlterar = new TelaAlteracao();
                tlAlterar.AbreTelaAlteracao();
                
                dispose(); // Fecha a tela de início e joga para a tela de alteração de dados
            }catch (HeadlessException ex){
                System.out.println("Erro ao acessar a página, verifique: " + ex.getMessage());
            }
        });
        
        btnExcluir.addActionListener((ActionEvent e) -> {
            try{
                Object[] options = {"Sim", "Não"}; //Array que recebe as opções do painel
                int opcao = JOptionPane.showOptionDialog(null, Usuario.nomeUsuario + " deverá ser excluído",
                        "Atenção!", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, options, options[0]);
                
                if(opcao == 0){
                    Usuario usu = new Usuario();
                    
                    //Buscando o método para exclusão do usuário
                    exclusaoValida = usu.deletaUsuario(Usuario.usuarioSistema);
                    
                    if(exclusaoValida == true){
                        mensagemJOption = "Usuário deletado da base de dados!";
                        mensagemTipo = 1;
                        
                        //Abre-se a tela de login de novo
                        TelaLogin tlLogin = new TelaLogin();
                        tlLogin.AbreTela();
                        
                        dispose(); // Sai da página de início de volta para o login
                    }else{
                        mensagemJOption = "Erro ao deletar usuário";
                        mensagemTipo = 0;
                    }
                }
            }catch(HeadlessException ex){
                System.out.println("Erro ao deletar o usuário: " + ex.getMessage());
            }
        });
    }
    
    //Abertura da tela de início em um método
    public void abreTelaInicio(){
        TelaInicio tlInicio = new TelaInicio();
        tlInicio.setVisible(true);
    }
}
