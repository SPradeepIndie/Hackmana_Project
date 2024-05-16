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

    public void insertDesktop(Desktop desktop) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO Desktop (DesRegNum, serialNum, model, purchasedFrom, ram, processor, hardDisk, os, status, floppyDisk, soundCard, tvCard, networkCard, monitorRegNum, speakerRegNum, mouseRegNum, keyboardRegNum, micRegNum, scannerRegNum, userNIC) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
            );
            preparedStatement.setString(1, desktop.getRegNum());
            preparedStatement.setString(2, desktop.getSerialNum());
            preparedStatement.setString(3, desktop.getModel());
            preparedStatement.setString(4, desktop.getPurchasedFrom());
            preparedStatement.setString(5, desktop.getRam());
            preparedStatement.setString(6, desktop.getProcessor());
            preparedStatement.setString(7, desktop.getHardDisk());
            preparedStatement.setString(8, desktop.getOs());
            preparedStatement.setString(9, desktop.getStatus());
            preparedStatement.setString(10, desktop.getFloppyDisk());
            preparedStatement.setString(11, desktop.getSoundCard());
            preparedStatement.setString(12, desktop.getTvCard());
            preparedStatement.setString(13, desktop.getNetworkCard());
            preparedStatement.setString(14, desktop.getMonitorRegNum());
            preparedStatement.setString(15, desktop.getSpeakerRegNum());
            preparedStatement.setString(16, desktop.getMouseRegNum());
            preparedStatement.setString(17, desktop.getKeyboardRegNum());
            preparedStatement.setString(18, desktop.getMicRegNum());
            preparedStatement.setString(19, desktop.getScannerRegNum());
            preparedStatement.setString(20, desktop.getUserNIC());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertUser(DeviceUser user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO DeviceUser (userNIC, name, title, gmail) VALUES (?, ?, ?, ?)"
            );
            preparedStatement.setString(1, user.getNic());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getTitle());
            preparedStatement.setString(4, user.getGmail());


            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
