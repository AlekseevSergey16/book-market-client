package com.salekseev.booksmarketclient.view.login;

import com.salekseev.booksmarketclient.model.User;
import com.salekseev.booksmarketclient.service.retrofit.BookMarketService;
import com.salekseev.booksmarketclient.util.FxUtil;
import com.salekseev.booksmarketclient.view.mainView.MainView;
import com.salekseev.booksmarketclient.view.registration.RegistrationView;
import com.salekseev.booksmarketclient.view.user.MainUserView;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.Optional;

public class LoginView extends LoginViewDesigner {

    private final BookMarketService service = BookMarketService.getInstance();

    @Override
    protected void loginButtonOnAction(ActionEvent event) {
        User user = buildUser();

        Optional<User> userOptional = service.getUser(user.getUsername(), user.getPassword());

        if (userOptional.isPresent()) {
            User user1 = userOptional.get();

            if (user1.getRole().equals("admin")) {
                var root = new MainView();

                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setTitle("Книжный магазин (" + user1.getUsername() + ")");
                stage.getIcons().add(new Image(getClass().getResource("/icons/main-icon.png").toExternalForm()));
                stage.setScene(scene);
                stage.show();

                ((Stage)((Node) event.getSource()).getScene().getWindow()).close();
            } else if (user1.getRole().equals("user")) {
                var root = new MainUserView(user1.getId());

                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setTitle("Книжный магазин (" + user1.getUsername() + ")");
                stage.getIcons().add(new Image(getClass().getResource("/icons/main-icon.png").toExternalForm()));
                stage.setScene(scene);
                stage.show();

                ((Stage)((Node) event.getSource()).getScene().getWindow()).close();
            }
        } else {
            errorLabel.setVisible(true);
        }

    }

    @Override
    protected void registrationLabelOnClick(MouseEvent event) {
        FxUtil.showView("Регистрация", new RegistrationView(), this.getScene().getWindow());
    }

    private User buildUser() {
        User user = new User();
        user.setUsername(this.loginField.getText());
        user.setPassword(this.passwordField.getText());
        return user;
    }

}
