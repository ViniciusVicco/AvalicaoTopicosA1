package avaliacao.enums;

public enum TipoPerfil {

	GRATUITO(1,"Gratuito"),
	PREMIUM(2,"Premium");

	private int index;
	private String label;
	
	
	public int getIndex() {
		return index;
	}

	public String getLabel() {
		return label;
	}




	
	
	TipoPerfil(int index, String label) {
		// TODO Auto-generated constructor stub
		this.index = index;
		this.label = label;
	}

	public static TipoPerfil fromBd(int index) {
		TipoPerfil retorno = TipoPerfil.GRATUITO;
		switch(index) {
		case 1:
			retorno = TipoPerfil.GRATUITO;
			break;
		case 2:
			retorno = TipoPerfil.PREMIUM;
			break;
		}
		return retorno;
	}

	
}
