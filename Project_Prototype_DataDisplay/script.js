
var baseUrl = "https://tcm-prototype-apirest.herokuapp.com/data/";
var url = baseUrl;

function renderChart(data, labels) {
    var ctx = document.getElementById("myChart").getContext('2d');
    var myChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: labels,
            datasets: [{
                label: 'Last 24 hours',
                data: data,
            }]
        },
    });
}

$("#renderBtn").click(prepareChart());

function prepareChart(){
    getIdToUrl();
    getData();
}

/*
* Funcio per llegir de quina raspberry volem llegir la temperatura amb el input
* */
function getIdToUrl(){
    var id = document.getElementById("idSelector").value;
    url = baseUrl + id;
}

/*
* Ajax per obtenir les dades de la base de dades
* */
function getData(){
    $.ajax({
        url: url,
        type:"GET",
        success: function (result){
            createChart(result);
        },
        error: function (error){
            console.log("ERROR: no s'han pogut obtenir les dades amb l'ajax");
            console.log(error);
        }
    })
}

/*
* Passem amb l'ajax les dades a aquesta funcio que crea la taula
* */
function createChart(result){
    //convertim la string de data que arriba en objecte
    var obj = JSON.parse(result);
    var data = [];
    var labels = [];

    //endreçem les dades que ens arriben
    sortByDate(obj);

    //omplim les variables que formen la x i y del gràfic
    if(obj){
        for(var i = 0; i<obj.length && i < 24; i++){
            data.push(obj[i].value);
            labels.push(obj[i].time);
        }
    }
    //creem el gràfic
    renderChart(data, labels);
}

/*
* Funcio per endreçar tota la info que arriba en funcio de la data de manera que agafem les ultimes 24 hores
* */
function sortByDate(array){
    //new date any mes dia
    array.sort(function(a, b) {
        var dateA = new Date(parseInt(a.date.substring(6) + "20"), parseInt(a.date.substring(0,2)), parseInt(a.date.substring(3,5)));
        var dateB = new Date(parseInt(b.date.substring(6) + "20"), parseInt(b.date.substring(0,2)), parseInt(b.date.substring(3,5)));
        return dateA - dateB;
    });
}

function ready(){
    console.log("funciona el ready");
}

document.addEventListener("DOMContentLoaded", ready);
