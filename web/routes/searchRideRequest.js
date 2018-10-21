const express = require("express");
const path = require("path");
const moment = require("moment");
const request = require("request");

const router = express.Router();

router.get("/", function(req, res) {
	res.sendFile(path.join(__dirname, "../public/html/searchRideRequest.html"));
});

router.post("/", function(req, res) {
	let date = req.body.date;
	let time = moment(req.body.time, "HH:mm").format("HH:mm:ss");

	var offset = moment.duration("04:00:00");
	var datentime = moment(date + " " + time);
	datentime.add(offset);

	var data = {
		userid: req.body.userid,
		pickuplocation: req.body.pickuplocation,
		destination: req.body.destination,
		pickupproximity: req.body.pickupproximity,
		destinationproximity: req.body.destinationproximity,
		datentime: datentime.format("YYYY-MM-DD HH:mm"),
		datentimerange: req.body.datentimerange,
		seats: req.body.seats,
		luggage: req.body.luggage,
		smoking: req.body.smoking,
		foodndrink: req.body.foodndrink,
		pets: req.body.pets,
		ac: req.body.ac,
	};

	console.log("searchRideRequest: ");
	console.log(data);

	var options = {
		uri: "http://localhost:8080/ride/search/request",
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

router.post("/accept", function(req, res) {
	var data = {
		userid: req.body.userid,
		requestid: req.body.requestid,
	};

	console.log("searchRideRequest accept: ");
	console.log(data);

	var options = {
		uri: "http://localhost:8080/ride/accept/request",
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
