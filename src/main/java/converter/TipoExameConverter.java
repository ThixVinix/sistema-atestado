package converter;

import javax.persistence.AttributeConverter;

import enumeracoes.TipoExameEnum;

public class TipoExameConverter implements AttributeConverter<TipoExameEnum, String> {

	@Override
	public String convertToDatabaseColumn(TipoExameEnum tipoExameEnum) {
		return tipoExameEnum != null ? tipoExameEnum.getCodigo() : null;
	}

	@Override
	public TipoExameEnum convertToEntityAttribute(String s) {
		return s != null ? TipoExameEnum.valorOfCodigo(s) : null;
	}

}
