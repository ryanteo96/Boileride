var express = require("express");
var path = require("path");

var app = express();

app.get("/", function(req, res) {
	res.sendFile(path.join(__dirname, "public/signIn.html"));
});

app.listen(4000);
