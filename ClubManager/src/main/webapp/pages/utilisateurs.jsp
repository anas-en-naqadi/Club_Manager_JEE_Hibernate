<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Liste des utilisateurs</title>
</head>
<body>
    <h1>Liste des utilisateurs</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Email</th>
            <th>Rôle</th>
        </tr>
        <c:forEach var="utilisateur" items="${utilisateurs}">
            <tr>
                <td>${utilisateur.idUtilisateur}</td>
                <td>${utilisateur.email}</td>
                <td>${utilisateur.role}</td>
            </tr>
        </c:forEach>
    </table>
    <a href="ajouterUtilisateur.jsp">Ajouter un utilisateur</a>
</body>
</html>