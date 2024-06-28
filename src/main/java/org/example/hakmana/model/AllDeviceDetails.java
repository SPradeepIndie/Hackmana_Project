package org.example.hakmana.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AllDeviceDetails {
    private static final Logger sqlLogger= (Logger) LogManager.getLogger(AllDeviceDetails.class);
    private String deviceName;
    private String deviceCount;
    private String status;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceCount() {
        return deviceCount;
    }

    public void setDeviceCount(String deviceCount) {
        this.deviceCount = deviceCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public AllDeviceDetails() {

    }
    public AllDeviceDetails(String deviceName, String deviceCount, String status) {
        this.deviceName = deviceName;
        this.deviceCount = deviceCount;
        this.status = status;
    }

    public String[] getDeviceList() {
        DatabaseConnection conn = DatabaseConnection.getInstance();
        List<String> deviceNames = new ArrayList<>();
        String sql = "SELECT table_name FROM information_schema.tables WHERE table_schema = 'hakmanaEdm' AND table_type = 'BASE TABLE' AND table_name NOT IN ('notes', 'systemUser', 'DeviceUser');";
        try {
            ResultSet resultSet = conn.executeSt(sql);
            while (resultSet.next()) {
                deviceNames.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            sqlLogger.error(e.getMessage());
            e.printStackTrace();
        }
        return deviceNames.toArray(new String[0]);
    }

    public AllDeviceDetails[] getActiveDevicesCount() {
        AllDeviceDetails allDeviceDetails = new AllDeviceDetails();
        String[] divNameList = allDeviceDetails.getDeviceList();
        AllDeviceDetails[] activeDevList = new AllDeviceDetails[divNameList.length];

        DatabaseConnection conn = DatabaseConnection.getInstance();

        String sql = "SELECT COUNT(*) AS active_records_count FROM %s WHERE status = 'Active';";

        int index = 0;
        for (String tableName : divNameList) {
            try {
                Statement statement = conn.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery(String.format(sql, tableName));

                while (resultSet.next()) {
                    AllDeviceDetails activeDev = new AllDeviceDetails();
                    activeDev.setDeviceName(tableName);
                    activeDev.setDeviceCount(resultSet.getString("active_records_count"));
                    activeDev.setStatus("Active");
                    activeDevList[index] = activeDev;
                }
            } catch (SQLException e) {
                sqlLogger.error(e.getMessage());
                e.printStackTrace();
            }
            index++;
        }
        return activeDevList;
    }


}





