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

import org.example.hakmana.model.SystemUser;
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
        SystemUser systemUser=new SystemUser();
        String[] userDet=systemUser.getSystemUserDetails();

        String[] name = userDet[0].split(" ");
        userDetailTitle.setText(name[0]);
        userNameLabel.setText("Full Name     : " + userDet[0]);
        userPostLabel.setText("Post          : " + userDet[1]);
        userEmailLabel.setText("Email        : " + userDet[2]);
        userPhNumLabel.setText("Phone Number : " + userDet[3]);
        userEmpIdLabel.setText("Employee Id  : " + userDet[4]);

    }

    // Button action methods
    @FXML
    public void handleCreateAccountButtonAction(ActionEvent event) throws IOException {
        FXMLLoader createAccfxmlLoad = new FXMLLoader();
        createAccfxmlLoad.setLocation(UserMngmntController.class.getResource("DialogBox/CreateAccount.fxml"));
        DialogPane createAccDialogPane = createAccfxmlLoad.load();

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(createAccDialogPane);
        dialog.setTitle("Create Account");

        Optional<ButtonType> clickedButton = dialog.showAndWait();
    }

    @FXML
    protected void handleEditProfileButtonAction(ActionEvent event) throws IOException {
        FXMLLoader editAccfxmlLoad = new FXMLLoader();
        editAccfxmlLoad.setLocation(UserMngmntController.class.getResource("DialogBox/EditProfile.fxml"));
        DialogPane editAccDialogPane = editAccfxmlLoad.load();

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(editAccDialogPane);
        dialog.setTitle("Edit Account");

        Optional<ButtonType> clickedButton = dialog.showAndWait();
    }

    @FXML
    protected void handleShowUsersButtonAction(ActionEvent event) throws IOException {
        FXMLLoader showAccfxmlLoad = new FXMLLoader();
        showAccfxmlLoad.setLocation(UserMngmntController.class.getResource("DialogBox/ShowUsers.fxml"));
        DialogPane showAccDialogPane = showAccfxmlLoad.load();

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(showAccDialogPane);
        dialog.setTitle("Show Users");

        Optional<ButtonType> clickedButton = dialog.showAndWait();
    }

}

