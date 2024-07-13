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

public class Printer extends Devices {
    private static final Logger sqlLogger= (Logger) LogManager.getLogger(Printer.class);
    private DatabaseConnection conn=DatabaseConnection.getInstance();
    private static Printer printerInstance=null;
    private String printerRegNum;
    private String model;
    private String status;
    private String serialNum;
    private String paperInput;
    private String paperOutput;
    private String purchasedFrom;//***************
    private String userName="No user";

    private Printer() {
    }

    public static Printer getPrinterInstance() {
        if(printerInstance==null){
            printerInstance=new Printer();
            return printerInstance;
        }
        return printerInstance;
    }

    @Override
    public String getRegNum() {
        return printerRegNum;
    }
    @Override
    public void setRegNum(String printerRegNum) {
        this.printerRegNum = printerRegNum;
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

    //get the Desktop array from the database
    //for updating cards
    @Override
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
            sqlLogger.error(e.getMessage());
            alerting(Alert.AlertType.WARNING,"Error Updating Device","An error occurred while updating the device.",e.getMessage());
        }

        return printers.toArray(new Printer[0]);
    }
    @Override
    public Printer getDevice(String printerRegNum) {
        //pass query to the connection class
        String sql = "SELECT * FROM printer Where PrinterRegNum=?";

        try {
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ps.setString(1, printerRegNum);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Printer printer = new Printer();
                printer.setRegNum(rs.getString("PrinterRegNum"));
                printer.setModel(rs.getString("model"));
                printer.setStatus(rs.getString("status"));
                printer.setSerialNum(rs.getString("serialNum"));
                printer.setPaperInput(rs.getString("paperInput"));
                printer.setPaperOutput(rs.getString("paperOutput"));
                printer.setPurchasedFrom(rs.getString("purchasedFrom"));

                return printer;
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
        String sql="UPDATE printer SET model=?,status=?,serialNum=?,paperInput=?,paperOutput=?,purchasedFROM=? WHERE PrinterRegNum=?";
        return dbInteraction(sql,list,list.getLast());
    }
    public boolean insertDevice(ArrayList<String> list){
        //pass query to the connection class
        String sql="INSERT INTO printer (PrinterRegNum,model,status,purchasedFROM,serialNum,paperInput,paperOutput)" +
                "VALUES (?,?,?,?,?,?,?)";
        return dbInteraction(sql,list, list.getFirst());
    }

    public ArrayList<String> getAllPrinters(){
        ArrayList<String> List=new ArrayList<>();
        String sql = "SELECT PrinterRegNum FROM printer";

        try (ResultSet resultSet = conn.executeSt(sql)) {// get result set from connection class and auto closable

            while (resultSet.next()) {
                List.add(resultSet.getString(1)) ;
            }
            resultSet.close();
        } catch (SQLException e) {
            sqlLogger.error(e.getMessage());
            alerting(Alert.AlertType.WARNING,"Error Updating Device","An error occurred while updating the device.",e.getMessage());
        }

        return List;
    }
}
