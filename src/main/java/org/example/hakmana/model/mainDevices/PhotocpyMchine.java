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

public class PhotocpyMchine extends Devices {
    private static final Logger sqlLogger= (Logger) LogManager.getLogger(PhotocpyMchine.class);
    private DatabaseConnection conn=DatabaseConnection.getInstance();
    private static PhotocpyMchine photocpyMchineInstance=null;
    private String photoCopyRegNum;
    private String model;
    private String status;
    private String purchasedFrom = "NO";//**********
    private String userName="No User";

    private PhotocpyMchine() {
    }

    public static PhotocpyMchine getPhotocpyMchineInstance() {
        if(photocpyMchineInstance==null){
            photocpyMchineInstance=new PhotocpyMchine();
            return photocpyMchineInstance;
        }
        return photocpyMchineInstance;
    }

    @Override
    public String getRegNum() {
        return photoCopyRegNum;
    }
    @Override
    public void setRegNum(String photoCopyRegNum) {
        this.photoCopyRegNum = photoCopyRegNum;
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
    public void setUserName(String userName) {
        this.userName = userName;
    }
    @Override
    public String getUserName() {
        return userName;
    }

    public String getPhotoCopyRegNum() {
        return photoCopyRegNum;
    }

    public void setPhotoCopyRegNum(String photoCopyRegNum) {
        this.photoCopyRegNum = photoCopyRegNum;
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
    public PhotocpyMchine[] getDevices() {
        List<PhotocpyMchine> photocopyMachines = new ArrayList<>();
        //pass query to the connection class
        String sql = "SELECT PhotoCopyMachine.* FROM PhotoCopyMachine";

        try {
            // get result set from connection class
            ResultSet resultSet = conn.executeSt(sql);

            // Iterate through the result set and create Desktop and DeviceUser objects
            while (resultSet.next()) {
                PhotocpyMchine photocopyMachine = new PhotocpyMchine();

                photocopyMachine.setRegNum(resultSet.getString("PhotoCopyMachineRegNum"));
                photocopyMachine.setModel(resultSet.getString("model"));
                photocopyMachine.setStatus(resultSet.getString("status"));
                photocopyMachine.setUserName("no user");

                photocopyMachines.add(photocopyMachine);//add photocopyMachines to the photocopyMachins list
            }
        }
        catch (SQLException e){
            sqlLogger.error(e.getMessage());
            alerting(Alert.AlertType.WARNING,"Error Updating Device","An error occurred while updating the device.",e.getMessage());
        }

        return photocopyMachines.toArray(new PhotocpyMchine[0]);
    }
    @Override
    public PhotocpyMchine getDevice(String photoCopyRegNum) {
        //pass query to the connection class
        String sql = "SELECT * FROM PhotoCopyMachine Where PhotoCopyMachineRegNum=?";

        try {
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ps.setString(1, photoCopyRegNum);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                PhotocpyMchine PhotoCopyMachine = new PhotocpyMchine();
                PhotoCopyMachine.setRegNum(rs.getString("PhotoCopyMachineRegNum"));
                PhotoCopyMachine.setModel(rs.getString("model"));
                PhotoCopyMachine.setStatus(rs.getString("status"));
                PhotoCopyMachine.setPurchasedFrom(rs.getString("purchasedFrom"));

                return PhotoCopyMachine;
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
        String sql="UPDATE PhotoCopyMachine SET model= ?, status= ? , purchasedFrom=? WHERE PhotoCopyMachineRegNum=?";
        return dbInteraction(sql,list,list.getLast());
    }
    public boolean insertDevice(ArrayList<String> list) {
        //pass query to the connection class
        String sql = "INSERT INTO PhotoCopyMachine (PhotoCopyMachineRegNum,model,status,purchasedFrom) VALUES (?,?,?,?)";
        return dbInteraction(sql,list, list.getFirst());
    }
}
