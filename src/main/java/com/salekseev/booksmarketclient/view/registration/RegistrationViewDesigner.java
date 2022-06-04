package com.salekseev.booksmarketclient.view.registration;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

public abstract class RegistrationViewDesigner extends VBox {

    JFXTextField loginField;
    JFXPasswordField passwordField;
    JFXButton regButton;

    public RegistrationViewDesigner() {
        createView();
    }

    private void createView() {
        loginField = new JFXTextField();
        loginField.setPromptText("Логин");

        passwordField = new JFXPasswordField();
        passwordField.setPromptText("Пароль");

        regButton = new JFXButton("Войти");
        regButton.setOnAction(this::regButtonOnAction);

        setSpacing(10);
        setPadding(new Insets(10, 50, 10, 50));
        getChildren().addAll(loginField, passwordField, regButton);
    }

    protected abstract void regButtonOnAction(ActionEvent event);

}
