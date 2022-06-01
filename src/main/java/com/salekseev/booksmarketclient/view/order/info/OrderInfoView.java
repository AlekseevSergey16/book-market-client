package com.salekseev.booksmarketclient.view.order.info;

import com.salekseev.booksmarketclient.model.Order;
import com.salekseev.booksmarketclient.model.OrderItem;
import com.salekseev.booksmarketclient.util.FxUtil;
import com.salekseev.booksmarketclient.view.order.selectItem.OrderItemView;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.function.Consumer;

public class OrderInfoView extends OrderInfoViewDesigner {

    private Consumer<Order> orderConsumer;

    public OrderInfoView(Order order) {
        fillFields(order);
        disableControls();
    }

    public OrderInfoView(Consumer<Order> orderConsumer) {
        this.orderConsumer = orderConsumer;
        bindFields();
    }

    @Override
    protected void saveOrderButtonOnAction(ActionEvent event) {
        orderConsumer.accept(buildOrder());
        ((Stage)((Node) event.getSource()).getScene().getWindow()).close();
    }

    @Override
    protected void cancelOrderButtonOnAction(ActionEvent event) {
        ((Stage)((Node) event.getSource()).getScene().getWindow()).close();
    }

    @Override
    protected void addOrderItemButtonOnAction(ActionEvent event) {
        Consumer<OrderItem> itemConsumer = orderItem -> {
            orderItemListView.getItems().add(orderItem);

            double totalCost = orderItemListView.getItems().stream()
                    .mapToDouble(item -> item.getBook().getCost() * item.getQuantity())
                    .sum();

            totalCostField.setText(String.valueOf(totalCost));
        };

        FxUtil.showView("Книги", new OrderItemView(itemConsumer), this.getScene().getWindow());
    }

    @Override
    protected void deleteOrderItemButton(ActionEvent event) {
        OrderItem item = orderItemListView.getSelectionModel().getSelectedValues().get(0);
        double totalCost = Double.parseDouble(totalCostField.getText());
        totalCost = totalCost - item.getBook().getCost() * item.getQuantity();
        totalCostField.setText(String.valueOf(totalCost));
        orderItemListView.getItems().remove(item);
    }

    private Order buildOrder() {
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setItems(new ArrayList<>(orderItemListView.getItems()));
        order.setTotalCost(Double.parseDouble(totalCostField.getText()));

        return order;
    }

    private void bindFields() {
        this.saveOrderButton.disableProperty().bind(Bindings.isEmpty(orderItemListView.getItems()));
    }

    private void fillFields(Order order) {
        totalCostField.setText(String.valueOf(order.getTotalCost()));
        orderItemListView.setItems(FXCollections.observableArrayList(order.getItems()));
    }

    private void disableControls() {
        this.saveOrderButton.setVisible(false);
        this.cancelOrderButton.setVisible(false);
        this.addOrderItemButton.setDisable(true);
        this.deleteOrderItemButton.setDisable(true);
    }

}
