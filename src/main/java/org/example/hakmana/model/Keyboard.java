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
public class Keyboard extends Devices{
    private String regNum;
    private String model="No";
    private String name;
    private String status;

    private String purchasedFrom;
    private  String connectivity;

    public Keyboard[] getDevices() {
        DatabaseConnection conn=DatabaseConnection.getInstance();
        List<Keyboard> keyboards = new ArrayList<>();
        //pass query to the connection class
        String sql = "SELECT * FROM Keyboard";

        try {
            // get result set from connection class
            ResultSet resultSet = conn.executeSt(sql);

            // Iterate through the result set and create Desktop and DeviceUser objects
            while (resultSet.next()) {
                Keyboard keyboard1 = new Keyboard();

                keyboard1.setRegNum(resultSet.getString("KeyboardRegNum"));
                keyboard1.setModel(resultSet.getString("model"));
                keyboard1.setModel(resultSet.getString("name"));
                keyboard1.setStatus(resultSet.getString("status"));

                keyboards.add(keyboard1);
            }
        }
        catch (SQLException e){
            System.out.println(e);
        }

        return keyboards.toArray(new Keyboard[0]);
    }

    @Override
    public String getUserName() {
        return null;
    }

    @Override
    public void setUserName(String para1) {

    }
}
