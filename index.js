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
    '#E42C64',
    '#FCD02C',
    '#E12B38',
    '#3778C2',
    '#3EB650',
    '#E56B1F',
    '#150734',
    '#E42C64',
    '#FCD02C',
    '#E12B38',
    '#3778C2',
    '#3EB650',
    '#E56B1F',
    '#150734',
    '#E42C64',
    '#FCD02C',
    '#E12B38',
    '#3778C2',
    '#3EB650',
    '#E56B1F',
    '#150734',
    '#E42C64',
    '#FCD02C',
    '#E12B38',
    '#3778C2',
    '#3EB650',
    '#E56B1F',
    '#150734',
    '#E42C64',
    '#FCD02C',
    '#E12B38',
    '#3778C2',
    '#3EB650',
    '#E56B1F',
    '#150734',
    '#E42C64',
    '#FCD02C',
    '#E12B38',
    '#3778C2',
    '#3EB650',
    '#E56B1F',
    '#150734'
];

var borderColor = [
   '#E42C64',
   '#FCD02C',
   '#E12B38',
   '#3778C2',
   '#3EB650',
   '#E56B1F',
   '#150734',
   '#E42C64',
   '#FCD02C',
   '#E12B38',
   '#3778C2',
   '#3EB650',
   '#E56B1F',
   '#150734',
   '#E42C64',
   '#FCD02C',
   '#E12B38',
   '#3778C2',
   '#3EB650',
   '#E56B1F',
   '#150734',
   '#E42C64',
   '#FCD02C',
   '#E12B38',
   '#3778C2',
   '#3EB650',
   '#E56B1F',
   '#150734',
   '#E42C64',
   '#FCD02C',
   '#E12B38',
   '#3778C2',
   '#3EB650',
   '#E56B1F',
   '#150734',
   '#E42C64',
   '#FCD02C',
   '#E12B38',
   '#3778C2',
   '#3EB650',
   '#E56B1F',
   '#150734'
  ]



var colorSec = [
    '#3778C2',
    '#3EB650',
    '#E56B1F',
    '#150734',
    '#E42C64',
    '#FCD02C',
    '#E12B38',
    '#3778C2',
    '#3EB650',
    '#E56B1F',
    '#150734',
    '#E42C64',
    '#FCD02C',
    '#E12B38',
    '#3778C2',
    '#3EB650',
    '#E56B1F',
    '#150734',
    '#E42C64',
    '#FCD02C',
    '#E12B38',
    '#3778C2',
    '#3EB650',
    '#E56B1F',
    '#150734',
    '#E42C64',
    '#FCD02C',
    '#E12B38'
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
var listNewConnection= new Array();
function getNewConnectionsListServer(uri){
$.get(serviceUrl+uri, function(data, status){

    console.log(data);
  }).done(function(data) {
  listNewConnection=data;
       });
}

function getNewConnectionsList(){
    uri="/newconnections"
    return getNewConnectionsListServer(uri);
}



var valuesGraph=new Array();
getConnectionsList();
function getConnectionsListServer(uri){
$.get(serviceUrl+uri, function(data, status){
  }).done(function(data) {
  console.log("new>>>>>.");
  console.log(listNewConnection);
        var i=0;
        for(a in data){
        valuesGraph[i]=new Array()
         valuesGraph[i].push("YOU");

         valuesGraph[i].push(a.split("@")[0]);
         i++;
        }
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


getUserInfo();
function getUserInfoServer(uri){
$.get(serviceUrl+uri, function(data, status){
    console.log(data);
  }).done(function(data) {
    $("#email").html(data.emailId);
    $("#username").html(data.userName);

       });
}

function getUserInfo(){
    uri="/userinfo"
    return getUserInfoServer(uri);
}

