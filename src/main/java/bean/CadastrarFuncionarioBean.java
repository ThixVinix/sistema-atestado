package bean;

import java.util.ArrayList;
import java.util.List;

import dao.FuncionarioDao;
import enumeracoes.TipoCrudEnum;
import model.Funcionario;
import util.Constantes;
import util.Validacoes;
import views.ViewCadastrarFuncionarios;

public class CadastrarFuncionarioBean {

	private static String mensagem;

	private List<Funcionario> funcionarios;
	private FuncionarioDao funcionarioDAO = FuncionarioDao.getInstance();

	public CadastrarFuncionarioBean() {
		setFuncionarios(new ArrayList<Funcionario>());
		setFuncionarios(funcionarioDAO.findAll());

	}

	public void validarCampos(Funcionario funcionario, TipoCrudEnum tipoCrud) {
		if (Validacoes.isNullOrEmpty(funcionario.getNome())) {
			mensagem = Constantes.MENSAGEM_NOME_OBRIGATORIO;
			ViewCadastrarFuncionarios.verificarMensagemInvalida(true, mensagem);
			return;
		}
		
		if (!Validacoes.hasNotOnlyNumberAndEspecialCharacters(funcionario.getNome())) {
			mensagem = Constantes.MENSAGEM_PREENCHER_NOME_CORRETAMENTE;
			ViewCadastrarFuncionarios.verificarMensagemInvalida(true, mensagem);
			return;
		}
		
		if (funcionario.getNome().length() > 40) {
			mensagem = Constantes.MENSAGEM_NOME_MAXIMO_CARACTERES_40;
			ViewCadastrarFuncionarios.verificarMensagemInvalida(true, mensagem);
			return;
		}
		

		System.out.println("Passou aqui");
		switch (tipoCrud) {
		case ADICIONAR:
			ViewCadastrarFuncionarios.isSaveSuccessfully(adicionarFuncionario(funcionario));
			break;

		default:
			break;
		}

	}

	private boolean adicionarFuncionario(Funcionario funcionario) {

		try {
//			List<Funcionario> listFunc = funcionarioDAO.findAll();
//			Long id = (long) listFunc.size();
//			funcionario.setId(id);
			funcionarioDAO.persist(funcionario);

			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

}
