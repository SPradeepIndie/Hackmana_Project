module org.example.hakmana {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens org.example.hakmana to javafx.fxml;
    exports org.example.hakmana;



}