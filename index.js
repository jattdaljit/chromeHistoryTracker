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
  prepareBar(labels, count, color, borderColor)
       });
}

function getFromServerAggr( uri){
$.get(serviceUrl+uri, function(data, status){
    console.log(data);

    labels=data.type;
    count=data.time;
    console.log(labels+" "+count);
    //return data;
  }).done(function() {
  pieChart(labels, count, color, borderColor)
       });
}

function getChromeData(){
    uri="/chrome-history"
    return getFromServer(uri);
}

function getAggrChromeData(){
    uri="/chrome-aggr"
    console.log(uri)
    return getFromServerAggr(uri);
}
var varFreeTime= new Array();
var varMettingTime= new Array();
var varMonth = new Array();
function getWorkingHoursServer( uri){
$.get(serviceUrl+uri, function(data, status){
console.log(data)
  }).done(function(data) {

 for (k in data){
console.log(data[k])
  varMonth.push(k);
  varMettingTime.push(data[k]/60);
  varFreeTime.push(8-(data[k]/60));
 }
 console.log(varMonth)
 console.log(varMettingTime)
 console.log(varFreeTime)

 barWorkingHours();
 });
}
getWorkingHours();
function getWorkingHours(){
    uri="/workinghour";
    return getWorkingHoursServer(uri);
}


function prepareBar(labels, count){

var chromeHistory = document.getElementById('chromeHistory').getContext('2d');
var myChart = new Chart(chromeHistory, {
    type: 'bar',
    data: {
        labels: labels,
        datasets: [{
            label: 'Browser Analytics',
            data: count,
            backgroundColor: color,
            borderColor: borderColor,
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

function pieChart(labels, count){
data = {
    datasets: [{
        data: count,
        backgroundColor: color,
        borderColor: borderColor,
        borderWidth: 1
    }],
    labels: labels
};
var chromeAggrData = document.getElementById('chromeAggrData').getContext('2d');
var myPieChart = new Chart(chromeAggrData, {
    type: 'pie',
    data: data,
    options: {}
});
}

var color = [
    'rgba(255, 99, 132, 0.2)',
    'rgba(54, 162, 235, 0.2)',
    'rgba(255, 206, 86, 0.2)',
    'rgba(75, 192, 192, 0.2)',
    'rgba(153, 102, 255, 0.2)',
    'rgba(255, 159, 64, 0.2)',
    'rgba(255, 99, 132, 0.2)',
     'rgba(54, 162, 235, 0.2)',
     'rgba(255, 206, 86, 0.2)',
     'rgba(75, 192, 192, 0.2)',
     'rgba(153, 102, 255, 0.2)',
     'rgba(255, 159, 64, 0.2)',
     'rgba(255, 99, 132, 0.2)',
     'rgba(54, 162, 235, 0.2)',
     'rgba(255, 206, 86, 0.2)',
     'rgba(75, 192, 192, 0.2)',
     'rgba(153, 102, 255, 0.2)',
     'rgba(255, 159, 64, 0.2)',
     'rgba(255, 99, 132, 0.2)',
     'rgba(54, 162, 235, 0.2)',
     'rgba(255, 206, 86, 0.2)',
     'rgba(75, 192, 192, 0.2)',
     'rgba(153, 102, 255, 0.2)',
     'rgba(255, 159, 64, 0.2)',
     'rgba(255, 99, 132, 0.2)',
     'rgba(54, 162, 235, 0.2)',
     'rgba(255, 206, 86, 0.2)',
     'rgba(75, 192, 192, 0.2)',
     'rgba(153, 102, 255, 0.2)',
     'rgba(255, 159, 64, 0.2)',
     'rgba(255, 99, 132, 0.2)',
     'rgba(54, 162, 235, 0.2)',
     'rgba(255, 206, 86, 0.2)',
     'rgba(75, 192, 192, 0.2)',
     'rgba(153, 102, 255, 0.2)',
     'rgba(255, 159, 64, 0.2)',
     'rgba(255, 99, 132, 0.2)',
     'rgba(54, 162, 235, 0.2)',
     'rgba(255, 206, 86, 0.2)',
     'rgba(75, 192, 192, 0.2)',
     'rgba(153, 102, 255, 0.2)',
     'rgba(255, 159, 64, 0.2)'
];

var borderColor = [
   'rgba(255, 99, 132, 1)',
   'rgba(54, 162, 235, 1)',
   'rgba(255, 206, 86, 1)',
   'rgba(75, 192, 192, 1)',
   'rgba(153, 102, 255, 1)',
   'rgba(255, 159, 64, 1)',
   'rgba(255, 99, 132, 1)',
      'rgba(54, 162, 235, 1)',
      'rgba(255, 206, 86, 1)',
      'rgba(75, 192, 192, 1)',
      'rgba(153, 102, 255, 1)',
      'rgba(255, 159, 64, 1)',
   'rgba(255, 99, 132, 1)',
      'rgba(54, 162, 235, 1)',
      'rgba(255, 206, 86, 1)',
      'rgba(75, 192, 192, 1)',
      'rgba(153, 102, 255, 1)',
      'rgba(255, 159, 64, 1)',
   'rgba(255, 99, 132, 1)',
      'rgba(54, 162, 235, 1)',
      'rgba(255, 206, 86, 1)',
      'rgba(75, 192, 192, 1)',
      'rgba(153, 102, 255, 1)',
      'rgba(255, 159, 64, 1)',
   'rgba(255, 99, 132, 1)',
      'rgba(54, 162, 235, 1)',
      'rgba(255, 206, 86, 1)',
      'rgba(75, 192, 192, 1)',
      'rgba(153, 102, 255, 1)',
      'rgba(255, 159, 64, 1)',
     'rgba(255, 99, 132, 1)',
        'rgba(54, 162, 235, 1)',
        'rgba(255, 206, 86, 1)',
        'rgba(75, 192, 192, 1)',
        'rgba(153, 102, 255, 1)',
        'rgba(255, 159, 64, 1)',
      'rgba(255, 99, 132, 1)',
         'rgba(54, 162, 235, 1)',
         'rgba(255, 206, 86, 1)',
         'rgba(75, 192, 192, 1)',
         'rgba(153, 102, 255, 1)',
         'rgba(255, 159, 64, 1)'
  ]



var colorSec = [
    'rgba(75, 192, 192, 0.2)',
    'rgba(54, 162, 235, 0.2)',
    'rgba(255, 99, 132, 0.2)',
    'rgba(255, 206, 86, 0.2)',
    'rgba(153, 102, 255, 0.2)',
    'rgba(255, 159, 64, 0.2)',
    'rgba(255, 99, 132, 0.2)',
     'rgba(54, 162, 235, 0.2)',
     'rgba(255, 206, 86, 0.2)',
     'rgba(75, 192, 192, 0.2)',
     'rgba(153, 102, 255, 0.2)',
     'rgba(255, 159, 64, 0.2)',
     'rgba(255, 99, 132, 0.2)',
     'rgba(54, 162, 235, 0.2)',
     'rgba(255, 206, 86, 0.2)',
     'rgba(75, 192, 192, 0.2)',
     'rgba(153, 102, 255, 0.2)',
     'rgba(255, 159, 64, 0.2)',
     'rgba(255, 99, 132, 0.2)',
     'rgba(54, 162, 235, 0.2)',
     'rgba(255, 206, 86, 0.2)',
     'rgba(75, 192, 192, 0.2)',
     'rgba(153, 102, 255, 0.2)',
     'rgba(255, 159, 64, 0.2)',
     'rgba(255, 99, 132, 0.2)',
     'rgba(54, 162, 235, 0.2)',
     'rgba(255, 206, 86, 0.2)',
     'rgba(75, 192, 192, 0.2)',
     'rgba(153, 102, 255, 0.2)',
     'rgba(255, 159, 64, 0.2)',
     'rgba(255, 99, 132, 0.2)',
     'rgba(54, 162, 235, 0.2)',
     'rgba(255, 206, 86, 0.2)',
     'rgba(75, 192, 192, 0.2)',
     'rgba(153, 102, 255, 0.2)',
     'rgba(255, 159, 64, 0.2)',
     'rgba(255, 99, 132, 0.2)',
     'rgba(54, 162, 235, 0.2)',
     'rgba(255, 206, 86, 0.2)',
     'rgba(75, 192, 192, 0.2)',
     'rgba(153, 102, 255, 0.2)',
     'rgba(255, 159, 64, 0.2)'
];


function barWorkingHours(){
var workingHoursbar = document.getElementById('workingHoursbar').getContext('2d');
var myChart = new Chart(workingHoursbar, {
    type: 'line',
    data: {
        datasets: [{
            data: varMettingTime,
            label: 'Meetings',
            backgroundColor: color,
            borderColor: borderColor,
            borderWidth: 1,
            // This binds the dataset to the left y axis
            yAxisID: 'left-y-axis'
        }, {
            data: varFreeTime,
            label: 'Time to Focus',
            backgroundColor: colorSec,
            borderColor: borderColor,
            borderWidth: 1,
            // This binds the dataset to the right y axis
            yAxisID: 'right-y-axis'
        }],
        labels: varMonth
    },
    options: {
        beginAtZero: true,
        scales: {
            yAxes: [{
                id: 'left-y-axis',
                type: 'linear',
                position: 'left',
                 ticks: {
                                    beginAtZero: true
                       }
            }, {
                id: 'right-y-axis',
                type: 'linear',
                position: 'right',
                                                  ticks: {
                                                                     beginAtZero: true
                                                        }
            }]
        }
    }
});

}
getNewConnectionsList();
function getNewConnectionsListServer(uri){
$.get(serviceUrl+uri, function(data, status){

    console.log(data);
  }).done(function(data) {
       });
}

function getNewConnectionsList(){
    uri="/newconnections"
    return getNewConnectionsListServer(uri);
}



getConnectionsList();
function getConnectionsListServer(uri){
$.get(serviceUrl+uri, function(data, status){


    console.log(data);
  }).done(function(data) {
       });
}

function getConnectionsList(){
    uri="/connectionlist"
    return getConnectionsListServer(uri);
}

getProcessList();
function getProcessListServer(uri){
$.get(serviceUrl+uri, function(data, status){

    console.log(data);
  }).done(function(data) {

  for(k in data.apps){
  console.log(">>> "+data.apps[k])
  if(data.apps[k]==""){

    data.apps[k]="ALT";
  }
  }
  prepareDoughnut(data.apps, data.timeSpent)
       });
}

function getProcessList(){
    uri="/processes"
    return getProcessListServer(uri);
}



function prepareDoughnut(labels, count){

var processlist = document.getElementById('processlist').getContext('2d');
var myChart = new Chart(processlist, {
    type: 'doughnut',
    data: {
        labels: labels,
        datasets: [{
            label: 'Application Analytics',
            data: count,
            backgroundColor: color,
            borderColor: borderColor,
            borderWidth: 1
        }]
    },
    options: {
    }
});


}
