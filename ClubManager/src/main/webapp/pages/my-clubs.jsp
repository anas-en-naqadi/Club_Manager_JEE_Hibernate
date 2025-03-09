<%@ page contentType="text/html;charset=UTF-8" %>


<!DOCTYPE html>
<html lang="fr">
	<head>
	    <meta charset="UTF-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <title>Mes Clubs - UniClubs</title>
	    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
	    
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
	        <h1 class="text-center mb-5">Mes Clubs</h1>
	        
	        <c:choose>
	            <c:when test="${empty clubs}">
	                <div class="empty-state text-center">
	                    <div>
	                        <h2 class="text-muted mb-4">Aucun club rejoint pour le moment</h2>
	                        <a href="${pageContext.request.contextPath}/clubes" class="btn btn-primary btn-lg">
	                            Découvrir les clubs
	                        </a>
	                    </div>
	                </div>
	            </c:when>
	            
	            <c:otherwise>
					<div class="container my-5">
			
						<div class="row g-4">
						    <c:forEach items="${clubs}" var="club">
						        <div class="col-md-6 col-lg-4">
						            <div class="card club-card">
						            
						            	<img src="<%= request.getContextPath() %>/clubImage?id=${club.idClub}" class="card-img-top" alt="${club.nom}">
						            	
						            	
						                <div class="card-body">
						                    <h5 class="card-title">
						                        <a href="${pageContext.request.contextPath}/club?id=${club.idClub}" 
						                           class="text-decoration-none club_name">${club.nom}</a>
						                    </h5>
						                    <p class="card-text">${club.description}</p>
						                    
						                    <div class="bottom_cont">
						                        <span class="badge bg-primary">${club.membres.size()} membres</span>
						                        
							                    <form method="post" action="${pageContext.request.contextPath}/exit_club">
							                        <input type="hidden" name="clubId" value="${club.idClub}">
							                        <button type="submit" class="btn btn-sm btn-outline-danger ">Sortir</button>
							                    </form>
							                    
						                    </div>
						                    
						                </div>
						            </div>
						        </div>
						    </c:forEach>
						</div>
					    
					    
					</div>
			
	            </c:otherwise>
	        </c:choose>
	    </div>
	    
	    
	    
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	    
	</body>
</html>