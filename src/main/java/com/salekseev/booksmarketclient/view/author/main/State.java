package com.salekseev.booksmarketclient.view.author.main;

import com.salekseev.booksmarketclient.model.Author;
import com.salekseev.booksmarketclient.service.retrofit.BookMarketService;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.MapProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

import java.util.List;
import java.util.function.Function;

public class State {

    private final BookMarketService marketService = BookMarketService.getInstance();
    Function<Throwable, Void> onError;

    private final ObservableList<Author> authorList = FXCollections.observableArrayList(new Author());
    private final MapProperty<Integer, Author> authorMap = new SimpleMapProperty<>();
    private final BooleanProperty selectedOneAuthor = new SimpleBooleanProperty();

    public State(Function<Throwable, Void> throwableConsumer) {
        this.onError = throwableConsumer;
    }

    public void getAllAuthorsFromServer() {
        marketService.getAllAuthors()
                .thenAccept(authors -> Platform.runLater(() -> authorList.setAll(authors)))
                .exceptionally(throwable -> onError.apply(throwable));
    }

    public MapProperty<Integer, Author> authorMapProperty() {
        return authorMap;
    }

    public void addAuthor(Author author) {
        marketService.createAuthor(author)
                .thenAccept(authorId -> Platform.runLater(() -> {
                    author.setId(authorId);
                    authorList.add(author);
                }))
                .exceptionally(throwable -> onError.apply(throwable));
    }

    public void updateAuthor(Author author) {
        marketService.updateAuthor(author)
                .thenAccept(response -> Platform.runLater(() -> {
                    authorList.removeIf(a -> a.getId().equals(author.getId())); //return null id
                    authorList.add(author);
                }))
                .exceptionally(throwable -> onError.apply(throwable));
    }

    public void deleteAuthor(long id) {
        marketService.deleteAuthor(id)
                .thenAccept(response -> Platform.runLater(() -> {
                    authorList.removeIf(author -> author.getId().equals(id));
                }))
                .exceptionally(throwable -> onError.apply(throwable));
    }

    public ObservableList<Author> getAuthorList() {
        return authorList;
    }

    public ObservableMap<Integer, Author> getAuthorMap() {
        return authorMap.get();
    }

    public void setAuthorMap(ObservableMap<Integer, Author> authorMap) {
        this.authorMap.set(authorMap);
    }

    public boolean isSelectedOneAuthor() {
        return selectedOneAuthor.get();
    }

    public BooleanProperty selectedOneAuthorProperty() {
        return selectedOneAuthor;
    }

    public void setSelectedOneAuthor(boolean selectedOneAuthor) {
        this.selectedOneAuthor.set(selectedOneAuthor);
    }
}
