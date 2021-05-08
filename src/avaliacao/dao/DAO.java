package avaliacao.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import avaliacao.models.Vehicle;

public interface DAO {
	
	public boolean inserir(Vehicle obj);
	public boolean alterar(Vehicle obj);
	public boolean remover(Vehicle obj);
	public List<Vehicle> obterTodos();
	public Vehicle obterUm(Integer id);
	
	public static Connection getConnection() {

		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = null;
			connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/concessionariadb", "topicos1",
					"123456");
			return connection;
		} catch (ClassNotFoundException e) {
			System.out.println("O Driver n�o foi encontrado.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Falha na conexao com o banco de dados.");
			e.printStackTrace();
		}
		return null;
	}

	
	
	
	//DAO For�a implementa��o de m�todos est�ticos previamente definidos

}
