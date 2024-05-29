package org.example.hakmana.view.component;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.hakmana.view.scene.DashboardController;
import org.example.hakmana.view.scene.DevDetailedViewController;
import org.example.hakmana.view.scene.DeviceMngmntSmmryScene;
import org.example.hakmana.view.scene.ReportHndlingController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class PathFinderController extends VBox implements Initializable {
    private static final ArrayList<URL> sceneList = new ArrayList<>();
    private NavPanelController navPanelControllerPath;

    public NavPanelController getNavPanelControllerPath() {
        return navPanelControllerPath;
    }

    public void setNavPanelControllerPath(NavPanelController navPanelControllerPath) {
        this.navPanelControllerPath = navPanelControllerPath;
    }

    private boolean searchBarVisible;
    @FXML
    public HBox searchBar;
    @FXML
    private Label pathTxt;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setSearchBarVisible(boolean searchBarVisible) {
        this.searchBarVisible = searchBarVisible;
        searchBar.setVisible(this.searchBarVisible);
    }

    public PathFinderController() {
        super();
        FXMLLoader fxmlPathLoader = new FXMLLoader(org.example.hakmana.view.component.PathFinderController.class.getResource("PathFinder.fxml"));
        fxmlPathLoader.setController(this);
        fxmlPathLoader.setRoot(this);
        try{
            fxmlPathLoader.load();
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    public boolean isSearchBarVisible() {
        return searchBarVisible;
    }

    public Label getPathTxt() {
        return pathTxt;
    }

    public void setPathTxt(String pathTxt) {
        this.pathTxt.setText(pathTxt);
    }

    public List<URL> getSceneList() {
        return sceneList;
    }

    //this method is called by the relevant scene controller when scene is change
    public void setBckBtnScene(URL bckBtnScene) {
        sceneList.add(bckBtnScene);
    }
    @FXML
    public void goBack(ActionEvent event) throws IOException {
        Parent root;
        URL listScenename = DashboardController.class.getResource("dashboard.fxml");//always load dashboard if list is empty
        if(!sceneList.isEmpty()) {
            //To remove current scene from the list.
            //Because current scene is also added to the list
            sceneList.removeLast();
            if(!sceneList.isEmpty()){
                listScenename = sceneList.getLast();
                sceneList.removeLast();
            }
            System.out.println(sceneList);
            System.out.println(listScenename);
        }else{
            System.out.println("list is empty");
        }
//
//        //This switch method especially load the DeviceMngmntSmmryScene and DevDetailedView
//        //Because they don't have controllers with the fxml and had to set manually
//        //Also had to call method to add componnet card and form details when calling the fxml file
//        if(listScenename==org.example.hakmana.view.scene.DeviceMngmntSmmryScene.class.getResource("DeviceMngmntSmmryScene.fxml")) {
//            // Load the FXML loader for the target scene
//            FXMLLoader deviceSmmryfxmlLoder = new FXMLLoader(listScenename);
//
//            //create DevDetailedViewController instance
//            DeviceMngmntSmmryScene deviceMngmntSmmryScene = new DeviceMngmntSmmryScene();
//
//            deviceSmmryfxmlLoder.setController(deviceMngmntSmmryScene);
//
//            root = deviceSmmryfxmlLoder.load();// Load the scene
//
//            //Using Setter Method
//            deviceMngmntSmmryScene.addLastComponent();
//            deviceMngmntSmmryScene.addComponent();
//        } else if (listScenename==org.example.hakmana.view.scene.DevDetailedViewController.class.getResource("DevDetailedView.fxml")) {
//            // Load the FXML loader for the target scene
//            FXMLLoader detailDevicefxmlLoder = new FXMLLoader(listScenename);
//
//            //create DevDetailedViewController instance
//            DevDetailedViewController devDetailedViewController = new DevDetailedViewController();
//
//            detailDevicefxmlLoder.setController(devDetailedViewController);
//
//            root = detailDevicefxmlLoder.load();// Load the scene
//
//            //Using Setter Method
//            devDetailedViewController.showDeviceDetail();
//        }
//        else
        if(listScenename== DashboardController.class.getResource("dashboard.fxml")){
            getNavPanelControllerPath().dashboardScene(event);
        }
        else if(listScenename== ReportHndlingController.class.getResource("ReportHndling.fxml")){
            FXMLLoader vboxLoader =new FXMLLoader(listScenename);
            ReportHndlingController reportHndlingController=ReportHndlingController.getInstance();
            vboxLoader.setController(reportHndlingController);
        }

    }
}
