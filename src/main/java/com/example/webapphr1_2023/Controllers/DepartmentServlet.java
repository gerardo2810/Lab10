package com.example.webapphr1_2023.Controllers;

import com.example.webapphr1_2023.Beans.Department;
import com.example.webapphr1_2023.Beans.Employee;
import com.example.webapphr1_2023.Beans.Location;
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
                        request.setAttribute("department", demp);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

        DepartmentDao departmentDao = new DepartmentDao();

        Department department = new Department();
        department.setDepartmentName(request.getParameter("department_name"));

        // Puedes agregar más configuraciones según los campos del formulario

        switch (action) {
            case "guardar":
                departmentDao.guardarDepartment(department);
                response.sendRedirect("DepartmentServlet?action=lista"); // redirige a la lista después de guardar
                break;
            case "actualizar":
                department.setDepartmentId(Integer.parseInt(request.getParameter("department_id"))); // Asegúrate de tener un campo oculto en tu formulario con el ID

                departmentDao.actualizarDepartment(department);
                response.sendRedirect("DepartmentServlet?action=lista"); // redirige a la lista después de actualizar
                break;
            // Puedes agregar más casos según las acciones específicas que necesites manejar
        }
    }
}
