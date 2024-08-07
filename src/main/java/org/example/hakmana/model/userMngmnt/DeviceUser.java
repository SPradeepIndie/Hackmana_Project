package org.example.hakmana.model.userMngmnt;

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

public class DeviceUser {
    private static final Logger sqlLogger = (Logger) LogManager.getLogger(DeviceUser.class);
    private static DeviceUser deviceUserInstance = null;
    private final DatabaseConnection dbconn = DatabaseConnection.getInstance();
    private final Connection connection = dbconn.getConnection();
    private String nic;
    private String name;
    private String title;
    private String gmail;

    private DeviceUser() {
    }

    public static DeviceUser getDeviceUserInstance() {
        if (deviceUserInstance == null) {
            deviceUserInstance = new DeviceUser();
            return deviceUserInstance;
        }
        return deviceUserInstance;
    }

    public ArrayList<String> list = new ArrayList<>();

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public DeviceUser getUser(String userNic) {
        try {
            String sql = "SELECT * FROM deviceuser WHERE userNIC=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, userNic);
            // Execute the SQL query and get the result set
            ResultSet resultSet = preparedStatement.executeQuery();

            // Iterate through the result set and create Desktop and DeviceUser objects
            while (resultSet.next()) {
                DeviceUser deviceUser = new DeviceUser();

                deviceUser.setNic(resultSet.getString("UserNIC"));
                deviceUser.setName(resultSet.getString("name"));
                deviceUser.setTitle(resultSet.getString("title"));
                deviceUser.setGmail(resultSet.getString("gmail"));

                return deviceUser;

            }
        } catch (SQLException e) {
            sqlLogger.error(e.getMessage());
            throw new RuntimeException(e);
        }

        return null;
    }

    public DeviceUser isNicAvailable(String nic) {
        DeviceUser[] deviceUsers = getUsers();
        for (DeviceUser deviceUser : deviceUsers) {
            if (deviceUser.getNic().equalsIgnoreCase(nic)) {
                return deviceUser;
            }
        }
        return null;
    }

    public DeviceUser[] getUsers() {
        List<DeviceUser> deviceUsers = new ArrayList<>();
        try {
            String sql = "SELECT * FROM deviceuser";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Execute the SQL query and get the result set
            ResultSet resultSet = preparedStatement.executeQuery();

            // Iterate through the result set and create Desktop and DeviceUser objects
            while (resultSet.next()) {
                DeviceUser deviceUser = new DeviceUser();

                deviceUser.setNic(resultSet.getString("UserNIC"));
                deviceUser.setName(resultSet.getString("name"));
                deviceUser.setTitle(resultSet.getString("title"));
                deviceUser.setGmail(resultSet.getString("gmail"));


                deviceUsers.add(deviceUser);
            }
        } catch (SQLException e) {
            sqlLogger.error(e.getMessage());
            throw new RuntimeException(e);
        }

        return deviceUsers.toArray(new DeviceUser[0]);
    }

    public void insertUser(ArrayList<String> newUser) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO deviceuser (userNIC, name, title, gmail) VALUES (?, ?, ?, ?)"
            );
            preparedStatement.setString(1, newUser.get(0));
            preparedStatement.setString(2, newUser.get(1));
            preparedStatement.setString(3, newUser.get(2));
            preparedStatement.setString(4, newUser.get(3));

            preparedStatement.executeUpdate();

            //Check confirmation to change
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("success");
            alert.setContentText("Added new deviceUser ");

            Optional<ButtonType> alertResult = alert.showAndWait();//wait until button press in alert box

        } catch (SQLException e) {
            sqlLogger.error("An sql error occur", e);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error adding DeviceUser");
            alert.setHeaderText("An error occurred while adding new deviceUser.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}

    //get all device users
//    public ArrayList<String> getAllUsers() {
//        try {
//            String sql = "SELECT name FROM deviceuser";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            // Execute the SQL query and get the result set
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            // Iterate through the result set and create Desktop and DeviceUser objects
//            while (resultSet.next()) {
//
//                list.add(resultSet.getString(1));
//            }
//        } catch (SQLException e) {
//            sqlLogger.error(e.getMessage());
//            throw new RuntimeException(e);
//        }
//        return list;
//    }
//}
