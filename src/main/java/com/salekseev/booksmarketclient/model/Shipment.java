package com.salekseev.booksmarketclient.model;

import java.time.LocalDate;
import java.util.List;

public class Shipment {

    private Long id;
    private LocalDate shipmentDate;
    private List<ShipmentItem> items;
    private Integer totalAmount;

}
