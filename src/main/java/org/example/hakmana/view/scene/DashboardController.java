package org.example.hakmana.view.scene;

import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.hakmana.GetNoteController;
import org.example.hakmana.model.NoteTable;
import org.example.hakmana.model.setTableColumnData;
import org.example.hakmana.view.component.FooterController;
import org.example.hakmana.view.component.HeaderController;
import org.example.hakmana.view.component.NavPanelController;
import org.example.hakmana.view.component.PathFinderController;
import org.example.hakmana.view.dialogBoxes.AddDeviceDialogController;
import org.example.hakmana.view.dialogBoxes.AddNoteDialogPane;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class DashboardController extends Component implements Initializable {
    private static DashboardController instance=null;
    @FXML
    public HeaderController headerController;//header custom component injector
    @FXML
    public NavPanelController navPanelController;//NavPanel custom component injector
    public AnchorPane parentAnchor;
    @FXML
    private PathFinderController pathFinderControlle;
    public FooterController footerController;
    @FXML
    public PathFinderController pathFinderController;
    @FXML
    public ScrollPane bodyScrollPane;
    @FXML
    private  VBox bodyComponet;//injector for VBox to expand
    @FXML
    private Stage stage;
    @FXML
    private VBox vbox1,vbox2,vbox3,vbox4,vbox5;

    @FXML
    private TableView<GetNoteController> table1;
    @FXML
    private TableColumn<GetNoteController,String> col1;
    @FXML
    private TableColumn<GetNoteController,String> col2;
    @FXML
    private TableColumn<GetNoteController, Date> col3;
    private String titles;
    private String ids;
    private TextField titl1;
    private TextField user1;
    private TextField id1;
    private  TextArea note1;
    private  Label date1;
    private Button editButton;
    private Button updateButton;
    private NoteTable noteInstance;

    private DashboardController(){}

    public static DashboardController getInstance() {
        if(instance==null){
            instance=new DashboardController();
            return instance;
        }
        return instance;
    }

    public void initialize(URL location, ResourceBundle resources) {
        //automaticaly upadate the cards
        noteInstance=NoteTable.getInstance();
            //create the connections
            int count1;
            int count2;
            int count3;
            int count4;
            //get numbers of columns from database
            int size =noteInstance.getTableNamesQuiries("tablesize") ;
            String[] table ;
            table=noteInstance.getArray(size);
            //update the cards
            for (int j = 0; j < size; j++) {
                count1 = 0;
                count2 = 0;
                count3 = 0;
                count4 = 0;

                if ((table[j].equals("desktop")) || (table[j].equals("photocopymachine")) || (table[j].equals("monitor")) || (table[j].equals("multimediaProjector")) || (table[j].equals("laptop")) || (table[j].equals("ups"))) {

                    switch (table[j]) {
                        case "desktop" -> dashboardCardUpdate(count1, count2, count3, count4, table[j], "DesRegNum");
                        case "photocopymachine" ->
                                dashboardCardUpdate(count1, count2, count3, count4, table[j], "PhotoCopyMachineRegNum");
                        case "monitor" ->
                                dashboardCardUpdate(count1, count2, count3, count4, table[j], "MonitorRegNum");
                        case "multimediaProjector" ->
                                dashboardCardUpdate(count1, count2, count3, count4, table[j], "MultimediaProjectorRegNum");
                        case "laptop" -> dashboardCardUpdate(count1, count2, count3, count4, table[j], "LaptopRegNum");
                        case "ups" -> dashboardCardUpdate(count1, count2, count3, count4, table[j], "UpsRegNum");
                    }
                }

            }

            headerController.setFontSize("3em");
            headerController.setTitleMsg("Welcome");
            headerController.setUsernameMsg("Mr.Udara Mahanama");
            headerController.setDesignationMsg("Development Officer");

            //set the dashboard scroll pane in navigation panel
            navPanelController.setDashboardBodyScrollpane(bodyScrollPane);
            //set the pathfinder controller in navigation panel

            NavPanelController.setDashboardpathFinderController(pathFinderController);


            //set the navigation panel controller in pathfinder controller
            pathFinderController.setNavPanelControllerPath(navPanelController);
            pathFinderController.setSearchBarVisible(false);
            pathFinderController.setBckBtnScene("dashboard");

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

    public void dashboardCardUpdate(int count1, int count2, int count3, int count4, String tableValue, String regNum ){
        noteInstance=NoteTable.getInstance();

        count1= noteInstance.setPrValues(regNum,tableValue,"Active");
        Label label1 = new Label(tableValue +"\t\t\t"+ count1);
        VBox.setMargin(label1, new Insets(0, 0, 0, 10));
        vbox5.getChildren().add(label1);

        count3 = noteInstance.setPrValues(regNum,tableValue,"Repairing");
        Label label4 = new Label(tableValue+"\t\t\t"+ Integer.toString(count3));
        VBox.setMargin(label4, new Insets(0, 0, 0, 10));
        vbox1.getChildren().add(label4);

        count2 =noteInstance.setPrValues(regNum,tableValue,"Inactive");
        Label label2 = new Label(tableValue + "\t\t\t "+Integer.toString(count2));
        VBox.setMargin(label2, new Insets(0, 0, 0, 10));
        vbox2.getChildren().add(label2);

        count4 =noteInstance.setPrValues(regNum,tableValue,"NotAssign");
        Label label0 = new Label(tableValue + "\t\t\t "+Integer.toString(count4));
        VBox.setMargin(label0, new Insets(0, 0, 0, 10));
        vbox3.getChildren().add(label0);


        Label label3=new Label(tableValue + "\t\t\t"+Integer.toString(count1+count2+count3+count4));
        VBox.setMargin(label3, new Insets(0, 0, 0, 10));
        vbox4.getChildren().add(label3);

    }
    
    public void tableAdd(){
        setTableColumnData controller=new setTableColumnData();
        ObservableList<GetNoteController> list= controller.getNote();
        col1.setCellValueFactory(new PropertyValueFactory<GetNoteController,String>("id"));
        col2.setCellValueFactory(new PropertyValueFactory<GetNoteController,String>("title"));
        col3.setCellValueFactory(new PropertyValueFactory<GetNoteController,Date>("date"));
        table1.setItems(list);

    }

    public void delete(){
        noteInstance=NoteTable.getInstance();
        int selectedValue=table1.getSelectionModel().getSelectedIndex();
        System.out.println(selectedValue);
        if(selectedValue>=0){
        Alert.AlertType type = Alert.AlertType.CONFIRMATION;
        Alert alert = new Alert(type, "");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(stage);
        alert.getDialogPane().setContentText("do you want to delete this note?");
        alert.getDialogPane().setHeaderText("confirmation!");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() &&result.get()==ButtonType.OK) {
               String  titles = table1.getItems().get(selectedValue).getTitle();
               String ids= table1.getItems().get(selectedValue).getId();
                table1.getItems().remove(selectedValue);
            table1.getSelectionModel().clearSelection();
                noteInstance.deleteTableQueries(ids,titles);
            }
        if(result.isPresent() && result.get()==ButtonType.CANCEL){
            table1.getSelectionModel().clearSelection();
        }
        }
        else{
            JOptionPane.showMessageDialog(this,"please select a note","alert",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void view(){
            int selectedValue=table1.getSelectionModel().getSelectedIndex();
            String[] data=new String[5];
            System.out.println(selectedValue);
            if(selectedValue>=0) {
                String titles = table1.getItems().get(selectedValue).getTitle();
                String ids = table1.getItems().get(selectedValue).getId();

                     data = noteInstance.viewQueries(titles,ids);
                    FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(AddNoteDialogPane.class.getResource("AddnoteDialog.fxml")));
                   
                        DialogPane dialog1 = fxmlLoader.load();
                        AddNoteDialogPane dialogpane = fxmlLoader.getController();
                        dialogpane.getEditButton().setVisible(true);
                        editButton = dialogpane.getEditButton();
                        dialogpane.getAddNote().setVisible(false);
                        dialogpane.getUpdateButton().setVisible(false);
                        updateButton = dialogpane.getUpdateButton();
                        dialogpane.setIds(ids);
                        TextField titl1 = dialogpane.getTitle();
                        TextArea note1 = dialogpane.getNote();
                        TextField user1 = dialogpane.getUsername();
                        TextField id1 = dialogpane.getDeviceId();
                        Label date1 = dialogpane.getDate();
                        titl1.setText(data[4]);
                        note1.setText(data[2]);
                        user1.setText(data[1]);
                        id1.setText(data[0]);
                        String date = data[3].toString();
                        date1.setText(date);
                        titl1.setEditable(false);
                        note1.setEditable(false);
                        user1.setEditable(false);
                        id1.setEditable(false);
                        Dialog<ButtonType> dialog = new Dialog<>();
                        dialog.setDialogPane(dialogpane.getDialogpane1());
                        dialog.setTitle("ADD NOTE");
                        editButton.setOnAction(e -> {
                            id1.setEditable(true);
                            titl1.setEditable(true);
                            user1.setEditable(true);
                            note1.setEditable(true);
                            updateButton.setVisible(true);
                        });
                        updateButton.setOnAction(e1 -> {
                            LocalDate localDate = LocalDate.now();
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                            String currentDate = localDate.format(formatter);
                            try {
                                noteInstance.updateTableQuiries(id1.getText(),user1.getText(),note1.getText() ,titl1.getText(),currentDate,titles,ids);
                                tableAdd();
                                JOptionPane.showMessageDialog(this, "update successful!", "successful", JOptionPane.INFORMATION_MESSAGE);
                            } catch (SQLException event) {
                                JOptionPane.showMessageDialog(this, "same title with same id not valid", "Rejected!", JOptionPane.ERROR_MESSAGE);
                            } finally {
                                table1.getSelectionModel().clearSelection();
                            }
                        });
                        Optional<ButtonType> check= dialog.showAndWait();
                        if(check.isPresent() && check.get()==ButtonType.CLOSE) {

                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }




            }


            }
    }

    private void Animation(double animStartPos,double animEndPos){
        //Animation object refernce
        TranslateTransition bodyExpand = new TranslateTransition(Duration.millis(300), bodyComponet);
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
        addDevicefxmlLoad.setLocation(org.example.hakmana.view.dialogBoxes.AddDeviceDialogController.class.getResource("AddDeviceDialog.fxml"));

        AddDeviceDialogController addDeviceDialogController=AddDeviceDialogController.getInstance();
        addDevicefxmlLoad.setController(addDeviceDialogController);

        DialogPane addDeviceDialogPane=addDevicefxmlLoad.load();

        Dialog<ButtonType> dialog=new Dialog<>();
        dialog.setDialogPane(addDeviceDialogPane);
        dialog.setTitle("Add Device");

        Optional<ButtonType> clickedButton=dialog.showAndWait();

    }

    public void Add(){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(org.example.hakmana.view.dialogBoxes.AddNoteDialogPane.class.getResource("AddnoteDialog.fxml"));
        try {
            DialogPane dialogPane = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        AddNoteDialogPane dialogpane = fxmlLoader.getController();
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(dialogpane.getDialogpane1());
        dialog.setTitle("ADD NOTE");
        dialogpane.getEditButton().setVisible(false);
        dialogpane.getUpdateButton().setVisible(false);
        Optional<ButtonType> check = dialog.showAndWait();
        if(check.isPresent() && check.get()==ButtonType.CLOSE){
            tableAdd();
        }
    }

}

