package org.example.hakmana.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class OtherDevices {
    private DatabaseConnection databaseConnection ;
    private static Connection connection;
    private static List<String> excludedTables;
    private static List<String> devices= new ArrayList<>();
    private static boolean devicesLoaded = false;

    //table column values
    private int num;
    private String dev;
    private int totalDev;
    private int numActiveDev;
    private int numInactiveDev;
    private int numRepairingDev;
    private ObservableList<OtherDevices> observableOtherDevices = FXCollections.observableArrayList();
    private boolean tblRowLoaded=false;

    /*-----------------constructors for this class---------------*/
    public OtherDevices() {

    }

    //This constructor Especially for the table data  inserting purpose
    public OtherDevices(int num, String dev, int totalDev, int numActiveDev, int numInactiveDev, int numRepairingDev) {
        this.num = num;
        this.dev = dev;
        this.totalDev = totalDev;
        this.numActiveDev = numActiveDev;
        this.numInactiveDev = numInactiveDev;
        this.numRepairingDev = numRepairingDev;
    }

    /*---------------------getters and seetters--------------------*/
    public static List<String> getExcludedTables() {
        return excludedTables;
    }
    public List<String> getDevices() {
        //create the database connection
        databaseConnection = DatabaseConnection.getInstance();
        connection = databaseConnection.getConnection();
        //this list contains the main devices list
        excludedTables = Arrays.asList("desktop","monitors","laptop","printer","ups","multimediaprojector","photocopymachine");

        if (!devicesLoaded) {
            // Fetch table names and populate devices list
            fetchTableNames();
            devicesLoaded = true;
        }
        return devices;
    }

    //These getters are especially for the table data inserting
    public int getNumActiveDev() {
        return numActiveDev;
    }
    public int getNumInactiveDev() {
        return numInactiveDev;
    }
    public int getNumRepairingDev() {
        return numRepairingDev;
    }
    public int getNum() {
        return num;
    }
    public String getDev() {
        return dev;
    }
    public int getTotalDev() {
        return totalDev;
    }
    public boolean isTblRowLoaded() {
        return tblRowLoaded;
    }
    public ObservableList<OtherDevices> getObservableOtherDevices() {
        if (!isTblRowLoaded()) {
            setOtherDeviceTblDetails();
            tblRowLoaded=true;
        }
        return observableOtherDevices;
    }

    //This method fetch the other devices list from the database
    private static void fetchTableNames(){
        String sql = "SELECT TABLE_NAME FROM information_schema.TABLES WHERE TABLE_SCHEMA = 'hakmanaedm'AND TABLE_NAME NOT IN(";
        for (int i = 0; i < getExcludedTables().size(); i++) {
            sql += "'" + getExcludedTables().get(i) + "'";
            if (i != getExcludedTables().size() - 1) {
                sql += ",";
            }
        }
        sql += ");";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                devices.add(resultSet.getString("TABLE_NAME"));
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //This method set the rows of the table and add to the Observable list
    private void setOtherDeviceTblDetails() {
            int row = 1;// Start adding devices from row 1 (after header row)
            for (String d : getDevices()) {
                int numActiveDev = 0;
                int numInactiveDev = 0;
                int numRepairingDev = 0;

                try {
                    // SQL query without placeholders
                    String sqlActive = "SELECT COUNT(*) AS num_active_dev FROM " + d + " WHERE status = 'active';";
                    String sqlInactive = "SELECT COUNT(*) AS num_inactive_dev FROM " + d + " WHERE status = 'inactive';";
                    String sqlRepairing = "SELECT COUNT(*) AS num_repairing_dev FROM " + d + " WHERE status = 'repairing';";

                    // Execute queries for each status
                    ResultSet resultSet;

                    // Active devices
                    PreparedStatement preparedStatementActive = connection.prepareStatement(sqlActive);
                    resultSet = preparedStatementActive.executeQuery();
                    if (resultSet.next()) {
                        numActiveDev = resultSet.getInt("num_active_dev");
                    }
                    resultSet.close();
                    preparedStatementActive.close();

                    // Inactive devices
                    PreparedStatement preparedStatementInactive = connection.prepareStatement(sqlInactive);
                    resultSet = preparedStatementInactive.executeQuery();
                    if (resultSet.next()) {
                        numInactiveDev = resultSet.getInt("num_inactive_dev");
                    }
                    resultSet.close();
                    preparedStatementInactive.close();

                    // Repairing devices
                    PreparedStatement preparedStatementRepairing = connection.prepareStatement(sqlRepairing);
                    resultSet = preparedStatementRepairing.executeQuery();
                    if (resultSet.next()) {
                        numRepairingDev = resultSet.getInt("num_repairing_dev");
                    }
                    resultSet.close();
                    preparedStatementRepairing.close();
                } catch (SQLException e) {
                    e.getMessage();
                }

                // Print results or use them as needed
                //System.out.println(numActiveDev+ "\t"+ numInactiveDev+ "\t"+ numRepairingDev);
                observableOtherDevices.add(new OtherDevices(row, d,
                        numActiveDev+numInactiveDev+numRepairingDev,
                        numActiveDev, numInactiveDev, numRepairingDev));
                row++;
            }
    }
}

