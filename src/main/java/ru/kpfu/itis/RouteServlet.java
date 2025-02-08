package ru.kpfu.itis;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/route")
public class RouteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("route_search.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pointA = req.getParameter("pointA");
        String pointB = req.getParameter("pointB");
        String transportType = req.getParameter("transportType");

        System.out.println("pointA: " + pointA);
        System.out.println("pointB: " + pointB);
        System.out.println("transportType: " + transportType);

        String jsonResponse = DgisApiService.getRoute(pointA, pointB, transportType);

        resp.setContentType("application/json");
        resp.getWriter().write(jsonResponse);
    }
}
