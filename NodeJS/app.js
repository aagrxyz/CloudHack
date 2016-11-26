var express = require('express'),
	bodyParser = require("body-parser");

var app = express();
var apiRoutes = require("./routes/apiRoutes");

app.use("/api",apiRoutes)
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

app.get('/', function (req, res) {
  res.json({
	message: "Welcome to Hisaab API",
	secret: "We strive to make the world a better place"
	});
});

app.listen("8080","127.0.0.1",function () {
  console.log('Example app listening on port 8080!')
});
