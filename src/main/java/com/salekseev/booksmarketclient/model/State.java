package com.salekseev.booksmarketclient.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Label;

public class State {

    private ObjectProperty<Label> selectedLabel = new SimpleObjectProperty<>();

    public Label getSelectedLabel() {
        return selectedLabel.get();
    }

    public ObjectProperty<Label> selectedLabelProperty() {
        return selectedLabel;
    }

    public void setSelectedLabel(Label selectedLabel) {
        this.selectedLabel.set(selectedLabel);
    }
}
