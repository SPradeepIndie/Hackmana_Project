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
public class Desktop extends Devices{
    private String regNum;//Database have DesRegNum
    private String model;
    private String status;
    private String userName;

    private String serialNum="NO";
    private String purchasedFrom="NO";
    private String ram="NO";
    private String processor="NO";
    private String hardDisk="NO";
    private String os="NO";
    private String monitorRegNum="NO";
    private String speakerRegNum="NO";
    private String mouseRegNum="NO";
    private String keyboardRegNum="NO";
    private String soundCard="NO";
    private String tvCard="NO";
    private String networkCard="NO";
    private String micRegNum="NO";
    private String userNIC="No User";
    private String floppyDisk="NO";
    private String scannerRegNum="NO";
    private String ssd;
    private String cdRom;
    private String printerRegNum;
    private String upsRegNum;
    private String powerSupplyRegNum;






    //get the Desktop array from the database
    @Override
    public Desktop[] getDevices() {
        DatabaseConnection conn=DatabaseConnection.getInstance();
        List<Desktop> desktops = new ArrayList<>();
        //pass query to the connection class
        String sql = "SELECT Desktop.DesRegNum,Desktop.model,Desktop.status,DeviceUser.name FROM desktop LEFT JOIN user ON Desktop.userNIC = DeviceUser.userNIC";

        try(ResultSet resultSet = conn.executeSt(sql)) {// get result set from connection class and auto closable

            // Iterate through the result set and create Desktop and User objects
            while (resultSet.next()) {
                Desktop desktop = new Desktop();
                desktop.setRegNum(resultSet.getString("DesRegNum"));
                desktop.setModel(resultSet.getString("model"));
                desktop.setStatus(resultSet.getString("status"));
                desktop.setUserName(resultSet.getString("name"));

                desktops.add(desktop);//add desktop to the desktops list
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

        //return desktops list as an array
        return desktops.toArray(new Desktop[0]);
    }

}
