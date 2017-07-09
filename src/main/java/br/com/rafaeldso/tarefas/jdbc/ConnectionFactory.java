package br.com.rafaeldso.tarefas.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public Connection getConnection() {
		// TODO Auto-generated method stub
		try {
			//jdbc:mysql://localhost:3306/
			//jdbc:mysql://localhost/
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("Where is your MySQL JDBC Driver?");
				e.printStackTrace();
			}
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/bancoRafael?useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "espaco");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			throw new RuntimeException();
		} 
	}

}
