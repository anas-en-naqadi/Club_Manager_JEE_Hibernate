<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>

<html lang="fr">
	<head>
	    <meta charset="UTF-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <title>Tous les clubs</title>
	    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
	    <style>
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
	    </style>
	</head>
	
	
	<body>
	    <!-- Navbar dynamique -->
	    <%@ include file="navbar.jsp" %>

		<div class="container my-5">
		    <h1>Tous les clubs</h1>

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

	    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	</body>
</html>