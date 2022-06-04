package com.salekseev.booksmarketclient.view.shipment.bookSelect;

import com.salekseev.booksmarketclient.model.Book;
import com.salekseev.booksmarketclient.model.ShipmentItem;
import com.salekseev.booksmarketclient.view.author.bookInfo.BooksOfAuthorVM;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.util.function.Consumer;

public class BookSelectView extends BookSelectViewDesigner {

    private final BookSelectVM viewModel;
    private final Consumer<ShipmentItem> itemConsumer;

    public BookSelectView(Consumer<ShipmentItem> itemConsumer) {
        this.itemConsumer = itemConsumer;
        this.viewModel = new BookSelectVM();
        this.viewModel.loadBooks();
        bindFields();
    }

    @Override
    protected void okButtonOnAction(ActionEvent event) {
        itemConsumer.accept(buildShipmentItem());
        ((Stage)((Node) event.getSource()).getScene().getWindow()).close();
    }

    private void bindFields() {
        this.tableView.setItems(viewModel.getBookObservableList());
        this.okButton.disableProperty()
                .bind(tableView.getSelectionModel().selectionProperty().isNull()
                .or(amountField.textProperty().isEmpty()));
    }

    private ShipmentItem buildShipmentItem() {
        Book book = tableView.getSelectionModel().getSelectedValues().stream().findAny().orElseThrow();

        ShipmentItem item = new ShipmentItem();
        item.setBook(book);
        item.setAmount(Integer.parseInt(amountField.getText()));

        return item;
    }

}
