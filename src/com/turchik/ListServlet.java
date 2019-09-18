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

@WebServlet(name = "ListServlet", urlPatterns = "/list")
public class ListServlet extends HttpServlet {
    private Connection conn;
    private PreparedStatement pStmt;
    private ResultSet rSet;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        try{
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            String absPath = getServletContext().getRealPath("/WEB-INF/lib/dbNiceMiceWebApp");

            conn = DriverManager.getConnection(DRIVER_NAME + absPath, USERNAME, PASSWORD);
            pStmt = conn.prepareStatement("SELECT * FROM COMMENT");

            rSet = pStmt.executeQuery();

            StringBuilder html = new StringBuilder("<html><body><ul><li>\tTable: Comment</li><br/><hr/>");

            while(rSet.next()){
                int commentId = rSet.getInt(1);
                String page = rSet.getString(2).equals("p") ? "Profile" : "Image";
                int imageId = rSet.getInt(3);
                String content = rSet.getString(4);
                html.append("<li>Comment Id: ").append(commentId).append("</li>");
                html.append("<li>Host Page: ").append(page).append("</li>");
                if(page.equals("Image"))
                    html.append("<li>Image Id: ").append(imageId).append("</li>");
                html.append("<li>Content: ").append(content).append("</li><br/><hr/>");
            }

            html.append("</ul></body></html>");

            response.getWriter().print(html.toString());
        } catch(Exception e){
            response.getWriter().print(e.getMessage());
        } finally{
            UtilDatabase.closeAll(conn, pStmt, rSet);
        }
    }
}
