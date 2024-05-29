package org.example.hakmana.view.scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ReportHndlingController{
    private static ReportHndlingController instance=null;
    public Label descriptionLabel;
    public Button downloadButton;
    public TableView reportTable;
    public TableColumn reportNameColumn;
    public TableColumn reportDetailColumn;

    private ReportHndlingController(){
    }

    public static ReportHndlingController getInstance() {
        if(instance == null){
            instance= new ReportHndlingController();
            return instance;
        }
        return instance;

    }
}


