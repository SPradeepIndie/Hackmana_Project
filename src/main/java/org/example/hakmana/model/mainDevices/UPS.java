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


public class UPS extends Devices{
    private DatabaseConnection conn;
    private String regNum;
    private String model="No";
    private String status;
    private String userName;

    public UPS(String upsRegNum, String model, String userName, String status) {
        super(upsRegNum, model, userName, status);
    }

    public UPS() {
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
    @Override
    public String getUserName() {
        return userName;
    }
    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UPS[] getDevices() {
       conn=DatabaseConnection.getInstance();
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
            System.out.println(e);
        }

        return ups.toArray(new UPS[0]);
    }
    @Override
    public UPS getDevice(String regNum) {
        conn = DatabaseConnection.getInstance();
        //pass query to the connection class
        String sql = "SELECT * FROM ups Where upsRegNum=?";

        try {
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ps.setString(1, regNum);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                UPS ups = new UPS();
                ups.setRegNum(rs.getString("upsRegNum"));
                ups.setModel(rs.getString("model"));
                ups.setStatus(rs.getString("status"));

                return ups;
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
        String sql="UPDATE ups SET model=?,status=? WHERE upsRegNum=?";
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
            alert.setContentText("Update "+ i+" rows desktop registration number " +list.get(5));

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
    public boolean insertDevice(ArrayList<String> list){
        conn = DatabaseConnection.getInstance();
        Connection connection= conn.getConnection();
        //pass query to the connection class
        String sql="INSERT INTO ups (upsRegNum,model,status)" +
                "VALUES (?,?,?)";
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
            alert.setContentText("Update "+ i+" rows desktop registration number " +list.getFirst());

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

}
