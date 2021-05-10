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
import avaliacao.dao.VehicleDAO;
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

		VehicleDAO dao = new VehicleDAO();
		if (dao.inserir(getVehicle())) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Inclusão realizada com sucesso", null));
			clear();

		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Inclusão realizada com sucesso", null));
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

		DAO dao = new VehicleDAO();

		setVehicle(dao.obterUm(vehicle.getId()));

	}

	public void edit() {
		DAO dao = new VehicleDAO();
		// Ta buscando do banco sempre e não alterando
		if (dao.alterar(getVehicle())) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Alteração realizada com sucesso Crud", null));
			listVehicles = dao.obterTodos();
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alteração falhou", null));
		}
	}

	public void clear() {
		print("Limpando dados");
		setVehicle(null);
	}

	public void remove(Vehicle vehicle) {
		VehicleDAO dao = new VehicleDAO();
		
		if(dao.remover(vehicle)== true) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Remoção realizada com sucesso Crud", null));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Remoção falhou", null));
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
