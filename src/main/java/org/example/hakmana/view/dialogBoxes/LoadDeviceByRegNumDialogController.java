package org.example.hakmana.view.dialogBoxes;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import org.example.hakmana.model.mainDevices.Devices;
import org.example.hakmana.model.otherDevices.OtherDevices;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LoadDeviceByRegNumDialogController implements Initializable {
    private static LoadDeviceByRegNumDialogController instance=null;
    private final OtherDevices otherDevices=OtherDevices.getOtherDevicesInstance();
    @FXML//device category selector
    public ChoiceBox<String> devCat;
    @FXML//device Registration number selector
    public ChoiceBox<String> devRegNum;

    private String selectedDevice;
    private String selectedDevRegNum;

    private final String[] mainDevCategories={"Desktop","Photocopy Machines","Monitors","Projectors","Laptops","Printers","UPS"};
    private List<String> otherDevCategories;
    private List<String> devRegNumbers;;
    private boolean fromOtherDevice;

    private LoadDeviceByRegNumDialogController() {
    }

    public static LoadDeviceByRegNumDialogController getInstance() {
        if(instance==null){
            instance=new LoadDeviceByRegNumDialogController();
            return instance;
        }
        return instance;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateDevCatChoiceBox();
        devCat.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setSelectedDevice(newValue);
        });
        devRegNum.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
           setSelectedDevice(newValue);
        });
        populateDevRegChoiceBox();
    }

    public String getSelectedDevice() {
        return selectedDevice;
    }

    public void setSelectedDevice(String selectedDevice) {
        this.selectedDevice = selectedDevice;
    }

    public String getSelectedDevRegNum() {
        return selectedDevRegNum;
    }

    public void setSelectedDevRegNum(String selectedDevRegNum) {
        this.selectedDevRegNum = selectedDevRegNum;
    }

    public List<String> getOtherDevCategories() {
        return otherDevCategories;
    }

    public void setOtherDevCategories(List<String> otherDevCategories) {
        this.otherDevCategories = otherDevCategories;
    }

    public List<String> getDevRegNumbers() {
        return devRegNumbers;
    }

    public void setDevRegNumbers(List<String> devRegNumbers) {
        this.devRegNumbers = devRegNumbers;
    }

    public boolean isFromOtherDevice() {
        return fromOtherDevice;
    }

    public void setFromOtherDevice(boolean fromOtherDevice) {
        this.fromOtherDevice = fromOtherDevice;
    }

    //according to the button selected fetch table name from other device table
    private void fetchOtherDevices(){
        setOtherDevCategories(otherDevices.loadForQuickAccess());
    }
    private void populateDevCatChoiceBox(){
        if(isFromOtherDevice()){
            fetchOtherDevices();
            devCat.getItems().addAll(getOtherDevCategories());
        }else{
            devCat.getItems().addAll(mainDevCategories);
        }
    }

    //When device cat selected the reg numbers from tables
    private void populateDevRegChoiceBox(){
        System.out.println(getSelectedDevice());

    }


}
