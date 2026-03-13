<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.EventRegistration" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Registration</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; }
        body {
            background: #838996 ;
            min-height: 100vh; display: flex; justify-content: center; align-items: center; padding: 20px;
        }
        .container { background: rgb(229,228,226); padding: 40px; border-radius: 16px;
            box-shadow: 0 20px 60px rgba(0,0,0,0.3); width: 100%; max-width: 600px; }
        h1 { text-align: center; color:#1A1110; margin-bottom: 10px; font-size: 28px; }
        .form-group { margin-bottom: 20px; }
        .form-row { display: flex; gap: 20px; }
        .form-row .form-group { flex: 1; }
        label { display: block; margin-bottom: 8px; font-weight: 500; color: #333; }
        label .required { color: #e74c3c; }
        input[type="text"], input[type="email"], input[type="tel"], input[type="date"],
        input[type="number"], select, textarea {
            width: 100%; padding: 12px 16px; border: 2px solid #e0e0e0;
            border-radius: 8px; font-size: 16px;
        }
        input:focus, select:focus, textarea:focus {
            outline: none; border-color:#1A1110; box-shadow: #1A1110;
        }
        .radio-group { display: flex; gap: 20px; padding: 10px 0; }
        .radio-option { display: flex; align-items: center; gap: 8px; cursor: pointer; }
        .radio-option input[type="radio"] { width: 18px; height: 18px; }
        textarea { resize: vertical; min-height: 100px; }
        .divider { border-top: 1px solid #e0e0e0; margin: 25px 0; }
        button { width: 100%; padding: 16px;
            background: #1A1110;
            color: white; border: none; border-radius: 8px; font-size: 18px;
            font-weight: 600; cursor: pointer; }
       
        
        .date-hint {
    font-size: 12px;
    color: #353839;
    margin-top: 4px;
    }
        
        
    </style>
</head>
<body>
<%
    EventRegistration reg = (EventRegistration) request.getAttribute("registration");
    if (reg == null) {
        response.sendRedirect(request.getContextPath() + "/register");
        return;
    }
%>
<div class="container">
    <h1>Update Registration</h1>
    
    <% if (request.getAttribute("error") != null) { %>
    <div style="background:#ffe6e6;color:#d63031;padding:12px;border-radius:8px;margin-bottom:20px;border-left:4px solid #d63031;">
        <%= request.getAttribute("error") %>
    </div>
<% } %>
    

    <form action="<%=request.getContextPath()%>/edit" method="POST">
        <input type="hidden" name="id" value="<%= reg.getId() %>">

        <div class="form-group">
            <label>Full Name <span class="required">*</span></label>
            <input type="text" name="fullName" value="<%= reg.getFullName() %>" required>
        </div>

        <div class="form-row">
            <div class="form-group">
                <label>Email Address <span class="required">*</span></label>
                <input type="email" name="email" value="<%= reg.getEmail() %>" required>
            </div>
            <div class="form-group">
                <label>Phone Number <span class="required">*</span></label>
                <input type="tel" name="phone" value="<%= reg.getPhone() %>" required>
            </div>
        </div>

     
           <div class="form-row">
    <div class="form-group">
        <label>Event Name <span class="required">*</span></label>
        <select name="eventName" id="eventName" required onchange="updateDateRange()">
            <option value="">Select an event</option>
            <option value="E-Summit" <%= "E-Summit".equals(reg.getEventName()) ? "selected" : "" %>>E-Summit (Feb 2 – Feb 10)</option>
            <option value="T-Spark" <%= "T-Spark".equals(reg.getEventName()) ? "selected" : "" %>>T-Spark (Feb 3 – Feb 5)</option>
            <option value="Tarangan" <%= "Tarangan".equals(reg.getEventName()) ? "selected" : "" %>>Tarangan (Feb 6 – Feb 8)</option>
            <option value="T-Sports" <%= "T-Sports".equals(reg.getEventName()) ? "selected" : "" %>>T-Sports (Feb 1 – Feb 15)</option>
            <option value="Zephyr" <%= "Zephyr".equals(reg.getEventName()) ? "selected" : "" %>>Zephyr (Feb 7 – Feb 9)</option>
        </select>
    </div>
    <div class="form-group">
        <label>Preferred Date <span class="required">*</span></label>
        <input type="date" name="preferredDate" id="preferredDate"
               value="<%= reg.getPreferredDate() %>" required>
        <small id="dateHint" style="color:#888; margin-top:4px; display:block;"></small>
    </div>
</div>
           


        <div class="form-group">
            <label>Ticket Type <span class="required">*</span></label>
            <div class="radio-group">
                <label class="radio-option">
                    <input type="radio" name="ticketType" value="Thakurpass"
                        <%= "Thakurpass".equals(reg.getTicketType()) ? "checked" : "" %>> Thakurpass-Free
                </label>
                <label class="radio-option">
                    <input type="radio" name="ticketType" value="Standard"
                        <%= "Standard".equals(reg.getTicketType()) ? "checked" : "" %>> Standard-₹200
                </label>
                <label class="radio-option">
                    <input type="radio" name="ticketType" value="VIP"
                        <%= "VIP".equals(reg.getTicketType()) ? "checked" : "" %>> VIP-₹400
                </label>
            </div>
        </div>


        <div class="divider"></div>


        <button type="submit">Save</button>
    </form>
</div>

<script>
    const dateRanges = {
        "E-Summit":  { min: "2026-02-02", max: "2026-02-10" },
        "T-Spark":   { min: "2026-02-03", max: "2026-02-05" },
        "Tarangan":  { min: "2026-02-06", max: "2026-02-08" },
        "T-Sports":  { min: "2026-02-01", max: "2026-02-15" },
        "Zephyr":    { min: "2026-02-07", max: "2026-02-09" }
    };

    function updateDateRange() {
        const event = document.getElementById("eventName").value;
        const dateInput = document.getElementById("preferredDate");
        const hint = document.getElementById("dateHint");

        if (dateRanges[event]) {
            dateInput.min = dateRanges[event].min;
            dateInput.max = dateRanges[event].max;
            dateInput.disabled = false;
            hint.textContent = "Allowed: " + dateRanges[event].min + " to " + dateRanges[event].max;
        } else {
            dateInput.min = "";
            dateInput.max = "";
            dateInput.value = "";
            dateInput.disabled = true;
            hint.textContent = "";
        }
    }

    // Auto-set range on page load for pre-selected event
    window.onload = function() { updateDateRange(); };
</script>

</body>
</html>
