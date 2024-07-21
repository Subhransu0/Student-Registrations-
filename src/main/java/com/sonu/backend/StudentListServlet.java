
package com.sonu.backend;



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/StudentListS")
public class StudentListServlet extends HttpServlet {
	private static final String query = "SELECT ID , NAME , CONTACT , UNIVERSITY , ADDRESS FROM student";

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbpractice", "root",
				"Subh123@"); PreparedStatement ps = connection.prepareStatement(query);) {
			ResultSet rs = ps.executeQuery();
			out.println("<table border='1' align = 'center' border='2' style='border-collapse: collapse;' >");
			out.println("<tr>");
			out.println("<th>ID</th>");
			out.println("<th>NAME</th>");
			out.println("<th>CONTACT</th>");
			out.println("<th>UNIVERSITY</th>");
			out.println("<th>ADDRESS</th>");			
			out.println("<th>EDIT</th>");
			out.println("<th>DELETE</th>");
			out.println("</tr>");
			while (rs.next()) {
				out.println("<tr>");
				out.println("<td>"+rs.getInt(1)+"</td>");
				out.println("<td>"+rs.getString(2)+"</td>");
				out.println("<td>"+rs.getString(3)+"</td>");
				out.println("<td>"+rs.getString(4)+"</td>");
				out.println("<td>"+rs.getString(5)+"</td>");
				out.println("<td><a href='editUrl?id="+rs.getInt(1)+"'>Edit</a></td>");
				out.println("<td><a href='deleteList?id="+rs.getInt(1)+"'>Delete</td>");
				out.println("</tr>");
							
			}
			out.println("</table>");
			

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
