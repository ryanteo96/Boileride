var express = require("express");
var path = require("path");

var app = express();

app.get("/", function(req, res) {
	res.redirect("/signIn");
});

app.get("/signIn", function(req, res) {
	res.sendFile(path.join(__dirname, "public/signIn.html"));
});

app.get("/signUp", function(req, res) {
	res.sendFile(path.join(__dirname, "public/signUp.html"));
});

app.get("/forgotPw", function(req, rest) {
	res.sendFile(path.join(__dirname, "public/forgotPw.html"));
});

app.listen(4000);
