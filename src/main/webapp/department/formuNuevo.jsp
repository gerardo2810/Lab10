<%@page import="java.util.ArrayList" %>
<%@page import="com.example.webapphr1_2023.Beans.Location" %>
<%@page import="com.example.webapphr1_2023.Beans.Countries" %>
<jsp:useBean id="listaCountries" type="ArrayList<Countries>" scope="request" />
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="../includes/bootstrap_header.jsp"/>
    <title>Nueva Locación</title>
</head>
<body>
<div class='container'>
    <div class="row justify-content-center">
        <form method="POST" action="LocationServlet?action=guardar" class="col-md-6 col-lg-6">
            <h1 class='mb-3'>Nueva Locación</h1>
            <hr>
            <div class="mb-3">
                <label for="street_address">Dirección</label>
                <input type="text" class="form-control form-control-sm" name="street_address" id="street_address">
            </div>
            <div class="mb-3">
                <label for="postal_code">Código Postal</label>
                <input type="text" class="form-control form-control-sm" name="postal_code" id="postal_code">
            </div>
            <div class="mb-3">
                <label for="city">Ciudad</label>
                <input type="text" class="form-control form-control-sm" name="city" id="city">
            </div>
            <div class="mb-3">
                <label for="state_province">Provincia/Estado</label>
                <input type="text" class="form-control form-control-sm" name="state_province" id="state_province">
            </div>
            <div class="mb-3">
                <label for="country_id">País</label>
                <select name="country_id" class="form-select" id="country_id">
                    <% for(Countries country: listaCountries){ %>
                    <option value="<%=country.getCountry_id()%>"> <%=country.getCountry_name()%> </option>
                    <% } %>
                </select>
            </div>
            <a href="<%= request.getContextPath()%>/LocationServlet" class="btn btn-danger">Cancelar</a>
            <input type="submit" value="Guardar" class="btn btn-primary"/>
        </form>
    </div>
</div>
<jsp:include page="../includes/bootstrap_footer.jsp"/>
</body>
</html>

