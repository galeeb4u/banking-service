package com.springboot.bankproject.util;


import java.sql.*;

public class DatabaseConnection {
    private static final String url = "jdbc:mariadb://localhost:3306/BankingProject";
    private static final String username = "root";
    private static final String password = "password";
    private static Connection connection=null;
    private DatabaseConnection(){}

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        //Singleton pattern
        if (connection == null)
        {
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to database server "
                    + connection.getMetaData().getDatabaseProductName()
                    + " version: "
                    + connection.getMetaData().getDatabaseProductVersion());
        } 
        return connection;
    }
    
    public static void closeConnection() throws SQLException
    {
        if(connection != null)
            connection.close();
    }
}
