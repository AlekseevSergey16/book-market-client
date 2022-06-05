package com.salekseev.booksmarketclient.view.order.main;

import com.salekseev.booksmarketclient.model.Order;
import com.salekseev.booksmarketclient.util.FxUtil;
import com.salekseev.booksmarketclient.view.order.info.OrderInfoView;
import javafx.event.ActionEvent;

public class OrderView extends OrderViewDesigner {

    private final OrderVM viewModel = new OrderVM();

    public OrderView() {
        viewModel.loadOrders();
        bindFields();
    }

    public OrderView(long userId) {
        viewModel.loadOrders(userId);
        bindFields();
    }

    @Override
    protected void addOrderButtonOnAction(ActionEvent event) {
        FxUtil.showView("Оформление заказа", new OrderInfoView(viewModel::addOrder), this.getScene().getWindow());
    }

    @Override
    protected void showDetailedItemOnAction(ActionEvent event) {
        Order order = tableView.getSelectionModel().getSelectedValues().get(0);
        FxUtil.showView("Просмотр заказа", new OrderInfoView(order), this.getScene().getWindow());
    }

    @Override
    protected void createReportItemOnAction(ActionEvent event) {
        viewModel.loadReport();
    }

    private void bindFields() {
        tableView.setItems(viewModel.getOrderObservableList());
    }

}
