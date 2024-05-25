package org.example.hakmana.model;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
//@NoArgsConstructor
@AllArgsConstructor
public class PhotocpyMchine extends Devices {
    private DatabaseConnection conn;
    private String regNum;
    private String model;
    private String status;
    private String userName;
    private String CopyingCapability;

    public PhotocpyMchine(String regNum, String model, String userName, String status, String copyingCapability) {
        super(regNum, model, userName, status);
        CopyingCapability = copyingCapability;
    }

    public PhotocpyMchine(String regNum, String model, String userName, String status) {
        super(regNum, model, userName, status);
    }

    public PhotocpyMchine() {
    }

    @Override
    public String getRegNum() {
        return regNum;
    }
    @Override
    public void setRegNum(String regNum) {
        this.regNum = regNum;
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

    private String purchasedFrom;

    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCopyingCapability() {
        return CopyingCapability;
    }

    public void setCopyingCapability(String copyingCapability) {
        CopyingCapability = copyingCapability;
    }
    @Override
    public PhotocpyMchine[] getDevices() {
       conn=DatabaseConnection.getInstance();
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

                photocopyMachines.add(photocopyMachine);//add photocopyMachines to the photocopyMachins list
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

        return photocopyMachines.toArray(new PhotocpyMchine[0]);
    }
    @Override
    public PhotocpyMchine getDevice(String regNum) {
        conn = DatabaseConnection.getInstance();
        //pass query to the connection class
        String sql = "SELECT * FROM PhotoCopyMachine Where regNum=?";

        try {
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ps.setString(1, regNum);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PhotocpyMchine PhotoCopyMachine = new PhotocpyMchine();
                PhotoCopyMachine.setRegNum(rs.getString("regNum"));
                PhotoCopyMachine.setModel(rs.getString("model"));
                PhotoCopyMachine.setStatus(rs.getString("status"));
                PhotoCopyMachine.setCopyingCapability(rs.getString("CopyingCapability"));

                return PhotoCopyMachine;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //return null if there is no result
        return null;
    }
    public boolean updateDevice(ArrayList<String> list){
        conn = DatabaseConnection.getInstance();
        Connection connection= conn.getConnection();
        //pass query to the connection class
        String sql="UPDATE PhotoCopyMachine SET model= ?, status= ?, CopyingCapability= ? WHERE regNUM=?";
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
        conn = DatabaseConnection.getInstance();
        Connection connection = conn.getConnection();
        //pass query to the connection class
        String sql = "INSERT INTO PhotoCopyMachine (regNum,model,status,CopyingCapability) VALUES (?,?,?,?)";
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
