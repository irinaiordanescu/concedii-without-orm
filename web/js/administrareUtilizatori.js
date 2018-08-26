$(function () {
    //Populeaza lista de selectie cu tipuri de angajat
    $.ajax({
        type: "GET",
        url: "TipAngajat",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        dataType: 'json',
        success: function (data) {
            data.tipuriAngajati.forEach((tipAngajat, index) => {
                $("#tipangajat").append("<option value=" + (index + 1) + ">" + tipAngajat + "</option>");
                $("#editeazaTipangajat").append("<option value=" + (index + 1) + ">" + tipAngajat + "</option>");
            });
        }
    });

    //Populeaza lista de selectie cu departamente
    $.ajax({
        type: "GET",
        url: "Departament",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        dataType: 'json',
        success: function (data) {
            console.log("Data" + data);
            data.departamente.forEach((departament, index) => {
                $("#departament").append("<option value=" + (index + 1) + ">" + departament + "</option>");
                $("#editeazaDepartament").append("<option value=" + (index + 1) + ">" + departament + "</option>");
            });
        }
    });

    $.ajax({
        type: "GET",
        url: "Utilizatori",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        dataType: 'json',

        success: function (data, textStatus) {
            //alert('success');
            adaugaMembriiInPagina(data);
        }
        ,
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert("EROARE");
            console.log(textStatus);
        }
    }
    );

    function adaugaMembriiInPagina(data) {

        data.utilizatori.forEach((utilizator) => {
            var element =
                    `<tr>`;

            element += "<td>" + utilizator.username + "</td>";
            element += "<td>" + utilizator.tip_angajat + "</td>";
            element += "<td>" + utilizator.departament + "</td>";
            var esteAdmin;
            if (utilizator.este_admin == "1") {
                esteAdmin = "Da";
            } else {
                esteAdmin = "Nu";
            }
            element += "<td>" + esteAdmin + "</td>";
            element +=
                    ` <td>
             <a class="edit" href="#editEmployeeModal" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="" data-original-title="Edit" onClick="creazaPaginaDeEditareUtilizator(` + utilizator.id + `);">&#xE254;</i></a>
             <a class="delete" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="" data-original-title="Delete" onClick="stergeUtilizator(` + utilizator.id + `);">&#xE872;</i></a>
             </td>
             </tr> `
            $("#angajati").append(element);
        });
    }
});


function adaugaUtilizator() {
    var username = $("#adaugaUsernameUtilizator").val();
    var parola = $("#adaugaParolaUtilizator").val();
    var verificareParola = $("#adaugaVerificareParolaUtilizator").val();

    if (parola != verificareParola) {
        alert("Parola de verificare difera de cea de introducere");
        return;
    }

    var esteAdmin = $("#adaugaAdminUtilizator").is(":checked");
    var tipAngajat = $("#tipangajat").val();
    var departament = $("#departament").val();

    $.ajax({
        type: "POST",
        url: "Utilizatori",
        data: "username=" + username + "&password=" + parola + "&departament=" + departament + "&tipAngajat=" + tipAngajat + "&esteAdmin=" + esteAdmin,
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        dataType: 'json',

        success: function (data, textStatus) {
            alert('success reg');
            window.location.href = "administrareUtilizatori.jsp";
        }
        ,
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(errorThrown);
        }
    });
}

function stergeUtilizator(id) {
    if (confirm('Sigur vrei sa stergi utiliztorul din baza de date?')) {
        $.ajax({
            type: "POST",
            url: "Utilizatori",
            data: "id=" + id + "&type=DELETE",
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            dataType: 'json',

            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log(errorThrown);
            }
        });
    } else {
        // Do nothing!
    }
    window.location.href = "administrareUtilizatori.jsp";
}

function creazaPaginaDeEditareUtilizator(id) {
    $.ajax({
        type: "GET",
        url: "Utilizatori",
        data: "id=" + id,
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        dataType: 'json',
        success: function (data) {
            var username = data.utilizator.username;
            var admin = data.utilizator.este_admin == 1 ? true : false;
            var tipAngajat = data.utilizator.tip_angajat;
            var valoareTipAngajat = $("option").filter(function () {
                return $(this).text() === tipAngajat;
            }).first().attr("value");
            var departament = data.utilizator.departament;
            var valoareDepartament = $("option").filter(function () {
                return $(this).text() === departament;
            }).first().attr("value");

            $("#editeazaUsername").val(username);
            $("#editeazaAdmin").prop("checked", admin);
            $("#editeazaTipangajat").val(valoareTipAngajat);
            $("#editeazaDepartament").val(valoareDepartament);
            $('#formEditare').append('<input type="hidden" id="idAngajat" value="'+id+'" />');
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(errorThrown);
        }
    });
//    $("#editEmployeeModal").click();
//    href="#editEmployeeModal"
}

function editeazaUtilizatorul() {
    var username = $("#editeazaUsername").val();
    var esteAdmin = $("#editeazaAdmin").is(":checked");
    var tipAngajat = $("#editeazaTipangajat").val();
    var departament = $("#editeazaDepartament").val();
    var id = $("#idAngajat").val();

    $.ajax({
        type: "POST",
        url: "Utilizatori",
        data: "username=" + username + "&departament=" + departament + "&tipAngajat=" + tipAngajat + "&esteAdmin=" + esteAdmin + "&type=EDIT&id=" + id,
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        dataType: 'json'
        ,
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(errorThrown);
        }
    });

    window.location.href = "administrareUtilizatori.jsp";
}

function redirectioneazaPePaginaDeAdmin(){
    window.location.href = "paginaAdmin.jsp";
}