<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="fr">
	<head>
	    <meta charset="UTF-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <title>Connexion - UniClubs</title>
	    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
	    <style>
	        .login-section {
	            background: linear-gradient(rgba(0,0,0,0.5), rgba(0,0,0,0.5)), 
	                        url('https://source.unsplash.com/random/1920x1080/?campus') center/cover;
	            min-height: 100vh;
	            display: flex;
	            align-items: center;
	        }
	        .login-card {
	            backdrop-filter: blur(10px);
	            background: rgba(255, 255, 255, 0.9);
	        }
	    </style>
	</head>
	<body>

		<!-- Navbar dynamique -->
	    <%@ include file="navbar.jsp" %>
	    
	    
	    <div class="login-section">
	        <div class="container">
	            <div class="row justify-content-center">
	                <div class="col-md-8 col-lg-6">
	                    <div class="login-card card shadow">
	                        <div class="card-body p-5">
	                            <h2 class="card-title text-center mb-4">Connexion</h2>
	                            
	                            
							    <!-- <c:if test="${not empty errorMessage}">
							        <div class="alert alert-danger">${errorMessage}</div>
							    </c:if> -->
	
	                            <form id="loginForm" method="POST" action="${pageContext.request.contextPath}/login">
	                                <div class="mb-3">
	                                    <label for="email" class="form-label">Email universitaire</label>
	                                    <input type="email" class="form-control" id="email" name="email" required>
	                                </div>
	                                <div class="mb-4">
	                                    <label for="password" class="form-label">Mot de passe</label>
	                                    <input type="password" class="form-control" id="password" name="password" required>
	                                </div>

	                                <button type="submit" class="btn btn-primary w-100">Se connecter</button>
	                            </form>
	                            
	                            <div class="text-center mt-4">
	                                <p class="mt-2">Pas encore membre ? 
	                                    <a href="./register" class="text-decoration-none">
	                                        Créer un compte
	                                    </a>
	                                </p>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	
	    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	</body>
</html>