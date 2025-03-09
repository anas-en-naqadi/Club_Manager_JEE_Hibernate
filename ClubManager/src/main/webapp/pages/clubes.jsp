<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, com.example.clubManager.models.Club" %>
<!DOCTYPE html>

<html lang="fr">
	<head>
	    <meta charset="UTF-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <title>Tous les clubs</title>
	    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
	    <style>
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

	        .show_more_cont{
	        	display: flex;
			    justify-content: space-between;
			    align-items: center;
			    width: 90%;
    			position: absolute;
	        	bottom: 5px;
	        }
	    </style>
	</head>
	
	
	<body>
	    <!-- Navbar dynamique -->
	    <%@ include file="navbar.jsp" %>

		<div class="container my-5">
		    <h1>Tous les clubs</h1>

		    <div class="row g-5">
		  
		  
	                <%
	                    List<Club> clubs = (List<Club>) request.getAttribute("clubs");
	                	Map<Integer, Integer> memberCounts = (Map<Integer, Integer>) request.getAttribute("memberCounts");
	                	
	                	
	                    if (clubs != null && !clubs.isEmpty()) {
	                        for (Club club : clubs) {
	                %>	  
					<div class="col-md-6 col-lg-4">
                        <div class="card club-card">
                            <img src="<%= request.getContextPath() %>/clubImage?id=<%= club.getIdClub() %>" class="card-img-top" alt="<%= club.getNom() %>">
                            <div class="card-body">
                                <h5 class="card-title">
                                    <a href="./club?id=<%= club.getIdClub() %>" class="text-decoration-none club_name" ><%= club.getNom() %></a>
                                </h5>
                                <p class="card-text"><%= club.getDescription() %></p>
                                <div class="show_more_cont">
									<span class="badge bg-primary">
									    <%= (memberCounts != null && memberCounts.containsKey(club.getIdClub())) ? memberCounts.get(club.getIdClub()) : 0 %> membres
									</span>
                                </div>
                            </div>
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
		    
		    
		</div>

	    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	</body>
</html>