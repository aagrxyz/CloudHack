var express = require('express'),
	bodyParser = require("body-parser");

var app = express();

app.get('/', function (req, res) {
  res.json({
	message: "Welcome to Hissab API",
	secret: "We strive to make the world a better place"
	});

app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

app.listen("801","127.0.0.1",function () {
  console.log('Example app listening on port 8080!')
});
