<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.webapphr1_2023.Beans.Employee" %>
<%@ page import="com.example.webapphr1_2023.Beans.Location" %>
<%@ page import="com.example.webapphr1_2023.Beans.Department" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%
    ArrayList<Employee> listaJefes = (ArrayList<Employee>) request.getAttribute("listaJefes");
    Department department = (Department) request.getAttribute("department");
    ArrayList<Location> listaUbicaciones = (ArrayList<Location>) request.getAttribute("listaUbicaciones");
%>

<!DOCTYPE html>
<html>
<head>
    <jsp:include page="../includes/bootstrap_header.jsp"/>
    <title>Nuevo departamento</title>
</head>
<body>
<div class='container'>
    <div class="row justify-content-center">
        <form method="POST" action="DepartmentServlet?action=guardar" class="col-md-6 col-lg-6">
            <h1 class='mb-3'>Nuevo departamento</h1>
            <hr>
            <div class="mb-3">
                <label for="department_name">Nombre del departamento</label>
                <input type="text" class="form-control form-control-sm" name="department_name" id="department_name">
            </div>
            <div class="mb-3">
                <label for="manager_id">Jefe de departamento</label>
                <select name="manager_id" class="form-select" id="manager_id">
                    <option value="0">-- Sin jefe --</option>
                    <% for (Employee manager : listaJefes) { %>
                    <option value="<%= manager.getEmployeeId() %>"><%= manager.getFullName() %></option>
                    <% } %>
                </select>
            </div>
            <div class="mb-3">
                <label for="location_id">Ubicación</label>
                <select name="location_id" class="form-select" id="location_id">
                    <option value="0">-- Sin ubicación --</option>
                    <% for (Location location : listaUbicaciones) { %>
                    <option value="<%= location.getLocationId() %>"><%= location.getCity() %></option>
                    <% } %>
                </select>
            </div>
            <a href="<%= request.getContextPath() %>/DepartmentServlet" class="btn btn-danger">Cancelar</a>
            <input type="submit" value="Guardar" class="btn btn-primary"/>
        </form>
    </div>
</div>
<jsp:include page="../includes/bootstrap_footer.jsp"/>
</body>
</html>
