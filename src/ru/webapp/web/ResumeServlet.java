package ru.webapp.web;

import ru.webapp.Config;
import ru.webapp.model.Resume;
import ru.webapp.storage.Storage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ResumeServlet extends HttpServlet {
    private Storage storage = Config.get().getStorage();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset =UTF-8");
        String name = request.getParameter("name");
        response.getWriter().write(name == null ? "Hello from get servlet!" : "Hello " + name + "!");

        PrintWriter writer = response.getWriter();
        writer.write(
                "<div style=\"text-align: center;\">\n" +
                        "<table border=\"2\" width=\"200\" height=\"150\">\n" +
                        "    <tr>\n" +
                        "        <td><strong>Uuid</strong></td>\n" +
                        "        <td><strong>Full Name</strong></td>\n" +
                        "    </tr>\n");
        for (Resume resume : storage.getAllSorted()) {
            writer.write("<tr>\n" +
                    "        <td>" + resume.getUuid() + "</td>\n" +
                    "        <td>" + resume.getFullName() + "</td>\n" +
                    "    </tr>\n");
        }
        writer.write("</table>\n" + "</div>");
    }
}
