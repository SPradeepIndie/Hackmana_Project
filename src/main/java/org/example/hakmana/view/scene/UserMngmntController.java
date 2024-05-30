package org.example.hakmana.view.scene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import org.example.hakmana.model.DatabaseConnection;

import org.example.hakmana.model.SystemUser;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import java.util.ResourceBundle;

public class UserMngmntController implements Initializable {
    private static UserMngmntController instance=null;
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
   ;
    private UserMngmntController(){
    }

    public static UserMngmntController getInstance() {
        if(instance==null){
            instance=new UserMngmntController();
            return  instance;
        }
        return instance;
    }

    private UserMngmntController(){
    }

    public static UserMngmntController getInstance() {
        if(instance==null){
            instance=new UserMngmntController();
            return  instance;
        }
        return instance;
    }

    public void initialize(URL location, ResourceBundle resources) {
        SystemUser systemUser=new SystemUser();
        String[] userDet=systemUser.getSystemUserDetails();

        String[] name = userDet[0].split(" ");
        userDetailTitle.setText(name[0]);
        userNameLabel.setText(userDet[0]);
        userPostLabel.setText(userDet[1]);
        userEmailLabel.setText(userDet[2]);
        userPhNumLabel.setText(userDet[3]);
        userEmpIdLabel.setText(userDet[4]);

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

