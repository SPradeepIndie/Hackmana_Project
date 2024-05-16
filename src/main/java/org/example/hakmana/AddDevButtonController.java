package org.example.hakmana;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddDevButtonController extends AnchorPane implements Initializable {

    @FXML
    private Button addDeviceBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    private AnchorPane root;

    public AddDevButtonController() {
        super();
        FXMLLoader fxmlFooterLoader = new FXMLLoader(getClass().getResource("Component/addDevButton.fxml"));
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
        FXMLLoader addDevicefxmlLoad = new FXMLLoader();
        addDevicefxmlLoad.setLocation(getClass().getResource("Scene/DialogBox/DesktopForm.fxml"));
        DialogPane addDeviceDialogPane=addDevicefxmlLoad.load();

        Dialog<ButtonType> dialog=new Dialog<>();
        dialog.setDialogPane(addDeviceDialogPane);
        dialog.setTitle("Contact Us");

        Optional<ButtonType> clickedButton=dialog.showAndWait();



    }
}
