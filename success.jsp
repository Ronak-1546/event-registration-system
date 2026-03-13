<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.EventRegistration" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration Successful</title>

<style>
* {
    box-sizing: border-box;
    font-family: Arial, sans-serif;
}

body {
    background: #838996 ;
    min-height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
}

.container {
    background: #fff;
    padding: 25px;
    border-radius: 8px;
    width: 420px;
}

h2 {
    text-align: left;
    margin-bottom: 20px;
}

table {
    width: 100%;
    border-collapse: collapse;
}

td {
    padding: 10px;
    border: 1px solid #ccc;
}

td:first-child {
    font-weight: bold;
    width: 40%;
    background: #f4f4f4;
}

.action-buttons {
    margin-top: 20px;
    display: flex;
    gap: 10px;
    justify-content: center;
}

button {
    padding: 8px 18px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    color: white;
}

.btn-update {
    background: #1A1110;
}

.btn-delete {
    background: #E3242B;
}
</style>
</head>

<body>

<%
EventRegistration reg = (EventRegistration) request.getAttribute("registration");
if (reg != null) {
%>

<div class="container">
    <h2>Registration Details</h2>

    <table>
        <tr>
            <td>Name</td>
            <td><%= reg.getFullName() %></td>
        </tr>
        <tr>
            <td>Email</td>
            <td><%= reg.getEmail() %></td>
        </tr>
        <tr>
            <td>Event</td>
            <td><%= reg.getEventName() %></td>
        </tr>
        <tr>
            <td>Date</td>
            <td><%= reg.getPreferredDate() %></td>
        </tr>
        <tr>
            <td>Ticket Type</td>
            <td><%= reg.getTicketType() %></td>
        </tr>
    </table>

    <div class="action-buttons">
        <form action="<%=request.getContextPath()%>/edit" method="get">
            <input type="hidden" name="id" value="<%= reg.getId() %>">
            <button class="btn-update" type="submit">Update</button>
        </form>

        <form action="<%=request.getContextPath()%>/delete" method="post">
            <input type="hidden" name="id" value="<%= reg.getId() %>">
            <button class="btn-delete" type="submit"
                    onclick="return confirm('Are you sure you want to delete?')">
                Delete
            </button>
        </form>
    </div>
</div>

<% } %>

</body>
</html>
