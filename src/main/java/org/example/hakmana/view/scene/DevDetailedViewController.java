package org.example.hakmana.view.scene;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.example.hakmana.model.mainDevices.*;
import org.example.hakmana.model.userMngmnt.DeviceUser;
import org.example.hakmana.view.component.AddDevButtonController;
import org.example.hakmana.view.component.DeviceInfoCardController;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DevDetailedViewController implements Initializable {
    private static final Logger otherErrorLogger= (Logger) LogManager.getLogger(DevDetailedViewController.class);
    private static DevDetailedViewController instance=null;

    //Device details common
    @FXML
    public VBox commonVbox;
    @FXML
    public TextField StatusTextField;
    @FXML
    public TextField modelTextField;
    @FXML
    public TextField regNumTextField;

    //other details
    private ArrayList<HBox> otherHboxList;
    private ArrayList<Label> otherLblList;
    private ArrayList<TextField> otherTextList;
    @FXML
    public VBox otherDetailVbox;
    @FXML
    public Label other1Lbl;
    @FXML
    public HBox other1Hbox;
    @FXML
    public TextField other1;
    @FXML
    public HBox other2Hbox;
    @FXML
    public Label other2Lbl;
    @FXML
    public TextField other2;
    @FXML
    public HBox other3Hbox;
    @FXML
    public Label other3Lbl;
    @FXML
    public TextField other3;
    @FXML
    public HBox other4Hbox;
    @FXML
    public Label other4Lbl;
    @FXML
    public TextField other4;
    @FXML
    public HBox other5Hbox;
    @FXML
    public Label other5Lbl;
    @FXML
    public TextField other5;
    @FXML
    public HBox other6Hbox;
    @FXML
    public Label other6Lbl;
    @FXML
    public TextField other6;
    @FXML
    public HBox other7Hbox;
    @FXML
    public Label other7Lbl;
    @FXML
    public TextField other7;
    @FXML
    public HBox other8Hbox;
    @FXML
    public Label other8Lbl;
    @FXML
    public TextField other8;
    @FXML
    public HBox other9Hbox;
    @FXML
    public Label other9Lbl;
    @FXML
    public TextField other9;
    @FXML
    public HBox other10Hbox;
    @FXML
    public Label other10Lbl;
    @FXML
    public TextField other10;
    @FXML
    public HBox other11Hbox;
    @FXML
    public Label other11Lbl;
    @FXML
    public TextField other11;
    @FXML
    public HBox other12Hbox;
    @FXML
    public Label other12Lbl;
    @FXML
    public TextField other12;
    @FXML
    public HBox other13Hbox;
    @FXML
    public Label other13Lbl;
    @FXML
    public TextField other13;
    @FXML
    public HBox other14Hbox;
    @FXML
    public Label other14Lbl;
    @FXML
    public TextField other14;
    @FXML
    public HBox other15Hbox;
    @FXML
    public Label other15Lbl;
    @FXML
    public TextField other15;


    //input Dev details
    private ArrayList<HBox> inputHboxList;
    private ArrayList<Label> inputLblList;
    private ArrayList<TextField> inputTextList;
    @FXML
    public VBox inputVbox;
    @FXML
    public HBox input1Hbox;
    @FXML
    public Label input1Lbl;
    @FXML
    public TextField input1;
    @FXML
    public HBox input2Hbox;
    @FXML
    public Label input2Lbl;
    @FXML
    public TextField input2;
    @FXML
    public HBox input3Hbox;
    @FXML
    public Label input3Lbl;
    @FXML
    public TextField input3;
    @FXML
    public HBox input4Hbox;
    @FXML
    public Label input4Lbl;
    @FXML
    public TextField input4;
    

    //output Dev details
    private ArrayList<HBox> outputHboxList;
    private ArrayList<Label> outputLblList;
    private ArrayList<TextField> outputTextList;
    @FXML
    public VBox outputVbox;
    @FXML
    public HBox output1Hbox;
    @FXML
    public Label output1Lbl;
    @FXML
    public TextField output1;
    @FXML
    public HBox output2Hbox;
    @FXML
    public Label output2Lbl;
    @FXML
    public TextField output2;
    @FXML
    public HBox output3Hbox;
    @FXML
    public Label output3Lbl;
    @FXML
    public TextField output3;

    //deviceUser details
    private ArrayList<TextField> userTextLsit;
    @FXML
    public VBox userDetailsVbox;
    @FXML
    public Button assignUserBtn;
    @FXML
    public TextField userNIC;
    private String initialUser;//this variable use to identify initial deviceUser to compare
    @FXML
    public TextField userTitle;
    @FXML
    public TextField userName;
    @FXML
    public TextField userGmail;

    @FXML
    public HBox interactionHbox;
    @FXML
    public Button editBtn;
    @FXML
    public Button saveBtn;
    @FXML
    public  Button resetBtn;

    ArrayList<String> newValues=new ArrayList<>();

    private static String deviceSelector;
    private static String devRegNum;
    private  TranslateTransition bodyExpand;//Animation object refernce

    private DevDetailedViewController(){}
    public static DevDetailedViewController getInstance() {
        if(instance==null){
            instance=new DevDetailedViewController();
            return instance;
        }
        return instance;
    }

    public void initialize(URL location, ResourceBundle resources) {
        //get all the other details vbox label and Hboxes
        otherHboxList=new ArrayList<>(List.of(other1Hbox,other2Hbox,other3Hbox,other4Hbox,other5Hbox,other6Hbox,
                other7Hbox,other8Hbox,other9Hbox,other10Hbox,other11Hbox,other12Hbox,other13Hbox,other14Hbox,other15Hbox));
        otherLblList=new ArrayList<>(List.of(other1Lbl,other2Lbl,other3Lbl,other4Lbl,
                other5Lbl,other6Lbl,other7Lbl,other8Lbl,other9Lbl,other10Lbl,other11Lbl,other12Lbl,other13Lbl,other14Lbl,other15Lbl));
        otherTextList=new ArrayList<>(List.of(other1,other2,other3,other4,other5,other6,
                other7,other8,other9,other10,other11,other12,other13,other14,other15));

        //get all the input vbox label and Hboxes and textfield
        inputHboxList=new ArrayList<>(List.of(input1Hbox,input2Hbox,input3Hbox,input4Hbox));
        inputLblList=new ArrayList<>(List.of(input1Lbl,input2Lbl,input3Lbl,input4Lbl));
        inputTextList=new ArrayList<>(List.of(input1,input2,input3,input4));

        //get all the output vbox label and Hboxes and textfield
        outputHboxList=new ArrayList<>(List.of(output1Hbox,output2Hbox,output3Hbox));
        outputLblList=new ArrayList<>(List.of(output1Lbl,output2Lbl,output3Lbl));
        outputTextList=new ArrayList<>(List.of(output1,output2,output3));

        //get all the deviceUser textfield
        userTextLsit=new ArrayList<>(List.of(userNIC,userName,userTitle,userGmail));

        //set the all vboxes not visible and not editable at the begining
        reset();

        //Realtime get the Users
        userNIC.textProperty().addListener((observable, oldValue, newValue) -> {
            // Enable the submitButton only if regNumTextField is not empty
            assignUserBtn.setDisable(newValue.isEmpty());

            // Check if the newValue is available in the users array
            DeviceUser deviceUser =DeviceUser.getDeviceUserInstance().isNicAvailable(newValue);
            if (deviceUser != null) {
                // Auto-fill the other text fields
                userGmail.setText(deviceUser.getGmail());
                userName.setText(deviceUser.getName());
                userTitle.setText(deviceUser.getTitle());
            }
        });

        // Disable the submitButton initially if regNumTextField is empty
        assignUserBtn.setDisable(userNIC.getText().isEmpty());

    }

    /*-------------------------Getter and Setter--------------------------------*/
    public String getDevRegNum() {
        return devRegNum;
    }
    public void setDevRegNum(String devRegNum) {
        DevDetailedViewController.devRegNum = devRegNum;
    }
    public void setDeviceSelector(String deviceSelector) {
        DevDetailedViewController.deviceSelector = deviceSelector;
    }

    public void showDeviceDetail(){
        switch (deviceSelector) {
            case "Desktop" -> {
                Desktop desktop = Desktop.getDesktopInstance().getDevice(getDevRegNum());
                setCommonToView(desktop);
                setOtherDetails(new String[]{"Serial Number","Purchased Form","Ram","Processor", "Hard Disk",
                                "Operating System","Floppy Disk","Sound Card","TV card","Network card","SSD","CD ROM","UPS Registration number","Power supply"},
                        desktop.getSerialNum(),desktop.getPurchasedFrom(),desktop.getRam(),desktop.getProcessor(),
                        desktop.getHardDisk(), desktop.getOs(),desktop.getFloppyDisk(),desktop.getSoundCard(),
                        desktop.getTvCard(),desktop.getNetworkCard(),desktop.getSsd(),desktop.getCdRom(),desktop.getUpsRegNum(),desktop.getPowerSupplyRegNum());
                setOutputDetails(new String[]{"Monitor Register Number","Speaker Register Number","Printer Registration number"},
                        desktop.getMonitorRegNum(),desktop.getSpeakerRegNum(),desktop.getPrinterRegNum());
                setInputDetails(new String[]{"Mouse Register Number","Keyboard Register Number","Mic Register Number","Scanner Register Number"},
                        desktop.getMouseRegNum(),desktop.getKeyboardRegNum(),desktop.getMicRegNum(),desktop.getScannerRegNum());
                userDetails(desktop.getUserNIC());
            }
            case "Photocopy Machines" ->{
                PhotocpyMchine photocpyMchine=PhotocpyMchine.getPhotocpyMchineInstance().getDevice(getDevRegNum());
                setCommonToView(photocpyMchine);
                setOtherDetails(new String[]{"Purchased From"},photocpyMchine.getPurchasedFrom());
            }
            case "Monitors" ->{
                Monitors monitor=Monitors.getMonitorInstance().getDevice(getDevRegNum());
                setCommonToView(monitor);
                setOtherDetails(new String[]{"Screen Size","Purchased From"},monitor.getScreenSize(),monitor.getPurchasedFrom());
            }
            case "Projectors" -> {
                Projectors projector=Projectors.getProjectorsInstance().getDevice(getDevRegNum());
                setCommonToView(projector);
                setOtherDetails(new String[]{"Purchased From"},projector.getPurchasedFrom());
            }
            case "Laptops" -> {
                    Laptops laptop = Laptops.getLaptopsInstance().getDevice(getDevRegNum());
                    setCommonToView(laptop);
                    setOtherDetails(new String[]{"Ram","Processor","Hard Disk","Operating System","Purchased Form"},laptop.getRam(), laptop.getCpu(), laptop.getStorage()
                                ,laptop.getOs(),laptop.getPurchasedFrom());
                    setInputDetails(new String[]{"Mouse Registration number","Keyboard Registration number"},laptop.getMouseRegNum(), laptop.getKeyboardRegNum());
                    userDetails(laptop.getUserNIC());
                }
            case "Printers" -> {
                Printer printer =Printer.getPrinterInstance().getDevice(getDevRegNum());
                setCommonToView(printer);
                setOtherDetails(new String[]{"Serial Number","Paper Input","Paper Output","Purchased From"},
                        printer.getSerialNum(),printer.getPaperInput(),printer.getPaperOutput(),printer.getPurchasedFrom());
            }
            case "UPS" -> {
                    UPS ups =UPS.getUpsInstance().getDevice(getDevRegNum());
                    setCommonToView(ups);
                    setOtherDetails(new String[]{"Purchased From"},ups.getPurchasedFrom());
            }

            default -> throw new IllegalStateException("Unexpected value: " + deviceSelector);
        }

    }

    /*---------------------Create Configurations--------------------*/
    private void setEditable(ArrayList<TextField> textFieldslist,boolean setEdit,String color){
        for(TextField textField:textFieldslist){
            textField.setEditable(setEdit);
            textField.setStyle("-fx-border-color: "+color+";-fx-border-width: 2;-fx-border-radius: 5");
        }
    }
    private void getTextFieldText(ArrayList<TextField> textFieldslists){
        for(TextField textField:textFieldslists){
            if(textField.getText().isEmpty()){
                continue;
            }
            newValues.add(textField.getText());
        }
    }

    /*---------------------Set the values in TextField--------------------------*/
    private void setCommonToView(Devices devCommon){
        regNumTextField.setText(devCommon.getRegNum());
        modelTextField.setText(devCommon.getModel());
        StatusTextField.setText(devCommon.getStatus());
    }
    private void setOtherDetails(String[] otherlblText,String ...setOtherTextField){
        otherDetailVbox.setVisible(true);
        for(int i=0;i< setOtherTextField.length;i++){
            otherHboxList.get(i).setVisible(true);
            otherTextList.get(i).setText(setOtherTextField[i]);
            otherLblList.get(i).setText(otherlblText[i]);
        }
    }
    private void setInputDetails(String[] inputLblText,String ...setInputTextField){
        inputVbox.setVisible(true);
        for(int i=0;i< setInputTextField.length;i++){
            inputHboxList.get(i).setVisible(true);
            inputTextList.get(i).setText(setInputTextField[i]);
            inputLblList.get(i).setText(inputLblText[i]);
        }
    }
    private void setOutputDetails(String[] outputLblText,String ...setInputTextField){
        outputVbox.setVisible(true);
        for(int i=0;i< setInputTextField.length;i++){
            outputHboxList.get(i).setVisible(true);
            outputTextList.get(i).setText(setInputTextField[i]);
            outputLblList.get(i).setText(outputLblText[i]);
        }

    }


    /*------------------------General Interactions-----------------------------*/
    @FXML
    private void edit(){
        setEditable(new ArrayList<>(List.of(modelTextField,StatusTextField)),true,"#03AED2");
        setEditable(otherTextList,true,"#03AED2");
        setEditable(inputTextList,true,"#03AED2");
        setEditable(outputTextList,true,"#03AED2");
        saveBtn.setDisable(false);
        resetBtn.setDisable(false);

    }
    private void reset(){
        setEditable(new ArrayList<>(List.of(regNumTextField,modelTextField,StatusTextField)),false,"grey");
        setEditable(otherTextList,false,"grey");
        setEditable(inputTextList,false,"grey");
        setEditable(outputTextList,false,"grey");
        setEditable(userTextLsit,false,"grey");

        otherDetailVbox.setVisible(false);
        inputVbox.setVisible(false);
        outputVbox.setVisible(false);
        userDetailsVbox.setVisible(false);
        for(HBox h:otherHboxList){
            h.setVisible(false);
        }
        for(HBox input:inputHboxList){
            input.setVisible(false);
        }
        saveBtn.setDisable(true);
        resetBtn.setDisable(true);
    }
    @FXML
    private void cancel(){
        reset();
        showDeviceDetail();
    }
    @FXML
    private void save(){
        newValues.clear();
        switch (deviceSelector) {
            case "Desktop" -> {
                getTextFieldText(new ArrayList<>(List.of(modelTextField,StatusTextField)));
                getTextFieldText(otherTextList);
                getTextFieldText(outputTextList);
                getTextFieldText(inputTextList);
                newValues.add(getDevRegNum());
                otherErrorLogger.info("update details of "+getDevRegNum());
                Desktop.getDesktopInstance().updateDevice(newValues);
                showDeviceDetail();

            }
            case "Photocopy Machines" -> {
                getTextFieldText(new ArrayList<>(List.of(modelTextField,StatusTextField)));
                getTextFieldText(otherTextList);
                newValues.add(getDevRegNum());
                otherErrorLogger.info("update details of "+getDevRegNum());

                PhotocpyMchine.getPhotocpyMchineInstance().updateDevice(newValues);
                showDeviceDetail();
            }
            case "Monitors" -> {
                getTextFieldText(new ArrayList<>(List.of(modelTextField,StatusTextField)));
                getTextFieldText(otherTextList);
                newValues.add(getDevRegNum());
                otherErrorLogger.info("update details of "+getDevRegNum());

                Monitors.getMonitorInstance().updateDevice(newValues);
                showDeviceDetail();


            }
            case "Projectors" -> {
                getTextFieldText(new ArrayList<>(List.of(modelTextField,StatusTextField)));
                getTextFieldText(otherTextList);
                newValues.add(getDevRegNum());
                otherErrorLogger.info("update details of "+getDevRegNum());

                Projectors.getProjectorsInstance().updateDevice(newValues);
                showDeviceDetail();

            }
            case "Laptops" -> {
                getTextFieldText(new ArrayList<>(List.of(modelTextField,StatusTextField)));
                getTextFieldText(otherTextList);
                getTextFieldText(inputTextList);
                newValues.add(getDevRegNum());
                otherErrorLogger.info("update details of "+getDevRegNum());

                Laptops.getLaptopsInstance().updateDevice(newValues);
                showDeviceDetail();

            }
            case "Printers" -> {
                getTextFieldText(new ArrayList<>(List.of(modelTextField,StatusTextField)));
                getTextFieldText(otherTextList);
                newValues.add(getDevRegNum());
                otherErrorLogger.info("update details of "+getDevRegNum());

                Printer.getPrinterInstance().updateDevice(newValues);
                showDeviceDetail();
            }
            case "UPS" -> {
                getTextFieldText(new ArrayList<>(List.of(modelTextField,StatusTextField)));
                getTextFieldText(otherTextList);
                newValues.add(getDevRegNum());
                otherErrorLogger.info("update details of "+getDevRegNum());

                UPS.getUpsInstance().updateDevice(newValues);
                showDeviceDetail();
            }
            default -> throw new IllegalStateException("Unexpected value: " + deviceSelector);
        }

        //after saving set non editable the field
        setEditable(new ArrayList<>(List.of(modelTextField,StatusTextField)),false,"grey");
        setEditable(otherTextList,false,"grey");
        setEditable(inputTextList,false,"grey");
        setEditable(outputTextList,false,"grey");

    }


    /*------------------------Interactions with DeviceUser TABLE-----------------------------------*/
    @FXML
    private void editUser(){
        setEditable(userTextLsit,true,"#03AED2");
    }
    @FXML
    private void assignUser(){
        if(!userNIC.getText().equals(initialUser)){
            if(DeviceUser.getDeviceUserInstance().isNicAvailable(userNIC.getText())==null) {
                //add new deviceUser to the deviceUser table
                DeviceUser.getDeviceUserInstance().insertUser(new ArrayList<>(List.of(userNIC.getText(), userName.getText(), userTitle.getText(), userGmail.getText())));
            }
            switch (deviceSelector){
                case "Desktop"->{
                    //add new deviceUser to the desktop table
                    Desktop.getDesktopInstance().updateDeviceUser(userNIC.getText(),getDevRegNum());
                    otherErrorLogger.info("new user "+userNIC.getText()+" assign to "+ getDevRegNum());

                }
                case "Laptops"->{
                    //add new deviceUser to the laptop table
                    Laptops.getLaptopsInstance().updateDeviceUser(userNIC.getText(),getDevRegNum());
                    otherErrorLogger.info("new user "+userNIC.getText()+" assign to "+ getDevRegNum());

                }
            }

        }
        setEditable(userTextLsit,false,"grey");
    }
    private void userDetails(String nic){
        userDetailsVbox.setVisible(true);
        userNIC.setText(nic);
        initialUser=nic;
    }

}
