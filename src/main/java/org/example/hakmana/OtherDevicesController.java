package org.example.hakmana;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.example.hakmana.model.DatabaseConnection;
import org.example.hakmana.model.SystemUser;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class OtherDevicesController {
    @FXML
    private NavPanelController navPanelController;//NavPanel custom component injector
    @FXML
    private HeaderController headerController;
    @FXML
    private VBox bodyComponet;//injector for VBox to expand
    @FXML
    private PathFinderController pathFinderController;
    private  TranslateTransition bodyExpand;//Animation object refernce
    @FXML
    private TableView<?> tableView;

    @FXML
    private Label devNameLabel;
    @FXML
    private Label noOfAcDevLabel;

    @FXML
    private Label noOfDevLabel;

    @FXML
    private Label noOfInAcDevLabel;

    private DatabaseConnection databaseConnection;
    private Connection connection;

    public static String dev="FaxMachine";


    public void initialize(URL location, ResourceBundle resources) {
        headerController.setFontSize("2.5em");
        headerController.setTitleMsg("Device Management");
        headerController.setUsernameMsg("Mr.Udara Mahanama");
        headerController.setDesignationMsg("Development Officer");
        navPanelController.setDeviceMngmntdBorder();
        pathFinderController.setSearchBarVisible(false);
        pathFinderController.setPathTxt("Device Management");



        //create the event listener to the navigation panel ToggleButton() method
        navPanelController.collapseStateProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                expand();
            } else {
                collapse();
            }
        });




    }
    private void Animation(double animStartPos,double animEndPos){
        bodyExpand = new TranslateTransition(Duration.millis(300), bodyComponet);
        bodyExpand.setFromX(animStartPos);
        bodyExpand.setToX(animEndPos); // expand VBox
        bodyExpand.setAutoReverse(true);
        bodyExpand.play();

    }
    public  void expand() {
        Animation(0, -244);
        bodyComponet.setMinWidth(992);
        bodyComponet.setMinWidth(bodyComponet.getWidth()+244);
        //System.out.println(bodyComponet.getWidth()+244);
    }
    public  void collapse() {
        Animation(-244, 0);
        bodyComponet.setMinWidth(bodyComponet.getWidth()-244);
        bodyComponet.setMinWidth(748);
    }
    private void  setDevInfo(String devName,int noOfDev,int noOfAcDev,int noOfInAcDev) {
       devNameLabel.setText(devName);
       noOfAcDevLabel.setText(noOfAcDev+"");
       noOfInAcDevLabel.setText(noOfInAcDev+"");
       noOfDevLabel.setText(noOfDev+"");
    }
    private void getDevInfo(String devName) {
        int numActiveDev = 0;
        int numInactiveDev = 0;
        int numRepairingDev = 0;
        DatabaseConnection databaseConnection = null;
        Connection connection = null;

        try {
            databaseConnection = DatabaseConnection.getInstance();
            connection = databaseConnection.getConnection();

            // SQL query without placeholders
            String sqlActive = "SELECT COUNT(*) AS num_active_dev FROM " + devName + " WHERE status = 'active';";
            String sqlInactive = "SELECT COUNT(*) AS num_inactive_dev FROM " + devName + " WHERE status = 'inactive';";
            String sqlRepairing = "SELECT COUNT(*) AS num_repairing_dev FROM " + devName + " WHERE status = 'repairing';";

            // Execute queries for each status
            ResultSet resultSet;

            // Active devices
            PreparedStatement preparedStatementActive = connection.prepareStatement(sqlActive);
            resultSet = preparedStatementActive.executeQuery();
            if (resultSet.next()) {
                numActiveDev = resultSet.getInt("num_active_dev");
            }
            resultSet.close();
            preparedStatementActive.close();

            // Inactive devices
            PreparedStatement preparedStatementInactive = connection.prepareStatement(sqlInactive);
            resultSet = preparedStatementInactive.executeQuery();
            if (resultSet.next()) {
                numInactiveDev = resultSet.getInt("num_inactive_dev");
            }
            resultSet.close();
            preparedStatementInactive.close();

            // Repairing devices
            PreparedStatement preparedStatementRepairing = connection.prepareStatement(sqlRepairing);
            resultSet = preparedStatementRepairing.executeQuery();
            if (resultSet.next()) {
                numRepairingDev = resultSet.getInt("num_repairing_dev");
            }
            resultSet.close();
            preparedStatementRepairing.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Print results or use them as needed
        System.out.println("Number of active devices: " + numActiveDev);
        System.out.println("Number of inactive devices: " + numInactiveDev);
        System.out.println("Number of repairing devices: " + numRepairingDev);
        setDevInfo(devName,numActiveDev+numInactiveDev+numRepairingDev,numActiveDev,numInactiveDev);
    }


    public void webCamOnAction(ActionEvent actionEvent) {
        dev="WebCam";
        getDevInfo(dev);
    }

    public void tripodOnAction(ActionEvent actionEvent) {
        dev="Tripod";
        getDevInfo(dev);
    }

    public void headphoneOnAction(ActionEvent actionEvent) {
        dev="Headphone";
        getDevInfo(dev);
    }

    public void usbHubsOnAcion(ActionEvent actionEvent) {
        dev="USBHub";
        getDevInfo(dev);
    }

    public void dvdOnAction(ActionEvent actionEvent) {
        dev="ExternalDVDRom";
        getDevInfo(dev);
    }

    public void routersOnAction(ActionEvent actionEvent) {
        dev="Router";
        getDevInfo(dev);
    }

    public void speakersOnAction(ActionEvent actionEvent) {
        dev="SpeakerSet";
        getDevInfo(dev);
    }

    public void keyBoardsOnAction(ActionEvent actionEvent) {
        dev="Keyboard";
        getDevInfo(dev);
    }


    public void faxOnAction(ActionEvent actionEvent) {
        dev="FaxMachine";
        getDevInfo(dev);
    }

    public void AddNewDevOnAction(ActionEvent actionEvent) {

    }
}
