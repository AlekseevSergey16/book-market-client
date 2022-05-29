package com.salekseev.booksmarketclient.view.shipment.bookSelect;

import com.salekseev.booksmarketclient.model.Book;
import com.salekseev.booksmarketclient.model.Genre;
import com.salekseev.booksmarketclient.model.Publisher;
import com.salekseev.booksmarketclient.service.retrofit.BookMarketService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashSet;

public class BookSelectVM {

    private final ObservableList<Book> bookObservableList = FXCollections.observableArrayList(
            new Book(new HashSet<>(), new Publisher(), new Genre()));
    private final BookMarketService service = BookMarketService.getInstance();

    public void loadBooks() {
        service.getAllBooks()
                .thenAccept(books -> Platform.runLater(() -> bookObservableList.setAll(books)));
    }

    public ObservableList<Book> getBookObservableList() {
        return bookObservableList;
    }

}
