package com.salekseev.booksmarketclient.view.author.bookInfo;

import com.jfoenix.controls.JFXButton;
import com.salekseev.booksmarketclient.model.Author;
import com.salekseev.booksmarketclient.model.Book;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.controlsfx.glyphfont.GlyphFont;
import org.controlsfx.glyphfont.GlyphFontRegistry;

public abstract class BooksOfAuthorViewDesigner extends StackPane {

    protected MFXTableView<Book> tableView;
    protected JFXButton okButton;

    public BooksOfAuthorViewDesigner() {
        this.createView();
        this.getStylesheets().add(getClass().getResource("/css/center-view.css").toExternalForm());
        this.getStylesheets().add(getClass().getResource("/css/add-author-view.css").toExternalForm());
    }

    private void createView() {
        GlyphFont fontAwesome = GlyphFontRegistry.font("FontAwesome");
        okButton = new JFXButton("OK");
        okButton.setOnAction(this::okButtonOnAction);

        tableView = new MFXTableView<>();
        tableView.setPrefSize(1024.0, 768.0);
        tableView.setFooterVisible(false);
        tableView.setMaxWidth(1024.0);

        MFXTableColumn<Book> titleColumn = new MFXTableColumn<>("Название", true);
        titleColumn.setRowCellFactory(book -> new MFXTableRowCell<>(Book::getTitle));
        titleColumn.setPrefWidth(100);

        MFXTableColumn<Book> authorsColumn = new MFXTableColumn<>("Автор(ы)", true);
        authorsColumn.setRowCellFactory(book -> new MFXTableRowCell<>(b -> {
            StringBuilder authors = new StringBuilder();

            for (Author author : b.getAuthors()) {
                authors.append(author.getLastName()).append(" ").append(author.getFirstName()).append(" ");
            }

            return authors.toString();
        }));
        authorsColumn.setPrefWidth(200);

        MFXTableColumn<Book> publisherColumn = new MFXTableColumn<>("Издатель", true);
        publisherColumn.setRowCellFactory(book -> new MFXTableRowCell<>(b -> b.getPublisher().getName()));
        publisherColumn.setPrefWidth(100);

        MFXTableColumn<Book> genreColumn = new MFXTableColumn<>("Жанр", true);
        genreColumn.setRowCellFactory(book -> new MFXTableRowCell<>(b -> b.getGenre().getName()));
        genreColumn.setPrefWidth(100);

        MFXTableColumn<Book> pagesColumn = new MFXTableColumn<>("Кол-во стр.", true);
        pagesColumn.setRowCellFactory(book -> new MFXTableRowCell<>(Book::getPages));
        pagesColumn.setPrefWidth(100);

        MFXTableColumn<Book> weightColumn = new MFXTableColumn<>("Вес г", true);
        weightColumn.setRowCellFactory(book -> new MFXTableRowCell<>(Book::getWeight));
        weightColumn.setPrefWidth(50);

        MFXTableColumn<Book> yearColumn = new MFXTableColumn<>("Год", true);
        yearColumn.setRowCellFactory(book -> new MFXTableRowCell<>(Book::getPublicationYear));
        yearColumn.setPrefWidth(50);

        MFXTableColumn<Book> costColumn = new MFXTableColumn<>("Стоимость ₽", true);
        costColumn.setRowCellFactory(book -> new MFXTableRowCell<>(Book::getCost));
        costColumn.setPrefWidth(100);

        MFXTableColumn<Book> descriptionColumn = new MFXTableColumn<>("Описание", false);
        descriptionColumn.setRowCellFactory(book -> new MFXTableRowCell<>(Book::getDescription));
        descriptionColumn.setPrefWidth(200);
        descriptionColumn.setMaxWidth(200);

        tableView.getTableColumns().addAll(titleColumn, authorsColumn, genreColumn, publisherColumn, pagesColumn,
                weightColumn, yearColumn, costColumn, descriptionColumn);

        HBox hBox = new HBox(5);
        hBox.setPadding(new Insets(0, 5, 0, 5));
        hBox.getChildren().addAll();

        HBox buttonsHBox = new HBox(5, okButton);
        buttonsHBox.setAlignment(Pos.CENTER_RIGHT);

        VBox vBox = new VBox(5,  hBox, tableView, buttonsHBox);
        vBox.setPadding(new Insets(5, 5, 5, 5));

        getChildren().add(vBox);
    }

    protected abstract void okButtonOnAction(ActionEvent event);

}
