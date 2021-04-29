package avaliacao.controllers;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import avaliacao.models.Funcionario;
import avaliacao.models.Perfil;

@Named
@ViewScoped
public class LoginController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7165251777611243492L;

	private List<Funcionario> listaFuncionario;
	private Funcionario funcionarioFirst;
	private boolean loginSuccess;
	private boolean isAdmin;
	DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	String data = "16/06/1998";
	LocalDate localDate = LocalDate.parse(data, formater);
	private String cpf = "";
	private String senha = "";
	Funcionario funcionario = new Funcionario(1, LocalDate.parse("16/06/2018", formater), "52245552098", "123456", true,
			Perfil.ATENDENTE);
	Funcionario funcionario1 = new Funcionario(2, LocalDate.parse("13/12/2020", formater), "89454492055", "12sdf3456",
			true, Perfil.ADM);
	Funcionario funcionario2 = new Funcionario(3, LocalDate.parse("03/09/2020", formater), "88142499037", "sdfdsf",
			true, Perfil.ATENDENTE);
	Funcionario funcionario3 = new Funcionario(4, LocalDate.parse("25/03/2016", formater), "85474006055", "123456",
			false, Perfil.ADM);
	Funcionario funcionario4 = new Funcionario(5, LocalDate.parse("30/01/2017", formater), "52008552098", "53321", true,
			Perfil.ATENDENTE);
	Funcionario funcionario5 = new Funcionario(6, LocalDate.parse("21/04/2020", formater), "97898847024", "23314", true,
			Perfil.ADM);

	public void addAllFuncionarios() {
		print("inserindo na lista de funcionarios");
		listaFuncionario.add(funcionario);
		listaFuncionario.add(funcionario1);
		listaFuncionario.add(funcionario2);
		listaFuncionario.add(funcionario3);
		listaFuncionario.add(funcionario4);
		listaFuncionario.add(funcionario5);
	}

	public String goToLogin() {

		return "login";
	}

	public String goMenu() {
		return "menu";
	}

	public String goToCadastrar() {
		return "cadastroautomovel";
	}

	public String goToSistemaEmConstrucao() {
		if (isLoginSuccess()) {
			if (isAdmin) {
				return goToCadastrar();
			} else {
				return "sistemaemconstrucao";
			}
		} else {
			return goToLogin();
		}
	}

	public void login() {
		if (getListaFuncionario().size() == 0) {
			addAllFuncionarios();
		}

		int length = getListaFuncionario().size();
		for (int i = 0; i < length; i++) {
			if (getListaFuncionario().get(i).isAtivo()) {
				if (getListaFuncionario().get(i).getCpf().equals(funcionarioFirst.getCpf())
						&& getListaFuncionario().get(i).getSenha().equals(funcionarioFirst.getSenha())) {
					print("Login Realizado com sucesso");
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_INFO, "Login Realizado com sucesso", null));
					if (getListaFuncionario().get(i).getPerfil() == Perfil.ADM) {
						isAdmin = true;
					} else {
						isAdmin = false;
					}
					loginSuccess = true;
					print(loginSuccess ? "Sucesso" : "Falha");
					return;
				}
				if (i == length) {
					if (getListaFuncionario().get(i).getCpf().equals(funcionarioFirst.getCpf())
							&& getListaFuncionario().get(i).getSenha().equals(funcionarioFirst.getSenha())) {
						print("Login Realizado com sucesso");
						FacesContext.getCurrentInstance().addMessage(null,
								new FacesMessage(FacesMessage.SEVERITY_INFO, "Login Realizado com sucesso", null));
						if (getListaFuncionario().get(i).getPerfil() == Perfil.ADM) {
							isAdmin = true;
						} else {
							isAdmin = false;
						}
						loginSuccess = true;
						print(loginSuccess ? "Sucesso" : "Falha");
						return;
					}
					if (!getListaFuncionario().get(i).getCpf().equals(funcionarioFirst.getCpf())
							|| !getListaFuncionario().get(i).getSenha().equals(funcionarioFirst.getSenha())) {
						print("Login falhou, as credenciais não batem");
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Login falhou, as credenciais não batem", null));
						loginSuccess = true;
						print(loginSuccess ? "Sucesso" : "Falha");
						return;
					}
				}
			} else {
				print("Ops, Perfil inativo");
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Ops, Perfil inativo ou login incorreto", null));
				loginSuccess = false;
				return;
			}
		}

	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	static void print(String params) {
		System.out.println(params);
	}

	public List<Funcionario> getListaFuncionario() {
		if (listaFuncionario == null) {
			listaFuncionario = new ArrayList<Funcionario>();
		}
		return listaFuncionario;
	}

	public boolean isLoginSuccess() {
		return loginSuccess;
	}

	public void setLoginSuccess(boolean loginSuccess) {
		this.loginSuccess = loginSuccess;
	}

	public void setListaFuncionario(List<Funcionario> listaFuncionario) {
		this.listaFuncionario = listaFuncionario;
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

	public Funcionario getFuncionarioFirst() {
		if (funcionarioFirst == null) {
			funcionarioFirst = new Funcionario(0, localDate, "", "", true, Perfil.ADM);
		}
		return funcionarioFirst;
	}

	public void setFuncionarioFirst(Funcionario funcionarioFirst) {
		this.funcionarioFirst = funcionarioFirst;
	}

}
