<%@ page import="java.util.List" %>
<%@ page import="com.example.webapphr1_2023.Beans.Department" %>
<%@ page import="com.example.webapphr1_2023.Beans.Employee" %>
<%@ page import="com.example.webapphr1_2023.Beans.Location" %>
<%@ page import="com.example.webapphr1_2023.Controllers.DepartmentServlet" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lista de Departamentos</title>
    <jsp:include page="../includes/bootstrap_header.jsp"/>
</head>
<body>
<div class='container'>
    <h1>Lista de Departamentos</h1>
    <table class='table table-bordered table-striped'>
        <thead>
        <tr>
            <th>#</th>
            <th>Nombre del Departamento</th>
            <th>Jefe de Departamento</th>
            <th>Ubicación</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <%
            int i = 1;
            for (Department department : (List<Department>) request.getAttribute("listaDepartamentos")) {
        %>
        <tr>
            <td><%= i %></td>
            <td><%= department.getDepartmentName() %></td>
            <td>
                <%
                    Employee manager = department.getManagerId();
                    if (manager != null) {
                %>
                <%= manager.getFullName() %>
                <%
                } else {
                %>
                -- Sin Jefe --
                <% } %>
            </td>
            <td>
                <%
                    Location location = department.getLocationId();
                    if (location != null) {
                %>
                <%= location.getStreet_address() %>
                <%
                } else {
                %>
                -- Sin Ubicación --
                <% } %>
            </td>
            <td>
                <a class="btn btn-primary" href="<%= request.getContextPath()%>/DepartmentServlet?action=editar&id=<%= department.getDepartmentId() %>">Editar</a>
                <a class="btn btn-danger" href="<%= request.getContextPath()%>/DepartmentServlet?action=borrar&id=<%= department.getDepartmentId() %>">Borrar</a>
            </td>
        </tr>
        <% i++; %>
        <% } %>
        </tbody>
    </table>
    <a class="btn btn-success" href="<%= request.getContextPath()%>/DepartmentServlet?action=agregar">Nuevo Departamento</a>
</div>
<jsp:include page="../includes/bootstrap_footer.jsp"/>
</body>
</html>
