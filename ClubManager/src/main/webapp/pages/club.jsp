<%@ page contentType="text/html;charset=UTF-8" %>


<!DOCTYPE html>
<html lang="fr">
	<head>
	    <meta charset="UTF-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <title>XX - UniClubs</title>
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
	    </style>
	</head>
	
	
	<body>
	    <!-- Navbar dynamique -->
	    <%@ include file="navbar.jsp" %>
	
	    <div class="container my-5">
	        <div class="row mb-5">
	            <div class="col-md-4">
	                <img src="${club.image}" class="club-banner w-100 rounded mb-3" 
	                     alt="${club.name}">
	            </div>
	            <div class="col-md-8">
	                <h1>${club.name}</h1>
	                <p class="lead">${club.description}</p>
	                <div class="d-flex gap-3">
	                    <span class="badge bg-primary d-flex align-items-center">
	                        ${club.members} membres
	                    </span>
	                    <form method="post" action="${pageContext.request.contextPath}/join-club">
	                        <input type="hidden" name="clubId" value="${club.id}">
	                        <button type="submit" class="btn btn-success">Rejoindre le club</button>
	                    </form>
	                </div>
	            </div>
	        </div>
	
	        <h2 class="mb-4">Événements à venir</h2>
	        <div class="row g-4">
	            <c:forEach items="${club.events}" var="event">
	                <div class="col-md-6 col-lg-4">
	                    <div class="card event-card h-100">
	                        <img src="${event.image}" class="card-img-top" alt="${event.title}">
	                        <div class="card-body">
	                            <h5 class="card-title">${event.title}</h5>
	                            <p class="card-text">${event.description}</p>
	                            <ul class="list-group list-group-flush">
	                                <li class="list-group-item">Date: ${event.date}</li>
	                                <li class="list-group-item">Lieu: ${event.location}</li>
	                            </ul>
	                            <div class="card-footer d-flex justify-content-between align-items-center">
	                                <span class="badge bg-success">${event.participants} participants</span>
	                                <button class="btn btn-sm btn-primary">S'inscrire</button>
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