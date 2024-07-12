package org.example.hakmana.view.dialogBoxes;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.example.hakmana.model.userMngmnt.SystemUser;
import org.example.hakmana.view.component.AddDevButtonController;

import javax.mail.MessagingException;
import java.sql.SQLException;
import java.util.Objects;
import java.util.regex.Pattern;

public class ForgotPasswrdDialog {
    private static final Logger otherErrorLogger= (Logger) LogManager.getLogger(AddDevButtonController.class);
    private static ForgotPasswrdDialog instance=null;
    public SystemUser systemUser =new SystemUser();
    private String usrEmail;

    //injectors for fxml components
    @FXML
    private VBox usrMailVbox,verificationCodeVbox,newPsswrdVbox;
    @FXML
    private Label outputLbl,psswrdOutputLbl;
    @FXML
    private TextField usrEmailField,verificationCodeField,newPsswrdFiled1,newPsswrdFiled2;
    @FXML
    private Button nextBtn,verfiyBtn,ClearBtn,ApplyBtn;


    public VBox getUsrMailVbox() {
        return usrMailVbox;
    }

    public VBox getVerificationCodeVbox() {
        return verificationCodeVbox;
    }

    public VBox getnewPsswrdVbox() {
        return newPsswrdVbox;
    }

    public Label getOutputLbl() {
        return outputLbl;
    }

    public TextField getUsrEmailField() {
        return usrEmailField;
    }

    public TextField getVerificationCodeField() {
        return verificationCodeField;
    }

    public TextField getNewPsswrdFiled1() {
        return newPsswrdFiled1;
    }

    public TextField getNewPsswrdFiled2() {
        return newPsswrdFiled2;
    }

    public void setUsrEmail(String usrEmail) {
        this.usrEmail = usrEmail;
    }

    public String getUsrEmail() {
        return usrEmail;
    }

    public Button getVerfiyBtn() {
        return verfiyBtn;
    }

    public void setVerfiyBtn(Button verfiyBtn) {
        this.verfiyBtn = verfiyBtn;
    }

    public Label getPsswrdOutputLbl() {
        return psswrdOutputLbl;
    }

    public Button getClearBtn() {
        return ClearBtn;
    }

    public Button getApplyBtn() {
        return ApplyBtn;
    }
    private ForgotPasswrdDialog(){
    }
    public static ForgotPasswrdDialog getInstance() {
        if(instance==null){
            instance=new ForgotPasswrdDialog();
            return instance;
        }
        return instance;
    }

    //get email and from the FrogotPsswrd.fxml
    public void storeUsrEmail(){
        setUsrEmail(getUsrEmailField().getText());
    }

    //check first validation in here and,
    //send the mail to the deviceUser.java model controller to check in database and get the result boolean
    public boolean isValidEmail(String email) throws SQLException {
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(email).matches() && systemUser.dbMailChecker(email);
    }

    //set the output in label
    public void usrOutput(String msg){
        getOutputLbl().setText(msg);
    }

    public void psswrdInfoOutput(String msg){
        getPsswrdOutputLbl().setText(msg);
    }

    //handle next button clicking
    @FXML
    public void nextBtnClick() throws SQLException, MessagingException {
        storeUsrEmail();
        if(isValidEmail(getUsrEmail())){
            getOutputLbl().setVisible(false);
            //System.out.println("valid");
            String code= systemUser.generateVerificationCode();
            //System.out.println(code);
            systemUser.dbUpdate(code);
            try {
                systemUser.sendEmail(code);
            } catch (Exception e) {
                otherErrorLogger.error(e.getMessage());
                throw new RuntimeException(e);
            }
            getVerificationCodeVbox().setDisable(false);
        }
        else{
            getOutputLbl().setVisible(true);
            usrOutput("Invalid email address");
        }
    }

    //handle OTP verification button
    @FXML
    public void verifyOTP() throws SQLException {
        if(systemUser.verifyWithDb(getVerificationCodeField().getText())){
            getVerificationCodeField().setStyle("-fx-border-color: green;-fx-border-width: 2px");
            getOutputLbl().setVisible(true);
            usrOutput("Verified! Now you can enter a new password");
            getnewPsswrdVbox().setDisable(false);
        }
        else{
            usrOutput("Incorrect code");
            getVerificationCodeField().setStyle("-fx-border-color: red;-fx-border-width: 2px");
        }

    }

    //check the password vbox is enable
    public void isVboxEnable() throws SQLException {
        if(!getnewPsswrdVbox().isDisable()){
            newPsswrd();
        }
    }

    //get the new password and send to the db
    @FXML
    public void newPsswrd() throws SQLException {
        if(getNewPsswrdFiled1().getText().equals(getNewPsswrdFiled2().getText()) && !Objects.equals(getNewPsswrdFiled1().getText(), "")){
            getNewPsswrdFiled1().setStyle("-fx-border-color: green;-fx-border-width: 2px");
            getNewPsswrdFiled2().setStyle("-fx-border-color: green;-fx-border-width: 2px");
            if(systemUser.pswrdChanger(getNewPsswrdFiled1().getText())){
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Password Changed");
                alert.setContentText("Password changed successfully!");
                alert.showAndWait();
                reset();
            }else
                getPsswrdOutputLbl().setText("Error!,Password not changed");
        }
        else{
            getPsswrdOutputLbl().setText("Passwords not matched");
            getNewPsswrdFiled1().setStyle("-fx-border-color: red;-fx-border-width: 2px");
            getNewPsswrdFiled2().setStyle("-fx-border-color: red;-fx-border-width: 2px");
        }
    }

    @FXML
    public void clearFileds(){
        getNewPsswrdFiled1().clear();
        getNewPsswrdFiled2().clear();
        getNewPsswrdFiled1().setStyle("-fx-border-width: 0px");
        getNewPsswrdFiled2().setStyle("-fx-border-width: 0px");
        getPsswrdOutputLbl().setText("");
    }

    public void reset(){
        clearFileds();
        getOutputLbl().setText("");
        getnewPsswrdVbox().setDisable(true);
        getVerificationCodeField().setStyle("-fx-border-width: 0px");
        getVerificationCodeField().clear();
        getVerificationCodeVbox().setDisable(true);
        getUsrEmailField().clear();
    }


}
