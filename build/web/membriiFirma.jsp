<html>
    <head>
        <title>Membrii firma</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <!--STYLESHEETS-->
        <link href="css/fabmin.css" rel="stylesheet" type="text/css" /> 
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css" /> 

        <!--SCRIPTS-->
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
        <script type="text/javascript" src="js/membriiFirma.js"></script>
    </head>
    
    <body class='container-login100' style="background-image: url('images/beach-sunset.jpg');">
        <div class="frame-margine">
            <br>
            Selectati tipul de angajat:
            <select color="#6e6e6e" title = " Selectati tipul de angajat: " id="mySelect">
                <option></option>
                <option>toti angajatii</option>
            </select>
            <br>
            <br>
                <button id="submit_button" class="btn btn btn-primary" name="button" type="submit" style="background-color: #b96c88" onclick="afisareAngajati()"> Afiseaza </button>              
        </div>
        <div id="membrii" class="membrii">
            
        </div>

</html>
