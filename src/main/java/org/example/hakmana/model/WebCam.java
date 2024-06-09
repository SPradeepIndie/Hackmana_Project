package org.example.hakmana.model;

import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Data
public class WebCam{
    private String regNum;//Database have DesRegNum
    private String model;
    private String status;
    private String userName;

    public WebCam() {
    }

}
