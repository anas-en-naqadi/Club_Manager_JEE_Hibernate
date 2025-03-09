<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, com.example.clubManager.models.Club" %>
<!DOCTYPE html>
<html lang="fr">
	<head>
	    <meta charset="UTF-8">
	    <title>Clubs</title>
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <!-- Bootstrap CSS and Icons -->
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
	    </style>
	</head>
	<body>
	<div class="container-fluid">
	    <div class="row">
	        <%@ include file="adminSidebar.jsp" %>
	        <main role="main" class="col-md-9 col-lg-10 px-4">
	        
	        
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
	            <h2 class="mt-4 text-center mb-5">Clubs</h2>
	            <div class="card-columns">
	                <%
	                    List<Club> clubs = (List<Club>) request.getAttribute("clubs");
	                	Map<Integer, Integer> memberCounts = (Map<Integer, Integer>) request.getAttribute("memberCounts");

	                    if (clubs != null && !clubs.isEmpty()) {
	                        for (Club club : clubs) {
	                %>
	                <div class="card club-card h-1000">
	                    <!-- Display club image via the ClubImageServlet -->
	                    <img src="<%= request.getContextPath() %>/clubImage?id=<%= club.getIdClub() %>" 
	                         class="card-img-top club_img" 
	                         alt="<%= club.getNom() %>">
	                         
	                         
	                        <h5 class="card-title">
	                            <a href="clubDetails.jsp?id=<%= club.getIdClub() %>" class="text-decoration-none club_name" ><%= club.getNom() %></a>
	                        </h5>
	                        
	                        <p class="card-text">
							    <%= club.getDescription() %>
							</p>
							
	                        <p class="card-text">
							    Date de création:<%= club.getDateCreation() %>
							</p>							
							<p class="card-text">
								<%= (memberCounts != null && memberCounts.containsKey(club.getIdClub())) ? memberCounts.get(club.getIdClub()) : 0 %> Membre
							</p>

	                    
	                    
						<div class="club_actions_cont">
	                       	<a href="./editClub?id=<%= club.getIdClub() %>" class="btn btn-primary btn-sm club_actions">Edit</a>
	                       	 
	                       	 <a href="${pageContext.request.contextPath}/admin/addEvent?clubId=<%= club.getIdClub() %>" class="btn btn-info btn-sm">
					            <i class="bi bi-calendar-plus"></i> New Event
							</a>
							
							<form action="./deleteClub" method="post" style="display: inline;">
							    <input type="hidden" name="id" value="<%= club.getIdClub() %>">
							    <button type="submit" class="btn btn-danger btn-sm club_actions">Remove</button>
							</form>
			
						</div>
						                    
	                </div>
	                <%
	                        }
	                    } else {
	                %>
	                <p>Aucun club trouvé.</p>
	                <%
	                    }
	                %>
	            </div>
	            <a href="<%= request.getContextPath() %>/admin/addClub" class="btn btn-success btn-lg rounded-circle position-fixed" 
	               style="bottom: 20px; right: 20px;">+</a>
	        </main>
	    </div>
	</div>
	<!-- Bootstrap JS Bundle -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<script>
	    // Activate sidebar items based on current page
	    document.addEventListener('DOMContentLoaded', function() {
	        const currentPath = window.location.pathname;
	        document.querySelectorAll('.nav-link').forEach(link => {
	            if (link.href === currentPath) {
	                link.classList.add('active');
	            }
	        });
	    });
	</script>
	</body>
</html>
