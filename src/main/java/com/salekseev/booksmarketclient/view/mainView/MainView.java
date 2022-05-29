package com.salekseev.booksmarketclient.view.mainView;

import com.salekseev.booksmarketclient.view.author.main.AuthorView;
import com.salekseev.booksmarketclient.view.book.main.BookView;
import com.salekseev.booksmarketclient.view.publisher.main.PublisherView;
import com.salekseev.booksmarketclient.view.shipment.main.ShipmentView;
import javafx.event.ActionEvent;

public class MainView extends MainViewDesigner {

    @Override
    public void bookButtonOnAction(ActionEvent event) {
        BookView bookView = new BookView();
        setCenter(bookView);
    }

    @Override
    public void authorButtonOnAction(ActionEvent event) {
        AuthorView authorView = new AuthorView();
        setCenter(authorView);
    }

    @Override
    public void publisherButtonOnAction(ActionEvent event) {
        PublisherView publisherView = new PublisherView();
        setCenter(publisherView);
    }

    @Override
    public void shipmentButtonOnAction(ActionEvent event) {
        ShipmentView shipmentView = new ShipmentView();
        setCenter(shipmentView);
    }

}
