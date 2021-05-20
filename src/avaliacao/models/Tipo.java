package avaliacao.models;

public enum Tipo {

	COMUM(1,"Comum"),
	PREMIUM(2,"Premium");

	private int index;
	private String label;
	
	
	public int getIndex() {
		return index;
	}

	public String getLabel() {
		return label;
	}




	
	
	Tipo(int index, String label) {
		// TODO Auto-generated constructor stub
		this.index = index;
		this.label = label;
	}

	public static Tipo fromBd(int index) {
		Tipo retorno = Tipo.COMUM;
		switch(index) {
		case 1:
			retorno = Tipo.COMUM;
			break;
		case 2:
			retorno = Tipo.PREMIUM;
			break;
		}
		return retorno;
	}

	
}
