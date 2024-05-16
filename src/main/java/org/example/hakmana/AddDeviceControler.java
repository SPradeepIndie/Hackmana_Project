package org.example.hakmana;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.example.hakmana.model.UPS;

import java.io.IOException;


public class AddDeviceControler implements Initializable {
    @FXML
    private ChoiceBox<String> devTypeChoiseBox;
    @FXML
    private GridPane gridPane;
    private String[] deviceTypes={"Desktop","Printer"};
    private String selectedItem;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        devTypeChoiseBox.getItems().addAll(deviceTypes);
        selectedItem=devTypeChoiseBox.getValue();
        devTypeChoiseBox.setValue(selectedItem);
        devTypeChoiseBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            gridPane.getChildren().clear();
            if (newValue.equals("Desktop")) {
                loadForm("Scene/DesktopForm.fxml");
            } else if (newValue.equals("Printer")) {
                loadForm("Scene/printerForm.fxml");
            }

        });
    }

    private void loadForm(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            gridPane.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




}
