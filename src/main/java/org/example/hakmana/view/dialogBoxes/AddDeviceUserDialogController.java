package org.example.hakmana.view.dialogBoxes;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.example.hakmana.model.userMngmnt.DeviceUser;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddDeviceUserDialogController implements Initializable {
    private static AddDeviceUserDialogController instance =null;
    private static final Logger otherErrorLogger= (Logger) LogManager.getLogger(AddDeviceDialogController.class);

    @FXML
    public TextField userNIC;
    @FXML
    public TextField userTitle;
    @FXML
    public TextField userName;
    @FXML
    public TextField userGmail;
    @FXML
    public Button addUserButton;

    public static DeviceUser deviceUser;

    private AddDeviceUserDialogController() {
    }

    public static AddDeviceUserDialogController getInstance() {
        if(instance==null){
            instance=new AddDeviceUserDialogController();
            return instance;
        }
        return instance;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Initially disable the user adding button
        addUserButton.setDisable(true);

        //Realtime get the Users
        userNIC.textProperty().addListener((observable, oldValue, newValue) -> {
            // Enable the submitButton only if regNumTextField is not empty
            addUserButton.setDisable(newValue.isEmpty());

            // Check if the newValue is available in the users array
            DeviceUser deviceUser =DeviceUser.getDeviceUserInstance().isNicAvailable(newValue);
            if (deviceUser != null) {
                // Autofill the other text fields
                userGmail.setText(deviceUser.getGmail());
                userName.setText(deviceUser.getName());
                userTitle.setText(deviceUser.getTitle());
            }
        });
    }
    /*------------------------Interactions with DeviceUser TABLE-----------------------------------*/
    private void alert(Alert.AlertType alertType,String title,String content){
        Alert alert=new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();

    }
    @FXML
    private void addUser(){
        deviceUser =DeviceUser.getDeviceUserInstance();
        if(deviceUser.isNicAvailable(userNIC.getText()) == null) {
                //add new deviceUser to the deviceUser table
                deviceUser.insertUser(new ArrayList<>(List.of(userNIC.getText(), userName.getText(), userTitle.getText(), userGmail.getText())));
                userNIC.clear();
                userName.clear();
                userTitle.clear();
                userGmail.clear();
                otherErrorLogger.info("new deviceUser is added / user:"+userNIC.getText());
        }

    }
}
