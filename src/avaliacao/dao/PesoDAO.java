package avaliacao.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import avaliacao.models.Estado;
import avaliacao.models.Peso;
import avaliacao.models.Types;
import avaliacao.models.Vehicle;

public class PesoDAO implements DAO<Peso> {

	@Override
	public boolean inserir(Peso obj) {
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
	public boolean alterar(Peso obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remover(Peso obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Peso> obterTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Peso obterUm(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCurVal() {
		Connection conn = DAO.getConnection();

		int curVal = -1;

		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT ");
		sql.append(" p.id ");
		sql.append(" FROM ");
		sql.append(" peso p ");
		sql.append(" ORDER BY ");
		sql.append(" p.id ");
		sql.append(" DESC LIMIT 1 ");
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				
				curVal = (rs.getInt("id"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {

			} catch (Exception e) {
				e.printStackTrace();
				curVal = -1;
			}
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
				curVal = -1;
			}
		}
		System.out.print("PESOID: "+curVal);
		return curVal;
	}

}
