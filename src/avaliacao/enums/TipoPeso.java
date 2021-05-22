package avaliacao.enums;

public enum TipoPeso {

	KG(1,"Quilo"),
	GRAMA(2,"Grama"),
	LIBRA(3,"Libra"),
	MILIGRAMA(4,"Miligrama");
	
	private int index;
	private String label;


	
	TipoPeso(int index, String label){
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
