package servlet;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EventRegistrationDAO;

@WebServlet("/delete")
public class DeleteRegistrationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        EventRegistrationDAO dao = new EventRegistrationDAO();
        dao.deleteById(id);
        // 👇 Redirect to registration form
        response.sendRedirect(request.getContextPath() + "/register");
    }
}

