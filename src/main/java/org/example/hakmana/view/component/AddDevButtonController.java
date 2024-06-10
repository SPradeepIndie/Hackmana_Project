package org.example.hakmana.view.component;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.AnchorPane;
import org.example.hakmana.view.dialogBoxes.AddDeviceDialogController;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddDevButtonController extends AnchorPane implements Initializable {
    @FXML
    public AnchorPane root;
    private static String devCat;
    @FXML
    public Button addDeviceBtn;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public String getDevCat() {
        return devCat;
    }

    public void setDevCat(String devCat) {
        AddDevButtonController.devCat = devCat;
    }

    public AddDevButtonController() {
        super();
        FXMLLoader fxmlFooterLoader = new FXMLLoader(AddDevButtonController.class.getResource("addDevButton.fxml"));
        fxmlFooterLoader.setController(this);
        fxmlFooterLoader.setRoot(this);

        try {
            fxmlFooterLoader.load();
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }

    }
    @FXML
    private void onMouseEntered() {
        root.setScaleX(1.1);
        root.setScaleY(1.1);
    }

    @FXML
    private void onMouseExited() {
        root.setScaleX(1.0);
        root.setScaleY(1.0);
    }
    public void addDeviceBtnDialogOpen(ActionEvent event) throws IOException {
        FXMLLoader addDevicefxmlLoad = new FXMLLoader(org.example.hakmana.view.dialogBoxes.AddDeviceDialogController.class.getResource("AddDeviceDialog.fxml"));
        AddDeviceDialogController addDeviceDialogController=AddDeviceDialogController.getInstance();
        addDevicefxmlLoad.setController(addDeviceDialogController);
        DialogPane addDeviceDialogPane=addDevicefxmlLoad.load();

        addDeviceDialogController.setCardForm(getDevCat());

        Dialog<ButtonType> dialog=new Dialog<>();
        dialog.setDialogPane(addDeviceDialogPane);
        dialog.setTitle("Add device");

        Optional<ButtonType> clickedButton=dialog.showAndWait();

    }
}
