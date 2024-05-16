package org.example.hakmana.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Devices {
    private String regNum;
    private String model="No";
    private String name;
    private String status;


    //get the Devices array from the database
    public Devices[] getDevices(){
        return null;
    }


}


