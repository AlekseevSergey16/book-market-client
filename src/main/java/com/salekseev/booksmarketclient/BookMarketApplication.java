package com.salekseev.booksmarketclient;

import com.salekseev.booksmarketclient.view.login.LoginView;
import com.salekseev.booksmarketclient.view.mainView.MainView;
import com.salekseev.booksmarketclient.view.user.MainUserView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class BookMarketApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        var root = new LoginView();

        Scene scene = new Scene(root);
        stage.setTitle("Книжный магазин");
        stage.getIcons().add(new Image(getClass().getResource("/icons/main-icon.png").toExternalForm()));
        stage.setScene(scene);
//        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
