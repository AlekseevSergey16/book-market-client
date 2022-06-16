package com.salekseev.booksmarketclient.view.author.info;

import com.salekseev.booksmarketclient.model.Author;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.util.function.Consumer;

public class AuthorInfoView extends AuthorInfoViewDesigner {

    private final AuthorInfoVM viewModel;
    private final Consumer<Author> authorConsumer;

    public AuthorInfoView(Consumer<Author> authorConsumer) {
        this.authorConsumer = authorConsumer;
        this.viewModel = new AuthorInfoVM();
        bindFields();
    }

    public AuthorInfoView(Author author, Consumer<Author> authorConsumer) {
        this.viewModel = new AuthorInfoVM(author);
        this.authorConsumer = authorConsumer;
        bindFields();
    }

    @Override
    protected void saveAuthorButtonOnAction(ActionEvent event) {
        Author author = viewModel.buildAuthor();
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

        this.firstNameField.textProperty().bindBidirectional(viewModel.firstNameProperty());
        this.lastNameField.textProperty().bindBidirectional(viewModel.lastNameProperty());
        this.middleNameField.textProperty().bindBidirectional(viewModel.middleNameProperty());
        this.informationArea.textProperty().bindBidirectional(viewModel.informationProperty());
    }

}
