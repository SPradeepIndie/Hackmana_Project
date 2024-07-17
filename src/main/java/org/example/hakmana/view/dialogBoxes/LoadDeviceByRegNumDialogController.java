package org.example.hakmana.view.dialogBoxes;

public class LoadDeviceByRegNumDialogController {
    private static LoadDeviceByRegNumDialogController instance=null;

    private LoadDeviceByRegNumDialogController() {
    }

    public static LoadDeviceByRegNumDialogController getInstance() {
        if(instance==null){
            instance=new LoadDeviceByRegNumDialogController();
            return instance;
        }
        return instance;
    }
}
