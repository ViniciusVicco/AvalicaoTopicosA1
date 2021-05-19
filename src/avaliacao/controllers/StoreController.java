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

import avaliacao.dao.DAO;
import avaliacao.dao.PesoDAO;
import avaliacao.dao.VehicleDAO;
import avaliacao.models.Estado;
import avaliacao.models.Peso;
import avaliacao.models.TipoPeso;
import avaliacao.models.Types;
import avaliacao.models.Vehicle;
import avaliacao.utils.Util;

@Named
@ViewScoped
public class StoreController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2904437223897869684L;
	private Vehicle vehicle;
	private List<Vehicle> listVehicles;
	private Peso peso;
	DAO vehicleDao = new VehicleDAO();
	DAO pesoDao = new PesoDAO();

	public Types[] getListTypes() {
		return Types.values();
	}
	
	public TipoPeso[] getTipoPeso() {
		return TipoPeso.values();
	}

	public Estado[] getListStates() {
		return Estado.values();
	}

	public void insert() {
		System.out.println("Inserindo");

		VehicleDAO dao = new VehicleDAO();
		if (dao.inserir(getVehicle())) {
			Util.addInfoMessage("Inclusão realizada com sucesso");
			clear();
			

		} else {
			Util.addFatalMessage("Tivemos um problema ao inserir este registro");
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

		setVehicle((Vehicle) 
				vehicleDao.obterUm(vehicle.getId()));
	}

	public void edit() {
		DAO dao = new VehicleDAO();
		// Ta buscando do banco sempre e não alterando
		if (dao.alterar(getVehicle())) {
			Util.addInfoMessage("Alterado com sucesso");
			listVehicles = dao.obterTodos();
		} else {
			Util.addInfoMessage("Houve um problema ao realizar essa alteração");
		}
	}

	public void clear() {
		print("Limpando dados");
		setVehicle(null);
	}

	public void remove(Vehicle vehicle) {
		VehicleDAO dao = new VehicleDAO();
		
		if(dao.remover(vehicle)== true) {
			Util.addInfoMessage("Remoção realizada com sucesso");
			listVehicles = dao.obterTodos();
		} else {
			Util.addFatalMessage("Ocorreu um erro ao remover");
		}
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
			VehicleDAO dao = new VehicleDAO();
			listVehicles = dao.obterTodos();
			if (listVehicles == null) {
				listVehicles = new ArrayList<Vehicle>();
			}

		}
		return listVehicles;
	}

	public void setListVehicles(List<Vehicle> listVehicles) {
		this.listVehicles = listVehicles;
	}

}
