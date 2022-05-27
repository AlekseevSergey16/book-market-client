package com.salekseev.booksmarketclient.view.publisher.info;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

abstract class PublisherInfoViewDesigner extends StackPane {

    protected JFXTextField nameField;
    protected JFXTextField phoneField;
    protected JFXTextField emailField;
    protected JFXTextArea informationArea;

    protected JFXButton savePublisherButton;
    protected JFXButton cancelPublisherButton;

    public PublisherInfoViewDesigner() {
        getChildren().add(createView());
        getStylesheets().add(getClass().getResource("/css/add-author-view.css").toExternalForm());
    }

    private Node createView() {
        VBox vBox = new VBox(30);
        vBox.setPadding(new Insets(40.0, 50.0, 30.0, 50.0));

        nameField = new JFXTextField();
        nameField.setPromptText("Наименование");

        emailField = new JFXTextField();
        emailField.setPromptText("Email");

        phoneField = new JFXTextField();
        phoneField.setPromptText("Номер телефона");

        informationArea = new JFXTextArea();
        informationArea.setPromptText("Информация");

        savePublisherButton = new JFXButton("Сохранить");
        savePublisherButton.setOnAction(this::savePublisherButtonOnAction);
        cancelPublisherButton = new JFXButton("Отменить");
        cancelPublisherButton.setOnAction(this::cancelPublisherButtonOnAction);

        HBox buttonsHBox = new HBox(5, savePublisherButton, cancelPublisherButton);
        buttonsHBox.setAlignment(Pos.CENTER_RIGHT);

        vBox.getChildren().addAll(nameField, emailField, phoneField, informationArea, buttonsHBox);
        vBox.setStyle("-fx-background-color: #fafafa;");

        return vBox;
    }

    protected abstract void savePublisherButtonOnAction(ActionEvent event);
    protected abstract void cancelPublisherButtonOnAction(ActionEvent event);

}
