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
public class Speaker extends Devices{
    private String regNum;
    private String model="No";
    private String user;
    private String status;

    private String purchasedFrom;

    public Speaker[] getDevices() {
        DatabaseConnection conn=DatabaseConnection.getInstance();
        List<Speaker> speakers = new ArrayList<>();
        //pass query to the connection class
        String sql = "SELECT * FROM Speaker";

        try {
            // get result set from connection class
            ResultSet resultSet = conn.executeSt(sql);

            // Iterate through the result set and create Desktop and User objects
            while (resultSet.next()) {
                Speaker speaker = new Speaker();

                speaker.setRegNum(resultSet.getString("SpeakerRegNum"));
                speaker.setModel(resultSet.getString("model"));
                speaker.setStatus(resultSet.getString("user"));
                speaker.setStatus(resultSet.getString("status"));

                speakers.add(speaker);
            }
        }
        catch (SQLException e){
            System.out.println(e);
        }

        return speakers.toArray(new Speaker[0]);
    }

}
