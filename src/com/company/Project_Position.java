package com.company;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by stefan on 17-Dec-16.
 */
public class Project_Position {
    int project_id;
    String position_Name;
    static ObservableList<Project_Position> projectPositions = FXCollections.observableArrayList();
    static Connection connection = Main.connection;

    public Project_Position(int project_id, String position_Name) {
        this.project_id = project_id;
        this.position_Name = position_Name;
    }

    public static ObservableList<Project_Position> getProjectPositions() {
        ResultSet resultSet;
        Statement statement;
        String sql;

        try{
            sql = "SELECT * from Project_Position";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                String position_Name = resultSet.getString(1);
                int project_id = resultSet.getInt(2);
                projectPositions.add(new Project_Position(project_id , position_Name));
            }
        }

        catch (Exception e){
            System.out.println(e);
        }
        return projectPositions;
    }

    public static void addButtonClicked(int project_id, String position_Name){
        String sql;
        PreparedStatement statement;

        try{
            sql = "INSERT INTO PROJECT_POSITION"
                    + "(position_name, project_id)"
                    + "VALUES(?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, position_Name);
            statement.setInt(2, project_id);
            statement.execute();
            projectPositions.add(new Project_Position(project_id, position_Name));
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public static void deleteButtonClicked(TableView tableView){
        String sql;
        PreparedStatement statement;
        ObservableList<Project_Position> productSelected, allProducts;
        allProducts = tableView.getItems();
        try {
            productSelected = tableView.getSelectionModel().getSelectedItems();
            Project_Position project_position = productSelected.get(0);
            int project_id = project_position.getProjectId();
            String positionName = project_position.getPosition_Name();
            productSelected.forEach(allProducts::remove);
            sql = "DELETE FROM PROJECT_POSITION"
                    + " WHERE project_id = (?)"
                    + " AND position_name = (?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, project_id);
            statement.setString(2, positionName);
            statement.execute();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public int getProjectId() {
        return project_id;
    }

    public String getPosition_Name() {
        return position_Name;
    }

}
