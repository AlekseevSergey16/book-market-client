package com.salekseev.booksmarketclient.view.user;

import com.salekseev.booksmarketclient.view.order.main.OrderView;
import com.salekseev.booksmarketclient.view.user.book.UserBookView;
import javafx.event.ActionEvent;

public class MainUserView extends MainUserViewDesigner {

    private final State state = new State();
    private final long userId;

    public MainUserView(long userId) {
        this.userId = userId;
    }

    @Override
    public void bookButtonOnAction(ActionEvent event) {
        UserBookView bookView = new UserBookView(state);
        setCenter(bookView);
    }

    @Override
    public void orderButtonOnAction(ActionEvent event) {
        OrderView orderView = new OrderView(userId);
        setCenter(orderView);
    }

}
