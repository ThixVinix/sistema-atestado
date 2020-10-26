package views;

import java.awt.Dialog.ModalExclusionType;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

import app.ProgramStart;

public class ViewCarregamento {

	public static JFrame frmCarregamento;
	public static JProgressBar progressBar;
	private static EventQueue eventQueue;
//	protected Thread threadPrincipal = programStart.currentThread();

	/**
	 * @wbp.parser.entryPoint
	 */
	public void initialize() {
		frmCarregamento = new JFrame();
		frmCarregamento.setResizable(false);
		frmCarregamento.setTitle("Carregando");
		frmCarregamento.setIconImage(Toolkit.getDefaultToolkit().getImage(ViewCarregamento.class
				.getResource("/com/sun/javafx/scene/control/skin/caspian/dialog-more-details@2x.png")));
		frmCarregamento.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		frmCarregamento.setBounds(100, 100, 450, 120);
		frmCarregamento.setLocationRelativeTo(null);
		frmCarregamento.getContentPane().setLayout(null);

	
		progressBar.setBounds(10, 11, 424, 69);
		frmCarregamento.getContentPane().add(progressBar);
		frmCarregamento.setVisible(true);

	}

	public static EventQueue getEventQueue() {
		return eventQueue;
	}

	public static void setEventQueue(EventQueue eventQueue) {
		ViewCarregamento.eventQueue = eventQueue;
	}

//	public class Temporizador extends Thread {
//
//		 private int DELAY = 500;
//		
//		public void run() {
//			 int minimum = progressBar.getMinimum();
//			    int maximum = progressBar.getMaximum();
//			    for (int i = minimum; i < maximum; i++) {
//			      try {
//			        int value = progressBar.getValue();
//			        progressBar.setValue(value + 1);
//
//			        Thread.sleep(DELAY);
//			      } catch (InterruptedException ignoredException) {
//			      ignoredException.getStackTrace();
//			      }
//			    
//			  }
//			frmCarregamento.dispose();
//		}
//	}

}
