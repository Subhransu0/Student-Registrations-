package com.sonu.backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Myregister")
public class RegisterJDBCServlet extends HttpServlet {
	private static final String query = "INSERT INTO student (NAME  , CONTACT , UNIVERSITY , ADDRESS) VALUES (?,?,?,?)";

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		String name1 = req.getParameter("name1");
		String contact1 = req.getParameter("Contact1");
		String University1 = req.getParameter("University1");
		String address1 = req.getParameter("Address1");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbpractice", "root",
				"Subh123@"); PreparedStatement ps = connection.prepareStatement(query);) {

			ps.setString(1, name1);
			ps.setString(2, contact1);
			ps.setString(3, University1);
			ps.setString(4, address1);
			int i = ps.executeUpdate();
			if (i == 1) {

				out.println("<h2 style='color:green'>Register Sucessfully</h2>");

			} else {

				out.println("<h2 style='color:red'>Register not Sucessfull</h2>");

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		out.println("<a href='StudentListS' text-align='Center'><button>STUDENT LIST</button></a>");
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
