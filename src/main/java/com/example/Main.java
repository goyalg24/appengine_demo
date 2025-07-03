package com.example;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        Server server = new Server(Integer.parseInt(System.getenv().getOrDefault("PORT", "8080")));
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        context.addServlet(HelloServlet.class, "/hello");
        server.start();
        server.join();
    }

    @WebServlet("/hello")
    public static class HelloServlet extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            String name = req.getParameter("name");
            if (name == null) name = "World";
            resp.setContentType("application/json");
            resp.getWriter().write("{\"message\": \"Hello, " + name + "!\"}");
        }
    }
}