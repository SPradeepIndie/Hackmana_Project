package org.example.hakmana.model;

public class DeviceReport {
    private String reportName;
    private String reportDetail;

    public DeviceReport(String reportName, String reportDetail) {
        this.reportName = reportName;
        this.reportDetail = reportDetail;
    }

    public String getReportName() {
        return reportName;
    }

    public String getReportDetail() {
        return reportDetail;
    }
}
