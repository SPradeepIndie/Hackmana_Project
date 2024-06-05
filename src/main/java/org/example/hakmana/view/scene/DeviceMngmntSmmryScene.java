package org.example.hakmana.view.scene;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import org.example.hakmana.model.mainDevices.*;
import org.example.hakmana.view.component.AddDevButtonController;
import org.example.hakmana.view.component.DeviceInfoCardController;
import org.example.hakmana.view.component.PathFinderController;

import java.net.URL;
import java.util.ResourceBundle;

public class DeviceMngmntSmmryScene implements Initializable {
    private static DeviceMngmntSmmryScene instance=null;
    private javafx.scene.control.ScrollPane bodyScrollPaneD;
    private PathFinderController pathFinderControllerD;
    private  Devices[] dev=null;
    @FXML
    public GridPane grid;
    private int rowCount = 1;
    private int colCount = 0;
    private static String dbSelector;

    private DeviceMngmntSmmryScene() {
    }

    public static DeviceMngmntSmmryScene getInstance() {
        if(instance==null){
            instance=new DeviceMngmntSmmryScene();
            return instance;
        }
        return instance;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addLastComponent();
        addComponent();
        rowCount=1;
        colCount=0;
    }
    public String getDbSelector() {
        return dbSelector;
    }

    public void setDbSelector(String dbSelector) {
        DeviceMngmntSmmryScene.dbSelector = dbSelector;
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

    public Devices[] getDev() {
        if(dev==null){
            //upcast the dev
            switch (dbSelector) {
                case "Desktop" -> dev = new Desktop().getDevices();
                case "Photocopy Machines" -> dev = new PhotocpyMchine().getDevices();
                case "Monitors" -> dev = new Monitors().getDevices();
                case "Projectors" -> dev = new Projectors().getDevices();
                case "Laptops" -> dev = new Laptops().getDevices();
                case "Printers" -> dev = new Printer().getDevices();
                case "UPS" -> dev = new UPS().getDevices();
            }
            return dev;
        }
        return dev;
    }

    //add DeviceInfoCards to the scene
    @FXML
    public void addComponent() {
        getDev();
        DeviceInfoCardController card;
        for (Devices d : dev) {//for all the Devices in dev array add card to the scene
            card=new DeviceInfoCardController();
            card.setUser(d.getUserName());
            card.setBrand(d.getModel());
            card.setDevId(d.getRegNum());
            card.setDeviceCat(getDbSelector());

            getPathFinderControllerD().setDeviceInfoCardController(card);
            DeviceInfoCardController.setDashboardBodyScrollpaneDD(getBodyScrollPaneD());//set the Scrollpane body in DevInfoCard class
            DeviceInfoCardController.setDashboardPathFinderControllerDD(getPathFinderControllerD());//pass pathFinderController to the Device Info card Component

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
}
