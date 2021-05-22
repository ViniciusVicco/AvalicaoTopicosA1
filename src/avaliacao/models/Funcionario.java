package avaliacao.models;

import java.time.LocalDate;

import avaliacao.enums.Perfil;

public class Funcionario {

	public Funcionario(Integer id, LocalDate dataCadastro, String cpf, String senha, boolean ativo, Perfil perfil) {
		this.id = id;
		this.dataCadastro = dataCadastro;
		this.cpf = cpf;
		this.senha = senha;
		this.ativo = ativo;
		this.perfil = perfil;
	}

	private Integer id;
	private LocalDate dataCadastro;
	private String cpf;
	private String senha;
	private boolean ativo;
	private Perfil perfil;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

}
