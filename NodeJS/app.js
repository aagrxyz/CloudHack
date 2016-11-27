var DigitalOcean = require('do-wrapper'),
api = new DigitalOcean('4bdd8e16d7d0c26128cf8f0ab8a38af2e7225ba296b75916ae1c73970f3255d5', 10);
api.account((err, res, body) => {         // api initialize
  console.log(body);
});







util = require("util");


var xp =  0;
var xd = 0;
var  xm = 0;
var backcount=0;
var ips = [];
var sprv = 5;
var myo;

var execs = require('child_process').exec;
     // list volumes
    var args = "  -X GET -H 'Content-Type: application/json' -H 'Authorization: Bearer 4bdd8e16d7d0c26128cf8f0ab8a38af2e7225ba296b75916ae1c73970f3255d5' 'https://api.digitalocean.com/v2/volumes?region=nyc1'";
 execs('curl ' + args, function (error, stdout, stderr) {
      console.log('stdout: ' + stdout);
      console.log('stderr: ' + stderr);
	 // sprv = stdout.volumes.size_gigabytes;
      if (error !== null) {
        console.log('exec error: ' + error);
      }
    });
	
	
api.dropletsGetAll({"page":1 , "per_page":10},(err, res, body) => {
	myo = body;
console.log("body " + util.inspect(body));
  console.log("nets  " + util.inspect(body.droplets[0].networks.v4[0].ip_address));
var myStringArray = body.droplets;
var arrayLength = myStringArray.length;
ips = []
for (var i = 0; i < arrayLength; i++) {
    ips.push(body.droplets[i].networks.v4[0].ip_address);
    //Do something
}
  console.log("ip list is   " + ips);

//console.log("mytt " + util.inspect(myo.droplets[0].snapshot_ids[0]));

});

function   mememe (){
"use strict";



   





const exec = require('child_process').exec

let child5 = exec("df -hl | grep 'sda' | awk 'BEGIN{} {percent+=$5;} END{print percent}'",{shell: '/bin/bash'}, (err, stdout, stderr) =>     {
 // console.log('DISK US  IS ',stdout, stderr); 
 var dint = parseInt(stdout, 10);
 
if( dint > 85) {
	
if(xd>3){
//more stor



var execs = require('child_process').exec;b  

    var args = "-X POST -H 'Content-Type: application/json' -H 'Authorization: Bearer 4bdd8e16d7d0c26128cf8f0ab8a38af2e7225ba296b75916ae1c73970f3255d5' -d '{treetypetree:treeresizetree,treesize_gigabytestree: 8, treeregiontree:treenyc1tree}' 'https://api.digitalocean.com/v2/volumes/1a89541c-b409-11e6-90ec-000f53315820/actions'".replace("tree", "\"");
sprv=sprv+1;
 execs('curl ' + args, function (error, stdout, stderr) {   // resize volume
      console.log('stdout: ' + stdout);
    //  console.log('stderr: ' + stderr);
	 // sprv= sprv+1;
	    //      console.log('size with +1: ' + sprv);

	 // sprv = stdout.volumes.size_gigabytes;
	//  console.log('size from do ' + sprv);
      if (error !== null) {
        console.log('exec error: ' + error);
      }
    });
















}
else{
xd=xd+1;

}

}
else{
xd=0
}
 
 
 
})


var memu;
var pru;
let child = exec("free | grep Mem | awk '{print $3/$2 * 100.0}'",{shell: '/bin/bash'}, (err, stdout, stderr) =>     {
  console.log('this is with bash',stdout, stderr); 
 memu = stdout;      
 
 var memint = parseInt(memu, 10); 
var xm =  0;
var backcount = 0;
if( memint > 85) {
if(xm>3){
//more memory
//   ran out of memory so creatin dis  

api.dropletsCreate({
  "name": "examama"+ Math.floor(Math.random() * 8888) + 1 ,
  "region": "nyc1",
  "size": "2gb",    //much more memory
  "image": 21150664,
  "volumes": null,
},(err,body,res)=> {
	//console.log("err--   " + util.inspect(err));

console.log("ran out of memory so creatin dis     " + util.inspect(res));
//console.log("net is    " + util.inspect(res.droplet.networks[0]));

//ips.push("");

});






}
else{
xm=xm+1;

}

}
else{
xm=0
}


})
let child2 = exec("echo $[100-$(vmstat 1 2|tail -1|awk '{print $15}')]",{shell: '/bin/bash'}, (err, stdout, stderr) => { console.log("This iprocs with sh", stdout, stderr);





var print = parseInt(stdout, 10);


if( print> 85) {
//console.log("print greta " + print);
if(xp>3){
	xp=0;
		console.log("Creatin coz no processor power");

//more droplet
api.dropletsCreate({
  "name": "examama"+ Math.floor(Math.random() * 555) + 1 ,
  "region": "nyc1",
  "size": "512mb",   // low memory because mainly for processor power
  "image": 21150664,
  "volumes": null,
},(err,body,res)=> {
	console.log("err--   " + util.inspect(err));

console.log("created the followin   " + util.inspect(res));
console.log("net is    " + util.inspect(res.droplet.networks[0]));

//ips.push("");

});
}
else{
xp=xp+1;}
}
else{
xp=0
}
 })

//ec("mpstat 1 1 | awk '$3 ~ /CPU/ { for(i=1;i<=NF;i++) { if ($i ~ /%idle/) field=i } } $3 ~ /all/ { printf("%d",100 - $field) }'", puts);




	//console.log("Print is  " + print);


if(backcount>100){
backcount = 0;
// do backup
}
}


var express = require('express'),
	bodyParser = require("body-parser"),
    mongoose = require("mongoose"),
    User = require("./models/user"),
    Group = require("./models/group"),
    Transaction = require("./models/transaction");


var app = express();
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

var apiRoutes = require("./routes/apiRoutes");

app.use("/api",apiRoutes);

var connection_string = 'mongodb://localhost/hisaab';
mongoose.connect(connection_string);

app.get('/', function (req, res) {
  res.json({
	message: "Welcome to Hisaab API",
	secret: "We strive to make the world a better place"
	});
		mememe();
});

app.listen("801","127.0.0.1",function () {
	mememe();
  console.log('Example app listening on port 8080!')
});
