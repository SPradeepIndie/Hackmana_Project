package org.example.hakmana.view.scene;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.example.hakmana.model.OtherDevices;
import org.example.hakmana.view.component.*;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class DeviceMngmntController implements Initializable {
    @FXML
    private GridPane grid;
    private int rowCount = 1;
    private int colCount = 0;

    public void initialize(URL location, ResourceBundle resources) {
        //add DeviceCategoryCardController for the scene
        addComponent("Desktop", new Image(Objects.requireNonNull(getClass().getResourceAsStream("Images/Desktop.png"))));
        addComponent("Photocopy Machines",new Image(Objects.requireNonNull(getClass().getResourceAsStream("Images/photoCopy.png"))));
        addComponent("Monitors",new Image(Objects.requireNonNull(getClass().getResourceAsStream("Images/monitor.png"))));
        addComponent("Projectors",new Image(Objects.requireNonNull(getClass().getResourceAsStream("Images/projector.png"))));
        addComponent("Laptops",new Image(Objects.requireNonNull(getClass().getResourceAsStream("Images/laptopcat.png"))));
        addComponent("UPS",new Image(Objects.requireNonNull(getClass().getResourceAsStream("Images/UPS.png"))));
        addComponent("Other Devices",new Image(Objects.requireNonNull(getClass().getResourceAsStream("Images/other.png"))));
    }

    @FXML
    private void addComponent(String catTitle, Image catImage) {
        // Create a new label
        DeviceCategoryCardController card=new DeviceCategoryCardController();
        card.setDevName(catTitle);
        card.setDeviceImage(catImage);

        //load the other device scene according to the card title
        if(Objects.equals(catTitle, "Other Devices"))
            card.setDevCatSceneName(OtherDevices.class.getResource("OtherDevice.fxml"));
        else
            card.setDevCatSceneName(DeviceMngmntSmmryScene.class.getResource("DeviceMngmntSmmryScene.fxml"));
        card.disableBtn(false);

        // Add the label to the grid
        grid.add(card, colCount, rowCount);

        // Increment the row count for the next component
        colCount++;

        // If the row count is a multiple of 3, increment the column count
        if (colCount % 4 == 0) {
            rowCount++;
            colCount = 0;
        }

    }

}