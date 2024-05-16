package org.example.hakmana;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.example.hakmana.model.DatabaseConnection;
import org.example.hakmana.model.DeviceUser;

import java.net.URL;
import java.util.ResourceBundle;

public class UserAssignDialogController implements Initializable {
    @FXML
    private TextField gmailTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField nicTextField;

    @FXML
    private TextField titleTextField;
    @FXML
    private Button assignUserButton;
    private DeviceUser user;

    public static boolean isAssignUserButtonClicked = false;



    public void assignUserButtonOnAction(ActionEvent event) {
        user=new DeviceUser();
        user.setNic(nicTextField.getText());
        user.setName(nameTextField.getText());
        user.setTitle(nameTextField.getText());
        user.setGmail(gmailTextField.getText());

        DesktopFormController.user=user;

        ((Node) event.getSource()).setDisable(true);
        isAssignUserButtonClicked = true;

    }
    private DeviceUser isNicAvailable(String nic) {
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
        DeviceUser[] users = databaseConnection.getUsers();

        for (DeviceUser user : users) {
            if (user.getNic().equalsIgnoreCase(nic)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nicTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            // Enable the submitButton only if regNumTextField is not empty
            assignUserButton.setDisable(newValue.isEmpty());
        });
        nicTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            // Enable the submitButton only if regNumTextField is not empty
            assignUserButton.setDisable(newValue.isEmpty());

            // Check if the newValue is available in the users array
            DeviceUser user = isNicAvailable(newValue);
            if (user != null) {
                // Auto-fill the other text fields
                gmailTextField.setText(user.getGmail());
                nameTextField.setText(user.getName());
                titleTextField.setText(user.getTitle());
            }
        });

        // Disable the submitButton initially if regNumTextField is empty
        assignUserButton.setDisable(nicTextField.getText().isEmpty());
    }
}
