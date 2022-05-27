package com.salekseev.booksmarketclient.view.book.main;

import com.salekseev.booksmarketclient.model.Book;
import com.salekseev.booksmarketclient.model.Genre;
import com.salekseev.booksmarketclient.model.Publisher;
import com.salekseev.booksmarketclient.service.retrofit.BookMarketService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashSet;

public class BookVM {

    private final ObservableList<Book> bookObservableList = FXCollections.observableArrayList(
            new Book(new HashSet<>(), new Publisher(), new Genre()));
    private final BookMarketService service = BookMarketService.getInstance();

    public ObservableList<Book> getBookObservableList() {
        return bookObservableList;
    }

    public void loadBooks() {
        service.getAllBooks()
                .thenAccept(books -> Platform.runLater(() -> bookObservableList.setAll(books)));
    }

    public void addBook(Book book) {
        service.createBook(book)
                .thenAccept(bookId -> Platform.runLater(() -> {
                    book.setId(bookId);
                    bookObservableList.add(book);
                }));
    }

    public void updateBook(Book book) {
        service.updateBook(book)
                .thenAccept(response -> Platform.runLater(() -> {
                    bookObservableList.removeIf(b -> b.getId().equals(book.getId()));
                    bookObservableList.add(book);
                }));
    }

    public void deleteBook(long id) {
        service.deleteBook(id)
                .thenAccept(response -> Platform.runLater(() ->
                        bookObservableList.removeIf(book -> book.getId().equals(id))));
    }

}
