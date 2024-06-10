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

public class Laptops extends Devices{
    private DatabaseConnection conn=DatabaseConnection.getInstance();
    private static Laptops laptopsInstance=null;
    private String regNum;
    private String model;
    private String status;
    private String userName;

    private String ram = "NO";
    private String cpu = "NO";
    private String storage;
    private String os = "NO";
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

    public Laptops[] getDevices() {
        List<Laptops> laptops = new ArrayList<>();
        //pass query to the connection class
        String sql = "SELECT Laptop.LaptopRegNum,Laptop.model,Laptop.status, DeviceUser.name FROM laptop LEFT JOIN deviceUser ON Laptop.userNIC = DeviceUser.userNIC";

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
            throw new RuntimeException(e);
        }

        return laptops.toArray(new Laptops[0]);
    }
    @Override
    public Laptops getDevice(String regNum) {
        //pass query to the connection class
        String sql = "SELECT * FROM laptop Where LaptopRegNum=?";

        try {
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ps.setString(1, regNum);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Laptops laptop = new Laptops();
                laptop.setRegNum(rs.getString("LaptopRegNum"));
                laptop.setModel(rs.getString("model"));
                laptop.setStatus(rs.getString("status"));
                laptop.setRam(rs.getString("RAM"));
                laptop.setCpu(rs.getString("processor"));
                laptop.setStorage(rs.getString("hardDiak"));
                laptop.setOs(rs.getString("os"));
                laptop.setUserNIC(rs.getString("userNIC"));

                return laptop;
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
        String sql="UPDATE laptop SET model=?,status=?,RAM=?,processor=?,hardDisk=?,os=? WHERE LaptopRregNum=?";
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
            alert.setContentText("Update "+ i+" rows laptop registration number " +list.get(8));

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
    public boolean updateDeviceUser(String userNic,String id) {
        Connection connection = conn.getConnection();
        //pass query to the connection class
        String sql = "UPDATE laptop SET userNIC=? WHERE LaptopRegNum=?";
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, userNic);
            ps.setString(2, id);

            ps.executeUpdate();

            //Check confirmation to change
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setContentText("Update deviceUser laptop registration number " + id);

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
        String sql="INSERT INTO laptop (LaptopRegNum,model,status,ram,processor,hardDisk,os,userNIC)" +
                "VALUES (?,?,?,?,?,?,?,?)";
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
