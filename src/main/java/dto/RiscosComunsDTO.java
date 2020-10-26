package dto;

public class RiscosComunsDTO {

	private String nomeRisco;
	private long qtdRisco;

	public RiscosComunsDTO(String nomeRisco, long qtdRisco) {
		this.nomeRisco = nomeRisco;
		this.qtdRisco = qtdRisco;
	}

	public String getNomeRisco() {
		return nomeRisco;
	}

	public void setNomeRisco(String nomeRisco) {
		this.nomeRisco = nomeRisco;
	}

	public long getQtdRisco() {
		return qtdRisco;
	}

	public void setQtdRisco(long qtdRisco) {
		this.qtdRisco = qtdRisco;
	}
}