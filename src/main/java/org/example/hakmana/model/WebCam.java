package org.example.hakmana.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebCam extends Devices{
    private String regNum;//Database have DesRegNum
    private String model;
    private String status;
    private String userName;

    public WebCam[] getDevices() {
        DatabaseConnection conn=DatabaseConnection.getInstance();
        List<WebCam> webCams = new ArrayList<>();
        //pass query to the connection class
        String sql = "SELECT WebCam.*, userNIC.name FROM WebCam LEFT JOIN user ON WebCam.userNIC = DeviceUser.userNIC";

        try {
            // get result set from connection class
            ResultSet resultSet = conn.executeSt(sql);

            // Iterate through the result set and create Desktop and User objects
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
            System.out.println(e);
        }

        return webCams.toArray(new WebCam[0]);
    }
}
