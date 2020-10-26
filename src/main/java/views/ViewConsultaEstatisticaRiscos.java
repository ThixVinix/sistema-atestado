package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.RiscoDao;
import dto.RiscosComunsDTO;
import model.Risco;

public class ViewConsultaEstatisticaRiscos {

	private JFrame frmVisualizarEstatsticaDe;
	private JTable table;

	Risco risco = new Risco();
	RiscoDao riscoDAO = RiscoDao.getInstance();

	/**
	 * @wbp.parser.entryPoint
	 */
	public void initialize(JFrame frameAnterior) {
		frameAnterior.setVisible(false);
		frmVisualizarEstatsticaDe = new JFrame();
		frmVisualizarEstatsticaDe.setTitle("Visualizar Estat\u00EDstica de Riscos");
		frmVisualizarEstatsticaDe.setBounds(100, 100, 615, 519);
		frmVisualizarEstatsticaDe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmVisualizarEstatsticaDe.getContentPane().setLayout(null);
		frmVisualizarEstatsticaDe.setLocationRelativeTo(null);
		frmVisualizarEstatsticaDe.setResizable(false);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 102, 579, 245);
		frmVisualizarEstatsticaDe.getContentPane().add(scrollPane);

		table = new JTable();
		table.setEnabled(false);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Nome do Risco", "Total de ocorr\u00EAncias" }));

		List<RiscosComunsDTO> pesquisaRiscos = riscoDAO.riscosMaisComuns();

		DefaultTableModel model = (DefaultTableModel) table.getModel();
//		List<JRadioButton> radionsButton = new ArrayList<JRadioButton>();
//		Map<Long, JRadioButton> radionButtons = new HashMap<Long, JRadioButton>();

		for (int i = 0; i < pesquisaRiscos.size(); i++) {
			model.addRow(new Object[] { String.valueOf(pesquisaRiscos.get(i).getNomeRisco()),
					String.valueOf(pesquisaRiscos.get(i).getQtdRisco()) });
//			JRadioButton rdbtnNewRadioButton = new JRadioButton("");
//			scrollPane.setRowHeaderView(rdbtnNewRadioButton);
//			radionButtons.put(funcionarios.get(i).getId(), rdbtnNewRadioButton);
		}

		table.getColumnModel().getColumn(0).setPreferredWidth(118);
		table.getColumnModel().getColumn(1).setPreferredWidth(86);

		scrollPane.setViewportView(table);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(247, 403, 95, 35);
		btnVoltar.setIcon(new ImageIcon(ViewConsultaEstatisticaRiscos.class
				.getResource("/com/sun/javafx/scene/control/skin/caspian/fxvk-backspace-button.png")));
		frmVisualizarEstatsticaDe.getContentPane().add(btnVoltar);

		JLabel lblEstatsticaDeRiscos = new JLabel("Estat\u00EDstica de Riscos");
		lblEstatsticaDeRiscos.setIcon(
				new ImageIcon(ViewVisualizarMedicos.class.getResource("/javax/swing/plaf/metal/icons/Inform.gif")));
		lblEstatsticaDeRiscos.setForeground(Color.DARK_GRAY);
		lblEstatsticaDeRiscos.setFont(new Font("HelvLight", Font.PLAIN, 38));
		lblEstatsticaDeRiscos.setBounds(88, 30, 461, 42);
		frmVisualizarEstatsticaDe.getContentPane().add(lblEstatsticaDeRiscos);
		btnVoltar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frmVisualizarEstatsticaDe.dispose();
				frameAnterior.setVisible(true);
			}
		});
		frmVisualizarEstatsticaDe.setVisible(true);

	}
	
}
