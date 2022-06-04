package com.salekseev.booksmarketclient.view.user;

import com.salekseev.booksmarketclient.model.Book;
import com.salekseev.booksmarketclient.model.Genre;
import com.salekseev.booksmarketclient.model.Publisher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class State {

    private final ObservableList<Book> bookObservableList = FXCollections.observableArrayList(
            new Book(new HashSet<>(), new Publisher(), new Genre()));

    private final List<Book> selectedBooks = new ArrayList<>();

    public ObservableList<Book> getBookObservableList() {
        return bookObservableList;
    }

    public List<Book> getSelectedBooks() {
        return selectedBooks;
    }
}
