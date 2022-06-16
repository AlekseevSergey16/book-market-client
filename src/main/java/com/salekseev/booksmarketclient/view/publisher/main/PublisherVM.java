package com.salekseev.booksmarketclient.view.publisher.main;

import com.salekseev.booksmarketclient.model.Publisher;
import com.salekseev.booksmarketclient.service.retrofit.BookMarketService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.function.Function;

public class PublisherVM {

    private final BookMarketService service = BookMarketService.getInstance();
    private final ObservableList<Publisher> publisherObservableList = FXCollections.observableArrayList(new Publisher());
    Function<Throwable, Void> onError;

    public void refreshPublishers() {
        service.getAllPublishers()
                .thenAccept(publishers -> Platform.runLater(() -> publisherObservableList.setAll(publishers)));
    }

    public void addPublisher(Publisher publisher) {
        service.createPublisher(publisher)
                .thenAccept(publisherId -> Platform.runLater(() -> {
                    publisher.setId(publisherId);
                    publisherObservableList.add(publisher);
                }));
    }

    public void updatePublisher(Publisher publisher) {
        service.updatePublisher(publisher)
                .thenAccept(response -> Platform.runLater(() -> {
                    for (int i = 0; i < publisherObservableList.size(); i++) {
                        if (publisherObservableList.get(i).getId().equals(publisher.getId())) {
                            publisherObservableList.add(i, publisher);
                            publisherObservableList.remove(i + 1);
                            break;
                        }
                    }
                }));
    }

    public void deletePublisher(long id) {
        service.deletePublisher(id)
                .thenAccept(response -> Platform.runLater(() ->
                        publisherObservableList.removeIf(publisher -> publisher.getId().equals(id))));
    }

    public ObservableList<Publisher> getPublisherObservableList() {
        return publisherObservableList;
    }
}
