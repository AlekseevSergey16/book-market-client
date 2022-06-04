package com.salekseev.booksmarketclient.view.registration;

import com.salekseev.booksmarketclient.model.User;
import com.salekseev.booksmarketclient.service.retrofit.BookMarketService;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class RegistrationView extends RegistrationViewDesigner {

    private final BookMarketService service = BookMarketService.getInstance();

    @Override
    protected void regButtonOnAction(ActionEvent event) {
        service.createUser(buildUser());
        ((Stage)((Node) event.getSource()).getScene().getWindow()).close();
    }

    private User buildUser() {
        User user = new User();
        user.setUsername(this.loginField.getText());
        user.setPassword(this.passwordField.getText());
        return user;
    }

}
