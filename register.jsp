<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Event Registration</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        
        body {
             background:#838996 ;
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
        }
        
        .container {
            background:rgb(229,228,226);
            padding: 40px;
            border-radius: 16px;
            box-shadow: 0 20px 60px rgba(0,0,0,0.3);
            width: 100%;
            max-width: 600px;
        }
        
        h1 {
            text-align: center;
            color:#1A1110;
            margin-bottom: 10px;
            font-size: 28px;
        }
        
        .error {
            background: #ffe6e6;
            color: #d63031;
            padding: 12px;
            border-radius: 8px;
            margin-bottom: 20px;
            border-left: 4px solid #d63031;
        }
        
        .form-group {
            margin-bottom: 26px;
        }
        
        .form-row {
            display: flex;
            gap: 30px;
        }
        
        .form-row .form-group {
            flex: 1;
        }
        
        label {
            display: block;
            margin-bottom: 8px;
            font-weight: 500;
            color: #333;
        }
        
        label .required {
            color: #e74c3c;
        }
        
        input[type="text"],
        input[type="email"],
        input[type="tel"],
        input[type="date"],
        input[type="number"],
        select,
        textarea {
            width: 100%;
            padding: 12px 16px;
            border: 2px solid #e0e0e0;
            border-radius: 8px;
            font-size: 16px;
            transition: border-color 0.3s, box-shadow 0.3s;
        }
        
        input:focus,
        select:focus,
        textarea:focus {
            outline: none;
            border-color: #1A1110;
            box-shadow: #1A1110;
        }
        
        .radio-group {
            display: flex;
            gap: 20px;
            padding: 10px 0;
        }
        
        .radio-option {
            display: flex;
            align-items: center;
            gap: 8px;
            cursor: pointer;
        }
        
        .radio-option input[type="radio"] {
            width: 18px;
            height: 18px;
            cursor: pointer;
        }
        
        textarea {
            resize: vertical;
            min-height: 100px;
        }
        
        .divider {
            border-top: 1px solid #e0e0e0;
            margin: 25px 0;
        }
        
        button {
            width: 100%;
            padding: 16px;
            background: #1A1110;
            color: white;
            border: none;
            border-radius: 8px;
            font-size: 18px;
            font-weight: 600;
            cursor: pointer;
            transition: transform 0.2s, box-shadow 0.2s;
        }
        
        
        
         
    .date-hint {
    color: #353839;
    font-size: 12px;
    margin-top: 4px;
}
    
        
        
    </style>
    
    <script
  src="https://unpkg.com/@lottiefiles/dotlottie-wc@0.8.11/dist/dotlottie-wc.js"
  type="module"
></script>
 
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
            dateInput.value = "";
            hint.textContent = "Allowed: " + dateRanges[event].min + " to " + dateRanges[event].max;
        } else {
            dateInput.min = "";
            dateInput.max = "";
            dateInput.value = "";
            dateInput.disabled = true;
            hint.textContent = "";
        }
    }
</script>
  
  
</head>
<body>


 <!-- LOTTIE OUTSIDE FORM -->
<dotlottie-wc
  src="https://lottie.host/e8a025f3-dd0f-4569-bdfb-a45d456c1be5/3uaeVEOSkC.lottie"
  style="width: 500px;height: 500px"
  autoplay
  loop
></dotlottie-wc>
 </div>




         <div class="container">
        <h1>Event Registration Form</h1>
        <!-- Display error message if any -->
        <% if (request.getAttribute("error") != null) { %>
            <div class="error">
                <%= request.getAttribute("error") %>
            </div>
        <% } %>
        
       <form action="<%=request.getContextPath()%>/register" method="POST">
            <div class="form-group">
                <label>Full Name <span class="required">*</span></label>
                <input type="text" name="fullName" placeholder="Enter your full name" required>
            </div>
            
            <div class="form-row">
                <div class="form-group">
                    <label>Email Address <span class="required">*</span></label>
                    <input type="email" name="email" placeholder="xyz@gmail.com" required>
                </div>
                <div class="form-group">
                    <label>Phone Number <span class="required">*</span></label>
                    <input type="tel" name="phone" placeholder="+91 XXXXX XXXXX" required>
                </div>
            </div>
            
 <div class="form-row">
    <div class="form-group">
        <label>Event Name <span class="required">*</span></label>
        <select name="eventName" id="eventName" required onchange="updateDateRange()">
            <option value="">Select an event</option>
            <option value="E-Summit">E-Summit (Feb 2 – Feb 10)</option>
            <option value="T-Spark">T-Spark (Feb 3 – Feb 5)</option>
            <option value="Tarangan">Tarangan (Feb 6 – Feb 8)</option>
            <option value="T-Sports">T-Sports (Feb 1 – Feb 15)</option>
            <option value="Zephyr">Zephyr (Feb 7 – Feb 9)</option>
        </select>
    </div>

    <div class="form-group">
        <label>Preferred Date <span class="required">*</span></label>
        <input type="date" name="preferredDate" id="preferredDate" required disabled>
        <small id="dateHint" class="date-hint"></small>
    </div>
</div>
 
   
           
            <div class="form-group">
                <label>Ticket Type <span class="required">*</span></label>
                <div class="radio-group">
                    <label class="radio-option">
                        <input type="radio" name="ticketType" value="Thakurpass" required >
                        Thakurpass-Free
                    </label>
                    <label class="radio-option">
                        <input type="radio" name="ticketType" value="Standard" >
                        Standard-₹200
                    </label>
                    <label class="radio-option">
                        <input type="radio" name="ticketType" value="VIP">
                        VIP-₹400
                    </label>
                </div>
            </div>
            
        
            <div class="divider"></div>
            
            
            <button type="submit">Register</button>
        </form>
    </div>
</body>
</html>