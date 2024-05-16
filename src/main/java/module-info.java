module org.example.hakmana {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires com.jfoenix;
    requires java.desktop;
    requires java.mail;
    requires com.google.api.client;
    requires com.google.api.client.json.gson;
    requires com.google.api.client.auth;
    requires com.google.api.client.extensions.java6.auth;
    requires com.google.api.client.extensions.jetty.auth;
    requires google.api.client;
    requires com.google.api.services.gmail;
    requires org.apache.commons.codec;
    requires jdk.httpserver;
    requires static lombok;

    opens org.example.hakmana to javafx.fxml;
    exports org.example.hakmana;
    exports org.example.hakmana.model;
    opens org.example.hakmana.model to javafx.fxml;


}