package com.example.webapphr1_2023.Daos;

import com.example.webapphr1_2023.Beans.Countries;
import com.example.webapphr1_2023.Beans.Department;
import com.example.webapphr1_2023.Beans.Employee;
import com.example.webapphr1_2023.Beans.Location;

import java.sql.*;
import java.util.ArrayList;

public class LocationDao extends DaoBase{
    public ArrayList<Location> listaLocation() {
        ArrayList<Location> list = new ArrayList<>();
        String sql = "SELECT \n" +
                "    l.location_id as `Loc ID`,\n" +
                "    l.street_address as `Street Address`,\n" +
                "    l.postal_code as `Postal Code`,\n" +
                "    l.city as `City`,\n" +
                "    l.state_province as `State Province`,\n" +
                "    c.country_id as `Country id`\n" +
                "FROM locations AS l\n" +
                "INNER JOIN countries c ON (l.country_id = c.country_id)";


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
                country.setCountry_id(rs.getString(6));
                location.setCountry(country);
                list.add(location);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return list;
    }

    //Buscar Location

    //CRUD
    public void guardarLocation(Location location){

        String sql = "INSERT INTO hr.locations (street_address, postal_code, city, state_province, country_id) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            setLocationData(location, pstmt);

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void actualizarLocation(Location location) {

        String sql = "UPDATE hr.locations "
                + "SET street_address = ?, "
                + "postal_code = ?, "
                + "city  = ?, "
                + "state_province = ?, "
                + "country_id  = ?, "
                + "WHERE location_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            setLocationData(location, pstmt);
            pstmt.setInt(6, location.getLocationId());

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public Location obtenerLocation(int locationId){
        Location location = null;
        String sql = "SELECT \n" +
                "    l.location_id as `Loc ID`,\n" +
                "    l.street_address as `Street Address`,\n" +
                "    l.postal_code as `Postal Code`,\n" +
                "    l.city as `City`,\n" +
                "    l.state_province as `State Province`,\n" +
                "    c.country_id as `Country id`\n" +
                "FROM hr.locations AS l\n" +
                "INNER JOIN hr.countries c ON (l.country_id = c.country_id)\n" +
                "where l.location_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, locationId);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    location = fetchLocationData(rs);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


        return location;
    }

    public void borrarLocation(int locationID){
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM hr.locations WHERE location_id = ?")) {

            pstmt.setInt(1, locationID);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void setLocationData(Location location, PreparedStatement pstmt) throws SQLException {
        pstmt.setString(1, location.getStreet_address());
        pstmt.setString(2, location.getPostal_code());
        pstmt.setString(3, location.getCity());
        pstmt.setString(4, location.getState_province());
        pstmt.setString(5, location.getCountry().getCountry_id());

    }

    private Location fetchLocationData(ResultSet rs) throws SQLException {
        Location location = new Location();
        location.setLocationId(rs.getInt(1));
        location.setState_province(rs.getString(2));
        location.setPostal_code(rs.getString(3));
        location.setCity(rs.getString(4));
        location.setState_province(rs.getString(5));

        Countries country = new Countries();
        country.setCountry_id(rs.getString(6));
        location.setCountry(country);

        return location;
    }
}
