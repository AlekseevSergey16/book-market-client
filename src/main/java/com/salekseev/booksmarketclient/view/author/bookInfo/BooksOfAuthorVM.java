package com.salekseev.booksmarketclient.view.author.bookInfo;

import com.salekseev.booksmarketclient.model.Book;
import com.salekseev.booksmarketclient.model.Genre;
import com.salekseev.booksmarketclient.model.Publisher;
import com.salekseev.booksmarketclient.service.retrofit.BookMarketService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashSet;

public class BooksOfAuthorVM {

    private final ObservableList<Book> bookObservableList = FXCollections.observableArrayList(
            new Book(new HashSet<>(), new Publisher(), new Genre()));
    private final BookMarketService service = BookMarketService.getInstance();

    public ObservableList<Book> getBookObservableList() {
        return bookObservableList;
    }

    public void loadBooks(long authorId) {
        service.getBooksByAuthor(authorId)
                .thenAccept(books -> Platform.runLater(() -> bookObservableList.setAll(books)));
    }

}
