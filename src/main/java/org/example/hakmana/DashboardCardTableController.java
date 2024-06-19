package org.example.hakmana;

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
