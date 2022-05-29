package com.salekseev.booksmarketclient.view.shipment.shipmentInfo;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.salekseev.booksmarketclient.model.*;
import io.github.palexdev.materialfx.controls.MFXListView;
import io.github.palexdev.materialfx.effects.DepthLevel;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.GlyphFont;
import org.controlsfx.glyphfont.GlyphFontRegistry;

abstract class ShipmentInfoViewDesigner extends StackPane {

    JFXComboBox<Supplier> supplierComboBox;
    JFXButton addShipmentItemButton;
    MFXListView<ShipmentItem> shipmentItemListView;
    JFXTextField totalAmountField;

    final JFXButton saveBookButton = new JFXButton("Сохранить");
    final JFXButton cancelBookButton = new JFXButton("Отменить");

    public ShipmentInfoViewDesigner() {
        createView();
        getStylesheets().add(getClass().getResource("/css/add-author-view.css").toExternalForm());
    }

    private void createView() {
        GlyphFont fontAwesome = GlyphFontRegistry.font("FontAwesome");

        VBox vBox = new VBox(30);
        vBox.setPadding(new Insets(40.0, 50.0, 30.0, 50.0));

        supplierComboBox = new JFXComboBox<>();
        supplierComboBox.setPromptText("Поставщик");
        supplierComboBox.setPrefWidth(520);

        addShipmentItemButton = new JFXButton("", fontAwesome.create(FontAwesome.Glyph.PLUS).color(Color.valueOf("#3c72bf")).size(18));
        addShipmentItemButton.setOnAction(this::addShipmentItemButtonOnAction);
        addShipmentItemButton.getStylesheets().add(getClass().getResource("/css/button.css").toExternalForm());

        shipmentItemListView = new MFXListView<>();
        shipmentItemListView.setPrefSize(520.0, 100.0);
        shipmentItemListView.setDepthLevel(DepthLevel.LEVEL0);

        totalAmountField = new JFXTextField();
        totalAmountField.setPromptText("Общее количество");

        saveBookButton.setOnAction(this::saveShipmentButtonOnAction);
        cancelBookButton.setOnAction(this::cancelShipmentButtonOnAction);

        HBox buttonsHBox = new HBox(5, saveBookButton, cancelBookButton);
        buttonsHBox.setAlignment(Pos.CENTER_RIGHT);

        vBox.getChildren().addAll(supplierComboBox, new VBox(5, addShipmentItemButton, shipmentItemListView),
                totalAmountField, buttonsHBox);
        vBox.setStyle("-fx-background-color: #fafafa;");

        getChildren().add(vBox);
    }

    protected abstract void saveShipmentButtonOnAction(ActionEvent event);
    protected abstract void cancelShipmentButtonOnAction(ActionEvent event);

    protected abstract void addShipmentItemButtonOnAction(ActionEvent event);
}
