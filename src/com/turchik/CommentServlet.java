package com.turchik;

import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.sql.*;

import static com.turchik.UtilDatabase.*;

@WebServlet(name = "CommentServlet", urlPatterns = "/Comment")
public class CommentServlet extends javax.servlet.http.HttpServlet {
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rSet;

    protected void doPost(javax.servlet.http.HttpServletRequest request,
                          javax.servlet.http.HttpServletResponse response) throws IOException {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            String absPath = getServletContext().getRealPath("/WEB-INF/lib/dbNiceMiceWebApp");
            conn = DriverManager.getConnection(DRIVER_NAME + absPath, USERNAME, PASSWORD);

            String hostType = request.getParameter("hosttype");
            String comment = request.getParameter("comment");
            if(hostType.equals("p")){
                stmt = conn.prepareStatement("INSERT INTO COMMENT(hosttypecode, content)" +
                        "VALUES(?, ?)");
                stmt.setString(1, hostType);
                stmt.setString(2, comment);
                stmt.executeUpdate();
                response.sendRedirect("/NiceMiceWebApp/profile.jsp");
            }else if(hostType.equals("d")){
                stmt = conn.prepareStatement("INSERT INTO COMMENT(hosttypecode, imageid, content)" +
                                                "VALUES(?, ?, ?)");
                stmt.setString(1, hostType);
                stmt.setString(2, request.getParameter("imageId"));
                stmt.setString(3, comment);
                stmt.executeUpdate();
                response.sendRedirect("/NiceMiceWebApp/detail.jsp");
            }else{throw new Exception("Wow you messed, bud.");}
        } catch (Exception e) {
            response.getWriter().print(e.getMessage());
        } finally {
            UtilDatabase.closeAll(conn, stmt, rSet);
        }
    }
}
