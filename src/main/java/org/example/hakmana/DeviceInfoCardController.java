package org.example.hakmana;

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
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.hakmana.model.DatabaseConnection;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class DeviceInfoCardController extends AnchorPane implements Initializable {
     private Stage stage;
     private Scene scene;
     private Parent sceneroot;
     @FXML
     private Button DetailedViewBtn;
     @FXML
     private TextField devIdTxt;
     @FXML
     private TextField brandTxt;
     @FXML
     private TextField userTxt;
     @FXML
     private TextArea noteTxtArea;
     @FXML
     private AnchorPane root;
     @FXML
     private Pane addBtn;
     @FXML
     private Pane moreInfoBtn;
     @FXML
     private String devId;
     private String user;
     private String brand;
     private String note;
     @Override
     public void initialize(URL url, ResourceBundle resourceBundle) {

     }
     public DeviceInfoCardController() {
          super();
          FXMLLoader fxmlFooterLoader = new FXMLLoader(getClass().getResource("Component/DeviceInfoCard.fxml"));
          fxmlFooterLoader.setController(this);
          fxmlFooterLoader.setRoot(this);

          try {
               fxmlFooterLoader.load();
          }
          catch (IOException e){
               throw new RuntimeException(e);
          }
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

     public String getDevId() {
          return devId;
     }

     public void setDevId(String devId) {
          this.devId = devId;
          devIdTxt.setText(this.devId);
     }

     public String getUser() {
          return user;
     }

     public void setUser(String user) {
          this.user = user;
          userTxt.setText(this.user);
     }

     public String getBrand() {
          return brand;
     }

     public void setBrand(String brand) {
          this.brand = brand;
          brandTxt.setText(this.brand);
     }

     public String getNote() {
          return note;
     }

     public void setNote(String note) {
          this.note = note;
          noteTxtArea.setText(this.note);
     }

     //handle more info button to load DevDetailedView scene
     public void DetailedViewSceneLoad(ActionEvent event) throws IOException {
          Parent sceneroot = FXMLLoader.load(getClass().getResource("Scene/DevDetailedView.fxml"));
          stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          scene = new Scene(sceneroot);
          stage.setScene(scene);
          stage.show();
     }


     public void popupdialog() {


          FXMLLoader fxmlLoader = new FXMLLoader();
          fxmlLoader.setLocation(getClass().getResource("Scene/dialogbox.fxml"));
          try {
               DialogPane dialogPane = fxmlLoader.load();
          } catch (IOException e) {
               throw new RuntimeException(e);
          }

          dialogPaneController dialogpane = fxmlLoader.getController();
          Dialog<ButtonType> dialog = new Dialog<>();
          dialog.setDialogPane(dialogpane.getDialogpane1());
          dialog.setTitle("ADD NOTE");
          Optional<ButtonType> check = dialog.showAndWait();
          if(check.get()==ButtonType.CANCEL){

          }

     }

}
