package org.example.hakmana.view.component;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.example.hakmana.view.dialogBoxes.DialogPaneController;
import org.example.hakmana.view.scene.DevDetailedViewController;

import org.example.hakmana.model.DatabaseConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class DeviceInfoCardController extends AnchorPane implements Initializable {
    private Parent sceneRoot;
     @FXML
     private Button DetailedViewBtn;
     @FXML
     private TextField devIdTxt;
     @FXML
     private TextField brandTxt;
     @FXML
     private TextField userTxt;
     @FXML
     private VBox noteTxtArea;
     @FXML
     private AnchorPane root;
     @FXML
     private Pane addBtn;
     @FXML
     private Pane moreInfoBtn;
     @FXML
     private String devId;

     private   ArrayList<String> paneControllers=new ArrayList<String>();
     private ArrayList<String> username=new ArrayList<String>();
     private String deviceCat;
     private String user;
     private String brand;
     private String note;
     @Override
     public void initialize(URL url, ResourceBundle resourceBundle) {

     }
     public DeviceInfoCardController() {
          super();
          FXMLLoader fxmlFooterLoader = new FXMLLoader(DeviceInfoCardController.class.getResource("DeviceInfoCard.fxml"));
          fxmlFooterLoader.setController(this);
          fxmlFooterLoader.setRoot(this);

          try {
               fxmlFooterLoader.load();
          }
          catch (IOException e){
               throw new RuntimeException(e);
          }

     }

    public String getDevId() {
        return devId;
    }

    public void setDeviceCat(String deviceCat) {
        this.deviceCat = deviceCat;
    }

    public String getDeviceCat() {
        return deviceCat;
    }

    public String getUser() {
        return user;
    }

    public String getBrand() {
        return brand;
    }

    public String getNote() {
        return note;
    }

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

    public void setDevId(String devId) {
         //creating database connection
         DatabaseConnection instance = DatabaseConnection.getInstance();
         Connection conn = instance.getConnection();
         int r = 0;
         this.devId = devId;
         devIdTxt.setText(this.devId);
         //add notes to the deviceInfoCard
         try {
             Statement str = conn.createStatement();
             ResultSet rst = str.executeQuery("select title,notes from notes where id='" + devId + "'");
             ArrayList<Button> list = new ArrayList<Button>();
             while (rst.next()) {
                 String finalnote = rst.getString(2);
                 Button lab = new Button(Integer.toString(r + 1) + ")" + rst.getString(1));
                 lab.setStyle("-fx-background-color: white; -fx-margin:0px 5px 0px 0px;");
                 list.add(lab);
                 noteTxtArea.getChildren().add(list.get(r));
                 r++;
             }
             str.close();
             rst.close();
         } catch (SQLException e) {
             throw new RuntimeException(e);
         } finally {
             //adddefaultDetails();
                paneControllers.add(devId);
         }

     }
     //for get username and device id
        public void adddefaultDetails(){
             FXMLLoader dialogPaneFxml = new FXMLLoader(Objects.requireNonNull(DeviceInfoCardController.class.getResource("DialogBox/AddnoteDialog.fxml")));
             try {
                 // Load the FXML file and retrieve the controller
                 Parent root = dialogPaneFxml.load();
                 DialogPaneController controller = dialogPaneFxml.getController();

                 // Set the device ID name on the controller
                 controller.setSetDeviceIdName(devId);

                 // Add the root to the scene or scene node
                 // For example, if you're using a DialogPane:
                 // DialogPane dialogPane = new DialogPane();
                 // dialogPane.setContent(root);

                 // Show the dialog pane
                 // primaryStage.setScene(new Scene(root));
                 // primaryStage.show();

             } catch (IOException e) {
                 e.printStackTrace(); // Handle the exception appropriately
             }
         }


    public void setUser(String user) {
          this.user = user;
          userTxt.setText(this.user);
         //adddefaultDetails();
         username.add(user);


     }

    public void setBrand(String brand) {
          this.brand = brand;
          brandTxt.setText(this.brand);
     }


    //handle more info button to load DevDetailedView scene
     @FXML
     public void DetailedViewSceneLoad(ActionEvent event) throws IOException {
         // Load the FXML loader for the target scene
          FXMLLoader detailDevicefxmlLoder = new FXMLLoader(Objects.requireNonNull(org.example.hakmana.view.scene.DevDetailedViewController.class.getResource("DevDetailedView.fxml")));
          //create DevDetailedViewController instance
          DevDetailedViewController devDetailedViewController=new DevDetailedViewController();
         detailDevicefxmlLoder.setController(devDetailedViewController);
          sceneRoot=detailDevicefxmlLoder.load();// Load the scene

          //Using Setter Method
         devDetailedViewController.setDeviceSelector(getDeviceCat());
         devDetailedViewController.setDevRegNum(getDevId());
         devDetailedViewController.showDeviceDetail();

          Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          Scene scene = new Scene(sceneRoot);
          stage.setScene(scene);
          stage.show();
     }

     //note adding dialog box

     public void popupdialog() {
          FXMLLoader noteFxmlLoader = new FXMLLoader();
          noteFxmlLoader.setLocation(getClass().getResource("Scene/AddnoteDialog.fxml"));
          try {
               DialogPane dialogPane = noteFxmlLoader.load();
          } catch (IOException e) {
               throw new RuntimeException(e);
          }
         DialogPaneController dialogpane1;
         dialogpane1  = noteFxmlLoader.getController();
         dialogpane1.getEditButton().setVisible(false);
         dialogpane1.setSetDeviceIdName(paneControllers.get(0));
         dialogpane1.setUser(username.get(0));
          Dialog<ButtonType> dialog = new Dialog<>();
          dialog.setDialogPane(dialogpane1.getDialogpane1());
          dialog.setTitle("ADD NOTE");
          Optional<ButtonType> check = dialog.showAndWait();
          if(check.get()==ButtonType.CANCEL){
          }

     }

}
