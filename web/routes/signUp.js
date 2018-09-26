const express = require("express");
const path = require("path");
const crypto = require("crypto-js/sha3");
const request = require("request");

const router = express.Router();

router.get("/", function(req, res) {
	res.sendFile(path.join(__dirname, "../public/html/signUp.html"));
});

router.post("/", function(req, res) {
	var data = {
		email: req.body.email,
		password: crypto(req.body.email + req.body.password).toString(),
		nickname: req.body.nickname,
		phone: req.body.phone,
	};

	// res.send("0");
	console.log(data);

	// temp server connection test
	var options = {
		uri: "http://localhost:8080/user/signup",
		json: data,
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
	};

	request(options, function(error, response) {
		res.send(response.body);
		return;
	});
});

module.exports = router;
