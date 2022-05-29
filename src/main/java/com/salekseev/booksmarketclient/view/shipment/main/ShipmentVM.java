package com.salekseev.booksmarketclient.view.shipment.main;

import com.salekseev.booksmarketclient.model.Shipment;
import com.salekseev.booksmarketclient.service.retrofit.BookMarketService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ShipmentVM {

    private final ObservableList<Shipment> shipmentObservableList = FXCollections
            .observableArrayList(new Shipment());

    private final BookMarketService service = BookMarketService.getInstance();

    public void loadShipments() {
        service.getAllShipments()
                .thenAccept(shipments -> Platform.runLater(() -> shipmentObservableList.setAll(shipments)));
    }

    public void addShipment(Shipment shipment) {
        service.createShipment(shipment)
                .thenAccept(shipmentId -> Platform.runLater(() -> {
                    shipment.setId(shipmentId);
                    shipmentObservableList.add(shipment);
                }));
    }

    public ObservableList<Shipment> getShipmentObservableList() {
        return shipmentObservableList;
    }
}
