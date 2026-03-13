package model;

import java.sql.Date;

/**
 * Model class representing an Event Registration
 * This follows the JavaBean conventions
 */
public class EventRegistration {
    
    // Private instance variables (encapsulation)
    private int id;
    private String fullName;
    private String email;
    private String phone;
    private String eventName;
    private Date preferredDate;
    private String ticketType;
 
    
    // Default constructor (required for JavaBean)
    public EventRegistration() {
    }
    
    // Parameterized constructor for convenience
    public EventRegistration(String fullName, String email, String phone,
                            String eventName, Date preferredDate, 
                            String ticketType) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.eventName = eventName;
        this.preferredDate = preferredDate;
        this.ticketType = ticketType;
      
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getFullName() {
        return fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getEventName() {
        return eventName;
    }
    
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
    
    public Date getPreferredDate() {
        return preferredDate;
    }
    
    public void setPreferredDate(Date preferredDate) {
        this.preferredDate = preferredDate;
    }
    
    public String getTicketType() {
        return ticketType;
    }
    
    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }
    
  

 
    

    
    // toString method for debugging
    @Override
    public String toString() {
        return "EventRegistration [id=" + id + ", fullName=" + fullName + 
               ", email=" + email + ", eventName=" + eventName + "]";
    }
}