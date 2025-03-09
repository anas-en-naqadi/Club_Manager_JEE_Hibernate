<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, com.example.clubManager.models.Club" %>


<!DOCTYPE html>
<html>
	<head>
	    <meta charset="UTF-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <title>University Clubs Manager</title>
	    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
	    <style>
	        .hero-section {
	            background: linear-gradient(rgba(0,0,0,0.5), rgba(0,0,0,0.5)), url('https://source.unsplash.com/random/1920x1080/?university') center/cover;
	            height: 60vh;
	        }
	        .feature-card:hover {
	            transform:scale(1.09);
	            transition: all ease-in-out 0.4s;
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

	        .show_more_cont{
	        	display: flex;
			    justify-content: space-between;
			    align-items: center;
			    width: 90%;
    			position: absolute;
	        	bottom: 5px;
	        }
	        h2{
				margin-bottom: 30px;
	        
	        }
	    </style>
	    
	    
	    
	    
	    
	    
	</head>
	
	
	<body>
	
	    <!-- Navbar dynamique -->
	    <%@ include file="navbar.jsp" %>
	
	    <!-- Hero Section -->
	    <div class="hero-section text-white d-flex align-items-center">
	        <div class="container text-center">
	            <h1 class="display-4 mb-4">Bienvenue à UniClubs</h1>
	            <p class="lead mb-4">Rejoignez la communauté étudiante et découvrez tous les clubs de votre université</p>
	            <a href="./clubes" class="btn btn-primary btn-lg">Explorer les clubs</a>
	        </div>
	    </div>
	
		<div class="container my-5">
		    <h2>Clubs populaire</h2>

		    <div class="row g-5">
	                <%
	                    List<Club> popularClubs = (List<Club>) request.getAttribute("popularClubs");
	                	Map<Integer, Integer> memberCounts = (Map<Integer, Integer>) request.getAttribute("memberCounts");

	                    if (popularClubs != null && !popularClubs.isEmpty()) {
	                        for (Club club : popularClubs) {
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
    	
    	
	</body>
</html>