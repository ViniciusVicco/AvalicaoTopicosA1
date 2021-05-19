package avaliacao.dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import avaliacao.models.Usuario;
public class UsuarioDAO implements DAO<Usuario> {

	@Override
	public boolean inserir(Usuario obj) {
		Connection conn = DAO.getConnection();
		boolean hasError = true;
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO veiculo ");
		sql.append("(valor, cor, data_lancamento, modelo, marca, tipo, estado) ");
		sql.append("VALUES ");
		sql.append(" (?, ?, ?, ?, ?, ?, ? ) ");
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());

//			stat.setInt(1, obj.getValor());
//			stat.setString(2, obj.getCor());
//			Date data = Date.valueOf(obj.getDataLancamento());
//			stat.setDate(3, data);
//			stat.setString(4, obj.getModelo());
//			stat.setString(5, obj.getMarca());
//			stat.setInt(6, obj.getTipo().getIndex());
//			stat.setInt(7, obj.getEstadoDeConservacao().getIndex());
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

			}
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();

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
		sql.append("UPDATE veiculo SET ");
		sql.append(" valor=?, cor=?, modelo=?, marca=?, tipo=?, estado=? ");
		sql.append(" WHERE ");
		sql.append(" id = ? ");

		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());

			stat.setInt(7, obj.getId());

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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getCurVal() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Usuario> obterTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario obterUm(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
