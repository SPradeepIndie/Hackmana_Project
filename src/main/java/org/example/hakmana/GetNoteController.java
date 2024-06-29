package org.example.hakmana;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.example.hakmana.view.component.AddDevButtonController;

import java.sql.*;

public class GetNoteController {
    private String id;
    private String title;
    private Date date;

    public GetNoteController(String id, String title, Date date) {
        this.id = id;
        this.title = title;
        this.date = date;

    }
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }


}


