module com.salekseev.booksmarketclient {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    requires com.jfoenix;
    requires MaterialFX;
    requires org.controlsfx.controls;
    requires retrofit2;
    requires retrofit2.converter.jackson;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires okhttp3;
    requires java.net.http;
    requires com.fasterxml.jackson.datatype.jsr310;
//    opens com.salekseev.booksmarketclient to javafx.fxml;
    exports com.salekseev.booksmarketclient;
    exports com.salekseev.booksmarketclient.model;
}
