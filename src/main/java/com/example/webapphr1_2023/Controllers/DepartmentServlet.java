package com.example.webapphr1_2023.Controllers;

import com.example.webapphr1_2023.Beans.Department;
import com.example.webapphr1_2023.Daos.DepartmentDao;
import com.example.webapphr1_2023.Daos.EmployeeDao;
import com.example.webapphr1_2023.Daos.JobDao;
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
        JobDao jobDao = new JobDao();
        DepartmentDao departmentDao = new DepartmentDao();

        switch (action) {
            case "lista":
                request.setAttribute("listaDepartments", departmentDao.lista());
                view = request.getRequestDispatcher("department/lista.jsp");
                view.forward(request, response);
                break;
            case "agregar":
                request.setAttribute("listaTrabajos",jobDao.obtenerListaTrabajos());
                request.setAttribute("listaJefes",employeeDao.listarEmpleados());
                request.setAttribute("listaDepartamentos",departmentDao.lista());
                view = request.getRequestDispatcher("employees/formularioNuevo.jsp");
                view.forward(request, response);
                break;
            case "editar":
                if (request.getParameter("id") != null) {
                    String departmentIdString = request.getParameter("id");
                    int departmentid = 0;
                    try {
                        departmentid = Integer.parseInt(departmentIdString);
                    } catch (NumberFormatException ex) {
                        response.sendRedirect("EmployeeServlet");

                    }

                    Department demp = departmentDao.obtenerDepartment(departmentid);

                    if (demp != null) {
                        request.setAttribute("empleado", demp);
                        request.setAttribute("listaTrabajos",jobDao.obtenerListaTrabajos());
                        request.setAttribute("listaJefes",employeeDao.listarEmpleados());
                        request.setAttribute("listaDepartamentos",departmentDao.lista());
                        view = request.getRequestDispatcher("employees/formularioEditar.jsp");
                        view.forward(request, response);
                    } else {
                        response.sendRedirect("EmployeeServlet");
                    }

                } else {
                    response.sendRedirect("EmployeeServlet");
                }

                break;
            case "borrar":
                if (request.getParameter("id") != null) {
                    String departmentIdString = request.getParameter("id");
                    int departmentid = 0;
                    try {
                        departmentid = Integer.parseInt(departmentIdString);
                    } catch (NumberFormatException ex) {
                        response.sendRedirect("DepartmentServlet");
                    }

                    Department demp = departmentDao.obtenerDepartment(departmentid);

                    if (demp != null) {
                        employeeDao.borrarEmpleado(departmentid);
                    }
                }

                response.sendRedirect("DepartmentServlet");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
