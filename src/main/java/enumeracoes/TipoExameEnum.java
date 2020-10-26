package enumeracoes;

public enum TipoExameEnum {
	ADMISSIONAL("AD"), PERIODICO("PD"), RETORNO_TRABALHO("RT"), 
	DEMISSIONAL("DM"), TRANSFERENCIA_FUNCAO("TF");

	TipoExameEnum(String exame) {
		this.exame = exame;
	}

	private String exame;

	public static TipoExameEnum valorOfCodigo(String exame) {
		for (TipoExameEnum situacaoVendedorEnum : values()) {
			if (situacaoVendedorEnum.exame.equalsIgnoreCase(exame)) {
				return situacaoVendedorEnum;
			}
		}
		throw new IllegalArgumentException("Exame não encontrado=" + exame);
	}

	public String getCodigo() {
		return exame;
	}

}
