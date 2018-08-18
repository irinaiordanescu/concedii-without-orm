function doLogin() {
   // alert("dologin");
    var userName = $('#username').val();
    var password = $('#password').val();

    //alert("username " + userName + " password " + password);
    $.ajax({
        type: "POST",
        url: "Login",
        data: "username=" + userName + "&password=" + password,
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        dataType: 'json',
        
        success: function (data, textStatus) {
           var user = data;
           if(user.este_admin == "1"){
               window.location.href = "paginaAdmin.jsp";
               return;
           }
            window.location.href = "frame.jsp";
        }   
        ,
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert ("EROARE");
            console.log(textStatus);
        }
    });
}

function doReg() {
    //alert("doReg");
    var userName = $('#username').val();
    var password = $('#password').val();
    var checkpassword = $('#checkpassword').val();
    var prioritate = $('#prioritate').val();
    var departament = $('#departament').val();
    var tipangajat = $('#tipangajat').val()
    //alert("username " + userName + " password " + password + " checkpassword " + checkpassword + " tipangajat " + tipangajat);
    
    $.ajax({
        type: "post",
        url: "Register",
        data: "username=" + userName + "&password=" + password + "&checkpassword=" + checkpassword + "&departament=" + departament + "&tipangajat=" + tipangajat,
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        dataType: 'json',
        
        success: function (data, textStatus) {
            alert('success reg');
            
            window.location.href = "frame.html";
        }   
        ,
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            //alert("err");
            if(userName == "" || password == ""){
                alert("Eroare ---> username sau parola = null");
            } else if(password != checkpassword){
                alert("Eroare ---> parolele nu coincid");                               
            } else alert("Eroare ---> username-ul exista deja ==> alege alt username");
            console.log(textStatus);
        }
    });
}

function showReg() {
    var divelement = document.getElementById("checkpassword");
    if(divelement.style.display == "none") {
        divelement.style.display = "block";
    }
    else {
        divelement.style.display = "none";
    }
     
    var divelement2 = document.getElementById("departament");
    if(divelement2.style.display == "none") {
        divelement2.style.display = "block";
    }
    else {
        divelement2.style.display = "none";
    }
    
    var divelement3 = document.getElementById("tipangajat");
    if(divelement3.style.display == "none") {
        divelement3.style.display = "block";
    }
    else {
        divelement3.style.display = "none";
    }
}

$(document).keypress(function (e) { //dupa ce scriu in pagina si dau enter ma duce mai departe
    if (e.which == 13) {
        doLogin();
    }
});

