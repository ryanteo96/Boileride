const express = require("express");
const path = require("path");
const request = require("request");
const crypto = require("crypto-js/sha3");

const router = express.Router();

router.get("/", function(req, res) {
	res.sendFile(path.join(__dirname, "../public/html/settings.html"));
});

router.post("/changeNickname", function(req, res) {
	var data = {
		userid: req.body.userid,
		email: "",
		password: "",
		nickname: req.body.nickname,
		phone: "",
	};

	console.log(data);

	var options = {
		uri: "http://localhost:8080/user/update",
		json: data,
		method: "POST",
		headers: {
			"Content-Type": "application/json",
			Cookie: global.cookie,
		},
	};

	request(options, function(error, response) {
		if (response) {
			res.send(response.body);
		}
		return;
	});
});

router.post("/changeEmail", function(req, res) {
	var data = {
		userid: req.body.userid,
		email: req.body.email,
		password: "",
		nickname: "",
		phone: "",
	};

	console.log(data);

	var options = {
		uri: "http://localhost:8080/user/update",
		json: data,
		method: "POST",
		headers: {
			"Content-Type": "application/json",
			Cookie: global.cookie,
		},
	};

	request(options, function(error, response) {
		if (response) {
			res.send(response.body);
		}
		return;
	});
});

router.post("/changePassword", function(req, res) {
	var data = {
		userid: req.body.userid,
		email: "",
		password: crypto(req.body.email + req.body.newpassword).toString(),
		nickname: "",
		phone: "",
	};

	console.log(data);

	var options = {
		uri: "http://localhost:8080/user/update",
		json: data,
		method: "POST",
		headers: {
			"Content-Type": "application/json",
			Cookie: global.cookie,
		},
	};

	request(options, function(error, response) {
		if (response) {
			res.send(response.body);
		}
		return;
	});
});

router.post("/changePhone", function(req, res) {
	var data = {
		userid: req.body.userid,
		email: "",
		password: "",
		nickname: "",
		phone: req.body.phone,
	};

	console.log(data);

	var options = {
		uri: "http://localhost:8080/user/update",
		json: data,
		method: "POST",
		headers: {
			"Content-Type": "application/json",
			Cookie: global.cookie,
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
