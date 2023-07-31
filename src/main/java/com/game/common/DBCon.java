package com.game.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBCon {
	public static final String DRIVER_NAME = "org.mariadb.jdbc.Driver";
	public static final String URL = "jdbc:mariadb://localhost:3306/game";
	public static final String USER = "root";
	public static final String PWD = "kd1824java";

	static {
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getCon() {
		try {
			Connection con = DriverManager.getConnection(URL, USER, PWD);
			return con;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		System.out.println(getCon());

	}
}
