package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import bean.CadastrarEmpresaBean;
import enumeracoes.TipoCrudEnum;
import model.Empresa;
import util.Validacoes;

public class ViewCadastrarEmpresas {

	private JFrame frmCadastrarEmpresa;
	private JTextField textFieldNomeEmpresa;
	private TipoCrudEnum tipoCrudEnum;
	private Empresa empresa = new Empresa();
	private static String mensagem;
	private static boolean mensagemInvalida;
	private static boolean createSucess;

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws ParseException
	 * 
	 * @wbp.parser.entryPoint
	 */
	public void initialize(JFrame frameAnterior) throws ParseException {
		frameAnterior.setVisible(false);
		frmCadastrarEmpresa = new JFrame();
		frmCadastrarEmpresa.setTitle("Cadastrar Empresa");
		frmCadastrarEmpresa.setBounds(100, 100, 670, 500);
		frmCadastrarEmpresa.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmCadastrarEmpresa.setLocationRelativeTo(null);
		frmCadastrarEmpresa.setResizable(false);
		frmCadastrarEmpresa.getContentPane().setLayout(null);

		JLabel lblNomeEmpresa = new JLabel("Nome:");
		lblNomeEmpresa.setBounds(10, 77, 46, 14);
		frmCadastrarEmpresa.getContentPane().add(lblNomeEmpresa);

		textFieldNomeEmpresa = new JTextField();
		textFieldNomeEmpresa.setBounds(10, 102, 221, 30);
		frmCadastrarEmpresa.getContentPane().add(textFieldNomeEmpresa);
		textFieldNomeEmpresa.setColumns(10);

		JLabel lblCNPJ = new JLabel("CNPJ:");
		lblCNPJ.setBounds(10, 239, 46, 14);
		frmCadastrarEmpresa.getContentPane().add(lblCNPJ);

		JFormattedTextField formattedTextFieldCNPJ;

		formattedTextFieldCNPJ = new JFormattedTextField(new MaskFormatter("##.###.###/####-##"));
		formattedTextFieldCNPJ.setFocusLostBehavior(JFormattedTextField.COMMIT);
		formattedTextFieldCNPJ.setBounds(10, 264, 221, 30);
		frmCadastrarEmpresa.getContentPane().add(formattedTextFieldCNPJ);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(124, 392, 141, 30);
		frmCadastrarEmpresa.getContentPane().add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Cadastro cancelado...");
				frmCadastrarEmpresa.dispose();
				frameAnterior.setVisible(true);
			}
		});

		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(303, 392, 141, 30);
		frmCadastrarEmpresa.getContentPane().add(btnConfirmar);
		btnConfirmar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setMensagemInvalida(false);
				empresa.getNome();

				String nome = Validacoes.removeExceededWhiteSpace(textFieldNomeEmpresa.getText());
				empresa.setNome(nome);
				String cnpj = Validacoes.removeWhiteSpace(Validacoes.removeMaskCNPJ(((JTextField) formattedTextFieldCNPJ).getText()));
				empresa.setCnpj(cnpj);

				tipoCrudEnum = TipoCrudEnum.ADICIONAR;
				CadastrarEmpresaBean createEmpresa = new CadastrarEmpresaBean();
				createEmpresa.validarCampos(empresa, tipoCrudEnum);
				if (isMensagemInvalida()) {
					JOptionPane.showMessageDialog(null, getMensagem(),"Erro de preenchimento", JOptionPane.WARNING_MESSAGE, null);
					return;
				}

				if (isCreateSucess()) {
					JOptionPane.showMessageDialog(null, "Empresa salva com sucesso!", "Cadastro concluído", JOptionPane.INFORMATION_MESSAGE, null);
				} else {
					JOptionPane.showMessageDialog(null, "Não foi possível adicionar a Empresa.", "Erro", JOptionPane.ERROR, null);
					return;
				}
				empresa = new Empresa();
				frmCadastrarEmpresa.dispose();
				frameAnterior.setVisible(true);
			}
		});

		frmCadastrarEmpresa.getContentPane().add(btnConfirmar);
		frmCadastrarEmpresa.setVisible(true);
	}

	public static String verificarMensagemInvalida(boolean invalido, String mensagem) {

		if (invalido) {
			setMensagem(mensagem);
			setMensagemInvalida(true);
		} else {
			setMensagemInvalida(false);
		}

		return getMensagem();

	}

	public static void isSaveSuccessfully(boolean empresa) {

		setCreateSucess(empresa);
	}

	public static String getMensagem() {
		return mensagem;
	}

	public static void setMensagem(String mensagem) {
		ViewCadastrarEmpresas.mensagem = mensagem;
	}

	public static boolean isMensagemInvalida() {
		return mensagemInvalida;
	}

	public static void setMensagemInvalida(boolean mensagemInvalida) {
		ViewCadastrarEmpresas.mensagemInvalida = mensagemInvalida;
	}

	public static boolean isCreateSucess() {
		return createSucess;
	}

	public static void setCreateSucess(boolean createSucess) {
		ViewCadastrarEmpresas.createSucess = createSucess;
	}

}
