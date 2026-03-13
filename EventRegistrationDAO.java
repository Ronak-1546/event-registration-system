package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.EventRegistration;
import util.DBConnection;


import java.sql.ResultSet; 


/**
 * Data Access Object for EventRegistration
 * Handles all database operations for event registrations
 */
public class EventRegistrationDAO {
    
    /**
     * Insert a new event registration into the database
     * @param registration The registration object to insert
     * @return true if successful, false otherwise
     */
	public boolean insertRegistration(EventRegistration registration) {
	    String sql = "INSERT INTO event_registration " +
	                 "(full_name, email, phone, event_name, preferred_date, " +
	                 "ticket_type) " +
	                 "VALUES (?, ?, ?, ?, ?, ?)";

	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet generatedKeys = null;

	    try {
	        conn = DBConnection.getConnection();
	        // 👇 ADD Statement.RETURN_GENERATED_KEYS
	        pstmt = conn.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);

	        pstmt.setString(1, registration.getFullName());
	        pstmt.setString(2, registration.getEmail());
	        pstmt.setString(3, registration.getPhone());
	        pstmt.setString(4, registration.getEventName());
	        pstmt.setDate(5, registration.getPreferredDate());
	        pstmt.setString(6, registration.getTicketType());

	        int rowsAffected = pstmt.executeUpdate();

	        if (rowsAffected > 0) {
	            // 👇 ADD THIS: Get the auto-generated ID
	            generatedKeys = pstmt.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                registration.setId(generatedKeys.getInt(1));
	            }
	            return true;
	        }
	        return false;

	    } catch (SQLException e) {
	        System.err.println("Error inserting registration: " + e.getMessage());
	        e.printStackTrace();
	        return false;
	    } finally {
	        try { if (generatedKeys != null) generatedKeys.close(); } catch (SQLException e) {}
	        try { if (pstmt != null) pstmt.close(); } catch (SQLException e) {}
	        DBConnection.closeConnection(conn);
	    }
	}

    
    public EventRegistration getById(int id) {
        String sql = "SELECT * FROM event_registration WHERE id=?";
        EventRegistration r = null;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                r = new EventRegistration();
                r.setId(rs.getInt("id"));
                r.setFullName(rs.getString("full_name"));
                r.setEmail(rs.getString("email"));
                r.setPhone(rs.getString("phone"));
                r.setEventName(rs.getString("event_name"));
                r.setPreferredDate(rs.getDate("preferred_date"));
                r.setTicketType(rs.getString("ticket_type"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

    
    
    
    
    
    public boolean updateById(EventRegistration r) {
        String sql = "UPDATE event_registration SET " +
                     "full_name=?, email=?, phone=?, event_name=?, preferred_date=?, " +
                     "ticket_type=? WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, r.getFullName());
            ps.setString(2, r.getEmail());
            ps.setString(3, r.getPhone());
            ps.setString(4, r.getEventName());
            ps.setDate(5, r.getPreferredDate());
            ps.setString(6, r.getTicketType());
            ps.setInt(7, r.getId());

            int rowsAffected = ps.executeUpdate();
            System.out.println("UPDATE rows affected: " + rowsAffected + " for id=" + r.getId());
            return rowsAffected > 0;

            

            

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    
    
    public boolean deleteById(int id) {
        String sql = "DELETE FROM event_registration WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}