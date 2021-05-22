package avaliacao.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import avaliacao.models.Peso;

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



}
