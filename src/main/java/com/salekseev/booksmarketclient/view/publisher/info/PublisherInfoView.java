package com.salekseev.booksmarketclient.view.publisher.info;

import com.salekseev.booksmarketclient.model.Publisher;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.util.Random;
import java.util.function.Consumer;

public class PublisherInfoView extends PublisherInfoViewDesigner {

    private final Consumer<Publisher> publisherConsumer;
    private Publisher publisher;

    public PublisherInfoView(Consumer<Publisher> publisherConsumer) {
        this.publisherConsumer = publisherConsumer;
        bindFields();
    }

    public PublisherInfoView(Consumer<Publisher> publisherConsumer, Publisher publisher) {
        this.publisherConsumer = publisherConsumer;
        this.publisher = publisher;
        this.nameField.setText(publisher.getName());
        this.phoneField.setText(publisher.getPhone());
        this.emailField.setText(publisher.getEmail());
        this.informationArea.setText(publisher.getInformation());
    }

    @Override
    protected void savePublisherButtonOnAction(ActionEvent event) {
        Publisher publisher = this.buildPublisher();

        if (this.publisher != null) {
            publisher.setId(this.publisher.getId());
        }

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
    }

    private Publisher buildPublisher() {
        Publisher publisher = new Publisher();
        publisher.setId(new Random().nextLong() + new Random().nextLong());
        publisher.setName(nameField.getText());
        publisher.setPhone(phoneField.getText());
        publisher.setEmail(emailField.getText());
        publisher.setInformation(informationArea.getText());
        return publisher;
    }

}
