package org.example.hakmana.view.dialogBoxes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.example.hakmana.model.DatabaseConnection;
import org.example.hakmana.view.scene.LoginPageController;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EditAccountController  implements Initializable{

    @FXML
    public TextField editNameField;
    @FXML
    public TextField editEmailField;
    @FXML
    public TextField editPhoneField;
    @FXML
    public Button updateProfileButton;
    @FXML
    public TextField editPostField;

    private PreparedStatement preparedStatement;

    private DatabaseConnection databaseConnection;

    private Connection connection;

    static void alertBox(ActionEvent event,String title,String content){
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
    public void updateProfileButtonOnAction(ActionEvent actionEvent) {
        try {
            String query = "UPDATE systemUser SET fullName=?, post=?, email=?, phoneNum=? WHERE userName=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, editNameField.getText());
            preparedStatement.setString(2, editPostField.getText());
            preparedStatement.setString(3, editEmailField.getText());
            preparedStatement.setString(4, editPhoneField.getText());
            preparedStatement.setString(5, LoginPageController.curentUser);
            preparedStatement.executeUpdate();

            alertBox(null,"","Updated Profile Succesfully!");

        } catch (SQLException e) {
            alertBox(null,"","Error!");
            e.printStackTrace();

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        databaseConnection = DatabaseConnection.getInstance();
        connection = databaseConnection.getConnection();

        try {
            // Query to retrieve deviceUser information
            String query = "SELECT * FROM systemUser WHERE userName = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, LoginPageController.curentUser);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println(LoginPageController.curentUser);

            if (resultSet.next()) {
                String storedFullName = resultSet.getString("fullName");
                String storedPost = resultSet.getString("post");
                String storedEmail = resultSet.getString("email");
                String storedPhoneNum = resultSet.getString("phoneNum");

                editNameField.setText(storedFullName);
                editPostField.setText(storedPost);
                editEmailField.setText(storedEmail);
                editPhoneField.setText(storedPhoneNum);
                
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }

    }
}
