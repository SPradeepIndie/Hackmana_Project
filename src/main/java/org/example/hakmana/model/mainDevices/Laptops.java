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

public class Laptops extends Devices{
    private static final Logger sqlLogger= (Logger) LogManager.getLogger(Laptops.class);
    private DatabaseConnection conn=DatabaseConnection.getInstance();
    private static Laptops laptopsInstance=null;
    private String laptopRegNum;
    private String model;
    private String status;
    private String userName;
    private String ram = "NO";
    private String cpu = "NO";
    private String storage;
    private String os = "NO";
    private String purchasedFrom = "NO";//**********
    private String mouseRegNum = "NO";//**********
    private String keyboardRegNum = "NO";//**********
    private String userNIC = "No DeviceUser";

    private Laptops() {
    }

    public static Laptops getLaptopsInstance() {
        if(laptopsInstance==null){
            laptopsInstance=new Laptops();
            return laptopsInstance;
        }
        return laptopsInstance;
    }

    @Override
    public String getRegNum() {
        return laptopRegNum;
    }
    @Override
    public void setRegNum(String laptopRegNum) {
        this.laptopRegNum = laptopRegNum;
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

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getUserNIC() {
        return userNIC;
    }

    public void setUserNIC(String userNIC) {
        this.userNIC = userNIC;
    }

    public String getPurchasedFrom() {
        return purchasedFrom;
    }

    public void setPurchasedFrom(String purchasedFrom) {
        this.purchasedFrom = purchasedFrom;
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

    //get the Desktop array from the database
    //for updating cards
    @Override
    public Laptops[] getDevices() {
        List<Laptops> laptops = new ArrayList<>();
        //pass query to the connection class
        String sql = "SELECT Laptop.LaptopRegNum,Laptop.model,Laptop.status, DeviceUser.name FROM laptop LEFT JOIN DeviceUser ON Laptop.userNIC = DeviceUser.userNIC";

        try {
            // get result set from connection class
            ResultSet resultSet = conn.executeSt(sql);

            // Iterate through the result set and create Desktop and DeviceUser objects
            while (resultSet.next()) {
                Laptops laptop = new Laptops();

                laptop.setRegNum(resultSet.getString("LaptopRegNum"));
                laptop.setModel(resultSet.getString("model"));
                laptop.setStatus(resultSet.getString("status"));
                laptop.setUserName(resultSet.getString("name"));

                laptops.add(laptop);//add laptop to the laptops list
            }
        }
        catch (SQLException e){
            sqlLogger.error(e.getMessage());
            alerting(Alert.AlertType.WARNING,"Error Updating Device","An error occurred while updating the device.",e.getMessage());
        }

        return laptops.toArray(new Laptops[0]);
    }
    @Override
    public Laptops getDevice(String laptopRegNum) {
        //pass query to the connection class
        String sql = "SELECT * FROM laptop Where LaptopRegNum=?";

        try {
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ps.setString(1, laptopRegNum);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Laptops laptop = new Laptops();
                laptop.setRegNum(rs.getString("LaptopRegNum"));
                laptop.setModel(rs.getString("model"));
                laptop.setStatus(rs.getString("status"));
                laptop.setRam(rs.getString("ram"));
                laptop.setCpu(rs.getString("processor"));
                laptop.setStorage(rs.getString("hardDisk"));
                laptop.setOs(rs.getString("os"));
                laptop.setPurchasedFrom(rs.getString("purchasedForm"));
                laptop.setMouseRegNum(rs.getString("mouseRegNum"));
                laptop.setKeyboardRegNum(rs.getString("keyboardRegNum"));
                laptop.setUserNIC(rs.getString("userNIC"));

                return laptop;
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
        String sql="UPDATE laptop SET model=?,status=?,purchasedForm=?,processor=?,hardDisk=?,ram=?,os=?,mouseRegNum=?,keyboardRegNum=? WHERE LaptopRegNum=?";
        return  dbInteraction(sql,list,list.getLast());

    }
    public void updateDeviceUser(String userNic,String id) {
        //pass query to the connection class
        String sql = "UPDATE laptop SET userNIC=? WHERE LaptopRegNum=?";
        dbInteraction(sql,new ArrayList<>(List.of(userNic,id)),id);

    }
    public boolean insertDevice(ArrayList<String> list){
        //pass query to the connection class
        String sql="INSERT INTO laptop (LaptopRegNum,model,status,purchasedForm,processor,hardDisk,ram,os,mouseRegNum,keyboardRegNum,userNIC)" +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        return dbInteraction(sql,list, list.getFirst());
    }

    public ArrayList<String> getMousesRegNum() {
        String sql="Select mouseRegNum From mouse";
        String colName="mouseRegNum";
        return regNumbGetQueryExecute(sql,colName);
    }

    public ArrayList<String> getKeyboardsRegNum() {
        String sql="Select KeyboardRegNum From Keyboard";
        String colName="KeyboardRegNum";
        return regNumbGetQueryExecute(sql,colName);
    }

    public ArrayList<String> getAllLaptops(){
        ArrayList<String> laptopList=new ArrayList<>();
        String sql = "SELECT LaptopRegNum FROM laptop";

        try (ResultSet resultSet = conn.executeSt(sql)) {// get result set from connection class and auto closable

            while (resultSet.next()) {
                laptopList.add(resultSet.getString(1)) ;
            }
        } catch (SQLException e) {
            sqlLogger.error(e.getMessage());
            alerting(Alert.AlertType.WARNING,"Error Updating Device","An error occurred while updating the device.",e.getMessage());
        }

        return laptopList;
    }
}
