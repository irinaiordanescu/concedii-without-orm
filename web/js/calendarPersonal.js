$(function() {
    $('#calendar').fullCalendar({
        header: {
            left: '',
            center: 'title',
            right: 'today,month,agendaWeek,prev,next'
        },
        height: 650,
        defaultView: 'month'
    });

    $.ajax({
        type: "GET",
        url: "CalendarPersonal",
        data: "id=1",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        dataType: 'json',

        success: function (data, textStatus) {
            populeazaCalendar(data);
        }
        ,
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert("EROARE");
            console.log(textStatus);
        }
    });

    function populeazaCalendar(data) {
        console.log(data);
        data.concedii.forEach((c) => {
            $('#calendar').fullCalendar('renderEvent', {title: 'Concediu', start: c[0], end: c[1]}, true);
        });
    }  
});
