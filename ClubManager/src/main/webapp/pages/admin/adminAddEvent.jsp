<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
	    <title>Create Event</title>
	    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
	    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
	    
	    
	    <style>
	        .sidebar {
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
	        /* Flex layout for cards */
	        .card-columns {
	            display: flex;
	            flex-wrap: wrap;
	            gap: 20px;
	        }
	        .card {
	            width: calc(33% - 20px);

	        }
	        .club_img{
	        	width: 100%;
	        	height: 200px;
	        }
	        .club_name{
	            transition: all ease-in-out 0.2s;
	        }
	        .club_name:hover{
	            color: brown;
	        }
	        .club-card {
	            display: flex;
	            flex-direction: column;
	            justify-content: center;
	            align-items: start;
	            gap: 15px;
				padding: 10px;
				position: relative;
				height: 530px;
	        }

	        .club_actions_cont{
	            position: absolute;
	            bottom: 10px;
	            display: flex;
	            justify-content: space-between;
	            align-items: center;
				width: 90%;



	        }
		    .alert-fixed {
		        position: fixed;
		        bottom: 20px;
		        left: 20px;
		        z-index: 1000;
		        width: 300px;
		        margin: 0;
		        box-shadow: 0 2px 10px rgba(0,0,0,0.1);
		    }
		    
		    main {
		        margin-bottom: 100px;
		        height: 500vh;
		    }
	    </style>	    
	    
	    
	    
	    
	    
	    
	    
	    
	</head>
	<body>
	
	
	<div class="container-fluid">
	    <div class="row">
			<%@ include file="adminSidebar.jsp" %>
			
			<main class="col-md-9 col-lg-10 px-4">
			    <h2 class="my-4">Create New Event</h2>
			    
			    <form action="${pageContext.request.contextPath}/admin/addEvent" method="post" enctype="multipart/form-data">
			        <div class="mb-3">
			            <label class="form-label">Event Name:</label>
			            <input type="text" name="nom" class="form-control" required>
			        </div>
			        
			        <div class="mb-3">
			            <label class="form-label">Description:</label>
			            <textarea name="description" class="form-control" rows="3"></textarea>
			        </div>
			        
			        
			        <div class="mb-3">
						 <label for="image" class="form-label">Image d'evenement</label>
						 <input type="file" class="form-control" id="image" name="image" accept="image/*">
					</div>
					
					
			        <div class="row">
			            <div class="col-md-6 mb-3">
			                <label class="form-label">Date & Time:</label>
			                <input type="datetime-local" name="date" class="form-control" required>
			            </div>
			            <div class="col-md-6 mb-3">
			                <label class="form-label">Location:</label>
			                <input type="text" name="lieu" class="form-control" required>
			            </div>
			        </div>
			        
			        <div class="mb-3">
			            <label class="form-label">Club:</label>
			            <select name="clubId" class="form-select" required>
			                <c:forEach items="${clubs}" var="club">
			                    <option value="${club.idClub}" 
			                        ${club.idClub == preselectedClubId ? 'selected' : ''}>
			                        ${club.nom}
			                    </option>
			                </c:forEach>
			            </select>
			        </div>
			        
			        <button type="submit" class="btn btn-primary">Create Event</button>
			        <a href="${pageContext.request.contextPath}/admin/evenements" class="btn btn-secondary">Cancel</a>
			    </form>
			</main>	    
	    
	    
	    </div>	
	
	
	
	</div>

	
	
	
	
	
		
		
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	</body>
</html>