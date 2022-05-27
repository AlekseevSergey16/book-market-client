package com.salekseev.booksmarketclient.util;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class FxUtil {

    public static void showView(String title, Parent view, Window window) {
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.getIcons().add(new Image(FxUtil.class.getResource("/icons/main-icon.png").toExternalForm()));
        stage.setScene(new Scene(view));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(window);
        stage.showAndWait();
    }

}
