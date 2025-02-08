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

        // Валидация
        if (pointA == null || pointB == null || transportType == null) {
            resp.getWriter().write("{\"error\": \"Все поля обязательны для заполнения.\"}");
            return;
        }

        String regex = "^-?\\d+(\\.\\d+)?,-?\\d+(\\.\\d+)?$";
        if (!pointA.matches(regex) || !pointB.matches(regex)) {
            resp.getWriter().write("{\"error\": \"Некорректный формат координат.\"}");
            return;
        }

        try {
            String jsonResponse = DgisApiService.getRoute(pointA, pointB, transportType);
            resp.setContentType("application/json");
            resp.getWriter().write(jsonResponse);
        } catch (IOException e) {
            resp.getWriter().write("{\"error\": \"Ошибка при запросе к API: " + e.getMessage() + "\"}");
        }
    }
}
