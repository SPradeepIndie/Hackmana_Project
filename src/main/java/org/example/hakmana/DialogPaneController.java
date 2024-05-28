package org.example.hakmana;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.example.hakmana.model.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

public  class DialogPaneController extends Component implements Initializable  {

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



    private String Title;
    DatabaseConnection instance = DatabaseConnection.getInstance();
    Connection conn = instance.getConnection();

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


    public DialogPaneController() {
    }

    public void addDetails() {
        ids = deviceId.getText().isEmpty() ? null : deviceId.getText();
        userName1 = username.getText().isEmpty() ? null : username.getText();
        Note1 = note.getText().isEmpty() ? null : note.getText();
        Title=title.getText().isEmpty() ? null : title.getText();
    }


    public void createnote() {
        DatabaseConnection instance = DatabaseConnection.getInstance();
        Connection conn = instance.getConnection();
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

            if ((getIds() != null) && (getUserName1() != null) && (getNote() != null)) {
                PreparedStatement notesse = null;
                try {
                    notesse = conn.prepareStatement("insert into notes values(?,?,?,?,?)");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                try {

                    notesse.setString(1, getIds());
                    notesse.setString(2, getUserName1());
                    notesse.setString(3, getNote1());
                    notesse.setDate(4,java.sql.Date.valueOf(localDate));
                    notesse.setString(5,Title);
                    notesse.executeUpdate();
                    notesse.close();
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LocalDate localDate=LocalDate.now();
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String currentDate=localDate.format(formatter);
        date.setText(currentDate);
    }

    public void edit() {
            deviceId.setEditable(true);
            title.setEditable(true);
            username.setEditable(true);
            note.setEditable(true);

    }


}

