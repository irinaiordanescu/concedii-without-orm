/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
           //alert('success');
            
            console.log(data);
            console.log(data.id);
            console.log(data.username);
            console.log(data.password);
            
            var id = data.id;
            var user = data.user;
            var password = data.password;

            window.location.href = "paginaPrincipala.html";
        }   
        ,
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert ("EROARE");
            console.log(textStatus); 
        }
    });
}