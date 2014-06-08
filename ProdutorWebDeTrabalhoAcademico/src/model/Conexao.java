package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	private static Connection conn;

	private Conexao() {
		String server = "jdbc:mysql://localhost:3306/";
		String banco = "ptaol_bd";
		String user = "root";
		String senha = "123456";

		StringBuilder sqlDeConexao = new StringBuilder();
		sqlDeConexao.append(server);
		sqlDeConexao.append(banco);
		sqlDeConexao.append("?user=");
		sqlDeConexao.append(user);
		sqlDeConexao.append("&password=");
		sqlDeConexao.append(senha);

		try {

			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(sqlDeConexao.toString());
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection open() {

		try {
			if(conn==null || conn.isClosed()){

			  new Conexao();
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return conn;

	}

	public static void close() {

		try {
			if (conn != null) {
				if (!conn.isClosed()) {
					conn.close();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
