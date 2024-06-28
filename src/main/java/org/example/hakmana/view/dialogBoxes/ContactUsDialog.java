package org.example.hakmana.view.dialogBoxes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.example.hakmana.view.component.AddDevButtonController;

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
