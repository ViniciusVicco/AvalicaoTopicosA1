package avaliacao.models;

import java.sql.Date;
import java.time.LocalDate;

import avaliacao.enums.TipoPerfil;

public class Usuario {
	
	private Integer id;
	private String cpf;
	private String nome;
	private LocalDate dataNascimento;
	private String email;
	private String login;
	private String senha;
	private TipoPerfil perfil;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public TipoPerfil getPerfil() {
		return perfil;
	}
	public void setPerfil(TipoPerfil perfil) {
		this.perfil = perfil;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	
}
