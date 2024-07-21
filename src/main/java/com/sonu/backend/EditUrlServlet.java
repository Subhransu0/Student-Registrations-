package com.sonu.backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/editUrl")
public class EditUrlServlet extends HttpServlet {
    private static final String query = "SELECT NAME, CONTACT, UNIVERSITY, ADDRESS FROM student WHERE id=?";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        int ID = Integer.parseInt(req.getParameter("id"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbpractice", "root", "Subh123@");
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                out.println("<form action='editScreen?id=" + ID + "' method='post'>");
                out.println("<table align='center' border='2' style='border-collapse: collapse;'>");
                out.println("<tr>");
                out.println("<td>NAME<td>");
                out.println("<td><input type='text' name='name1' value='" + rs.getString(1) + "'><td>");
                out.println("</tr>");
                
                out.println("<tr>");
                out.println("<td>CONTACT<td>");
                out.println("<td><input type='text' name='contact1' value='" + rs.getString(2) + "'><td>");
                out.println("</tr>");
                
                out.println("<tr>");
                out.println("<td>UNIVERSITY<td>");
                out.println("<td><input type='text' name='university1' value='" + rs.getString(3) + "'><td>");
                out.println("</tr>");
                
                out.println("<tr>");
                out.println("<td>ADDRESS<td>");
                out.println("<td><input type='text' name='address1' value='" + rs.getString(4) + "'><td>");
                out.println("</tr>");
                
                out.println("<tr>");
                out.println("<td><input type='submit' value='EDIT'></td>");
                out.println("<td><input type='reset' value='CANCEL'></td>");
                out.println("</tr>");
                
                out.println("</table>");
                out.println("</form>");
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
