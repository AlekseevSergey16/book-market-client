package com.salekseev.booksmarketclient.view.shipment.main;

import com.jfoenix.controls.JFXButton;
import com.salekseev.booksmarketclient.model.Publisher;
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

abstract class ShipmentViewDesigner extends StackPane {

    JFXButton addShipmentButton;
    JFXButton detailButton;
    MFXTableView<Shipment> tableView;

    public ShipmentViewDesigner() {
        createView();
        getStylesheets().add(getClass().getResource("/css/center-view.css").toExternalForm());
    }

    private void createView() {
        GlyphFont fontAwesome = GlyphFontRegistry.font("FontAwesome");

        addShipmentButton = new JFXButton("", fontAwesome.create(FontAwesome.Glyph.PLUS).color(Color.valueOf("#3c72bf")).size(18));
        addShipmentButton.setOnAction(this::addShipmentButtonOnAction);

        MenuItem showSupplierItem = new MenuItem("Добавить Поставщика");
        showSupplierItem.setOnAction(this::showSupplierItemOnAction);
        ContextMenu contextMenu = new ContextMenu(showSupplierItem);

        detailButton = new JFXButton("", fontAwesome.create(FontAwesome.Glyph.ELLIPSIS_H).color(Color.valueOf("#3c72bf")).size(18));
        detailButton.setOnMouseClicked(event -> contextMenu.show(detailButton, event.getScreenX() - 150, event.getScreenY()));

        Pane hiddenPane = new Pane();
        HBox.setHgrow(hiddenPane, Priority.ALWAYS);

        tableView = new MFXTableView<>();
        tableView.setPrefSize(1024.0, 768.0);
        tableView.setFooterVisible(false);

        MFXTableColumn<Shipment> column1 = new MFXTableColumn<>("Дата поставки", false);
        column1.setRowCellFactory(rowCell -> new MFXTableRowCell<>(Shipment::getShipmentDate));
        column1.setMinWidth(200);

        MFXTableColumn<Shipment> column2 = new MFXTableColumn<>("Поставщик", false);
        column2.setRowCellFactory(rowCell -> new MFXTableRowCell<>(Shipment::getSupplier));
        column2.setMinWidth(200);

        MFXTableColumn<Shipment> column3 = new MFXTableColumn<>("Кол-во экземплятор", false);
        column3.setRowCellFactory(rowCell -> new MFXTableRowCell<>(Shipment::getTotalAmount));
        column3.setMinWidth(200);

        tableView.getTableColumns().addAll(column1, column2, column3);

        HBox hBox = new HBox(5);
        hBox.setPadding(new Insets(0, 5, 0, 5));
        hBox.getChildren().addAll(addShipmentButton, hiddenPane, detailButton);

        VBox vBox = new VBox(5,  hBox, tableView);
        vBox.setPadding(new Insets(5, 5, 5, 5));

        getChildren().add(vBox);
    }

    protected abstract void addShipmentButtonOnAction(ActionEvent event);
    protected abstract void showSupplierItemOnAction(ActionEvent event);

}
