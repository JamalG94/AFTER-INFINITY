package com.company;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.sql.Connection;

public class Main extends Application{

    Scene scene1, scene2, scene3, scene4, scene5;
    VBox homeLayout, employeeLayout, projectLayout, layout4, addressLayout;
    TableView<Employee> employeeTable;
    TableView<Employee_Address> employee_AddressTable;
    TableView<Employee_Degree> employee_DegreeTable;
    TableView<Employee_Position> employee_PositionTable;
    TableView<Address> addressTable;
    public static Connection connection = Connector.connect();

    public static void main(String[] args) {
	    launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage window = primaryStage;

        Button button1 = new Button("Get access to employee data");
        Button button3 = new Button("Get access to address data");
        button3.setOnAction(event -> window.setScene(scene5));
        button1.setOnAction(event -> window.setScene(scene2));
        Button button2 = new Button("Back to main menu");
        button2.setOnAction(event -> window.setScene(scene1));
        Button button4 = new Button("Back to main menu");     // We copy the back to main menu button, since javafx can't use 1 button for multiple layouts
        button4.setOnAction(event -> window.setScene(scene1));
        Button button5 = new Button("Back to main menu");
        button5.setOnAction(event -> window.setScene(scene1));

        homeLayout = new VBox();
        Label label = new Label("Welcome to scene 1");
        homeLayout.getChildren().addAll(button1,button3);
        scene1 = new Scene(homeLayout, 300,300);

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

        // Employee_Degree
        TableColumn<Employee_Degree, String> degreeBSNColumn = new TableColumn<>("BSN");
        ColumnCreator.createColumn(degreeBSNColumn, "BSN");

        TableColumn<Employee_Degree, String> degreeIDColumn = new TableColumn<>("Degree ID");
        ColumnCreator.createColumn(degreeIDColumn, "degreeID");

        // Employee_Position
        TableColumn<Employee_Position, String> positionBSNColumn = new TableColumn<>("BSN");
        ColumnCreator.createColumn(positionBSNColumn, "BSN");

        TableColumn<Employee_Position, String> positionNameColumn = new TableColumn<>("Position Name");
        ColumnCreator.createColumn(positionNameColumn, "position_Name");

        TableColumn<Employee_Position, String> positionhours = new TableColumn<>("Hours");
        ColumnCreator.createColumn(positionhours, "hours");

        // Address
        TableColumn<Address, String> addressCountryColumn = new TableColumn<>("Country");
        ColumnCreator.createColumn(addressCountryColumn, "Country");

        TableColumn<Address, String> addressCityColumn = new TableColumn<>("City");
        ColumnCreator.createColumn(addressCityColumn, "City");

        TableColumn<Address, String> addressStreetColumn = new TableColumn<>("Street");
        ColumnCreator.createColumn(addressStreetColumn, "Street");

        TableColumn<Address, String> addressPostalCode = new TableColumn<>("Postal Code");
        ColumnCreator.createColumn(addressPostalCode, "Postal Code");

        TableColumn<Address, Integer> addressStreetNumber = new TableColumn<>("Street Number");
        ColumnCreator.createColumn(addressStreetNumber, "Street Number");

        // Employee Textfields
        TextField bsnInput = new TextField();
        bsnInput.setPromptText("BSN");
        bsnInput.setMinWidth(100);

        TextField nameInput = new TextField();
        nameInput.setPromptText("Name");
        nameInput.setMinWidth(100);

        TextField surnameInput = new TextField();
        surnameInput.setPromptText("Surname");
        surnameInput.setMinWidth(100);

        TextField buildingNameInput = new TextField();
        buildingNameInput.setPromptText("Building Name");
        buildingNameInput.setMinWidth(100);

        // EmployeeAddress Textfields
        TextField bsnInputA = new TextField();
        bsnInputA.setPromptText("BSN");
        bsnInputA.setMinWidth(100);

        TextField countryInput = new TextField();
        countryInput.setPromptText("Country");
        countryInput.setMinWidth(100);

        TextField postalInput = new TextField();
        postalInput.setPromptText("Postal_Code");
        postalInput.setMinWidth(100);

        TextField newpostalInput = new TextField();
        newpostalInput.setPromptText("New Postal_Code");
        newpostalInput.setMinWidth(100);


        //Degree Textfields
        TextField bsnInputD = new TextField();
        bsnInputD.setPromptText("BSN");
        bsnInputD.setMinWidth(100);

        TextField degreeIDInput = new TextField();
        degreeIDInput.setPromptText("Degree ID");
        degreeIDInput.setMinWidth(100);

        TextField degreeIDInputnew = new TextField();
        degreeIDInputnew.setPromptText("New Degree");
        degreeIDInputnew.setMinWidth(100);

        //Position Textfields
        TextField bsnInputP = new TextField();
        bsnInputP.setPromptText("BSN");
        bsnInputP.setMinWidth(100);

        TextField positionInput = new TextField();
        positionInput.setPromptText("Position Name");
        positionInput.setMinWidth(100);

        TextField hoursInput = new TextField();
        hoursInput.setPromptText("Hours");
        hoursInput.setMinWidth(100);

        TextField newPositionInput = new TextField();
        newPositionInput.setPromptText("New Position");
        newPositionInput.setMinWidth(100);

        // Address Text Fields
        TextField countryInputB = new TextField();
        countryInputB.setPromptText("Country");
        countryInputB.setMinWidth(100);

        TextField cityInput = new TextField();
        cityInput.setPromptText("City");
        cityInput.setMinWidth(100);

        TextField streetInput = new TextField();
        streetInput.setPromptText("Street");
        streetInput.setMinWidth(100);

        TextField streetNumberInput = new TextField();
        streetNumberInput.setPromptText("Street Number");
        streetNumberInput.setMinWidth(100);

        TextField postalCodeInput = new TextField();
        postalCodeInput.setPromptText("Postal Code");
        postalCodeInput.setMinWidth(100);

        // Employee Buttons
        Button addButton = new Button("Add Employee");
        addButton.setOnAction(event ->  Employee.addButtonClicked(Integer.parseInt(bsnInput.getText()), nameInput.getText(), surnameInput.getText(), buildingNameInput.getText()));

        Button deleteButton = new Button("Delete Employee");
        deleteButton.setOnAction(event -> Employee.deleteButtonClicked(employeeTable));

        Button updateButton = new Button("Update Employee");
        updateButton.setOnAction(event -> Employee.updateButtonClicked(Integer.parseInt(bsnInput.getText()),nameInput.getText(), surnameInput.getText(), buildingNameInput.getText()));
        // Address Buttons
        Button addAddressButton = new Button("Add Address");
        addAddressButton.setOnAction(event -> Employee_Address.addButtonClicked(Integer.parseInt(bsnInputA.getText()), countryInput.getText(), postalInput.getText()));

        Button deleteAddressButton = new Button("Delete Address");
        deleteAddressButton.setOnAction(event -> Employee_Address.deleteButtonClicked(employee_AddressTable));

        Button updateAddressButton = new Button("Update Address");
        updateAddressButton.setOnAction(event -> Employee_Address.updateButtonClicked(Integer.parseInt(bsnInputA.getText()), countryInput.getText(), postalInput.getText(), newpostalInput.getText()));

        // Degree Buttons
        Button addDegreeButton = new Button("Add degree");
        addDegreeButton.setOnAction(event -> Employee_Degree.addButtonClicked(Integer.parseInt(bsnInputD.getText()), Integer.parseInt(degreeIDInput.getText())));

        Button deleteDegreeButton = new Button("Delete degree");
        deleteDegreeButton.setOnAction(event -> Employee_Degree.deleteButtonClicked(employee_DegreeTable));

        Button updateDegreeButton = new Button("Update degree");
        updateDegreeButton.setOnAction(event -> Employee_Degree.updateButtonClicked(Integer.parseInt(degreeIDInputnew.getText()), Integer.parseInt(bsnInputD.getText()), Integer.parseInt(degreeIDInput.getText())));

        // Position Buttons
        Button addPositionButton = new Button("Add position");
        addPositionButton.setOnAction(event -> Employee_Position.addButtonClicked(Integer.parseInt(bsnInputP.getText()), Integer.parseInt(hoursInput.getText()), positionInput.getText()));

        Button deletePositionButton = new Button("Delete position");
        deletePositionButton.setOnAction(event -> Employee_Position.deleteButtonClicked(employee_PositionTable));

        Button updatePositionButton = new Button("Update position");
        updatePositionButton.setOnAction(event -> Employee_Position.updateButtonClicked(Integer.parseInt(hoursInput.getText()),newPositionInput.getText(),
                Integer.parseInt(bsnInputP.getText()), positionInput.getText()));

        // Address Buttons
        Button addAddressButtonA = new Button("Add address");
        addAddressButtonA.setOnAction(event -> Address.addButtonClicked(countryInputB.getText(), cityInput.getText(), streetInput.getText(), Integer.parseInt(streetNumberInput.getText()), postalCodeInput.getText()));

        Button deleteAddressButtonA = new Button("Delete address");
        deleteAddressButtonA.setOnAction(event -> Address.deleteButtonClicked(addressTable));

        HBox employeeHbox = new HBox();
        employeeHbox.setPadding(new Insets(10));
        employeeHbox.setSpacing(10);
        employeeHbox.getChildren().addAll(bsnInput, nameInput, surnameInput, buildingNameInput, addButton, deleteButton, updateButton);

        HBox addressHBox = new HBox();
        addressHBox.getChildren().addAll(bsnInputA, countryInput, postalInput, newpostalInput, addAddressButton, deleteAddressButton, updateAddressButton);

        HBox degreeHBox = new HBox();
        degreeHBox.getChildren().addAll(bsnInputD, degreeIDInput, degreeIDInputnew, addDegreeButton, deleteDegreeButton, updateDegreeButton);

        HBox positionHbox = new HBox();
        positionHbox.getChildren().addAll(bsnInputP, hoursInput, positionInput, newPositionInput, addPositionButton, deletePositionButton, updatePositionButton);

        HBox addressHBoxA = new HBox();
        addressHBoxA.getChildren().addAll(countryInputB, cityInput, streetInput, streetNumberInput, postalCodeInput, addAddressButtonA, deleteAddressButtonA);

        employeeTable = new TableView<>();
        employeeTable.setItems(Employee.getEmployees());
        employeeTable.getColumns().addAll(bsnColumn, nameColumn, surNameColumn, buildingNameColumn);

        employee_AddressTable = new TableView<>();
        employee_AddressTable.setItems(Employee_Address.getEmployeesAddress());
        employee_AddressTable.getColumns().addAll(addressBSNColumn, countryColumn, postalCodeColumn);

        employee_DegreeTable = new TableView<>();
        employee_DegreeTable.setItems(Employee_Degree.getEmployee_degrees());
        employee_DegreeTable.getColumns().addAll(degreeBSNColumn, degreeIDColumn);

        employee_PositionTable = new TableView<>();
        employee_PositionTable.setItems(Employee_Position.getEmployeePositions());
        employee_PositionTable.getColumns().addAll(positionBSNColumn, positionhours, positionNameColumn);

        addressTable = new TableView<>();
        addressTable.setItems(Address.getAddresses());
        addressTable.getColumns().addAll(addressCountryColumn, addressCityColumn, addressStreetColumn, addressStreetNumber , addressPostalCode);

        employeeLayout = new VBox();
        employeeLayout.getChildren().addAll(button2, employeeTable, employeeHbox, employee_AddressTable, addressHBox, employee_DegreeTable, degreeHBox, employee_PositionTable, positionHbox);
        scene2 = new Scene(employeeLayout, 500, 500);

        projectLayout = new VBox();
        projectLayout.getChildren().addAll(button4);
        scene3 = new Scene(projectLayout, 500, 500);

        addressLayout = new VBox();
        addressLayout.getChildren().addAll(button2, addressTable, addressHBoxA);
        scene5 = new Scene(addressLayout, 500, 500);

        window.setScene(scene1);

        window.show();
    }
}
