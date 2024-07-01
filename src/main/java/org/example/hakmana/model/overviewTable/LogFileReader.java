package org.example.hakmana.model.overviewTable;

import javafx.collections.ObservableList;
import org.example.hakmana.model.noteHndling.NoteTable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogFileReader {
    private static LogFileReader logFileReaderInstance=null;
    private LogFileReader(){

    }
    public static LogFileReader getInstance(){
        if(logFileReaderInstance==null){
            logFileReaderInstance = new LogFileReader();
        }
        return logFileReaderInstance;
    }
    public static ArrayList<LogEntry> readLogFile() throws IOException {
        ArrayList <LogEntry> logEntries=new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/logs/systemuser.log"))) {
            String line;
            while ((line = br.readLine()) != null) {
                LogEntry entry = parseLogEntry(line);
                if (entry != null) {
                    logEntries.add(entry);
                }
            }
        }
        return logEntries;
    }

    private static LogEntry parseLogEntry(String logLine) {

        String[] parts = logLine.split("/", 3);
        if (parts.length < 3) {
            return null;
        }
        String time=parts[0];
        String process=parts[1];
        String details=parts[2];
            return new LogEntry(details,time,process );

    }
}

