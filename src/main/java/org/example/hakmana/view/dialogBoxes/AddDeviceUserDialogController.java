package org.example.hakmana.view.dialogBoxes;

public class AddDeviceUserDialogController {
    private static AddDeviceUserDialogController instance =null;

    private AddDeviceUserDialogController() {
    }

    public static AddDeviceUserDialogController getInstance() {
        if(instance==null){
            instance=new AddDeviceUserDialogController();
            return instance;
        }
        return instance;
    }
}
