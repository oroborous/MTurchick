package com.turchik;

import nicemice.utility.utilDatabase;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static nicemice.utility.utilDatabase.*;

@WebServlet(name = "SearchServlet",
            urlPatterns = "/search")
public class SearchServlet extends HttpServlet {
    private Connection conn;
    private PreparedStatement pStmt;
    private ResultSet rSet;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            String absPath = getServletContext().getRealPath("/WEB-INF/lib/dbNiceMiceWebApp");

            conn = DriverManager.getConnection(DRIVER_NAME + absPath, USERNAME, PASSWORD);
            pStmt = conn.prepareStatement("SELECT * FROM IMAGE WHERE TITLE = ?");
            String searchTerm = request.getParameter("searchTerm");
            pStmt.setString(1, searchTerm);

            rSet = pStmt.executeQuery();

            StringBuilder html = new StringBuilder("<html><body><ul><li>\tTable: Column\tSearchTerm: " + searchTerm + "</li>");

            while(rSet.next()){
                int imageId = rSet.getInt(1);
                String filepath = rSet.getString(2);
                String title = rSet.getString(3);
                String description = rSet.getString(4);
                boolean isFave = rSet.getBoolean(5);
                html.append("<li>Image Id: ").append(imageId).append("</li>");
                html.append("<li>File Path: ").append(filepath).append("</li>");
                html.append("<li>Image Title: ").append(title).append("</li>");
                html.append("<li>Description: ").append(description).append("</li>");
                html.append("<li>Is Favorite: ").append(isFave).append("</li>");
            }

            html.append("</ul></body></html>");

            response.getWriter().print(html.toString());
        } catch(Exception e){
            response.getWriter().print(e.getMessage());
        } finally{
            utilDatabase.closeAll(conn, pStmt, rSet);
        }
    }
}
