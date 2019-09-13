package com.turchik;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

import static com.turchik.DatabaseUtil.*;

@WebServlet(name = "SearchServlet",
            urlPatterns = "/search")
public class SearchServlet extends HttpServlet {
    private Connection conn;
    private PreparedStatement pStmt;
    private ResultSet rSet;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
                Boolean isFave = rSet.getBoolean(5);
                html.append("<li>Image Id: "+imageId +"</li>");
                html.append("<li>File Path: "+filepath +"</li>");
                html.append("<li>Image Title: "+title +"</li>");
                html.append("<li>Description: "+description +"</li>");
                html.append("<li>Is Favorite: "+isFave.toString() +"</li>");
            }

            html.append("</ul></body></html>");

            response.getWriter().print(html.toString());
        } catch(Exception e){
            response.getWriter().print(e.getMessage());
        } finally{
            DatabaseUtil.closeAll(conn, pStmt, rSet);
        }
    }
}
