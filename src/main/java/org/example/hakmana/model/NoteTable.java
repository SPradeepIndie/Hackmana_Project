package org.example.hakmana.model;

import java.sql.*;


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
    private PreparedStatement getPreparedStatement(String regNum,String tableValue) throws SQLException {
        PreparedStatement pr = null;
        pr = conn.prepareStatement("SELECT "+regNum+" FROM " +tableValue+ " WHERE status=?");

        return pr;
    }

   public ResultSet setPrValues(String regNum,String tableValue,String state) throws SQLException {
       PreparedStatement pr=getPreparedStatement(regNum,tableValue);
        pr.setString(1,state);
        ResultSet rs=pr.executeQuery();
        return rs;
   }

   public void deleteTableQueries(String ids,String titles){
       Statement st= null;
       try {
           st = conn.createStatement();
           st.executeUpdate("delete from notes where title='"+titles + "' and id='"+ids+"'");
           st.close();
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }

   }
    //view quries
   public ResultSet viewQueries(String titles,String ids){
       Statement str2 = null;
       ResultSet rs=null;
       try {
           str2 = conn.createStatement();
           rs= str2.executeQuery("Select id,username,notes,createdate,title from notes where title='" + titles + "' and id='"+ ids +"'");

       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
       return  rs;

   }
    //update quiry values
   public void updateTableQuiries(String id,String userName,String note,String title,String currentDate,String titles,String ids) throws SQLException{
       Statement st3 = null;
           st3 = conn.createStatement();
           st3.executeUpdate("update notes set id='" + id + "'" + ",username='" + userName + "',notes='" + note + "',title='" + title + "' ,createdate='" + currentDate + "' " + " where title='" + titles + "' and id='" + ids + "'");

   }


}
