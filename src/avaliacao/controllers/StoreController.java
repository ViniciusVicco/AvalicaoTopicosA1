package avaliacao.controllers;

import java.io.Serializable;
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
	private Integer id = 0;

	public Types[] getListTypes() {
		return Types.values();
	}

	public Estado[] getListStates() {
		return Estado.values();
	}

	public void insert() {
		System.out.println("Inserir");
		getVehicle().setId(id);
		getListVehicles().add(getVehicle());
		System.out.println(vehicle.toString());
		clear();
		id++;
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Inclusão realizada com sucesso", null));

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

	public void clear() {
		print("Limpando dados");
		setVehicle(null);
	}

	public void remove(Vehicle vehicle) {
		System.out.println("Excluir");
		listVehicles.remove(vehicle);
		clear();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
		}
		return listVehicles;
	}

	public void setListVehicles(List<Vehicle> listVehicles) {
		this.listVehicles = listVehicles;
	}

}
