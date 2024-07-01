package org.example.hakmana.model.overviewTable;

public class LogEntry {
    private String timestamp;
    private String process;
    private String details;

    public LogEntry(String details, String timestamp, String process) {
        this.details = details;
        this.timestamp = timestamp;
        this.process = process;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getProcess() {
        return process;
    }

    public String getDetails() {
        return details;
    }
}