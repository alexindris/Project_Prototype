
var baseUrl = "http://localhost:8080/data/";
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

function getData(){
    $.ajax({
        url: url,
        type:"GET",
        success: function (result){
            createChart(result);
        },
        error: function (error){
            console.log("---ERROR");
            console.log(error);
        }
    })
}

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

function getIdToUrl(){
    var id = document.getElementById("idSelector").value;
    url = baseUrl + id;
}

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
