/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Login;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import static Login.Usuario.usuarioSistema;

public class TelaAlteracao extends JFrame {

    private final JPanel tela;
    private final JTextField txtNome;
    private final JPasswordField pasAtual;
    private final JPasswordField pasSenha;
    private final JPasswordField confirmSenha;

    private boolean atualizacaoValida;

    public TelaAlteracao() {

        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Fatec - São Roque");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 200, 426, 330); // parâmetros de altura, largura e tamanho

        tela = new JPanel();
        tela.setBackground(SystemColor.gray);
        setContentPane(tela);
        tela.setLayout(null);

        //JLabels
        JLabel lblIdentificacao = new JLabel("Informe os campos para alteração");
        lblIdentificacao.setBounds(60, 0, 500, 39);
        lblIdentificacao.setFont(new Font("Arial", 3, 19));
        tela.add(lblIdentificacao);

        JLabel lblNome = new JLabel("Nome: ");
        lblNome.setBounds(24, 50, 100, 15);
        tela.add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(135, 50, 218, 20);
        tela.add(txtNome);
        txtNome.setColumns(10);

        JLabel lblSenha = new JLabel("Senha Atual: ");
        lblSenha.setBounds(24, 90, 110, 15);
        tela.add(lblSenha);

        pasAtual = new JPasswordField();
        pasAtual.setBounds(135, 90, 219, 19);
        tela.add(pasAtual);

        JLabel lblSenhaNova = new JLabel("Nova Senha: ");
        lblSenhaNova.setBounds(24, 130, 110, 15);
        tela.add(lblSenhaNova);

        pasSenha = new JPasswordField();
        pasSenha.setBounds(135, 130, 218, 20);
        tela.add(pasSenha);

        JLabel lblConfirmaSenha = new JLabel("Confirmar Senha: ");
        lblConfirmaSenha.setBounds(24, 170, 130, 15);
        tela.add(lblConfirmaSenha);

        confirmSenha = new JPasswordField();
        confirmSenha.setBounds(135, 170, 218, 20);
        tela.add(confirmSenha);

        JButton btnAlterar = new JButton("Alterar");
        btnAlterar.setBounds(230, 220, 117, 25);
        tela.add(btnAlterar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(80, 220, 117, 25);
        tela.add(btnCancelar);

        btnCancelar.addActionListener((ActionEvent e) -> {
            TelaInicio tlInicio = new TelaInicio();
            tlInicio.abreTelaInicio();

            dispose(); //A tela é fechada e redirecionada para a Tela de Início
        });

        btnAlterar.addActionListener((ActionEvent e) -> {
            try {
                //Instancio a classe usuario
                Usuario usu = new Usuario();

                //Validações antes de efetivar a alteração
                usu.setSenha(confirmSenha.getText());
                usu.setUsuario(usuarioSistema);

                if ("".equals(usu.getNome())) {

                    JOptionPane.showMessageDialog(null, "O campo nome precisa ser informado!", "Atenção",
                            JOptionPane.ERROR_MESSAGE);
                    txtNome.grabFocus(); //Volta o cursor ao campo de nome

                } else if ("".equals(usu.getSenha())) {

                    JOptionPane.showMessageDialog(null, "O campo senha precisa ser informado!", "Atenção",
                            JOptionPane.ERROR_MESSAGE);
                    pasAtual.grabFocus(); //Volta o cursor ao campo de senha

                } else if (usu.verificaUsuario(usuarioSistema, pasAtual.getText()) == false) {

                    JOptionPane.showMessageDialog(null, "Senha inválida! Verifique!", "Atenção",
                            JOptionPane.ERROR_MESSAGE);
                    pasAtual.grabFocus();

                } else if (!pasSenha.getText().equals(confirmSenha.getText())) {

                    JOptionPane.showMessageDialog(null, "Senhas não batem! Tente Novamente!", "Atenção",
                            JOptionPane.ERROR_MESSAGE);
                    pasSenha.grabFocus();

                } else {
                    atualizacaoValida = usu.alteraUsuario(txtNome.getText(), usu.getUsuario(), usu.getSenha());

                    if (atualizacaoValida == true) {
                        JOptionPane.showMessageDialog(null, "Dados do usuário alterados! Voltamos à tela de login",
                                "Atenção!", JOptionPane.INFORMATION_MESSAGE);

                        //Voltando para o login
                        TelaLogin tlLogin = new TelaLogin();
                        tlLogin.AbreTela();
                        dispose(); //Fecha a tela de alteração e volta ao login
                    } else {
                        JOptionPane.showMessageDialog(null, "Problemas ao alterar dados", "Atenção",
                                JOptionPane.ERROR_MESSAGE);
                    }

                }
            } catch (HeadlessException ex) {
                System.out.println("Erro ao alterar dados: " + ex.getMessage());
            }
        });
        
        // Atribuir o atributo global ao objeto
        txtNome.setText(Usuario.nomeUsuario);
    }

    public void AbreTelaAlteracao() {
        TelaAlteracao tlAlteracao = new TelaAlteracao();
        tlAlteracao.setVisible(true); //Abre a tela de alteração
    }
}
