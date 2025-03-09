<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="fr">
	<head>
	    <meta charset="UTF-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <title>Etudiants - Admin</title>
	    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
	    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
	    <style>
	    
		    .alert-fixed {
		        position: fixed;
		        bottom: 20px;
		        left: 20px;
		        z-index: 1000;
		        width: 300px;
		        margin: 0;
		        box-shadow: 0 2px 10px rgba(0,0,0,0.1);
		    }	    
	        .club-list {
	            list-style: none;
	            padding-left: 0;
	        }
	        .club-item {
	            margin-bottom: 5px;
	        }
	        .president-badge {
	            font-size: 0.8em;
	            margin-left: 5px;
	        }
	        
	        
	        .container { 
	       		margin-top: 30px; 
	        
	        }
	        main{
	        	height: 100vh;
	        	margin-bottom: 100px;
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
					<%-- Message Display Section --%>
					<%
					    String message = request.getParameter("message");
					    String success = request.getParameter("success");
					    String error = request.getParameter("error");
					    
					    if (message != null && !message.isEmpty()) {
					        String alertType = "primary"; // default type
					        
					        // Check error first for highest priority
					        if (error != null && Boolean.parseBoolean(error)) {
					            alertType = "danger";
					        } 
					        // Then check success status
					        else if (success != null) {
					            alertType = Boolean.parseBoolean(success) ? "success" : "danger";
					        }
					%>
					<div class="alert alert-<%= alertType %> alert-dismissible fade show alert-fixed" role="alert">
					    <%= message %>
					    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
					</div>
					<%
					    }
					%>				
				
				
				
					<h2 class="mb-4">Etudiants Management</h2>

			        <div class="table-responsive">
			            <table class="table table-striped table-hover">
			                <thead class="table-dark">
			                    <tr>
			                        <th>CNE</th>
			                        <th>Name</th>
			                        <th>Faculty</th>
			                        <th>Clubs</th>
			                        <th>Actions</th>
			                    </tr>
			                </thead>
			                <tbody>
			                    <c:forEach items="${students}" var="student">
			                        <tr>
			                            <td>${student.cne}</td>
			                            <td>${student.nomComplet}</td>
			                            <td>${student.faculte}</td>
			                            <td>
			                                <ul class="club-list">
												<c:forEach items="${student.memberships}" var="membership">	                                        <li class="club-item">
			                                            <a href="${pageContext.request.contextPath}/club?id=${membership.club.idClub}" 
			                                               class="text-decoration-none">
			                                                ${membership.club.nom}
			                                            </a>
														<c:if test="${membership.club.president.idEtudiant eq student.idEtudiant}">
														    <span class="badge bg-warning president-badge">President</span>
														</c:if>
			                                        </li>
			                                    </c:forEach>
			                                </ul>
			                            </td>
			                            <td>
			                                <form method="post" action="${pageContext.request.contextPath}/admin/etudiants"
			                                      onsubmit="return confirm('Are you sure? This will delete all clubs where this student is president!')">
			                                    <input type="hidden" name="studentId" value="${student.idEtudiant}">
			                                    <button type="submit" class="btn btn-danger btn-sm">
			                                        Remove Student
			                                    </button>
			                                </form>
			                            </td>
			                        </tr>
			                    </c:forEach>
			                </tbody>
			            </table>
			        </div>
				
				</main>
		
			</div>
		
		
		
		
		</div>
	    		

	

	
	    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	</body>
</html>