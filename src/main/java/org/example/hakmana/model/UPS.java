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
public class UPS extends Devices{
    private String regNum;
    private String model="No";
    private String status;
    private String purchasedFrom;

    public UPS[] getDevices() {
        DatabaseConnection conn=DatabaseConnection.getInstance();
        List<UPS> ups = new ArrayList<>();
        //pass query to the connection class
        String sql = "SELECT * FROM Ups";

        try {
            // get result set from connection class
            ResultSet resultSet = conn.executeSt(sql);

            // Iterate through the result set and create Desktop and User objects
            while (resultSet.next()) {
                UPS ups1 = new UPS();

                ups1.setRegNum(resultSet.getString("UpsRegNum"));
                ups1.setModel(resultSet.getString("model"));
                ups1.setStatus(resultSet.getString("status"));

                ups.add(ups1);
            }
        }
        catch (SQLException e){
            System.out.println(e);
        }

        return ups.toArray(new UPS[0]);
    }

}
