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
public class PhotocpyMchine extends Devices {
    private String regNum;
    private String model;
    private String status;
    private String userName;

    private String purchasedFrom;

    @Override
    public Devices[] getDevices() {
        DatabaseConnection conn=DatabaseConnection.getInstance();
        List<PhotocpyMchine> photocopyMachines = new ArrayList<>();
        //pass query to the connection class
        String sql = "SELECT PhotoCopyMachine.* FROM PhotoCopyMachine";

        try {
            // get result set from connection class
            ResultSet resultSet = conn.executeSt(sql);

            // Iterate through the result set and create Desktop and User objects
            while (resultSet.next()) {
                PhotocpyMchine photocopyMachine = new PhotocpyMchine();

                photocopyMachine.setRegNum(resultSet.getString("PhotoCopyMachineRegNum"));
                photocopyMachine.setModel(resultSet.getString("model"));
                photocopyMachine.setStatus(resultSet.getString("status"));

                photocopyMachines.add(photocopyMachine);//add photocopyMachines to the photocopyMachins list
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

        return photocopyMachines.toArray(new PhotocpyMchine[0]);
}
}
