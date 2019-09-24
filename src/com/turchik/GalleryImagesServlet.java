package com.turchik;

import nicemice.utility.utilDatabase;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static nicemice.utility.utilDatabase.*;

@WebServlet(name = "GalleryImagesServlet", urlPatterns = "/galleryImages")
public class GalleryImagesServlet extends HttpServlet {
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rSet;

    protected void doGet(javax.servlet.http.HttpServletRequest request,
                         javax.servlet.http.HttpServletResponse response) throws IOException {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            String absPath = getServletContext().getRealPath("/WEB-INF/lib/dbNiceMiceWebApp");
            conn = DriverManager.getConnection(DRIVER_NAME + absPath, USERNAME, PASSWORD);

            //Get images with the favorite flag true
            stmt = conn.prepareStatement("SELECT * FROM IMAGE");
            rSet = stmt.executeQuery();

            StringBuilder imageTable = new StringBuilder("<tbody><tr>");
            int counter = 1;
            while(rSet.next()){
                String fileName = rSet.getString(2);
                String title = rSet.getString(3);
                imageTable.append("<td><a href=\"detail.jsp;").append(title)
                        .append("\"><img alt=\"[Image of ").append(title)
                        .append(".]\" src=\"img/").append(fileName)
                        .append("\" /></a></td>");
                if(counter++ / 4.0 == Math.floor(counter / 4.0))
                    imageTable.append("</tr><tr>");
            }
            imageTable.append("</tr></tbody>");
            response.getWriter().print(imageTable.toString());
        } catch (Exception e) {
            response.getWriter().print(e.getMessage());
        } finally {
            utilDatabase.closeAll(conn, stmt, rSet);
        }
    }
}
