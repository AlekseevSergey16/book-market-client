package com.salekseev.booksmarketclient.view.shipment.supplierAdd;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class SupplierAddView extends SupplierAddViewDesigner {

    private final SupplierAddVM viewModel = new SupplierAddVM();

    public SupplierAddView() {
        bindFields();
    }

    @Override
    protected void saveSupplierButtonOnAction(ActionEvent event) {
        viewModel.addSupplier();
        ((Stage)((Node) event.getSource()).getScene().getWindow()).close();
    }

    @Override
    protected void cancelSupplierButtonOnAction(ActionEvent event) {
        ((Stage)((Node) event.getSource()).getScene().getWindow()).close();
    }

    private void bindFields() {
        viewModel.nameProperty().bind(nameField.textProperty());

        saveSupplierButton.disableProperty()
                .bind(nameField.textProperty().isEmpty());
    }

}
