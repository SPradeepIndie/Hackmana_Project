package org.example.hakmana.view.component;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.example.hakmana.view.scene.DeviceMngmntSmmryScene;
import org.example.hakmana.view.scene.OtherDevicesController;
import org.example.hakmana.view.scene.OverviewController;

import javax.print.attribute.standard.MediaSize;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class DeviceCategoryCardController extends AnchorPane implements Initializable{
    //For get the main dashboard body scroll pane
    private static javafx.scene.control.ScrollPane dashboardBodyScrollpaneD;
    private static PathFinderController dashboardPathFinderControllerD;

    //Injector for anchorpane For animation
    @FXML
    private AnchorPane root;

    //Injectors for button and the image
    @FXML
    private Button devInfoBtn;
    @FXML
    private ImageView devImage;

    //private variable to set iamge and the device naem
    private Image deviceImage;
    private String devName;
    private URL devCatSceneName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public DeviceCategoryCardController() {
        super();
        FXMLLoader fxmlLoader = new FXMLLoader(DeviceCategoryCardController.class.getResource("DeviceCategoryCard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public static ScrollPane getDashboardBodyScrollpaneD() {
        return dashboardBodyScrollpaneD;
    }
    public static void setDashboardBodyScrollpaneD(ScrollPane dashboardBodyScrollpaneD) {
        DeviceCategoryCardController.dashboardBodyScrollpaneD = dashboardBodyScrollpaneD;
    }
    public void setDevCatSceneName(URL devCatSceneName) {
        this.devCatSceneName = devCatSceneName;
    }

    public Image getDeviceImage() {
        return deviceImage;
    }

    public String getDevName() {
        return devName;
    }

    public URL getDevCatSceneName() {
        return devCatSceneName;
    }

    public static PathFinderController getDashboardPathFinderControllerD() {
        return dashboardPathFinderControllerD;
    }

    public static void setDashboardPathFinderControllerD(PathFinderController dashboardPathFinderControllerD) {
        DeviceCategoryCardController.dashboardPathFinderControllerD = dashboardPathFinderControllerD;
    }

    //    For animation of the cards
    @FXML
    private void onMouseEntered() {
        root.setScaleX(1.1);
        root.setScaleY(1.1);
    }
    @FXML
    private void onMouseExited() {
        root.setScaleX(1.0);
        root.setScaleY(1.0);
    }

    public void setDeviceImage(Image deviceImage) {
        this.deviceImage = deviceImage;
        devImage.setImage(deviceImage);
    }

    public void setDevName(String devName) {
        this.devName = devName;
        devInfoBtn.setText(this.devName);
    }

    public void disableBtn(boolean stateVal){
        devInfoBtn.setDisable(stateVal);
    }


//  For the DeviceMngmntSmmryScene load when device category button click
    public void DevInfoCall(){
        FXMLLoader vboxLoader;
        getDashboardPathFinderControllerD().setPathTxt("Device Management>"+getDevName());
        if(Objects.equals(getDevName(), "Other Devices")){
            vboxLoader = new FXMLLoader(OtherDevicesController.class.getResource("OtherDevices.fxml"));
            OtherDevicesController otherDevicesController=OtherDevicesController.getInstance();
            vboxLoader.setController(otherDevicesController);

            getDashboardPathFinderControllerD().setBckBtnScene("OtherDevices");
        }else {
            vboxLoader =new FXMLLoader(DeviceMngmntSmmryScene.class.getResource("DeviceMngmntSmmryScene.fxml"));

            DeviceMngmntSmmryScene deviceMngmntSmmryScene=DeviceMngmntSmmryScene.getInstance(); // Create controller instance
            vboxLoader.setController(deviceMngmntSmmryScene);

            deviceMngmntSmmryScene.setBodyScrollPaneD(getDashboardBodyScrollpaneD());//set the Scroll pane in Device management class

            getDashboardPathFinderControllerD().setBckBtnScene("DeviceMngmntSmmryScene");
            //After loading, set database name using setter
            deviceMngmntSmmryScene.setDbSelector(getDevName());
        }
        try{
            VBox vbox=vboxLoader.load();
            getDashboardBodyScrollpaneD().setContent(vbox);//this scroll pane id knows only that controller file

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}

