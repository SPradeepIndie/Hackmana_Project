package org.example.hakmana.model.mainDevices;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.example.hakmana.model.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public abstract class Devices {
    private final DatabaseConnection conn=DatabaseConnection.getInstance();
    private String regNum;
    private String model="No";
    private String name;
    private String status;
    public Devices(String regNum, String model, String name, String status) {
        this.regNum = regNum;
        this.model = model;
        this.name = name;
        this.status = status;
    }
    public Devices() {
    }

    //get the Devices array from the database
    public  Devices[] getDevices(){
        return null;
    }

    public Devices getDevice(String regNum){return null;}

    public abstract void setRegNum(String para1);
    public abstract String getRegNum();

    public abstract void setModel(String para1);
    public abstract String getModel();

    public abstract String getUserName();
    public abstract void setUserName(String para1);

    public abstract void setStatus(String para1);
    public abstract String getStatus();

    public Optional<ButtonType> alerting(Alert.AlertType alertType, String title, String header, String content){
        Alert alert=new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        return alert.showAndWait();
    }

    private void changesCommit(Connection conn, Optional<ButtonType> alertResult) throws SQLException {
        if (alertResult.isPresent() && alertResult.get() == ButtonType.OK) {
            conn.commit();
            return;
        }
        conn.rollback();
    }

    public void dbInteraction(String incompleteSql,ArrayList<String> valList,String deviceRegNum){
        Connection connection= conn.getConnection();
        try {
            connection.setAutoCommit(false);
            int i=1;
            PreparedStatement ps = connection.prepareStatement(incompleteSql);
            for(String l:valList){
                ps.setString(i,l);
                i++;
            }
            i= ps.executeUpdate();

            changesCommit(connection,alerting(Alert.AlertType.CONFIRMATION,"Confirmation","Desktop registration number"+deviceRegNum,"Update "+ i+" rows " ));
            connection.setAutoCommit(true);

        } catch (SQLException e) {
            // Rollback the transaction on error
            alerting(Alert.AlertType.WARNING,"Error Updating Device","An error occurred while updating the device.",e.getMessage());
        }
    }

    public ArrayList<String> regNumbGetQueryExecute(String qry,String regNumcolumnName){
        ArrayList<String> regNumbers=new ArrayList<>();
        try(ResultSet rs = conn.executeSt(qry)){
            while(rs.next()){
                regNumbers.add(rs.getString(regNumcolumnName));
            }
        }catch (SQLException e){
          alerting(Alert.AlertType.INFORMATION,"Unsuccessfully fetched data","","");
        }
        return regNumbers;
    }
}


