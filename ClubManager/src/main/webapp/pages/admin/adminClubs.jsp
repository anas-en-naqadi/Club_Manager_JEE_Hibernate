<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.example.clubManager.models.Club" %>
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
	            height: 100vh;
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

	    </style>
	</head>
	<body>
	<div class="container-fluid">
	    <div class="row">
	        <%@ include file="adminSidebar.jsp" %>
	        <main role="main" class="col-md-9 col-lg-10 px-4">
	            <h2 class="mt-4 text-center mb-5">Clubs</h2>
	            <div class="card-columns">
	                <%
	                    List<Club> clubs = (List<Club>) request.getAttribute("clubs");
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
							    Membres: 0
							</p>

	                    
	                    
						<div class="club_actions_cont">
	                       	<a href="./editClub?id=<%= club.getIdClub() %>" class="btn btn-primary btn-sm club_actions">Edit</a>
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
