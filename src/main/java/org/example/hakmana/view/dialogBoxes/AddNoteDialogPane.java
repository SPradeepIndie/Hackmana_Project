package org.example.hakmana.view.dialogBoxes;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.example.hakmana.model.noteHndling.NoteTable;
import org.example.hakmana.view.component.AddDevButtonController;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

public  class AddNoteDialogPane extends Component implements Initializable  {
    private static AddNoteDialogPane instance=null;
    @FXML
    private DialogPane dialogpane1;
    @FXML
    private TextField deviceId;
    @FXML
    private Label date;
    @FXML
    private TextField username;
    @FXML
    private TextArea note;
    @FXML
    private Button updateButton;
    @FXML
    private Button editButton;
    @FXML
    private TextField title;
    private String ids;
    private String userName1;
    private String cardNoteId;
    private String setDeviceIdName;
    private String Note1;
    private Stage stage;
    @FXML
    private  Button addNote;
    private NoteTable noteInstance;
    private String Title;
    public Button getUpdateButton() {
        return updateButton;
    }

    public void setUpdateButton(Button updateButton) {
        this.updateButton = updateButton;
    }

    public DialogPane getDialogpane1() {
        return dialogpane1;
    }

    public void setDialogpane1(DialogPane dialogpane1) {
        this.dialogpane1 = dialogpane1;
    }

    public Label getDate() {
        return date;
    }

    public void setDate(Label date) {
        this.date = date;
    }

    public TextField getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(TextField deviceId) {
        this.deviceId = deviceId;
    }

    public TextField getUsername() {
        return username;
    }

    public void setUsername(TextField username) {
        this.username = username;
    }

    public TextArea getNote() {
        return note;
    }

    public void setNote(TextArea note) {
        this.note = note;
    }

    public Button getEditButton() {
        return editButton;
    }

    public void setEditButton(Button editButton) {
        this.editButton = editButton;
    }

    public TextField getTitle() {
        return title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setTitle(TextField title) {
        this.title = title;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getUserName1() {
        return userName1;
    }

    public void setUserName1(String userName1) {
        this.userName1 = userName1;
    }

    public String getCardNoteId() {
        return cardNoteId;
    }

    public void setCardNoteId(String cardNoteId) {
        this.cardNoteId = cardNoteId;
    }

    public String getSetDeviceIdName() {
        return setDeviceIdName;
    }

    public String getNote1() {
        return Note1;
    }

    public void setNote1(String note1) {
        Note1 = note1;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Button getAddNote() {
        return addNote;
    }

    public void setAddNote(Button addNote) {
        this.addNote = addNote;
    }

    public void setSetDeviceIdName(String setDeviceIdName) {
        this.deviceId.setText(setDeviceIdName);
    }
    public  void setUser(String userName){
        this.username.setText(userName);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LocalDate localDate=LocalDate.now();
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String currentDate=localDate.format(formatter);
        date.setText(currentDate);
    }

    private AddNoteDialogPane() {
    }

    public static AddNoteDialogPane getInstance() {
        if(instance==null){
            instance=new AddNoteDialogPane();
            return instance;
        }
        return instance;
    }

    public void addDetails() {
        ids = deviceId.getText().isEmpty() ? null : deviceId.getText();
        userName1 = username.getText().isEmpty() ? null : username.getText();
        Note1 = note.getText().isEmpty() ? null : note.getText();
        Title=title.getText().isEmpty() ? null : title.getText();
    }


    public void createnote() {
        addDetails();

        Alert.AlertType type = Alert.AlertType.CONFIRMATION;
        Alert alert = new Alert(type, "");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(stage);
        alert.getDialogPane().setContentText("do you want to add this note?");
        alert.getDialogPane().setHeaderText("confirmation!");
        Optional<ButtonType> reasult = alert.showAndWait();
        String currentdate=date.getText();
        LocalDate localDate=LocalDate.parse(currentdate);
        if (reasult.get() == ButtonType.OK) {

            if ((ids !=null) && (userName1 != null) && (Note1!=null) && (Title !=null)) {

                try {
                    noteInstance=NoteTable.getInstance();
                    noteInstance.createNoteQuries(getIds(),getUserName1(),getNote1(),java.sql.Date.valueOf(localDate),Title);
                    deviceId.setText(null);
                    username.setText(null);
                    title.setText(null);
                    note.setText(null);
                    JOptionPane.showMessageDialog(this, "the note is successfully added!", "alert!", JOptionPane.INFORMATION_MESSAGE);

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "same title with same id not valid", "Rejected!", JOptionPane.ERROR_MESSAGE);

                }


            } else {
                JOptionPane.showMessageDialog(this, "All fields need to be filled.", "Rejected!", JOptionPane.ERROR_MESSAGE);
            }

        } else if (reasult.get() == ButtonType.CANCEL) {
            JOptionPane.showMessageDialog(this, "note is cancelled!", "alert!", JOptionPane.INFORMATION_MESSAGE);

        }

    }




}

