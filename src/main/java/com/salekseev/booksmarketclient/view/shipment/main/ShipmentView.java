package com.salekseev.booksmarketclient.view.shipment.main;

import com.salekseev.booksmarketclient.model.Shipment;
import com.salekseev.booksmarketclient.util.FxUtil;
import com.salekseev.booksmarketclient.view.shipment.shipmentInfo.ShipmentInfoView;
import com.salekseev.booksmarketclient.view.shipment.supplierAdd.SupplierAddView;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.function.Consumer;

public class ShipmentView extends ShipmentViewDesigner {

    private final ShipmentVM viewModel;

    private final Consumer<Shipment> addShipmentConsumer = this::addShipment;

    public ShipmentView() {
        this.viewModel = new ShipmentVM();
        viewModel.loadShipments();
        bindFields();
    }

    @Override
    protected void addShipmentButtonOnAction(ActionEvent event) {
        FxUtil.showView("Добавление Поставки", new ShipmentInfoView(addShipmentConsumer), this.getScene().getWindow());
    }

    @Override
    protected void showSupplierItemOnAction(ActionEvent event) {
        FxUtil.showView("Добавление Поставщика", new SupplierAddView(), this.getScene().getWindow());
    }

    @Override
    protected void showDetailedItemOnAction(ActionEvent event) {
        Shipment shipment = tableView.getSelectionModel().getSelectedValues().get(0);
        FxUtil.showView("Просмотр Поставки", new ShipmentInfoView(shipment), this.getScene().getWindow());
    }

    private void bindFields() {
        this.tableView.setItems(viewModel.getShipmentObservableList());
    }

    private void addShipment(Shipment shipment) {
        viewModel.addShipment(shipment);
    }

}
