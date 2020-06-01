
var baseUrl = "Access-Control-Allow-Origin: https://tcm-prototype-apirest.herokuapp.com/data/";
var url = baseUrl;

function renderChart(data, labels) {
    var ctx = document.getElementById("myChart").getContext('2d');
    var myChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: labels,
            datasets: [{
                label: 'This week',
                data: data,
            }]
        },
    });
}

$("#renderBtn").click(createChart());

function createChart(){
    getIdToUrl();
    getData();

    data = [20000, 14000, 12000, 15000, 18000, 19000, 22000];
    labels =  ["sunday", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday"];
    renderChart(data, labels);
}

function getIdToUrl(){
    var id = document.getElementById("idSelector").value;
    url = baseUrl + id;
}


function getData(){

    $.ajax({
        url: url,
        type:"GET",
        success: function (result){
            console.log("---RESULTAT");
            console.log(result);
        },
        error: function (error){
            console.log("---ERROR");
            console.log(error);
        }
    })

    //var url = 'http://bar.other/resources/public-data/';

    //callOtherDomain();

    /*fetch(url)
        .then(res => res.json())
        .then(data => {
            // code to handle the response
        }).catch(err => {
        console.error('Error: ', err);
    });*/

    /*var data;
    data.data = [];*/
}


function callOtherDomain() {
    var invocation = new XMLHttpRequest();
    if(invocation) {
        invocation.open('GET', url, true);
        //invocation.onreadystatechange = handler;
        invocation.send();
    }
}

function ready(){
    console.log("funciona el ready");
}

document.addEventListener("DOMContentLoaded", ready);
