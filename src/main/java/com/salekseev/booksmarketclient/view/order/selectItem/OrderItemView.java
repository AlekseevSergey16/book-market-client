package com.salekseev.booksmarketclient.view.order.selectItem;

import com.salekseev.booksmarketclient.model.Book;
import com.salekseev.booksmarketclient.model.OrderItem;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.function.Consumer;

public class OrderItemView extends OrderItemViewDesigner {

    private final OrderItemVM viewModel = new OrderItemVM();
    private final Consumer<OrderItem> itemConsumer;

    public OrderItemView(Consumer<OrderItem> itemConsumer) {
        this.itemConsumer = itemConsumer;
        this.viewModel.loadBooks();
        bindFields();
    }

    @Override
    protected void okButtonOnAction(ActionEvent event) {
        itemConsumer.accept(buildOrderItem());
        ((Stage)((Node) event.getSource()).getScene().getWindow()).close();
    }

    private void bindFields() {
        this.tableView.setItems(viewModel.getBookObservableList());
//        this.okButton.disableProperty()
//                .bind(quantityField.textProperty().isEmpty());

        this.tableView.getSelectionModel().selectionProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) return;

            if (quantityField.getText().isEmpty()) return;

            Book book = (Book) newValue.values().toArray()[0];
            int amount = Integer.parseInt(quantityField.getText());

            if (amount > book.getAmount()) {
                this.okButton.setDisable(true);
            }

        });

        this.quantityField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null || newValue.equals("")) {
                this.okButton.setDisable(true);
                return;
            }

            int quantity = Integer.parseInt(newValue);

            this.okButton.setDisable(quantity > tableView.getSelectionModel().getSelectedValues().get(0).getAmount());

        });
    }

    private OrderItem buildOrderItem() {
        Book book = tableView.getSelectionModel().getSelectedValues().stream()
                .findAny().orElseThrow();

        OrderItem item = new OrderItem();
        item.setBook(book);
        item.setQuantity(Integer.parseInt(quantityField.getText()));

        return item;
    }

}
