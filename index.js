var serviceUrl="http://localhost:8080/";

console.log("loading index.js");
var labels;
var count;

function getFromServer( uri){
$.get(serviceUrl+uri, function(data, status){
    console.log(data);

    labels=data.siteNames;
    count=data.count;
    console.log(labels+" "+count);
    //return data;
  }).done(function() {
  prepareBar(labels, count)
       });
}

function getChromeData(){
    uri="/chrome-history"

    return getFromServer(uri);

}


function prepareBar(labels, count){

var chromeHistory = document.getElementById('chromeHistory').getContext('2d');
var myChart = new Chart(chromeHistory, {
    type: 'bar',
    data: {
        labels: labels,
        datasets: [{
            label: '# of Votes',
            data: count,
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)'
            ],
            borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)'
            ],
            borderWidth: 1
        }]
    },
    options: {
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero: true
                }
            }]
        }
    }
});



}

function pieChart(){

data = {
    datasets: [{
        data: [10, 20, 30],
        backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)'
            ],
            borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)'
            ],
            borderWidth: 1
    }],



    // These labels appear in the legend and in the tooltips when hovering different arcs
    labels: [
        'Red',
        'Yellow',
        'Blue'
    ]
};
var outlook = document.getElementById('outlook').getContext('2d');
var myPieChart = new Chart(outlook, {
    type: 'pie',
    data: data,
    options: {}
});
}