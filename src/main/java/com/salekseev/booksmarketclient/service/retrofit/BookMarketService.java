package com.salekseev.booksmarketclient.service.retrofit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salekseev.booksmarketclient.model.Author;
import com.salekseev.booksmarketclient.model.Book;
import com.salekseev.booksmarketclient.model.Genre;
import com.salekseev.booksmarketclient.model.Publisher;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.Path;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class BookMarketService {

    private static final BookMarketService INSTANCE = new BookMarketService();
    BookMarketApi api;

    public static BookMarketService getInstance() {
        return INSTANCE;
    }

    private BookMarketService() {
        ObjectMapper objectMapper = new ObjectMapper();
        OkHttpClient httpClient = new OkHttpClient.Builder().build();

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

}
