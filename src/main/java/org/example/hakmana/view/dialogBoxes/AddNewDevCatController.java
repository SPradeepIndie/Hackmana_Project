package org.example.hakmana.view.dialogBoxes;


import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.example.hakmana.model.otherDevices.OtherDevices;

public class AddNewDevCatController {
    private static AddNewDevCatController instance=null;
    @FXML
    public TextField devNameTxtField;

    private AddNewDevCatController(){}

    public static AddNewDevCatController getInstance() {
        if(instance==null){
            instance=new AddNewDevCatController();
            return instance;
        }
        return instance;
    }

    @FXML
    public void addDevCatBtuttonOnAction(ActionEvent actionEvent) {
        String devName=devNameTxtField.getText();
        OtherDevices otherDevices=OtherDevices.getOtherDevicesInstance();
        otherDevices.createNewDevCat(devName);
    }
}
