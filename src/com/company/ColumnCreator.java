package com.company;


import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class ColumnCreator {

    public static TableColumn createColumn(TableColumn tableColumn, String propertyValueIdentifier){
        tableColumn.setMinWidth(200);
        tableColumn.setCellValueFactory(new PropertyValueFactory<>(propertyValueIdentifier));
        return tableColumn;
    }
}
