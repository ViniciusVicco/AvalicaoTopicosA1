package avaliacao.models;

public enum Estado {

	EXCELENTE(1, "Excelente"), BOM(2, "Bom"), FUNCIONAL(3, "Funcional"), RUIM(4, "Ruim"), PESSIMO(5, "Pessimo");

	private int index;
	private String label;

	Estado(int index, String label) {
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
