package org.example.hakmana.view.dialogBoxes;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.example.hakmana.model.otherDevices.OtherDevices;


public class AddNewDevCatController {
    private static AddNewDevCatController instance = null;

    public TextField devNameTxtField;

    private AddNewDevCatController() {
    }

    static void alertBox(ActionEvent event, String type, String content){
        Alert alert=null;
        if(type.equals("CONFIRMATION")){
            alert=new Alert(Alert.AlertType.CONFIRMATION);
        }else{
            alert=new Alert(Alert.AlertType.ERROR);
        }

        alert.setTitle("");
        alert.setContentText(content);
        alert.showAndWait();
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
        boolean isAdd=otherDevices.createNewDevCat(newDevName);


        if(isAdd)
            alertBox(null,"CONFIRMATION","Device added Succesfully!");
        else
            alertBox(null,"ERROR","Device didn't add!");

        devNameTxtField.setText("");
    }

}
