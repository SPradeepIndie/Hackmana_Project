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
    //get the size of the array
    public int getTableNamesQuiries(String returnValue){
        int size=0;
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
            while (rs.next()) {
                size++;
            }
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return  size;
    }
    //return an array consists with table names
    public String[] getArray(int size){
        String[] items=new String[size];
        int item=0;
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
            while (rs.next()) {
                items[item]=rs.getString(1);
                item++;
            }
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return items;

    }
    //get prepared statements
    private PreparedStatement getPreparedStatement(String regNum,String tableValue) throws SQLException {
        PreparedStatement pr = null;
        pr = conn.prepareStatement("SELECT "+regNum+" FROM " +tableValue+ " WHERE status=?");

        return pr;
    }

   public int setPrValues(String regNum,String tableValue,String state) {
       int count1=0;
       PreparedStatement pr= null;
       try {
           pr = getPreparedStatement(regNum,tableValue);
           pr.setString(1,state);
           ResultSet rs=pr.executeQuery();
           while (rs.next()) {
               count1++;
           }
           rs.close();
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }

       return count1;
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

   public void createNoteQuries(String id,String name,String note,Date date,String title) throws SQLException {
       PreparedStatement notesse = null;
       notesse = conn.prepareStatement("insert into notes values(?,?,?,?,?)");
       notesse.setString(1, id);
       notesse.setString(2, name);
       notesse.setString(3, note);
       notesse.setDate(4,date);
       notesse.setString(5,title);
       notesse.executeUpdate();
       notesse.close();
   }


}
