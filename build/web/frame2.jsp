<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<% 
    if((String) session.getAttribute("id") == null){
       response.setStatus(HttpServletResponse.SC_FOUND);//302
      response.setHeader("Location", "http://localhost:8080/Concedii/paginaLogin.html/");
    }
%>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <!--STYLESHEETS-->
        <link href="css/fabmin.css" rel="stylesheet" type="text/css" /> 
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css" /> 

        <!--SCRIPTS-->
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
        <script type="text/javascript" src="js/paginaLogin.js"></script>
        <script type="text/javascript" src="js/frame.js"></script>
    </head>
    <body class="container-login100" style="background-image: url('images/beach-sunset.jpg');">
        <div>  </div>
    </body>
</html>
