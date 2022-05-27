package com.salekseev.booksmarketclient.view.author.info;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public abstract class AuthorInfoViewDesigner extends StackPane {

    protected JFXTextField firstNameField;
    protected JFXTextField lastNameField;
    protected JFXTextField middleNameField;
    protected JFXTextArea informationArea;

    protected JFXButton saveAuthorButton;
    protected JFXButton cancelAuthorButton;

    public AuthorInfoViewDesigner() {
        createView();
    }

    private void createView() {
        VBox vBox = new VBox(30);
        vBox.setPadding(new Insets(40.0, 50.0, 30.0, 50.0));

        lastNameField = new JFXTextField();
        lastNameField.setPromptText("Фамилия");

        firstNameField = new JFXTextField();
        firstNameField.setPromptText("Имя");

        middleNameField = new JFXTextField();
        middleNameField.setPromptText("Отчество");

        informationArea = new JFXTextArea();
        informationArea.setPromptText("Информация");

        saveAuthorButton = new JFXButton("Сохранить");
        saveAuthorButton.setOnAction(this::saveAuthorButtonOnAction);
        cancelAuthorButton = new JFXButton("Отменить");
        cancelAuthorButton.setOnAction(this::cancelAuthorButtonOnAction);

        HBox buttonsHBox = new HBox(5, saveAuthorButton, cancelAuthorButton);
        buttonsHBox.setAlignment(Pos.CENTER_RIGHT);

        vBox.getChildren().addAll(lastNameField, firstNameField, middleNameField, informationArea, buttonsHBox);
        vBox.setStyle("-fx-background-color: #fafafa;");

        getChildren().add(vBox);
        getStylesheets().add(getClass().getResource("/css/add-author-view.css").toExternalForm());;
    }

    protected abstract void saveAuthorButtonOnAction(ActionEvent event);
    protected abstract void cancelAuthorButtonOnAction(ActionEvent event);

}
