package app;

import java.awt.EventQueue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import otherThreads.TemporizadorCarregamento;
import util.LayoutPersonalizado;
import views.ViewCarregamento;
import views.ViewLogin;

public class ProgramStart extends Thread {

	protected static EntityManagerFactory emf = null;
	protected static EntityManager em = null;
	protected static EventQueue eventQueue;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		emf = Persistence.createEntityManagerFactory("persistence-atestado");
		em = emf.createEntityManager();

		ViewCarregamento.setEventQueue(eventQueue);
		LayoutPersonalizado.determinarLayoutPersonalizado("Nimbus");

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					 ViewCarregamento viewCarregamento = new ViewCarregamento();
//					 viewCarregamento.initialize();
//					Thread barThread = new TemporizadorCarregamento();
//					barThread.start();
					ViewLogin window = new ViewLogin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
	
	

}
