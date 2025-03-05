<%@ page contentType="text/html;charset=UTF-8" %>



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
	            cursor: pointer;
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
	        <h2>Clubs populaires</h2>
	        <div class="row">
	            <c:forEach items="${featuredClubs}" var="club">
	                <div class="col-md-4 mb-4">
	                    <a href="${pageContext.request.contextPath}/club?id=${club.id}">
	                        <div class="card">
	                            <div class="card-body">
	                                <h5>${club.name}</h5>
	                                <p>${club.description}</p>
	                                <span class="badge bg-primary">${club.members} membres</span>
	                            </div>
	                        </div>
	                    </a>
	                </div>
	            </c:forEach>
	        </div>
    	</div>
    	
    	
	</body>
</html>