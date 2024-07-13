package org.example.hakmana.model.mainDevices;

import javafx.scene.control.Alert;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.example.hakmana.model.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Monitors extends Devices{
    private static final Logger sqlLogger= (Logger) LogManager.getLogger(Monitors.class);
    private DatabaseConnection conn=DatabaseConnection.getInstance();
    private static Monitors monitorInstance=null;
    private String monitorRegNum;
    private String model;
    private String status;
    private String userName;
    private String screenSize;//************
    private String purchasedFrom;//************

    private Monitors() {
    }

    public static Monitors getMonitorInstance() {
        if(monitorInstance==null){
            monitorInstance=new Monitors();
            return monitorInstance;
        }
        return monitorInstance;
    }
    @Override
    public String getRegNum() {
        return monitorRegNum;
    }
    @Override
    public void setRegNum(String monitorRegNum) {
        this.monitorRegNum = monitorRegNum;
    }
    @Override
    public String getModel() {
        return model;
    }
    @Override
    public void setModel(String model) {
        this.model = model;
    }
    @Override
    public String getStatus() {
        return status;
    }
    @Override
    public void setStatus(String status) {
        this.status = status;
    }
    @Override
    public String getUserName() {
        return userName;
    }
    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getScreenSize() {
        return screenSize;
    }
    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }
    public String getPurchasedFrom() {
        return purchasedFrom;
    }
    public void setPurchasedFrom(String purchasedFrom) {
        this.purchasedFrom = purchasedFrom;
    }


    //get the Desktop array from the database
    //for updating cards
    @Override
    public Monitors[] getDevices() {
        List<Monitors> monitors = new ArrayList<>();
        //pass query to the connection class
        String sql = "SELECT Monitor.MonitorRegNum,Monitor.model,Monitor.status FROM Monitor";

        try {
            // get result set from connection class
            ResultSet resultSet = conn.executeSt(sql);

            // Iterate through the result set and create Desktop and DeviceUser objects
            while (resultSet.next()) {
                Monitors monitor = new Monitors();

                monitor.setRegNum(resultSet.getString("MonitorRegNum"));
                monitor.setModel(resultSet.getString("model"));
                monitor.setStatus(resultSet.getString("status"));
                monitor.setUserName("no user");

                monitors.add(monitor);//add monitor to the monitors list
            }
        }
        catch (SQLException e){
            sqlLogger.error(e.getMessage());
            alerting(Alert.AlertType.WARNING,"Error Updating Device","An error occurred while updating the device.",e.getMessage());
        }

        return monitors.toArray(new Monitors[0]);
    }
    @Override
    public Monitors getDevice(String monitorRegNum) {
        //pass query to the connection class
        String sql = "SELECT * FROM monitor Where MonitorRegNum=?";

        try {
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ps.setString(1, monitorRegNum);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Monitors monitors = new Monitors();
                monitors.setRegNum(rs.getString("MonitorRegNum"));
                monitors.setModel(rs.getString("model"));
                monitors.setScreenSize(rs.getString("screenSize"));
                monitors.setStatus(rs.getString("status"));
                monitors.setPurchasedFrom(rs.getString("purchasedFrom"));

                return monitors;
            }
        } catch (SQLException e) {
            sqlLogger.error(e.getMessage());
            alerting(Alert.AlertType.WARNING,"Error Updating Device","An error occurred while updating the device.",e.getMessage());
        }

        //return null if there is no result
        return null;
    }

    public boolean updateDevice(ArrayList<String> list){
        //pass query to the connection class
        String sql="UPDATE monitor SET model=?,status=?,screenSize=?,purchasedFrom=? WHERE MonitorRegNum=?";
        return dbInteraction(sql,list,list.getLast());
    }
    public boolean insertDevice(ArrayList<String> list){
        //pass query to the connection class
        String sql="INSERT INTO monitor (MonitorRegNum,model,status,purchasedFrom,screenSize)" +
                "VALUES (?,?,?,?,?)";
        return dbInteraction(sql,list, list.getFirst());
    }

    public ArrayList<String> getAllMonitors(){
        ArrayList<String> monitorList=new ArrayList<>();
        String sql = "SELECT MonitorRegNum FROM monitor";

        try (ResultSet resultSet = conn.executeSt(sql)) {// get result set from connection class and auto closable

            while (resultSet.next()) {
                monitorList.add(resultSet.getString(1)) ;
            }
        } catch (SQLException e) {
            sqlLogger.error(e.getMessage());
            alerting(Alert.AlertType.WARNING,"Error Updating Device","An error occurred while updating the device.",e.getMessage());
        }

        return monitorList;
    }
}
