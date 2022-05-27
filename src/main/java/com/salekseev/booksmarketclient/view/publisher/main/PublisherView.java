package com.salekseev.booksmarketclient.view.publisher.main;

import com.salekseev.booksmarketclient.model.Publisher;
import com.salekseev.booksmarketclient.util.FxUtil;
import com.salekseev.booksmarketclient.view.publisher.info.PublisherInfoView;
import javafx.collections.MapChangeListener;
import javafx.event.ActionEvent;

import java.util.List;
import java.util.function.Consumer;

public class PublisherView extends PublisherViewDesigner {

    private final PublisherVM viewModel = new PublisherVM();

    private final Consumer<Publisher> addPublisherConsumer = this::addPublisher;
    private final Consumer<Publisher> editPublisherConsumer = this::editPublisher;

    public PublisherView() {
        viewModel.refreshPublishers();
        bindFields();
    }

    @Override
    protected void addPublisherButtonOnAction(ActionEvent event) {
        FxUtil.showView("Добавление Издательства", new PublisherInfoView(addPublisherConsumer), this.getScene().getWindow());
    }

    @Override
    protected void editPublisherButtonOnAction(ActionEvent event) {
        Publisher selectedPublisher = (Publisher) tableView.getSelectionModel().getSelectedValues().toArray()[0];
        FxUtil.showView("Изменение Издательства",
                new PublisherInfoView(editPublisherConsumer, selectedPublisher),
                this.getScene().getWindow());
    }

    @Override
    protected void deletePublisherButtonOnAction(ActionEvent event) {
        List<Publisher> selectedPublishers = tableView.getSelectionModel().getSelectedValues();
        deletePublishers(selectedPublishers);
    }

    private void bindFields() {
        this.tableView.setItems(viewModel.getPublisherObservableList());

        tableView.getSelectionModel().selectionProperty().addListener((MapChangeListener<Integer, Publisher>) change -> {
            if (change.getMap().size() == 1) {
                editPublisherButton.setDisable(false);
                deletePublisherButton.setDisable(false);
            } else if (change.getMap().size() > 1) {
                editPublisherButton.setDisable(true);
                deletePublisherButton.setDisable(false);
            } else if (change.getMap().isEmpty()) {
                editPublisherButton.setDisable(true);
                deletePublisherButton.setDisable(true);
            }
        });
    }

    private void addPublisher(Publisher publisher) {
        viewModel.addPublisher(publisher);
    }

    private void editPublisher(Publisher publisher) {
        viewModel.updatePublisher(publisher);
    }

    private void deletePublishers(List<Publisher> publishers) {
        publishers.stream()
                .map(Publisher::getId)
                .forEach(viewModel::deletePublisher);
    }

}
