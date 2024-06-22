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
    private static DeviceMngmntSmmryScene instance = null;
    private static String dbSelector;
    private String searchText = ""; // Initialize searchText
    private Boolean isDevIdSelected = true;
    private javafx.scene.control.ScrollPane bodyScrollPaneD;
    private PathFinderController pathFinderControllerD;
    private Devices[] dev = null;

    @FXML
    public GridPane grid;

    private DeviceMngmntSmmryScene() {
    }

    public void setDevIdSelected(Boolean devIdSelected) {
        isDevIdSelected = devIdSelected;
        updateUI(); // Update UI when searchText changes
    }

    public static DeviceMngmntSmmryScene getInstance() {
        if (instance == null) {
            instance = new DeviceMngmntSmmryScene();
        }
        return instance;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        updateUI(); // Initial update of UI
    }

    public static String getDbSelector() {
        return dbSelector;
    }

    public static void setDbSelector(String dbSelector) {
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
        // upcast the dev
        switch (dbSelector) {
            case "Desktop":
                dev = Desktop.getDesktopInstance().getDevices();
                break;
            case "Photocopy Machines":
                dev = PhotocpyMchine.getPhotocpyMchineInstance().getDevices();
                break;
            case "Monitors":
                dev = Monitors.getMonitorInstance().getDevices();
                break;
            case "Projectors":
                dev = Projectors.getProjectorsInstance().getDevices();
                break;
            case "Laptops":
                dev = Laptops.getLaptopsInstance().getDevices();
                break;
            case "Printers":
                dev = Printer.getPrinterInstance().getDevices();
                break;
            case "UPS":
                dev = UPS.getUpsInstance().getDevices();
                break;
        }
        return dev;
    }

    // Update UI dynamically based on searchText
    private void updateUI() {
        grid.getChildren().clear(); // Clear existing cards
        addFirstComponent();
        getDev();
        int rowCount = 1;
        int colCount = 1;

        // Add the last component (e.g., add button)


        if (isDevIdSelected == true) {
            for (Devices d : dev) {
                if (searchText.isEmpty() || d.getRegNum().toLowerCase().contains(searchText.toLowerCase())) {
                    DeviceInfoCardController card = new DeviceInfoCardController();
                    card.setUser(d.getUserName());
                    card.setBrand(d.getModel());
                    card.setDevId(d.getRegNum());
                    card.setDeviceCat(getDbSelector());

                    DeviceInfoCardController.setDashboardBodyScrollpaneDD(getBodyScrollPaneD());
                    DeviceInfoCardController.setDashboardPathFinderControllerDD(getPathFinderControllerD());

                    // Add the card to the grid
                    colCount++;
                    grid.add(card, colCount % 3, rowCount); // Modulo 3 for column, rowCount for row

                    // Manage row and column overflow
                    if (colCount % 3 == 0) {
                        rowCount++;
                    }
                }
            }
        } else {
            for (Devices d : dev) {
                if (searchText.isEmpty() || d.getUserName().toLowerCase().contains(searchText.toLowerCase())) {
                    DeviceInfoCardController card = new DeviceInfoCardController();
                    card.setUser(d.getUserName());
                    card.setBrand(d.getModel());
                    card.setDevId(d.getRegNum());
                    card.setDeviceCat(getDbSelector());

                    DeviceInfoCardController.setDashboardBodyScrollpaneDD(getBodyScrollPaneD());
                    DeviceInfoCardController.setDashboardPathFinderControllerDD(getPathFinderControllerD());

                    // Add the card to the grid
                    colCount++;
                    grid.add(card, colCount % 3, rowCount); // Modulo 3 for column, rowCount for row

                    // Manage row and column overflow
                    if (colCount % 3 == 0) {
                        rowCount++;
                    }
                }
            }
        }

    }

    // Setter for searchText to be called externally (e.g., from a search field)
    public void setSearchText(String searchText) {
        this.searchText = searchText;
        updateUI(); // Update UI when searchText changes
    }

    // Add the last component (e.g., add button) to the grid
    @FXML
    public void addFirstComponent() {
        AddDevButtonController addDevButtonController = new AddDevButtonController();
        addDevButtonController.setDevCat(getDbSelector());

        grid.add(addDevButtonController, 1, 1); // Modulo 3 for column, rowCount for row

    }
}
