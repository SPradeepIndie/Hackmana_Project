package org.example.hakmana;

import javafx.animation.TranslateTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class NavPanelController extends AnchorPane implements Initializable {
    private FooterController footerController=new FooterController();//for cancel the clock thread
    private Stage stage;
    private Scene scene;
    private Parent root;

    /*-------variables for collapse the navigation panel----------*/
    @FXML
    private AnchorPane sidebar; // injector to sidebar container
    @FXML
    private VBox collapsedNavBar;//injector to sidebar for after collapse
    @FXML
    private Button toggleButton; // injector to toggle button
    private boolean isCollapsed = true;
    private double sidebarWidth;
    private TranslateTransition animationCollapse;//Animation object reference

    //injectors to the sections before navigation panel collapsed
    @FXML
    private VBox vbox1;
    @FXML
    private VBox vbox2;
    @FXML
    private VBox vbox3;
    @FXML
    private Button dashboardBtn;
    @FXML
    private Button deviceMngmntBtn;
    @FXML
    private Button reportHndlingBtn;
    @FXML
    private Button overviewHistryBtn;
    @FXML
    private Button userMngmntBtn;
    @FXML
    private Button logout;


    /*-------variable set the navigation panel collapse state ------*/
    private final BooleanProperty collapseState=new SimpleBooleanProperty(false);

    /*---------Override the initialize method in Initializable interface--------*/
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sidebarWidth = sidebar.getPrefWidth()-32;
    }

    private Border border=new Border(new BorderStroke(Color.web("#FFB8B8"),BorderStrokeStyle.SOLID,new CornerRadii(8),new BorderWidths(2)));
    public void setDashboardBorder(){
        dashboardBtn.setBorder(border);
    }
    public void setDeviceMngmntdBorder(){
        deviceMngmntBtn.setBorder(border);
    }
    public void setReportHndlingdBorder(){
        reportHndlingBtn.setBorder(border);
    }
    public void setOverviewHistryBorder(){
        overviewHistryBtn.setBorder(border);
    }
    public void setUserMngmntBorder(){
        userMngmntBtn.setBorder(border);
    }

    /*--------------Getters---------------*/
    public boolean isTest() {
        return collapseState.get();
    }
    public BooleanProperty collapseStateProperty() {
        return collapseState;
    }
    public Button getToggleButton() {
        return toggleButton;
    }

    /*--------Load the custom Component using a constructor--------*/
    public NavPanelController() {
        super();
        FXMLLoader fxmlNavPanelController=new FXMLLoader(getClass().getResource("Component/Nav_panel.fxml"));
        fxmlNavPanelController.setController(this);
        fxmlNavPanelController.setRoot(this);
        try {
            fxmlNavPanelController.load();
        }
        catch(IOException navPnlException){
            throw new RuntimeException(navPnlException);
        }
    }

    /*--------------Navigation panel animations-------------*/
    //Define translation of the navigation panel
    private void Animation(double animStartPos,double animEndPos){
        animationCollapse = new TranslateTransition(Duration.millis(300), sidebar);
        animationCollapse.setFromX(animStartPos);
        animationCollapse.setToX(animEndPos); // Hide sidebar initially
        animationCollapse.setAutoReverse(true);
        animationCollapse.play();
    }

    //collapse button Event handling function
    @FXML
    public void ToggleButton(ActionEvent event) {
        if(isCollapsed) {
            Animation(0,-sidebarWidth);
            vbox1.setVisible(false);
            vbox2.setVisible(false);
            vbox3.setVisible(false);
            collapsedNavBar.setVisible(true);
            collapseState.set(true);

        }
        else{
            Animation(-sidebarWidth,0);
            collapsedNavBar.setVisible(false);
            vbox1.setVisible(true);
            vbox2.setVisible(true);
            vbox3.setVisible(true);
            collapseState.set(false);
        }
        isCollapsed = !isCollapsed;
    }
    public void deviceMnagmnt(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Scene/DeviceMngmntDevCard.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void overviewScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Scene/Overview.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void userMngmntScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Scene/UserMngmnt.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void reportHndlingScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Scene/ReportHndling.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void dashboardScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Scene/dashboard.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void logOut(ActionEvent event) throws IOException {
        footerController.clockStoper();
        System.out.println("logout");
    }
}
