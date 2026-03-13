package servlet;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EventRegistrationDAO;
import model.EventRegistration;

@WebServlet("/edit")
public class EditRegistrationServlet extends HttpServlet {

    private EventRegistrationDAO dao = new EventRegistrationDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        EventRegistration reg = dao.getById(id);

        if (reg == null) {
            // 👇 Handle missing record
            response.sendRedirect(request.getContextPath() + "/register");
            return;
        }

        request.setAttribute("registration", reg);
        request.getRequestDispatcher("/update.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	System.out.println("===== EDIT POST HIT =====");
    	System.out.println("id param: [" + request.getParameter("id") + "]");
    	System.out.println("fullName: [" + request.getParameter("fullName") + "]");
    	System.out.println("email: [" + request.getParameter("email") + "]");
    	System.out.println("phone: [" + request.getParameter("phone") + "]");
    	System.out.println("eventName: [" + request.getParameter("eventName") + "]");
    	System.out.println("preferredDate: [" + request.getParameter("preferredDate") + "]");
    	System.out.println("ticketType: [" + request.getParameter("ticketType") + "]");


        EventRegistration reg = new EventRegistration();
        reg.setId(Integer.parseInt(request.getParameter("id")));
        reg.setFullName(request.getParameter("fullName"));
        reg.setEmail(request.getParameter("email"));
        reg.setPhone(request.getParameter("phone"));
        reg.setEventName(request.getParameter("eventName"));
        try {
            reg.setPreferredDate(java.sql.Date.valueOf(request.getParameter("preferredDate")));
        } catch (Exception e) {
            System.out.println("DATE PARSE FAILED: " + e.getMessage());
            request.setAttribute("error", "Invalid date format.");
            request.setAttribute("registration", reg);
            request.getRequestDispatcher("update.jsp").forward(request, response);
            return;
        }

        reg.setTicketType(request.getParameter("ticketType"));
   
     // Date range validation
        java.util.Map<String, String[]> dateRanges = new java.util.HashMap<>();
        dateRanges.put("E-Summit",  new String[]{"2026-02-02", "2026-02-10"});
        dateRanges.put("T-Spark",   new String[]{"2026-02-03", "2026-02-05"});
        dateRanges.put("Tarangan",  new String[]{"2026-02-06", "2026-02-08"});
        dateRanges.put("T-Sports",  new String[]{"2026-02-01", "2026-02-15"});
        dateRanges.put("Zephyr",    new String[]{"2026-02-07", "2026-02-09"});

        String dateStr = request.getParameter("preferredDate");
        String evName = request.getParameter("eventName");
        String[] range = dateRanges.get(evName);
        if (range != null && (dateStr.compareTo(range[0]) < 0 || dateStr.compareTo(range[1]) > 0)) {
            request.setAttribute("error", "Invalid date for " + evName);
            request.setAttribute("registration", reg);
            request.getRequestDispatcher("update.jsp").forward(request, response);
            return;
        }

        
    
        boolean updated = dao.updateById(reg);
        if (updated) {
            EventRegistration freshReg = dao.getById(reg.getId());
            request.setAttribute("registration", freshReg);
            request.setAttribute("success", "Registration updated successfully!");
            request.getRequestDispatcher("success.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Update failed. Please try again.");
            request.setAttribute("registration", reg);
            request.getRequestDispatcher("update.jsp").forward(request, response);
        }

    }
}