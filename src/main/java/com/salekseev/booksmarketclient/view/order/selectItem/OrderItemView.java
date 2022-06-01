package com.salekseev.booksmarketclient.view.order.selectItem;

import com.salekseev.booksmarketclient.model.Book;
import com.salekseev.booksmarketclient.model.OrderItem;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

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
