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
import org.example.hakmana.view.component.DeviceCategoryCardController;
import org.example.hakmana.view.component.HeaderController;
import org.example.hakmana.view.component.NavPanelController;
import org.example.hakmana.view.component.PathFinderController;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class DeviceMngmntController implements Initializable {

    @FXML
    private NavPanelController navPanelController;//NavPanel custom component injector
    @FXML
    private HeaderController headerController;
    @FXML
    private  VBox bodyComponet;//injector for VBox to expand
    @FXML
    private PathFinderController pathFinderController;
    @FXML
    public AnchorPane parentAnchor;

    @FXML
    private GridPane grid;
    private int rowCount = 1;
    private int colCount = 0;

    public void initialize(URL location, ResourceBundle resources) {
        headerController.setFontSize("2.5em");
        headerController.setTitleMsg("Device Management");
        headerController.setUsernameMsg("Mr.Udara Mahanama");
        headerController.setDesignationMsg("Development Officer");
        navPanelController.setDeviceMngmntdBorder();
        pathFinderController.setSearchBarVisible(false);
        pathFinderController.setPathTxt("Device Management");
        pathFinderController.setBckBtnScene("DeviceMngmnt.fxml");

        //create the event listener to the navigation panel ToggleButton() method
        navPanelController.collapseStateProperty().addListener((observable, oldValue, newValue) ->{
            if(newValue){
                expand();
            }else{
                collapse();
            }
        });

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
            card.setDevCatSceneName(OtherDevices.class.getResource("DeviceMngmntSmmryScene"));
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

    private void Animation(double animStartPos,double animEndPos){
        //Animation object refernce
        TranslateTransition bodyExpand = new TranslateTransition(Duration.millis(300), bodyComponet);
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


}