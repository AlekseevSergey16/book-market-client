package com.salekseev.booksmarketclient.view.author.info;

import com.salekseev.booksmarketclient.model.Author;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AuthorInfoVM {

    private final LongProperty id = new SimpleLongProperty();
    private final StringProperty firstName = new SimpleStringProperty();
    private final StringProperty lastName = new SimpleStringProperty();
    private final StringProperty middleName = new SimpleStringProperty();
    private final StringProperty information = new SimpleStringProperty();

    public AuthorInfoVM() {
    }

    public AuthorInfoVM(Author author) {
        this.id.setValue(author.getId());
        this.firstName.setValue(author.getFirstName());
        this.lastName.setValue(author.getLastName());
        this.middleName.setValue(author.getMiddleName());
        this.information.setValue(author.getInformation());
    }

    public Author buildAuthor() {
        return Author.builder()
                .id(id.getValue())
                .firstName(firstName.get())
                .lastName(lastName.get())
                .middleName(middleName.get())
                .information(information.get())
                .build();
    }

    public LongProperty idProperty() {
        return id;
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public StringProperty middleNameProperty() {
        return middleName;
    }

    public StringProperty informationProperty() {
        return information;
    }

}
