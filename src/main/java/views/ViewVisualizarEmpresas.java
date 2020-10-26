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

import dao.EmpresaDao;
import model.Empresa;

public class ViewVisualizarEmpresas {

	private JFrame frmVisualizarEmpresas;
	private JTable table;

	private List<Empresa> empresas = new ArrayList<>();

	/**
	 * @wbp.parser.entryPoint
	 */
	public void initialize(JFrame frameAnterior) {
		empresas = new ArrayList<Empresa>();
		EmpresaDao funcionarioDAO = EmpresaDao.getInstance();
		empresas = funcionarioDAO.findAll();
		if (empresas.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Não existe empresas cadastradas no sistema.");
			return;
		}

		frameAnterior.setVisible(false);

		frmVisualizarEmpresas = new JFrame();
		frmVisualizarEmpresas.getContentPane().setBackground(Color.LIGHT_GRAY);
		frmVisualizarEmpresas.setTitle("Visualizar Empresas");
		frmVisualizarEmpresas.setBounds(100, 100, 615, 519);
		frmVisualizarEmpresas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmVisualizarEmpresas.setLocationRelativeTo(null);
		frmVisualizarEmpresas.setResizable(false);

		JScrollPane scrollPane = new JScrollPane();

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setIcon(new ImageIcon(ViewVisualizarFuncionarios.class
				.getResource("/com/sun/javafx/scene/control/skin/caspian/fxvk-backspace-button.png")));
		btnVoltar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frmVisualizarEmpresas.dispose();
				frameAnterior.setVisible(true);
			}
		});

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

		JLabel lblNewLabel = new JLabel("Tabela de Empresas");
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setFont(new Font("HelvLight", Font.PLAIN, 38));
		lblNewLabel.setIcon(
				new ImageIcon(ViewVisualizarMedicos.class.getResource("/javax/swing/plaf/metal/icons/Inform.gif")));
		GroupLayout groupLayout = new GroupLayout(frmVisualizarEmpresas.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(78).addComponent(btnVoltar).addGap(89)
								.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
								.addGap(97).addComponent(btnRemover))
						.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(scrollPane,
								GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)))
				.addContainerGap())
				.addGroup(Alignment.TRAILING,
						groupLayout.createSequentialGroup().addContainerGap(104, Short.MAX_VALUE)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 461, GroupLayout.PREFERRED_SIZE)
								.addGap(44)));
		groupLayout
				.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
						groupLayout.createSequentialGroup().addGap(20)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 42, Short.MAX_VALUE).addGap(26)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 325, GroupLayout.PREFERRED_SIZE)
								.addGap(18).addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnVoltar).addComponent(btnEditar).addComponent(btnRemover))
								.addGap(24)));

		table = new JTable();
		table.setEnabled(false);
		table.setBackground(Color.WHITE);
		table.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Nome", "CNPJ" }) {
			private static final long serialVersionUID = -4103823211578130925L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { Object.class, String.class, String.class };

			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});

		DefaultTableModel model = (DefaultTableModel) table.getModel();
//		List<JRadioButton> radionsButton = new ArrayList<JRadioButton>();
//		Map<Long, JRadioButton> radionButtons = new HashMap<Long, JRadioButton>();

		for (int i = 0; i < empresas.size(); i++) {
			model.addRow(new Object[] { String.valueOf(empresas.get(i).getNome()),
					String.valueOf(empresas.get(i).getCnpj()) });
//			JRadioButton rdbtnNewRadioButton = new JRadioButton("");
//			scrollPane.setRowHeaderView(rdbtnNewRadioButton);
//			radionButtons.put(funcionarios.get(i).getId(), rdbtnNewRadioButton);
		}

		scrollPane.setViewportView(table);

		frmVisualizarEmpresas.getContentPane().setLayout(groupLayout);
		frmVisualizarEmpresas.setVisible(true);
	}

}
