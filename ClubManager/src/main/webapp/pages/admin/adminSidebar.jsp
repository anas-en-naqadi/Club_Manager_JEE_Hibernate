<!-- adminSidebar.jsp -->
<div class="col-md-3 col-lg-2 sidebar" style="height: auto !important;" >
    <div class="d-flex flex-column " >
        <div class="p-3">
            <h4 class="sidebar-header">Admin Panel</h4>
        </div>
        <nav class="nav flex-column gap-3" >
            <a class="nav-link ${param.page eq 'dashboard' ? 'active' : ''}" 
               href="${pageContext.request.contextPath}/admin/dashboard">
                <i class="bi bi-speedometer2"></i> Dashboard
            </a>
            <a class="nav-link ${param.page eq 'etudiants' ? 'active' : ''}" 
               href="${pageContext.request.contextPath}/admin/etudiants">
                <i class="bi bi-person"></i> Étudiants
            </a>
            <a class="nav-link ${param.page eq 'clubs' ? 'active' : ''}" 
               href="${pageContext.request.contextPath}/admin/clubs">
                <i class="bi bi-people"></i> Clubs
            </a>
            <a class="nav-link ${param.page eq 'events' ? 'active' : ''}" 
               href="${pageContext.request.contextPath}/admin/events">
                <i class="bi bi-calendar-event"></i> Événements
            </a>
        </nav>
    </div>
</div>
