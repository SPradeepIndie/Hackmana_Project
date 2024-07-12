package org.example.hakmana.view.dialogBoxes;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import org.example.hakmana.model.otherDevices.OtherDevices;


public class AddNewDevCatController {
    private static AddNewDevCatController instance = null;

    public TextField devNameTxtField;

    private AddNewDevCatController() {
    }

    public static AddNewDevCatController getInstance() {
        if (instance == null) {
            instance = new AddNewDevCatController();
            return instance;
        }
        return instance;
    }

    public void addBtnOnAction(ActionEvent actionEvent){
        String newDevName=devNameTxtField.getText();
        OtherDevices otherDevices=OtherDevices.getOtherDevicesInstance();
        otherDevices.createNewDevCat(newDevName);
    }

}
