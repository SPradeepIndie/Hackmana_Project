package org.example.hakmana;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.hakmana.view.scene.LoginPageController;

import java.io.IOException;
import java.util.Objects;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginPageController.class.getResource("loginPage.fxml"));
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("view/scene/Images/icon-airplay.png")));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("EDMSystem");
        stage.getIcons().add(icon);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}