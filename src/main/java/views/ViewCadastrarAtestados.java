package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import bean.CadastrarAtestadoBean;
import dao.FuncionarioDao;
import dao.MedicoDao;
import dao.RiscoDao;
import enumeracoes.TipoCrudEnum;
import enumeracoes.TipoExameEnum;
import model.Atestado;
import model.Funcionario;
import model.Medico;
import model.Risco;
import util.Validacoes;

public class ViewCadastrarAtestados {

	private JFrame frmCadastrarAtestado;
	private TipoCrudEnum tipoCrudEnum;
	private TipoExameEnum tipoExameEnumSelecionado;
	private Atestado atestado = new Atestado();
	private Funcionario funcionario = new Funcionario();
	private Medico medico = new Medico();
	private Risco risco = new Risco();
	RiscoDao riscoDAO = RiscoDao.getInstance();
	private static String mensagem;
	private static boolean mensagemInvalida;
	private static boolean createSucess;
	private List<Funcionario> funcionarios;
	private List<Medico> medicos;
	private List<Risco> riscos = new ArrayList<Risco>();
	private static Boolean biologico;

	private static Boolean fisico;
	private static Boolean ergonomico;
	private static Boolean quimico;
	private static Boolean acidentes;
	private static boolean isApto;

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @wbp.parser.entryPoint
	 */
	public void initialize(JFrame frameAnterior) {

		setAcidentes(false);
		setBiologico(false);
		setErgonomico(false);
		setFisico(false);
		setQuimico(false);
		setApto(false);

		funcionarios = new ArrayList<Funcionario>();
		FuncionarioDao funcionarioDAO = FuncionarioDao.getInstance();
		funcionarios = funcionarioDAO.findAll();
		if (funcionarios.isEmpty()) {
			JOptionPane.showMessageDialog(null,
					"Cadastre pelo menos um \"FUNCIONÁRIO\" para poder cadastrar o atestado!");
			return;
		}

		medicos = new ArrayList<Medico>();
		MedicoDao medicoDAO = MedicoDao.getInstance();
		medicos = medicoDAO.findAll();
		if (medicos.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Cadastre pelo menos um \"MÉDICO\" para poder cadastrar o atestado!");
			return;
		}
		frameAnterior.setVisible(false);
		frmCadastrarAtestado = new JFrame();
		frmCadastrarAtestado.setBounds(100, 100, 670, 500);
		frmCadastrarAtestado.setTitle("Cadastrar Atestado");
		frmCadastrarAtestado.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmCadastrarAtestado.setLocationRelativeTo(null);
		frmCadastrarAtestado.setResizable(false);
		frmCadastrarAtestado.getContentPane().setLayout(null);

		JLabel lblTipoExame = new JLabel(" Tipo Exame:");
		lblTipoExame.setBounds(31, 11, 141, 14);
		frmCadastrarAtestado.getContentPane().add(lblTipoExame);

		JComboBox<TipoExameEnum> comboBoxTipoExame = new JComboBox<TipoExameEnum>();

		comboBoxTipoExame.setModel(new DefaultComboBoxModel<TipoExameEnum>(TipoExameEnum.values()));

		comboBoxTipoExame.setBounds(31, 36, 234, 30);
		frmCadastrarAtestado.getContentPane().add(comboBoxTipoExame);
		tipoExameEnumSelecionado = TipoExameEnum.ADMISSIONAL;
		comboBoxTipoExame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (comboBoxTipoExame.getSelectedIndex() == 0) {
					tipoExameEnumSelecionado = (TipoExameEnum) comboBoxTipoExame.getSelectedItem();
				} else if (comboBoxTipoExame.getSelectedIndex() == 1) {
					tipoExameEnumSelecionado = (TipoExameEnum) comboBoxTipoExame.getSelectedItem();
				} else if (comboBoxTipoExame.getSelectedIndex() == 2) {
					tipoExameEnumSelecionado = (TipoExameEnum) comboBoxTipoExame.getSelectedItem();
				} else if (comboBoxTipoExame.getSelectedIndex() == 3) {
					tipoExameEnumSelecionado = (TipoExameEnum) comboBoxTipoExame.getSelectedItem();
				} else if (comboBoxTipoExame.getSelectedIndex() == 4) {
					tipoExameEnumSelecionado = (TipoExameEnum) comboBoxTipoExame.getSelectedItem();
				} else {
					tipoExameEnumSelecionado = null;
				}

			}
		});

		JLabel lblAptidao = new JLabel("Aptid\u00E3o:");
		lblAptidao.setBounds(31, 304, 46, 14);
		frmCadastrarAtestado.getContentPane().add(lblAptidao);

		JRadioButton rdbtnApto = new JRadioButton("Apto");
		rdbtnApto.setBounds(31, 325, 60, 23);
		frmCadastrarAtestado.getContentPane().add(rdbtnApto);

		JRadioButton rdbtnNaoApto = new JRadioButton("N\u00E3o Apto");
		rdbtnNaoApto.setBounds(125, 325, 109, 23);
		frmCadastrarAtestado.getContentPane().add(rdbtnNaoApto);

		rdbtnApto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (rdbtnApto.isSelected()) {
					rdbtnNaoApto.setSelected(false);
					setApto(true);
				}

			}
		});

		rdbtnNaoApto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (rdbtnNaoApto.isSelected()) {
					rdbtnApto.setSelected(false);
					setApto(false);
				}

			}
		});

		JLabel lblFuncionrio = new JLabel("Funcion\u00E1rio:");
		lblFuncionrio.setBounds(31, 85, 141, 14);
		frmCadastrarAtestado.getContentPane().add(lblFuncionrio);

		JCheckBox chckbxFisicos = new JCheckBox("F\u00EDsicos");
		chckbxFisicos.setBounds(31, 252, 97, 23);
		frmCadastrarAtestado.getContentPane().add(chckbxFisicos);
		chckbxFisicos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (chckbxFisicos.isSelected()) {
					setFisico(true);
				} else {
					setFisico(false);
				}

			}
		});

		JCheckBox chckbxBiologicos = new JCheckBox("Biol\u00F3gicos");
		chckbxBiologicos.setBounds(125, 252, 97, 23);
		frmCadastrarAtestado.getContentPane().add(chckbxBiologicos);
		chckbxBiologicos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (chckbxBiologicos.isSelected()) {
					setBiologico(true);
				} else {
					setBiologico(false);
				}

			}
		});

		JCheckBox chckbxAcidentes = new JCheckBox("Acidentes");
		chckbxAcidentes.setBounds(224, 252, 97, 23);
		frmCadastrarAtestado.getContentPane().add(chckbxAcidentes);
		chckbxAcidentes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (chckbxAcidentes.isSelected()) {
					setAcidentes(true);
				} else {
					setAcidentes(false);
				}
			}
		});

		JCheckBox chckbxQuimicos = new JCheckBox("Qu\u00EDmicos");
		chckbxQuimicos.setBounds(323, 252, 97, 23);
		frmCadastrarAtestado.getContentPane().add(chckbxQuimicos);
		chckbxQuimicos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (chckbxQuimicos.isSelected()) {
					setQuimico(true);
				} else {
					setQuimico(false);
				}
			}
		});

		JCheckBox chckbxErgonomicos = new JCheckBox("Ergon\u00F4micos");
		chckbxErgonomicos.setBounds(422, 252, 97, 23);
		frmCadastrarAtestado.getContentPane().add(chckbxErgonomicos);
		chckbxErgonomicos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (chckbxErgonomicos.isSelected()) {
					setErgonomico(true);
				} else {
					setErgonomico(false);
				}
			}
		});

		JLabel lblRiscos = new JLabel("Riscos:");
		lblRiscos.setBounds(31, 231, 46, 14);
		frmCadastrarAtestado.getContentPane().add(lblRiscos);

		JLabel lblMedico = new JLabel("M\u00E9dico:");
		lblMedico.setBounds(31, 161, 46, 14);
		frmCadastrarAtestado.getContentPane().add(lblMedico);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Cadastro cancelado...");
				frmCadastrarAtestado.dispose();
				frameAnterior.setVisible(true);
			}
		});
		btnCancelar.setBounds(124, 392, 141, 30);
		frmCadastrarAtestado.getContentPane().add(btnCancelar);

		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(303, 392, 141, 30);
		frmCadastrarAtestado.getContentPane().add(btnConfirmar);

		JComboBox<String> comboBoxFuncionario = new JComboBox<String>();
		comboBoxFuncionario.setBounds(31, 110, 234, 30);
		frmCadastrarAtestado.getContentPane().add(comboBoxFuncionario);

		comboBoxFuncionario.addItem("Selecione uma opção");

		for (int i = 0; i < funcionarios.size(); i++) {

			comboBoxFuncionario.addItem(funcionarios.get(i).getNome());

		}

		frmCadastrarAtestado.getContentPane().add(comboBoxFuncionario);
		comboBoxFuncionario.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				for (int i = 0; i < funcionarios.size(); i++) {

					if ((comboBoxFuncionario.getSelectedIndex() - 1) == -1) {
						funcionario = null;
					} else {
						if (funcionarios.get(i).equals(funcionarios.get(comboBoxFuncionario.getSelectedIndex() - 1))) {
							funcionario = funcionarios.get(i);
						}
					}
				}

			}
		});

		JComboBox<String> comboBoxMedico = new JComboBox<String>();
		comboBoxMedico.setBounds(31, 110, 234, 30);

		comboBoxMedico.addItem("Selecione uma opção");

		for (int i = 0; i < medicos.size(); i++) {

			comboBoxMedico.addItem(medicos.get(i).getNome());

		}
		comboBoxMedico.setBounds(31, 186, 234, 30);
		frmCadastrarAtestado.getContentPane().add(comboBoxMedico);

		comboBoxMedico.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				for (int i = 0; i < medicos.size(); i++) {
					if ((comboBoxMedico.getSelectedIndex() - 1) == -1) {
						medico = null;
					} else {
						if (medicos.get(i).equals(medicos.get(comboBoxMedico.getSelectedIndex() - 1))) {
							medico = medicos.get(i);
						}

					}
				}

			}
		});

		btnConfirmar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setMensagemInvalida(false);

				if (Validacoes.isNullOrEmpty(tipoExameEnumSelecionado)) {
					JOptionPane.showMessageDialog(null, "Escolha um Tipo de Exame disponível.", "Erro de preenchimento",
							JOptionPane.WARNING_MESSAGE, null);
					return;
				}

				if (comboBoxFuncionario.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "Escolha um(a) Funcionário(a) disponível.",
							"Erro de preenchimento", JOptionPane.WARNING_MESSAGE, null);
					return;
				}

				if (comboBoxMedico.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "Escolha um(a) Médico(a) disponível.", "Erro de preenchimento",
							JOptionPane.WARNING_MESSAGE, null);
					return;
				}

				if (!rdbtnApto.isSelected() && !rdbtnNaoApto.isSelected()) {
					JOptionPane.showMessageDialog(null, "Preencha a opção \"Aptidão\".", "Erro de preenchimento",
							JOptionPane.WARNING_MESSAGE, null);
					return;
				}

				if (!getAcidentes() && !getBiologico() && !getErgonomico() && !getFisico() && !getQuimico()) {
					JOptionPane.showMessageDialog(null, "Selecione pelo menos um risco.", "Erro de preenchimento",
							JOptionPane.WARNING_MESSAGE, null);
					return;
				}

				atestado.setAptidao(isApto);
				atestado.setFuncionario(funcionario);
				atestado.setMedico(medico);
				atestado.setTipoExame(tipoExameEnumSelecionado);

				Risco riscoFisico;
				Risco riscoQuimico;
				Risco riscoBiologico;
				Risco riscoAcidentes;
				Risco riscoErgonomico;

				if (getFisico()) {
					riscoFisico = riscoDAO.getById(1L);
					riscos.add(riscoFisico);
				}

				if (getBiologico()) {
					riscoBiologico = riscoDAO.getById(2L);
					riscos.add(riscoBiologico);
				}

				if (getAcidentes()) {
					riscoAcidentes = riscoDAO.getById(3L);
					riscos.add(riscoAcidentes);
				}

				if (getQuimico()) {
					riscoQuimico = riscoDAO.getById(4L);
					riscos.add(riscoQuimico);
				}

				if (getErgonomico()) {
					riscoErgonomico = riscoDAO.getById(5L);
					riscos.add(riscoErgonomico);
				}

				atestado.setRiscos(riscos);
				tipoCrudEnum = TipoCrudEnum.ADICIONAR;

				CadastrarAtestadoBean createAtestado = new CadastrarAtestadoBean();
				createAtestado.validarCampos(atestado, tipoCrudEnum);
				if (mensagemInvalida) {
					JOptionPane.showMessageDialog(null, getMensagem(), "Erro de preenchimento",
							JOptionPane.WARNING_MESSAGE, null);

					return;
				}

				if (isCreateSucess()) {
					JOptionPane.showMessageDialog(null, "Atestado salvo com sucesso!", "Cadastro concluído",
							JOptionPane.INFORMATION_MESSAGE, null);
				} else {
					JOptionPane.showMessageDialog(null,
							"Não foi possível adicionar o atestado. Consulte o administrador do sistema.",
							"Erro de inserção", JOptionPane.ERROR, null);
					return;
				}
				atestado = new Atestado();
				frmCadastrarAtestado.dispose();
				frameAnterior.setVisible(true);
			}
		});

		frmCadastrarAtestado.setVisible(true);
	}

	public static void isSaveSuccessfully(boolean atestado) {
		setCreateSucess(atestado);
	}

	public static String getMensagem() {
		return mensagem;
	}

	public static void setMensagem(String mensagem) {
		ViewCadastrarAtestados.mensagem = mensagem;
	}

	public static boolean isMensagemInvalida() {
		return mensagemInvalida;
	}

	public static void setMensagemInvalida(boolean mensagemInvalida) {
		ViewCadastrarAtestados.mensagemInvalida = mensagemInvalida;
	}

	public static boolean isCreateSucess() {
		return createSucess;
	}

	public static void setCreateSucess(boolean createSucess) {
		ViewCadastrarAtestados.createSucess = createSucess;
	}

	public static Boolean getBiologico() {
		return biologico;
	}

	public static void setBiologico(Boolean biologico) {
		ViewCadastrarAtestados.biologico = biologico;
	}

	public static Boolean getFisico() {
		return fisico;
	}

	public static void setFisico(Boolean fisico) {
		ViewCadastrarAtestados.fisico = fisico;
	}

	public static Boolean getErgonomico() {
		return ergonomico;
	}

	public static void setErgonomico(Boolean ergonomico) {
		ViewCadastrarAtestados.ergonomico = ergonomico;
	}

	public static Boolean getQuimico() {
		return quimico;
	}

	public static void setQuimico(Boolean quimico) {
		ViewCadastrarAtestados.quimico = quimico;
	}

	public static Boolean getAcidentes() {
		return acidentes;
	}

	public static void setAcidentes(Boolean acidentes) {
		ViewCadastrarAtestados.acidentes = acidentes;
	}

	public static boolean isApto() {
		return isApto;
	}

	public static void setApto(boolean isApto) {
		ViewCadastrarAtestados.isApto = isApto;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Atestado getAtestado() {
		return atestado;
	}

	public void setAtestado(Atestado atestado) {
		this.atestado = atestado;
	}

	public TipoExameEnum getTipoExameEnumSelecionado() {
		return tipoExameEnumSelecionado;
	}

	public void setTipoExameEnumSelecionado(TipoExameEnum tipoExameEnumSelecionado) {
		this.tipoExameEnumSelecionado = tipoExameEnumSelecionado;
	}

	public List<Risco> getRiscos() {
		return riscos;
	}

	public void setRiscos(List<Risco> riscos) {
		this.riscos = riscos;
	}

	public Risco getRisco() {
		return risco;
	}

	public void setRisco(Risco risco) {
		this.risco = risco;
	}

}
