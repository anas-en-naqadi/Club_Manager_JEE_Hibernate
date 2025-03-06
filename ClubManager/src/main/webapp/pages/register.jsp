<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
	
	                            <c:if test="${not empty param.error}">
	                                <div class="alert alert-danger">
	                                    ${param.error}
	                                </div>
	                            </c:if>
	
	                            <form id="registerForm" method="POST" action="${pageContext.request.contextPath}/register">
	                                <!-- Full Name -->
	                                <div class="mb-3">
	                                    <label for="nomComplet" class="form-label">Nom Complet</label>
	                                    <input type="text" class="form-control" id="nomComplet" name="nomComplet" 
	                                           value="${param.nomComplet}" required>
	                                </div>
	
	                                <!-- CNE -->
	                                <div class="mb-3">
	                                    <label for="cne" class="form-label">CNE</label>
	                                    <input type="text" class="form-control" id="cne" name="cne" 
	                                           value="${param.cne}" required>
	                                </div>
	
	                                <!-- Faculty -->
	                                <div class="mb-3">
	                                    <label for="faculte" class="form-label">Faculté</label>
	                                    <select class="form-select" id="faculte" name="faculte" required>
	                                        <option value="">Choisir une faculté</option>
	                                        <option value="Faculté des Sciences" ${param.faculte == 'Faculté des Sciences' ? 'selected' : ''}>Faculté des Sciences</option>
	                                        <option value="Faculté des Lettres" ${param.faculte == 'Faculté des Lettres' ? 'selected' : ''}>Faculté des Lettres</option>
	                                        <option value="Faculté de Droit" ${param.faculte == 'Faculté de Droit' ? 'selected' : ''}>Faculté de Droit</option>
	                                    </select>
	                                </div>
	
	                                <!-- Fillière -->
	                                <div class="mb-3">
	                                    <label for="filliere" class="form-label">Fillière</label>
	                                    <input type="text" class="form-control" id="filliere" name="filliere"
	                                           value="${param.filliere}" required>
	                                </div>
	
	                                <!-- Email -->
	                                <div class="mb-3">
	                                    <label for="email" class="form-label">Email universitaire</label>
	                                    <input type="email" class="form-control" id="email" name="email"
	                                           value="${param.email}" required>
	                                    <div class="form-text">Exemple: cin.cne@ucd.ac.ma</div>
	                                </div>
	
	                                <!-- Password Fields -->
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
	
	        // Password confirmation validation
	        document.getElementById('registerForm').addEventListener('submit', function(e) {
	            const password = document.getElementById('password').value;
	            const confirmPassword = document.getElementById('confirmPassword').value;
	            
	            if (password !== confirmPassword) {
	                e.preventDefault();
	                alert('Les mots de passe ne correspondent pas!');
	            }
	        });
	    </script>
	</body>
</html>