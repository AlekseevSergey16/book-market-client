package com.salekseev.booksmarketclient.service;

import com.salekseev.booksmarketclient.model.*;
import com.salekseev.booksmarketclient.service.retrofit.ServiceCallback;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface BookMarketService {

    public CompletableFuture<List<Author>> getAllAuthors();

    public CompletableFuture<Long> createAuthor(Author author);

    public CompletableFuture<Void> updateAuthor(Author author);

    public CompletableFuture<Void> deleteAuthor(long id);

    public CompletableFuture<List<Publisher>> getAllPublishers();

    public CompletableFuture<Long> createPublisher(Publisher publisher);

    public CompletableFuture<Void> updatePublisher(Publisher publisher);

    public CompletableFuture<Void> deletePublisher(long id);

    public CompletableFuture<List<Book>> getAllBooks();

    public CompletableFuture<List<Book>> getAvailabilityBooks();

    public CompletableFuture<List<Book>> getBooksByAuthor(long authorId);

    public CompletableFuture<Long> createBook(Book book);

    public CompletableFuture<Void> updateBook(Book book);

    public CompletableFuture<Void> deleteBook(long id);

    public CompletableFuture<List<Genre>> getAllGenres();

    public CompletableFuture<List<Supplier>> getAllSuppliers();

    public CompletableFuture<Long> createSupplier(Supplier supplier);

    public CompletableFuture<List<Shipment>> getAllShipments();

    public CompletableFuture<Long> createShipment(Shipment shipment);

    public CompletableFuture<List<Order>> getAllOrders();

    public CompletableFuture<List<Order>> getOrdersByUserId(long userId);

    public CompletableFuture<Long> createOrder(Order order);

    public CompletableFuture<List<BookReport>> getBookReports();

}
