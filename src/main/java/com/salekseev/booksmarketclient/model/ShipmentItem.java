package com.salekseev.booksmarketclient.model;

public class ShipmentItem {

    private Long id;
    private Shipment shipment;
    private Book book;
    private Integer amount;

    public ShipmentItem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return book.getAuthors() + ": " +  book.getTitle() + " " + "(" + amount + ")";
    }
}
