package com.example.webapphr1_2023.Controllers;

import com.example.webapphr1_2023.Beans.Employee;
import com.example.webapphr1_2023.Daos.CountryDao;
import com.example.webapphr1_2023.Daos.LocationDao;
import com.example.webapphr1_2023.Beans.Location;
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
                req.setAttribute("listaLocations", locationDao.listaLocation());
                view = req.getRequestDispatcher("locations/lista.jsp");view.forward(req, resp);
                break;
            case "agregar":
                req.setAttribute("listaCountries",countryDao.lista());
                view = req.getRequestDispatcher("locations/formularioNuevoLocation.jsp");
                view.forward(req, resp);
                break;

            case "editar":
                if (req.getParameter("id") != null){
                    String locationeIdString = req.getParameter("id");
                    int locationId = 0;
                    try {
                        locationId = Integer.parseInt(locationeIdString);
                    } catch (NumberFormatException ex) {
                        resp.sendRedirect("LocationServlet");
                    }

                    Location l = locationDao.obtenerLocation(locationId);
                    if (l != null) {
                        req.setAttribute("listaLocations", locationDao.listaLocation());
                        view = req.getRequestDispatcher("locations/formularioEditarLocation.jsp");
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}