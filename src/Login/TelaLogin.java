package Login;

//Importando as classes AWT e SWING
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane; // trabalha com mensagens
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public final class TelaLogin extends JFrame {

    // Variável para a tela em si
    private final JPanel panelTela;
    // Variável para o campo de texto do usuário
    private final JTextField nmUsuario;
    // Variável para o campo de senha do usuário
    private final JPasswordField snUsuario;

    //método de validação do usuário
    private boolean usuarioValido;

    //Método construtor
    public TelaLogin() {
        setLocationRelativeTo(null); //Joga a tela para o centro;
        setResizable(false); // Tela não pode ser redimensionada;

        setTitle("Gerenciamento de Usuários - Fatec"); // Definindo o título
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Clicando no X, a tela é fechada

        setBounds(60, 170, 530, 350); // Definindo os parâmetros de posicionamento (x, y, width e height)

        //Criando um objeto JPanel e atribuindo uma variável a ele
        panelTela = new JPanel();

        //Definindo as propriedades desse JPanel
        panelTela.setBackground(SystemColor.LIGHT_GRAY);
        setContentPane(panelTela);

        panelTela.setLayout(null); // Layout deixa de ser o padrão

        // Adicionando objetos na tela com um JLabel
        JLabel identificacao = new JLabel("IDENTIFICAÇÃO DE USUÁRIO");

        identificacao.setBounds(120, 0, 325, 39);

        identificacao.setFont(new Font("Arial", 3, 19));

        //Adicionando o elemento na tela
        panelTela.add(identificacao);

        //Criando os elementos label de campo Usuário
        JLabel c_nome = new JLabel("Usuario: ");
        c_nome.setBounds(80, 92, 70, 15);
        panelTela.add(c_nome);

        //Criando elementos label de campo senha
        JLabel c_senha = new JLabel("Senha: ");
        c_senha.setBounds(80, 135, 70, 15);
        panelTela.add(c_senha);

        //Adicionando os elementos de TextField e PasswordField e definindo suas posições e tamanhos em pixels
        nmUsuario = new JTextField();
        nmUsuario.setBounds(135, 92, 280, 20);
        panelTela.add(nmUsuario);
        nmUsuario.setColumns(10);

        snUsuario = new JPasswordField();
        snUsuario.setBounds(135, 135, 280, 20);
        panelTela.add(snUsuario);

        //Trabalhando com o JButton e os botões Entrar e Cadastrar
        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setBounds(300, 210, 90, 30);
        panelTela.add(btnEntrar);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(130, 210, 110, 30);
        panelTela.add(btnCadastrar);

        //Ação do botão ao entrar no sistema
        btnEntrar.addActionListener((ActionEvent e) -> {
                // instancia da classe usuario
                Usuario u = new Usuario();
                
                //setter do usuario
                u.setUsuario(nmUsuario.getText());
                u.setSenha(snUsuario.getText());
                
                if ("".equals(nmUsuario.getText())) {
                    //Exibir uma mensagem em tela para caso não seja definido um nome de usuário
                    JOptionPane.showMessageDialog(null, "Campo de usuário precisa ser informado!", "Atenção!",
                            JOptionPane.ERROR_MESSAGE);
                    //Voltar o cursor ao campo NmUsuario
                    snUsuario.grabFocus();
                } else if ("".equals(snUsuario.getText())) {
                    JOptionPane.showMessageDialog(null, "Campo de senha precisa ser informado!", "Atenção!",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    //Verifico se o usuário está no banco de dados
                    usuarioValido = u.verificaUsuario(u.getUsuario(), u.getSenha());
                    
                    if (usuarioValido == true) {
                        // Usuário e senha corretos
                        JOptionPane.showMessageDialog(null, "Usuário válido no banco de dados", "Atenção",
                                JOptionPane.INFORMATION_MESSAGE);
                        
                        //Abrir a tela de início
                        TelaInicio tlInicio = new TelaInicio();
                        tlInicio.abreTelaInicio();
                        dispose();
                        
                    }else {
                        // Usuário e senha incorretos
                        JOptionPane.showMessageDialog(null, "Usuário inválido ou inexistente", "Atenção",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
                
                // Mando foco para o campo de usuário
                nmUsuario.grabFocus();
        });

        btnCadastrar.addActionListener((ActionEvent ex) -> {
                TelaCadastro tlCadastro = new TelaCadastro();
                tlCadastro.abrirTelaCadastro();

                dispose(); // fecho a tela de login
        });
    }

    public void AbreTela() {
        TelaLogin tela = new TelaLogin();
        tela.setVisible(true);// O método pega todo o script do método construtor e usa o SetVisible para abrir em tela
    }

    public void limpaTela() {
        nmUsuario.setText(""); // zerando os valores de usuário e senha
        snUsuario.setText("");
    }

}
