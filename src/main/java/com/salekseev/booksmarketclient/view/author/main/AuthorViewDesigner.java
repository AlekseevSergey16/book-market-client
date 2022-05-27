package com.salekseev.booksmarketclient.view.author.main;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.skins.JFXButtonSkin;
import com.salekseev.booksmarketclient.model.Author;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.controlsfx.control.PopOver;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.GlyphFont;
import org.controlsfx.glyphfont.GlyphFontRegistry;

public abstract class AuthorViewDesigner extends StackPane {

    JFXButton addAuthorButton;
    JFXButton editAuthorButton;
    JFXButton deleteAuthorButton;
    JFXButton detailButton;
    JFXButton showBooksButton;
    MFXTableView<Author> tableView;

    public AuthorViewDesigner() {
        createView();
        getStylesheets().add(getClass().getResource("/css/center-view.css").toExternalForm());
    }

    private void createView() {
        GlyphFont fontAwesome = GlyphFontRegistry.font("FontAwesome");

        addAuthorButton = new JFXButton("", fontAwesome.create(FontAwesome.Glyph.PLUS).color(Color.valueOf("#3c72bf")).size(18));
        addAuthorButton.setOnAction(this::addAuthorButtonOnAction);

        editAuthorButton = new JFXButton("", fontAwesome.create(FontAwesome.Glyph.PENCIL).color(Color.valueOf("#3c72bf")).size(18)); //"\uf044"
        editAuthorButton.setOnAction(this::editAuthorButtonOnAction);
        editAuthorButton.setDisable(true);

        deleteAuthorButton = new JFXButton("", fontAwesome.create(FontAwesome.Glyph.TRASH).color(Color.valueOf("#3c72bf")).size(18));
        deleteAuthorButton.setOnAction(this::deleteAuthorButtonOnAction);
        deleteAuthorButton.setDisable(true);

        showBooksButton = new JFXButton("Показать книги автора");
        showBooksButton.setOnAction(this::showBooksButtonOnAction);

        PopOver popOver = new PopOver();
        popOver.setAnimated(false);
        popOver.setArrowLocation(PopOver.ArrowLocation.RIGHT_BOTTOM);
        popOver.setContentNode(new VBox(showBooksButton));
        popOver.setCornerRadius(1);
        popOver.setArrowSize(1);

        detailButton = new JFXButton("", fontAwesome.create(FontAwesome.Glyph.ELLIPSIS_H).color(Color.valueOf("#3c72bf")).size(18));
        detailButton.setOnAction(event -> popOver.show(detailButton));
        Pane hiddenPane = new Pane();
        HBox.setHgrow(hiddenPane, Priority.ALWAYS);

        tableView = new MFXTableView<>();
        tableView.setPrefSize(1024.0, 768.0);
        tableView.setFooterVisible(false);

        MFXTableColumn<Author> lastNameColumn = new MFXTableColumn<>("Фамилия", true);
        lastNameColumn.setRowCellFactory(author -> new MFXTableRowCell<>(Author::getLastName));
        lastNameColumn.setMinWidth(200);

        MFXTableColumn<Author> firstNameColumn = new MFXTableColumn<>("Имя", true);
        firstNameColumn.setRowCellFactory(author -> new MFXTableRowCell<>(Author::getFirstName));
        firstNameColumn.setMinWidth(200);

        MFXTableColumn<Author> middleNameColumn = new MFXTableColumn<>("Отчество", true);
        middleNameColumn.setRowCellFactory(author -> new MFXTableRowCell<>(Author::getMiddleName));
        middleNameColumn.setMinWidth(200);

        MFXTableColumn<Author> informationColumn = new MFXTableColumn<>("Информация", true);
        informationColumn.setRowCellFactory(author -> new MFXTableRowCell<>(Author::getInformation));
        informationColumn.setMinWidth(400);

        tableView.getTableColumns().addAll(lastNameColumn, firstNameColumn, middleNameColumn, informationColumn);
        tableView.scrollToFirst();
        tableView.scrollToLast();

        HBox hBox = new HBox(5);
        hBox.setPadding(new Insets(0, 5, 0, 5));
        hBox.getChildren().addAll(addAuthorButton, editAuthorButton, deleteAuthorButton, hiddenPane, detailButton);

        VBox vBox = new VBox(5,  hBox, tableView);
        vBox.setPadding(new Insets(5, 5, 5, 5));

        getChildren().add(vBox);
    }

    protected abstract void addAuthorButtonOnAction(ActionEvent event);
    protected abstract void editAuthorButtonOnAction(ActionEvent event);
    protected abstract void deleteAuthorButtonOnAction(ActionEvent event);
    protected abstract void showBooksButtonOnAction(ActionEvent event);

}
