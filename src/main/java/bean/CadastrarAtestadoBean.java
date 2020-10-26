package bean;

import java.util.ArrayList;
import java.util.List;

import dao.AtestadoDao;
import enumeracoes.TipoCrudEnum;
import model.Atestado;
import views.ViewCadastrarAtestados;

public class CadastrarAtestadoBean {

	private List<Atestado> funcionarios;
	private AtestadoDao atestadoDAO = AtestadoDao.getInstance();

	public CadastrarAtestadoBean() {
		setFuncionarios(new ArrayList<Atestado>());
		setFuncionarios(atestadoDAO.findAll());

	}

	public void validarCampos(Atestado atestado, TipoCrudEnum tipoCrud) {

		switch (tipoCrud) {
		case ADICIONAR:
			ViewCadastrarAtestados.isSaveSuccessfully(adicionarAtestado(atestado));
			break;

		default:
			break;
		}

	}

	private boolean adicionarAtestado(Atestado atestado) {

		try {
//			List<Atestado> listAt = atestadoDAO.findAll();
//			Long id = (long) listAt.size();
//			atestado.setId(id);
			atestadoDAO.persist(atestado);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public List<Atestado> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Atestado> funcionarios) {
		this.funcionarios = funcionarios;
	}

}
