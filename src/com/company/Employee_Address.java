package com.company;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class Employee_Address{
    private int BSN;
    private String country;
    private String postal_Code;
    static Connection connection = Main.connection;
    static ObservableList<Employee_Address> employeeAddress = FXCollections.observableArrayList();

    public Employee_Address(int BSN, String country, String postal_Code){
        this.BSN = BSN;
        this.country = country;
        this.postal_Code = postal_Code;
    }

    public int getBSN() {
        return BSN;
    }

    public String getCountry() {
        return country;
    }

    public String getPostal_Code() {
        return postal_Code;
    }

    public static Connection getConnection() {
        return connection;
    }


    public static ObservableList<Employee_Address> getEmployeesAddress() {

        ResultSet resultSet;
        Statement statement;
        String sql;

        try{
            sql = "SELECT * from EMPLOYEE_ADDRESS";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                int BSN = resultSet.getInt(2);
                String Country = resultSet.getString(1);
                String Postal_Code = resultSet.getString(3);
                employeeAddress.add(new Employee_Address(BSN, Country, Postal_Code));
            }
        }

        catch (Exception e){
            System.out.println(e);
        }
        return employeeAddress;
    }


    public static void addButtonClicked(int BSN, String country, String postal_Code) {
        String sql;
        PreparedStatement statement;

        try{
        sql = "INSERT INTO EMPLOYEE_ADDRESS"
                + "(country, bsn, postal_code)"
                + " VALUES (?, ?, ?)";
        statement = connection.prepareStatement(sql);
        statement.setString(1, country);
        statement.setInt(2, BSN);
        statement.setString(3, postal_Code);
        statement.execute();
        employeeAddress.add(new Employee_Address(BSN, country, postal_Code));
    }
        catch (Exception e){
        System.out.println(e);
        }
    }


    public static void deleteButtonClicked(TableView<Employee_Address> tableView) {
        String sql;
        PreparedStatement statement;
        ObservableList<Employee_Address> productSelected, allProducts;
        allProducts = tableView.getItems();
        try {
            productSelected = tableView.getSelectionModel().getSelectedItems();
            Employee_Address employeeAddress = (Employee_Address) productSelected.get(0);
            String county = employeeAddress.getCountry();
            String postal_Code = employeeAddress.getPostal_Code();
            productSelected.forEach(allProducts::remove);
            sql = "DELETE FROM Employee_Address"
                    + " WHERE country = (?)"
                    + "AND postal_code = (?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, county);
            statement.setString(2, postal_Code);
            statement.execute();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public static void updateButtonClicked(int bsn, String country, String postal_Code) {
        String sql;
        PreparedStatement statement;
        int i;
        try {
            sql = "UPDATE Employee_Address"
                    + " SET country = (?)"
                    + ", postal_code = (?)"
                    + " WHERE bsn = (?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, country);
            statement.setString(2, postal_Code);
            statement.setInt(3, bsn);
            statement.execute();
            int size = employeeAddress.size();
            for(i = 0; i < size; i++){
                employeeAddress.remove(employeeAddress.size()-1);
            }
            getEmployeesAddress();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}

