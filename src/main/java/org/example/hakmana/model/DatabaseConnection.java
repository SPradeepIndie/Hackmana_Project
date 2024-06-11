package org.example.hakmana.model;

import javafx.scene.control.Alert;

import java.sql.*;

public class DatabaseConnection {
    private static DatabaseConnection instance =null;
    private Connection connection;
    private DatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hakmanaEdm", "root", "SPAxim1@");
            System.out.println("Connection Successfully");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Connection failed");
            //Need to show this alert
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Unable to establish the connection");
        }
    }

    public static DatabaseConnection getInstance(){
        if(instance == null){
            instance= new DatabaseConnection();
            return instance;
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public ResultSet executeSt(String sqlSt) {
        ResultSet resultSet;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlSt);
            // Execute the SQL query and get the result set
            resultSet = preparedStatement.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultSet;
    }
}
