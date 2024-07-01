package org.example.hakmana.model.noteHndling;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.example.hakmana.GetNoteController;
import org.example.hakmana.model.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class setTableColumnData {
    private static final Logger sqlLogger= (Logger) LogManager.getLogger(setTableColumnData.class);
    public ObservableList<GetNoteController> getNote() {
        DatabaseConnection instance = DatabaseConnection.getInstance();
        Connection conn = instance.getConnection();
        ObservableList<GetNoteController> list = FXCollections.observableArrayList();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select id,title,createdate from notes");

            while (rs.next()) {
                list.add(new GetNoteController(rs.getString(1),rs.getString(2),rs.getDate(3)));
            }
        } catch (SQLException e) {
            sqlLogger.error("An sql error occur",e);
            throw new RuntimeException(e);
        }

        return list;
    }

}
