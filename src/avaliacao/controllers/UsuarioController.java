package avaliacao.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import avaliacao.dao.UsuarioDAO;
import avaliacao.enums.Perfil;
import avaliacao.enums.Types;
import avaliacao.models.Usuario;
import avaliacao.models.Vehicle;
import avaliacao.utils.Util;

@Named
@ViewScoped
public class UsuarioController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2200916904007902530L;

	private Usuario usuario;
	private List<Usuario> listaUsuario;

	public Perfil[] getListPerfil() {
		return Perfil.values();
	}
	
	public void inserir() {
		UsuarioDAO dao = new UsuarioDAO();
		Util.print("Inserindo");
		if(dao.inserir(usuario)) {
			Util.addInfoMessage("Cadastro efetuado com sucesso");
			limpar();
			listaUsuario = dao.obterTodos();
		} else {
			Util.addFatalMessage("Tivemos um problema ao inserir este registro");
		}
	}

	public void alterar(Usuario usuario) {
		UsuarioDAO dao = new UsuarioDAO();
		if(dao.alterar(getUsuario())) {
			Util.addInfoMessage("Alterado com sucesso");
			listaUsuario = dao.obterTodos();
			limpar();
		} else {
			Util.addFatalMessage("Houve um problema ao realizar essa alteração");
		}
	}
	
	public void selecionar(Usuario usuario) {
		UsuarioDAO dao = new UsuarioDAO();
		Util.print("Obtendo 1 registro");
		usuario = dao.obterUm(usuario.getId());
		setUsuario((Usuario) dao.obterUm(usuario.getId()));
	}


	public void excluir(Usuario usuario) {
		UsuarioDAO dao = new UsuarioDAO();
		if(dao.remover(usuario)) {
			Util.addInfoMessage("Remoção realizada com sucesso");
			listaUsuario = dao.obterTodos();
		} else {
			Util.addFatalMessage("Ocorreu um erro ao remover");
		}

	}
	
	public void limpar() {
		setUsuario(null);
	}

	public List<Usuario> getListaUsuario() {
		UsuarioDAO dao = new UsuarioDAO();
		listaUsuario = dao.obterTodos();
		if (listaUsuario == null) {
			listaUsuario = new ArrayList<Usuario>();
		}
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {

		this.listaUsuario = listaUsuario;
	}

	public Usuario getUsuario() {
		if (usuario == null) {
			usuario = new Usuario();
		}
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
