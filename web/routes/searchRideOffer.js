const express = require("express");
const path = require("path");
const moment = require("moment");
const request = require("request");

const router = express.Router();

router.get("/", function(req, res) {
	res.sendFile(path.join(__dirname, "../public/html/searchRideOffer.html"));
});

router.post("/", function(req, res) {
	let date = req.body.date;
	let time = moment(req.body.time, "HH:mm").format("HH:mm:ss");

	var data = {
		userid: req.body.userid,
		pickuplocation: req.body.pickuplocation,
		destination: req.body.destination,
		pickupproximity: req.body.pickupproximity,
		destinationproximity: req.body.destinationproximity,
		datentime: date + " " + time,
		datentimerange: req.body.datentimerange,
		passengers: req.body.passengers,
		luggage: req.body.luggage,
		smoking: req.body.smoking,
		numrides: req.body.numrides,
		foodndrink: req.body.foodndrink,
		pets: req.body.pets,
		ac: req.body.ac,
	};

	console.log(data);

	var options = {
		uri: "http://localhost:8080/ride/search/offer",
		json: data,
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
	};

	request(options, function(error, response) {
		var body = {
			result: 0,
		};

		res.send(body);

		if (response) {
			res.send(response.body);
		}
		return;
	});
});
module.exports = router;
