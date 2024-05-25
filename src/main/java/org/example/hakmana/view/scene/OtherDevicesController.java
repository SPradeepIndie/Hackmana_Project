package org.example.hakmana.view.scene;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.example.hakmana.model.OtherDevices;
import org.example.hakmana.view.component.HeaderController;
import org.example.hakmana.view.component.NavPanelController;
import org.example.hakmana.view.component.PathFinderController;

import java.net.URL;
import java.util.ResourceBundle;

public class OtherDevicesController implements Initializable {
    public OtherDevices otherDevicesDb;
    @FXML
    public NavPanelController navPanelController;//NavPanel custom component injector
    @FXML
    public HeaderController headerController;
    @FXML
    public VBox bodyComponet;//injector for VBox to expand
    @FXML
    public PathFinderController pathFinderController;

    @FXML
    public Button addNew;
    @FXML
    public Button ViewMore;

    @FXML
    public TableView<OtherDevices> otherDeviceTblView;
    @FXML
    public TableColumn<OtherDevices, Integer> num;
    @FXML
    public TableColumn<OtherDevices, String> deviceNameClmn;
    @FXML
    public TableColumn<OtherDevices, Integer> totalClmn;
    @FXML
    public TableColumn<OtherDevices, Integer> activeClmn;

    @FXML
    public TableColumn<OtherDevices, Integer> inactiveClmn;

    @FXML
    public TableColumn<OtherDevices, Integer> repairClmn;

    public void initialize(URL location, ResourceBundle resources) {
        otherDevicesDb=new OtherDevices();

        headerController.setFontSize("2.5em");
        headerController.setTitleMsg("Device Management");
        headerController.setUsernameMsg("Mr.Udara Mahanama");
        headerController.setDesignationMsg("Development Officer");
        navPanelController.setDeviceMngmntdBorder();
        pathFinderController.setSearchBarVisible(false);
        pathFinderController.setPathTxt("Device Management");

        //create the event listener to the navigation panel ToggleButton() method
        navPanelController.collapseStateProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                expand();
            } else {
                collapse();
            }
        });

        num.setCellValueFactory(new PropertyValueFactory<OtherDevices ,Integer>("num"));
        deviceNameClmn.setCellValueFactory(new PropertyValueFactory<OtherDevices ,String>("dev"));
        activeClmn.setCellValueFactory(new PropertyValueFactory<OtherDevices ,Integer>("numActiveDev"));
        inactiveClmn.setCellValueFactory(new PropertyValueFactory<OtherDevices ,Integer>("numInactiveDev"));
        repairClmn.setCellValueFactory(new PropertyValueFactory<OtherDevices ,Integer>("numRepairingDev"));
        totalClmn.setCellValueFactory(new PropertyValueFactory<OtherDevices ,Integer>("totalDev"));

        addTblRow();
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

    public void addTblRow() {
        new Thread(() -> otherDeviceTblView.setItems(otherDevicesDb.getObservableOtherDevices())).start();
    }
}
