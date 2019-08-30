package com.turchik;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet(name = "CommentServlet", urlPatterns = "/CommentServlet")
public class CommentServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request,
                          javax.servlet.http.HttpServletResponse response) throws IOException {
        var comment = request.getParameter("ed-comment");

        //post comment to db

        response.sendRedirect("profile.html");
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request,
                         javax.servlet.http.HttpServletResponse response) throws IOException {
        String stuff = "THIS IS SOME AWESOME STUFF";
        response.getWriter().println(stuff);
    }
}
