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
}
