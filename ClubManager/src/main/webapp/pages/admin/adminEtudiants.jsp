<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.example.clubManager.models.Etudiant" %>
<html>
	<head>
	    <title>Etudiants</title>
	    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	</head>
	<body>
	    <div class="container-fluid">
	        <div class="row">
	            <%@ include file="adminSidebar.jsp" %>
	            <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
	                <h2 class="mt-4">Etudiants</h2>
	                <table class="table table-striped table-responsive-sm">
	                    <thead class="thead-dark">
	                        <tr>
	                            <th>ID</th>
	                            <th>Nom Complet</th>
	                            <th>CNE</th>
	                        </tr>
	                    </thead>
	                    <tbody>
	                        <% 
	                            List<Etudiant> etudiants = (List<Etudiant>) request.getAttribute("etudiants");
	                            if (etudiants != null) {
	                                for (Etudiant etudiant : etudiants) {
	                        %>
	                        <tr>
	                            <td><%= etudiant.getIdEtudiant() %></td>
	                            <td><%= etudiant.getNomComplet() %></td>
	                            <td><%= etudiant.getCne() %></td>
	                        </tr>
	                        <% 
	                                }
	                            } else {
	                        %>
	                        <tr>
	                            <td colspan="4">Aucun étudiant trouvé.</td>
	                        </tr>
	                        <% } %>
	                    </tbody>
	                </table>
	            </main>
	        </div>
	    </div>
	    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	</body>
</html>