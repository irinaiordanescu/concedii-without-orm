<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<% 
    if((String) session.getAttribute("id") == null){
       response.setStatus(HttpServletResponse.SC_FOUND);//302
       return;
//      response.setHeader("Location", "http://localhost:8080/Concedii/paginaLogin.html/");
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
    <body>
        <div id="sidebar" class="sidebar-offcanvas left-sidebar-nav">
            <header> 
                <div class="well sidebar-nav"> 
                    <div class="quick-profile-user-control">
                        <div class="quick-profile">
                            <menu>
                                <div id="quickProfileToggle">
                                    <a class="user-full-name">
                                        <%
                                            String name = (String) session.getAttribute("username");
                                            out.print(name);
                                        %> 
                                    </a>
                                    <img src="images/Avatar.png" alt="Avatar" class="center-block" style="border-radius:50%; height:185px; width:175px; margin-bottom:10px">
                                </div>
                            </menu>
                        </div>
                    </div>

                    <nav>
                        <ul class="level0" role="navigation">
                            <li class="nav-btn-members"> 
                                <a id="members_nav" class="active"  href="formularConcediu.jsp" target="frame_02">
                                    <i class="fa fa-use" data-original-title="" title=""> </i>
                                    FORMULAR CONCEDIU
                                    <span class="indicator"> </span>
                                </a>
                            </li>

                            <li>
                                <a id="time_off_nav" href="calendarPersonal.jsp" target="frame_02" >
                                    <i class="fa fa-calendar" data-original-title="" title=""> </i>
                                    CALENDAR PERSONAL
                                    <span class="indicator"> </span>
                                </a>
                            </li>

                            <li>
                                <a id="time_off_nav" href="calendarFirma.jsp" target="frame_02" >
                                    <i class="fa fa-calendar" data-original-title="" title=""> </i>
                                    CALENDAR FIRMA
                                    <span class="indicator"> </span>
                                </a>
                            </li>

                            <li class="nav-btn-members"> 
                                <a id="members_nav" class="active" href="membriiFirma.jsp" target="frame_02" >
                                    <i class="fa fa-use" data-original-title="" title=""> </i>
                                    MEMBRII FIRMA 
                                    <span class="indicator"> </span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </header>
        </div>
    </body>
</html>
