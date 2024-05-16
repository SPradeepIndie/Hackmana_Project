package org.example.hakmana.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Laptops extends Devices{
    private String regNum;
    private String model;
    private String status;
    private String userName;

    private String ram;
    private String processor;
    private String hardDisk;
    private  String os;
    private  String purchasedFrom;
    private String mouseRegNum;
    private String keyboardRegNum;
    private String userNIC;


    public Laptops[] getDevices() {
        DatabaseConnection conn=DatabaseConnection.getInstance();
        List<Laptops> laptops = new ArrayList<>();
        //pass query to the connection class
        String sql = "SELECT Laptop.LaptopRegNum,Laptop.model,Laptop.status, DeviceUser.name FROM laptop LEFT JOIN user ON Laptop.userNIC = DeviceUser.userNIC";

        try {
            // get result set from connection class
            ResultSet resultSet = conn.executeSt(sql);

            // Iterate through the result set and create Desktop and User objects
            while (resultSet.next()) {
                Laptops laptop = new Laptops();

                laptop.setRegNum(resultSet.getString("LaptopRegNum"));
                laptop.setModel(resultSet.getString("model"));
                laptop.setStatus(resultSet.getString("status"));
                laptop.setUserName(resultSet.getString("name"));

                laptops.add(laptop);//add laptop to the laptops list
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

        return laptops.toArray(new Laptops[0]);
    }

}
