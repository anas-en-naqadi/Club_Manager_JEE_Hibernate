<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="fr">
	<head>
	    <meta charset="UTF-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <title>Inscription - UniClubs</title>
	    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
	    <style>
	        .register-section {
	            background: linear-gradient(rgba(0,0,0,0.5), rgba(0,0,0,0.5));
	            min-height: 100vh;
	            display: flex;
	            align-items: center;
	
	        }
	        .register-card {
	            backdrop-filter: blur(10px);
	            background: rgba(255, 255, 255, 0.9);
	        }
	        .register-container {
	            margin-top: 40px;
	            margin-bottom: 40px;
	
	        }
	        .password-rules {
	            font-size: 0.9rem;
	            color: #666;
	        }
	        .password-rules ul {
	            list-style: none;
	            padding-left: 1.5rem;
	            margin-bottom: 0;
	        }
	        .password-rules li {
	            position: relative;
	            margin-bottom: 0.3rem;
	            color: #dc3545;
	            transition: all 0.3s ease;
	        }
	        .password-rules li.valid {
	            color: #28a745;
	        }
	        .password-rules li::before {
	            content: "✗";
	            position: absolute;
	            left: -1.5rem;
	        }
	        .password-rules li.valid::before {
	            content: "✓";
	        }
	    </style>
	</head>
	<body>
		<!-- Navbar dynamique -->
	    <%@ include file="navbar.jsp" %>
	
	    <div class="register-section">
	        <div class="container register-container">
	            <div class="row justify-content-center">
	                <div class="col-md-8 col-lg-6">
	                    <div class="register-card card shadow">
	                        <div class="card-body p-5">
	                            <h2 class="card-title text-center mb-4">Créer un compte</h2>
	                            

								<!-- <c:if test="${not empty errors}">
								    <div class="alert alert-danger">
								        <ul class="mb-0">
								            <c:forEach items="${errors}" var="error">
								                <li>${error.value}</li>
								            </c:forEach>
								        </ul>
								    </div>
								</c:if> -->
	
	                            <form id="registerForm" method="POST" action="${pageContext.request.contextPath}/register">
	                                <div class="row mb-3">
	                                    <div class="col-md-6">
	                                        <label for="firstName" class="form-label">Nom Complet</label>
	                                        <input type="text" class="form-control" id="firstName" name="firstName" 
	                                               value="${formData.full_name}" required>
	                                    </div>
	                                   
	                                </div>
	                                
	                                <div class="mb-3">
	                                    <label for="email" class="form-label">Email universitaire</label>
	                                    <input type="email" class="form-control" id="email" name="email"
	                                           value="${formData.email}" required>
	                                    <div class="form-text">Exemple: cin.cne@ucd.ac.ma</div>
	                                </div>
	
	                                <div class="mb-3">
	                                    <label for="password" class="form-label">Mot de passe</label>
	                                    <input type="password" class="form-control" id="password" name="password" required>
	                                    <div class="password-rules mt-2">
	                                        Le mot de passe doit contenir :
	                                        <ul>
	                                            <li id="length-requirement">Au moins 8 caractères</li>
	                                            <li id="uppercase-requirement">Une majuscule</li>
	                                            <li id="number-requirement">Un chiffre</li>
	                                        </ul>
	                                    </div>
	                                </div>
	
	                                <div class="mb-4">
	                                    <label for="confirmPassword" class="form-label">Confirmer le mot de passe</label>
	                                    <input type="password" class="form-control" id="confirmPassword" 
	                                           name="confirmPassword" required>
	                                </div>
	
	                                <button type="submit" class="btn btn-primary w-100">S'inscrire</button>
	                            </form>
	                            <div class="text-center mt-4">
	                                <p class="mb-0">Déjà inscrit ? 
	                                    <a href="${pageContext.request.contextPath}/login" class="text-decoration-none">
	                                        Se connecter
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
	    <script>
	        function updatePasswordRequirements(password) {
				console.log("dddsdqdqs")
	            const requirements = {
	                length: password.length >= 8,
	                uppercase: /[A-Z]/.test(password),
	                number: /\d/.test(password)
	            };
	
	            document.getElementById('length-requirement').classList.toggle('valid', requirements.length);
	            document.getElementById('uppercase-requirement').classList.toggle('valid', requirements.uppercase);
	            document.getElementById('number-requirement').classList.toggle('valid', requirements.number);
	        }
	
	        document.getElementById('password').addEventListener('input', function(e) {
	            updatePasswordRequirements(e.target.value);
	        });
	        
	    </script>
	</body>
</html>