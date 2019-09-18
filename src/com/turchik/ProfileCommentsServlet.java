package com.turchik;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

import static com.turchik.UtilDatabase.*;

@WebServlet(name = "ProfileCommentsServlet", urlPatterns = "/profileComments")
public class ProfileCommentsServlet extends HttpServlet {
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rSet;

    protected void doGet(javax.servlet.http.HttpServletRequest request,
                         javax.servlet.http.HttpServletResponse response) throws IOException {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            String absPath = getServletContext().getRealPath("/WEB-INF/lib/dbNiceMiceWebApp");
            conn = DriverManager.getConnection(DRIVER_NAME + absPath, USERNAME, PASSWORD);

            stmt = conn.prepareStatement("SELECT * FROM COMMENT WHERE HOSTTYPECODE = 'p' ORDER BY COMMENTID DESC");
            rSet = stmt.executeQuery();

            StringBuilder comments = new StringBuilder();
            int counter = 1;
            while(rSet.next()){
                String content = rSet.getString(4);
                comments.append("<li>").append(counter++).append("] ").append(content).append("</li>");
            }
            response.getWriter().print(comments.toString());
        } catch (Exception e) {
            response.getWriter().print(e.getMessage());
        } finally {
            UtilDatabase.closeAll(conn, stmt, rSet);
        }
    }
}
