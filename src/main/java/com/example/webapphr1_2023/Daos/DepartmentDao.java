package com.example.webapphr1_2023.Daos;


import com.example.webapphr1_2023.Beans.Department;
import com.example.webapphr1_2023.Beans.Employee;
import com.example.webapphr1_2023.Beans.Location;

import java.sql.*;
import java.util.ArrayList;

public class DepartmentDao extends DaoBase {

    public ArrayList<Department> lista() {

        ArrayList<Department> list = new ArrayList<>();
        String sql = "select * from departments d\n" +
                "inner join locations l on d.location_id = l.location_id\n" +
                "left join employees m on d.manager_id = m.employee_id\n" ;
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Department department = new Department();
                department.setDepartmentId(rs.getInt(1));
                department.setDepartmentName(rs.getString(2));
                list.add(department);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return list;
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

       String sql = "UPDATE employees "
               + "SET department_name = ?, "
               + "manager_id = ?, "
               + "location_id = ?, "
               + "WHERE department_id = ?";

       try (Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

           pstmt.setInt(11, department.getDepartmentId());

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
    private void setDepartmentData(Department department, PreparedStatement pstmt) throws SQLException {
        pstmt.setString(1, department.getDepartmentName());

        // Validar que el ID del gerente sea un número entero mayor que 0
        if (department.getManagerId() != null && department.getManagerId().getEmployeeId() <= 0) {
            throw new SQLException("El ID del gerente debe ser un número entero mayor que 0");
        }

        // Validar que el ID de la ubicación sea un número entero mayor que 0
        if (department.getLocationId() != null && department.getLocationId().getLocationId() <= 0) {
            throw new SQLException("El ID de la ubicación debe ser un número entero mayor que 0");
        }

        pstmt.setInt(2, department.getManagerId() != null ? department.getManagerId().getEmployeeId() : null);
        pstmt.setInt(3, department.getLocationId() != null ? department.getLocationId().getLocationId() : null);
    }

}