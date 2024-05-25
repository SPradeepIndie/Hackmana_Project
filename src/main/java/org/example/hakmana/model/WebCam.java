package org.example.hakmana.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Data
//@AllArgsConstructor
//NoArgsConstructor
public class WebCam extends Devices{
    private String regNum;//Database have DesRegNum
    private String model;
    private String status;
    private String userName;

    public WebCam(String regNum, String model, String userName, String status) {
        super(regNum, model, userName, status);
    }

    public WebCam() {
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



    public WebCam[] getDevices() {
        DatabaseConnection conn=DatabaseConnection.getInstance();
        List<WebCam> webCams = new ArrayList<>();
        //pass query to the connection class
        String sql = "SELECT WebCam.*, userNIC.name FROM WebCam LEFT JOIN deviceUser ON WebCam.userNIC = DeviceUser.userNIC";

        try {
            // get result set from connection class
            ResultSet resultSet = conn.executeSt(sql);

            // Iterate through the result set and create Desktop and DeviceUser objects
            while (resultSet.next()) {
                WebCam webCam = new WebCam();

                webCam.setRegNum(resultSet.getString("WebCamRegNum"));
                webCam.setModel(resultSet.getString("model"));
                webCam.setStatus(resultSet.getString("status"));
                webCam.setStatus(resultSet.getString("name"));

                webCams.add(webCam);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return webCams.toArray(new WebCam[0]);
    }
}
