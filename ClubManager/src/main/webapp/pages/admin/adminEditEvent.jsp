<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
	    <title>Modifier Événement</title>
	    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
	</head>
	<body>
	<%@ include file="adminSidebar.jsp" %>
	
	<main class="col-md-9 col-lg-10 px-4">
	    <h2 class="my-4">Modifier Événement</h2>
	    
	    <form action="${pageContext.request.contextPath}/admin/updateEvent" method="post">
	        <input type="hidden" name="id" value="${event.idEvenement}">
	        
	        <div class="mb-3">
	            <label class="form-label">Nom:</label>
	            <input type="text" name="nom" class="form-control" value="${event.nom}" required>
	        </div>
	        
	        <div class="mb-3">
	            <label class="form-label">Description:</label>
	            <textarea name="description" class="form-control" rows="3">${event.description}</textarea>
	        </div>
	        
	        <div class="row">
	            <div class="col-md-6 mb-3">
	                <label class="form-label">Date:</label>
	                <input type="datetime-local" name="date" class="form-control" 
	                       value="<fmt:formatDate value="${event.dateEvenement}" pattern="yyyy-MM-dd'T'HH:mm" />" required>
	            </div>
	            <div class="col-md-6 mb-3">
	                <label class="form-label">Lieu:</label>
	                <input type="text" name="lieu" class="form-control" value="${event.lieu}" required>
	            </div>
	        </div>
	        
	        <div class="mb-3">
	            <label class="form-label">Club:</label>
	            <select name="clubId" class="form-select" required>
	                <c:forEach items="${clubs}" var="club">
	                    <option value="${club.idClub}" ${club.idClub == event.club.idClub ? 'selected' : ''}>
	                        ${club.nom}
	                    </option>
	                </c:forEach>
	            </select>
	        </div>
	        
	        <button type="submit" class="btn btn-primary">Mettre à jour</button>
	        <a href="${pageContext.request.contextPath}/admin/evenements" class="btn btn-secondary">Annuler</a>
	    </form>
	</main>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	</body>
</html>