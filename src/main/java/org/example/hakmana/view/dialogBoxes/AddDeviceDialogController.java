package org.example.hakmana.view.dialogBoxes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.example.hakmana.model.mainDevices.*;
import org.example.hakmana.model.userMngmnt.DeviceUser;
import org.example.hakmana.view.component.AddDevButtonController;
import org.example.hakmana.view.component.DeviceInfoCardController;
import org.example.hakmana.view.scene.DeviceMngmntSmmryScene;
import org.example.hakmana.view.scene.LoginPageController;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddDeviceDialogController implements Initializable {
    private static final Logger otherErrorLogger= (Logger) LogManager.getLogger(AddDeviceDialogController.class);
    private static AddDeviceDialogController instance=null;
    @FXML
    public ChoiceBox<String> devCat;//devcat selector
    private String devCategoryName;
    private String devRegNum;

    //Device details
    //common
    @FXML
    public TextField modelTextField;
    @FXML
    public TextField regNumTextField;
    @FXML
    public ChoiceBox<String> StatusChoiceBox;


    //other details
    private ArrayList<HBox> otherHboxList;
    private ArrayList<Label> otherLblList;
    private ArrayList<TextField> otherTextList;
    private ArrayList<ChoiceBox> otherChoiceBoxList;
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
    public ChoiceBox<String> OSChoiseBox;
    @FXML
    public HBox other7Hbox;
    @FXML
    public Label other7Lbl;
    @FXML
    public TextField other7;
    @FXML
    public HBox other8Hbox;
    @FXML
    public ChoiceBox<String> FloppyDiskChoiseBox;
    @FXML
    public HBox other9Hbox;
    @FXML
    public ChoiceBox<String> SoundCardChoiseBox;
    @FXML
    public HBox other10Hbox;
    @FXML
    public ChoiceBox<String> TVCardChoiseBox;
    @FXML
    public HBox other11Hbox;
    @FXML
    public ChoiceBox<String> NetworkCardChoiseBox;
    @FXML
    public HBox other12Hbox;
    @FXML
    public ChoiceBox<String> SsdChoiceBox;
    @FXML
    public HBox other13Hbox;
    @FXML
    public ChoiceBox<String> CdRomChoiceBox;
    @FXML
    public HBox other14Hbox;
    @FXML
    public ChoiceBox<String> UpsChoiceBox;
    @FXML
    public HBox other15Hbox;
    @FXML
    public ChoiceBox<String> PowerSupplyChoiceBox;


    //input Dev details
    private ArrayList<HBox> inputHboxList;
    private ArrayList<Label> inputLblList;
    private ArrayList<ChoiceBox> inputChoiceBoxList;
    @FXML
    public VBox inputVbox;
    @FXML
    public HBox input1Hbox;
    @FXML
    public Label input1Lbl;
    @FXML
    public ChoiceBox<String> inputChoiceBox1;
    @FXML
    public HBox input2Hbox;
    @FXML
    public Label input2Lbl;
    @FXML
    public ChoiceBox<String> inputChoiceBox2;
    @FXML
    public HBox input3Hbox;
    @FXML
    public Label input3Lbl;
    @FXML
    public ChoiceBox<String> inputChoiceBox3;
    @FXML
    public HBox input4Hbox;
    @FXML
    public Label input4Lbl;
    @FXML
    public ChoiceBox<String> inputChoiceBox4;
    private final String[] inputDeviceList1={"No"};
    private final String[] inputDeviceList2={"No"};
    private final String[] inputDeviceList3={"No"};
    private final String[] inputDeviceList4={"No"};


    //output Dev details
    private ArrayList<HBox> outputHboxList;
    private ArrayList<Label> outputLblList;
    private ArrayList<ChoiceBox> outputChoiceBoxList;
    @FXML
    public VBox outputVbox;
    @FXML
    public HBox output1Hbox;
    @FXML
    public Label output1Lbl;
    @FXML
    public ChoiceBox<String> outputChoiceBox1;
    @FXML
    public HBox output2Hbox;
    @FXML
    public Label output2Lbl;
    @FXML
    public ChoiceBox<String> outputChoiceBox2;
    @FXML
    public HBox output3Hbox;
    @FXML
    public Label output3Lbl;
    @FXML
    public ChoiceBox<String> outputChoiceBox3;
    private final String[] outputDeviceList1={"No"};
    private final String[] outputDeviceList2={"No"};
    private final String[] outputDeviceList3={"No"};


    //deviceUser details
    private ArrayList<TextField> userTextLsit;
    @FXML
    public VBox userDetailsVbox;
    @FXML
    public TextField userNIC;
    @FXML
    public TextField userTitle;
    @FXML
    public TextField userName;
    @FXML
    public TextField userGmail;

    @FXML
    public HBox interactionHbox;
    @FXML
    public Button submitButton;
    @FXML
    public Button resetBtn;
    @FXML
    public Button addUserButton;
    LoginPageController newInstance=LoginPageController.getInstance();
    //Array fo populate the choiceBoxes
    private final String[] devCategories={"Desktop","Photocopy Machines","Monitors","Projectors","Laptops","Printers","UPS"};
    private final String[] deviceStatus={"Active","Repairing","Inactive","Not Assigned"};
    private final String[] YN={"Yes","No"};
    private final String[] WinLin={"Windows","Linux"};
    private final String[] OnboardDecicated={"On Board","Dedicated","No"};
    private final String[] ssdList={"No"};
    private final String[] cdRomList={"No"};
    private final String[] powerSupplyList={"No"};
    private final String[] upsList={"No"};

    private boolean isFromComponent;

    public static DeviceUser deviceUser;

    //for get new values from the textFields
    private ArrayList<String> newValues=new ArrayList<>();

    /*----------------------------------------------------------------------------*/
    private AddDeviceDialogController(){

    }
    public static AddDeviceDialogController getInstance() {
        if(instance==null){
            instance=new AddDeviceDialogController();
            return instance;
        }
        return instance;
    }

    /*-------------------------------Initialize---------------------------------*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        isFromComponent=false;
        deviceUser =DeviceUser.getDeviceUserInstance();

        //populate the choiceboxes
        devCat.getItems().addAll(devCategories);
        StatusChoiceBox.getItems().addAll(deviceStatus);

        FloppyDiskChoiseBox.getItems().addAll(YN);
        CdRomChoiceBox.getItems().addAll(YN);

        NetworkCardChoiseBox.getItems().addAll(OnboardDecicated);
        SoundCardChoiseBox.getItems().addAll(OnboardDecicated);
        TVCardChoiseBox.getItems().addAll(OnboardDecicated);

        OSChoiseBox.getItems().addAll(WinLin);
        SsdChoiceBox.getItems().addAll(ssdList);
        CdRomChoiceBox.getItems().addAll(cdRomList);
        PowerSupplyChoiceBox.getItems().addAll(powerSupplyList);
        UpsChoiceBox.getItems().addAll(upsList);

        inputChoiceBox1.getItems().addAll(inputDeviceList1);
        inputChoiceBox2.getItems().addAll(inputDeviceList2);
        inputChoiceBox3.getItems().addAll(inputDeviceList3);
        inputChoiceBox4.getItems().addAll(inputDeviceList4);
        outputChoiceBox1.getItems().addAll(outputDeviceList1);
        outputChoiceBox2.getItems().addAll(outputDeviceList2);
        outputChoiceBox3.getItems().addAll(outputDeviceList3);

        //get all the other details vbox, label, Hboxes, TextField, ChoiceBoxes
        otherHboxList=new ArrayList<>(List.of(other1Hbox,other2Hbox,other3Hbox,other4Hbox,other5Hbox,other7Hbox));
        otherLblList=new ArrayList<>(List.of(other1Lbl,other2Lbl,other3Lbl,other4Lbl, other5Lbl,other7Lbl));
        otherTextList=new ArrayList<>(List.of(other1,other2,other3,other4,other5,other7));
        otherChoiceBoxList=new ArrayList<>(List.of(OSChoiseBox,FloppyDiskChoiseBox,
                SoundCardChoiseBox,TVCardChoiseBox,NetworkCardChoiseBox,
                SsdChoiceBox,CdRomChoiceBox,UpsChoiceBox,PowerSupplyChoiceBox));

        //get all the input vbox, label, Hboxes and ChoiceBoxes
        inputHboxList=new ArrayList<>(List.of(input1Hbox,input2Hbox,input3Hbox,input4Hbox));
        inputLblList=new ArrayList<>(List.of(input1Lbl,input2Lbl,input3Lbl,input4Lbl));
        inputChoiceBoxList=new ArrayList<>(List.of(inputChoiceBox1,inputChoiceBox2,inputChoiceBox3,inputChoiceBox4));

        //get all the output vbox, label, Hboxes and Choiceboxes
        outputHboxList=new ArrayList<>(List.of(output1Hbox,output2Hbox,output3Hbox));
        outputLblList=new ArrayList<>(List.of(output1Lbl,output2Lbl,output3Lbl));
        outputChoiceBoxList=new ArrayList<>(List.of(outputChoiceBox1,outputChoiceBox2,outputChoiceBox3));

        //get all the deviceUser textfield
        userTextLsit=new ArrayList<>(List.of(userNIC,userName,userTitle,userGmail));

        resetBtnAction();

        //update the register number field realtime
        regNumTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            //Check the given regNUm is available

            // Enable the submitButton only if regNumTextField is not empty
            submitButton.setDisable(newValue.isEmpty());

            //set editable textfield when deviceUser enter register number
            setEditable(new ArrayList<>(List.of(modelTextField)),true,"#03AED2");
            setEditable(otherTextList,true,"#03AED2");
            setEditable(userTextLsit,true,"#03AED2");
            //set choice box enable
            StatusChoiceBox.setDisable(false);
            setChoiceBoxDisability(false,otherChoiceBoxList);
            setChoiceBoxDisability(false,inputChoiceBoxList);
            setChoiceBoxDisability(false,outputChoiceBoxList);
            setDevRegNum(newValue);

        });

        //Realtime get the Users
        userNIC.textProperty().addListener((observable, oldValue, newValue) -> {
            // Enable the submitButton only if regNumTextField is not empty
            addUserButton.setDisable(newValue.isEmpty());

            // Check if the newValue is available in the users array
            DeviceUser deviceUser =DeviceUser.getDeviceUserInstance().isNicAvailable(newValue);
            if (deviceUser != null) {
                // Autofill the other text fields
                userGmail.setText(deviceUser.getGmail());
                userName.setText(deviceUser.getName());
                userTitle.setText(deviceUser.getTitle());
            }
        });

        //update the device category selecting choicebox realtime
        devCat.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            listnerReset();
            setDevCategoryName(newValue);
            setView();
        });

        // Disable the submitButton initially if regNumTextField is empty
        submitButton.setDisable(regNumTextField.getText().isEmpty());
        addUserButton.setDisable(regNumTextField.getText().isEmpty());
    }


    /*----------------------Getters and Setters----------------------*/
    public String getDevCategoryName() {
        return devCategoryName;
    }
    public void setDevCategoryName(String devCategoryName) {
        this.devCategoryName = devCategoryName;
    }
    public String getDevRegNum() {
        return devRegNum;
    }
    public void setDevRegNum(String devRegNum) {
        this.devRegNum = devRegNum;
    }
    public void setDevCat() {
        devCat.setValue("Select a device");
    }

    private void setView(){
        setChoiceBoxVisibility(false);
        switch (getDevCategoryName()) {
            case "Desktop" -> {
                setChoiceBoxVisibility(true);
                setOtherDetails(new String[]{"Serial Number","Purchased Form","Ram","Processor","Hard Disk"});
                setOutputDetails(new String[]{"Monitor Register Number","Speaker Register Number"});
                setInputDetails(new String[]{"Mouse Register Number","Keyboard Register Number","Mic Register Number","Scanner Register Number"});
                userDetailsVbox.setVisible(true);
            }
            case "Photocopy Machines", "Projectors" ->
                setOtherDetails(new String[]{"Purchased From"});
            case "Monitors" ->
                setOtherDetails(new String[]{"Purchased From","Screen Size"});
            case "Laptops" -> {
                setChoiceBoxVisibility(true);
                setOtherDetails(new String[]{"Purchased From","Ram","CPU","Storage"});
                setInputDetails(new String[]{"Mouse Register Number","Keyboard Register Number"});
                other6Hbox.setVisible(true);
                userDetailsVbox.setVisible(true);
            }
            case "Printers" ->
                setOtherDetails(new String[]{"purchased From","Serial Number","Paper Input","Paper Output"});
            case "UPS" ->
                setOtherDetails(new String[]{"purchasedFrom"});
            default ->
                System.out.println(getDevCategoryName());
        }
    }
    private void setOtherDetails(String[] otherlblText){
        otherDetailVbox.setVisible(true);
        for(int i=0;i< otherlblText.length;i++){
            otherHboxList.get(i).setVisible(true);
            otherTextList.get(i).setVisible(true);
            otherLblList.get(i).setText(otherlblText[i]);
        }
    }
    private void setInputDetails(String[] inputLblText){
        inputVbox.setVisible(true);
        for(int i=0;i< inputLblText.length;i++){
            inputHboxList.get(i).setVisible(true);
            inputChoiceBoxList.get(i).setVisible(true);
            inputLblList.get(i).setText(inputLblText[i]);
        }
    }
    private void setOutputDetails(String[] outputLblText){
        outputVbox.setVisible(true);
        for(int i=0;i< outputLblText.length;i++){
            outputHboxList.get(i).setVisible(true);
            outputLblList.get(i).setText(outputLblText[i]);
        }

    }


    /*--------------------------Interaction------------------------------------*/
    private boolean checkCat(){
        if(getDevCategoryName() == null){
            alert(Alert.AlertType.WARNING,"No Category","Please select a category first");
            return false;
        }
        return true;
    }
    private boolean addDb(){
       String loggedUser=newInstance.getLogedUser();
        switch (getDevCategoryName()) {
            case "Desktop" -> {
                newValues.add(getDevRegNum());
                newValues.add(modelTextField.getText());
                newValues.add(StatusChoiceBox.getValue());
                getTextFieldText(otherTextList);
                getChoiceBoxValue(otherChoiceBoxList);
                newValues.add(userNIC.getText());

                if(nicFieldCheck()){
                    // Call addUser in a background thread
                    new Thread(this::addUser).start();
                }
                return Desktop.getDesktopInstance().insertDevice(newValues);

            }
            case "Photocopy Machines" -> {
                newValues.add(getDevRegNum());
                newValues.add(modelTextField.getText());
                newValues.add(StatusChoiceBox.getValue());
                getTextFieldText(otherTextList);
                otherErrorLogger.info("user "+loggedUser+"  added new device/"+newValues);
                return PhotocpyMchine.getPhotocpyMchineInstance().insertDevice(newValues);
            }
            case "Monitors" -> {
                newValues.add(getDevRegNum());
                newValues.add(modelTextField.getText());
                newValues.add(StatusChoiceBox.getValue());
                getTextFieldText(otherTextList);
                otherErrorLogger.info("user "+loggedUser+"  added new device/"+newValues);

                return Monitors.getMonitorInstance().insertDevice(newValues);

            }
            case "Projectors" -> {
                newValues.add(getDevRegNum());
                newValues.add(modelTextField.getText());
                newValues.add(StatusChoiceBox.getValue());
                otherErrorLogger.info("user "+loggedUser+"  added new device/"+newValues);

                return Projectors.getProjectorsInstance().insertDevice(newValues);

            }
            case "Laptops" -> {
                newValues.add(getDevRegNum());
                newValues.add(modelTextField.getText());
                newValues.add(StatusChoiceBox.getValue());
                getTextFieldText(otherTextList);
                newValues.add( OSChoiseBox.getValue());
                newValues.add(userNIC.getText());
                otherErrorLogger.info("user "+loggedUser+"  added new device/"+newValues);

                if(nicFieldCheck()) {
                    // Call addUser in a background thread
                    new Thread(this::addUser).start();
                }

                return Laptops.getLaptopsInstance().insertDevice(newValues);

            }
            case "Printers" -> {
                newValues.add(getDevRegNum());
                newValues.add(modelTextField.getText());
                newValues.add(StatusChoiceBox.getValue());
                getTextFieldText(otherTextList);
                otherErrorLogger.info("user "+loggedUser+"  added new device/"+newValues);

                return Printer.getPrinterInstance().insertDevice(newValues);

            }
            case "UPS" -> {
                newValues.add(getDevRegNum());
                newValues.add(modelTextField.getText());
                newValues.add(StatusChoiceBox.getValue());
                getTextFieldText(otherTextList);
                otherErrorLogger.info("user "+loggedUser+"  added new device/"+newValues);

                return UPS.getUpsInstance().insertDevice(newValues);

            }
            default -> {
                return false;
            }
        }

    }
    @FXML
    public void submitButtonOnAction(ActionEvent event) {
        newValues.clear();
        boolean isCatSelected=checkCat();
        boolean isDbAdded=false;
        if(isCatSelected){
            isDbAdded=addDb();

        }
        if(isCatSelected && isDbAdded){
            alert(Alert.AlertType.INFORMATION,"Success","Successfully inserted new device \n"+newValues);
            resetBtnAction();
            setDevCat();
        }
        if(isFromComponent){
            DeviceMngmntSmmryScene.getInstance().updateUI();//when device added update ui
        }
        else {
            System.out.println(newValues);
        }

    }

    // Reset the form into general form according to the event listener
    private void listnerReset(){
        clearAll();//clearAll the fields

        StatusChoiceBox.setDisable(true);
        //disable all the choice boxes
        setChoiceBoxDisability(true,otherChoiceBoxList);
        setChoiceBoxDisability(true,inputChoiceBoxList);
        setChoiceBoxDisability(true,outputChoiceBoxList);

        //set text field to non-editable and colors
        setEditable(new ArrayList<>(List.of(modelTextField)), false, "grey");
        setEditable(otherTextList, false, "grey");
        setEditable(userTextLsit, false, "grey");

        //disable the addUserButton
        addUserButton.setDisable(true);

        if(!isFromComponent) {
            //except the common vbox set all other fields to not visible
            otherDetailVbox.setVisible(false);
            for (HBox otherHbox : otherHboxList) {
                otherHbox.setVisible(false);
            }
            for(TextField otherText:otherTextList){
                otherText.setVisible(false);
            }

            inputVbox.setVisible(false);
            for(HBox inputHbox:inputHboxList){
                inputHbox.setVisible(false);
            }
            for(ChoiceBox inputChoiceBox:inputChoiceBoxList){
                inputChoiceBox.setVisible(false);
            }
            outputVbox.setVisible(false);
            userDetailsVbox.setVisible(false);

        }
    }

    //handle reset button action
    @FXML
    public void resetBtnAction(){
        listnerReset();
        if(!isFromComponent){
            setDevCat();
        }
    }

    //clear all  the text field and choice box values
    private void clearAll(){
        StatusChoiceBox.getSelectionModel().clearSelection();
        clearChoiceBox(otherChoiceBoxList);
        clearChoiceBox(inputChoiceBoxList);
        clearChoiceBox(outputChoiceBoxList);
        regNumTextField.clear();
        modelTextField.clear();
        for(TextField fields:otherTextList){
            fields.clear();
        }
        for(TextField fields:userTextLsit){
            fields.clear();
        }

    }

    private void alert(Alert.AlertType alertType,String title,String content){
        Alert alert=new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();

    }


    /*---------------------Create Configurations--------------------*/

    //According to the device selected in DeviceMngmntSmmryScene load the view
    //disable the devCat dropdown box
    public void setCardForm(String cat){
        devCat.setValue(cat);
        devCat.setDisable(true);
        setDevCategoryName(cat);
        isFromComponent=true;
        setView();
    }

    //set the All Choice box Disability
    private void setChoiceBoxDisability(boolean isDisable, ArrayList<ChoiceBox> choiceBoxList){
        for(ChoiceBox choBox:choiceBoxList){
            choBox.setDisable(isDisable);
        }
    }

    //set the Choice box Visibility(except Status Choice Box)
    private void setChoiceBoxVisibility(boolean isVisible){
        other6Hbox.setVisible(isVisible);
        other8Hbox.setVisible(isVisible);
        other9Hbox.setVisible(isVisible);
        other10Hbox.setVisible(isVisible);
        other11Hbox.setVisible(isVisible);
        other12Hbox.setVisible(isVisible);
        other13Hbox.setVisible(isVisible);
        other14Hbox.setVisible(isVisible);
        other15Hbox.setVisible(isVisible);
    }

    //clear all the Choice boxes values
    private void clearChoiceBox(ArrayList<ChoiceBox> choiceBoxList){
        for(ChoiceBox choBox:choiceBoxList){
            choBox.getSelectionModel().clearSelection();
        }
    }

    //get the Choice Box values and add to the newValues arraylist(except Status Choice Box)
    private void getChoiceBoxValue(ArrayList<ChoiceBox> choiceBoxList){
        for(ChoiceBox choBox:choiceBoxList){
            if(choBox.getValue().toString().isEmpty()){
                break;
            }
            if(choBox.isVisible()){
                newValues.add((String) choBox.getValue());
            }

        }
    }

    //set the text fields editing ability and border color
    private void setEditable(ArrayList<TextField> textFieldslist, boolean setEdit, String color){
        for(TextField textField:textFieldslist){
            textField.setEditable(setEdit);
            textField.setStyle("-fx-border-color: "+color+";-fx-border-width: 2;-fx-border-radius: 5");
        }
    }

    //get the text fields values
    private void getTextFieldText(ArrayList<TextField> textFieldslists){
        for(TextField textField:textFieldslists){
            if(textField.getText().isEmpty() && textField.isVisible()){
                //alert(Alert.AlertType.CONFIRMATION,"Confirm","Empty Field detected\nDo you want to continue");
                break;
            }
            else if(textField.isVisible()){
                newValues.add(textField.getText());
            }

        }
    }

    /*------------------------Interactions with DeviceUser TABLE-----------------------------------*/
    private boolean nicFieldCheck(){
        return userNIC.getText() != null;
    }
    @FXML
    private void addUser(){
        if(nicFieldCheck()) {
            if (deviceUser.isNicAvailable(userNIC.getText()) == null) {
                //add new deviceUser to the deviceUser table
                deviceUser.insertUser(new ArrayList<>(List.of(userNIC.getText(), userName.getText(), userTitle.getText(), userGmail.getText())));
                otherErrorLogger.info("new deviceUser "+userNIC.getText()+" is added");
            }
        }else {
            alert(Alert.AlertType.WARNING,"No User","Please select a device user");
        }
    }

    
}
