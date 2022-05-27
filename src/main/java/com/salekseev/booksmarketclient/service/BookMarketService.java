package com.salekseev.booksmarketclient.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.salekseev.booksmarketclient.model.Author;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class BookMarketService {

    private static final BookMarketService INSTANCE = new BookMarketService();

    private final HttpClient httpClient;
    private final String baseUri = "http://localhost:8080/book-market/api";

    private BookMarketService() {
        httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();
    }

    public static BookMarketService getInstance() {
        return INSTANCE;
    }

    public CompletableFuture<List<Author>> getAllAuthors() {
        UncheckedObjectMapper<List<Author>> mapper = new UncheckedObjectMapper<>();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("Accept", "application/json")
                .header("Content-type", "application/json")
                .uri(URI.create(baseUri + "/authors"))
                .build();

        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(mapper::readValue);
    }

    public CompletableFuture<Long> createAuthor(Author author) {
        UncheckedObjectMapper<Author> mapper = new UncheckedObjectMapper<>();
        String body = mapper.writeValue(author);
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .header("Accept", "application/json")
                .header("Content-type", "application/json")
                .uri(URI.create(baseUri + "/authors"))
                .build();


        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(Long::parseLong);
    }

    public CompletableFuture<Void> updateAuthor(Author author) {
        UncheckedObjectMapper<Author> mapper = new UncheckedObjectMapper<>();
        HttpRequest request = HttpRequest.newBuilder()
                .PUT(HttpRequest.BodyPublishers.ofString(mapper.writeValue(author)))
                .header("Accept", "application/json")
                .header("Content-type", "application/json")
                .uri(URI.create(baseUri + "/authors"))
                .build();

        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(null); // body ->
    }

    static class UncheckedObjectMapper<T> extends com.fasterxml.jackson.databind.ObjectMapper {

        T readValue(String content) {
            try {
                return this.readValue(content, new TypeReference<>() {
                });
            } catch (IOException ioe) {
                throw new CompletionException(ioe);
            }
        }

        String writeValue(T value) {
            try {
                return this.writeValueAsString(value);
            } catch (IOException ioe) {
                throw new CompletionException(ioe);
            }
        }

    }

}
