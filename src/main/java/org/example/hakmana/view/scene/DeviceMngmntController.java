package org.example.hakmana.view.scene;

import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import org.example.hakmana.view.component.DeviceCategoryCardController;
import org.example.hakmana.view.component.PathFinderController;


import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class DeviceMngmntController implements Initializable {
    private static DeviceMngmntController instance=null;
    private javafx.scene.control.ScrollPane bodyScrollPaneD=null;

    private PathFinderController pathFinderControllerD;

    public GridPane grid;
    private int rowCount = 1;
    private int colCount = 0;

    private DeviceMngmntController(){
    }

    public static DeviceMngmntController getInstance() {
        if(instance==null){
            instance=new DeviceMngmntController();
            return instance;
        }
        return instance;
    }

    public void initialize(URL location, ResourceBundle resources) {
        //add DeviceCategoryCardController for the scene
        addComponent("Desktop", new Image(Objects.requireNonNull(getClass().getResourceAsStream("Images/Desktop.png"))));
        addComponent("Photocopy Machines",new Image(Objects.requireNonNull(getClass().getResourceAsStream("Images/photoCopy.png"))));
        addComponent("Monitors",new Image(Objects.requireNonNull(getClass().getResourceAsStream("Images/monitor.png"))));
        addComponent("Projectors",new Image(Objects.requireNonNull(getClass().getResourceAsStream("Images/projector.png"))));
        addComponent("Laptops",new Image(Objects.requireNonNull(getClass().getResourceAsStream("Images/laptopcat.png"))));
        addComponent("UPS",new Image(Objects.requireNonNull(getClass().getResourceAsStream("Images/UPS.png"))));
        addComponent("Printers",new Image(Objects.requireNonNull(getClass().getResourceAsStream("Images/other.png"))));
        addComponent("Other Devices",new Image(Objects.requireNonNull(getClass().getResourceAsStream("Images/other.png"))));
        //After adding componennt reset the row and column number
        rowCount=1;
        colCount=0;
    }

    private void addComponent(String catTitle, Image catImage) {
        // Create a new label
        DeviceCategoryCardController card=new DeviceCategoryCardController();
        card.setDevName(catTitle);
        card.setDeviceImage(catImage);
        card.disableBtn(false);

        getPathFinderControllerD().setDeviceCategoryCardController(card);
        DeviceCategoryCardController.setDashboardBodyScrollpaneD(bodyScrollPaneD);
        card.setDashboardPathFinderControllerD(pathFinderControllerD);

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

    public ScrollPane getBodyScrollPaneD() {
        return bodyScrollPaneD;
    }

    public void setBodyScrollPaneD(ScrollPane bodyScrollPaneD) {
        this.bodyScrollPaneD = bodyScrollPaneD;
    }

    public PathFinderController getPathFinderControllerD() {
        return pathFinderControllerD;
    }

    public void setPathFinderControllerD(PathFinderController pathFinderControllerD) {
        this.pathFinderControllerD = pathFinderControllerD;
    }
}