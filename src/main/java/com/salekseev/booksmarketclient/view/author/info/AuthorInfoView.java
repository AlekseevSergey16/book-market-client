package com.salekseev.booksmarketclient.view.author.info;

import com.salekseev.booksmarketclient.model.Author;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.util.function.Consumer;

public class AuthorInfoView extends AuthorInfoViewDesigner {

    private Author author;
    private final Consumer<Author> authorConsumer;

    public AuthorInfoView(Consumer<Author> authorConsumer) {
        this.authorConsumer = authorConsumer;
        bindFields();
    }

    public AuthorInfoView(Author author, Consumer<Author> authorConsumer) {
        this.author = author;
        this.authorConsumer = authorConsumer;
        this.firstNameField.setText(author.getFirstName());
        this.lastNameField.setText(author.getLastName());
        this.middleNameField.setText(author.getMiddleName());
        this.informationArea.setText(author.getInformation());
        bindFields();
    }

    @Override
    protected void saveAuthorButtonOnAction(ActionEvent event) {
        Author author = buildAuthor();

        if (this.author != null) {
            author.setId(this.author.getId());
        }

        authorConsumer.accept(author);
        ((Stage)((Node) event.getSource()).getScene().getWindow()).close();
    }

    @Override
    protected void cancelAuthorButtonOnAction(ActionEvent event) {
        ((Stage)((Node) event.getSource()).getScene().getWindow()).close();
    }

    private void bindFields() {
        saveAuthorButton.disableProperty()
                .bind(firstNameField.textProperty().isEmpty()
                .or(lastNameField.textProperty().isEmpty()));
    }

    private Author buildAuthor() {
        return Author.builder()
                .firstName(firstNameField.getText())
                .lastName(lastNameField.getText())
                .middleName(middleNameField.getText())
                .information(informationArea.getText())
                .build();
    }

}
