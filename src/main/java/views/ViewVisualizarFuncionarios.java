package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import dao.FuncionarioDao;
import model.Empresa;
import model.Funcionario;

public class ViewVisualizarFuncionarios {

	private JFrame frame;
	private JTable table;

	private List<Funcionario> funcionarios = new ArrayList<>();
	private List<Empresa> empresas = new ArrayList<>();
	private Funcionario funcionario = new Funcionario();
	private Empresa empresa = new Empresa();
	private FuncionarioDao funcionarioDAO = FuncionarioDao.getInstance();


	/**
	 * @wbp.parser.entryPoint
	 */
	@SuppressWarnings("serial")
	public void initialize(JFrame frameAnterior) {
		funcionarios = new ArrayList<Funcionario>();
		FuncionarioDao funcionarioDAO = FuncionarioDao.getInstance();
		funcionarios = funcionarioDAO.findAll();
		if (funcionarios.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Não existe funcionários cadastrados no sistema.");
			return;
		}

		frameAnterior.setVisible(false);

		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 615, 519);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

		JScrollPane scrollPane = new JScrollPane();

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				frameAnterior.setVisible(true);
			}
		});
		btnVoltar.setIcon(new ImageIcon(ViewVisualizarFuncionarios.class
				.getResource("/com/sun/javafx/scene/control/skin/caspian/fxvk-backspace-button.png")));

		JButton btnRemover = new JButton("Remover");
		btnRemover.setEnabled(false);

		JButton btnEditar = new JButton("Editar");
		btnEditar.setEnabled(false);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnRemover.setVisible(false);
				btnVoltar.setVisible(false);
				table.setEnabled(true);
			}
		});

		JLabel lblNewLabel = new JLabel("Tabela de Funcionários");
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setFont(new Font("HelvLight", Font.PLAIN, 38));
		lblNewLabel.setIcon(
				new ImageIcon(ViewVisualizarMedicos.class.getResource("/javax/swing/plaf/metal/icons/Inform.gif")));

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(78).addComponent(btnVoltar).addGap(89)
								.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
								.addGap(97).addComponent(btnRemover))
						.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(scrollPane,
								GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)))
				.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup().addContainerGap(67, Short.MAX_VALUE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 498, GroupLayout.PREFERRED_SIZE)
						.addGap(44)));
		groupLayout
				.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup().addGap(20)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 42, Short.MAX_VALUE).addGap(26)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 325, GroupLayout.PREFERRED_SIZE)
								.addGap(18).addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnVoltar).addComponent(btnEditar).addComponent(btnRemover))
								.addGap(24)));

		table = new JTable();
		table.setEnabled(false);
		table.setBackground(Color.WHITE);
		table.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Nome", "Empresa" }) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { Object.class, String.class, String.class };

			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});

		DefaultTableModel model = (DefaultTableModel) table.getModel();
//		List<JRadioButton> radionsButton = new ArrayList<JRadioButton>();
//		Map<Long, JRadioButton> radionButtons = new HashMap<Long, JRadioButton>();

		for (int i = 0; i < funcionarios.size(); i++) {
			model.addRow(new Object[] { String.valueOf(funcionarios.get(i).getNome()),
					String.valueOf(funcionarios.get(i).getEmpresa().getNome()) });
//			JRadioButton rdbtnNewRadioButton = new JRadioButton("");
//			scrollPane.setRowHeaderView(rdbtnNewRadioButton);
//			radionButtons.put(funcionarios.get(i).getId(), rdbtnNewRadioButton);
		}

		scrollPane.setViewportView(table);

		frame.getContentPane().setLayout(groupLayout);
		frame.setVisible(true);
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public FuncionarioDao getFuncionarioDAO() {
		return funcionarioDAO;
	}

	public void setFuncionarioDAO(FuncionarioDao funcionarioDAO) {
		this.funcionarioDAO = funcionarioDAO;
	}
}
