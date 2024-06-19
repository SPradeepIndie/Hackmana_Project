package org.example.hakmana.model.mainDevices;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import org.example.hakmana.model.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PhotocpyMchine extends Devices {
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
            throw new RuntimeException(e);
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

            while (rs.next()) {
                PhotocpyMchine PhotoCopyMachine = new PhotocpyMchine();
                PhotoCopyMachine.setRegNum(rs.getString("PhotoCopyMachineRegNum"));
                PhotoCopyMachine.setModel(rs.getString("model"));
                PhotoCopyMachine.setStatus(rs.getString("status"));

                return PhotoCopyMachine;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //return null if there is no result
        return null;
    }
    public boolean updateDevice(ArrayList<String> list){
        Connection connection= conn.getConnection();
        //pass query to the connection class
        String sql="UPDATE PhotoCopyMachine SET model= ?, status= ? WHERE PhotoCopyMachineRegNum=?";
        try {
            connection.setAutoCommit(false);

            int i=1;
            PreparedStatement ps = connection.prepareStatement(sql);
            for(String l:list){
                ps.setString(i,l);
                i++;
            }

            i=ps.executeUpdate();

            //Check confirmation to change
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setContentText("Update "+ i+" rows Photocopy Machine registration number " +list.get(3));

            Optional<ButtonType> alertResult = alert.showAndWait();//wait until button press in alert box

            //if alert box ok pressed execute sql quires
            if (alertResult.isPresent() && alertResult.get() == ButtonType.OK) {
                // commit the sql quires
                connection.commit();
                connection.setAutoCommit(true);
                return true;
            } else {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

        } catch (SQLException e) {
            // Rollback the transaction on error
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Updating Device");
            alert.setHeaderText("An error occurred while updating the device.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        return false;
    }
    public boolean insertDevice(ArrayList<String> list) {
        Connection connection = conn.getConnection();
        //pass query to the connection class
        String sql = "INSERT INTO PhotoCopyMachine (PhotoCopyMachineRegNum,model,status) VALUES (?,?,?)";
        try {
            connection.setAutoCommit(false);

            int i = 1;
            PreparedStatement ps = connection.prepareStatement(sql);
            for (String l : list) {
                ps.setString(i, l);
                i++;
            }

            i = ps.executeUpdate();

            //Check confirmation to change
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setContentText("Update " + i + " rows desktop registration number " + list.getFirst());

            Optional<ButtonType> alertResult = alert.showAndWait();//wait until button press in alert box

            //if alert box ok pressed execute sql quires
            if (alertResult.isPresent() && alertResult.get() == ButtonType.OK) {
                // commit the sql quires
                connection.commit();
                connection.setAutoCommit(true);
                return true;
            } else {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

        } catch (SQLException e) {
            // Rollback the transaction on error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Updating Device");
            alert.setHeaderText("An error occurred while updating the device.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        return false;
    }
}
