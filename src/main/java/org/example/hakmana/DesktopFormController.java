package org.example.hakmana;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.hakmana.model.DatabaseConnection;
import org.example.hakmana.model.Desktop;
import org.example.hakmana.model.DeviceUser;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DesktopFormController implements Initializable {
    Stage stage;
    Scene scene;
    Parent root;
    @FXML
    private ChoiceBox<String> FloppyDiskChoiseBox;

    @FXML
    private ChoiceBox<String> NetworkCardChoiseBox;

    @FXML
    private ChoiceBox<String> OSChoiseBox;
    @FXML
    private Button submitButton;

    @FXML
    private ChoiceBox<String> SoundCardChoiseBox;

    @FXML
    private ChoiceBox<String> StatusChoiseBox;

    @FXML
    private ChoiceBox<String> TVCardChoiseBox;

    @FXML
    private TextField hardDiskTextField;

    @FXML
    private TextField keyboardRegNumTextField;

    @FXML
    private TextField micRegNumTextField;

    @FXML
    private TextField modelTextField;

    @FXML
    private TextField monitorRegNumTextField;

    @FXML
    private TextField mouseRegNumTextField;

    @FXML
    private TextField processorTextField;

    @FXML
    private TextField projectorRegNumTextField;

    @FXML
    private TextField purchasedFromTextField;

    @FXML
    private TextField ramTextField;

    @FXML
    private TextField regNumTextField;

    @FXML
    private TextField scannerRegNumTextField;

    @FXML
    private TextField serialNumTextField;

    @FXML
    private TextField speakerRegNumTextField;

    @FXML
    private TextField warrantyTextField;
    @FXML
    private Button addUserButton;

    private String[] deviceStatus={"Active","Repairing","Inactive"};
    private String[] YN={"Yes","No"};
    private String[] WinLin={"Windows","Linux"};
    private String[] OnboardDecicated={"On Board","Dedicated"};

    public static DeviceUser user;
    public void addUser(ActionEvent event) throws IOException {
        ((Node) event.getSource()).setDisable(UserAssignDialogController.isAssignUserButtonClicked);

        if(!UserAssignDialogController.isAssignUserButtonClicked){
            Parent root = FXMLLoader.load(getClass().getResource("Scene/userAssignDialog.fxml"));
            Stage stage=new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        }

    }



    public void desktop(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Scene/desktop.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        StatusChoiseBox.getItems().addAll(deviceStatus);
        FloppyDiskChoiseBox.getItems().addAll(YN);
        NetworkCardChoiseBox.getItems().addAll(OnboardDecicated);
        SoundCardChoiseBox.getItems().addAll(OnboardDecicated);
        TVCardChoiseBox.getItems().addAll(OnboardDecicated);
        OSChoiseBox.getItems().addAll(WinLin);
        user=new DeviceUser();
        user.setNic("No User");

        DialogPane dialogPane = new DialogPane();
        ButtonType customButtonType = new ButtonType("Submit");
        dialogPane.getButtonTypes().addAll(customButtonType, ButtonType.CANCEL);

        regNumTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            // Enable the submitButton only if regNumTextField is not empty
            submitButton.setDisable(newValue.isEmpty());
            addUserButton.setDisable(newValue.isEmpty() );
        });

        // Disable the submitButton initially if regNumTextField is empty
        submitButton.setDisable(regNumTextField.getText().isEmpty());
        addUserButton.setDisable(regNumTextField.getText().isEmpty());

    }


    public void submitButtonOnAction(ActionEvent event) {

        Desktop desktop=new Desktop();
        desktop.setRegNum(regNumTextField.getText());
        desktop.setSerialNum(serialNumTextField.getText());
        desktop.setModel(modelTextField.getText());
        desktop.setPurchasedFrom(purchasedFromTextField.getText());
        desktop.setRam(ramTextField.getText());
        desktop.setProcessor(processorTextField.getText());
        desktop.setWarranty(warrantyTextField.getText());
        desktop.setHardDisk(hardDiskTextField.getText());
        desktop.setOs(OSChoiseBox.getValue());
        desktop.setStatus(StatusChoiseBox.getValue());
        desktop.setMonitorRegNum(monitorRegNumTextField.getText());
        desktop.setProjectorRegNum(projectorRegNumTextField.getText());
        desktop.setSpeakerRegNum(speakerRegNumTextField.getText());
        desktop.setMouseRegNum(mouseRegNumTextField.getText());
        desktop.setKeyboardRegNum(keyboardRegNumTextField.getText());
        desktop.setSoundCard(SoundCardChoiseBox.getValue());
        desktop.setTvCard(TVCardChoiseBox.getValue());
        desktop.setNetworkCard(NetworkCardChoiseBox.getValue());
        desktop.setMicRegNum(micRegNumTextField.getText());

        desktop.setUserNIC(user.getNic());

        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();

        databaseConnection.insertDesktop(desktop);
        if(!user.getNic().equalsIgnoreCase("No User")){
            databaseConnection.insertUser(user);
        }
        System.out.println("Done");



        // Reset the form
        regNumTextField.clear();
        serialNumTextField.clear();
        modelTextField.clear();
        purchasedFromTextField.clear();
        ramTextField.clear();
        processorTextField.clear();
        warrantyTextField.clear();
        hardDiskTextField.clear();
        OSChoiseBox.getSelectionModel().clearSelection();
        StatusChoiseBox.getSelectionModel().clearSelection();
        monitorRegNumTextField.clear();
        projectorRegNumTextField.clear();
        speakerRegNumTextField.clear();
        mouseRegNumTextField.clear();
        keyboardRegNumTextField.clear();
        SoundCardChoiseBox.getSelectionModel().clearSelection();
        TVCardChoiseBox.getSelectionModel().clearSelection();
        NetworkCardChoiseBox.getSelectionModel().clearSelection();
        micRegNumTextField.clear();

        UserAssignDialogController.isAssignUserButtonClicked=false;
        addUserButton.setDisable(false);


    }
 public void cancelButtonOnAction(ActionEvent event) {
     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
     stage.close();

 }


}
