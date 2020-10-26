package otherThreads;

import javax.swing.JProgressBar;

import views.ViewCarregamento;

public class TemporizadorCarregamento extends Thread{

	private int DELAY = 500;
	
	public ViewCarregamento viewCarregamento = new ViewCarregamento();
	
	public static JProgressBar progressBar;
	
	public void run() {
		viewCarregamento.initialize(); 
		
		int minimum = progressBar.getMinimum();
		    int maximum = progressBar.getMaximum();
		    for (int i = minimum; i < maximum; i++) {
		      try {
		        int value = progressBar.getValue();
		        progressBar.setValue(value + 1);

		        Thread.sleep(DELAY);
		      } catch (InterruptedException ignoredException) {
		      ignoredException.getStackTrace();
		      }
		    
		  }
		ViewCarregamento.frmCarregamento.dispose();
	}
}
	

