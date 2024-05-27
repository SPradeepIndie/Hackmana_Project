package org.example.hakmana.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Keyboard{
    private String regNum;
    private String model="No";
    private String name;
    private String status;

    private String purchasedFrom;
    private  String connectivity;


}
