var express = require("express");
var path = require("path");

var app = express();

app.get("/signIn", function(req, res) {
	res.sendFile(path.join(__dirname, "publicsignIn.html"));
});

app.listen(4000);
