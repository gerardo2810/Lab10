package com.example.webapphr1_2023.Daos;


import com.example.webapphr1_2023.Beans.Department;
import com.example.webapphr1_2023.Beans.Employee;
import com.example.webapphr1_2023.Beans.Location;

import java.sql.*;
import java.util.ArrayList;

public class DepartmentDao extends DaoBase {

    public ArrayList<Department> lista() {

        ArrayList<Department> list = new ArrayList<>();
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM departments")) {

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
   /* public void crearDepartment(int department_id, String department_name, Employee manager_id, Location location_id) {

        String sql = "INSERT INTO jobs (department_id,department_name,manager_id,location_id) VALUES (?,?,?,?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, jobId);
            pstmt.setString(2, jobTitle);
            pstmt.setInt(3, minSalary);
            pstmt.setInt(4, maxSalary);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
   /*public void actualizarDepartment(Department department) {

       String sql = "UPDATE employees "
               + "SET department_name = ?, "
               + "manager_id = ?, "
               + "location_id = ?, "
               + "WHERE department_id = ?";

       try (Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

           setEmployeeData(department, pstmt);
           pstmt.setInt(11, department.getEmployeeId());

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
    }*/
   /*private void setDepartmentData(Department department, PreparedStatement pstmt) throws SQLException {
       pstmt.setString(1, department.getDepartmentName());
       pstmt.setInt(3, department.getManagerId().getEmployeeId());
       pstmt.setString(4, department.getLocationId().g);

       pstmt.setString(6, department.getE().getJobId());
       pstmt.setBigDecimal(7, employee.getSalary());
       if (employee.getCommissionPct() == null) {
           pstmt.setNull(8, Types.DECIMAL);
       } else {
           pstmt.setBigDecimal(8, employee.getCommissionPct());
       }
       if (employee.getManager().getEmployeeId() == 0) {
           pstmt.setNull(9, Types.INTEGER);
       } else {
           pstmt.setInt(9, employee.getManager().getEmployeeId());
       }

       if (employee.getDepartment().getDepartmentId() == 0) {
           pstmt.setNull(10, Types.INTEGER);
       } else {
           pstmt.setInt(10, employee.getDepartment().getDepartmentId());
       }
   }*/
}