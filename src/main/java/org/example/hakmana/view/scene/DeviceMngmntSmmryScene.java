package org.example.hakmana.view.scene;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import java.net.URL;
import java.util.ResourceBundle;

import org.example.hakmana.model.*;
import org.example.hakmana.view.component.*;

public class DeviceMngmntSmmryScene implements Initializable {
    @FXML
    public PathFinderController pathFinderController;
    @FXML
    public NavPanelController navPanelController;//NavPanel custom component injector
    @FXML
    public HeaderController headerController;
    @FXML
    public  VBox bodyComponet;//injector for VBox to expand

    @FXML
    public GridPane grid;
    private int rowCount = 1;
    private int colCount = 0;

    private static String dbSelector;

    public String getDbSelector() {
        return dbSelector;
    }

    public void setDbSelector(String dbSelector) {
        DeviceMngmntSmmryScene.dbSelector = dbSelector;
    }

    public void initialize(URL location, ResourceBundle resources) {
        headerController.setFontSize("2.5em");
        headerController.setTitleMsg("Device Management");
        headerController.setUsernameMsg("Mr.Udara Mahanama");
        headerController.setDesignationMsg("Development Officer");
        navPanelController.setDeviceMngmntdBorder();
        pathFinderController.setBckBtnScene(DeviceMngmntSmmryScene.class.getResource("DeviceMngmntSmmryScene.fxml"));

        //create the event listener to the navigation panel ToggleButton() method
        navPanelController.collapseStateProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                expand();
            } else {
                collapse();
            }
        });

    }

    //add DeviceInfoCards to the scene
    @FXML
    public void addComponent() {
        //use polymorphism concept upcasting
        Devices[] dev=null;//dev store the array of Devices
        if(dbSelector.equals("Desktop")){
            dev=new Desktop().getDevices();
            pathFinderController.setPathTxt("Device Management>Desktop");
        }
        if(dbSelector.equals("Photocopy Machines")){
            dev=new PhotocpyMchine().getDevices();
            pathFinderController.setPathTxt("Device Management>Photocopy Machines");
        }
        if(dbSelector.equals("Monitors")){
            dev=new Monitors().getDevices();
            pathFinderController.setPathTxt("Device Management>Monitors");
        }
        if(dbSelector.equals("Projectors")){
            dev=new Projectors().getDevices();
            pathFinderController.setPathTxt("Device Management>Projectors");
        }
        if(dbSelector.equals("Laptops")){
            dev=new Laptops().getDevices();
            pathFinderController.setPathTxt("Device Management>Laptops");
        }
        if(dbSelector.equals("Printers")){
            dev=new Printer().getDevices();
            pathFinderController.setPathTxt("Device Management>Printers");
        }
        if(dbSelector.equals("UPS")){
            dev=new UPS().getDevices();
            pathFinderController.setPathTxt("Device Management>UPS");
        }

        DeviceInfoCardController card;
        for (Devices d : dev) {//for all the Devices in dev array add card to the scene
            card=new DeviceInfoCardController();
            card.setUser(d.getUserName());
            card.setBrand(d.getModel());
            card.setDevId(d.getRegNum());
            card.setDeviceCat(getDbSelector());

            // Add the label to the grid
            grid.add(card, colCount, rowCount);

            // Increment the row count for the next component
            colCount++;

            // If the row count is a multiple of 3, increment the column count
            if (colCount % 3 == 0) {
                rowCount++;
                colCount = 0;
            }
        }

    }

    //add AddDevice card to the scene
    public void addLastComponent() {
        AddDevButtonController addDevButtonController=new AddDevButtonController();
        addDevButtonController.setDevCat(getDbSelector());
        // Add the label to the grid
        grid.add(addDevButtonController, colCount, rowCount);

        // Increment the row count for the next component
        colCount++;

        // If the row count is a multiple of 3, increment the column count
        if (colCount % 3 == 0) {
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
    }
    public  void collapse() {
        Animation(-244, 0);
        bodyComponet.setMinWidth(bodyComponet.getWidth()-244);
        bodyComponet.setMinWidth(748);
    }
}
