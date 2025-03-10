<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, com.example.clubManager.models.Club , com.example.clubManager.models.Utilisateur" %>


<!DOCTYPE html>
<html lang="fr">
	<head>
	    <meta charset="UTF-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <title>Add event - UniClubs</title>
	    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
	    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
	    
	    <style>
	    	.card-body{
	    		display: flex;
	    		flex-direction: column;
    			gap: 25px;

	    	
	    	}
	        .club-card img {
	            height: 200px;
	            object-fit: cover;
	        }
	        .empty-state {
	            min-height: 400px;
	            display: flex;
	            align-items: center;
	            justify-content: center;
	        }
	        
	    	.club-card{
				height: 110%;
	    	}
	        .club-card img {
	            height: 200px;
	            object-fit: cover;
	        }
	        .club_name{
	            transition: all ease-in-out 0.2s;
	        }
	        .club_name:hover{
	            color: brown;
	        }

	        .bottom_cont{
	        	display: flex;
			    justify-content: space-between;
			    align-items: center;
			    width: 90%;
    			position: absolute;
	        	bottom: 5px;
	        }
	      
	      	.bottom_cont > a{
				font-size: 0.76em;
				height: 23px;
				display: flex;
				justify-content: center;
				align-items: center;	      		
	      	
	      	}
	      
	      
		    .alert-fixed {
		        position: fixed;
		        bottom: 20px;
		        left: 20px;
		        z-index: 1000;
		        width: fit-content;
		        margin: 0;
		        box-shadow: 0 2px 10px rgba(0,0,0,0.1);
		    }		      
	        
	    </style>
	</head>
	<body>
	
	    <!-- Navbar dynamique -->
	    <%@ include file="navbar.jsp" %>
	
	
				<%-- Message Display Section --%>
				<%
				    String message = request.getParameter("message");
				    String success = request.getParameter("success");
				    String error = request.getParameter("error");
				    
				    if (message != null && !message.isEmpty()) {
				        String alertType = "primary"; // default type
				        
				        // Check error first for highest priority
				        if (error != null && Boolean.parseBoolean(error)) {
				            alertType = "danger";
				        } 
				        // Then check success status
				        else if (success != null) {
				            alertType = Boolean.parseBoolean(success) ? "success" : "danger";
				        }
				%>
			<div class="alert alert-<%= alertType %> alert-dismissible fade show alert-fixed" role="alert">
			    <%= message %>
			    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
			</div>
				<%
				    }
				%>	
	
	

	
	    <div class="container my-5">
<h2 class="my-4">Create New Event</h2>
			    
			    <form action="${pageContext.request.contextPath}/addEvent" method="post" enctype="multipart/form-data">
			        <div class="mb-3">
			            <label class="form-label">Event Name:</label>
			            <input type="text" name="nom" class="form-control" required>
			        </div>
			        
			        <div class="mb-3">
			            <label class="form-label">Description:</label>
			            <textarea name="description" class="form-control" rows="3"></textarea>
			        </div>
			        
			        
			        <div class="mb-3">
						 <label for="image" class="form-label">Image d'evenement</label>
						 <input type="file" class="form-control" id="image" name="image" accept="image/*">
					</div>
					
					
			        <div class="row">
			            <div class="col-md-6 mb-3">
			                <label class="form-label">Date & Time:</label>
			                <input type="datetime-local" name="date" class="form-control" required>
			            </div>
			            <div class="col-md-6 mb-3">
			                <label class="form-label">Location:</label>
			                <input type="text" name="lieu" class="form-control" required>
			            </div>
			        </div>
			        
			        <div class="mb-3">
			            <label class="form-label">Club:</label>
			            <select name="clubId" class="form-select" required>
			                <c:forEach items="${clubs}" var="club">
			                    <option value="${club.idClub}" 
			                        ${club.idClub == preselectedClubId ? 'selected' : ''}>
			                        ${club.nom}
			                    </option>
			                </c:forEach>
			            </select>
			        </div>
			        
			        <button type="submit" class="btn btn-primary">Create Event</button>
			        <a href="${pageContext.request.contextPath}/admin/evenements" class="btn btn-secondary">Cancel</a>
			    </form>	        
	    </div>
	    
	    
	    
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	    
	</body>
</html>