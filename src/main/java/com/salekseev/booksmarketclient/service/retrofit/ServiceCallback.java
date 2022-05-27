package com.salekseev.booksmarketclient.service.retrofit;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class ServiceCallback<T> implements Callback<T> {

    CompletableFuture<T> result;

    public ServiceCallback(CompletableFuture<T> result) {
        this.result = result;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            result.complete(response.body());
        } else {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Непредвиденная ошибка");
                alert.setHeaderText("Ошибка!");
                try {
                    alert.setContentText(response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                alert.showAndWait();
            });
        }
    }

    @Override
    public void onFailure(Call call, Throwable t) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка соединения");
            alert.setHeaderText("Ошибка!");
            alert.setContentText("Неизвестная ошибка сервера");

            alert.showAndWait();
        });
    }
}
