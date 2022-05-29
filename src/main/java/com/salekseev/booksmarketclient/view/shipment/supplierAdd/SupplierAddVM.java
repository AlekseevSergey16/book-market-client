package com.salekseev.booksmarketclient.view.shipment.supplierAdd;

import com.salekseev.booksmarketclient.model.Supplier;
import com.salekseev.booksmarketclient.service.retrofit.BookMarketService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SupplierAddVM {

    private final StringProperty name = new SimpleStringProperty();
    private final BookMarketService service = BookMarketService.getInstance();

    public void addSupplier() {
        Supplier supplier = new Supplier(name.get());
        service.createSupplier(supplier);
    }

    public StringProperty nameProperty() {
        return name;
    }
}
