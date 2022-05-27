package com.salekseev.booksmarketclient.view.publisher.main;

import com.jfoenix.controls.JFXButton;
import com.salekseev.booksmarketclient.model.Author;
import com.salekseev.booksmarketclient.model.Publisher;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.GlyphFont;
import org.controlsfx.glyphfont.GlyphFontRegistry;

abstract class PublisherViewDesigner extends StackPane {

    JFXButton addPublisherButton;
    JFXButton editPublisherButton;
    JFXButton deletePublisherButton;
    MFXTableView<Publisher> tableView;

    public PublisherViewDesigner() {
        createView();
        getStylesheets().add(getClass().getResource("/css/center-view.css").toExternalForm());
    }

    private void createView() {
        GlyphFont fontAwesome = GlyphFontRegistry.font("FontAwesome");

        addPublisherButton = new JFXButton("", fontAwesome.create(FontAwesome.Glyph.PLUS).color(Color.valueOf("#3c72bf")).size(18));
        addPublisherButton.setOnAction(this::addPublisherButtonOnAction);

        editPublisherButton = new JFXButton("", fontAwesome.create(FontAwesome.Glyph.PENCIL).color(Color.valueOf("#3c72bf")).size(18)); //"\uf044"
        editPublisherButton.setOnAction(this::editPublisherButtonOnAction);
        editPublisherButton.setDisable(true);

        deletePublisherButton = new JFXButton("", fontAwesome.create(FontAwesome.Glyph.TRASH).color(Color.valueOf("#3c72bf")).size(18));
        deletePublisherButton.setOnAction(this::deletePublisherButtonOnAction);
        deletePublisherButton.setDisable(true);

        tableView = new MFXTableView<>();
        tableView.setPrefSize(1024.0, 768.0);
        tableView.setFooterVisible(false);

        MFXTableColumn<Publisher> lastNameColumn = new MFXTableColumn<>("Наименование", true);
        lastNameColumn.setRowCellFactory(rowCell -> new MFXTableRowCell<>(Publisher::getName));
        lastNameColumn.setMinWidth(200);

        MFXTableColumn<Publisher> firstNameColumn = new MFXTableColumn<>("Телефон", true);
        firstNameColumn.setRowCellFactory(rowCell -> new MFXTableRowCell<>(Publisher::getPhone));
        firstNameColumn.setMinWidth(200);

        MFXTableColumn<Publisher> middleNameColumn = new MFXTableColumn<>("Email", true);
        middleNameColumn.setRowCellFactory(rowCell -> new MFXTableRowCell<>(Publisher::getEmail));
        middleNameColumn.setMinWidth(200);

        MFXTableColumn<Publisher> informationColumn = new MFXTableColumn<>("Информация", true);
        informationColumn.setRowCellFactory(rowCell -> new MFXTableRowCell<>(Publisher::getInformation));
        informationColumn.setMinWidth(400);

        tableView.getTableColumns().addAll(lastNameColumn, firstNameColumn, middleNameColumn, informationColumn);

        HBox hBox = new HBox(5);
        hBox.setPadding(new Insets(0, 5, 0, 5));
        hBox.getChildren().addAll(addPublisherButton, editPublisherButton, deletePublisherButton);

        VBox vBox = new VBox(5,  hBox, tableView);
        vBox.setPadding(new Insets(5, 5, 5, 5));

        getChildren().add(vBox);
    }

    protected abstract void addPublisherButtonOnAction(ActionEvent event);
    protected abstract void editPublisherButtonOnAction(ActionEvent event);
    protected abstract void deletePublisherButtonOnAction(ActionEvent event);

}
