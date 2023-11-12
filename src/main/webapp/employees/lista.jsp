<%@page import="com.example.webapphr1_2023.Beans.Location" %>
<%@page import="java.util.ArrayList" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<jsp:useBean id="locationList" type="ArrayList<Location>" scope="request"/>

<!DOCTYPE html>
<html>
<head>
    <jsp:include page="../includes/bootstrap_header.jsp"/>
    <title>Lista de Locaciones</title>
</head>
<body>
<div class='container'>
    <h1 class='mb-3'>Lista de Locaciones</h1>
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="<%= request.getContextPath()%>">Home</a></li>
            <li class="breadcrumb-item active">Locaciones</li>
        </ol>
    </nav>
    <a href="<%= request.getContextPath()%>/LocationServlet?action=agregar" class="btn btn-primary mb-4">
        Agregar nueva locación</a>
    <table class="table">
        <thead>
        <tr>
            <th>#</th>
            <th>Dirección</th>
            <th>Código Postal</th>
            <th>Ciudad</th>
            <th>Provincia/Estado</th>
            <th>País</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <%
            int i = 1;
            for (Location loc : locationList) {
        %>
        <tr>
            <td><%= i%></td>
            <td><%= loc.getStreet_address()%></td>
            <td><%= loc.getPostal_code()%></td>
            <td><%= loc.getCity()%></td>
            <td><%= loc.getState_province()%></td>
            <td><%= loc.getCountry().getCountry_id()%></td>
            <td>
                <a class="btn btn-primary"
                   href="<%=request.getContextPath()%>/LocationServlet?action=editar&id=<%=loc.getLocationId()%>">
                    <i class="bi bi-pencil-square"></i>
                </a>
            </td>
            <td>
                <a class="btn btn-danger"
                   href="<%=request.getContextPath()%>/LocationServlet?action=borrar&id=<%=loc.getLocationId()%>">
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
