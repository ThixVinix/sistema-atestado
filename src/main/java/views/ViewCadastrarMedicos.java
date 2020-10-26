package views;

import java.awt.Color;
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

import bean.CadastrarMedicoBean;
import enumeracoes.TipoCrudEnum;
import model.Medico;
import util.Validacoes;

public class ViewCadastrarMedicos {

	private JFrame frmCadastrarMedico;
	private JTextField textFieldNomeMedico;
	private TipoCrudEnum tipoCrudEnum;
	private Medico medico = new Medico();
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
		frmCadastrarMedico = new JFrame();
		frmCadastrarMedico.setTitle("Cadastrar M\u00E9dico");
		frmCadastrarMedico.setBounds(100, 100, 450, 300);
		frmCadastrarMedico.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmCadastrarMedico.setLocationRelativeTo(null);
		frmCadastrarMedico.setResizable(false);
		frmCadastrarMedico.getContentPane().setLayout(null);

		JLabel lblNomeMedico = new JLabel("Nome:");
		lblNomeMedico.setBounds(10, 11, 46, 14);
		frmCadastrarMedico.getContentPane().add(lblNomeMedico);

		textFieldNomeMedico = new JTextField();
		textFieldNomeMedico.setBounds(10, 36, 382, 30);
		frmCadastrarMedico.getContentPane().add(textFieldNomeMedico);
		textFieldNomeMedico.setColumns(10);

		JLabel lblCRM = new JLabel("CRM:");
		lblCRM.setBounds(10, 77, 46, 14);
		frmCadastrarMedico.getContentPane().add(lblCRM);

		JFormattedTextField formattedTextFieldCRM;

		formattedTextFieldCRM = new JFormattedTextField(new MaskFormatter("#######"));
		formattedTextFieldCRM.setFocusLostBehavior(JFormattedTextField.COMMIT);
		frmCadastrarMedico.getContentPane().add(formattedTextFieldCRM);
		formattedTextFieldCRM.setBounds(10, 102, 382, 30);

		JLabel lblObsOCampo = new JLabel("Obs: O campo \"CRM\" deve conter 7 d\u00EDgitos.");
		lblObsOCampo.setForeground(Color.BLUE);
		lblObsOCampo.setBounds(10, 143, 265, 14);
		frmCadastrarMedico.getContentPane().add(lblObsOCampo);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(39, 199, 141, 30);
		frmCadastrarMedico.getContentPane().add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Cadastro cancelado...");
				frmCadastrarMedico.dispose();
				frameAnterior.setVisible(true);
			}
		});

		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(214, 199, 141, 30);
		btnConfirmar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setMensagemInvalida(false);

				String nome = Validacoes.removeExceededWhiteSpace(textFieldNomeMedico.getText());
				medico.setNome(nome);
				String crm = Validacoes.removeWhiteSpace(((JTextField) formattedTextFieldCRM).getText());
				medico.setCrm(crm);

				tipoCrudEnum = TipoCrudEnum.ADICIONAR;
				CadastrarMedicoBean createMedico = new CadastrarMedicoBean();
				createMedico.validarCampos(medico, tipoCrudEnum);
				if (isMensagemInvalida()) {
					JOptionPane.showMessageDialog(null, getMensagem(),"Erro de preenchimento", JOptionPane.WARNING_MESSAGE, null);
					return;
				}

				if (isCreateSucess()) {
					JOptionPane.showMessageDialog(null, "Médico(a) salvo(a) com sucesso!", "Cadastro concluído", JOptionPane.INFORMATION_MESSAGE, null);
				} else {
					JOptionPane.showMessageDialog(null, "Não foi possível adicionar o(a) Médico(a). Consulte o administrador do sistema.", "Erro de inserção", JOptionPane.ERROR, null);
					return;
				}
				medico = new Medico();
				frmCadastrarMedico.dispose();
				frameAnterior.setVisible(true);
			}
		});
		frmCadastrarMedico.getContentPane().add(btnConfirmar);

		frmCadastrarMedico.setVisible(true);
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

	public static void isSaveSuccessfully(boolean medico) {

		setCreateSucess(medico);
	}

	public static String getMensagem() {
		return mensagem;
	}

	public static void setMensagem(String mensagem) {
		ViewCadastrarMedicos.mensagem = mensagem;
	}

	public static boolean isMensagemInvalida() {
		return mensagemInvalida;
	}

	public static void setMensagemInvalida(boolean mensagemInvalida) {
		ViewCadastrarMedicos.mensagemInvalida = mensagemInvalida;
	}

	public static boolean isCreateSucess() {
		return createSucess;
	}

	public static void setCreateSucess(boolean createSucess) {
		ViewCadastrarMedicos.createSucess = createSucess;
	}
}
