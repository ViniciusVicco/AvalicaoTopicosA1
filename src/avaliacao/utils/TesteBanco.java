package avaliacao.utils;

import java.util.Map;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TesteBanco {

	public static void main(String[] args) {


		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/concessionariadb", "topicos1", "123456");
		} catch (SQLException e) {
			System.out.println("Falha na conexão de banco de dados");
			e.printStackTrace();
		}
		ResultSet rs = null;
		try {
			rs = conn.createStatement().executeQuery("Select * FROM usuario");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			while (rs.next()) {

				System.out.println(rs.getInt("Id"));
				System.out.println(rs.getString("cpf"));
				System.out.println(rs.getString("nome"));
				System.out.println(rs.getString("perfil"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}