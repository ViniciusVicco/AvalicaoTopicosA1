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
import avaliacao.models.Usuario;
import avaliacao.models.Vehicle;
import avaliacao.utils.Util;

@Named
@ViewScoped
public class LoginController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7165251777611243492L;

	private Usuario usuario;
	private List<Usuario> listUsuario;
	
	public String entrar() {
		Util.print(usuario.getLogin() + usuario.getSenha());
		return null;
		
	}
	

		
	public List<Usuario> getListUsuario() {
		if(listUsuario == null) {
			listUsuario = new ArrayList<Usuario>();
		}
		return listUsuario;
	}
	public void setListUsuario(List<Usuario> listUsuario) {
		this.listUsuario = listUsuario;
	}

	public Usuario getUsuario() {
		if(usuario == null) {
			usuario = new Usuario();
		}
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}