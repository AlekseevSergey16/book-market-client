package com.salekseev.booksmarketclient.view.author.main;

import com.salekseev.booksmarketclient.model.Author;
import com.salekseev.booksmarketclient.util.FxUtil;
import com.salekseev.booksmarketclient.view.author.bookInfo.BooksOfAuthorView;
import com.salekseev.booksmarketclient.view.author.info.AuthorInfoView;
import com.salekseev.booksmarketclient.view.book.main.BookView;
import javafx.collections.MapChangeListener;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;
import java.util.function.Consumer;

public class AuthorView extends AuthorViewDesigner {

    State state;

    Consumer<Author> addAuthorConsumer = author -> state.addAuthor(author);
    Consumer<Author> editAuthorConsumer = author -> state.updateAuthor(author);

    public AuthorView() {
        this.state = new State(this::onServerError);
        this.state.getAllAuthorsFromServer();
        bindFields(state);
    }

    @Override
    protected void addAuthorButtonOnAction(ActionEvent event) {
        showView("Добавление Автора", new AuthorInfoView(addAuthorConsumer));
    }

    @Override
    protected void editAuthorButtonOnAction(ActionEvent event) {
        Author author = (Author) state.getAuthorMap().values().toArray()[0];
        showView("Изменение Автора", new AuthorInfoView(author, editAuthorConsumer));
    }

    @Override
    protected void deleteAuthorButtonOnAction(ActionEvent event) {
        List<Author> selectedAuthors = this.tableView.getSelectionModel().getSelectedValues();
        selectedAuthors.stream()
                .map(Author::getId)
                .forEach(state::deleteAuthor);
    }

    @Override
    protected void showBooksButtonOnAction(ActionEvent event) {
        Author author = (Author) state.getAuthorMap().values().toArray()[0];
        String title = String.format("Книги (%s %s)", author.getFirstName(), author.getLastName());
        FxUtil.showView(title, new BooksOfAuthorView(author), this.getScene().getWindow());
    }

    private void bindFields(State state) {
        this.tableView.setItems(state.getAuthorList());

        state.authorMapProperty().bindBidirectional(tableView.getSelectionModel().selectionProperty());

        state.authorMapProperty().addListener((MapChangeListener<Integer, Author>) change -> {
            if (change.getMap().size() == 1) {
                editAuthorButton.setDisable(false);
                deleteAuthorButton.setDisable(false);
                detailButton.setDisable(false);
            } else if (change.getMap().size() > 1) {
                editAuthorButton.setDisable(true);
                deleteAuthorButton.setDisable(false);
                detailButton.setDisable(true);
            } else if (change.getMap().isEmpty()) {
                editAuthorButton.setDisable(true);
                deleteAuthorButton.setDisable(true);
                detailButton.setDisable(true);
            }
        });
    }

    private void showView(String title, Parent view) {
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.getIcons().add(new Image(getClass().getResource("/icons/main-icon.png").toExternalForm()));
        stage.setScene(new Scene(view));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner((getScene().getWindow()));
        stage.showAndWait();
    }

    private Void onServerError(Throwable throwable) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка соединения");
        alert.setHeaderText("Ошибка!");
        alert.setContentText("Неизвестная ошибка сервера");

        alert.showAndWait();

        return null;
    }

}
