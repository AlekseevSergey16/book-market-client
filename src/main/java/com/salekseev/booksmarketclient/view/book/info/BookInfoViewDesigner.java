package com.salekseev.booksmarketclient.view.book.info;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.salekseev.booksmarketclient.model.Author;
import com.salekseev.booksmarketclient.model.Genre;
import com.salekseev.booksmarketclient.model.Publisher;
import io.github.palexdev.materialfx.controls.MFXListView;
import io.github.palexdev.materialfx.effects.DepthLevel;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

abstract class BookInfoViewDesigner extends StackPane {

    final JFXTextField titleField = new JFXTextField();
    final MFXListView<Author> authorListView = new MFXListView<>();
    final JFXComboBox<Publisher> publisherComboBox = new JFXComboBox<>();
    final JFXComboBox<Genre> genreComboBox = new JFXComboBox<>();
    final JFXTextField publicationYearField = new JFXTextField();
    final JFXTextField costField = new JFXTextField();
    final JFXTextField pagesField = new JFXTextField();
    final JFXTextField weightField = new JFXTextField();
    final JFXTextArea descriptionArea = new JFXTextArea();

    final Label authorsLabel = new Label("Авторы");

    final JFXButton saveBookButton = new JFXButton("Сохранить");
    final JFXButton cancelBookButton = new JFXButton("Отменить");

    public BookInfoViewDesigner() {
        createView();
        getStylesheets().add(getClass().getResource("/css/add-author-view.css").toExternalForm());
    }

    private void createView() {
        VBox vBox = new VBox(30);
        vBox.setPadding(new Insets(40.0, 50.0, 30.0, 50.0));

        titleField.setPromptText("Название");
        publicationYearField.setPromptText("Год издания");
        costField.setPromptText("Стоимость, ₽");
        pagesField.setPromptText("Количество страниц");
        weightField.setPromptText("Вес, г");
        descriptionArea.setPromptText("Описание");

        saveBookButton.setOnAction(this::saveBookButtonOnAction);
        cancelBookButton.setOnAction(this::cancelBookButtonOnAction);

        authorListView.setPrefSize(520.0, 100.0);
        authorListView.setDepthLevel(DepthLevel.LEVEL0);

        HBox authorHBox = new HBox(authorListView);
        authorHBox.setAlignment(Pos.CENTER);

        publisherComboBox.setPromptText("Издательство");
        publisherComboBox.setLabelFloat(true);
        publisherComboBox.setPrefWidth(260);

        genreComboBox.setPromptText("Жанр");
        genreComboBox.setLabelFloat(true);
        genreComboBox.setPrefWidth(260);

        HBox comboBoxH = new HBox(5, publisherComboBox, genreComboBox);

        HBox fieldHBox = new HBox(10, pagesField, weightField, publicationYearField);

        HBox buttonsHBox = new HBox(5, saveBookButton, cancelBookButton);
        buttonsHBox.setAlignment(Pos.CENTER_RIGHT);

        vBox.getChildren().addAll(titleField, new VBox(5, authorsLabel, authorListView), comboBoxH, fieldHBox, costField, descriptionArea, buttonsHBox);
        vBox.setStyle("-fx-background-color: #fafafa;");

        getChildren().add(vBox);
    }

    protected abstract void saveBookButtonOnAction(ActionEvent event);
    protected abstract void cancelBookButtonOnAction(ActionEvent event);

}
