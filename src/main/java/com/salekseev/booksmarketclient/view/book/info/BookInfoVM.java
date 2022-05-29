package com.salekseev.booksmarketclient.view.book.info;

import com.salekseev.booksmarketclient.model.Author;
import com.salekseev.booksmarketclient.model.Book;
import com.salekseev.booksmarketclient.model.Genre;
import com.salekseev.booksmarketclient.model.Publisher;
import com.salekseev.booksmarketclient.service.retrofit.BookMarketService;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

import java.util.List;

public class BookInfoVM {

    private final ObservableList<Genre> genreObservableList = FXCollections.observableArrayList(List.of(
            new Genre(1L, "Художественная лит-ра"),
            new Genre(2L, "Компьютерная лит-ра"),
            new Genre(3L, "Детская лит-ра")
    ));
    private final ObservableList<Author> authorObservableList = FXCollections.observableArrayList(new Author());
    private final ObservableList<Publisher> publisherObservableList = FXCollections.observableArrayList(new Publisher());
    private final BookMarketService service = BookMarketService.getInstance();

    public void loadDataFromServer() {
        service.getAllAuthors()
                .thenAccept(authors -> {
                    Platform.runLater(() -> {
                        authorObservableList.setAll(authors);
                    });
                });
        service.getAllGenres()
                .thenAccept(genres -> Platform.runLater(() -> genreObservableList.setAll(genres)));
        service.getAllPublishers()
                .thenAccept(publishers -> Platform.runLater(() -> publisherObservableList.setAll(publishers)));
    }

    public ObservableList<Author> getAuthorObservableList() {
        return authorObservableList;
    }

    public ObservableList<Publisher> getPublisherObservableList() {
        return publisherObservableList;
    }

    public ObservableList<Genre> getGenreObservableList() {
        return genreObservableList;
    }

}
