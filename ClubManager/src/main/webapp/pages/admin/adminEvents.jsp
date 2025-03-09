<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, com.example.clubManager.models.Evenement" %>
<!DOCTYPE html>
<html lang="fr">
	<head>
	    <meta charset="UTF-8">
	    <title>Événements</title>
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
	    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
	    <style>
	        .sidebar {
	            background: #343a40;
	            border-right: 1px solid #495057;
	        }
	        .sidebar .nav-link {
	            color: #ffffff;
	            padding: 12px 20px;
	            transition: background 0.3s;
	        }
	        .sidebar .nav-link:hover {
	            background: #495057;
	        }
	        .sidebar .nav-link.active {
	            background: #495057;
	            font-weight: 500;
	        }
	        .sidebar .nav-link i {
	            margin-right: 10px;
	        }
	        .sidebar-header {
	            font-weight: bold;
	            padding-bottom: 20px;
	            color: #ffffff;
	        }
	        .main-content {
	            padding: 20px;
	        }
	        /* Flex layout for cards */
	        .card-columns {
	            display: flex;
	            flex-wrap: wrap;
	            gap: 20px;
	        }
	        .card {
	            width: calc(33% - 20px);
				justify-content: space-between;
	        }
	        .club_img{
	        	width: 100%;
	        	height: 200px;
	        }
	        .club_name{
	            transition: all ease-in-out 0.2s;
	        }
	        .club_name:hover{
	            color: brown;
	        }
	        .club-card {
	            display: flex;
	            flex-direction: column;
	            justify-content: center;
	            align-items: start;
	            gap: 15px;
				padding: 10px;
				position: relative;
				height: 530px;
	        }

	        .club_actions_cont{
	            position: absolute;
	            bottom: 10px;
	            display: flex;
	            justify-content: space-between;
	            align-items: center;
				width: 90%;



	        }
		    .alert-fixed {
		        position: fixed;
		        bottom: 20px;
		        left: 20px;
		        z-index: 1000;
		        width: 300px;
		        margin: 0;
		        box-shadow: 0 2px 10px rgba(0,0,0,0.1);
		    }
		    
		    main {
		        margin-bottom: 100px;
		        height: 500vh;
		    }
		    .event-details{
		    	padding: 10px;
		    
		    }
		    
		    .event-actions{
			    display: flex;
			    with: 100%;
			    width: 100%;
			    justify-content: space-between;
			    padding: 5px;		    

		    }
	    </style>
	</head>
	<body>
	<div class="container-fluid">
	    <div class="row">
	        <%@ include file="adminSidebar.jsp" %>
	        <main role="main" class="col-md-9 col-lg-10 px-4">
	        
	            <%-- Message Display --%>
	            <% String message = request.getParameter("message");
	               String success = request.getParameter("success");
	               String error = request.getParameter("error");
	               if (message != null) { 
	                   String alertType = "primary";
	                   if (error != null) alertType = "danger";
	                   else if (success != null) alertType = "success";
	            %>
	            <div class="alert alert-<%= alertType %> alert-dismissible fade show alert-fixed" role="alert">
	                <%= message %>
	                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
	            </div>
	            <% } %>
	
	            <h2 class="mt-4 text-center mb-5">Événements</h2>
	            
	            <div class="card-columns">
	                <% List<Evenement> events = (List<Evenement>) request.getAttribute("events");
	                   if (events != null && !events.isEmpty()) {
	                       for (Evenement event : events) { %>
	                <div class="card event-card">
	                    <img src="<%= request.getContextPath() %>/eventImage?id=<%= event.getIdEvenement() %>" 
	                         class="card-img-top club_img" 
	                         alt="<%= event.getNom() %>">	                
	                    <div class="event-details">
	                        <h5 class="card-title">
	                            <a href="eventDetails.jsp?id=<%= event.getIdEvenement() %>" 
	                               class="text-decoration-none text-dark">
	                               <%= event.getNom() %>
	                            </a>
	                        </h5>
	                        
	                        <div class="mb-2">
	                            <span class="date-badge">
	                                <%= new java.text.SimpleDateFormat("dd MMM yyyy HH:mm").format(event.getDateEvenement()) %>
	                            </span>
	                        </div>
	                        
	                        <p class="card-text"><%= event.getDescription() %></p>
	                        
	                        <div class="text-muted small">
	                            <i class="bi bi-geo-alt"></i> <%= event.getLieu() %><br>
	                            <i class="bi bi-people"></i> <%= event.getParticipants().size() %> participants<br>
	                            Club: <%= event.getClub().getNom() %>
	                        </div>
	                    </div>
	                    
	                    <div class="event-actions">
	                        <a href="./editEvent?id=<%= event.getIdEvenement() %>" 
	                           class="btn btn-primary btn-sm">Modifier</a>
							<form action="${pageContext.request.contextPath}/admin/deleteEvent" method="post">
							    <input type="hidden" name="eventId" value="<%= event.getIdEvenement() %>">
							    <button type="submit" class="btn btn-danger btn-sm">Supprimer</button>
							</form>
	                    </div>
	                </div>
	                <%   }
	                   } else { %>
	                <p>Aucun événement trouvé.</p>
	                <% } %>
	            </div>
	            
	           	<a href="${pageContext.request.contextPath}/admin/addEvent" class="btn btn-success btn-lg rounded-circle position-fixed" 
	               style="bottom: 20px; right: 20px;">+</a>

	        </main>
	    </div>
	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	</body>
</html>