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
import org.example.hakmana.view.scene.*;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class PathFinderController extends VBox implements Initializable {
    private Stack<String> sceneStack = new Stack<>(); // Create the scene stack
    private String currentScene;//hold the current scene
    private NavPanelController navPanelControllerPath;//reference for navpanel
    private DeviceCategoryCardController deviceCategoryCardController;//reference for device categorycard
    private boolean searchBarVisible;
    @FXML
    public HBox searchBar;
    @FXML
    private Label pathTxt;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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

    public DeviceCategoryCardController getDeviceCategoryCardController() {
        return deviceCategoryCardController;
    }

    public void setDeviceCategoryCardController(DeviceCategoryCardController deviceCategoryCardController) {
        this.deviceCategoryCardController = deviceCategoryCardController;
    }

    public void setSearchBarVisible(boolean searchBarVisible) {
        this.searchBarVisible = searchBarVisible;
        searchBar.setVisible(this.searchBarVisible);
    }
    public NavPanelController getNavPanelControllerPath() {
        return navPanelControllerPath;
    }

    public void setNavPanelControllerPath(NavPanelController navPanelControllerPath) {
        this.navPanelControllerPath = navPanelControllerPath;
    }

    public Label getPathTxt() {
        return pathTxt;
    }

    public void setPathTxt(String pathTxt) {
        this.pathTxt.setText(pathTxt);
    }

    //this method is called by the relevant scene controller when scene is change
    public void setBckBtnScene(String bckBtnScene) {
        sceneStack.push(bckBtnScene);
        currentScene=bckBtnScene;
    }
    @FXML
    public void goBack(ActionEvent event) throws IOException {
        FXMLLoader vboxLoader;
        if(!sceneStack.isEmpty()) {
            String listScenename=sceneStack.pop();
            //To remove current scene from the list.
            //Because current scene is also added to the list
            if(Objects.equals(listScenename, currentScene)){
                if(!sceneStack.isEmpty()){
                    listScenename=sceneStack.pop();
                }
            }
            switch (listScenename) {
                case "ReportHndling" -> getNavPanelControllerPath().reportHndlingScene();
                case "DeviceMngmnt" -> getNavPanelControllerPath().deviceMnagmnt();
                case "Overview" -> getNavPanelControllerPath().overviewScene();
                case "UserMngmnt" -> getNavPanelControllerPath().userMngmntScene();
                case "dashboard" -> getNavPanelControllerPath().dashboardScene(event);
                default -> getDeviceCategoryCardController().DevInfoCall();
            }

        }else{
            System.out.println("list is empty");
        }


    }

}
