<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, com.example.clubManager.models.Club ,com.example.clubManager.models.Evenement"  %>
<% Club club = (Club) request.getAttribute("club"); %>

<!DOCTYPE html>
<html lang="fr">
	<head>
	    <meta charset="UTF-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <title><%=club.getNom() %> - Club</title>
	    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
	    <style>
	        .event-card img {
	            height: 180px;
	            object-fit: cover;
	        }
	        .club-banner {
	            height: 300px;
	            object-fit: cover;
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
	        <div class="row mb-5">
	            <div class="col-md-4">
                 	<img src="<%= request.getContextPath() %>/clubImage?id=<%= club.getIdClub() %>" class="card-img-top" alt="<%= club.getNom() %>">

	            </div>
	            <div class="col-md-8">
	                <h1><%= club.getNom() %></h1>
	                <p class="lead"><%= club.getNom() %></p>
	                <div class="d-flex gap-3">
              
	                    <span class="badge bg-primary d-flex align-items-center">
	                        <%= request.getAttribute("memberCounts") %> membres
	                    </span>
						<%
						    // Retrieve the attribute as a Boolean object
						    Boolean isMemberObj = (Boolean) request.getAttribute("isMember");
						    // Default to false if the attribute is null
						    boolean isMember = (isMemberObj != null ? isMemberObj.booleanValue() : false);
						    
						    if(isMember){
						%>
						    <form method="post" action="${pageContext.request.contextPath}/exit_club">
						        <input type="hidden" name="clubId" value="${club.idClub}">
						        <button type="submit" class="btn btn-sm btn-outline-danger">Sortir</button>
						    </form>
						<%
						    } else {
						%>
						
						
						    <form method="post" action="${pageContext.request.contextPath}/join_club">
						        <input type="hidden" name="clubId" value="<%= club.getIdClub() %>">
						        <button type="submit" class="btn btn-success">Rejoindre le club</button>
						    </form>						
						
						
						
						
						
						

						<%
						    }
						%>

	                </div>
	            </div>
	        </div>
	
	        <h2 class="mb-4">Événements à venir</h2>
	        <div class="row g-4">
	            <%
	            
	            	Set<Evenement> evenements = (Set<Evenement>) request.getAttribute("evenements");
	                if (evenements != null && !evenements.isEmpty()) {
	                	for (Evenement event : evenements) {
	            %>
	            
	            
	            <%
	            
	            
	            
	            %>
	            
					<div class="col-md-6 col-lg-4">
	                    <div class="card event-card h-100">
	                        <img src="<%= request.getContextPath() %>/eventImage?id=<%= event.getIdEvenement() %>"  class="card-img-top" alt="<%=event.getNom()%>">
	                        <div class="card-body">
	                            <h5 class="card-title"><%=event.getNom()%></h5>
	                            <p class="card-text"><%=event.getDescription()%></p>
	                            <ul class="list-group list-group-flush">
	                                <li class="list-group-item">Date: <%=event.getDateEvenement()%></li>
	                                <li class="list-group-item">Lieu: <%=event.getLieu()%></li>
	                            </ul>
	                            <div class="card-footer d-flex justify-content-between align-items-center">
									<span class="badge bg-success">
									    <%= (event.getParticipants().isEmpty() ? 0 : event.getParticipants().size()) %> participants
									</span>
	                                
	                                
	                                
	                                
			                                
				                    <form method="post" action="${pageContext.request.contextPath}/join_event">
				                    	<input type="hidden" name="clubId" value="<%= club.getIdClub() %>">
				                   
				                        <input type="hidden" name="eventId" value="<%= event.getIdEvenement() %>">
				                        <button type="submit" class="btn btn-sm btn-primary">S'inscrire</button>
				                    </form>	                                
	                                

	                            </div>
	                        </div>
	                    </div>
	                </div>	            
	            
	            
	            
	            
	            <%
	                 }
	                } else {
	            %>
	                <p>Aucun évenemment trouvé.</p>
	            <%
	                 }
	            %>	

	        </div>
	    </div>
	
	    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

	</body>
</html>