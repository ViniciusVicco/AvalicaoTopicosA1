package avaliacao.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import avaliacao.dao.UsuarioDAO;
import avaliacao.models.Usuario;
@Named
@ViewScoped
public class UsuarioController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2200916904007902530L;

	private Usuario usuario;
	private	List<Usuario> listaUsuario;
	
	
	
	public Usuario getUsuario() {
		if(usuario == null) {
			usuario = new Usuario();
		}
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<Usuario> getListaUsuario() {
		UsuarioDAO dao = new UsuarioDAO();
		listaUsuario = dao.obterTodos();
		if(listaUsuario == null) {
			listaUsuario = new ArrayList<Usuario>();
		}
		return listaUsuario;
	}
	public void setListaUsuario(List<Usuario> listaUsuario) {
		
		this.listaUsuario = listaUsuario;
	}
}
