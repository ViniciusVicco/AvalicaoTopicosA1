package avaliacao.models;

public enum Types {
	CARRO(1, "Carro"),
	MOTOCICLETA(2, "Motocicleta"),
	PICAPE(3, "Picape"),
	UTILITARIO(4, "Utilitario");
	
	private int index;
	private String label;
	
	Types(int index, String label){
		this.index = index;
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
	
	public int getIndex() {
		return index;
	}

}
