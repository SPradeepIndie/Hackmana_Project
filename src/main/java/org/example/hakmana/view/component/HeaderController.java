package org.example.hakmana.view.component;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HeaderController extends VBox implements Initializable {
    /*Injectors for Labels in fxml file*/
    private static final Logger otherErrorLogger= (Logger) LogManager.getLogger(HeaderController.class);
    @FXML
    public Text headerTitle;
    @FXML
    public Text userName;
    @FXML
    public Text designation;

    /*Variables for change the labels*/
    private String titleMsg;
    private String fontSize;
    private String usernameMsg;
    private String designationMsg;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public HeaderController() {
        super();
        FXMLLoader fxmlHeaderLoader = new FXMLLoader(org.example.hakmana.view.component.HeaderController.class.getResource("Header.fxml"));
        fxmlHeaderLoader.setController(this);
        fxmlHeaderLoader.setRoot(this);

        try{
            fxmlHeaderLoader.load();
        }
        catch(IOException e){
            otherErrorLogger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String getTitleMsg() {
        return titleMsg;
    }

    public String getFontSize() {
        return fontSize;
    }

    public String getUsernameMsg() {
        return usernameMsg;
    }

    public String getDesignationMsg() {
        return designationMsg;
    }

    public void setFontSize(String fontSize) {
        this.fontSize = fontSize;
    }

    public void setTitleMsg(String titleMsg) {
        String fontStyle = "-fx-font-size:"+fontSize+";";
        headerTitle.setStyle(fontStyle);
        this.titleMsg = titleMsg;
        headerTitle.setText(this.titleMsg);
    }

    public void setUsernameMsg(String usernameMsg) {
        this.usernameMsg = usernameMsg;
        userName.setText(this.usernameMsg);
    }

    public void setDesignationMsg(String designationMsg) {
        this.designationMsg = designationMsg;
        designation.setText(this.designationMsg);
    }
}
