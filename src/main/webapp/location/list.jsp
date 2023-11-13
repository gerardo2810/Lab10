<%@ page import="com.example.webapphr1_2023.Beans.Location" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean type="java.util.ArrayList<com.example.webapphr1_2023.Beans.Location>" scope="request" id="locationList"/>

<!DOCTYPE html>
<html>
<head>
    <jsp:include page="../includes/bootstrap_header.jsp"/>
    <title>Lista de Locations en hr</title>
</head>
<body>
<div class='container'>

    <h1 class='mb-3'>Lista de Locations en hr</h1>
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="<%= request.getContextPath()%>">Home</a></li>
            <li class="breadcrumb-item active">Locations</li>
        </ol>
    </nav>
    <a class="btn btn-primary mb-3" href="<%=request.getContextPath()%>/LocationServlet?action=agregar">Agregar nueva Ubicación</a>
    <table class="table">
            <tr>
                <th>Loc ID</th>
                <th>Street Address</th>
                <th>Postal Code</th>
                <th>City</th>
                <th>State Province</th>
                <th>Country id</th>
                <th></th>
                <th></th>
            </tr>
        <%
            for (Location loc : locationList) {
        %>
        <tr>
            <td><%=loc.getLocationId()%>
            </td>
            <td><%=loc.getStreet_address() == null ? "Without Street Address" : loc.getStreet_address()%>
            </td>
            <td><%= loc.getPostal_code() == null ? "Without Postal Code" : loc.getPostal_code()%>
            </td>
            <td><%=loc.getCity()%>
            </td>
            <td><%= loc.getState_province() == null ? "Without State province" : loc.getState_province()%>
            </td>
            <td><%= loc.getCountry().getCountry_id()%>
            </td>
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
            }
        %>
    </table>
</div>
<jsp:include page="../includes/bootstrap_footer.jsp"/>
</body>
</html>


