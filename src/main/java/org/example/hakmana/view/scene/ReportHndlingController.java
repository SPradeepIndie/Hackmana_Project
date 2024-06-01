package org.example.hakmana.view.scene;

import com.itextpdf.layout.property.UnitValue;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.*;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.TextAlignment;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.hakmana.model.AllDeviceDetails;

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

    public void downloadButtonOnAction(ActionEvent actionEvent) {
        // Create a file chooser dialog
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save PDF");
        fileChooser.setInitialFileName("Active Devices Count.pdf");

        // Show the dialog to select the download location
        File selectedFile = fileChooser.showSaveDialog(new Stage());
        if (selectedFile != null) {
            String filePath = selectedFile.getAbsolutePath();
            try {
                // Create a PdfWriter object with the selected file path
                PdfWriter writer = new PdfWriter(new File(filePath));

                // Create a PdfDocument object
                PdfDocument pdf = new PdfDocument(writer);

                // Create a Document object
                Document document = new Document(pdf);

                // Your PDF creation code here...
                // Add header
                Paragraph header = new Paragraph("Active Device Details")
                        .setTextAlignment(TextAlignment.CENTER)
                        .setFontSize(16);
                document.add(header);

                // Create table
                Table table = new Table(2); // 2 columns
                table.setWidth(UnitValue.createPercentValue(100)); // Set table width to 100% of page

                // Add headers
                table.addHeaderCell(new Cell().add(new Paragraph("Device Name")));
                table.addHeaderCell(new Cell().add(new Paragraph("Devices Count")));

                AllDeviceDetails allDeviceDetails = new AllDeviceDetails();
                for (AllDeviceDetails dev : allDeviceDetails.getActiveDevicesCount()) {
                    // Add data rows (you can add more rows as needed)
                    table.addCell(new Cell().add(new Paragraph(dev.getDeviceName())));
                    table.addCell(new Cell().add(new Paragraph(dev.getDeviceCount())));
                }

                // Add table to document
                document.add(table);

                // Close the document
                document.close();

                System.out.println("PDF created successfully!");

                // Open the created PDF file
                openPdf(filePath);
            } catch (IOException e) {
                System.err.println("Error creating PDF: " + e.getMessage());
            }
        }
    }

    private void openPdf(String filePath) {
        try {
            File file = new File(filePath);
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            System.err.println("Error opening PDF: " + e.getMessage());
        }
    }
}


