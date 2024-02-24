package org.example.hakmana;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.hakmana.HeaderController;
import org.example.hakmana.NavPanelController;

import java.io.IOException;
import java.net.URL;
import java.security.PrivilegedAction;
import java.util.ResourceBundle;

public class UserMngmntController implements Initializable {

    @FXML
    private HeaderController headerController;

    @FXML
    private NavPanelController navPanelController;//NavPanel custom component injector

    @FXML
    private  VBox bodyComponet;//injector for VBox to expand

    private  TranslateTransition bodyExpand;//Animation object refernce

    @FXML
    private AnchorPane parentAnchor;
    public void initialize(URL location, ResourceBundle resources) {

        headerController.setFontSize("2.5em");
        headerController.setTitleMsg("User management");
        navPanelController.setUserMngmntBorder();
        //create the event listener to the navigation panel ToggleButton() method
        navPanelController.collapseStateProperty().addListener((observable, oldValue, newValue) ->{
            if(newValue){
                expand();
            }else{
                collapse();
            }
        });
    }

    private void Animation(double animStartPos,double animEndPos){
        bodyExpand = new TranslateTransition(Duration.millis(300), bodyComponet);
        bodyExpand.setFromX(animStartPos);
        bodyExpand.setToX(animEndPos); // expand VBox
        bodyExpand.setAutoReverse(true);
        bodyExpand.play();

    }
    public  void expand() {
        Animation(0, -244);
        bodyComponet.setMinWidth(992);
        bodyComponet.setMinWidth(bodyComponet.getWidth()+244);
        //System.out.println(bodyComponet.getWidth()+244);
    }
    public  void collapse() {
        Animation(-244, 0);
        bodyComponet.setMinWidth(bodyComponet.getWidth()-244);
        bodyComponet.setMinWidth(748);
    }

    //Nimnada Added For Testing - START

    // Button action methods
    @FXML
    public void handleCreateAccountButtonAction(ActionEvent event) {
        try {
            Parent createAccountParent = FXMLLoader.load(getClass().getResource("/org/example/hakmana/scene/CreateAccount.fxml"));
            Scene createAccountScene = new Scene(createAccountParent);

            // Get the current window and set the scene
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(createAccountScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void handleEditProfileButtonAction(ActionEvent event) {
        try {
            Parent createAccountParent = FXMLLoader.load(getClass().getResource("/org/example/hakmana/scene/EditProfile.fxml"));
            Scene createAccountScene = new Scene(createAccountParent);

            // Get the current window and set the scene
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(createAccountScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void handleShowUsersButtonAction(ActionEvent event) {
        try {
            Parent createAccountParent = FXMLLoader.load(getClass().getResource("/org/example/hakmana/scene/Showusers.fxml"));
            Scene createAccountScene = new Scene(createAccountParent);

            // Get the current window and set the scene
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(createAccountScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Helper method to load the interfaces
    private void loadInterface(String fxmlFile) {
        try {
            // The FXMLLoader now points to the correct path within the resources directory
            Node node = FXMLLoader.load(getClass().getResource(fxmlFile));
            bodyComponet.getChildren().setAll(node);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception here (e.g., show an error dialog)
        }
    }

    //Nimnada Added For Testing - END


}