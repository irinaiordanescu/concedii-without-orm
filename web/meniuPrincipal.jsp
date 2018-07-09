<%-- 
    Document   : meniuPrincipal
    Created on : 28.06.2018, 09:30:42
    Author     : Irina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript" src="js/myfunctions.js"></script>
    </head>
    <body>
        <%
            String id = request.getParameter("id");
            session.setAttribute("sess_id", id);
            String username = request.getParameter("username");
            session.setAttribute("sess_username", username);
            String password = request.getParameter("password");
            session.setAttribute("sess_password", password);
        %>
    </body>
</html>
