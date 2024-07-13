package org.example.hakmana.model.mainDevices;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.example.hakmana.model.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class UPS extends Devices{
    private static final Logger sqlLogger= (Logger) LogManager.getLogger(UPS.class);
    private DatabaseConnection conn=DatabaseConnection.getInstance();;
    private static UPS upsInstance=null;
    private String upsRegNum;
    private String model="No";
    private String status;
    private String userName="No";
    private String purchasedFrom;//***************

    private UPS() {
    }

    public static UPS getUpsInstance() {
        if(upsInstance==null){
            upsInstance=new UPS();
            return upsInstance;
        }
        return upsInstance;
    }

    @Override
    public String getRegNum() {
        return upsRegNum;
    }
    @Override
    public void setRegNum(String upsRegNum) {
        this.upsRegNum = upsRegNum;
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

    public String getPurchasedFrom() {
        return purchasedFrom;
    }
    public void setPurchasedFrom(String purchasedFrom) {
        this.purchasedFrom = purchasedFrom;
    }

    //get the Desktop array from the database
    //for updating cards
    @Override
    public UPS[] getDevices() {
        List<UPS> ups = new ArrayList<>();
        //pass query to the connection class
        String sql = "SELECT * FROM Ups";

        try {
            // get result set from connection class
            ResultSet resultSet = conn.executeSt(sql);

            // Iterate through the result set and create Desktop and DeviceUser objects
            while (resultSet.next()) {
                UPS ups1 = new UPS();

                ups1.setRegNum(resultSet.getString("UpsRegNum"));
                ups1.setModel(resultSet.getString("model"));
                ups1.setStatus(resultSet.getString("status"));

                ups.add(ups1);
            }
        }
        catch (SQLException e){
            sqlLogger.error(e.getMessage());
            alerting(Alert.AlertType.WARNING,"Error Updating Device","An error occurred while updating the device.",e.getMessage());
        }

        return ups.toArray(new UPS[0]);
    }
    @Override
    public UPS getDevice(String upsRegNum) {
        //pass query to the connection class
        String sql = "SELECT * FROM ups Where upsRegNum=?";

        try {
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ps.setString(1, upsRegNum);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                UPS ups = new UPS();
                ups.setRegNum(rs.getString("upsRegNum"));
                ups.setModel(rs.getString("model"));
                ups.setStatus(rs.getString("status"));
                ups.setPurchasedFrom(rs.getString("purchasedFrom"));

                return ups;
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
        String sql="UPDATE ups SET model=?,status=?,purchasedFrom=? WHERE upsRegNum=?";
        return dbInteraction(sql,list,list.getLast());
    }
    public boolean insertDevice(ArrayList<String> list){
        //pass query to the connection class
        String sql="INSERT INTO ups (upsRegNum,model,status,purchasedFrom)" +
                "VALUES (?,?,?,?)";
        return dbInteraction(sql,list, list.getFirst());

    }

    public ArrayList<String> getAllUps(){
        ArrayList<String> List=new ArrayList<>();
        String sql = "SELECT upsRegNum FROM ups";

        try (ResultSet resultSet = conn.executeSt(sql)) {// get result set from connection class and auto closable

            while (resultSet.next()) {
                List.add(resultSet.getString(1)) ;
            }

        } catch (SQLException e) {
            sqlLogger.error(e.getMessage());
            alerting(Alert.AlertType.WARNING,"Error Updating Device","An error occurred while updating the device.",e.getMessage());
        }

        return List;
    }

}
