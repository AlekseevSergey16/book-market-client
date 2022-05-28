package com.salekseev.booksmarketclient.view.author.bookInfo;

import com.salekseev.booksmarketclient.model.Author;

public class BooksOfAuthorView extends BooksOfAuthorViewDesigner {

    private final Author author;
    private final BooksOfAuthorVM viewModel = new BooksOfAuthorVM();

    public BooksOfAuthorView(Author author) {
        this.author = author;
        this.viewModel.loadBooks(author.getId());
        this.bindFields();
    }

    private void bindFields() {
        this.tableView.setItems(viewModel.getBookObservableList());
    }

}
