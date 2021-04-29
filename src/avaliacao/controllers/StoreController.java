package avaliacao.controllers;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import avaliacao.models.Estado;
import avaliacao.models.Types;
import avaliacao.models.Vehicle;

@Named
@ViewScoped
public class StoreController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2904437223897869684L;
	private Vehicle vehicle;
	private List<Vehicle> listVehicles;

	public Types[] getListTypes() {
		return Types.values();
	}

	public Estado[] getListStates() {
		return Estado.values();
	}

	public void insert() {
		System.out.println("Inserindo");
		Connection conn = StoreController.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO veiculo ");
		sql.append("(valor, cor, modelo, marca, tipo, estado) ");
		sql.append("VALUES ");
		sql.append(" (?, ?, ?, ?, ?, ? ) ");
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());

			stat.setInt(1, getVehicle().getValor());
			stat.setString(2, getVehicle().getCor());
//			stat.setDate(3, getVehicle().getDataLancamento());
			stat.setString(3, getVehicle().getModelo());
			stat.setString(4, getVehicle().getMarca());
			stat.setInt(5, getVehicle().getTipo().getIndex());
			stat.setInt(6, getVehicle().getEstadoDeConservacao().getIndex());
			stat.execute();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Inclusão realizada com sucesso", null));
			clear();
		} catch (SQLException e) {
			e.printStackTrace();
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
		return "sistemaemconstrucao";
	}

	public void select(Vehicle vehicle) {

		System.out.println("Selecionado " + vehicle);
		setVehicle(vehicle);

	}

	public void edit() {
		System.out.println("Alterar");
		if (listVehicles == null) {
			System.out.print("Lista veiculo ta nula");
		}
		if (vehicle != null || listVehicles.size() == 0) {
			int index = listVehicles.indexOf(vehicle);
			if (index == -1) {
				System.out.println("Objeto veiculo não foi encontrado");
				System.out.println(listVehicles.size());
				return;
			} else {
				getListVehicles().set(index, getVehicle());
			}

		}
		clear();
		return;
	}

	private static Connection getConnection() {

		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = null;
			connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/concessionariadb", "topicos1",
					"123456");
			return connection;
		} catch (ClassNotFoundException e) {
			System.out.println("O Driver não foi encontrado.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Falha na conexao com o banco de dados.");
			e.printStackTrace();
		}
		return null;
	}

	public void clear() {
		print("Limpando dados");
		setVehicle(null);
	}

	public void remove(Vehicle vehicle) {
		System.out.println("Excluir");
		listVehicles.remove(vehicle);
		clear();
	}

	static void print(String params) {
		System.out.println(params);
	}

	public Vehicle getVehicle() {
		if (vehicle == null) {
			vehicle = new Vehicle();
		}
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public List<Vehicle> getListVehicles() {
		if (listVehicles == null) {
			listVehicles = new ArrayList<Vehicle>();
			Connection conn = StoreController.getConnection();
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
			sql.append(" veiculo v");
			PreparedStatement stat = null;
			try {
				stat = conn.prepareStatement(sql.toString());
				ResultSet rs = stat.executeQuery();
				while (rs.next()) {
					Vehicle vehicle = new Vehicle();
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
					listVehicles.add(vehicle);
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

		}
		return listVehicles;
	}

	public void setListVehicles(List<Vehicle> listVehicles) {
		this.listVehicles = listVehicles;
	}

}
