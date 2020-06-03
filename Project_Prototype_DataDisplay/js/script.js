var baseUrl = "https://tcm-prototype-apirest.herokuapp.com/data/";
var url = baseUrl;
var urlId = "https://tcm-prototype-apirest.herokuapp.com/data/id";

function ready() {
    console.log("funciona el ready");
    getAvailableIds();
}

document.addEventListener("DOMContentLoaded", ready);

function renderChart(data, labels, id, dataColor, maximum) {
    var ctx = document.getElementById(id).getContext('2d');
    var myChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: labels,
            datasets: [{
                label: 'Last 24 hours',
                data: data,
                backgroundColor: dataColor
            }]
        }, options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true,
                        max: maximum
                    }

                }]
            }
        }
    });
}

function getAvailableIds() {
    $.ajax({
        url: urlId,
        type: "GET",
        success: function (result) {
            loadIds(result);
        },
        error: function (error) {
            console.log("ERROR: no s'han pogut obtenir els ids amb l'ajax");
            console.log(error);
        }
    })
}

function loadIds(result) {
    $("#divInput").show();
    $("#wait").hide();
    var obj = JSON.parse(result);

    document.getElementById("ids").innerHTML = '';//aquest elimina les opcions de l'html

    if (obj) {
        for (var i = 0; i < obj.length; i++) {
            $("#ids").append("<option value=" + obj[i] + ">");
        }
    }

    document.getElementById("renderBtn").addEventListener("click", prepareChart);
    document.getElementById("idsInput").addEventListener("click", deleteDataFromInput);
}

function prepareChart() {
    $("#temperatureDiv").hide();
    $("#humidityDiv").hide();
    getIdToUrl();
    getData();
    getAvailableIds();
}

/*
* Funcio per llegir de quina raspberry volem llegir la temperatura amb el input
* */
function getIdToUrl() {
    var id = document.getElementById("idsInput").value;
    url = baseUrl + id;
}

/*
* Ajax per obtenir les dades de la base de dades
* */
function getData() {
    $.ajax({
        url: url,
        type: "GET",
        success: function (result) {
            createCharts(result);
        },
        error: function (error) {
            console.log("ERROR: no s'han pogut obtenir les dades amb l'ajax");
            console.log(error);
        }
    })
}

/*
* Passem amb l'ajax les dades a aquesta funcio que crea la taula
* */

//humidity
//temperature
function createCharts(result) {
    //convertim la string de data que arriba en objecte
    var obj = JSON.parse(result);
    var data = [];
    var labels = [];
    var temperature = [];
    var humidity = [];

    var cont = 0;
    if (obj) {
        if (obj.length - 48 < 0) {
            var i = 0;
        } else {
            var i = obj.length - 48;
        }
        for (i; i < obj.length; i++) {
            if (obj[i].sensor.localeCompare("Temperature") == 0) {
                temperature.push(obj[i]);
            }
            if (obj[i].sensor.localeCompare("Humidity") == 0) {
                humidity.push(obj[i]);
            }
            cont++;
        }
    }

    //omplim les variables que formen la x i y del gràfic
    if (temperature) {
        for (var i = 0; i < temperature.length && i < 24; i++) {
            data.push(temperature[i].value);
            labels.push(temperature[i].time);
        }
        $("#temperatureDiv").show();
        //creem el gràfic de temperature
        renderChart(data, labels, "temperatureCanvas", "#ccf2ff", 40);
    }

    data = [];
    labels = [];

    if (humidity) {
        for (var i = 0; i < humidity.length && i < 24; i++) {
            data.push(humidity[i].value);
            labels.push(humidity[i].time);
        }
        $("#humidityDiv").show();
        //creem el gràfic de humidity
        renderChart(data, labels, "humidityCanvas", "#ccf2ff", 100);
    }
}

function deleteDataFromInput() {
    document.getElementById("idsInput").value = '';
}

/*
* Funcio util per endreçar per data d'anterior a més nova
* */
function sortByDate(array) {
    //new date any mes dia
    array.sort(function (a, b) {
        var dateA = new Date(parseInt(a.date.substring(6) + "20"), parseInt(a.date.substring(0, 2)), parseInt(a.date.substring(3, 5)));
        var dateB = new Date(parseInt(b.date.substring(6) + "20"), parseInt(b.date.substring(0, 2)), parseInt(b.date.substring(3, 5)));
        return dateA - dateB;
    });
}





