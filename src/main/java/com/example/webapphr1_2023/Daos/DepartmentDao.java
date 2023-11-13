package com.example.webapphr1_2023.Daos;


import com.example.webapphr1_2023.Beans.Department;
import com.example.webapphr1_2023.Beans.Employee;
import com.example.webapphr1_2023.Beans.Location;
import com.example.webapphr1_2023.Beans.Countries;
import java.sql.*;
import java.util.ArrayList;

public class DepartmentDao extends DaoBase {

    public ArrayList<Department> lista() {

        ArrayList<Department> list = new ArrayList<>();
        String sql = "SELECT \n" +
                "    d.department_id,\n" +
                "    d.department_name,\n" +
                "    m.first_name,\n" +
                "    m.last_name,\n" +
                "    l.city,\n" +
                "    l.street_address\n" +
                "FROM departments d\n" +
                "LEFT JOIN employees m ON d.manager_id = m.employee_id\n" +
                "LEFT JOIN locations l ON d.location_id = l.location_id";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Department department = new Department();
                department.setDepartmentId(rs.getInt(1));
                department.setDepartmentName(rs.getString(2));
                Employee manager = new Employee();
                manager.setFirstName(rs.getString(3));
                manager.setLastName(rs.getString(4));
                department.setManager(manager);
                Location location = new Location();
                location.setCity(rs.getString(5));
                location.setStreet_address(rs.getString(6));
                department.setLocation(location);

                list.add(department);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return list;
    }
    public Department obtenerDepartment(int departmentid) {

        Department department = null;

        String sql = "SELECT \n" +
                "    d.department_id,\n" +
                "    d.department_name,\n" +
                "    m.first_name,\n" +
                "    m.last_name,\n" +
                "    l.city,\n" +
                "    l.street_address\n" +
                "FROM departments d\n" +
                "LEFT JOIN employees m ON d.manager_id = m.employee_id\n" +
                "LEFT JOIN locations l ON d.location_id = l.location_id\n" +
                "where d.department_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, departmentid);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    department = fetchDepartmentData(rs);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return department;
    }
    /*public void crearDepartment(Department department) {

        String sql = "INSERT INTO jobs (department_id,department_name,manager_id,location_id) VALUES (?,?,?,?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, department.getDepartmentId());
            pstmt.setString(2, department.getDepartmentName());
            pstmt.setInt(3, department.getManagerId().getEmployeeId());
            pstmt.setInt(4, department.getLocationId().getLocationId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
    public void guardarDepartment(Department department) {

        String sql = "INSERT INTO departments (department_id, department_name, manager_id, location_id) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            setDepartmentData(department, pstmt);

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void actualizarDepartment(Department department) {
        String sql = "UPDATE departments "
                + "SET department_name = ?, "
                + "manager_id = ?, "
                + "location_id = ? "
                + "WHERE department_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            setDepartmentData(department, pstmt);
            pstmt.setInt(4, department.getDepartmentId());

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }



    public void borrarDepartment(int department_id) {

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM departments WHERE department_id = ?")) {

            pstmt.setInt(1, department_id);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    private Department fetchDepartmentData(ResultSet rs) throws SQLException {
        Department department = new Department();
        department.setDepartmentId(rs.getInt(1));
        department.setDepartmentName(rs.getString(2));

        int managerId = rs.getInt("manager_id");
        if (managerId != 0) {
            Employee manager = new Employee();
            manager.setFirstName(rs.getString("first_name"));
            manager.setLastName(rs.getString("last_name"));
            department.setManager(manager);
        }

        int locationId = rs.getInt(3);
        if (locationId != 0) {
            Location location = new Location();
            location.setCity(rs.getString("city"));
            location.setState_province(rs.getString("street_address"));
            department.setLocation(location);
        }

        return department;
    }



    private void setDepartmentData(Department department, PreparedStatement pstmt) throws SQLException {
        pstmt.setString(1, department.getDepartmentName());

        Employee manager = department.getManager();
        Location location = department.getLocation();

        pstmt.setInt(2, manager != null ? manager.getEmployeeId() : 0);
        pstmt.setInt(3, location != null ? location.getLocationId() : 0);
    }


}