package com.company;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import static com.company.Main.connection;

/**
 * Created by Jamal on 8-12-2016.
 */
public class Employee_Degree {
    int degreeID;
    int BSN;
    static ObservableList<Employee_Degree> employee_degrees = FXCollections.observableArrayList();

    public Employee_Degree(int degreeID, int BSN){
        this.degreeID = degreeID;
        this.BSN = BSN;

    }
    public static ObservableList<Employee_Degree> getEmployee_degrees() {
        Statement statement;
        String sql;
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            sql = "SELECT * FROM EMPLOYEE_DEGREE";
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                int BSN = resultSet.getInt(1);
                int degreeID = resultSet.getInt(2);
                employee_degrees.add(new Employee_Degree(degreeID, BSN));
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return employee_degrees;
    }

    public static void addButtonClicked(int BSN, int degreeID){
        String sql;
        PreparedStatement statement;
        try{
            sql = "INSERT INTO EMPLOYEE_DEGREE"
                    + "(degree_id, BSN)"
                    + "VALUES(?,?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, degreeID);
            statement.setInt(2, BSN);
            statement.execute();
            employee_degrees.add(new Employee_Degree(degreeID, BSN));
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void deleteButtonClicked(TableView tableView){
        String sql;
        PreparedStatement statement;
        ObservableList<Employee_Degree> productSelected, allProducts;
        allProducts = tableView.getItems();
        try {
            productSelected = tableView.getSelectionModel().getSelectedItems();
            Employee_Degree employee_degree = productSelected.get(0);
            int BSN = employee_degree.getBSN();
            int degree_id = employee_degree.getDegreeID();
            productSelected.forEach(allProducts::remove);
            sql = "DELETE FROM Employee_Degree"
                    + " WHERE bsn = (?)"
                    + " AND degree_id = (?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, BSN);
            statement.setInt(2, degree_id);
            statement.execute();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public static void updateButtonClicked(int newdegreeID, int oldBSN, int olddegreeID){
        String sql;
        PreparedStatement statement;
        int i;
        try {
            sql = "UPDATE Employee_Degree"
                    + " SET degree_id = (?)"
                    + " WHERE bsn = (?)" +
                    " AND degree_id = (?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, newdegreeID);
            statement.setInt(2, oldBSN);
            statement.setInt(3, olddegreeID);
            statement.execute();
            int size = employee_degrees.size();
            for(i = 0; i < size; i++){
                employee_degrees.remove(employee_degrees.size()-1);
        }
            getEmployee_degrees();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public int getDegreeID() {
        return degreeID;
    }

    public int getBSN() {
        return BSN;
    }
}
