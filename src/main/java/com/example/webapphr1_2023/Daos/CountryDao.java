package com.example.webapphr1_2023.Daos;

import com.example.webapphr1_2023.Beans.Countries;
import com.example.webapphr1_2023.Beans.Department;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CountryDao extends DaoBase{

    public ArrayList<Countries> lista() {

        ArrayList<Countries> list = new ArrayList<>();
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM hr.countries")) {

            while (rs.next()) {
                Countries country = new Countries();
                country.setCountry_id(rs.getString(1));
                country.setCountry_name(rs.getString(2));
                list.add(country);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return list;
    }
}
