<%@ page import="com.example.webapphr1_2023.Beans.Department" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<jsp:useBean id="listaDepartamentos" type="ArrayList<Department>" scope="request" />
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="../includes/bootstrap_header.jsp"/>
    <title>Lista de departamentos</title>
</head>
<body>
<div class='container'>
    <h1 class='mb-3'>Lista de departamentos</h1>
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="<%= request.getContextPath()%>">Home</a></li>
            <li class="breadcrumb-item active">Departamentos</li>
        </ol>
    </nav>
    <a href="<%= request.getContextPath()%>/DepartmentServlet?action=agregar" class="btn btn-primary mb-4">
        Agregar nuevo departamento</a>
    <table class="table">
        <thead>
        <tr>
            <th>#</th>
            <th>Nombre del Departamento</th>
            <th>Gerente</th>
            <th>Ubicación</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <%
            int i = 1;
            for (Department department : listaDepartamentos) {
        %>
        <tr>
            <td><%= i %></td>
            <td><%= department.getDepartmentId() %></td>
            <td><%= department.getDepartmentName() %></td>
            <td><%= department.getManagerId() == null ? "-- Sin gerente --" : department.getManagerId().getFullName() %></td>
            <td><%= department.getLocationId().getCity() %></td>
            <td>
                <a class="btn btn-primary"
                   href="<%=request.getContextPath()%>/DepartmentServlet?action=editar&id=<%=department.getDepartmentId()%>">
                    <i class="bi bi-pencil-square"></i>
                </a>
            </td>
            <td>
                <a class="btn btn-danger"
                   href="<%=request.getContextPath()%>/DepartmentServlet?action=borrar&id=<%=department.getDepartmentId()%>">
                    <i class="bi bi-trash3"></i>
                </a>
            </td>
        </tr>
        <%
                i++;
            }
        %>
        </tbody>
    </table>
</div>
<jsp:include page="../includes/bootstrap_footer.jsp"/>
</body>
</html>


