var serviceUrl="http://localhost:8080/";

console.log("loading index.js");

function getImagesFromServer(){
$.get(serviceUrl, function(data, status){
    console.log(data);
  });
}
