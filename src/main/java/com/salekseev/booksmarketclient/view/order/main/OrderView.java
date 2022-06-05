package com.salekseev.booksmarketclient.view.order.main;

import com.salekseev.booksmarketclient.model.Order;
import com.salekseev.booksmarketclient.util.FxUtil;
import com.salekseev.booksmarketclient.view.order.chart.BookSoldChart;
import com.salekseev.booksmarketclient.view.order.info.OrderInfoView;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OrderView extends OrderViewDesigner {

    private final OrderVM viewModel = new OrderVM();

    public OrderView() {
        viewModel.loadOrders();
        bindFields();
    }

    public OrderView(long userId) {
        viewModel.loadOrders(userId);
        this.createReportItem.setVisible(false);
        this.showChartItem.setVisible(false);
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

    @Override
    protected void showChartItemOnAction(ActionEvent event) {
        Scene scene = new Scene(new BookSoldChart(), 600, 400);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Диаграмма \"Список продаж\"");
        stage.show();
    }

    private void bindFields() {
        tableView.setItems(viewModel.getOrderObservableList());
    }

}
