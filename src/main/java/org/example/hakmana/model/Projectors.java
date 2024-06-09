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

public class Projectors extends Devices{
    private DatabaseConnection conn =DatabaseConnection.getInstance();;
    private String regNum;
    private String model;
    private String status;

    public Projectors(String regNum, String model, String name, String status) {
        super(regNum, model, name, status);
    }

    public Projectors() {
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
            System.out.println(e);
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

            while (rs.next()) {
                Projectors multimediaprojector = new Projectors();
                multimediaprojector.setRegNum(rs.getString("MultimediaProjectorRegNum"));
                multimediaprojector.setModel(rs.getString("model"));
                multimediaprojector.setStatus(rs.getString("status"));

                return multimediaprojector;
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
        String sql="UPDATE multimediaprojector SET model=?,status=? WHERE regNUM=?";
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
            alert.setContentText("Update "+ i+" rows desktop registration number " +list.get(2));

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
        String sql="INSERT INTO multimediaprojector (MultimediaProjectorRegNum,model,status) VALUES (?,?,?)";
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
