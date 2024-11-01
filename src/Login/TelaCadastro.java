/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Login;

//Importando as classes AWT e SWING
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

public class TelaCadastro extends JFrame{
    // variável para a tela
    private final JPanel tela;
    // variável para o campo de texto;
    private final JTextField nome;
    private final JTextField usuario;
    // variáveis de campo de senha
    private final JPasswordField senha;
    private final JPasswordField confirmarSenha;
    
    //Variáveis para validação do usuário/cadastro
    private boolean usuarioValido;
    private boolean cadastroValido;
    
    //String de mensagem
    private String mensagemJOption;
    private int mensagemTipo = 0; // 0 no caso seria o ERROR_MESSAGE; e o 1 seria o INFORMATION_MESSAGE
    
    public TelaCadastro(){
        setLocationRelativeTo(null); // Deixando a tela no centro
        setResizable (false); // Tela não é redimensionável
        setTitle("Cadastro"); // Nome da tela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Clicar em X fecha a tela
        setBounds(80, 100, 500, 350); // Propriedades de tamanho e posicionamento
        
        //Parâmetros do título
        tela = new JPanel();
        tela.setBackground(SystemColor.ORANGE);
        setContentPane(tela); // A tela e seus atributos serão o conteúdo do painel
        tela.setLayout(null);
        
        //Parâmetros de label
        JLabel cadastro = new JLabel("CADASTRO");
        
        cadastro.setBounds(185, -100, 300, 300);
        cadastro.setFont(new Font("Arial", 3, 22));
        tela.add(cadastro); // Adiciona o titulo cadastro na tela
        
        JLabel cad_nome = new JLabel("Nome: ");
        cad_nome.setBounds(80, 80, 80, 30);
        tela.add(cad_nome);
        
        JLabel cad_usuario = new JLabel("Usuário: ");
        cad_usuario.setBounds(80, 120, 80, 30);
        tela.add(cad_usuario);
        
        JLabel cad_senha = new JLabel("Senha: ");
        cad_senha.setBounds(80, 160, 80, 30);
        tela.add(cad_senha);
        
        JLabel cad_confirm = new JLabel("Confirmar Senha: ");
        cad_confirm.setBounds(80, 200, 80, 30);
        tela.add(cad_confirm);
        
        //Parâmetros para textfield e passwordfield
        nome = new JTextField();
        nome.setBounds(140, 80, 220, 30);
        tela.add(nome);
        nome.setColumns(10);
        
        usuario = new JTextField();
        usuario.setBounds(140, 120, 220, 30);
        tela.add(usuario);
        
        senha = new JPasswordField();
        senha.setBounds(140, 160, 220, 30);
        tela.add(senha);
        
        confirmarSenha = new JPasswordField();
        confirmarSenha.setBounds(140, 200, 220, 30);
        tela.add(confirmarSenha);
        
        //Parâmetros para os botões
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(140, 250, 90, 40);
        tela.add(btnCancelar);
        
        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setBounds(270, 250, 90, 40);
        tela.add(btnEntrar);
        
        //Método para o botão de cancelar
        btnCancelar.addActionListener((ActionEvent e) -> {
            TelaLogin tlLogin = new TelaLogin();
            tlLogin.AbreTela();
            dispose(); // fechar a janela atual
        });
        
        //Método para o botão de cadastrar
        btnEntrar.addActionListener((ActionEvent e) -> {
            try{
                //instanciar a classe Usuario
                Usuario user = new Usuario();
                
                //realizando setters dos dados da tela
                user.setNome(nome.getText());
                user.setUsuario(usuario.getText());
                user.setSenha(senha.getText());
                
                //validação para preenchimento dos dados
                if("".equals(nome.getText())){
                    mensagemJOption = "O campo de nome precisa ser informado!";
                    mensagemTipo = 0;
                }else if("".equals(usuario.getText())){
                    mensagemJOption = "O campo de usuário precisa ser informado!";
                    mensagemTipo = 0;
                }else if("".equals(senha.getText())){
                    mensagemJOption = "O campo de senha precisa ser preenchido!";
                    mensagemTipo = 0;
                }else{
                    usuarioValido = user.verificaUsuario(user.getUsuario()); // em caso dos campos preenchidos, há a validação do usuário no banco
                    
                    if(usuarioValido == true){
                        mensagemJOption = "O usuário já está registrado na base de dados!";
                        mensagemTipo = 0;
                    }else{
                        cadastroValido = user.cadastraUsuario(user.getNome(), user.getUsuario(), user.getSenha());
                        
                        if(cadastroValido == true){
                            //Usuario cadastrado na base de dados
                            mensagemJOption = "Usuário cadastrado no banco de dados";
                            mensagemTipo = 1;
                        }else{
                            mensagemJOption = "Erro ao inserir usuário!";
                            mensagemTipo = 0;
                        }
                    }
                }
                
                //Painel de mensagens
                JOptionPane.showMessageDialog(null, mensagemJOption, "Atenção", mensagemTipo);
                if(mensagemTipo == 1){ // se a mensagem for 1, tudo está certo, e voltamos à tela de login
                    TelaLogin tlLogin = new TelaLogin();
                    tlLogin.AbreTela();
                    dispose();
                }
            }catch(HeadlessException ex){
                System.out.println("Erro no cadastro de usuário: " + ex.getMessage());
            }
        });
    }
    
    public void abrirTelaCadastro(){
        TelaCadastro ab_tela = new TelaCadastro();
        ab_tela.setVisible(true); // ab_tela pega todas as propriedades de construção do método construtor e abre em tela
    }
}
