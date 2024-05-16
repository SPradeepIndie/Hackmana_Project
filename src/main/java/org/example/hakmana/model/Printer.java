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
public class Printer extends Devices {
    private String regNum;
    private String model;
    private String status;
    private String userName;

    private String serialNum;
    private String paperInput;
    private String paperOutput;
    private String purchasedFrom;

    public Printer[] getDevices() {
        DatabaseConnection conn=DatabaseConnection.getInstance();
        List<Printer> printers = new ArrayList<>();
        //pass query to the connection class
        String sql = "SELECT Printer.PrinterRegNum,Printer.model,Printer.status, DeviceUser.name FROM Printer LEFT JOIN user ON Printer.userNIC = DeviceUser.userNIC";

        try {
            // get result set from connection class
            ResultSet resultSet = conn.executeSt(sql);

            // Iterate through the result set and create Desktop and User objects
            while (resultSet.next()) {
                Printer printer = new Printer();
                printer.setRegNum(resultSet.getString("PrinterRegNum"));
                printer.setModel(resultSet.getString("model"));
                printer.setStatus(resultSet.getString("status"));
                printer.setUserName(resultSet.getString("name"));

                printers.add(printer);
            }
        }
        catch (SQLException e){
            System.out.println(e);
        }

        return printers.toArray(new Printer[0]);
    }
}
