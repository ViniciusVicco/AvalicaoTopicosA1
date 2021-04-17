package avaliacao.models;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Past;

public class Vehicle {
	private Integer id;
	private double valor;
	@NotNull
	private String cor;
	@PastOrPresent
	private LocalDate dataLancamento;
	@NotNull
	private String modelo;
	private Types tipo;
	private Estado estadoDeConservacao;



	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", valor=" + valor + ", cor=" + cor + ", dataLancamento=" + dataLancamento
				+ ", modelo=" + modelo + ", tipo=" + tipo + ", estadoDeConservacao=" + estadoDeConservacao + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}



	public LocalDate getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(LocalDate dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Types getTipo() {
		return tipo;
	}

	public void setTipo(Types tipo) {
		this.tipo = tipo;
	}

	public Estado getEstadoDeConservacao() {
		return estadoDeConservacao;
	}

	public void setEstadoDeConservacao(Estado estadoDeConservacao) {
		this.estadoDeConservacao = estadoDeConservacao;
	}



	
}
