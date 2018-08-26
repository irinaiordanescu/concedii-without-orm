$(function () {
    $.ajax({
        type: "GET",
        url: "CalendarPersonal",
        data: "id=1",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        dataType: 'json',

        success: function (data, textStatus) {
            var zileOcupate = 0;
            data.concedii.forEach((f) => {
                var tipConcediu = f[2];
                var descriere = f[3];
                var deLaData = f[0];
                var panaLaData = f[1];
                zileOcupate += (new Date(panaLaData) - new Date(deLaData)) / (24*60*60*1000);

                var id = f[4];
                var element = `<tr>`;
                element += "<td>" + tipConcediu + "</td>";
                element += "<td>" + descriere + "</td>";
                element += "<td>" + deLaData + "</td>";
                element += "<td>" + panaLaData + "</td>";
                element += `"<td><a class="delete" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="" data-original-title="Delete" onClick="stergeFormularConcediu(` + id + `); ">&#xE872;</i></a></td></tr>`;
                $("#concedii").append(element);
            })
            $("#zileLibereOcupate").text("Zile libere ocupate: " + zileOcupate);
            $("#zileLibereRamase").text("Zile libere ramase: " + (35 - zileOcupate));
        }
        ,
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert("EROARE");
            console.log(textStatus);
        }
    });
})

function stergeFormularConcediu(id) {
    if (confirm("Sunteti sigur ca stergeti acest concediu?")) {
        $.ajax({
            type: "POST",
            url: "CalendarPersonal",
            data: "id=" + id + "&type=DELETE",
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            dataType: 'json',

            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log("error");
            }
        });
    } else {
        // Do nothing!
    }
    window.location.href = "administrareConcedii.jsp";
}