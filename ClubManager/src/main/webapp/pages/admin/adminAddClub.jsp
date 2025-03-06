<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.example.clubManager.models.Etudiant, com.example.clubManager.models.Club" %>
<!DOCTYPE html>
<html lang="fr">
	<head>
	    <meta charset="UTF-8">
	    <title>Ajouter un Club</title>
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
	    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
	    <style>
	        .container { 
	       		margin-top: 30px; 
	        
	        }
	        main{
	        	height: 100vh;
	        
	        }
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
	    </style>
	</head>
	<body>
	
	<div class="container-fluid">
	    <div class="row">
	    
	 		<%@ include file="adminSidebar.jsp" %>
	 		<main role="main" class="col-md-9 col-lg-10 px-4">
				<h2>Ajouter un nouveau club</h2>

				<form action="<%= request.getContextPath() %>/admin/addClub" method="post" enctype="multipart/form-data">
					<div class="mb-3">
						<label for="nom" class="form-label">Nom du Club</label>
						<input type="text" class="form-control" id="nom" name="nom" required>
					</div>
					
					
					<div class="mb-3">
						 <label for="image" class="form-label">Image du Club</label>
						 <input type="file" class="form-control" id="image" name="image" accept="image/*">
					</div>
					
					
					<div class="mb-3">
						  <label for="description" class="form-label">Description</label>
						   <textarea class="form-control" id="description" name="description" rows="4"></textarea>
					</div>

					<div class="mb-3">
						   <label for="presidentId" class="form-label">Président du Club</label>
						            <!-- Zone de recherche par CNE -->
						    <input type="text" class="form-control" id="searchCne" placeholder="Rechercher par CNE">
						            <!-- Liste déroulante des étudiants -->
						    <select class="form-select mt-2" id="presidentId" name="presidentId" required>
						    <option value="">Sélectionnez un étudiant</option>
						                <%
						                    List<Etudiant> etudiants = (List<Etudiant>) request.getAttribute("etudiants");
						                    if(etudiants != null) {
						                        for(Etudiant etu : etudiants) {
						                %>
						    <option value="<%= etu.getIdEtudiant() %>" data-cne="<%= etu.getCne() %>">
						                    <%= etu.getNomComplet() %> (CNE: <%= etu.getCne() %>)
						    </option>
						                <%
						                        }
						                    }
						                %>
						  </select>
					</div>
					
					<button type="submit" class="btn btn-primary">Ajouter le Club</button>
				</form>	 		
	 		
	 		</main>
		       
	    
	    
	    </div>
	</div>
	
	<!-- JavaScript for filtering the select options by CNE -->
	<script>
	    document.getElementById('searchCne').addEventListener('input', function() {
	        let filter = this.value.toLowerCase();
	        let select = document.getElementById('presidentId');
	        let options = select.options;
	        for(let i = 0; i < options.length; i++) {
	            let option = options[i];
	            let cne = option.getAttribute('data-cne');
	            if(cne && cne.toLowerCase().indexOf(filter) > -1) {
	                option.style.display = "";
	            } else if(i === 0) {
	                // Always show the first placeholder option
	                option.style.display = "";
	            } else {
	                option.style.display = "none";
	            }
	        }
	    });
	</script>
	
	<!-- Bootstrap JS Bundle -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	</body>
</html>
