<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
	    <title>401 Unauthorized</title>
	    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	    <style>
	        body {
	            background-color: #f8f9fa;
	            display: flex;
	            justify-content: center;
	            align-items: center;
	            height: 100vh;
	            margin: 0;
	        }
	        .error-container {
	            text-align: center;
	            padding: 20px;
	            border: 1px solid #dee2e6;
	            border-radius: 5px;
	            background-color: #ffffff;
	            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	        }
	        h1 {
	            font-size: 2.5rem;
	            color: #dc3545;
	        }
	        p {
	            font-size: 1.2rem;
	            color: #6c757d;
	        }
	        a {
	            margin-top: 20px;
	        }
	    </style>
	</head>
	<body>
	    <div class="error-container">
	        <h1>401 Unauthorized</h1>
	        <p>Sorry, you do not have permission to access this page.</p>
	        <p>Please log in with an account that has the necessary permissions or contact support for assistance.</p>
	        <a href="${pageContext.request.contextPath}/login" class="btn btn-primary">Go to Login</a>
	        <a href="${pageContext.request.contextPath}/home" class="btn btn-secondary">Go to Home</a>
	    </div>
	</body>
</html>