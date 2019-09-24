package nicemice.profile;

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

@WebServlet(name = "ProfileController", urlPatterns = "/profile")
public class ProfileController extends HttpServlet {
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

            if (!edName.isBlank() && !edName.isEmpty()) {
                stmt.setString(1, edName);
                stmt.setString(2, "name");
                stmt.executeUpdate();
            }
            if (!edFave.isBlank() && !edFave.isEmpty()) {
                stmt.setString(1, edFave);
                stmt.setString(2, "fave");
                stmt.executeUpdate();
            }
            if (!edMotd.isBlank() && !edMotd.isEmpty()) {
                stmt.setString(1, edMotd);
                stmt.setString(2, "motd");
                stmt.executeUpdate();
            }

            response.sendRedirect("/NiceMiceWebApp/profile.jsp");
        } catch (Exception e) {
            response.getWriter().print(e.getMessage());
        } finally {
            utilDatabase.closeAll(conn, stmt, rSet);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            String absPath = getServletContext().getRealPath("/WEB-INF/lib/dbNiceMiceWebApp");
            conn = DriverManager.getConnection(DRIVER_NAME + absPath, USERNAME, PASSWORD);

            request.getSession().removeAttribute("ProfileDetails");
            request.getSession().removeAttribute("ImageTable");

            stmt = conn.prepareStatement("SELECT * FROM PROFILE ORDER BY PROFILEID");
            rSet = stmt.executeQuery();

            ProfileBean profile = new ProfileBean();

            while (rSet.next()) {
                String label = rSet.getString(2);
                switch (label) {
                    case "name":
                        profile.setName(rSet.getString(3));
                        break;
                    case "fave":
                        profile.setFavorite(rSet.getString(3));
                        break;
                    case "motd":
                        profile.setMotd(rSet.getString(3));
                        break;
                }
            }

            request.getSession().setAttribute("ProfileDetails", profile);
            stmt = conn.prepareStatement("SELECT * FROM IMAGE WHERE FAVORITE ORDER BY IMAGEID");
            rSet = stmt.executeQuery();

            StringBuilder imageTable = new StringBuilder("<tbody><tr>");
            int counter = 1;
            while (rSet.next()) {
                String fileName = rSet.getString(2);
                imageTable.append("<td><a>") //Set link ref
                        .append("<img alt=\"pro-image\" src=\"img/")
                        .append(fileName)
                        .append("\" /></a></td>");
                if (counter / 3.0 == Math.floor(counter / 3.0))
                    imageTable.append("</tr><tr>"); //New row every 3 images
            }
            imageTable.append("</tr></tbody>");

            request.getSession().setAttribute("ImageTable", imageTable.toString());
        } catch (Exception e) {
            response.getWriter().print(e.getMessage());
        } finally {
            utilDatabase.closeAll(conn, stmt, rSet);
        }
    }
}
