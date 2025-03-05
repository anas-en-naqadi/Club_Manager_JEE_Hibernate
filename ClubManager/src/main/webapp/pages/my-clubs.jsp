<%@ page contentType="text/html;charset=UTF-8" %>


<!DOCTYPE html>
<html lang="fr">
	<head>
	    <meta charset="UTF-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <title>Mes Clubs - UniClubs</title>
	    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
	    
	    <style>
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
	    </style>
	</head>
	<body>
	
	    <!-- Navbar dynamique -->
	    <%@ include file="navbar.jsp" %>
	
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
			                        <div class="card club-card h-100">
			                            <img src="${club.img}" class="card-img-top" alt="${club.name}">
			                            <div class="card-body">
			                                <h5 class="card-title">
			                                    <a href="./club?id=${club.id}" class="text-decoration-none club_name">${club.name}</a>
			                                </h5>
			                                <p class="card-text">${club.desc}</p>
			                                <div class="d-flex justify-content-between align-items-center">
			                                    <span class="badge bg-primary">${club.members} membres</span>
			                                    <a href="club.html?id=${club.id}" class="btn btn-sm btn-outline-primary">Voir plus</a>
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