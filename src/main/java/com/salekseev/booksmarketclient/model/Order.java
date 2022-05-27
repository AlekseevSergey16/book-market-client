package com.salekseev.booksmarketclient.model;

import java.time.LocalDateTime;
import java.util.List;

public class Order {

    private Long id;
    private LocalDateTime orderDate;
    private List<OrderItem> items;
    private Double totalCost;


}
