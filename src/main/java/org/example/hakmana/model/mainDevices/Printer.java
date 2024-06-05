package org.example.hakmana.model.mainDevices;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import lombok.*;
import org.example.hakmana.model.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class Printer extends Devices {
    private DatabaseConnection conn=DatabaseConnection.getInstance();
    private String regNum;
    private String model;
    private String status;
    private String userName;

    private String serialNum;
    private String paperInput;
    private String paperOutput;
    private String purchasedFrom;


    public Printer(String regNum, String model, String name, String status) {
        super(regNum, model, name, status);
    }

    public Printer() {
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public String getPaperInput() {
        return paperInput;
    }

    public void setPaperInput(String paperInput) {
        this.paperInput = paperInput;
    }

    public String getPaperOutput() {
        return paperOutput;
    }

    public void setPaperOutput(String paperOutput) {
        this.paperOutput = paperOutput;
    }

    public String getPurchasedFrom() {
        return purchasedFrom;
    }

    public void setPurchasedFrom(String purchasedFrom) {
        this.purchasedFrom = purchasedFrom;
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

    public Printer[] getDevices() {
        List<Printer> printers = new ArrayList<>();
        //pass query to the connection class
        String sql = "SELECT Printer.PrinterRegNum,Printer.model,Printer.status FROM Printer";

        try {
            // get result set from connection class
            ResultSet resultSet = conn.executeSt(sql);

            // Iterate through the result set and create Desktop and DeviceUser objects
            while (resultSet.next()) {
                Printer printer = new Printer();
                printer.setRegNum(resultSet.getString("PrinterRegNum"));
                printer.setModel(resultSet.getString("model"));
                printer.setStatus(resultSet.getString("status"));
                printer.setUserName("no user");

                printers.add(printer);
            }
        }
        catch (SQLException e){
            System.out.println(e);
        }

        return printers.toArray(new Printer[0]);
    }
    @Override
    public Printer getDevice(String regNum) {
        //pass query to the connection class
        String sql = "SELECT * FROM printer Where PrinterRegNum=?";

        try {
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ps.setString(1, regNum);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Printer printer = new Printer();
                printer.setRegNum(rs.getString("PrinterRegNum"));
                printer.setModel(rs.getString("model"));
                printer.setStatus(rs.getString("status"));
                printer.setSerialNum(rs.getString("serialNum"));
                printer.setPaperInput(rs.getString("paperInput"));
                printer.setPaperOutput(rs.getString("paperOutput"));

                return printer;
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
        String sql="UPDATE printer SET model=?,status=?,serialNum=?,paperInput=?,paperOutput=? WHERE PrinterRegNum=?";
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
            alert.setContentText("Update "+ i+" rows desktop registration number " +list.get(6));

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
        String sql="INSERT INTO printer (PrinterRegNum,model,status,serialNum,paperInput,paperOutput)" +
                "VALUES (?,?,?,?,?,?)";
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
