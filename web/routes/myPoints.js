const express = require("express");
const path = require("path");
const moment = require("moment");
const request = require("request");

const router = express.Router();

router.get("/", function(req, res) {
	res.sendFile(path.join(__dirname, "../public/html/myPoints.html"));
});

router.post("/", function(req, res) {
	var data = {
		userid: req.body.userid,
	};

	console.log("myPoints: ");
	console.log(data);

	var options = {
		uri: "http://localhost:8080/user/view/points",
		json: data,
		method: "POST",
		headers: {
			"Content-Type": "application/json",
			Cookie: JSON.parse(req.body.cookie),
		},
	};

	request(options, function(error, response) {
		if (response) {
			res.send(response);
		}
		return;
	});
});

router.post("/myTransactions", function(req, res) {
	var data = {
		userid: req.body.userid,
	};

	console.log("myTransactions: ");
	console.log(data);

	var options = {
		uri: "http://localhost:8080/user/view/transaction",
		json: data,
		method: "POST",
		headers: {
			"Content-Type": "application/json",
			Cookie: JSON.parse(req.body.cookie),
		},
	};

	request(options, function(error, response) {
		if (response) {
			res.send(response);
		}
		return;
	});
});

module.exports = router;
