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
public class Desktop extends Devices {
    private DatabaseConnection conn=DatabaseConnection.getInstance();
    private String regNum;
    private String model;
    private String status;
    private String userName;


    private String serialNum = "NO";
    private String purchasedFrom = "NO";
    private String ram = "NO";
    private String processor = "NO";
    private String hardDisk = "NO";
    private String os = "NO";
    private String monitorRegNum = "NO";
    private String speakerRegNum = "NO";
    private String mouseRegNum = "NO";
    private String keyboardRegNum = "NO";
    private String soundCard = "NO";
    private String tvCard = "NO";
    private String networkCard = "NO";
    private String micRegNum = "NO";
    private String userNIC = "No DeviceUser";
    private String floppyDisk = "NO";
    private String scannerRegNum = "NO";

    public Desktop(String regNum, String model, String userName, String status, String serialNum, String purchasedFrom, String ram, String processor, String hardDisk, String os, String floppyDisk, String soundCard, String tvCard, String networkCard, String monitorRegNum, String speakerRegNum, String mouseRegNum, String keyboardRegNum, String micRegNum, String scannerRegNum, String userNIC) {
        super(regNum, model, userName, status);
        this.floppyDisk = floppyDisk;
        this.soundCard = soundCard;
        this.tvCard = tvCard;
        this.networkCard = networkCard;
        this.serialNum = serialNum;
        this.purchasedFrom = purchasedFrom;
        this.ram = ram;
        this.processor = processor;
        this.hardDisk = hardDisk;
        this.os = os;
        this.monitorRegNum = monitorRegNum;
        this.speakerRegNum = speakerRegNum;
        this.mouseRegNum = mouseRegNum;
        this.keyboardRegNum = keyboardRegNum;
        this.micRegNum = micRegNum;
        this.scannerRegNum = scannerRegNum;
        this.userNIC = userNIC;
    }

    public Desktop(String regNum, String model, String userName, String status) {
        super(regNum, model, userName, status);
    }

    public Desktop() {
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
    public void setModel(String model) {this.model = model;}
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

    public String getPurchasedFrom() {
        return purchasedFrom;
    }

    public void setPurchasedFrom(String purchasedFrom) {
        this.purchasedFrom = purchasedFrom;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getHardDisk() {
        return hardDisk;
    }

    public void setHardDisk(String hardDisk) {
        this.hardDisk = hardDisk;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getMonitorRegNum() {
        return monitorRegNum;
    }

    public void setMonitorRegNum(String monitorRegNum) {
        this.monitorRegNum = monitorRegNum;
    }

    public String getSpeakerRegNum() {
        return speakerRegNum;
    }

    public void setSpeakerRegNum(String speakerRegNum) {
        this.speakerRegNum = speakerRegNum;
    }

    public String getMouseRegNum() {
        return mouseRegNum;
    }

    public void setMouseRegNum(String mouseRegNum) {
        this.mouseRegNum = mouseRegNum;
    }

    public String getKeyboardRegNum() {
        return keyboardRegNum;
    }

    public void setKeyboardRegNum(String keyboardRegNum) {
        this.keyboardRegNum = keyboardRegNum;
    }

    public String getSoundCard() {
        return soundCard;
    }

    public void setSoundCard(String soundCard) {
        this.soundCard = soundCard;
    }

    public String getTvCard() {
        return tvCard;
    }

    public void setTvCard(String tvCard) {
        this.tvCard = tvCard;
    }

    public String getNetworkCard() {
        return networkCard;
    }

    public void setNetworkCard(String networkCard) {
        this.networkCard = networkCard;
    }

    public String getMicRegNum() {
        return micRegNum;
    }

    public void setMicRegNum(String micRegNum) {
        this.micRegNum = micRegNum;
    }

    public String getUserNIC() {
        return userNIC;
    }

    public void setUserNIC(String userNIC) {
        this.userNIC = userNIC;
    }

    public String getFloppyDisk() {
        return floppyDisk;
    }

    public void setFloppyDisk(String floppyDisk) {
        this.floppyDisk = floppyDisk;
    }

    public String getScannerRegNum() {
        return scannerRegNum;
    }

    public void setScannerRegNum(String scannerRegNum) {
        this.scannerRegNum = scannerRegNum;
    }

    //get the Desktop array from the database
    //for updating cards
    @Override
    public Desktop[] getDevices() {
        List<Desktop> desktops = new ArrayList<>();
        //pass query to the connection class
        String sql = "SELECT Desktop.DesRegNum,Desktop.model,Desktop.status,DeviceUser.name FROM desktop LEFT JOIN deviceUser ON Desktop.userNIC = DeviceUser.userNIC";

        try (ResultSet resultSet = conn.executeSt(sql)) {// get result set from connection class and auto closable

            // Iterate through the result set and create Desktop and DeviceUser objects
            while (resultSet.next()) {
                Desktop desktop = new Desktop(null, null, null, null);
                desktop.setRegNum(resultSet.getString("DesRegNum"));
                desktop.setModel(resultSet.getString("model"));
                desktop.setStatus(resultSet.getString("status"));
                desktop.setUserName(resultSet.getString("name"));

                desktops.add(desktop);//add desktop to the desktops list
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //return desktops list as an array
        return desktops.toArray(new Desktop[0]);
    }

    //for get special device from the database
    @Override
    public Desktop getDevice(String regNum) {
        //pass query to the connection class
        String sql = "SELECT * FROM desktop Where DesRegNum=?";

        try {
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ps.setString(1, regNum);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Desktop desktop = new Desktop();
                desktop.setRegNum(rs.getString("DesRegNum"));
                desktop.setModel(rs.getString("model"));
                desktop.setStatus(rs.getString("status"));
                desktop.setSerialNum(rs.getString("serialNum"));
                desktop.setPurchasedFrom(rs.getString("purchasedFrom"));
                desktop.setRam(rs.getString("ram"));
                desktop.setProcessor(rs.getString("processor"));
                desktop.setHardDisk(rs.getString("hardDisk"));
                desktop.setOs(rs.getString("os"));
                desktop.setFloppyDisk(rs.getString("floppyDisk"));
                desktop.setSoundCard(rs.getString("soundCard"));
                desktop.setTvCard(rs.getString("tvCard"));
                desktop.setNetworkCard(rs.getString("networkCard"));
                desktop.setMonitorRegNum(rs.getString("monitorRegNum"));
                desktop.setSpeakerRegNum(rs.getString("speakerRegNum"));
                desktop.setMouseRegNum(rs.getString("mouseRegNum"));
                desktop.setKeyboardRegNum(rs.getString("keyboardRegNum"));
                desktop.setMicRegNum(rs.getString("micRegNum"));
                desktop.setScannerRegNum(rs.getString("scannerRegNum"));
                desktop.setUserNIC(rs.getString("userNIC"));

                return desktop;
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
        String sql="UPDATE desktop SET model=?,status=?,serialNum=?,purchasedFrom=?,ram=?," +
                "processor=?,hardDisk=?,os=?,floppyDisk=?,soundCard=?,tvCard=?,networkCard=?,monitorRegNum=?," +
                "speakerRegNum=?,mouseRegNum=?,keyboardRegNum=?,micRegNum=?,scannerRegNum=?" +
                "WHERE DesRegNum=?";
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
            alert.setContentText("Update "+ i+" rows desktop registration number " +list.get(20));

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
    public boolean updateDeviceUser(String userNic,String id){
        Connection connection= conn.getConnection();
        //pass query to the connection class
        String sql="UPDATE desktop SET userNIC=? WHERE DesRegNum=?";
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,userNic);
            ps.setString(2,id);

            ps.executeUpdate();

            //Check confirmation to change
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setContentText("Update deviceUser desktop registration number " +id);

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
            alert.setTitle("Error Updating DeviceUser");
            alert.setHeaderText("An error occurred while updating the device deviceUser.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        return false;
    }
    public boolean insertDevice(ArrayList<String> list){
        Connection connection= conn.getConnection();
        //pass query to the connection class
        String sql="INSERT INTO desktop (DesRegNum,model,status,serialNum,purchasedFrom,ram," +
                "processor,hardDisk,os,floppyDisk,soundCard,tvCard,networkCard,monitorRegNum," +
                "speakerRegNum,mouseRegNum,keyboardRegNum,micRegNum,scannerRegNum,userNIC)" +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
