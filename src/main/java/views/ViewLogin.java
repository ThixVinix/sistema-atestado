package views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ViewLogin {

	public JFrame frame;
	private JPasswordField txtSenha;
	private JTextField txtLogin;
	private JLabel Login_name;

	/**
	 * Create the application.
	 */
	public ViewLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		createWindowLogin();
		createLoginInterface();
		createPasswordInterface();
		createLoginButton();

	}

	private void createWindowLogin() {
		frame = new JFrame();
		frame.setTitle("Login");
		frame.setBounds(100, 100, 546, 373);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	}

	private void createLoginInterface() {

		// JLabel
		Login_name = new JLabel("Login");
		Login_name.setFont(new Font("Tahoma", Font.PLAIN, 19));
		Login_name.setBounds(153, 45, 70, 29);
		frame.getContentPane().add(Login_name);

		// JTextField
		txtLogin = new JTextField();
		txtLogin.setBounds(153, 85, 215, 36);
		frame.getContentPane().add(txtLogin);
		txtLogin.setColumns(10);
	}

	private void createPasswordInterface() {

		// JLabel
		JLabel Senha_name = new JLabel("Senha");
		Senha_name.setFont(new Font("Tahoma", Font.PLAIN, 19));
		Senha_name.setBounds(153, 140, 70, 19);
		frame.getContentPane().add(Senha_name);

		// JPasswordField
		txtSenha = new JPasswordField();
		txtSenha.setBounds(153, 170, 215, 36);
		frame.getContentPane().add(txtSenha);
	}

	private void createLoginButton() {
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(199, 250, 127, 29);
		frame.getContentPane().add(btnEntrar);

		createActionBtnEntrar(btnEntrar);
	}

	private void createActionBtnEntrar(JButton btnEntrar) {
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (checkLogin(txtLogin.getText(), new String(txtSenha.getPassword()))) {
					JOptionPane.showMessageDialog(null, "Bem vindo ao sistema");
					ViewPrimeiraTela primeiraTela = new ViewPrimeiraTela();
					frame.setVisible(false);
					frame.dispose();
					primeiraTela.initialize();

				} else {
					JOptionPane.showMessageDialog(null, "Dados inválidos!", "Entrada não liberada",
							JOptionPane.WARNING_MESSAGE);

				}

			}
		});
	}

	public boolean checkLogin(String login, String senha) {
		return login.equalsIgnoreCase("usuario") && senha.equalsIgnoreCase("123");
	}

}
