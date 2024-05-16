package org.example.hakmana.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mouse extends Devices{
    private String regNum;
    private String model="No";
    private String name;
    private String status;

    private String purchasedFrom;
    private String Connectivity;

    public Mouse[] getDevices() {
        DatabaseConnection conn=DatabaseConnection.getInstance();
        List<Mouse> mouse = new ArrayList<>();
        //pass query to the connection class
        String sql = "SELECT * FROM Mouse";

        try {
            // get result set from connection class
            ResultSet resultSet = conn.executeSt(sql);

            // Iterate through the result set and create Desktop and User objects
            while (resultSet.next()) {
                Mouse mouse1 = new Mouse();

                mouse1.setRegNum(resultSet.getString("MouseRegNum"));
                mouse1.setModel(resultSet.getString("model"));
                mouse1.setModel(resultSet.getString("name"));
                mouse1.setStatus(resultSet.getString("status"));

                mouse.add(mouse1);
            }
        }
        catch (SQLException e){
            System.out.println(e);
        }

        return mouse.toArray(new Mouse[0]);
    }

}
