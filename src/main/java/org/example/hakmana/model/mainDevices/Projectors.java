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

public class Projectors extends Devices{
    private static final Logger sqlLogger= (Logger) LogManager.getLogger(Projectors.class);
    private DatabaseConnection conn =DatabaseConnection.getInstance();
    private static Projectors projectorsInstance=null;
    private String multimediaProjectorRegNum;
    private String model;
    private String status;
    private String userName;
    private String purchasedFrom;//***************

    private Projectors() {
    }

    public static Projectors getProjectorsInstance() {
        if(projectorsInstance==null){
            projectorsInstance=new Projectors();
            return projectorsInstance;
        }
        return projectorsInstance;
    }

    @Override
    public void setRegNum(String multimediaProjectorRegNum) {
        this.multimediaProjectorRegNum=multimediaProjectorRegNum;
    }
    @Override
    public String getRegNum() {
        return multimediaProjectorRegNum;
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
    public Projectors[] getDevices() {
        List<Projectors> projectors = new ArrayList<>();
        //pass query to the connection class
        String sql = "SELECT * FROM MultimediaProjector";

        try {
            // get result set from connection class
            ResultSet resultSet = conn.executeSt(sql);

            // Iterate through the result set and create Desktop and DeviceUser objects
            while (resultSet.next()) {
                Projectors projector = new Projectors();

                projector.setRegNum(resultSet.getString("MultimediaProjectorRegNum"));
                projector.setModel(resultSet.getString("model"));
                projector.setStatus(resultSet.getString("status"));
                projector.setUserName("no user");

                projectors.add(projector);
            }
        }
        catch (SQLException e){
            sqlLogger.error(e.getMessage());
            alerting(Alert.AlertType.WARNING,"Error Updating Device","An error occurred while updating the device.",e.getMessage());
        }

        return projectors.toArray(new Projectors[0]);
    }
    @Override
    public Projectors getDevice(String MultimediaProjectorRegNum) {
        //pass query to the connection class
        String sql = "SELECT * FROM multimediaprojector Where MultimediaProjectorRegNum=?";

        try {
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ps.setString(1, MultimediaProjectorRegNum);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Projectors multimediaprojector = new Projectors();
                multimediaprojector.setRegNum(rs.getString("MultimediaProjectorRegNum"));
                multimediaprojector.setModel(rs.getString("model"));
                multimediaprojector.setStatus(rs.getString("status"));
                multimediaprojector.setPurchasedFrom(rs.getString("purchasedFrom"));

                return multimediaprojector;
            }
        } catch (SQLException e) {
            sqlLogger.error(e.getMessage());
            alerting(Alert.AlertType.WARNING,"Error Updating Device","An error occurred while updating the device.",e.getMessage());
        }

        //return null if there is no result
        return null;
    }

    public void updateDevice(ArrayList<String> list){
        //pass query to the connection class
        String sql="UPDATE multimediaprojector SET model=?,status=?,purchasedFrom=? WHERE MultimediaProjectorRegNum=?";
        dbInteraction(sql,list,list.getLast());
    }
    public boolean insertDevice(ArrayList<String> list){
        //pass query to the connection class
        String sql="INSERT INTO multimediaprojector (MultimediaProjectorRegNum,model,status,purchasedFrom) VALUES (?,?,?,?)";
        return dbInteraction(sql,list, list.getFirst());
    }
}
