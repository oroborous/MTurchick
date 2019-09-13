package com.turchik;

import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.sql.*;

@WebServlet(name = "CommentServlet", urlPatterns = "/CommentServlet")
public class CommentServlet extends javax.servlet.http.HttpServlet {
    private Connection conn;
    private Statement stmt;
    private ResultSet rSet;

    protected void doPost(javax.servlet.http.HttpServletRequest request,
                          javax.servlet.http.HttpServletResponse response) throws IOException {
        // Load the driver
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            // Find the absolute path of the database folder
            String absPath = getServletContext().getRealPath("/WEB-INF/lib/dbNiceMiceWebApp");

            // Create a connection
            conn = DriverManager.getConnection("jdbc:derby:" + absPath, "mark","admin");

            // Create a statement to execute SQL
            stmt = conn.createStatement();
            StringBuilder comment = new StringBuilder("<html><body><ul>");

            rSet = stmt.executeQuery("SELECT * FROM comment");
            while(rSet.next()){
                String hostType = rSet.getString(1);
                int imgId = rSet.getInt(2);
                String content = rSet.getString(3);

                comment.append("<li>").append(content).append("</li>");
            }
            comment.append("</ul></body></html>");

            rSet.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.closeAll(conn, stmt, rSet);
        }
        var comment = request.getParameter("ed-comment");

        //post comment to db

        //response.sendRedirect("profile.html");
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request,
                         javax.servlet.http.HttpServletResponse response) throws IOException {
        String stuff = "THIS IS SOME AWESOME STUFF";
        response.getWriter().println(stuff);
    }
}
