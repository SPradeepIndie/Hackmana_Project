package org.example.hakmana.view.component;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardCardController extends ScrollPane implements Initializable {
    @FXML
    public Label topLabel;
    public DashboardCardController() {
        super();
        FXMLLoader fxmlDashboardCardLoader=new FXMLLoader(DashboardCardController.class.getResource("dashboardCard.fxml"));

        fxmlDashboardCardLoader.setController(this);
        fxmlDashboardCardLoader.setRoot(this);


        try {
            fxmlDashboardCardLoader.load();

        }
        catch(IOException navPnlException){
            throw new RuntimeException(navPnlException);
        }
    }
    public void setText(String text) {
        topLabel.setText(text);
    }
    public String getText() {
        return topLabel.getText();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
