package org.example.hakmana.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class NoteTable {
    private static NoteTable noteInstance=null;
    private DatabaseConnection instance;
    Connection conn;
    private NoteTable(){
        instance = DatabaseConnection.getInstance();
        conn = instance.getConnection();
    }

    public static NoteTable getInstance(){
        if(noteInstance==null){
            noteInstance = new NoteTable();
        }
        return noteInstance;
    }

    public ResultSet getTableNamesQuiries(){
        Statement st = null;
        ResultSet rs;
        try {
            st = conn.createStatement();
          rs = st.executeQuery("""
                        SELECT t.TABLE_NAME
                        FROM information_schema.TABLES t
                        INNER JOIN information_schema.COLUMNS c ON t.TABLE_NAME = c.TABLE_NAME
                        WHERE c.COLUMN_NAME = 'status'\s
                          AND t.TABLE_SCHEMA = 'hakmanaedm'""");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return  rs;
    }
}
