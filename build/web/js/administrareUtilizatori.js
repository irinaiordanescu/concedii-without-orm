$(function () {

    // Activate tooltip
    $('[data-toggle="tooltip"]').tooltip();

    // Select/Deselect checkboxes
    var checkbox = $('table tbody input[type="checkbox"]');
    $("#selectAll").click(function () {
        if (this.checked) {
            checkbox.each(function () {
                this.checked = true;
            });
        } else {
            checkbox.each(function () {
                this.checked = false;
            });
        }
    });
    checkbox.click(function () {
        if (!this.checked) {
            $("#selectAll").prop("checked", false);
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
            element += "<td>" + utilizator.prioritate + "</td>";
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
             <a href="#editEmployeeModal" class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="" data-original-title="Edit">&#xE254;</i></a>
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

    var prioritate = $("#adaugaPrioritateUtilizator").val();
    var esteAdmin = $("#adaugaAdminUtilizator").is(":checked");
    var tipAngajat = $("#tipangajat").val();
    var departament = $("#departament").val();

    $.ajax({
        type: "POST",
        url: "Utilizatori",
        data: "username=" + username + "&password=" + parola + "&prioritate=" + prioritate + "&departament=" + departament + "&tipAngajat=" + tipAngajat + "&esteAdmin=" + esteAdmin,
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
            data: "id=" + id +"&type=DELETE" ,
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            dataType: 'json',

            success: function (data, textStatus) {
                window.location.href = "administrareUtilizatori.jsp";
            }
            ,
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log(errorThrown);
            }
        });
    } else {
        // Do nothing!
    }

}