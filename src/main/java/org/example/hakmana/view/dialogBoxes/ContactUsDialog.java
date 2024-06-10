package org.example.hakmana.view.dialogBoxes;

public class ContactUsDialog {
    private static ContactUsDialog instance=null;
    private ContactUsDialog() {
    }

    public static ContactUsDialog getInstance() {
        if(instance==null){
            instance=new ContactUsDialog();
            return instance;
        }
        return instance;
    }
}
