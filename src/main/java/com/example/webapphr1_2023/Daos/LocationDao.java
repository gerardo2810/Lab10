package com.example.webapphr1_2023.Daos;

import com.example.webapphr1_2023.Beans.Countries;
import com.example.webapphr1_2023.Beans.Department;
import com.example.webapphr1_2023.Beans.Location;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LocationDao extends DaoBase{
    public ArrayList<Location> listaLocation() {

        String sql = "SELECT \n" +
                "    l.location_id as `Loc ID`,\n" +
                "    l.street_address as `Street Address`,\n" +
                "    l.postal_code as `Postal Code`,\n" +
                "    l.city as `City`,\n" +
                "    l.state_province as `State Province`,\n" +
                "    c.country_name as `Country id`\n" +
                "FROM hr.locations AS l\n" +
                "INNER JOIN hr.countries c ON (l.country_id = c.country_id)";

        ArrayList<Location> list = new ArrayList<>();
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Location location = new Location();
                location.setLocationId(rs.getInt(1));
                location.setStreet_address(rs.getString(2));
                location.setPostal_code(rs.getString(3));
                location.setCity(rs.getString(4));
                location.setState_province(rs.getString(5));

                Countries country = new Countries();
                country.setCountry_id(rs.getString("l.country_id"));
                location.setCountry(country);
                list.add(location);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return list;
    }

    //CRUD
    public void guardarLocation(Location location){

    }

    public {

    }
}
