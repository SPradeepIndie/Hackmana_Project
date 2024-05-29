package org.example.hakmana.view.component;

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
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.hakmana.view.scene.*;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class NavPanelController extends AnchorPane implements Initializable {
    private PathFinderController pathFinderController=null;
    private Stage stage;
    private Scene scene;

    /*-------variables for collapse the navigation panel----------*/
    private final Border border=new Border(new BorderStroke(Color.web("#FFB8B8"),BorderStrokeStyle.SOLID,new CornerRadii(8),new BorderWidths(2)));
    @FXML
    private AnchorPane sidebar; // injector to sidebar container
    @FXML
    private VBox collapsedNavBar;//injector to sidebar for after collapse
    @FXML
    private Button toggleButton; // injector to toggle button
    private boolean isCollapsed = true;
    private double sidebarWidth;

    //For get the main dashboard body scroll pane
    private javafx.scene.control.ScrollPane dashboardBodyScrollpane;

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

    /*-----------set border style for the button after clicked----------------*/
    public void setDashboardBorder(){
        setBorderStyle(dashboardBtn);
    }
    public void setBorderStyle(Button btn){
        btn.setStyle("-fx-border-color:#FFB8B8;-fx-border-radius: 8;-fx-border-width: 2;-fx-border-style: solid ");
    }
    public void removeBorderStyle(){
        String borderStyle="-fx-border-style: none;";
        dashboardBtn.setStyle(borderStyle);
        deviceMngmntBtn.setStyle(borderStyle);
        reportHndlingBtn.setStyle(borderStyle);
        userMngmntBtn.setStyle(borderStyle);
        overviewHistryBtn.setStyle(borderStyle);
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
    public ScrollPane getDashboardBodyScrollpane() {
        return dashboardBodyScrollpane;
    }
    public void setDashboardBodyScrollpane(ScrollPane dashboardBodyScrollpane) {
        this.dashboardBodyScrollpane = dashboardBodyScrollpane;
    }
    public PathFinderController getPathFinderController() {
        return pathFinderController;
    }

    public void setPathFinderController(PathFinderController pathFinderController) {
        this.pathFinderController = pathFinderController;
    }

    /*--------Load the custom Component using a constructor--------*/
    public NavPanelController() {
        super();
        FXMLLoader fxmlNavPanelController=new FXMLLoader(NavPanelController.class.getResource("Nav_panel.fxml"));
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
        //Animation object reference
        TranslateTransition animationCollapse = new TranslateTransition(Duration.millis(300), sidebar);
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

    public void deviceMnagmnt(){
            FXMLLoader vboxLoader =new FXMLLoader(DeviceMngmntController.class.getResource("DeviceMngmnt.fxml"));
            DeviceMngmntController deviceMngmntController=DeviceMngmntController.getInstance();
            vboxLoader.setController(deviceMngmntController);
            //pass Scroll pane to the Device Management class
            deviceMngmntController.setBodyScrollPaneD(getDashboardBodyScrollpane());
            loadVBox(vboxLoader);//this method load the Vbox to the Scrollpane

            getPathFinderController().setBckBtnScene(DeviceMngmntController.class.getResource("DeviceMngmnt.fxml"));
            setBorderStyle(deviceMngmntBtn);
    }

    public void overviewScene() {
            FXMLLoader vboxLoader =new FXMLLoader(OverviewController.class.getResource("Overview.fxml"));
            OverviewController overviewController=OverviewController.getInstance();
            vboxLoader.setController(overviewController);

            loadVBox(vboxLoader);//this method load the Vbox to the Scrollpane

            getPathFinderController().setBckBtnScene(OverviewController.class.getResource("Overview.fxml"));
            setBorderStyle(overviewHistryBtn);

    }

    public void userMngmntScene() {
            FXMLLoader vboxLoader =new FXMLLoader(UserMngmntController.class.getResource("UserMngmnt.fxml"));
            UserMngmntController userMngmntController=UserMngmntController.getInstance();
            vboxLoader.setController(userMngmntController);

            loadVBox(vboxLoader);//this method load the Vbox to the Scrollpane

            getPathFinderController().setBckBtnScene(UserMngmntController.class.getResource("UserMngmnt.fxml"));
            setBorderStyle(userMngmntBtn);
    }

    public void reportHndlingScene(){
            FXMLLoader vboxLoader =new FXMLLoader(ReportHndlingController.class.getResource("ReportHndling.fxml"));
            ReportHndlingController reportHndlingController=ReportHndlingController.getInstance();
            vboxLoader.setController(reportHndlingController);

            loadVBox(vboxLoader);//this method load the Vbox to the Scrollpane

            getPathFinderController().setBckBtnScene(Objects.requireNonNull(ReportHndlingController.class.getResource("ReportHndling.fxml")));
            setBorderStyle(reportHndlingBtn);
    }

    //load Vbox
    public void loadVBox(FXMLLoader vboxloader){
        VBox vbox= null;
        try {
            vbox = vboxloader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        getDashboardBodyScrollpane().setContent(vbox);//this scollpane id knows only that controller file
        removeBorderStyle();
    }

    public void dashboardScene(ActionEvent event) throws IOException {
        FXMLLoader dasboardFxmlLoader=new FXMLLoader(DashboardController.class.getResource("dashboard.fxml"));

        DashboardController dashboardController=DashboardController.getInstance();
        dasboardFxmlLoader.setController(dashboardController);

        Parent root = dasboardFxmlLoader.load();

        getPathFinderController().setBckBtnScene(Objects.requireNonNull(DashboardController.class.getResource("dashboard.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        removeBorderStyle();
        setBorderStyle(reportHndlingBtn);
    }

    public void logOut(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginPageController.class.getResource("loginPage.fxml"));

        LoginPageController loginPageController=LoginPageController.getInstance();
        fxmlLoader.setController(loginPageController);

        Parent root = fxmlLoader.load();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setWidth(845.0);
        stage.setHeight(565.0);

        //for load the login page in the center of the screen
        Screen screen = Screen.getPrimary();
        double screenWidth = screen.getBounds().getWidth();
        double screenHeight = screen.getBounds().getHeight();

        double stageCenterX = (screenWidth - stage.getWidth()) / 2;
        double stageCenterY = (screenHeight - stage.getHeight()) / 2;
        stage.setX(stageCenterX);
        stage.setY(stageCenterY);

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}