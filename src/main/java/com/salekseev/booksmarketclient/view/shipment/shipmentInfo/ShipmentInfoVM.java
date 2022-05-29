package com.salekseev.booksmarketclient.view.shipment.shipmentInfo;

import com.salekseev.booksmarketclient.model.Shipment;
import com.salekseev.booksmarketclient.model.ShipmentItem;
import com.salekseev.booksmarketclient.model.Supplier;
import com.salekseev.booksmarketclient.service.retrofit.BookMarketService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ShipmentInfoVM {

    private final ObservableList<Supplier> supplierObservableList = FXCollections
            .observableArrayList(new Supplier());

    private final ObservableList<ShipmentItem> shipmentItemObservableList = FXCollections
            .observableArrayList(new ShipmentItem());

    private final BookMarketService service = BookMarketService.getInstance();

    public void loadSuppliers() {
        service.getAllSuppliers()
                .thenAccept(suppliers -> Platform.runLater(() -> supplierObservableList.setAll(suppliers)));
    }

    public ObservableList<Supplier> getSupplierObservableList() {
        return supplierObservableList;
    }

    public ObservableList<ShipmentItem> getShipmentItemObservableList() {
        return shipmentItemObservableList;
    }
}
