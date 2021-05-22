package avaliacao.models;

import avaliacao.enums.TipoPeso;

public class Peso {
	
	private Integer id;
	private String valor;
	private TipoPeso tipoPeso;
	private Integer produtoId;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}	
	public TipoPeso getTipoPeso() {
		return tipoPeso;
	}
	public void setTipoPeso(TipoPeso tipoPeso) {
		this.tipoPeso = tipoPeso;
	}
	public Integer getProdutoId() {
		return produtoId;
	}
	public void setProdutoId(Integer produtoId) {
		this.produtoId = produtoId;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	

}
