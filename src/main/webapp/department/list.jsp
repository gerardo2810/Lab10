<%@ page import="java.util.List" %>
<%@ page import="com.example.webapphr1_2023.Beans.Department" %>
<%@ page import="com.example.webapphr1_2023.Beans.Employee" %>
<%@ page import="com.example.webapphr1_2023.Beans.Location" %>
<%@ page import="com.example.webapphr1_2023.Controllers.DepartmentServlet" %>

<jsp:useBean type="java.util.ArrayList<com.example.webapphr1_2023.Beans.Department>" scope="request" id="listaDepartamentos"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lista de Departamentos</title>
    <jsp:include page="../includes/bootstrap_header.jsp"/>
</head>
<body>
<div class='container'>
    <h1 class='mb-3'>Lista de Departamentos</h1>
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="<%= request.getContextPath()%>">Home</a></li>
            <li class="breadcrumb-item active">Departments</li>
        </ol>
    </nav>
    <a href="<%= request.getContextPath()%>/DepartmentServlet?action=agregar" class="btn btn-primary mb-4">
        Agregar nuevo Departamento</a>
    <table class="table">
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
            for (Department department : listaDepartamentos) {
        %>
        <tr>
            <td><%= i %></td>
            <td><%= department.getDepartmentName() %></td>
            <td>
                <%
                    Employee manager = department.getManager();
                    if (manager != null) {
                %>
                <%= manager.getFirstName() + " " + manager.getLastName() %>
                <%
                } else {
                %>
                -- Sin Jefe --
                <% } %>
            </td>
            <td>
                <%
                    Location location = department.getLocation();
                    if (location != null) {
                %>
                <%= location.getCity() +" - " +location.getStreet_address() %>
                <%
                } else {
                %>
                -- Sin Ubicación --
                <% } %>
            </td>
            <td>
                <a class="btn btn-primary" href="<%= request.getContextPath()%>/DepartmentServlet?action=editar&id=<%= department.getDepartmentId() %>"><i class="bi bi-pencil-square"></i></a>
                <a class="btn btn-danger" href="<%= request.getContextPath()%>/DepartmentServlet?action=borrar&id=<%= department.getDepartmentId() %>"><i class="bi bi-trash3"></i></a>
            </td>
        </tr>
        <% i++; %>
        <% } %>
        </tbody>
    </table>
</div>
<jsp:include page="../includes/bootstrap_footer.jsp"/>
</body>
</html>