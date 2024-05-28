package org.example.hakmana.view.scene;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.net.URL;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.example.hakmana.view.component.HeaderController;
import org.example.hakmana.model.DatabaseConnection;
import org.example.hakmana.view.component.NavPanelController;
import org.example.hakmana.view.component.PathFinderController;

import java.util.ResourceBundle;

public class UserMngmntController implements Initializable {
    @FXML
    public Label userDetailTitle;
    @FXML
    public Label userNameLabel;
    @FXML
    public Label userPostLabel;
    @FXML
    public Label userEmpIdLabel;
    @FXML
    public Label userEmailLabel;
    @FXML
    public Label userPhNumLabel;
    private DatabaseConnection databaseConnection;
    private Connection connection;
    private PreparedStatement preparedStatement;

    public void initialize(URL location, ResourceBundle resources) {
        databaseConnection = DatabaseConnection.getInstance();
        connection = databaseConnection.getConnection();

        try {
            // Query to retrieve deviceUser information
            String query = "SELECT * FROM systemUser WHERE userName = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, LoginPageController.curentUser);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String storedFullName = (resultSet.getString("fullName")==null)?"":resultSet.getString("fullName");
                String storedPost = (resultSet.getString("post")==null)?"":resultSet.getString("post");
                String storedEmail =(resultSet.getString("email")==null)?"":resultSet.getString("email");
                String storedPhoneNum =(resultSet.getString("phoneNum")==null)?"":resultSet.getString("phoneNum");
                String storedEmpId =(resultSet.getString("empId")==null)?"":resultSet.getString("empId");

                String[] name=storedFullName.split(" ");
                userDetailTitle.setText(name[0]);
                userNameLabel.setText("Full Name     : "+storedFullName);
                userPostLabel.setText("Post          : "+storedPost);
                userEmailLabel.setText("Email        : "+storedEmail);
                userPhNumLabel.setText("Phone Number : "+storedPhoneNum);
                userEmpIdLabel.setText("Employee Id  : "+storedEmpId);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    // Button action methods
    @FXML
    public void handleCreateAccountButtonAction(ActionEvent event) throws IOException{
        FXMLLoader createAccfxmlLoad = new FXMLLoader();
        createAccfxmlLoad.setLocation(UserMngmntController.class.getResource("DialogBox/CreateAccount.fxml"));
        DialogPane createAccDialogPane=createAccfxmlLoad.load();

        Dialog<ButtonType> dialog=new Dialog<>();
        dialog.setDialogPane(createAccDialogPane);
        dialog.setTitle("Create Account");

        Optional<ButtonType> clickedButton=dialog.showAndWait();
    }

    @FXML
    protected void handleEditProfileButtonAction(ActionEvent event)throws IOException {
        FXMLLoader editAccfxmlLoad = new FXMLLoader();
        editAccfxmlLoad.setLocation(UserMngmntController.class.getResource("DialogBox/EditProfile.fxml"));
        DialogPane editAccDialogPane=editAccfxmlLoad.load();

        Dialog<ButtonType> dialog=new Dialog<>();
        dialog.setDialogPane(editAccDialogPane);
        dialog.setTitle("Edit Account");

        Optional<ButtonType> clickedButton=dialog.showAndWait();
    }

    @FXML
    protected void handleShowUsersButtonAction(ActionEvent event)throws IOException {
        FXMLLoader showAccfxmlLoad= new FXMLLoader();
        showAccfxmlLoad.setLocation(UserMngmntController.class.getResource("DialogBox/ShowUsers.fxml"));
        DialogPane showAccDialogPane=showAccfxmlLoad.load();

        Dialog<ButtonType> dialog=new Dialog<>();
        dialog.setDialogPane(showAccDialogPane);
        dialog.setTitle("Show Users");

        Optional<ButtonType> clickedButton=dialog.showAndWait();
    }

}

