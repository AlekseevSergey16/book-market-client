package com.salekseev.booksmarketclient.service.retrofit;

import com.salekseev.booksmarketclient.model.Author;
import com.salekseev.booksmarketclient.model.Book;
import com.salekseev.booksmarketclient.model.Genre;
import com.salekseev.booksmarketclient.model.Publisher;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface BookMarketApi {

    @GET("/book-market/api/authors")
    Call<List<Author>> getAllAuthors();

    @POST("/book-market/api/authors")
    Call<Long> createAuthor(@Body Author author);

    @PUT("/book-market/api/authors")
    Call<Void> updateAuthor(@Body Author author);

    @DELETE("/book-market/api/authors/{id}")
    Call<Void> deleteAuthor(@Path("id") long id);


    @GET("/book-market/api/publishers")
    Call<List<Publisher>> getAllPublishers();

    @POST("/book-market/api/publishers")
    Call<Long> createPublisher(@Body Publisher publisher);

    @PUT("/book-market/api/publishers")
    Call<Void> updatePublisher(@Body Publisher publisher);

    @DELETE("/book-market/api/publishers/{id}")
    Call<Void> deletePublisher(@Path("id") long id);

    @POST("/book-market/api/books")
    Call<Long> createBook(@Body Book book);

    @GET("/book-market/api/books")
    Call<List<Book>> getAllBooks();

    @GET("/book-market/api/books/search")
    Call<List<Book>> getBooksByAuthor(@Query("authorId") long authorId);

    @PUT("/book-market/api/books")
    Call<Void> updateBook(@Body Book book);

    @DELETE("/book-market/api/books/{id}")
    Call<Void> deleteBook(@Path("id") long id);

    @GET("/book-market/api/genres")
    Call<List<Genre>> getAllGenres();

}
