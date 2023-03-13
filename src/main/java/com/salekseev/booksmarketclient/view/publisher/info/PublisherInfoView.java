package com.salekseev.booksmarketclient.view.publisher.info;

import com.jfoenix.validation.RequiredFieldValidator;
import com.salekseev.booksmarketclient.model.Publisher;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.util.function.Consumer;

public class PublisherInfoView extends PublisherInfoViewDesigner {

    private final Consumer<Publisher> publisherConsumer;
    private final PublisherInfoVM viewModel;

    public PublisherInfoView(Consumer<Publisher> publisherConsumer) {
        this.publisherConsumer = publisherConsumer;
        this.viewModel = new PublisherInfoVM();
        bindFields();
        keyPressed();
    }

    public PublisherInfoView(Consumer<Publisher> publisherConsumer, Publisher publisher) {
        this.publisherConsumer = publisherConsumer;
        this.viewModel = new PublisherInfoVM(publisher);
        bindFields();
        keyPressed();
    }

    @Override
    protected void savePublisherButtonOnAction(ActionEvent event) {
        Publisher publisher = viewModel.buildPublisher();
        publisherConsumer.accept(publisher);

        ((Stage)((Node) event.getSource()).getScene().getWindow()).close();
    }

    @Override
    protected void cancelPublisherButtonOnAction(ActionEvent event) {
        ((Stage)((Node) event.getSource()).getScene().getWindow()).close();
    }

    private void bindFields() {
        savePublisherButton.disableProperty()
                .bind(nameField.textProperty().isEmpty()
                    .or(phoneField.textProperty().isEmpty())
                    .or(emailField.textProperty().isEmpty()));

        nameField.textProperty().bindBidirectional(viewModel.nameProperty());
        phoneField.textProperty().bindBidirectional(viewModel.phoneProperty());
        emailField.textProperty().bindBidirectional(viewModel.emailProperty());
        informationArea.textProperty().bindBidirectional(viewModel.informationProperty());

        nameField.setValidators(new RequiredFieldValidator("Наименование обязательно"));

        nameField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (nameField.getText().trim().isEmpty()) {
                nameField.validate();
            } else if (!nameField.getText().trim().isEmpty()
                    && nameField.getActiveValidator() != null) {
                nameField.resetValidation();
            }
        });
    }

    private void keyPressed() {
        this.setOnKeyPressed(event -> {
            Publisher publisher = viewModel.buildPublisher();
            publisherConsumer.accept(publisher);

            ((Stage)((Node) event.getSource()).getScene().getWindow()).close();
        });
    }

}
