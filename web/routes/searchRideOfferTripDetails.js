const express = require("express");
const path = require("path");
const moment = require("moment");
const request = require("request");

const router = express.Router();

router.get("/", function(req, res) {
	res.sendFile(
		path.join(__dirname, "../public/html/searchRideOfferTripDetails.html"),
	);
});

router.post("/edit", function(req, res) {
	var data = {
		userid: req.body.userid,
		startofferid: req.body.startofferid,
		endofferid: req.body.endofferid,
		pickuplocation: req.body.pickuplocation,
		destination: req.body.destination,
		pickupproximity: req.body.pickupproximity,
		destinationproximity: req.body.destinationproximity,
		datentime: req.body.datentime,
		datentimerange: req.body.datentimerange,
		numrides: req.body.numrides,
		passengers: req.body.passengers,
		luggage: req.body.luggage,
		smoking: req.body.smoking,
		foodndrink: req.body.foodndrink,
		pets: req.body.pets,
		ac: req.body.ac,
		trip: req.body.trip,
	};

	console.log(data);

	var options = {
		uri: "http://localhost:8080/ride/search/offer/alter",
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

router.post("/join", function(req, res) {
	let trip = JSON.parse(req.body.offeridlist);
	let offeridlist = [];
	for (var i = 0; i < trip.length; i++) {
		offeridlist.push(trip[i].offerid);
	}

	var data = {
		userid: req.body.userid,
		offeridlist: offeridlist,
		passenger: req.body.passenger,
		luggage: req.body.luggage,
	};

	console.log(data);

	var options = {
		uri: "http://localhost:8080/ride/join/offer",
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
