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

@WebServlet("/register")
public class EventRegistrationServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    private EventRegistrationDAO dao;
    
    // Initialize DAO in init() method
    @Override
    public void init() throws ServletException {
        dao = new EventRegistrationDAO();
    }
    
    /**
     * Handle POST request from registration form
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    	System.out.println("REGISTER POST HIT");
    	
        // Set character encoding for proper handling of special characters
        request.setCharacterEncoding("UTF-8");
        
        // Step 1: Read form data from request
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String eventName = request.getParameter("eventName");
        String preferredDateStr = request.getParameter("preferredDate");
        String ticketType = request.getParameter("ticketType");
       
        
        // Step 2: Validate required fields
        StringBuilder errors = new StringBuilder();
        
        if (fullName == null || fullName.trim().isEmpty()) {
            errors.append("Full Name is required. ");
        }
        if (email == null || email.trim().isEmpty()) {
            errors.append("Email is required. ");
        }
        if (phone == null || phone.trim().isEmpty()) {
            errors.append("Phone is required. ");
        }
        if (eventName == null || eventName.trim().isEmpty()) {
            errors.append("Event Name is required. ");
        }
        if (preferredDateStr == null || preferredDateStr.trim().isEmpty()) {
            errors.append("Preferred Date is required. ");
        }
        if (ticketType == null || ticketType.trim().isEmpty()) {
            errors.append("Ticket Type is required. ");
        }
        
        // If there are validation errors, redirect back with error message
        if (errors.length() > 0) {
            request.setAttribute("error", errors.toString());
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        
     // Date range validation per event
        java.util.Map<String, String[]> dateRanges = new java.util.HashMap<>();
        dateRanges.put("E-Summit",  new String[]{"2026-02-02", "2026-02-10"});
        dateRanges.put("T-Spark",   new String[]{"2026-02-03", "2026-02-05"});
        dateRanges.put("Tarangan",  new String[]{"2026-02-06", "2026-02-08"});
        dateRanges.put("T-Sports",  new String[]{"2026-02-01", "2026-02-15"});
        dateRanges.put("Zephyr",    new String[]{"2026-02-07", "2026-02-09"});

        if (eventName != null && preferredDateStr != null && !preferredDateStr.isEmpty()) {
            String[] range = dateRanges.get(eventName.trim());
            if (range != null) {
                if (preferredDateStr.compareTo(range[0]) < 0 || preferredDateStr.compareTo(range[1]) > 0) {
                    request.setAttribute("error", "Date must be between " + range[0] + " and " + range[1] + " for " + eventName);
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                    return;
                }
            }
        }

        
        
        
        try {
            // Step 3: Parse and convert data types
            Date preferredDate = Date.valueOf(preferredDateStr);
            
            // Step 4: Create model object
            EventRegistration registration = new EventRegistration();
            registration.setFullName(fullName.trim());
            registration.setEmail(email.trim());
            registration.setPhone(phone.trim());
            registration.setEventName(eventName.trim());
            registration.setPreferredDate(preferredDate);
            registration.setTicketType(ticketType);
      
            
            // Step 5: Call DAO to insert into database
            boolean success = dao.insertRegistration(registration);
            
            if (success) {
                // Step 6: On success, forward to success page
                request.setAttribute("registration", registration);
                request.getRequestDispatcher("success.jsp").forward(request, response);
            } else {
                // On failure, show error
                request.setAttribute("error", "Failed to save registration. Please try again.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
            
        } catch (IllegalArgumentException e) {
            // Handle date/number parsing errors
            request.setAttribute("error", "Invalid date or number format. Please check your input.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
    
    /**
     * Handle GET request - redirect to registration form
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }
    
    


    
}