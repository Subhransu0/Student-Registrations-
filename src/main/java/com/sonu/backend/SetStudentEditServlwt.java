package com.sonu.backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/editScreen")
public class SetStudentEditServlwt extends HttpServlet {
    private static final String query = "UPDATE student SET NAME = ?, CONTACT = ?, UNIVERSITY = ?, ADDRESS = ? WHERE id=?";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        int id1 = Integer.parseInt(req.getParameter("id"));
        String name1 = req.getParameter("name1");
        String contact1 = req.getParameter("contact1");
        String University1 = req.getParameter("university1");
        String add1 = req.getParameter("address1");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbpractice", "root", "Subh123@");
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, name1);
            ps.setString(2, contact1);
            ps.setString(3, University1);
            ps.setString(4, add1);
            ps.setInt(5, id1);

            int count = ps.executeUpdate();
            if (count == 1) {
                out.println("<h2>Record is Edited Successfully</h2>");
            } else {
                out.println("<h2>Record is Not Edited</h2>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        out.println("<a href='StudentListS' text-align='center'><button>STUDENT LIST</button></a>");
        out.println("<a href='home.html'><button>HOME</button></a>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service(req, resp);
    }
}
