package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import bean.CadastrarFuncionarioBean;
import dao.EmpresaDao;
import enumeracoes.TipoCrudEnum;
import model.Empresa;
import model.Funcionario;
import util.Validacoes;

public class ViewCadastrarFuncionarios {

	private JFrame frmCadastrarFuncionario;
	private JTextField textFieldNomeFuncionario;
	private TipoCrudEnum tipoCrudEnum;
	Funcionario funcionario = new Funcionario();
	private static String mensagem;
	private static boolean mensagemInvalida;
	private static boolean createSucess;
	private List<Empresa> empresas;

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @wbp.parser.entryPoint
	 */
	public void initialize(JFrame frameAnterior) {

		empresas = new ArrayList<Empresa>();
		EmpresaDao empresaDAO = EmpresaDao.getInstance();
		empresas = empresaDAO.findAll();
		if (empresas.isEmpty()) {
			JOptionPane.showMessageDialog(null,
					"Cadastre pelo menos uma \"EMPRESA\" para poder cadastrar o funcionário!");
			return;
		}

		frameAnterior.setVisible(false);

		JComboBox<String> comboBoxEmpresa = new JComboBox<String>();

		frmCadastrarFuncionario = new JFrame();
		frmCadastrarFuncionario.setTitle("Cadastrar Funcion\u00E1rio");
		frmCadastrarFuncionario.setBounds(100, 100, 450, 300);
		frmCadastrarFuncionario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmCadastrarFuncionario.setLocationRelativeTo(null);
		frmCadastrarFuncionario.setResizable(false);
		frmCadastrarFuncionario.getContentPane().setLayout(null);

		JLabel lblNomeFuncionario = new JLabel("Nome:");
		lblNomeFuncionario.setBounds(10, 11, 46, 14);
		frmCadastrarFuncionario.getContentPane().add(lblNomeFuncionario);

		textFieldNomeFuncionario = new JTextField();
		textFieldNomeFuncionario.setBounds(10, 36, 414, 30);
		frmCadastrarFuncionario.getContentPane().add(textFieldNomeFuncionario);
		textFieldNomeFuncionario.setColumns(10);

		JLabel lblEmpresa = new JLabel("Empresa:");
		lblEmpresa.setBounds(10, 77, 67, 14);
		frmCadastrarFuncionario.getContentPane().add(lblEmpresa);

		comboBoxEmpresa.addItem("Selecione uma opção");

		for (int i = 0; i < empresas.size(); i++) {

			comboBoxEmpresa.addItem(empresas.get(i).getNome());

		}

		comboBoxEmpresa.setBounds(10, 102, 209, 30);

		frmCadastrarFuncionario.getContentPane().add(comboBoxEmpresa);
		comboBoxEmpresa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				for (int i = 0; i < empresas.size(); i++) {

					if ((comboBoxEmpresa.getSelectedIndex() - 1) == -1) {
						funcionario.setEmpresa(null);
					} else {
						if (empresas.get(i).equals(empresas.get(comboBoxEmpresa.getSelectedIndex() - 1))) {
							funcionario.setEmpresa(empresas.get(i));
						}
					}
				}

			}
		});

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(39, 199, 141, 30);
		frmCadastrarFuncionario.getContentPane().add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Cadastro cancelado...");
				frmCadastrarFuncionario.dispose();
				frameAnterior.setVisible(true);
			}
		});

		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(214, 199, 141, 30);
		frmCadastrarFuncionario.getContentPane().add(btnConfirmar);
		btnConfirmar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setMensagemInvalida(false);

				String nome = Validacoes.removeExceededWhiteSpace(textFieldNomeFuncionario.getText());
				funcionario.setNome(nome);

				if (comboBoxEmpresa.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "Escolha uma Empresa disponível.");
					return;
				}

				tipoCrudEnum = TipoCrudEnum.ADICIONAR;
				CadastrarFuncionarioBean createFuncionario = new CadastrarFuncionarioBean();
				createFuncionario.validarCampos(funcionario, tipoCrudEnum);
				if (mensagemInvalida) {
					JOptionPane.showMessageDialog(null, getMensagem(), "Erro de preenchimento",
							JOptionPane.WARNING_MESSAGE, null);
					return;
				}

				if (isCreateSucess()) {
					JOptionPane.showMessageDialog(null, "Funcionário(a) salvo(a) com sucesso!", "Cadastro concluído",
							JOptionPane.INFORMATION_MESSAGE, null);
				} else {
					JOptionPane.showMessageDialog(null,
							"Não foi possível adicionar o(a) Funcionário(a). Consulte o administrador do sistema.",
							"Erro de inserção", JOptionPane.ERROR, null);
					return;
				}
				funcionario = new Funcionario();
				frmCadastrarFuncionario.dispose();
				frameAnterior.setVisible(true);
			}
		});
		frmCadastrarFuncionario.getContentPane().add(btnConfirmar);

		frmCadastrarFuncionario.setVisible(true);
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

	public static void isSaveSuccessfully(boolean funcionario) {
		setCreateSucess(funcionario);
	}

	public static String getMensagem() {
		return mensagem;
	}

	public static void setMensagem(String mensagem) {
		ViewCadastrarFuncionarios.mensagem = mensagem;
	}

	public static boolean isMensagemInvalida() {
		return mensagemInvalida;
	}

	public static void setMensagemInvalida(boolean mensagemInvalida) {
		ViewCadastrarFuncionarios.mensagemInvalida = mensagemInvalida;
	}

	public static boolean isCreateSucess() {
		return createSucess;
	}

	public static void setCreateSucess(boolean createSucess) {
		ViewCadastrarFuncionarios.createSucess = createSucess;
	}
}
