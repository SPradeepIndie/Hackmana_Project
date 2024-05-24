package org.example.hakmana.view.component;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.hakmana.view.scene.DevDetailedViewController;
import org.example.hakmana.view.scene.DeviceMngmntSmmryScene;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class PathFinderController extends VBox implements Initializable {
    private static final ArrayList<String> sceneList = new ArrayList<>();
    private boolean searchBarVisible;

    @FXML
    public HBox searchBar;
    @FXML
    private Button backBttn;
    @FXML
    private Label pathTxt;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public boolean isSearchBarVisible() {
        return searchBarVisible;
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

    public Label getPathTxt() {
        return pathTxt;
    }

    public void setPathTxt(String pathTxt) {
        this.pathTxt.setText(pathTxt);
    }

    public List<String> getSceneList() {
        return sceneList;
    }

    //this method is called by the relevant scene controller when scene is change
    public void setBckBtnScene(String bckBtnScene) {
        sceneList.add(bckBtnScene);
    }
    @FXML
    public void goBack(ActionEvent event) throws IOException {
        Parent root;
        String listScenename = "Scene/dashboard.fxml";//always load dashboard if list is empty
        if(!sceneList.isEmpty()) {
            //To remove current scene from the list.
            //Because current scene is also added to the list
            sceneList.removeLast();
            if(!sceneList.isEmpty()){
                listScenename = sceneList.getLast();
                sceneList.removeLast();
            }

        }else{
            System.out.println("list is empty");
        }

        //This swith method especially load the DeviceMngmntSmmryScene and DevDetailedView
        //Because they don't have controllers with the fxml and had to set manually
        //Also had to call method to add componnet card and form details when calling th efxml file
        switch (listScenename){
            case("Scene/DeviceMngmntSmmryScene.fxml"):
                // Load the FXML loader for the target scene
                FXMLLoader deviceSmmryfxmlLoder = new FXMLLoader(Objects.requireNonNull(getClass().getResource("Scene/DeviceMngmntSmmryScene.fxml")));

                //create DevDetailedViewController instance
                DeviceMngmntSmmryScene deviceMngmntSmmryScene=new DeviceMngmntSmmryScene();

                deviceSmmryfxmlLoder.setController(deviceMngmntSmmryScene);

                root=deviceSmmryfxmlLoder.load();// Load the scene

                //Using Setter Method
                deviceMngmntSmmryScene.addLastComponent();
                deviceMngmntSmmryScene.addComponent();
                break;
            case ("Scene/DevDetailedView.fxml"):
                // Load the FXML loader for the target scene
                FXMLLoader detailDevicefxmlLoder = new FXMLLoader(Objects.requireNonNull(getClass().getResource("Scene/DevDetailedView.fxml")));

                //create DevDetailedViewController instance
                DevDetailedViewController devDetailedViewController=new DevDetailedViewController();

                detailDevicefxmlLoder.setController(devDetailedViewController);

                root=detailDevicefxmlLoder.load();// Load the scene

                //Using Setter Method
                devDetailedViewController.showDeviceDetail();
                break;
            default:
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(listScenename)));

        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
