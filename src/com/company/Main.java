package com.company;

import javafx.application.Application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.sql.Connection;

public class Main extends Application{

    Scene scene1, scene2, scene3, scene4;
    VBox layout1, layout2, layout3, layout4;
    TableView<Employee> employeeTable;
    TableView<Employee_Address> employee_AddressTable;
    public static Connection connection = Connector.connect();

    public static void main(String[] args) {
	    launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage window = primaryStage;

        Button button1 = new Button("Get access to employee data");
        Button button3 = new Button("Get access to address data");
        button3.setOnAction(event -> window.setScene(scene3));
        button1.setOnAction(event -> window.setScene(scene2));
        Button button2 = new Button("Back to main menu");
        button2.setOnAction(event -> window.setScene(scene1));
        Button button4 = new Button("Back to main menu");     // We copy the back to main menu button, since javafx can't use 1 button for multiple layouts
        button4.setOnAction(event -> window.setScene(scene1));

        layout1 = new VBox();
        Label label = new Label("Welcome to scene 1");
        layout1.getChildren().addAll(button1,button3);
        scene1 = new Scene(layout1, 300,300);

        // Employee
        TableColumn<Employee, Integer> bsnColumn = new TableColumn<>("BSN");
        ColumnCreator.createColumn(bsnColumn, "BSN");

        TableColumn<Employee, String> nameColumn = new TableColumn<>("Name");
        ColumnCreator.createColumn(nameColumn, "Name");

        TableColumn<Employee, String> surNameColumn = new TableColumn<>("Surname");
        ColumnCreator.createColumn(surNameColumn, "Surname");

        TableColumn<Employee, String> buildingNameColumn = new TableColumn<>("Building Name");
        ColumnCreator.createColumn(buildingNameColumn, "building_Name");

        // Employee_Address
        TableColumn<Employee_Address, String> addressBSNColumn = new TableColumn<>("BSN");
        ColumnCreator.createColumn(addressBSNColumn, "BSN");

        TableColumn<Employee_Address, String> countryColumn = new TableColumn<>("Country");
        ColumnCreator.createColumn(countryColumn, "country");

        TableColumn<Employee_Address, String> postalCodeColumn = new TableColumn<>("Postal_Code");
        ColumnCreator.createColumn(postalCodeColumn, "postal_Code");

        TextField bsnInput = new TextField();
        bsnInput.setPromptText("BSN");
        bsnInput.setMinWidth(100);

        TextField nameInput = new TextField();
        nameInput.setPromptText("Name");
        nameInput.setMinWidth(100);

        TextField surnameInput = new TextField();
        surnameInput.setPromptText("Surname");
        surnameInput.setMinWidth(100);

        TextField bsnInputA = new TextField();
        bsnInputA.setPromptText("BSN");

        TextField buildingNameInput = new TextField();
        buildingNameInput.setPromptText("Building Name");
        buildingNameInput.setMinWidth(100);

        TextField countryInput = new TextField();
        countryInput.setPromptText("Country");
        countryInput.setMinWidth(100);

        TextField postalInput = new TextField();
        postalInput.setPromptText("Postal_Code");
        postalInput.setMinWidth(100);

        Button addButton = new Button("Add Employee");
        addButton.setOnAction(event ->  Employee.addButtonClicked(Integer.parseInt(bsnInput.getText()), nameInput.getText(), surnameInput.getText(), buildingNameInput.getText()));

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(event -> Employee.deleteButtonClicked(employeeTable));

        Button updateButton = new Button("Update");
        updateButton.setOnAction(event -> Employee.updateButtonClicked(Integer.parseInt(bsnInput.getText()),nameInput.getText(), surnameInput.getText(), buildingNameInput.getText()));

        Button addAddressButton = new Button("Add Address");
        addAddressButton.setOnAction(event -> Employee_Address.addButtonClicked(Integer.parseInt(bsnInputA.getText()), countryInput.getText(), postalInput.getText()));

        Button deleteAddressButton = new Button("Delete Address");
        deleteAddressButton.setOnAction(event -> Employee_Address.deleteButtonClicked(employee_AddressTable));

        Button updateAddressButton = new Button("Update Address");
        updateAddressButton.setOnAction(event -> Employee_Address.updateButtonClicked(Integer.parseInt(bsnInputA.getText()), countryInput.getText(), postalInput.getText()));

        HBox employeeHbox = new HBox();
        employeeHbox.setPadding(new Insets(10));
        employeeHbox.setSpacing(10);
        employeeHbox.getChildren().addAll(bsnInput, nameInput, surnameInput, buildingNameInput, addButton, deleteButton, updateButton);

        HBox addressHBox = new HBox();
        addressHBox.getChildren().addAll(bsnInputA, countryInput, postalInput, addAddressButton, deleteAddressButton, updateAddressButton);

        employeeTable = new TableView<>();
        employeeTable.setItems(Employee.getEmployees());
        employeeTable.getColumns().addAll(bsnColumn, nameColumn, surNameColumn, buildingNameColumn);

        employee_AddressTable = new TableView<>();
        employee_AddressTable.setItems(Employee_Address.getEmployeesAddress());
        employee_AddressTable.getColumns().addAll(addressBSNColumn, countryColumn, postalCodeColumn);
        layout2 = new VBox();
        layout2.getChildren().addAll(button2, employeeTable, employeeHbox, employee_AddressTable, addressHBox);
        scene2 = new Scene(layout2, 500, 500);

        layout3 = new VBox();
        layout3.getChildren().addAll(button4);
        scene3 = new Scene(layout3, 500, 500);

        window.setScene(scene1);

        window.show();
    }
}
