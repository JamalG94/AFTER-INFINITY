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
public class Address {
    private String Country;
    private String City;
    private String Street;
    private String Postal_code;
    private int Street_number;
    static Connection connection = Main.connection;
    static ObservableList<Address> addresses = FXCollections.observableArrayList();

    public static ObservableList<Address> getAddresses(){

        ResultSet resultSet;
        Statement statement;
        String sql;

        try{
            sql = "SELECT * from address";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                String Country = resultSet.getString(1);
                String City = resultSet.getString(2);
                String Street = resultSet.getString(3);
                String Postal_code = resultSet.getString(4);
                int Street_number = resultSet.getInt(5);
                addresses.add(new Address(Country, City, Street, Street_number, Postal_code));
            }
        }

        catch (Exception e){
            System.out.println(e);
        }
        return addresses;
    }

    public static void addButtonClicked(String country, String city, String street, int street_number, String postal_code){
        String sql;
        PreparedStatement statement;

        try{
            sql = "INSERT INTO ADDRESS"
                    + "(country, city, street, street_number, postal_code)"
                    + " VALUES (?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, country);
            statement.setString(2, city);
            statement.setString(3, street);
            statement.setInt(4, street_number);
            statement.setString(5, postal_code);
            statement.execute();
            addresses.add(new Address(country, city, street, street_number, postal_code));
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public static void deleteButtonClicked(TableView<Address> tableView){
        String sql;
        PreparedStatement statement;
        ObservableList<Address> productSelected, allProducts;
        allProducts = tableView.getItems();
        try {
            productSelected = tableView.getSelectionModel().getSelectedItems();
            Address address = (Address) productSelected.get(0);
            String Country = address.getCountry();
            String Postal_code = address.getPostal_code();
            productSelected.forEach(allProducts::remove);
            sql = "DELETE FROM address"
                    + " WHERE country = (?)"
                    + " AND postal_code = (?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, Country);
            statement.setString(2, Postal_code);
            statement.execute();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public Address(String Country, String City, String Street, int Street_number, String Postal_code){
        this.Country = Country;
        this.City = City;
        this.Street = Street;
        this.Street_number = Street_number;
        this.Postal_code = Postal_code;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {Country = country;}

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        this.City = city;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public int getStreet_number() {
        return Street_number;
    }

    public void setStreet_number(int street_number) {this.Street_number = street_number;}

    public String getPostal_code(){ return Postal_code; }

    public void setPostal_code(String postal_code){this.Postal_code = postal_code;}
}
