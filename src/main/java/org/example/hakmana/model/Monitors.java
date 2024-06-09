package org.example.hakmana.model;

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

public class Monitors extends Devices{
    private DatabaseConnection conn=DatabaseConnection.getInstance();;
    private String regNum;
    private String model;
    private String status;
    private String userName;

    private String screenSize;
    private String purchasedFrom;

    public Monitors(String regNum, String model, String userName, String status, String screenSize) {
        super(regNum, model, userName, status);
        this.screenSize = screenSize;
    }

    public Monitors(String regNum, String model, String userName, String status) {
        super(regNum, model, userName,status);
    }

    public Monitors() {
    }

    @Override
    public void setRegNum(String para1) {

    }

    @Override
    public String getRegNum() {
        return null;
    }

    @Override
    public void setModel(String para1) {

    }

    @Override
    public String getModel() {
        return null;
    }

    @Override
    public String getUserName() {
        return null;
    }

    @Override
    public void setUserName(String para1) {

    }

    @Override
    public void setStatus(String para1) {

    }

    @Override
    public String getStatus() {
        return null;
    }

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
            throw new RuntimeException(e);
        }

        return monitors.toArray(new Monitors[0]);
    }
    @Override
    public Monitors getDevice(String regNum) {
        //pass query to the connection class
        String sql = "SELECT * FROM monitors Where MonitoRegNum=?";

        try {
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ps.setString(1, regNum);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Monitors monitors = new Monitors();
                monitors.setRegNum(rs.getString("regNum"));
                monitors.setModel(rs.getString("model"));
                monitors.setStatus(rs.getString("status"));

                return monitors;
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
        String sql="UPDATE monitors SET model=?,status=? WHERE MonitoRegNum=?";
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
            alert.setContentText("Update "+ i+" rows desktop registration number " +list.get(3));

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
        Connection connection= conn.getConnection();
        //pass query to the connection class
        String sql="INSERT INTO monitors (MonitoRegNum,model,status)" +
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
