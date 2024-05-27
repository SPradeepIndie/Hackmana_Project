package org.example.hakmana.view.scene;

import javafx.animation.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.hakmana.*;
import org.example.hakmana.model.DatabaseConnection;
import org.example.hakmana.view.component.FooterController;
import org.example.hakmana.view.component.HeaderController;
import org.example.hakmana.view.component.NavPanelController;
import org.example.hakmana.view.component.PathFinderController;
import org.example.hakmana.view.dialogBoxes.AddDeviceDialogController;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class DashboardController extends Component implements Initializable {
    @FXML
    public HeaderController headerController;//header custom component injector
    @FXML
    public NavPanelController navPanelController;//NavPanel custom component injector
    @FXML
    public FooterController footerController;
    @FXML
    public PathFinderController pathFinderController;
    @FXML
    private  VBox bodyComponet;//injector for VBox to expand
    @FXML
    private AnchorPane body;
    @FXML
    private Stage stage;
    @FXML
    private Button addDeviceBtn;
    private  TranslateTransition bodyExpand;//Animation object refernce
    private int selectedIndex;
    @FXML
    private AnchorPane parentAnchor;
    @FXML
    private HBox hbox1,hbox2,hbox3,hbox4,hbox5;
    @FXML
    private VBox vbox1,vbox2,vbox3,vbox4,vbox5;

    @FXML
    private TableView<getNoteController> table1;
    @FXML
    private TableColumn<getNoteController,String> col1;
    @FXML
    private TableColumn<getNoteController,String> col2;
    @FXML
    private TableColumn<getNoteController, Date> col3;
    private String titles;
    private String ids;
    private TextField titl1;
    private TextField user1;
    private TextField id1;
    private  TextArea note1;
    private  Label date1;


    public void initialize(URL location, ResourceBundle resources) {
        //automaticaly upadate the cards

        try {
            //create the connections
            DatabaseConnection instance = DatabaseConnection.getInstance();
            Connection conn = instance.getConnection();

            int count1;
            int count2;
            int count3;
            int count4;
            //get numbers of columns from database
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT t.TABLE_NAME\n" +
                    "FROM information_schema.TABLES t\n" +
                    "INNER JOIN information_schema.COLUMNS c ON t.TABLE_NAME = c.TABLE_NAME\n" +
                    "WHERE c.COLUMN_NAME = 'status' \n" +
                    "  AND t.TABLE_SCHEMA = 'hakmanaedm'");
            int size = 0;
            while (rs.next()) {
                size++;
            }

            String[] table = new String[size];
            int item = 0;
            rs.close();
            ResultSet rs0 = st.executeQuery("SELECT t.TABLE_NAME\n" +
                    "FROM information_schema.TABLES t\n" +
                    "INNER JOIN information_schema.COLUMNS c ON t.TABLE_NAME = c.TABLE_NAME\n" +
                    "WHERE c.COLUMN_NAME = 'status' \n" +
                    "  AND t.TABLE_SCHEMA = 'hakmanaedm';");
            while (rs0.next()) {

                table[item] = rs0.getString(1);
                item++;
                //System.out.println(rs0.getString(1));

            }
            rs0.close();
            //update the cards
            for (int j = 0; j < size; j++) {
                count1 = 0;
                count2 = 0;
                count3 = 0;
                count4 = 0;

                if ((table[j].equals("desktop")) || (table[j].equals("photocopymachine")) || (table[j].equals("monitor")) || (table[j].equals("multimediaProjector")) || (table[j].equals("laptop")) || (table[j].equals("ups"))) {

                    if (table[j].equals("desktop")) {
                        dashboardCardUpdate(count1,count2,count3,count4,table[j],"DesRegNum");
                    }
                    else if(table[j].equals("photocopymachine")){
                        dashboardCardUpdate(count1,count2,count3,count4,table[j],"PhotoCopyMachineRegNum");
                    }
                    else if(table[j].equals("monitor")){
                        dashboardCardUpdate(count1,count2,count3,count4,table[j],"MonitorRegNum");
                    }
                    else if(table[j].equals("multimediaProjector")){
                        dashboardCardUpdate(count1,count2,count3,count4,table[j],"MultimediaProjectorRegNum");
                    }
                    else if(table[j].equals("laptop")){
                        dashboardCardUpdate(count1,count2,count3,count4,table[j],"LaptopRegNum");
                    }
                    else if(table[j].equals("ups")){
                        dashboardCardUpdate(count1,count2,count3,count4,table[j],"UpsRegNum");
                    }
                }

            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            headerController.setFontSize("3em");
            headerController.setTitleMsg("Welcome");
            headerController.setUsernameMsg("Mr.Udara Mahanama");
            headerController.setDesignationMsg("Development Officer");
            navPanelController.setDashboardBorder();
            pathFinderController.setSearchBarVisible(false);

            //create the event listener to the navigation panel ToggleButton() method
            navPanelController.collapseStateProperty().addListener((observable, oldValue, newValue) ->{
                if(newValue){
                    expand();
                }else{
                    collapse();
                }
            });
            tableAdd();

        }
    }
    public void dashboardCardUpdate(int count1,int count2,int count3,int count4,String tableValue,String regNum ){
        DatabaseConnection instance=DatabaseConnection.getInstance();
        Connection conn=instance.getConnection();
        PreparedStatement pr = null;
        try {
            pr = conn.prepareStatement("SELECT "+regNum+" FROM " +tableValue+ " WHERE status=?");
            System.out.println("SELECT "+regNum+" FROM " +tableValue+ " WHERE status=?");
            pr.setString(1, "Active");
            ResultSet rs1 = pr.executeQuery();
            while (rs1.next()) {
                count1++;
            }
            Label label1 = new Label(tableValue +"\t\t\t"+ Integer.toString(count1));
            vbox5.setMargin(label1, new Insets(0, 0, 0, 10));
            vbox5.getChildren().add(label1);
            rs1.close();

            pr.setString(1, "Repairing");
            ResultSet rs4 = pr.executeQuery();
            while (rs4.next()) {
                count3++;
            }
            Label label4 = new Label(tableValue+"\t\t\t"+ Integer.toString(count3));
            vbox1.setMargin(label4, new Insets(0, 0, 0, 10));
            vbox1.getChildren().add(label4);
            rs4.close();

            pr.setString(1, "Not Assign");
            ResultSet rs5 = pr.executeQuery();
            while (rs5.next()) {
                count4++;
            }
            Label label5 = new Label(tableValue +"\t\t\t"+ Integer.toString(count4));
            vbox3.setMargin(label5, new Insets(0, 0, 0, 10));
            vbox3.getChildren().add(label5);
            rs5.close();

            pr.setString(1, "Inactive");
            ResultSet rs2 = pr.executeQuery();
            while (rs2.next()) {
                count2++;
            }
            Label label2 = new Label(tableValue + "\t\t\t "+Integer.toString(count2));
            vbox2.setMargin(label2, new Insets(0, 0, 0, 10));
            vbox2.getChildren().add(label2);
            rs2.close();
            Label label3=new Label(tableValue + "\t\t\t"+Integer.toString(count1+count2+count3+count4));
            vbox4.setMargin(label3, new Insets(0, 0, 0, 10));
            vbox4.getChildren().add(label3);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void tableAdd(){
                getDataController controller=new getDataController();
               ObservableList<getNoteController> list= controller.getNote();
                col1.setCellValueFactory(new PropertyValueFactory<getNoteController,String>("id"));
                col2.setCellValueFactory(new PropertyValueFactory<getNoteController,String>("title"));
                col3.setCellValueFactory(new PropertyValueFactory<getNoteController,Date>("date"));
                table1.setItems(list);


    }
    public void delete(){
        DatabaseConnection instance=DatabaseConnection.getInstance();
        Connection conn=instance.getConnection();
        int selectedValue=table1.getSelectionModel().getSelectedIndex();
        System.out.println(selectedValue);
        if(selectedValue>=0){
        Alert.AlertType type = Alert.AlertType.CONFIRMATION;
        Alert alert = new Alert(type, "");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(stage);
        alert.getDialogPane().setContentText("do you want to delete this note?");
        alert.getDialogPane().setHeaderText("confirmation!");
        Optional<ButtonType> reasult = alert.showAndWait();
                if(reasult.get()==ButtonType.OK) {
                       String  titles = table1.getItems().get(selectedValue).getTitle();
                       String ids= table1.getItems().get(selectedValue).getId();
                        table1.getItems().remove(selectedValue);
                    table1.getSelectionModel().clearSelection();
                        System.out.println(ids);
                        try {
                            Statement st=conn.createStatement();
                            st.executeUpdate("delete from notes where title='"+titles + "' and id='"+ids+"'");
                            st.close();
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }

                    }

                if(reasult.get()==ButtonType.CANCEL){
                    table1.getSelectionModel().clearSelection();
                }
                // Clear the selection after removing the item

            }
        else{
            JOptionPane.showMessageDialog(this,"please select a note","alert",JOptionPane.ERROR_MESSAGE);
        }
                }
    public void view(){
            DatabaseConnection instance=DatabaseConnection.getInstance();
            Connection conn=instance.getConnection();

            int selectedValue=table1.getSelectionModel().getSelectedIndex();
            System.out.println(selectedValue);
            if(selectedValue>=0) {

                titles = table1.getItems().get(selectedValue).getTitle();
                ids=table1.getItems().get(selectedValue).getId();
                try {
                    Statement str2 = conn.createStatement();
                    ResultSet rs = str2.executeQuery("Select id,username,notes,createdate,title from notes where title='" + titles + "' and id='"+ids+"'");
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("Scene/View.fxml"));
                    try {
                        DialogPane dialog = fxmlLoader.load();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    viewDialogController dialogpane = fxmlLoader.getController();
                    dialogpane.setIds(ids);
                    titl1=dialogpane.getTitle1();
                    note1=dialogpane.getNote1();
                    user1=dialogpane.getUsername1();
                    id1=dialogpane.getId1();
                    date1=dialogpane.getDate1();
                    rs.next();
                    titl1.setText(rs.getString(5));
                    note1.setText(rs.getString(3));
                    user1.setText(rs.getString(2));
                    id1.setText(rs.getString(1));
                    String date=rs.getDate(4).toString();
                    date1.setText(date);
                    rs.close();
                    str2.close();
                    titl1.setEditable(false);
                    note1.setEditable(false);
                    user1.setEditable(false);
                    id1.setEditable(false);
                    Dialog<ButtonType> dialog = new Dialog<>();
                    dialog.setDialogPane(dialogpane.getDialogPane2());
                    dialog.setTitle("ADD NOTE");
                    Optional<ButtonType> check = dialog.showAndWait();
                    if(check.get()==ButtonType.OK){
                        Statement st3= null;
                        try {
                            st3 = conn.createStatement();
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        LocalDate localDate=LocalDate.now();
                        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        String currentDate=localDate.format(formatter);
                        try {
                          //  System.out.println("update notes set id='"+id1.getText()+"'"+",username='"+user1.getText()+"',notes='"+note1.getText()+"',title='"+titl1.getText()+" ,createdate='"+currentDate+"' "+" where title='"+titles+"' and ");
                            st3.executeUpdate("update notes set id='"+id1.getText()+"'"+",username='"+user1.getText()+"',notes='"+note1.getText()+"',title='"+titl1.getText()+"' ,createdate='"+currentDate+"' "+" where title='"+titles + "' and id='"+ids+"'");
                            tableAdd();
                            st3.close();
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(this, "same title with same id not valid", "Rejected!", JOptionPane.ERROR_MESSAGE);

                        }
                       finally{
                            table1.getSelectionModel().clearSelection();

                        }

                    }
                    if(check.get()==ButtonType.CANCEL){
                        table1.getSelectionModel().clearSelection();
                    }


                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }


            }

            }
            
    private void Animation(double animStartPos,double animEndPos){
        bodyExpand = new TranslateTransition(Duration.millis(300), bodyComponet);
        bodyExpand.setFromX(animStartPos);
        bodyExpand.setToX(animEndPos); // expand VBox
        bodyExpand.setAutoReverse(true);
        bodyExpand.play();

    }
    public  void expand() {
        ///String cssRule = "-fx-min-width: 992px;";
        Double W1=bodyComponet.getWidth()+244;
        Animation(0, -244);
        bodyComponet.setMinWidth(W1);
        //bodyComponet.getStyleClass().add(cssRule);

    }
    public  void collapse() {
        Double W1=bodyComponet.getWidth()-244;
        Animation(-244, 0);
        bodyComponet.setMinWidth(W1);
    }
    public void addDeviceBtnDialogOpen(ActionEvent event) throws IOException {
        FXMLLoader addDevicefxmlLoad = new FXMLLoader();
        addDevicefxmlLoad.setLocation(DashboardController.class.getResource("DialogBox/AddDeviceDialog.fxml"));

        AddDeviceDialogController addDeviceDialogController=new AddDeviceDialogController();
        addDevicefxmlLoad.setController(addDeviceDialogController);

        DialogPane addDeviceDialogPane=addDevicefxmlLoad.load();

        Dialog<ButtonType> dialog=new Dialog<>();
        dialog.setDialogPane(addDeviceDialogPane);
        dialog.setTitle("Add Device");

        Optional<ButtonType> clickedButton=dialog.showAndWait();

    }

    public void Add(){

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(dialogPaneController.class.getResource("DialogBox/AddnoteDialog.fxml"));
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
            tableAdd();
        }

    }
}
