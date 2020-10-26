package views;

public class ViewsInstance {

	private static ViewLogin viewLogin;
	private static ViewPrimeiraTela primeiraTela;
	private static ViewCadastrarAtestados cadastroAtestados;
	
	
	public  void instanciarViews() {
		viewLogin = new ViewLogin();
		primeiraTela = new ViewPrimeiraTela();
		cadastroAtestados = new ViewCadastrarAtestados();
	}
	
	public static ViewLogin getViewLogin() {
		return viewLogin;
	}


	public static void setViewLogin(ViewLogin viewLogin) {
		ViewsInstance.viewLogin = viewLogin;
	}


	public static ViewPrimeiraTela getPrimeiraTela() {
		return primeiraTela;
	}


	public static void setPrimeiraTela(ViewPrimeiraTela primeiraTela) {
		ViewsInstance.primeiraTela = primeiraTela;
	}


	public static ViewCadastrarAtestados getCadastroAtestados() {
		return cadastroAtestados;
	}


	public static void setCadastroAtestados(ViewCadastrarAtestados cadastroAtestados) {
		ViewsInstance.cadastroAtestados = cadastroAtestados;
	}
	
}
