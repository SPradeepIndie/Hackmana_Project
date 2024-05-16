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
public class Monitors extends Devices{
    private String regNum;
    private String model;
    private String status;
    private String userName;

    private String screenSize;
    private String purchasedFrom;


    public Monitors[] getDevices() {
        DatabaseConnection conn=DatabaseConnection.getInstance();
        List<Monitors> monitors = new ArrayList<>();
        //pass query to the connection class
        String sql = "SELECT Monitor.MonitorRegNum,Monitor.model,Monitor.status, DeviceUser.name FROM Monitor LEFT JOIN user ON Monitor.userNIC = DeviceUser.userNIC";

        try {
            // get result set from connection class
            ResultSet resultSet = conn.executeSt(sql);

            // Iterate through the result set and create Desktop and User objects
            while (resultSet.next()) {
                Monitors monitor = new Monitors();

                monitor.setRegNum(resultSet.getString("MonitorRegNum"));
                monitor.setModel(resultSet.getString("model"));
                monitor.setStatus(resultSet.getString("status"));
                monitor.setStatus(resultSet.getString("name"));

                monitors.add(monitor);//add monitor to the monitors list
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

        return monitors.toArray(new Monitors[0]);
    }
}
