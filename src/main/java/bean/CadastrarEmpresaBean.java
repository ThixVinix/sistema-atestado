package bean;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dao.EmpresaDao;
import enumeracoes.TipoCrudEnum;
import model.Empresa;
import util.Constantes;
import util.Validacoes;
import views.ViewCadastrarEmpresas;

public class CadastrarEmpresaBean {

	private static String mensagem;
	private List<Empresa> empresas;
	private EmpresaDao empresaDAO = EmpresaDao.getInstance();

	public CadastrarEmpresaBean() {
		setEmpresas(new ArrayList<Empresa>());
		setEmpresas(empresaDAO.findAll());
	}

	
	public void validarCampos(Empresa empresa, TipoCrudEnum tipoCrud) {

		if (Validacoes.isNullOrEmpty(empresa.getNome())) {
			mensagem = Constantes.MENSAGEM_NOME_OBRIGATORIO;
			ViewCadastrarEmpresas.verificarMensagemInvalida(true, mensagem);
			return;
		}

		if (!Validacoes.hasNotOnlyNumberAndEspecialCharacters(empresa.getNome())) {
			mensagem = Constantes.MENSAGEM_PREENCHER_NOME_CORRETAMENTE;
			ViewCadastrarEmpresas.verificarMensagemInvalida(true, mensagem);
			return;
		}

		if (empresa.getNome().length() > 50) {
			mensagem = Constantes.MENSAGEM_NOME_MAXIMO_CARACTERES_50;
			ViewCadastrarEmpresas.verificarMensagemInvalida(true, mensagem);
			return;
		}

		if (Validacoes.isNullOrEmpty(empresa.getCnpj())) {
			mensagem = Constantes.MENSAGEM_CNPJ_OBRIGATORIO;
			ViewCadastrarEmpresas.verificarMensagemInvalida(true, mensagem);
			return;
		}
		if (Validacoes.hasNotOnlyNumber(empresa.getCnpj())) {
			mensagem = Constantes.MENSAGEM_PREENCHER_CNPJ_APENAS_NUMEROS;
			ViewCadastrarEmpresas.verificarMensagemInvalida(true, mensagem);
			return;
		}

		if (empresa.getCnpj().length() < 14 || empresa.getCnpj().length() > 14) {
			mensagem = Constantes.MENSAGEM_CNPJ_APENAS_14_DIGITOS;
			ViewCadastrarEmpresas.verificarMensagemInvalida(true, mensagem);
			return;
		}

		switch (tipoCrud) {
		case ADICIONAR:
			ViewCadastrarEmpresas.isSaveSuccessfully(adicionarEmpresa(empresa));
			break;

		default:
			break;
		}

	}

	private boolean adicionarEmpresa(Empresa empresa) {

		try {
//			List<Empresa> listEmp = empresaDAO.findAll();
//			Long id = (long) listEmp.size();
//			empresa.setId(id);
			empresaDAO.persist(empresa);

			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

}
