package com.salekseev.booksmarketclient.view.shipment.shipmentInfo;

import com.salekseev.booksmarketclient.model.Shipment;
import com.salekseev.booksmarketclient.model.ShipmentItem;
import com.salekseev.booksmarketclient.util.FxUtil;
import com.salekseev.booksmarketclient.view.shipment.bookSelect.BookSelectView;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ShipmentInfoView extends ShipmentInfoViewDesigner {

    private final ShipmentInfoVM viewModel;
    private final Consumer<Shipment> shipmentConsumer;

    public ShipmentInfoView(Consumer<Shipment> shipmentConsumer) {
        this.shipmentConsumer = shipmentConsumer;
        this.viewModel = new ShipmentInfoVM();
        viewModel.loadSuppliers();
        bindFields();
    }

    @Override
    protected void saveShipmentButtonOnAction(ActionEvent event) {
        shipmentConsumer.accept(buildShipment());
    }

    @Override
    protected void cancelShipmentButtonOnAction(ActionEvent event) {
        ((Stage)((Node) event.getSource()).getScene().getWindow()).close();
    }

    @Override
    protected void addShipmentItemButtonOnAction(ActionEvent event) {
        Consumer<ShipmentItem> itemConsumer = shipmentItem -> {
            shipmentItemListView.getItems().add(shipmentItem);
            int totalAmount = shipmentItemListView.getItems().stream()
                    .mapToInt(ShipmentItem::getAmount)
                    .sum();

            totalAmountField.setText(String.valueOf(totalAmount));
        };

        FxUtil.showView("Книги", new BookSelectView(itemConsumer), getScene().getWindow());
    }

    private void bindFields() {
        this.supplierComboBox.setItems(viewModel.getSupplierObservableList());
    }

    private Shipment buildShipment() {
        Shipment shipment = new Shipment();
        shipment.setShipmentDate(LocalDate.now());
        shipment.setSupplier(supplierComboBox.getValue());
        shipment.setItems(new ArrayList<>(shipmentItemListView.getItems()));
        shipment.setTotalAmount(Integer.parseInt(totalAmountField.getText()));

        return shipment;
    }

}
