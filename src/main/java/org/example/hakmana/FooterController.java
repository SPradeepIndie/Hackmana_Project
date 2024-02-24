package org.example.hakmana;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;


public class FooterController extends VBox implements Initializable {
    @FXML
    private Label dateLabel;
    @FXML
    private Label timeLabel;
    private boolean test=true;

    private static Task<Void> updateTimeTask;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Task for updating date and time
        updateTimeTask = new Task<>() {
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
                    //System.out.println("thread1,I am runnin");
                    Thread.sleep(1000); // Update every second
                }
                return null;
            }
        };
        new Thread(updateTimeTask).start();
    }
    public void clockStoper(){
        test=false;
        updateTimeTask.cancel();
    }
    public FooterController() {
        super();
        FXMLLoader fxmlFooterLoader = new FXMLLoader(getClass().getResource("Component/Footer.fxml"));
        fxmlFooterLoader.setController(this);
        fxmlFooterLoader.setRoot(this);

        try {
            fxmlFooterLoader.load();
        }
        catch (IOException Footerexception){
            throw new RuntimeException(Footerexception);
        }
    }
}
