package org.example.hakmana.view.component;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import org.example.hakmana.view.dialogBoxes.ContactUsDialog;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;


public class FooterController extends VBox implements Initializable {
    @FXML
    public Label dateLabel;
    @FXML
    public Label timeLabel;
    private boolean test=true;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Task for updating date and time
        // Update every second
        Task<Void> updateTimeTask = new Task<>() {
            @Override
            protected Void call() throws Exception {
                while (test) {
                    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
                    SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");

                    String currentDate = dateFormatter.format(new Date());
                    String currentTime = timeFormatter.format(new Date());

                    Platform.runLater(() -> {
                        dateLabel.setText(currentDate);
                        timeLabel.setText(currentTime);
                    });
                    Thread.sleep(1000); // Update every second
                }
                return null;
            }
        };
        Thread clockThread = new Thread(updateTimeTask);
        clockThread.setDaemon(true);
        clockThread.start();
    }

    public FooterController() {
        super();
        FXMLLoader fxmlFooterLoader = new FXMLLoader(org.example.hakmana.view.component.FooterController.class.getResource("Footer.fxml"));
        fxmlFooterLoader.setController(this);
        fxmlFooterLoader.setRoot(this);
        try {
            fxmlFooterLoader.load();
        }
        catch (IOException FooterException){
            throw new RuntimeException(FooterException);
        }
    }
    @FXML
    public void ContactusBtnDialogOpen() throws IOException{
        FXMLLoader contactUsFxmlLoad = new FXMLLoader();
        contactUsFxmlLoad.setLocation(org.example.hakmana.view.dialogBoxes.ContactUsDialog.class.getResource("ContactUsDialogPane.fxml"));
        contactUsFxmlLoad.setController(ContactUsDialog.getInstance());
        DialogPane conactUsDialogPane=contactUsFxmlLoad.load();

        Dialog<ButtonType> dialog=new Dialog<>();
        dialog.setDialogPane(conactUsDialogPane);
        dialog.setTitle("Contact Us");

        Optional<ButtonType> clickedButton=dialog.showAndWait();

    }
}
