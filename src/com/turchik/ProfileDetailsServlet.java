package com.turchik;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static com.turchik.UtilDatabase.*;

@WebServlet(name = "ProfileDetailsServlet", urlPatterns = "/profileDetails")
public class ProfileDetailsServlet extends HttpServlet {
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rSet;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            String absPath = getServletContext().getRealPath("/WEB-INF/lib/dbNiceMiceWebApp");
            conn = DriverManager.getConnection(DRIVER_NAME + absPath, USERNAME, PASSWORD);

            String edName = request.getParameter("ed-name");
            String edFave = request.getParameter("ed-fave");
            String edMotd = request.getParameter("ed-motd");
            stmt = conn.prepareStatement("UPDATE PROFILE SET DETAIL = ? WHERE LABEL = ?");

            if(!edName.isBlank() && !edName.isEmpty()){
                stmt.setString(1, edName);
                stmt.setString(2, "name");
                stmt.executeUpdate();
            }
            if(!edFave.isBlank() && !edFave.isEmpty()){
                stmt.setString(1, edFave);
                stmt.setString(2, "fave");
                stmt.executeUpdate();
            }
            if(!edMotd.isBlank() && !edMotd.isEmpty()){
                stmt.setString(1, edMotd);
                stmt.setString(2, "motd");
                stmt.executeUpdate();
            }

            response.sendRedirect("/NiceMiceWebApp/profile.jsp");
        } catch (Exception e) {
            response.getWriter().print(e.getMessage());
        } finally {
            UtilDatabase.closeAll(conn, stmt, rSet);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            String absPath = getServletContext().getRealPath("/WEB-INF/lib/dbNiceMiceWebApp");
            conn = DriverManager.getConnection(DRIVER_NAME + absPath, USERNAME, PASSWORD);

            stmt = conn.prepareStatement("SELECT * FROM PROFILE ORDER BY PROFILEID");
            rSet = stmt.executeQuery();

            StringBuilder details = new StringBuilder();
            while(rSet.next()){
                String label = rSet.getString(2);
                String title = "";
                switch(label){
                    case "name":
                        title = "Name: ";
                        break;
                    case "fave":
                        title = "Favorite Animal: ";
                        break;
                    case "motd":
                        title = "Message of the Day: ";
                        break;
                }
                String detail = rSet.getString(3);
                details.append("<li><span id=\"").append(label).append("\">").append(title).append(detail).append("</span></li>");
            }
            response.getWriter().print(details.toString());
        } catch (Exception e) {
            response.getWriter().print(e.getMessage());
        } finally {
            UtilDatabase.closeAll(conn, stmt, rSet);
        }
    }
}
