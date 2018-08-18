$(function () {
    $.ajax({
        type: "GET",
        url: "TipAngajat",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        dataType: 'json',

        success: function (data, textStatus) {
            data.tipuriAngajati.forEach((e) => {
                $("#mySelect").append("<option>" + e + "</option>");
            });
        }
        ,
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert("EROARE");
            console.log(textStatus);
        }
    });

});

function afisareAngajati() {
    var tipAngajat = $('#mySelect').val();
    console.log(tipAngajat);
    $.ajax({
        type: "POST",
        url: "AfisareTipAngajat",
        data: "tipAngajat=" + tipAngajat,
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
}

function adaugaMembriiInPagina(data) {
    var elementAdaugat = "<ul class='list-group'>";
    data.usernames.forEach((name) => {
        elementAdaugat += "<li class='list-group-item'>" + name + "</li>";
    });
    elementAdaugat += "</ul>";
    
    $("#membrii").empty().append(elementAdaugat);
}