package com.salekseev.booksmarketclient.view.book.info;

import com.salekseev.booksmarketclient.model.Author;
import com.salekseev.booksmarketclient.model.Book;
import com.salekseev.booksmarketclient.model.Genre;
import com.salekseev.booksmarketclient.model.Publisher;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleMapProperty;
import javafx.beans.value.ObservableMapValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class BookInfoView extends BookInfoViewDesigner {

    private final BookInfoVM viewModel;
    private final Consumer<Book> bookConsumer;
    private Book book;

    public BookInfoView(Consumer<Book> bookConsumer) {
        this.viewModel = new BookInfoVM();
        this.bookConsumer = bookConsumer;
        viewModel.loadDataFromServer();
        bindFields();
    }
    public BookInfoView(Consumer<Book> bookConsumer, Book book) {
        this.viewModel = new BookInfoVM();
        this.bookConsumer = bookConsumer;
        this.book = book;
        viewModel.loadDataFromServer();
        bindFields();
        fillFields();
    }

    @Override
    protected void saveBookButtonOnAction(ActionEvent event) {
        Book book = this.buildBook();

        if (this.book != null) {
            book.setId(this.book.getId());
        }

        bookConsumer.accept(book);

        ((Stage)((Node) event.getSource()).getScene().getWindow()).close();
    }

    @Override
    protected void cancelBookButtonOnAction(ActionEvent event) {
        ((Stage)((Node) event.getSource()).getScene().getWindow()).close();
    }

    private void bindFields() {
        authorListView.setItems(viewModel.getAuthorObservableList());
        publisherComboBox.setItems(viewModel.getPublisherObservableList());
        genreComboBox.setItems(viewModel.getGenreObservableList());

        saveBookButton.disableProperty()
                .bind(authorListView.getSelectionModel().selectionProperty().emptyProperty()
                        .or(publisherComboBox.getSelectionModel().selectedItemProperty().isNull())
                        .or(genreComboBox.getSelectionModel().selectedItemProperty().isNull())
                        .or(titleField.textProperty().isEmpty())
                        .or(publicationYearField.textProperty().isEmpty())
                        .or(costField.textProperty().isEmpty())
                        .or(pagesField.textProperty().isEmpty())
                        .or(weightField.textProperty().isEmpty()));
    }

    private void fillFields() {
        titleField.setText(book.getTitle());
        publicationYearField.setText(String.valueOf(book.getPublicationYear()));
        costField.setText(String.valueOf(book.getCost()));
        pagesField.setText(String.valueOf(book.getPages()));
        weightField.setText(String.valueOf(book.getWeight()));
        descriptionArea.setText(book.getDescription());

        authorListView.getItems().addListener(new ListChangeListener<Author>() {
            @Override
            public void onChanged(Change<? extends Author> c) {
                List<Long> authorIds = book.getAuthors().stream()
                        .map(Author::getId)
                        .collect(Collectors.toList());

                List<Author> authors = authorListView.getItems().stream()
                        .filter(author -> authorIds.contains(author.getId()))
                        .collect(Collectors.toList());

                authors.forEach(author -> authorListView.getSelectionModel().selectItem(author));
            }
        });
        publisherComboBox.getItems().addListener(new ListChangeListener<Publisher>() {
            @Override
            public void onChanged(Change<? extends Publisher> c) {
                Publisher publisher = publisherComboBox.getItems().stream()
                        .filter(p -> p.getId().equals(book.getPublisher().getId()))
                        .findFirst()
                        .get();
                publisherComboBox.getSelectionModel().select(publisher);
            }
        });
        genreComboBox.getItems().addListener(new ListChangeListener<Genre>() {
            @Override
            public void onChanged(Change<? extends Genre> c) {
                Genre genre = genreComboBox.getItems().stream()
                        .filter(g -> g.getId().equals(book.getGenre().getId()))
                        .findFirst()
                        .get();
                genreComboBox.getSelectionModel().select(genre);
            }
        });
        genreComboBox.getSelectionModel().select(book.getGenre());
    }

    private Book buildBook() {
        Book book = new Book();
        book.setId(new Random().nextLong() + new Random().nextLong());
        book.setTitle(titleField.getText());
        book.setDescription(descriptionArea.getText());
        book.setAuthors(new HashSet<>(authorListView.getSelectionModel().getSelectedValues()));
        book.setGenre(genreComboBox.getValue());
        book.setPublisher(publisherComboBox.getValue());
        book.setPublicationYear(Integer.parseInt(publicationYearField.getText()));
        book.setCost(Double.parseDouble(costField.getText()));
        book.setPages(Integer.parseInt(pagesField.getText()));
        book.setWeight(Integer.parseInt(weightField.getText()));
        book.setAmount(0);
        return book;
    }

}
