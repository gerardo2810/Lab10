package com.example.webapphr1_2023.Controllers;

import com.example.webapphr1_2023.Beans.Employee;
import com.example.webapphr1_2023.Daos.CountryDao;
import com.example.webapphr1_2023.Daos.LocationDao;
import com.example.webapphr1_2023.Beans.Location;
import com.example.webapphr1_2023.Beans.Countries;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "LocationServlet", urlPatterns = {"/LocationServlet"})
public class LocationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action") == null ? "lista" : req.getParameter("action");

        RequestDispatcher view;
        LocationDao locationDao = new LocationDao();
        CountryDao countryDao = new CountryDao();
        //req.setAttribute("locationList", new ArrayList<>());
        //view = req.getRequestDispatcher("location/list.jsp");
        //view.forward(req, resp);

        switch (action) {
            case "lista":
                req.setAttribute("locationList", locationDao.listaLocation());
                view = req.getRequestDispatcher("location/list.jsp");
                view.forward(req, resp);
                break;
            case "agregar":
                req.setAttribute("listaCountries",countryDao.lista());
                view = req.getRequestDispatcher("location/FormularioNuevo.jsp");
                view.forward(req, resp);
                break;

            case "editar":
                if (req.getParameter("id") != null) {
                    String locationIdString = req.getParameter("id");
                    int locationId = 0;
                    try {
                        locationId = Integer.parseInt(locationIdString);
                    } catch (NumberFormatException ex) {
                        resp.sendRedirect("LocationServlet");
                        return;
                    }

                    Location l = locationDao.obtenerLocation(locationId);
                    if (l != null) {
                        req.setAttribute("location", l); // Configura el objeto location en el ámbito del request
                        req.setAttribute("listaLocations", locationDao.listaLocation());
                        req.setAttribute("listaCountries", countryDao.lista());
                        view = req.getRequestDispatcher("location/FormularioEditar.jsp");
                        view.forward(req, resp);
                    } else {
                        resp.sendRedirect("LocationServlet");
                    }

                } else {
                    resp.sendRedirect("LocationServlet");
                }
                break;

            case "borrar":
                if (req.getParameter("id") != null) {
                    String locationeIdString = req.getParameter("id");
                    int locationId = 0;
                    try {
                        locationId = Integer.parseInt(locationeIdString);
                    } catch (NumberFormatException ex) {
                        resp.sendRedirect("LocationServlet");
                    }

                    Location l = locationDao.obtenerLocation(locationId);

                    if (l != null) {
                        locationDao.borrarLocation(locationId);
                    }
                }

                resp.sendRedirect("LocationServlet");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

        LocationDao locationDao = new LocationDao();

        Location location = new Location();
        location.setStreet_address(request.getParameter("street_address"));
        location.setPostal_code(request.getParameter("postal_code"));
        location.setCity(request.getParameter("city"));
        location.setState_province(request.getParameter("state_province"));


        Countries countries = new Countries();
        countries.setCountry_id(request.getParameter("country_id"));
        location.setCountry(countries);


        switch (action) {
            case "guardar":
                locationDao.guardarLocation(location);

                response.sendRedirect("EmployeeServlet");
                break;
            case "actualizar":
                location.setLocationId(Integer.parseInt(request.getParameter("location_id"))); //no olvidar que para actualizar se debe enviar el ID

                locationDao.actualizarLocation(location);

                response.sendRedirect("LocationServlet");

                break;
        }
    }
}