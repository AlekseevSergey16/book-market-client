package com.salekseev.booksmarketclient.view.order.main;

import com.salekseev.booksmarketclient.model.Order;
import com.salekseev.booksmarketclient.service.retrofit.BookMarketService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class OrderVM {

    private final ObservableList<Order> orderObservableList = FXCollections
            .observableArrayList(new Order());
    private Long userId;

    private final BookMarketService service = BookMarketService.getInstance();

    public void loadOrders() {
        service.getAllOrders()
                .thenAccept(orders -> Platform.runLater(() -> orderObservableList.setAll(orders)));
    }

    public void loadOrders(long userId) {
        this.userId = userId;
        service.getOrdersByUserId(userId)
                .thenAccept(orders -> Platform.runLater(() -> orderObservableList.setAll(orders)));
    }

    public void addOrder(Order order) {
        order.setUserId(userId);
        service.createOrder(order)
                .thenAccept(orderId -> Platform.runLater(() -> {
                    order.setId(orderId);
                    orderObservableList.add(order);
                }));
    }

    public void loadReport() {
        service.getBooksSoldForMonth();
    }

    public ObservableList<Order> getOrderObservableList() {
        return orderObservableList;
    }

}
