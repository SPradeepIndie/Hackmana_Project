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
public class Desktop extends Devices {
    private static final Logger sqlLogger= (Logger) LogManager.getLogger(Desktop.class);
    private final DatabaseConnection conn=DatabaseConnection.getInstance();
    private static Desktop desktopInstance=null;
    private String DesRegNum;
    private String model;
    private String status;
    private String userName;
    private String serialNum = "NO";
    private String purchasedFrom = "NO";
    private String ram = "NO";
    private String processor = "NO";
    private String hardDisk = "NO";
    private String os = "NO";
    private String floppyDisk = "NO";
    private String soundCard = "NO";
    private String tvCard = "NO";
    private String networkCard = "NO";
    private String ssd="No";//*********
    private String cdRom="No";//*********
    private String monitorRegNum = "NO";
    private String speakerRegNum = "NO";
    private String mouseRegNum = "NO";
    private String keyboardRegNum = "NO";
    private String micRegNum = "NO";
    private String scannerRegNum = "NO";
    private String printerRegNum = "NO";//*********
    private String upsRegNum = "NO";//*********
    private String powerSupplyRegNum = "NO";//*********
    private String userNIC = "No DeviceUser";

    private Desktop() {
    }

    public static Desktop getDesktopInstance() {
        if(desktopInstance==null){
            desktopInstance=new Desktop();
            return desktopInstance;
        }
        return desktopInstance;

    }
    @Override
    public String getRegNum() {
        return DesRegNum;
    }
    @Override
    public void setRegNum(String DesRegNum) {
        this.DesRegNum = DesRegNum;
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

    public String getSsd() {
        return ssd;
    }

    public void setSsd(String ssd) {
        this.ssd = ssd;
    }

    public String getCdRom() {
        return cdRom;
    }

    public void setCdRom(String cdRom) {
        this.cdRom = cdRom;
    }

    public String getPrinterRegNum() {
        return printerRegNum;
    }

    public void setPrinterRegNum(String printerRegNum) {
        this.printerRegNum = printerRegNum;
    }

    public String getUpsRegNum() {
        return upsRegNum;
    }

    public void setUpsRegNum(String upsRegNum) {
        this.upsRegNum = upsRegNum;
    }

    public String getPowerSupplyRegNum() {
        return powerSupplyRegNum;
    }

    public void setPowerSupplyRegNum(String powerSupplyRegNum) {
        this.powerSupplyRegNum = powerSupplyRegNum;
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
                Desktop desktop = new Desktop();
                desktop.setRegNum(resultSet.getString("DesRegNum"));
                desktop.setModel(resultSet.getString("model"));
                desktop.setStatus(resultSet.getString("status"));
                desktop.setUserName(resultSet.getString("name"));

                desktops.add(desktop);//add desktop to the desktops list
            }
        } catch (SQLException e) {
            sqlLogger.error(e.getMessage());
            alerting(Alert.AlertType.WARNING,"Error Updating Device","An error occurred while updating the device.",e.getMessage());
        }

        //return desktops list as an array
        return desktops.toArray(new Desktop[0]);
    }

    //for get special device from the database
    @Override
    public Desktop getDevice(String DesRegNum) {
        //pass query to the connection class
        String sql = "SELECT * FROM desktop Where DesRegNum=?";

        try {
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ps.setString(1, DesRegNum);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
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
                desktop.setSsd(rs.getString("ssd"));
                desktop.setCdRom(rs.getString("cdRom"));
                desktop.setUpsRegNum(rs.getString("upsRegNum"));
                desktop.setPowerSupplyRegNum(rs.getString("powerSupplyRegNum"));
                desktop.setMonitorRegNum(rs.getString("monitorRegNum"));
                desktop.setSpeakerRegNum(rs.getString("speakerRegNum"));
                desktop.setMouseRegNum(rs.getString("mouseRegNum"));
                desktop.setKeyboardRegNum(rs.getString("keyboardRegNum"));
                desktop.setPrinterRegNum(rs.getString("printerRegNum"));
                desktop.setMicRegNum(rs.getString("micRegNum"));
                desktop.setScannerRegNum(rs.getString("scannerRegNum"));
                desktop.setUserNIC(rs.getString("userNIC"));

                return desktop;
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
        String sql="UPDATE desktop SET model=?,status=?,purchasedFrom=?,serialNum=?,processor=?," +
                "hardDisk=?,ram=?,os=?,floppyDisk=?,soundCard=?,tvCard=?,networkCard=?,ssd=?,cdRom=?,"+
                "upsRegNum=?,powerSupplyRegNum=?,mouseRegNum=?,keyboardRegNum=?,micRegNum=?,scannerRegNum=?," +
                "monitorRegNum=?,speakerRegNum=?,printerRegNum=?" +
                "WHERE DesRegNum=?";

        return dbInteraction(sql,list,list.getLast());

    }
    public void updateDeviceUser(String userNic,String id){
        //pass query to the connection class
        String sql="UPDATE desktop SET userNIC=? WHERE DesRegNum=?";
        dbInteraction(sql,new ArrayList<>(List.of(userNic,id)),id);
    }
    public boolean insertDevice(ArrayList<String> list){
        //pass query to the connection class
        String sql="INSERT INTO desktop (DesRegNum,model,status,serialNum,purchasedFrom,processor," +
                "hardDisk,ram,os,floppyDisk,soundCard,tvCard,networkCard,ssd,cdRom,upsRegNum,powerSupplyRegNum," +
                "mouseRegNum,keyboardRegNum,micRegNum,scannerRegNum,"+
                "monitorRegNum,speakerRegNum,printerRegNum,userNIC)" +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        return dbInteraction(sql,list, list.getFirst());

    }

    public ArrayList<String> getUPSRegNums(){
        String sql="Select UpsRegNum From Ups";
        String colName="UpsRegNum";
        return regNumbGetQueryExecute(sql,colName);
    }
    public ArrayList<String> getPowerSuppliesRegNum(){
        String sql="Select PowerSupplyRegNum From PowerSupply";
        String colName="PowerSupplyRegNum";
        return regNumbGetQueryExecute(sql,colName);
    }

    public ArrayList<String> getMousesRegNum(){
        String sql="Select mouseRegNum From mouse";
        String colName="mouseRegNum";
        return regNumbGetQueryExecute(sql,colName);
    }

    public ArrayList<String> getKeyboardsRegNum() {
        String sql="Select KeyboardRegNum From Keyboard";
        String colName="KeyboardRegNum";
        return regNumbGetQueryExecute(sql,colName);
    }

    public ArrayList<String> getMicsRegNum() {
        String sql="Select MICRegNum From Mic";
        String colName="MICRegNum";
        return regNumbGetQueryExecute(sql,colName);
    }

    public ArrayList<String> getScannersRegNum() {
        String sql="Select ScannersRegNum From Scanners";
        String colName="ScannersRegNum";
        return regNumbGetQueryExecute(sql,colName);
    }

    public ArrayList<String> getMonitorsRegNum() {
        String sql="Select MonitorRegNum From Monitor";
        String colName="MonitorRegNum";
        return regNumbGetQueryExecute(sql,colName);
    }

    public ArrayList<String> getSpeakersRegNum() {
        String sql="Select SpeakerRegNum From Speaker";
        String colName="SpeakerRegNum";
        return regNumbGetQueryExecute(sql,colName);
    }

    public ArrayList<String> getPrintersRegNum() {
        String sql="Select printerRegNum From printer";
        String colName="PrinterRegNum";
        return regNumbGetQueryExecute(sql,colName);
    }
    public ArrayList<String> getAllDesktops(){
        ArrayList<String> desktopList=new ArrayList<>();
        String sql = "SELECT DesRegNum FROM desktop";

        try (ResultSet resultSet = conn.executeSt(sql)) {// get result set from connection class and auto closable

            while (resultSet.next()) {
                   desktopList.add(resultSet.getString(1)) ;
                }
        } catch (SQLException e) {
            sqlLogger.error(e.getMessage());
            alerting(Alert.AlertType.WARNING,"Error Updating Device","An error occurred while updating the device.",e.getMessage());
        }
        desktopList.add("all");
        return desktopList;
    }


}

