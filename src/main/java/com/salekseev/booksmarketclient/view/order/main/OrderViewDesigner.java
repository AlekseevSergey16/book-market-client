package com.salekseev.booksmarketclient.view.order.main;

import com.jfoenix.controls.JFXButton;
import com.salekseev.booksmarketclient.model.Order;
import com.salekseev.booksmarketclient.model.OrderItem;
import com.salekseev.booksmarketclient.model.Shipment;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.GlyphFont;
import org.controlsfx.glyphfont.GlyphFontRegistry;

import java.util.stream.Collectors;

abstract class OrderViewDesigner extends StackPane {

    JFXButton addOrderButton;
    JFXButton detailButton;
    MFXTableView<Order> tableView;

    public OrderViewDesigner() {
        createView();
        getStylesheets().add(getClass().getResource("/css/center-view.css").toExternalForm());
    }

    private void createView() {
        GlyphFont fontAwesome = GlyphFontRegistry.font("FontAwesome");

        addOrderButton = new JFXButton("", fontAwesome.create(FontAwesome.Glyph.PLUS).color(Color.valueOf("#3c72bf")).size(18));
        addOrderButton.setOnAction(this::addOrderButtonOnAction);

        MenuItem showDetailedItem = new MenuItem("Подробнее");
        showDetailedItem.setOnAction(this::showDetailedItemOnAction);

        ContextMenu contextMenu = new ContextMenu(showDetailedItem);

        detailButton = new JFXButton("", fontAwesome.create(FontAwesome.Glyph.ELLIPSIS_H).color(Color.valueOf("#3c72bf")).size(18));
        detailButton.setOnMouseClicked(event -> contextMenu.show(detailButton, event.getScreenX() - 150, event.getScreenY()));

        Pane hiddenPane = new Pane();
        HBox.setHgrow(hiddenPane, Priority.ALWAYS);

        tableView = new MFXTableView<>();
        tableView.setPrefSize(1024.0, 768.0);
        tableView.setFooterVisible(false);

        MFXTableColumn<Order> column1 = new MFXTableColumn<>("Дата заказа", false);
        column1.setRowCellFactory(rowCell -> new MFXTableRowCell<>(Order::getOrderDate));
        column1.setMinWidth(200);

        MFXTableColumn<Order> column2 = new MFXTableColumn<>("Позиции заказа", false);
        column2.setRowCellFactory(rowCell -> new MFXTableRowCell<>(order -> order.getItems() == null ? "" :
                order.getItems().stream()
                        .map(OrderItem::getBook)
                        .collect(Collectors.toList())));
        column2.setMinWidth(500);

        MFXTableColumn<Order> column3 = new MFXTableColumn<>("Общая стоимость", false);
        column3.setRowCellFactory(rowCell -> new MFXTableRowCell<>(Order::getTotalCost));
        column3.setMinWidth(200);

        tableView.getTableColumns().addAll(column1, column2, column3);

        HBox hBox = new HBox(5);
        hBox.setPadding(new Insets(0, 5, 0, 5));
        hBox.getChildren().addAll(addOrderButton, hiddenPane, detailButton);

        VBox vBox = new VBox(5,  hBox, tableView);
        vBox.setPadding(new Insets(5, 5, 5, 5));

        getChildren().add(vBox);
    }

    protected abstract void addOrderButtonOnAction(ActionEvent event);
    protected abstract void showDetailedItemOnAction(ActionEvent event);

}
