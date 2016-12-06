package com.company;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class Employee  {
    private int BSN;
    private String Name;
    private String Surname;
    private String building_Name;
    static Connection connection = Main.connection;
    static ObservableList<Employee> employees = FXCollections.observableArrayList();

    public static ObservableList<Employee> getEmployees(){

        ResultSet resultSet;
        Statement statement;
        String sql;

        try{
            sql = "SELECT * from EMPLOYEE";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                int BSN = resultSet.getInt(1);
                String Name = resultSet.getString(2);
                String Surname = resultSet.getString(3);
                String Building_Name = resultSet.getString(4);
                employees.add(new Employee(BSN, Name, Surname, Building_Name));
            }
        }

        catch (Exception e){
            System.out.println(e);
        }
        return employees;
    }

    public static void addButtonClicked(int BSN, String name, String surname, String building_Name){
        String sql;
        PreparedStatement statement;

        try{
            sql = "INSERT INTO EMPLOYEE"
                    + "(bsn, employee_name, surname, building_name)"
                    + " VALUES (?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, BSN);
            statement.setString(2, name);
            statement.setString(3, surname);
            statement.setString(4, building_Name);
            statement.execute();
            employees.add(new Employee(BSN, name, surname, building_Name));
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public static void deleteButtonClicked(TableView<Employee> tableView){
        String sql;
        PreparedStatement statement;
        ObservableList<Employee> productSelected, allProducts;
        allProducts = tableView.getItems();
        try {
            productSelected = tableView.getSelectionModel().getSelectedItems();
            Employee employee = (Employee) productSelected.get(0);
            int BSN = employee.getBSN();
            productSelected.forEach(allProducts::remove);
            sql = "DELETE FROM Employee"
                    + " WHERE bsn = (?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, BSN);
            statement.execute();

        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public static void updateButtonClicked(int bsn, String name, String surname, String building_Name) {
        String sql;
        PreparedStatement statement;
        int i;
        try {
            sql = "UPDATE Employee"
                    + " SET employee_name = (?)"
                    + ", surname = (?)"
                    + ", building_name = (?)"
                    + " WHERE bsn = (?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setString(3, building_Name);
            statement.setInt(4, bsn);
            statement.execute();
            int size = employees.size();
            for(i = 0; i < size; i++){
                employees.remove(employees.size()-1);
            }
            getEmployees();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public Employee(int BSN, String Name, String Surname, String building_Name){
        this.BSN = BSN;
        this.Name = Name;
        this.Surname = Surname;
        this.building_Name = building_Name;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getBSN() {
        return BSN;
    }

    public void setBSN(int BSN) {
        this.BSN = BSN;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getBuilding_Name() {
        return building_Name;
    }

    public void setBuilding_Name(String building_Name) {
        this.building_Name = building_Name;
    }
}
