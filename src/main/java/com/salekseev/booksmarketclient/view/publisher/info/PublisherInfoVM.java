package com.salekseev.booksmarketclient.view.publisher.info;

import com.salekseev.booksmarketclient.model.Publisher;
import javafx.beans.property.*;

public class PublisherInfoVM {

    private final LongProperty id = new SimpleLongProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty phone = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();
    private final StringProperty information = new SimpleStringProperty();

    private final BooleanProperty requiredName = new SimpleBooleanProperty();

    public PublisherInfoVM() {
    }

    public PublisherInfoVM(Publisher publisher) {
        this.id.set(publisher.getId());
        this.name.set(publisher.getName());
        this.phone.set(publisher.getPhone());
        this.email.set(publisher.getEmail());
        this.information.set(publisher.getInformation());
    }

    public Publisher buildPublisher() {
        Publisher publisher = new Publisher();
        publisher.setId(id.get());
        publisher.setName(name.get());
        publisher.setPhone(phone.get());
        publisher.setEmail(email.get());
        publisher.setInformation(information.get());
        return publisher;
    }

    public LongProperty idProperty() {
        return id;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public StringProperty emailProperty() {
        return email;
    }

    public StringProperty informationProperty() {
        return information;
    }

    public BooleanProperty requiredNameProperty() {
        return requiredName;
    }
}
