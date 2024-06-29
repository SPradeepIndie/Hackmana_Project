package org.example.hakmana;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.example.hakmana.view.component.AddDevButtonController;

public class DashboardCardTableController {
    private String activeDevices;
    private String inactiveDevices;
    private String notAssignedDevices;
    private String repairedDevices;
    private String totalDevices;

    public DashboardCardTableController(String activeDevices, String inactiveDevices, String notAssignedDevices, String repairedDevices, String totalDevices) {
        this.activeDevices = activeDevices;
        this.inactiveDevices = inactiveDevices;
        this.notAssignedDevices = notAssignedDevices;
        this.repairedDevices = repairedDevices;
        this.totalDevices = totalDevices;
    }

    public String getInactiveDevices() {
        return inactiveDevices;
    }

    public String getActiveDevices() {
        return activeDevices;
    }

    public String getNotAssignedDevices() {
        return notAssignedDevices;
    }

    public String getRepairedDevices() {
        return repairedDevices;
    }

    public String getTotalDevices() {
        return totalDevices;
    }
}
