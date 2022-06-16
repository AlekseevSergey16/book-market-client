package com.salekseev.booksmarketclient.view.author.bookInfo;

import com.salekseev.booksmarketclient.model.Author;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class BooksOfAuthorView extends BooksOfAuthorViewDesigner {

    private final BooksOfAuthorVM viewModel = new BooksOfAuthorVM();

    public BooksOfAuthorView(Author author) {
        this.viewModel.loadBooks(author.getId());
        this.bindFields();
    }

    private void bindFields() {
        this.tableView.setItems(viewModel.getBookObservableList());
    }

    @Override
    protected void okButtonOnAction(ActionEvent event) {
        ((Stage)((Node) event.getSource()).getScene().getWindow()).close();
    }
}
