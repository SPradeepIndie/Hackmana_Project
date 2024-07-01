package org.example.hakmana.view.scene;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.example.hakmana.GetNoteController;
import org.example.hakmana.model.overviewTable.LogEntry;
import org.example.hakmana.model.overviewTable.OverviewTableData;
import org.example.hakmana.view.component.AddDevButtonController;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;


public class OverviewController implements Initializable {
    @FXML
    TableView<LogEntry> historyTable;
    @FXML
    TableColumn<LogEntry,String> dateColumn;
    @FXML
    TableColumn<LogEntry,String> processColumn;
    @FXML
    TableColumn<LogEntry,String> detailsColumn;
    private static OverviewController instance=null;

    private OverviewController(){}

    public static OverviewController getInstance() {
        if(instance== null){
            instance=new OverviewController();
            return instance;
        }


        return instance;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("hiiiiiiiii");
        OverviewTableData controller=new OverviewTableData();
        ObservableList<LogEntry> list= controller.setColumnData();
        dateColumn.setCellValueFactory(new PropertyValueFactory<LogEntry,String>("timestamp"));
        processColumn.setCellValueFactory(new PropertyValueFactory<LogEntry,String>("process"));
        detailsColumn.setCellValueFactory(new PropertyValueFactory<LogEntry, String>("details"));
        historyTable.setItems(list);


    }


}
