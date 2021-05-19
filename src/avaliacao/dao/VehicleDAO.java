package avaliacao.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import avaliacao.models.Estado;
import avaliacao.models.Types;
import avaliacao.models.Vehicle;

public class VehicleDAO implements DAO<Vehicle> {

	@Override
	public boolean inserir(Vehicle obj) {
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

			stat.setInt(1, obj.getValor());
			stat.setString(2, obj.getCor());
			Date data = Date.valueOf(obj.getDataLancamento());
			stat.setDate(3, data);
			stat.setString(4, obj.getModelo());
			stat.setString(5, obj.getMarca());
			stat.setInt(6, obj.getTipo().getIndex());
			stat.setInt(7, obj.getEstadoDeConservacao().getIndex());
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
	public boolean alterar(Vehicle obj) {
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
			stat.setInt(1, obj.getValor());
			stat.setString(2, obj.getCor());
			stat.setString(3, obj.getModelo());
			stat.setString(4, obj.getMarca());
			stat.setInt(5, obj.getTipo().getIndex());
			stat.setInt(6, obj.getEstadoDeConservacao().getIndex());
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
	public boolean remover(Vehicle obj) {
		boolean success = true;
		Connection conn = DAO.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE ");
		sql.append(" FROM ");
		sql.append(" veiculo ");
		sql.append(" WHERE");
		sql.append(" id = ?");
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, obj.getId());

			ResultSet rs = stat.executeQuery();
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
	public List<Vehicle> obterTodos() {

		Connection conn = DAO.getConnection();

		List<Vehicle> listVehicles = new ArrayList<Vehicle>();

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(" v.id, ");
		sql.append(" v.valor, ");
		sql.append(" v.cor, ");
		sql.append(" data_lancamento, ");
		sql.append(" v.modelo, ");
		sql.append(" v.marca, ");
		sql.append(" v.tipo, ");
		sql.append(" v.estado ");
		sql.append("FROM ");
		sql.append(" veiculo v");
		sql.append(" ORDER BY ID ");
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				Vehicle vehicle = new Vehicle();
				vehicle.setId(rs.getInt("id"));
				vehicle.setValor(rs.getInt("valor"));
				if (rs.getDate("data_lancamento") != null) {
					vehicle.setDataLancamento(rs.getDate("data_lancamento").toLocalDate());
				}
				vehicle.setCor(rs.getString("cor"));
				vehicle.setModelo(rs.getString("modelo"));
				vehicle.setMarca(rs.getString("marca"));
				switch (rs.getInt("tipo")) {
				case 1:
					vehicle.setTipo(Types.CARRO);
					break;
				case 2:
					vehicle.setTipo(Types.MOTOCICLETA);
					break;
				case 3:
					vehicle.setTipo(Types.PICAPE);
					break;
				case 4:
					vehicle.setTipo(Types.UTILITARIO);
					break;
				default:
					vehicle.setTipo(Types.CARRO);
					break;
				}
				switch (rs.getInt("estado")) {

				case 1:
					vehicle.setEstadoDeConservacao(Estado.EXCELENTE);
					break;
				case 2:
					vehicle.setEstadoDeConservacao(Estado.BOM);
					break;
				case 3:
					vehicle.setEstadoDeConservacao(Estado.FUNCIONAL);
					break;
				case 4:
					vehicle.setEstadoDeConservacao(Estado.RUIM);
					break;
				case 5:
					vehicle.setEstadoDeConservacao(Estado.PESSIMO);
					break;
				default:
					vehicle.setEstadoDeConservacao(Estado.BOM);
					break;
				}
				listVehicles.add(vehicle);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {

			} catch (Exception e) {
				e.printStackTrace();
				listVehicles = null;
			}
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
				listVehicles = null;
			}
		}
		if (listVehicles == null || listVehicles.isEmpty())
			return null;
		return listVehicles;
	}

	@Override
	public Vehicle obterUm(Integer id) {
		Connection conn = DAO.getConnection();

		Vehicle vehicle = null;

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(" v.id, ");
		sql.append(" v.valor, ");
		sql.append(" v.cor, ");
		sql.append(" v.modelo, ");
		sql.append(" v.marca, ");
		sql.append(" v.tipo, ");
		sql.append(" v.estado ");
		sql.append("FROM ");
		sql.append(" veiculo v ");
		sql.append("WHERE ");
		sql.append("  v.id = ? ");

		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, id);

			ResultSet rs = stat.executeQuery();

			if (rs.next()) {
				vehicle = new Vehicle();
				vehicle.setId(rs.getInt("id"));
				vehicle.setValor(rs.getInt("valor"));
				vehicle.setCor(rs.getString("cor"));
				vehicle.setModelo(rs.getString("modelo"));
				vehicle.setMarca(rs.getString("marca"));
				switch (rs.getInt("tipo")) {
				case 1:
					vehicle.setTipo(Types.CARRO);
					break;
				case 2:
					vehicle.setTipo(Types.MOTOCICLETA);
					break;
				case 3:
					vehicle.setTipo(Types.PICAPE);
					break;
				case 4:
					vehicle.setTipo(Types.UTILITARIO);
					break;
				default:
					vehicle.setTipo(Types.CARRO);
					break;
				}
				switch (rs.getInt("estado")) {

				case 1:
					vehicle.setEstadoDeConservacao(Estado.EXCELENTE);
					break;
				case 2:
					vehicle.setEstadoDeConservacao(Estado.BOM);
					break;
				case 3:
					vehicle.setEstadoDeConservacao(Estado.FUNCIONAL);
					break;
				case 4:
					vehicle.setEstadoDeConservacao(Estado.RUIM);
					break;
				case 5:
					vehicle.setEstadoDeConservacao(Estado.PESSIMO);
					break;
				default:
					vehicle.setEstadoDeConservacao(Estado.BOM);
					break;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			vehicle = null;
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
		return vehicle;
	}

	@Override
	public int getCurVal() {
		Connection conn = DAO.getConnection();

		int curVal = -1;

		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT ");
		sql.append(" v.id ");
		sql.append(" FROM ");
		sql.append(" veiculo v ");
		sql.append(" ORDER BY ");
		sql.append(" v.id ");
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
		System.out.print("VEICULO ID"+ curVal);
		return curVal;
	}

}
