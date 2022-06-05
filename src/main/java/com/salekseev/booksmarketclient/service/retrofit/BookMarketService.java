package com.salekseev.booksmarketclient.service.retrofit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.salekseev.booksmarketclient.model.*;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Query;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class BookMarketService {

    private static final BookMarketService INSTANCE = new BookMarketService();
    BookMarketApi api;

    public static BookMarketService getInstance() {
        return INSTANCE;
    }

    private BookMarketService() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(Duration.ofSeconds(10000))
                .readTimeout(Duration.ofSeconds(10000))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080")
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .client(httpClient)
                .build();

        api = retrofit.create(BookMarketApi.class);
    }

    public CompletableFuture<List<Author>> getAllAuthors() {
        var result = new CompletableFuture<List<Author>>();
        api.getAllAuthors().enqueue(new ServiceCallback<>(result));
        return result;
    }

    public CompletableFuture<Long> createAuthor(Author author) {
        var result = new CompletableFuture<Long>();
        api.createAuthor(author).enqueue(new ServiceCallback<>(result));
        return result;
    }

    public CompletableFuture<Void> updateAuthor(Author author) {
        var result = new CompletableFuture<Void>();
        api.updateAuthor(author).enqueue(new ServiceCallback<>(result));
        return result;
    }

    public CompletableFuture<Void> deleteAuthor(long id) {
        var result = new CompletableFuture<Void>();
        api.deleteAuthor(id).enqueue(new ServiceCallback<>(result));
        return result;
    }

    public CompletableFuture<List<Publisher>> getAllPublishers() {
        var result = new CompletableFuture<List<Publisher>>();
        api.getAllPublishers().enqueue(new ServiceCallback<>(result));
        return result;
    }

    public CompletableFuture<Long> createPublisher(Publisher publisher) {
        var result = new CompletableFuture<Long>();
        api.createPublisher(publisher).enqueue(new ServiceCallback<>(result));
        return result;
    }

    public CompletableFuture<Void> updatePublisher(Publisher publisher) {
        var result = new CompletableFuture<Void>();
        api.updatePublisher(publisher).enqueue(new ServiceCallback<>(result));
        return result;
    }

    public CompletableFuture<Void> deletePublisher(long id) {
        var result = new CompletableFuture<Void>();
        api.deletePublisher(id).enqueue(new ServiceCallback<>(result));
        return result;
    }

    public CompletableFuture<List<Book>> getAllBooks() {
        var result = new CompletableFuture<List<Book>>();
        api.getAllBooks().enqueue(new ServiceCallback<>(result));
        return result;
    }

    public CompletableFuture<List<Book>> getAvailabilityBooks() {
        var result = new CompletableFuture<List<Book>>();
        api.getAvailabilityBooks(true).enqueue(new ServiceCallback<>(result));
        return result;
    }

    public CompletableFuture<List<Book>> getBooksByAuthor(long authorId) {
        var result = new CompletableFuture<List<Book>>();
        api.getBooksByAuthor(authorId).enqueue(new ServiceCallback<>(result));
        return result;
    }

    public CompletableFuture<Long> createBook(Book book) {
        var result = new CompletableFuture<Long>();
        api.createBook(book).enqueue(new ServiceCallback<>(result));
        return result;
    }

    public CompletableFuture<Void> updateBook(Book book) {
        var result = new CompletableFuture<Void>();
        api.updateBook(book).enqueue(new ServiceCallback<>(result));
        return result;
    }

    public CompletableFuture<Void> deleteBook(long id) {
        var result = new CompletableFuture<Void>();
        api.deleteBook(id).enqueue(new ServiceCallback<>(result));
        return result;
    }

    public CompletableFuture<List<Genre>> getAllGenres() {
        var result = new CompletableFuture<List<Genre>>();
        api.getAllGenres().enqueue(new ServiceCallback<>(result));
        return result;
    }

    public CompletableFuture<List<Supplier>> getAllSuppliers() {
        var result = new CompletableFuture<List<Supplier>>();
        api.getAllSuppliers().enqueue(new ServiceCallback<>(result));
        return result;
    }

    public CompletableFuture<Long> createSupplier(Supplier supplier) {
        var result = new CompletableFuture<Long>();
        api.createSupplier(supplier).enqueue(new ServiceCallback<>(result));
        return result;
    }

    public CompletableFuture<List<Shipment>> getAllShipments() {
        var result = new CompletableFuture<List<Shipment>>();
        api.getAllShipments().enqueue(new ServiceCallback<>(result));
        return result;
    }

    public CompletableFuture<Long> createShipment(Shipment shipment) {
        var result = new CompletableFuture<Long>();
        api.createShipment(shipment).enqueue(new ServiceCallback<>(result));
        return result;
    }

    public CompletableFuture<List<Order>> getAllOrders() {
        var result = new CompletableFuture<List<Order>>();
        api.getAllOrders().enqueue(new ServiceCallback<>(result));
        return result;
    }

    public CompletableFuture<List<Order>> getOrdersByUserId(long userId) {
        var result = new CompletableFuture<List<Order>>();
        api.getOrdersByUser(userId).enqueue(new ServiceCallback<>(result));
        return result;
    }

    public CompletableFuture<Long> createOrder(Order order) {
        var result = new CompletableFuture<Long>();
        api.createOrder(order).enqueue(new ServiceCallback<>(result));
        return result;
    }

    public void getBooksSoldForMonth() {
        try {
            api.getBooksSoldForMonth().execute();
        } catch (IOException e) {
            //todo pdf
        }
    }

    public Optional<User> getUser(String username, String password) {
        try {
            Response<User> response = api.getUser(username, password).execute();
            if (response.isSuccessful()) {
                return Optional.of(response.body());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public Long createUser(User user) {
        try {
            Response<Long> response = api.createUser(user).execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new RuntimeException(response.message());
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

}
