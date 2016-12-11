package com.company;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by stefan on 11-Dec-16.
 */
public class Project {
    int project_id;
    String project_hq;
    int budget;
    int allocated_hours;
    static ObservableList<Project> projects = FXCollections.observableArrayList();
    static Connection connection = Main.connection;

    public Project(int project_id, String project_hq, int budget, int allocated_hours) {
        this.project_id = project_id;
        this.project_hq = project_hq;
        this.budget = budget;
        this.allocated_hours = allocated_hours;
    }

    public static ObservableList<Project> getProjects() {
        ResultSet resultSet;
        Statement statement;
        String sql;

        try{
            sql = "SELECT * from PROJECT";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                int project_id = resultSet.getInt(1);
                String project_hq = resultSet.getString(2);
                int budget = resultSet.getInt(3);
                int allocated_hours = resultSet.getInt(4);
                projects.add(new Project(project_id, project_hq, budget, allocated_hours));
            }
        }

        catch (Exception e){
            System.out.println(e);
        }
        return projects;
    }

    public static void addButtonClicked(int project_id, String project_hq, int budget, int allocated_hours){
        String sql;
        PreparedStatement statement;

        try{
            sql = "INSERT INTO PROJECT"
                    + "(project_id, project_headquarter, budget, allocated_hours)"
                    + "VALUES(?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, project_id);
            statement.setString(2, project_hq);
            statement.setInt(3, budget);
            statement.setInt(4, allocated_hours);
            statement.execute();
            projects.add(new Project(project_id, project_hq, budget, allocated_hours));
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public static void deleteButtonClicked(TableView tableView){
        String sql;
        PreparedStatement statement;
        ObservableList<Project> productSelected, allProducts;
        allProducts = tableView.getItems();
        try {
            productSelected = tableView.getSelectionModel().getSelectedItems();
            Project project = productSelected.get(0);
            int project_id = project.getProject_id();
            productSelected.forEach(allProducts::remove);
            sql = "DELETE FROM PROJECT"
                    + " WHERE project_id = (?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, project_id);
            statement.execute();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public static void updateButtonClicked(int oldProject_id, String project_hq, int budget, int allocated_hours) {
        String sql;
        PreparedStatement statement;
        int i;
        try {
            sql = "UPDATE PROJECT"
                    + " SET project_headquarter = (?)"
                    + ", budget = (?)"
                    + ", allocated_hours = (?)"
                    + " WHERE project_id = (?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, project_hq);
            statement.setInt(2, budget);
            statement.setInt(3, allocated_hours);
            statement.setInt(4, oldProject_id);
            statement.execute();
            int size = projects.size();
            for(i = 0; i < size; i++){
                projects.remove(projects.size()-1);
            }
            getProjects();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public int getProject_id() {
        return project_id;
    }

    public String getProject_hq() {
        return project_hq;
    }

    public int getBudget() {
        return budget;
    }

}
