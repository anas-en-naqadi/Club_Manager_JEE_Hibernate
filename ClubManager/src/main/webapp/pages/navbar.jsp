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
                <li class="nav-item">
                    <a class="nav-link ${currentPage eq 'login' ? 'active' : ''}" 
                       href="${pageContext.request.contextPath}/login">Login</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" 
                       href="${pageContext.request.contextPath}/logout" 
                       onclick="handleLogout()">Logout</a>
                </li>
            </ul>
        </div>
    </div>
</nav>