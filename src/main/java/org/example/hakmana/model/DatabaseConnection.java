package org.example.hakmana.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DatabaseConnection {
    private static DatabaseConnection instance =null;
    private Connection connection;
    private DatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/hakmanaEdm", "root", "");
            System.out.println("Connection Successfully");


        } catch (ClassNotFoundException | SQLException e) {
        }
    }

    public static DatabaseConnection getInstance(){
        if(instance == null){
            instance= new DatabaseConnection();
            return instance;
        }
        return instance;
    }

    public Connection getConnection(){
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

    public DeviceUser[] getUsers() {
        List<DeviceUser> users = new ArrayList<>();
        try {
            String sql = "SELECT * FROM DeviceUser";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Execute the SQL query and get the result set
            ResultSet resultSet = preparedStatement.executeQuery();

            // Iterate through the result set and create Desktop and User objects
            while (resultSet.next()) {
                DeviceUser  user = new DeviceUser();
                user.setNic(resultSet.getString("UserNIC"));
                user.setName(resultSet.getString("name"));
                user.setTitle(resultSet.getString("title"));
                user.setGmail(resultSet.getString("gmail"));


                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return users.toArray(new DeviceUser[0]);
    }

}
