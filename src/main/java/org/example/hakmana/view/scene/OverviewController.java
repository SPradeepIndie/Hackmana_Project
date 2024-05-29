package org.example.hakmana.view.scene;

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
