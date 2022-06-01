package com.salekseev.booksmarketclient.view.mainView;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
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
import org.controlsfx.glyphfont.GlyphFont;
import org.controlsfx.glyphfont.GlyphFontRegistry;

public abstract class MainViewDesigner extends BorderPane {

    VBox leftNavBar;
    Label title;

    JFXButton authorButton;
    JFXButton publisherButton;
    JFXButton bookButton;
    JFXButton shipmentButton;
    JFXButton orderButton;

    public MainViewDesigner() {
        createView();
        getStylesheets().add(getClass().getResource("/css/nav-bar.css").toExternalForm());
    }

    private void createView() {
        GlyphFont fontAwesome = GlyphFontRegistry.font("FontAwesome");
        title = new Label("Книжный магазин");
        title.setAlignment(Pos.CENTER);
        title.setPrefSize(260.0, 45.0);
        title.setFont(Font.font("Roboto", FontWeight.MEDIUM, 24));
        title.setPadding(new Insets(20, 0, 20, 0));

        Label welcome = new Label("", new ImageView(new Image(getClass().getResource("/icons/title-icon.png").toExternalForm())));
        welcome.setAlignment(Pos.CENTER);

        authorButton = new JFXButton("Авторы", fontAwesome.create(FontAwesome.Glyph.USER).color(Color.valueOf("#3c72bf")));
        authorButton.setPrefSize(258.0, 45.0);
        authorButton.setOnAction(this::authorButtonOnAction);

        publisherButton = new JFXButton("Издательства", fontAwesome.create(FontAwesome.Glyph.NEWSPAPER_ALT).color(Color.valueOf("#3c72bf")));
        publisherButton.setPrefSize(258.0, 45.0);
        publisherButton.setOnAction(this::publisherButtonOnAction);

        bookButton = new JFXButton("Книги", fontAwesome.create(FontAwesome.Glyph.BOOK).color(Color.valueOf("#3c72bf")));
        bookButton.setPrefSize(258.0, 45.0);
        bookButton.setOnAction(this::bookButtonOnAction);

        shipmentButton = new JFXButton("Поставки", fontAwesome.create(FontAwesome.Glyph.TRUCK).color(Color.valueOf("#3c72bf")));
        shipmentButton.setPrefSize(258.0, 45.0);
        shipmentButton.setOnAction(this::shipmentButtonOnAction);

        orderButton = new JFXButton("Заказы", fontAwesome.create(FontAwesome.Glyph.LIST_UL).color(Color.valueOf("#3c72bf")));
        orderButton.setPrefSize(258.0, 45.0);
        orderButton.setOnAction(this::orderButtonOnAction);

        leftNavBar = new VBox(title, authorButton, publisherButton, bookButton, shipmentButton, orderButton);
        leftNavBar.setPrefSize(260.0, 800.0);
//        leftNavBar.setStyle("-fx-background-color: rgb(239,239,239);");
        leftNavBar.setPadding(new Insets(0, 0, 0, 5));

        AnchorPane anchorPane = new AnchorPane(leftNavBar);
        anchorPane.setPrefSize(260.0, 800.0);
        anchorPane.maxHeight(Double.MAX_VALUE);
        anchorPane.maxWidth(Double.MAX_VALUE);
        anchorPane.minHeight(Double.MAX_VALUE);
        anchorPane.minWidth(Double.MAX_VALUE);

        setPrefSize(1284.0, 800.0);
        setAlignment(anchorPane, Pos.CENTER);
        setLeft(anchorPane);
        setCenter(welcome);
    }

    public abstract void bookButtonOnAction(ActionEvent event);
    public abstract void authorButtonOnAction(ActionEvent event);
    public abstract void publisherButtonOnAction(ActionEvent event);
    public abstract void shipmentButtonOnAction(ActionEvent event);
    public abstract void orderButtonOnAction(ActionEvent event);

}
