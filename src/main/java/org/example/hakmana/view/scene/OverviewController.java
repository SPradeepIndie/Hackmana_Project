package org.example.hakmana.view.scene;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.example.hakmana.view.component.AddDevButtonController;

public class OverviewController {
    private static OverviewController instance=null;

    private OverviewController(){}

    public static OverviewController getInstance() {
        if(instance== null){
            instance=new OverviewController();
            return instance;
        }
        return instance;
    }
}
