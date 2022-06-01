package com.salekseev.booksmarketclient.view.order.info;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.salekseev.booksmarketclient.model.OrderItem;
import com.salekseev.booksmarketclient.model.ShipmentItem;
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

abstract class OrderInfoViewDesigner extends StackPane {

    JFXButton addOrderItemButton;
    JFXButton deleteOrderItemButton;
    MFXListView<OrderItem> orderItemListView;
    JFXTextField totalCostField;

    final JFXButton saveOrderButton = new JFXButton("Сохранить");
    final JFXButton cancelOrderButton = new JFXButton("Отменить");

    public OrderInfoViewDesigner() {
        createView();
        getStylesheets().add(getClass().getResource("/css/add-author-view.css").toExternalForm());
    }

    private void createView() {
        VBox vBox = new VBox(30);
        vBox.setPadding(new Insets(40.0, 50.0, 30.0, 50.0));

        addOrderItemButton = new JFXButton("Добавить");
        addOrderItemButton.setOnAction(this::addOrderItemButtonOnAction);

        deleteOrderItemButton = new JFXButton("Удалить");
        deleteOrderItemButton.setOnAction(this::deleteOrderItemButton);

        orderItemListView = new MFXListView<>();
        orderItemListView.setPrefSize(520.0, 100.0);
        orderItemListView.setDepthLevel(DepthLevel.LEVEL0);

        totalCostField = new JFXTextField();
        totalCostField.setPromptText("Общая Стоимость");

        saveOrderButton.setOnAction(this::saveOrderButtonOnAction);
        cancelOrderButton.setOnAction(this::cancelOrderButtonOnAction);

        HBox buttonsHBox = new HBox(5, saveOrderButton, cancelOrderButton);
        buttonsHBox.setAlignment(Pos.CENTER_RIGHT);

        vBox.getChildren().addAll(new VBox(5, new HBox(5, addOrderItemButton, deleteOrderItemButton), orderItemListView),
                totalCostField, buttonsHBox);
        vBox.setStyle("-fx-background-color: #fafafa;");

        getChildren().add(vBox);
    }

    protected abstract void saveOrderButtonOnAction(ActionEvent event);
    protected abstract void cancelOrderButtonOnAction(ActionEvent event);

    protected abstract void addOrderItemButtonOnAction(ActionEvent event);
    protected abstract void deleteOrderItemButton(ActionEvent event);

}
