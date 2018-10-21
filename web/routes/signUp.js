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

	console.log("signUp: ");
	console.log(data);

	var options = {
		uri: "http://localhost:8080/user/signup",
		json: data,
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
	};

	request(options, function(error, response) {
		if (response) {
			res.send(response.body);
		}
		return;
	});
});

module.exports = router;
