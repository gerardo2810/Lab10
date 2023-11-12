package com.example.webapphr1_2023.Controllers;

import com.example.webapphr1_2023.Beans.Department;
import com.example.webapphr1_2023.Daos.DepartmentDao;
import com.example.webapphr1_2023.Daos.EmployeeDao;
import com.example.webapphr1_2023.Daos.LocationDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DepartmentServlet", urlPatterns = {"/DepartmentServlet"})
public class DepartmentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

        RequestDispatcher view;
        EmployeeDao employeeDao = new EmployeeDao();
        LocationDao locationDao = new LocationDao();
        DepartmentDao departmentDao = new DepartmentDao();

        switch (action) {
            case "lista":
                request.setAttribute("listaDepartamentos", departmentDao.lista());
                view = request.getRequestDispatcher("department/list.jsp");
                view.forward(request, response);
                break;
            case "agregar":
                request.setAttribute("listaUbicaciones", locationDao.listaLocation());
                request.setAttribute("listaJefes", employeeDao.listarEmpleados());
                request.setAttribute("listaDepartamentos", departmentDao.lista());
                view = request.getRequestDispatcher("department/formuNuevo.jsp");
                view.forward(request, response);
                break;
            case "editar":
                if (request.getParameter("id") != null) {
                    String departmentIdString = request.getParameter("id");
                    int departmentid = 0;
                    try {
                        departmentid = Integer.parseInt(departmentIdString);
                    } catch (NumberFormatException ex) {
                        response.sendRedirect("DepartmentServlet?action=lista");
                        return;
                    }

                    Department demp = departmentDao.obtenerDepartment(departmentid);

                    if (demp != null) {
                        request.setAttribute("department", demp);
                        request.setAttribute("listaUbicaciones", locationDao.listaLocation());
                        request.setAttribute("listaJefes", employeeDao.listarEmpleados());
                        request.setAttribute("listaDepartamentos", departmentDao.lista());
                        view = request.getRequestDispatcher("department/formuEditar.jsp");
                        view.forward(request, response);
                    } else {
                        response.sendRedirect("DepartmentServlet");
                    }
                } else {
                    response.sendRedirect("DepartmentServlet");
                }
                break;
            case "borrar":
                if (request.getParameter("id") != null) {
                    String departmentIdString = request.getParameter("id");
                    int departmentid = 0;
                    try {
                        departmentid = Integer.parseInt(departmentIdString);
                    } catch (NumberFormatException ex) {
                        response.sendRedirect("DepartmentServlet?action=lista");
                        return;
                    }

                    Department demp = departmentDao.obtenerDepartment(departmentid);

                    if (demp != null) {
                        employeeDao.borrarEmpleado(departmentid);
                    }
                }

                response.sendRedirect("DepartmentServlet?action=lista");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

        DepartmentDao departmentDao = new DepartmentDao();

        Department department = new Department();
        department.setDepartmentName(request.getParameter("department_name"));

        switch (action) {
            case "guardar":
                departmentDao.guardarDepartment(department);
                response.sendRedirect("DepartmentServlet?action=lista");
                break;
            case "actualizar":
                department.setDepartmentId(Integer.parseInt(request.getParameter("department_id")));
                departmentDao.actualizarDepartment(department);
                response.sendRedirect("DepartmentServlet?action=lista");
                break;
        }
    }
}

