package com.salekseev.booksmarketclient.view.shipment.supplierAdd;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

abstract class SupplierAddViewDesigner extends StackPane {

    protected JFXTextField nameField;

    protected JFXButton saveSupplierButton;
    protected JFXButton cancelSupplierButton;

    public SupplierAddViewDesigner() {
        getChildren().add(createView());
        getStylesheets().add(getClass().getResource("/css/add-author-view.css").toExternalForm());
    }

    private Node createView() {
        VBox vBox = new VBox(30);
        vBox.setPadding(new Insets(40.0, 50.0, 30.0, 50.0));

        nameField = new JFXTextField();
        nameField.setPromptText("Наименование");

        saveSupplierButton = new JFXButton("Сохранить");
        saveSupplierButton.setOnAction(this::saveSupplierButtonOnAction);
        cancelSupplierButton = new JFXButton("Отменить");
        cancelSupplierButton.setOnAction(this::cancelSupplierButtonOnAction);

        HBox buttonsHBox = new HBox(5, saveSupplierButton, cancelSupplierButton);
        buttonsHBox.setAlignment(Pos.CENTER_RIGHT);

        vBox.getChildren().addAll(nameField, buttonsHBox);
        vBox.setStyle("-fx-background-color: #fafafa;");

        return vBox;
    }

    protected abstract void saveSupplierButtonOnAction(ActionEvent event);
    protected abstract void cancelSupplierButtonOnAction(ActionEvent event);

}
