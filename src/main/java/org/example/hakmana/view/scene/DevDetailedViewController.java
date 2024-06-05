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
import org.example.hakmana.model.*;
import org.example.hakmana.model.mainDevices.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DevDetailedViewController implements Initializable {
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
                other7Hbox,other8Hbox,other9Hbox,other10Hbox,other11Hbox));
        otherLblList=new ArrayList<>(List.of(other1Lbl,other2Lbl,other3Lbl,other4Lbl,
                other5Lbl,other6Lbl,other7Lbl,other8Lbl,other9Lbl,other10Lbl,other11Lbl));
        otherTextList=new ArrayList<>(List.of(other1,other2,other3,other4,other5,other6,
                other7,other8,other9,other10,other11));

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
            DeviceUser deviceUser = new DeviceUser().isNicAvailable(newValue);
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

    public static String getDeviceSelector() {
        return deviceSelector;
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
                Desktop desktop = new Desktop().getDevice(getDevRegNum());
                setCommonToView("Device Management>Desktop>" + getDevRegNum(), desktop);
                setOtherDetails(new String[]{"Serial Number","Purchased Form","Ram","Processor",
                        "Hard Disk","Operating System","Floppy Disk","Sound Card","TV card","Netwrok card"},
                        desktop.getSerialNum(),desktop.getPurchasedFrom(),desktop.getRam(),
                        desktop.getHardDisk(),
                        desktop.getOs(),desktop.getFloppyDisk(),desktop.getSoundCard(),
                        desktop.getTvCard(),desktop.getNetworkCard());
                setOutputDetails(new String[]{"Monitor Register Number","Speaker Register Number"},
                        desktop.getMonitorRegNum(),desktop.getSpeakerRegNum());
                setInputDetails(new String[]{"Mouse Register Number","Keyboard Register Number","Mic Register Number","Scanner Register Number"},
                        desktop.getMouseRegNum(),desktop.getKeyboardRegNum(),desktop.getMicRegNum(),desktop.getScannerRegNum());
                userDetails(desktop.getUserNIC());
            }
            case "Photocopy Machines" ->{
                PhotocpyMchine photocpyMchine=new PhotocpyMchine().getDevice(getDevRegNum());
                setCommonToView("Device Management>Photocopy Machines>"+getDevRegNum(),photocpyMchine);
                //setOtherDetails(new String[]{"Copying Capability"},photocpyMchine.getCopyingCapability());
            }
            case "Monitors" ->{
                Monitors monitor=new Monitors().getDevice(getDevRegNum());
                setCommonToView("Device Management>Monitors>"+getDevRegNum(),monitor);
                //setOtherDetails(new String[]{"Desktop Register Number"},monitor.getRegNumDesktop());
            }
            case "Projectors" -> {
                Projectors projector=new Projectors().getDevice(getDevRegNum());
                setCommonToView("Device Management>Projectors>"+getDevRegNum(),projector);
            }
            case "Laptops" -> {
                    Laptops laptop = new Laptops().getDevice(getDevRegNum());
                    setCommonToView("Device Management>Laptops>" + getDevRegNum(), laptop);
                    setOtherDetails(new String[]{"Ram","CPU","Storage","Display",
                            "Operating System","Graphic Card"},laptop.getRam(), laptop.getCpu(), laptop.getStorage()
                                ,laptop.getOs());
                    userDetails(laptop.getUserNIC());
                }
            case "Printers" -> {
                Printer printer = new Printer().getDevice(getDevRegNum());
                setCommonToView("Device Management>Printers>" + getDevRegNum(), printer);
                setOtherDetails(new String[]{"Serial Number","Paper Input","Paper Output","Warranty"},
                        printer.getSerialNum(),printer.getPaperInput(),printer.getPaperOutput(),"waranty");
            }
            case "UPS" -> {
                    UPS ups = new UPS().getDevice(getDevRegNum());
                    setCommonToView("Device Management>UPS>" + getDevRegNum(), ups);
                    //setOtherDetails(new String[]{"Backup Power","Runtime","Desktop Register Number"},
                            //ups.getBackUpPower(),ups.getRunTime(),ups.getRegNumDesktop());
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
    private void setCommonToView(String path, Devices devCommon){
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
    }
    @FXML
    private void cancel(){
        reset();
        showDeviceDetail();
    }
    @FXML
    private void save(){
        switch (deviceSelector) {
            case "Desktop" -> {
                getTextFieldText(new ArrayList<>(List.of(modelTextField,StatusTextField)));
                getTextFieldText(otherTextList);
                getTextFieldText(outputTextList);
                getTextFieldText(inputTextList);
                newValues.add(getDevRegNum());

                if(!new Desktop().updateDevice(newValues)){
                    showDeviceDetail();
                }else{
                    //Check confirmation to change
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Success");
                    alert.setContentText("Successfully Updated desktop"+getDevRegNum() );
                    alert.showAndWait();
                }
            }
            case "Photocopy Machines" -> {
                getTextFieldText(new ArrayList<>(List.of(modelTextField,StatusTextField)));
                getTextFieldText(otherTextList);
                newValues.add(getDevRegNum());

                //System.out.println(newValues);
                if(!new PhotocpyMchine().updateDevice(newValues)){
                    showDeviceDetail();
                }else{
                    //Check confirmation to change
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Success");
                    alert.setContentText("Successfully Updated Photocopy Machine"+getDevRegNum() );
                    alert.showAndWait();
                }

            }
            case "Monitors" -> {
                getTextFieldText(new ArrayList<>(List.of(modelTextField,StatusTextField)));
                getTextFieldText(otherTextList);
                newValues.add(getDevRegNum());

                //System.out.println(newValues);
                if(!new Monitors().updateDevice(newValues)){
                    showDeviceDetail();
                }else{
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Success");
                    alert.setContentText("Successfully Updated Monitor"+getDevRegNum() );
                    alert.showAndWait();
                }

            }
            case "Projectors" -> {
                getTextFieldText(new ArrayList<>(List.of(modelTextField,StatusTextField)));
                getTextFieldText(otherTextList);
                newValues.add(getDevRegNum());

                //System.out.println(newValues);
                if(!new Projectors().updateDevice(newValues)){
                    showDeviceDetail();
                }else{
                    //Check confirmation to change
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Success");
                    alert.setContentText("Successfully Updated Multimedia Projector"+getDevRegNum() );
                    alert.showAndWait();
                }
            }
            case "Laptops" -> {
                getTextFieldText(new ArrayList<>(List.of(modelTextField,StatusTextField)));
                getTextFieldText(otherTextList);
                newValues.add(getDevRegNum());

               // System.out.println(newValues);
                if(!new Laptops().updateDevice(newValues)){
                    showDeviceDetail();
                }else{
                    //Check confirmation to change
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Success");
                    alert.setContentText("Successfully Updated Laptop"+getDevRegNum() );
                    alert.showAndWait();
                }

            }
            case "Printers" -> {
                getTextFieldText(new ArrayList<>(List.of(modelTextField,StatusTextField)));
                getTextFieldText(otherTextList);
                newValues.add(getDevRegNum());

                //System.out.println(newValues);
                if(!new Printer().updateDevice(newValues)){
                    showDeviceDetail();
                }else{
                    //Check confirmation to change
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Success");
                    alert.setContentText("Successfully Updated Printer"+getDevRegNum() );
                    alert.showAndWait();
                }

            }
            case "UPS" -> {
                getTextFieldText(new ArrayList<>(List.of(modelTextField,StatusTextField)));
                getTextFieldText(otherTextList);
                newValues.add(getDevRegNum());

               // System.out.println(newValues);
                if(!new UPS().updateDevice(newValues)){
                    showDeviceDetail();
                }else{
                    //Check confirmation to change
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Success");
                    alert.setContentText("Successfully Updated UPS"+getDevRegNum() );
                    alert.showAndWait();
                }

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
            if(new DeviceUser().isNicAvailable(userNIC.getText())==null) {
                //add new deviceUser to the deviceUser table
                new DeviceUser().insertUser(new ArrayList<>(List.of(userNIC.getText(), userName.getText(), userTitle.getText(), userGmail.getText())));
            }
            switch (deviceSelector){
                case "Desktop"->{
                    //add new deviceUser to the desktop table
                    new Desktop().updateDeviceUser(userNIC.getText(),getDevRegNum());
                }
                case "Laptops"->{
                    //add new deviceUser to the laptop table
                    new Laptops().updateDeviceUser(userNIC.getText(),getDevRegNum());
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
