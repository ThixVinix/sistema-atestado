package bean;

import java.util.ArrayList;
import java.util.List;

import dao.MedicoDao;
import enumeracoes.TipoCrudEnum;
import model.Medico;
import util.Constantes;
import util.Validacoes;
import views.ViewCadastrarMedicos;

public class CadastrarMedicoBean {

	private static String mensagem;

	private List<Medico> medicos;
	private MedicoDao medicoDAO = MedicoDao.getInstance();
	

	public CadastrarMedicoBean() {
		setMedicos(new ArrayList<Medico>());
		setMedicos(medicoDAO.findAll());

	}

	public void validarCampos(Medico medico, TipoCrudEnum tipoCrud) {

		if (Validacoes.isNullOrEmpty(medico.getNome())) {
			mensagem = Constantes.MENSAGEM_NOME_OBRIGATORIO;
			ViewCadastrarMedicos.verificarMensagemInvalida(true, mensagem);
			return;
		}

		if (!Validacoes.hasNotOnlyNumberAndEspecialCharacters(medico.getNome())) {
			mensagem = Constantes.MENSAGEM_PREENCHER_NOME_CORRETAMENTE;
			ViewCadastrarMedicos.verificarMensagemInvalida(true, mensagem);
			return;
		}

		if (medico.getNome().length() > 40) {
			mensagem = Constantes.MENSAGEM_NOME_MAXIMO_CARACTERES_40;
			ViewCadastrarMedicos.verificarMensagemInvalida(true, mensagem);
			return;
		}

		if (Validacoes.isNullOrEmpty(medico.getCrm())) {
			mensagem = Constantes.MENSAGEM_CRM_OBRIGATORIO;
			ViewCadastrarMedicos.verificarMensagemInvalida(true, mensagem);
			return;
		}
		if (Validacoes.hasNotOnlyNumber(medico.getCrm())) {
			mensagem = Constantes.MENSAGEM_PREENCHER_CRM_APENAS_NUMEROS;
			ViewCadastrarMedicos.verificarMensagemInvalida(true, mensagem);
			return;
		}

		if (medico.getCrm().length() < 7 || medico.getCrm().length() > 7) {
			mensagem = Constantes.MENSAGEM_CRM_APENAS_7_DIGITOS;
			ViewCadastrarMedicos.verificarMensagemInvalida(true, mensagem);
			return;
		}

		// TODO COLOCAR VALIDAÇÃO PARA NÃO PERMITIR "CRM" IGUAIS
//		if (Validacoes.isNullOrEmpty(medicos)) {
//			
//		}

		switch (tipoCrud) {
		case ADICIONAR:
			ViewCadastrarMedicos.isSaveSuccessfully(adicionarMedico(medico));
			break;

		default:
			break;
		}

	}

	private boolean adicionarMedico(Medico medico) {

		try {
//			List<Medico> medicos = medicoDAO.findAll();
//			Long id = (long) medicos.size();
//			medico.setId(id);
			medicoDAO.persist(medico);

			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public List<Medico> getMedicos() {
		return medicos;
	}

	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}

}
