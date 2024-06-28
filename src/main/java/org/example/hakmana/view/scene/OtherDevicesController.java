package org.example.hakmana.view.scene;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.example.hakmana.model.otherDevices.OtherDevices;
import org.example.hakmana.view.component.AddDevButtonController;

import java.net.URL;
import java.util.ResourceBundle;

public class OtherDevicesController implements Initializable {
    private static OtherDevicesController instance=null;
    public OtherDevices otherDevicesDb;
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

    private OtherDevicesController() {
    }

    public static OtherDevicesController getInstance() {
        if(instance==null){
            instance=new OtherDevicesController();
            return instance;
        }
        return instance;
    }

    public void initialize(URL location, ResourceBundle resources) {
        otherDevicesDb=new OtherDevices();
        num.setCellValueFactory(new PropertyValueFactory<OtherDevices ,Integer>("num"));
        deviceNameClmn.setCellValueFactory(new PropertyValueFactory<OtherDevices ,String>("dev"));
        activeClmn.setCellValueFactory(new PropertyValueFactory<OtherDevices ,Integer>("numActiveDev"));
        inactiveClmn.setCellValueFactory(new PropertyValueFactory<OtherDevices ,Integer>("numInactiveDev"));
        repairClmn.setCellValueFactory(new PropertyValueFactory<OtherDevices ,Integer>("numRepairingDev"));
        totalClmn.setCellValueFactory(new PropertyValueFactory<OtherDevices ,Integer>("totalDev"));

        addTblRow();
    }
    public void addTblRow() {
        new Thread(() -> otherDeviceTblView.setItems(otherDevicesDb.getObservableOtherDevices())).start();
    }
}
