package com.salekseev.booksmarketclient.view.login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public abstract class LoginViewDesigner extends VBox {

    JFXTextField loginField;
    JFXPasswordField passwordField;
    JFXButton loginButton;
    Label registrationLabel;
    Label errorLabel;

    public LoginViewDesigner() {
        createView();
    }

    private void createView() {
        loginField = new JFXTextField();
        loginField.setPromptText("Логин");

        passwordField = new JFXPasswordField();
        passwordField.setPromptText("Пароль");

        loginButton = new JFXButton("Войти");
        loginButton.setOnAction(this::loginButtonOnAction);

        registrationLabel = new Label("Зарегистрироваться");
        registrationLabel.setOnMouseClicked(this::registrationLabelOnClick);

        errorLabel = new Label("Проверьте введенные данные. Введенные данные неверны");
        errorLabel.setVisible(false);
        errorLabel.setStyle("-fx-text-fill: red");

        setSpacing(10);
        setPadding(new Insets(10, 50, 10, 50));
        getChildren().addAll(loginField, passwordField, errorLabel, registrationLabel, loginButton);
    }

    protected abstract void loginButtonOnAction(ActionEvent event);
    protected abstract void registrationLabelOnClick(MouseEvent event);

}
