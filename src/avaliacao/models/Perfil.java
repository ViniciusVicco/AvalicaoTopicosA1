package avaliacao.models;

public enum Perfil {

	ADM(1, "Administrador"),
	ATENDENTE(2, "Atentende");
	
	private int index;
	private String label;
	
	Perfil(int index, String label){
		this.index = index;
		this.label = label;
	}

	public int getIndex() {
		return index;
	}

	public String getLabel() {
		return label;
	}


	
}
