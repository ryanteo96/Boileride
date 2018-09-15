// Dependencies
const express = require("express");
const path = require("path");
const fs = require("fs");
const http = require("http");
const https = require("https");

const app = express();

const privateKey = fs.readFileSync(
	"/etc/letsencrypt/live/boileride.ryanteo96.tech/privkey.pem",
	"utf8",
);
const certificate = fs.readFileSync(
	"/etc/letsencrypt/live/boileride.ryanteo96.tech/cert.pem",
	"utf8",
);
const ca = fs.readFileSync(
	"/etc/letsencrypt/live/boileride.ryanteo96.tech/chain.pem",
	"utf8",
);

const credentials = {
	key: privateKey,
	cert: certificate,
	ca: ca,
};

app.get("/", function(req, res) {
	res.redirect("/signIn");
});

app.get("/signIn", function(req, res) {
	res.sendFile(path.join(__dirname, "public/signIn.html"));
});

app.get("/signUp", function(req, res) {
	res.sendFile(path.join(__dirname, "public/signUp.html"));
});

app.get("/forgotPw", function(req, res) {
	res.sendFile(path.join(__dirname, "public/forgotPw.html"));
});

// app.listen(4000);
const httpsServer = https.createServer(credentials, app);

httpsServer.listen(443, () => {
	console.log("HTTPS Server running on port 443");
});
