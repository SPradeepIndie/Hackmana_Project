package org.example.hakmana.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Projectors extends Devices{
    private String regNum;
    private String model;
    private String status;


    public Projectors[] getDevices() {
        DatabaseConnection conn=DatabaseConnection.getInstance();
        List<Projectors> projectors = new ArrayList<>();
        //pass query to the connection class
        String sql = "SELECT * FROM MultimediaProjector";

        try {
            // get result set from connection class
            ResultSet resultSet = conn.executeSt(sql);

            // Iterate through the result set and create Desktop and User objects
            while (resultSet.next()) {
                Projectors projector = new Projectors(null,null,null);

                projector.setRegNum(resultSet.getString("MultimediaProjectorRegNum"));
                projector.setModel(resultSet.getString("model"));
                projector.setStatus(resultSet.getString("status"));

                projectors.add(projector);
            }
        }
        catch (SQLException e){
            System.out.println(e);
        }

        return projectors.toArray(new Projectors[0]);
    }
}
