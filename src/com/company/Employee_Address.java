package com.company;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class Employee_Address{
    private int BSN;
    private String country;
    private String postal_Code;
    static Connection connection = Main.connection;
    static ObservableList<Employee_Address> employeesAddress = FXCollections.observableArrayList();

    public Employee_Address(int BSN, String country, String postal_Code){
        this.BSN = BSN;
        this.country = country;
        this.postal_Code = postal_Code;
    }

    public int getBSN() {
        return BSN;
    }

    public void setBSN(int BSN) {
        this.BSN = BSN;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostal_Code() {
        return postal_Code;
    }

    public void setPostal_Code(String postal_Code) {
        this.postal_Code = postal_Code;
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void setConnection(Connection connection) {
        Employee_Address.connection = connection;
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
                employeesAddress.add(new Employee_Address(BSN, Country, Postal_Code));
            }
        }

        catch (Exception e){
            System.out.println(e);
        }
        return employeesAddress;
    }

    public static void setEmployees(ObservableList<Employee_Address> employees) {
        Employee_Address.employeesAddress = employees;
    }
}
