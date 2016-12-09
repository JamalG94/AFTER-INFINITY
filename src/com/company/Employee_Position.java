package com.company;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import static com.company.Main.connection;

/**
 * Created by Jamal on 9-12-2016.
 */
public class Employee_Position {
    int hours;
    int BSN;
    String position_Name;
    static ObservableList<Employee_Position> employeePositions = FXCollections.observableArrayList();
    static Connection connection = Main.connection;

    public Employee_Position(int bsn, int hours, String position_Name) {
        this.hours = hours;
        this.BSN = bsn;
        this.position_Name = position_Name;
    }

    public static ObservableList<Employee_Position> getEmployeePositions() {
        ResultSet resultSet;
        Statement statement;
        String sql;

        try{
            sql = "SELECT * from EMPLOYEE_POSITION";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                String position_Name = resultSet.getString(1);
                int BSN = resultSet.getInt(2);
                int hours = resultSet.getInt(3);
                employeePositions.add(new Employee_Position(BSN, hours , position_Name));
            }
        }

        catch (Exception e){
            System.out.println(e);
        }
        return employeePositions;
    }

    public static void addButtonClicked(int BSN, int hours, String position_Name){
        String sql;
        PreparedStatement statement;

        try{
            sql = "INSERT INTO EMPLOYEE_POSITION"
                    + "(position_name, bsn, hours)"
                    + "VALUES(?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, position_Name);
            statement.setInt(2, BSN);
            statement.setInt(3, hours);
            statement.execute();
            employeePositions.add(new Employee_Position(BSN, hours, position_Name));
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public static void deleteButtonClicked(TableView tableView){
        String sql;
        PreparedStatement statement;
        ObservableList<Employee_Position> productSelected, allProducts;
        allProducts = tableView.getItems();
        try {
            productSelected = tableView.getSelectionModel().getSelectedItems();
            Employee_Position employee_degree = productSelected.get(0);
            int BSN = employee_degree.getBSN();
            String positionName = employee_degree.getPosition_Name();
            productSelected.forEach(allProducts::remove);
            sql = "DELETE FROM Employee_Position"
                    + " WHERE bsn = (?)"
                    + " AND position_name = (?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, BSN);
            statement.setString(2, positionName);
            statement.execute();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public static void updateButtonClicked(int hours, String position_Name, int oldBSN, String oldPositionName) {
        String sql;
        PreparedStatement statement;
        int i;
        try {
            sql = "UPDATE Employee_POSITION"
                    + " SET hours = (?)"
                    + ", position_name = (?)"
                    + " WHERE bsn = (?)"
                    + " AND  position_name = (?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, hours);
            statement.setString(2, position_Name);
            statement.setInt(3, oldBSN);
            statement.setString(4, oldPositionName);
            statement.execute();
            int size = employeePositions.size();
            for(i = 0; i < size; i++){
                employeePositions.remove(employeePositions.size()-1);
            }
            getEmployeePositions();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public int getHours() {
        return hours;
    }

    public int getBSN() {
        return BSN;
    }

    public String getPosition_Name() {
        return position_Name;
    }


}
