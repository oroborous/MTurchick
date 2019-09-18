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

@WebServlet(name = "ProfileFavoritesServlet", urlPatterns = "/profileFavorites")
public class ProfileFavoritesServlet extends HttpServlet {
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
            stmt = conn.prepareStatement("SELECT * FROM IMAGE WHERE FAVORITE");
            rSet = stmt.executeQuery();

            StringBuilder imageTable = new StringBuilder("<tbody><tr>");
            int counter = 1;
            while(rSet.next()){
                String fileName = rSet.getString(2);
                //a href="detail.html"><img alt="pro-image" src="img/1.jpeg"></a>
                imageTable.append("<td>")
                        .append("<a>")//Link to detail page for associated image
                        .append("<img alt=\"pro-image\" src=\"img/")
                        .append(fileName)
                        .append("\" /></a></td>");
                if(counter / 3.0 == Math.floor(counter / 3.0))
                    imageTable.append("</tr><tr>");
            }
            imageTable.append("</tr></tbody>");
            response.getWriter().print(imageTable.toString());
        } catch (Exception e) {
            response.getWriter().print(e.getMessage());
        } finally {
            UtilDatabase.closeAll(conn, stmt, rSet);
        }
    }
}
