package com.salekseev.booksmarketclient.service.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.salekseev.booksmarketclient.model.*;
import com.salekseev.booksmarketclient.service.BookMarketService;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class HttpBookMarketService implements BookMarketService {
    private static final String BASE_URI = "http://localhost:8080";

    private final HttpClient httpClient = HttpClient.newHttpClient();

//    @Override
//    public CompletableFuture<List<Author>> getAllAuthors() {
//        var request = HttpRequest.newBuilder().GET()
//                .uri(URI.create(BASE_URI + "/book-market/api/authors"))
//                .build();
//        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
//                .thenApply(HttpResponse::body)
//                .thenApply(body -> readValue(body, new TypeReference<>() {
//                }));
//    }

    @Override
    public CompletableFuture<List<Author>> getAllAuthors() {
        var request = HttpRequest.newBuilder().GET()
                .uri(URI.create(BASE_URI + "/book-market/api/authors"))
                .build();
        return httpClient.sendAsync(request, new JsonBodyHandler<List<Author>>())
                .thenApply(HttpResponse::body);
    }

    public List<Author> getAllAuthorsSyn() {
        try {
            return httpClient.send(HttpRequest.newBuilder().GET()
                    .uri(URI.create(BASE_URI + "/book-market/api/authors")).build(),
                    new JsonBodyHandler<List<Author>>()).body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Author> getAllAuthorsSyn2() {
        try {
            var response = httpClient.send(HttpRequest.newBuilder().GET()
                            .uri(URI.create(BASE_URI + "/book-market/api/authors")).build(),
                    new JsonBodyHandler<List<Author>>());
            if (response.statusCode() >= 400) {
                return Collections.emptyList();
            }
            return response.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Author> getAllAuthorsSyn3() {
        var request = HttpRequest.newBuilder().GET()
                .uri(URI.create(BASE_URI + "/book-market/api/authors"))
                .build();
        return httpClient.sendAsync(request, new JsonBodyHandler<List<Author>>())
                .thenApply(HttpResponse::body).join();
    }

    public Object getAllAuthorsSyn4() {
        var request = HttpRequest.newBuilder().GET()
                .uri(URI.create(BASE_URI + "/book-market/api/authors"))
                .build();
        try {
            var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() >= 400) {
                return new ObjectMapper().readValue(response.body(), ErrorInfo.class);
            }
            return new ObjectMapper().readValue(response.body(), new TypeReference<List<Author>>() {});
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public CompletableFuture<HttpResponse<String>> getAllAuthors4() {
        var request = HttpRequest.newBuilder().GET()
                .uri(URI.create(BASE_URI + "/book-market/api/authors"))
                .build();
        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
    }

    public CompletableFuture<Result<List<Author>>> getAllAuthors5() {
        var request = HttpRequest.newBuilder().GET()
                .uri(URI.create(BASE_URI + "/book-market/api/authors"))
                .build();
        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> {
                    try {
                        if (response.statusCode() >= 400) {
                            var error = new ObjectMapper().readValue(response.body(), ErrorInfo.class);
                            return Result.error(error);
                        }
                        var value = new ObjectMapper().readValue(response.body(), new TypeReference<List<Author>>() {});
                        return Result.ok(value);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @Override
    public CompletableFuture<Long> createAuthor(Author author) {
        return null;
    }

    @Override
    public CompletableFuture<Void> updateAuthor(Author author) {
        return null;
    }

    @Override
    public CompletableFuture<Void> deleteAuthor(long id) {
        return null;
    }

    @Override
    public CompletableFuture<List<Publisher>> getAllPublishers() {
        return null;
    }

    @Override
    public CompletableFuture<Long> createPublisher(Publisher publisher) {
        return null;
    }

    @Override
    public CompletableFuture<Void> updatePublisher(Publisher publisher) {
        return null;
    }

    @Override
    public CompletableFuture<Void> deletePublisher(long id) {
        return null;
    }

    @Override
    public CompletableFuture<List<Book>> getAllBooks() {
        return null;
    }

    @Override
    public CompletableFuture<List<Book>> getAvailabilityBooks() {
        return null;
    }

    @Override
    public CompletableFuture<List<Book>> getBooksByAuthor(long authorId) {
        return null;
    }

    @Override
    public CompletableFuture<Long> createBook(Book book) {
        return null;
    }

    @Override
    public CompletableFuture<Void> updateBook(Book book) {
        return null;
    }

    @Override
    public CompletableFuture<Void> deleteBook(long id) {
        return null;
    }

    @Override
    public CompletableFuture<List<Genre>> getAllGenres() {
        return null;
    }

    @Override
    public CompletableFuture<List<Supplier>> getAllSuppliers() {
        return null;
    }

    @Override
    public CompletableFuture<Long> createSupplier(Supplier supplier) {
        return null;
    }

    @Override
    public CompletableFuture<List<Shipment>> getAllShipments() {
        return null;
    }

    @Override
    public CompletableFuture<Long> createShipment(Shipment shipment) {
        return null;
    }

    @Override
    public CompletableFuture<List<Order>> getAllOrders() {
        return null;
    }

    @Override
    public CompletableFuture<List<Order>> getOrdersByUserId(long userId) {
        return null;
    }

    @Override
    public CompletableFuture<Long> createOrder(Order order) {
        return null;
    }

    @Override
    public CompletableFuture<List<BookReport>> getBookReports() {
        return null;
    }

    private static class JsonBodyHandler<W> implements HttpResponse.BodyHandler<W> {
//        private final TypeReference<W> valueTypeRef;
        static ObjectMapper MAPPER = new ObjectMapper();

        static {
            MAPPER.registerModule(new JavaTimeModule());
        }

//        public JsonBodyHandler(TypeReference<W> valueTypeRef) {
//            this.valueTypeRef = valueTypeRef;
//            MAPPER.registerModule(new JavaTimeModule());
//        }

        @Override
        public HttpResponse.BodySubscriber<W> apply(HttpResponse.ResponseInfo responseInfo) {
            return asJSON();
        }

        private static <W> HttpResponse.BodySubscriber<W> asJSON() {
            HttpResponse.BodySubscriber<InputStream> upstream = HttpResponse.BodySubscribers.ofInputStream();
            HttpResponse.BodySubscriber<W> downstream = HttpResponse.BodySubscribers.mapping(upstream, (InputStream is) -> {
                try (InputStream stream = is) {
                    return MAPPER.readValue(stream, new TypeReference<W>() {});
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            });
            return downstream;
        }
    }

}
