package avaliacao.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import avaliacao.enums.Estado;
import avaliacao.enums.TipoPerfil;
import avaliacao.enums.Types;
import avaliacao.models.Usuario;
import avaliacao.models.Vehicle;

public class UsuarioDAO implements DAO<Usuario> {

	
	
	public Usuario authentication(Usuario usuario) {
		Connection conn = DAO.getConnection();

		Usuario usuarioLogado = new Usuario();

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(" u.id, ");
		sql.append(" u.cpf, ");
		sql.append(" u.nome, ");
		sql.append(" u.data_nascimento, ");
		sql.append(" u.email, ");
		sql.append(" u.login, ");
		sql.append(" u.senha, ");
		sql.append(" u.perfil ");
		sql.append("FROM ");
		sql.append(" usuario u");
		sql.append(" WHERE ");
		sql.append(" u.login=? ");
		sql.append(" AND ");
		sql.append(" u.senha=? ");
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, usuario.getLogin());
			stat.setString(2, usuario.getSenha());
			ResultSet rs = stat.executeQuery();
			if (rs.next()) {

				usuarioLogado.setId(rs.getInt("id"));
				usuarioLogado.setCpf(rs.getString("cpf"));
				usuarioLogado.setNome(rs.getString("nome"));
				if (rs.getDate("data_nascimento") != null) {
					usuario.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
				}
				usuarioLogado.setEmail(rs.getString("email"));
				usuarioLogado.setLogin(rs.getString("login"));
				usuarioLogado.setSenha(rs.getString("senha"));
				usuarioLogado.setPerfil(TipoPerfil.fromBd(rs.getInt("perfil")));

			}
		} catch (SQLException e) {
			e.printStackTrace();
			usuarioLogado = null;
		} finally {
			try {

			} catch (Exception e) {
				e.printStackTrace();

			}
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();

			}
		}

		return usuarioLogado;
	}

	
	
	@Override
	public boolean inserir(Usuario obj) {
		Connection conn = DAO.getConnection();
		boolean hasError = true;
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO usuario ");
		sql.append("(cpf, nome, data_nascimento, email, login, senha, perfil) ");
		sql.append("VALUES ");
		sql.append(" (?, ?, ?, ?, ?, ?, ? ) ");
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());

			stat.setString(1, obj.getCpf());
			stat.setString(2, obj.getNome());
			Date data = Date.valueOf(obj.getDataNascimento());
			stat.setDate(3, data);
			stat.setString(4, obj.getEmail());
			stat.setString(5, obj.getLogin());
			stat.setString(6, obj.getSenha());
			stat.setInt(7, 1);
			stat.execute();
			hasError = false;
		} catch (SQLException e) {
			e.printStackTrace();
			hasError = true;
		} finally {
			try {
				stat.close();
			} catch (Exception e) {
				e.printStackTrace();
				hasError = true;

			}
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
				hasError = true;

			}
		}

		if (hasError)
			return false;
		else
			return true;
	}

	@Override
	public boolean alterar(Usuario obj) {
		Connection conn = DAO.getConnection();
		boolean hasError = false;

		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE usuario SET ");
		sql.append(" cpf=?, nome=?, data_nascimento=?, email=?, perfil=? ");
		sql.append(" WHERE ");
		sql.append(" id = ? ");

		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, obj.getCpf());
			stat.setString(2, obj.getNome());
			if (obj.getDataNascimento() == null)
				stat.setDate(3, null);
			else
				stat.setDate(3, Date.valueOf(obj.getDataNascimento()));
			stat.setString(4, obj.getEmail());
			stat.setInt(5, obj.getPerfil().getIndex());
			stat.setInt(6, obj.getId());

			stat.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			hasError = true;
		} finally {
			try {
				stat.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (hasError)
			return false;
		return true;
	}

	@Override
	public boolean remover(Usuario obj) {
		boolean success = true;
		Connection conn = DAO.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE ");
		sql.append(" FROM ");
		sql.append(" usuario ");
		sql.append(" WHERE");
		sql.append(" id = ?");
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, obj.getId());
			stat.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {

			} catch (Exception e) {
				e.printStackTrace();
				success = false;
			}
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
				success = false;
			}
		}
		return success;
	}

	@Override
	public List<Usuario> obterTodos() {
		Connection conn = DAO.getConnection();

		List<Usuario> listUsers = new ArrayList<Usuario>();

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(" u.id, ");
		sql.append(" u.cpf, ");
		sql.append(" u.nome, ");
		sql.append(" u.data_nascimento, ");
		sql.append(" u.email, ");
		sql.append(" u.login, ");
		sql.append(" u.senha, ");
		sql.append(" u.perfil ");
		sql.append("FROM ");
		sql.append(" usuario u");
		sql.append(" ORDER BY ID ");
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setCpf(rs.getString("cpf"));
				usuario.setNome(rs.getString("nome"));
				if (rs.getDate("data_nascimento") != null) {
					usuario.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
				}
				usuario.setEmail(rs.getString("email"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setPerfil(TipoPerfil.fromBd(rs.getInt("perfil")));

				listUsers.add(usuario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {

			} catch (Exception e) {
				e.printStackTrace();
				listUsers = null;
			}
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
				listUsers = null;
			}
		}
		if (listUsers == null || listUsers.isEmpty())
			return null;
		return listUsers;
	}

	@Override
	public Usuario obterUm(Integer id) {
		Connection conn = DAO.getConnection();

		Usuario usuario = new Usuario();

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(" u.id, ");
		sql.append(" u.cpf, ");
		sql.append(" u.nome, ");
		sql.append(" u.data_nascimento, ");
		sql.append(" u.email, ");
		sql.append(" u.login, ");
		sql.append(" u.senha, ");
		sql.append(" u.perfil ");
		sql.append("FROM ");
		sql.append(" usuario u");
		sql.append(" WHERE ");
		sql.append(" u.id=? ");
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, id);
			ResultSet rs = stat.executeQuery();
			if (rs.next()) {

				usuario.setId(rs.getInt("id"));
				usuario.setCpf(rs.getString("cpf"));
				usuario.setNome(rs.getString("nome"));
				if (rs.getDate("data_nascimento") != null) {
					usuario.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
				}
				usuario.setEmail(rs.getString("email"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setPerfil(TipoPerfil.fromBd(rs.getInt("perfil")));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {

			} catch (Exception e) {
				e.printStackTrace();

			}
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();

			}
		}

		return usuario;
	}

}
