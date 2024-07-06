package org.example.hakmana.model.overviewTable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.hakmana.GetNoteController;

import java.io.IOException;

public class OverviewTableData {
    private final LogFileReader instance=LogFileReader.getInstance();

    public ObservableList<LogEntry> setColumnData(){
        try {

            ObservableList<LogEntry> list=FXCollections.observableArrayList(instance.readLogFile());
            return  list;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
