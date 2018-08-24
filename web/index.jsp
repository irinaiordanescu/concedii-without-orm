<!DOCTYPE html>

<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!--STYLESHEETS-->
        <link href="css/style.css" rel="stylesheet" type="text/css" />
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css" />

        <!--SCRIPTS-->
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
        <script type="text/javascript" src="js/paginaLogin.js"></script>
    </head>
    
    <body>
        <div id="wrapper">
            <!--SLIDE-IN ICONS-->
            <div class="user-icon"></div>
            <div class="pass-icon"></div>
            <!--END SLIDE-IN ICONS-->

            <!--LOGIN FORM-->
            <form id="login-form" name="login-form" class="login-form" action="" method="post">
                <!--HEADER-->
                <div class="header">
                    <!--TITLE--><center><h1><img alt="" src="images/logo.png"></h1></center><!--END TITLE-->
                    <!--DESCRIPTION--><center><span>Login</span></center></div>
                <!--END HEADER-->

                <!--CONTENT-->
                <div class="content">                   
                        <input id="username" type="text" placeholder="Username/Email" class="form-control " value="" onfocus="this.value = ''" size="30" />
                        <input id="password" type="password" placeholder="Password" class="form-control" onfocus="this.value = ''" style="margin-top:5px" size="30" />
                        <input type="button" name="login" value="Login" class="btn btn-primary" onclick="doLogin()" style="margin-top:20px"/>
                </div>
                <!--END CONTENT-->

            </form>
            <!--END LOGIN FORM-->

        </div>
        <!--END WRAPPER-->
        <!--GRADIENT--><!--END GRADIENT-->
    </body>
</html>
