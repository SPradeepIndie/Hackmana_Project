package org.example.hakmana;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import java.net.URL;
import java.util.ResourceBundle;

public class OverviewController implements Initializable{
        @FXML
        private HeaderController headerController;
        @FXML


        private NavPanelController navPanelController;//NavPanel custom component injector
        @FXML
        private VBox bodyComponet;//injector for VBox to expand

        private TranslateTransition bodyExpand;//Animation object refernce

        @FXML
        private AnchorPane parentAnchor;
        public void initialize(URL location, ResourceBundle resources) {
            headerController.setFontSize("2.5em");
            headerController.setTitleMsg("Overview History");
            navPanelController.setOverviewHistryBorder();
            //create the event listener to the navigation panel ToggleButton() method
            navPanelController.collapseStateProperty().addListener((observable, oldValue, newValue) ->{
                if(newValue){
                    expand();
                }else{
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

}
