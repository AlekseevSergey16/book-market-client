package com.salekseev.booksmarketclient.view.book.main;

import com.salekseev.booksmarketclient.model.Book;
import com.salekseev.booksmarketclient.util.FxUtil;
import com.salekseev.booksmarketclient.view.book.info.BookInfoView;
import javafx.collections.MapChangeListener;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;

import java.util.List;
import java.util.function.Consumer;

public class BookView extends BookViewDesigner {

    private final BookVM viewModel = new BookVM();

    private final Consumer<Book> addBookConsumer = this::addBook;
    private final Consumer<Book> editBookConsumer = this::editBook;

    public BookView() {
        viewModel.loadBooks();
        bindFields();
    }

    @Override
    protected void addBookButtonOnAction(ActionEvent event) {
        FxUtil.showView("Добавление Книги", new BookInfoView(addBookConsumer), this.getScene().getWindow());
    }

    @Override
    protected void editBookButtonOnAction(ActionEvent event) {
        Book selectedBook = (Book) tableView.getSelectionModel().getSelectedValues().toArray()[0];
        FxUtil.showView("Изменение Книги", new BookInfoView(editBookConsumer, selectedBook), this.getScene().getWindow());
    }

    @Override
    protected void deleteBookButtonOnAction(ActionEvent event) {
        deleteBooks(tableView.getSelectionModel().getSelectedValues());
    }

    private void bindFields() {
        tableView.getSelectionModel().selectionProperty().addListener((MapChangeListener<Integer, Book>) change -> {
            if (change.getMap().size() == 1) {
                editBookButton.setDisable(false);
                deleteBookButton.setDisable(false);
            } else if (change.getMap().size() > 1) {
                editBookButton.setDisable(true);
                deleteBookButton.setDisable(false);
            } else if (change.getMap().isEmpty()) {
                editBookButton.setDisable(true);
                deleteBookButton.setDisable(true);
            }
        });

        FilteredList<Book> bookFilteredList = new FilteredList<>(viewModel.getBookObservableList());

        filterBookField.textProperty().addListener((observable, oldValue, newValue) -> {
            bookFilteredList.setPredicate(book -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (book.getTitle().toLowerCase().contains(lowerCaseFilter)) return true;

                return false;
            });
        });

        tableView.setItems(bookFilteredList);
    }

    private void addBook(Book book) {
        viewModel.addBook(book);
    }

    private void editBook(Book book) {
        viewModel.updateBook(book);
    }

    private void deleteBooks(List<Book> books) {
        books.stream()
                .map(Book::getId)
                .forEach(viewModel::deleteBook);
    }

}
