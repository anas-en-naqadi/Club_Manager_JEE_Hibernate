<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.example.clubManager.models.Etudiant, com.example.clubManager.models.Club, java.util.Base64" %>
<%
    Club club = (Club) request.getAttribute("club");
    String base64Image = "";
    if (club.getImage() != null && club.getImage().length > 0) {
        base64Image = Base64.getEncoder().encodeToString(club.getImage());
    }
%>
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
	        	height: 200vh !important;
	        
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
	            <h2>Modifier le Club</h2>
	
	            <% if (club != null) { %>
	            <form action="<%= request.getContextPath() %>/admin/editClub" method="post" enctype="multipart/form-data">
	                <input type="hidden" name="idClub" value="<%= club.getIdClub() %>">
	                
	                <div class="mb-3">
	                    <label for="nom" class="form-label">Nom du Club</label>
	                    <input type="text" class="form-control" id="nom" name="nom" 
	                           value="<%= club.getNom() %>" required>
	                </div>
	
	                <div class="mb-3">
	                    <label class="form-label">Image actuelle</label>
	                    <% if (!base64Image.isEmpty()) { %>
	                        <img src="data:image/*;base64,<%= base64Image %>" 
	                             class="img-thumbnail mb-2" style="max-width: 200px; display: block;">
	                    <% } else { %>
	                        <p>Aucune image disponible</p>
	                    <% } %>
	                    <label for="image" class="form-label">Nouvelle image</label>
	                    <input type="file" class="form-control" id="image" name="image" accept="image/*">
	                </div>
	
	                <div class="mb-3">
	                    <label for="description" class="form-label">Description</label>
	                    <textarea class="form-control" id="description" name="description" rows="4">
	                        <%= club.getDescription() %>
	                    </textarea>
	                </div>
	
	                <div class="mb-3">
	                    <label for="presidentId" class="form-label">Président du Club</label>
	                    <input type="text" class="form-control" id="searchCne" placeholder="Rechercher par CNE">
	                    <select class="form-select mt-2" id="presidentId" name="presidentId" required>
	                        <option value="">Sélectionnez un étudiant</option>
	                        <%
	                            List<Etudiant> etudiants = (List<Etudiant>) request.getAttribute("etudiants");
	                            if (etudiants != null) {
	                                for (Etudiant etu : etudiants) {
	                        %>
	                        <option value="<%= etu.getIdEtudiant() %>" 
	                                data-cne="<%= etu.getCne() %>"
	                                <%= (etu.getIdEtudiant() == club.getPresident().getIdEtudiant()) ? "selected" : "" %>>
	                            <%= etu.getNomComplet() %> (CNE: <%= etu.getCne() %>)
	                        </option>
	                        <%
	                                }
	                            }
	                        %>
	                    </select>
	                </div>
	
	                <button type="submit" class="btn btn-primary">Enregistrer les modifications</button>
	            </form>
	            <% } else { %>
	                <div class="alert alert-danger">Club non trouvé</div>
	            <% } %>
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
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>	</body>
</html>