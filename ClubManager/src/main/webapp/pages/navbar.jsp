<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/home">UniClubs</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link ${currentPage eq 'clubes' ? 'active' : ''}" 
                       href="${pageContext.request.contextPath}/clubes">All clubs</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link ${currentPage eq 'myClubs' ? 'active' : ''}" 
                       href="${pageContext.request.contextPath}/my_clubs">Mes Clubs</a>
                </li>
                
                <c:if test="${empty sessionScope.user}">
	                <li class="nav-item">
	                    <a class="nav-link ${currentPage eq 'login' ? 'active' : ''}" 
	                       href="${pageContext.request.contextPath}/login">Login</a>
	                </li>
                </c:if>
                
				<c:if test="${not empty sessionScope.user}">
				    <li class="nav-item">
				        <a class="nav-link" href="${pageContext.request.contextPath}/logout">Déconnexion</a>
				    </li>
				</c:if>
            </ul>
        </div>
    </div>
</nav>