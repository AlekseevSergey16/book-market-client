package com.salekseev.booksmarketclient.view.user;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.Glyph;
import org.controlsfx.glyphfont.GlyphFont;
import org.controlsfx.glyphfont.GlyphFontRegistry;

abstract class MainUserViewDesigner extends BorderPane {

    VBox leftNavBar;

    JFXButton bookButton;
    JFXButton orderButton;

    public MainUserViewDesigner() {
        createView();
        getStylesheets().add(getClass().getResource("/css/nav-bar.css").toExternalForm());
    }

    private void createView() {
        GlyphFont fontAwesome = GlyphFontRegistry.font("FontAwesome");

        Label welcome = new Label("", new ImageView(new Image(getClass().getResource("/icons/title-icon.png").toExternalForm())));
        welcome.setAlignment(Pos.CENTER);

        Glyph glyph1 = fontAwesome.create(FontAwesome.Glyph.BOOK).color(Color.valueOf("white"));
        bookButton = new JFXButton("", glyph1);
        bookButton.setPrefSize(45.0, 45.0);
        bookButton.setOnAction(this::bookButtonOnAction);
        bookButton.setStyle("-fx-border-radius: 0; -fx-background-radius: 0;");

        orderButton = new JFXButton("", fontAwesome.create(FontAwesome.Glyph.SHOPPING_CART).color(Color.valueOf("white")));
        orderButton.setPrefSize(50.0, 50.0);
        orderButton.setOnAction(this::orderButtonOnAction);
        orderButton.setStyle("-fx-border-radius: 0; -fx-background-radius: 0;");

        leftNavBar = new VBox(bookButton, orderButton);
        leftNavBar.setPrefSize(45.0, 600.0);
        leftNavBar.setStyle("-fx-background-color: #3c72bf");

        AnchorPane anchorPane = new AnchorPane(leftNavBar);
        anchorPane.setPrefSize(45.0, 400.0);
        anchorPane.maxHeight(Double.MAX_VALUE);
        anchorPane.maxWidth(Double.MAX_VALUE);
        anchorPane.minHeight(Double.MAX_VALUE);
        anchorPane.minWidth(Double.MAX_VALUE);

        setPrefSize(642.0 * 1.5, 200.0);
        setAlignment(anchorPane, Pos.CENTER);
        setLeft(anchorPane);
        setCenter(welcome);
    }

    public abstract void bookButtonOnAction(ActionEvent event);
    public abstract void orderButtonOnAction(ActionEvent event);

}
