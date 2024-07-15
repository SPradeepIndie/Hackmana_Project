package org.example.hakmana.model.overviewTable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import org.example.hakmana.GetNoteController;

import java.io.IOException;

public class OverviewTableData {
    private final LogFileReader instance=LogFileReader.getInstance();

    public ObservableList<LogEntry> setColumnData(ChoiceBox<String> user, ChoiceBox<String> device, ChoiceBox<String> deviceId){
        try {
            ObservableList<LogEntry> list=FXCollections.observableArrayList(instance.readLogFile(user,device,deviceId));
            return  list;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
