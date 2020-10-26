package views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import util.LayoutPersonalizado;

public class ViewPrimeiraTela {

	private JFrame frame;

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @wbp.parser.entryPoint
	 */
	public void initialize() {
		LayoutPersonalizado.determinarLayoutPersonalizado("Nimbus");
		frame = new JFrame();
		frame.setTitle("Menu");
		frame.setBounds(100, 100, 546, 373);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu menuAtestado = new JMenu("Atestado");
		menuBar.add(menuAtestado);

		JMenuItem cadastrarAtestado = new JMenuItem("Cadastrar");
		cadastrarAtestado.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ViewCadastrarAtestados viewCadastroAtestado = new ViewCadastrarAtestados();
//				frame.setVisible(false);
//				frame.dispose();
				viewCadastroAtestado.initialize(frame);

			}
		});
		menuAtestado.add(cadastrarAtestado);

		JMenuItem visualizarAtestado = new JMenuItem("Visualizar");
		menuAtestado.add(visualizarAtestado);
		
		JMenuItem consultaEstatisticaRiscos = new JMenuItem("Consulta estat\u00EDstica - Riscos");
		menuAtestado.add(consultaEstatisticaRiscos);
		consultaEstatisticaRiscos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ViewConsultaEstatisticaRiscos consultaEstatisticaRiscos = new ViewConsultaEstatisticaRiscos();
				consultaEstatisticaRiscos.initialize(frame);
				
			}
		});
		
		
		visualizarAtestado.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ViewVisualizarAtestados visualizacaoAtestados = new ViewVisualizarAtestados();
				visualizacaoAtestados.initialize(frame);
				
			}
		});
		

		JMenu menuEmpresa = new JMenu("Empresa");
		menuBar.add(menuEmpresa);

		JMenuItem cadastrarEmpresa = new JMenuItem("Cadastrar");
		menuEmpresa.add(cadastrarEmpresa);

		cadastrarEmpresa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ViewCadastrarEmpresas viewCadastroEmpresa = new ViewCadastrarEmpresas();
				try {
					viewCadastroEmpresa.initialize(frame);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}

			}
		});

		JMenuItem visualizarEmpresa = new JMenuItem("Visualizar");
		menuEmpresa.add(visualizarEmpresa);
		
		visualizarEmpresa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ViewVisualizarEmpresas visualizacaoEmpresas = new ViewVisualizarEmpresas();
					visualizacaoEmpresas.initialize(frame);
				
			}
		});

		JMenu menuFuncionario = new JMenu("Funcion\u00E1rio");
		menuBar.add(menuFuncionario);

		JMenuItem cadastrarFuncionario = new JMenuItem("Cadastrar");
		menuFuncionario.add(cadastrarFuncionario);

		cadastrarFuncionario.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ViewCadastrarFuncionarios viewCadastroFuncionario = new ViewCadastrarFuncionarios();
				viewCadastroFuncionario.initialize(frame);

			}
		});

		JMenuItem visualizarFuncionario = new JMenuItem("Visualizar");
		menuFuncionario.add(visualizarFuncionario);
		visualizarFuncionario.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ViewVisualizarFuncionarios visualizacaoFuncionarios = new ViewVisualizarFuncionarios();
				visualizacaoFuncionarios.initialize(frame);
				
			}
		});

		JMenu menuMedico = new JMenu("M\u00E9dico");
		menuBar.add(menuMedico);

		JMenuItem cadastrarMedico = new JMenuItem("Cadastrar");
		menuMedico.add(cadastrarMedico);

		cadastrarMedico.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ViewCadastrarMedicos viewCadastroMedico = new ViewCadastrarMedicos();
				try {
					viewCadastroMedico.initialize(frame);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JMenuItem visualizarMedico = new JMenuItem("Visualizar");
		menuMedico.add(visualizarMedico);
		
		visualizarMedico.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ViewVisualizarMedicos visualizacaoMedicos = new ViewVisualizarMedicos();
				visualizacaoMedicos.initialize(frame);
			}
		});
		frame.getContentPane().setLayout(null);

		JLabel lblSelecioneUmaOpo = new JLabel("Selecione uma op\u00E7\u00E3o do menu");
		lblSelecioneUmaOpo.setFont(new Font("Arial Black", Font.PLAIN, 24));
		lblSelecioneUmaOpo.setBounds(49, 82, 468, 159);
		frame.getContentPane().add(lblSelecioneUmaOpo);
		frame.setVisible(true);
	}
}
