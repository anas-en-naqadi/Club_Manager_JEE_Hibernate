<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.example.clubManager.models.Evenement" %>
<html>
	<head>
	    <title>Évenements</title>
	    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	</head>
	<body>
	    <div class="container-fluid">
	        <div class="row">
	            <%@ include file="adminSidebar.jsp" %>
	            <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
	                <h2 class="mt-4">Évenements</h2>
	                <div class="card-columns">
	                    <% 
	                        List<Evenement> evenements = (List<Evenement>) request.getAttribute("evenements");
	                        if (evenements != null) {
	                            for (Evenement even : evenements) {
	                    %>
	                    <div class="card">
	                        <img src="" class="card-img-top" alt="<%= even.getNom() %>">
	                        <div class="card-body">
	                            <h5 class="card-title">
	                                <a href="eventDetails.jsp?id=<%= even.getIdEvenement() %>"><%= even.getNom() %></a>
	                            </h5>
	                            <p class="card-text">Participants: <%= even.getParticipants().size() %></p>
	                            <a href="editEvent.jsp?id=<%= even.getIdEvenement() %>" class="btn btn-primary btn-sm">Edit</a>
	                            <a href="deleteEvent.jsp?id=<%= even.getIdEvenement() %>" class="btn btn-danger btn-sm">Remove</a>
	                        </div>
	                    </div>
	                    <% 
	                            }
	                        } else {
	                    %>
	                    <p>Aucun événement trouvé.</p>
	                    <% } %>
	                </div>
	                <a href="addEvent.jsp" class="btn btn-success btn-lg rounded-circle position-fixed" 
	                   style="bottom: 20px; right: 20px;">+</a>
	            </main>
	        </div>
	    </div>
	    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	</body>
</html>