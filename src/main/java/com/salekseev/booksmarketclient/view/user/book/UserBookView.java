package com.salekseev.booksmarketclient.view.user.book;

import com.salekseev.booksmarketclient.service.retrofit.BookMarketService;
import com.salekseev.booksmarketclient.view.user.State;
import javafx.application.Platform;

public class UserBookView extends UserBookViewDesigner {

    private final State state;
    private final BookMarketService service = BookMarketService.getInstance();

    public UserBookView(State state) {
        this.state = state;
        loadBooks();
        bindFields();
    }

    private void loadBooks() {
        service.getAllBooks()
                .thenAccept(books -> Platform.runLater(() -> state.getBookObservableList().setAll(books)));
    }

    private void bindFields() {
        this.tableView.setItems(state.getBookObservableList());
    }

}
