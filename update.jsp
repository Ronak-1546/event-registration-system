<%@ page import="model.EventRegistration" %>

<%
EventRegistration reg =
    (EventRegistration) request.getAttribute("registration");

if (reg == null) {
%>
    <h2 style="color:red;">No data found for update</h2>
<%
    return;
}
%>

<form action="update" method="post">

    <input type="hidden" name="id" value="<%= reg.getId() %>">

    <input type="text" name="fullName" value="<%= reg.getFullName() %>">
    <input type="email" name="email" value="<%= reg.getEmail() %>">
    <input type="text" name="phone" value="<%= reg.getPhone() %>">
    <input type="text" name="eventName" value="<%= reg.getEventName() %>">

    <input type="date" name="preferredDate"
           value="<%= reg.getPreferredDate().toString() %>">

    <button type="submit">Save Changes</button>
</form>
