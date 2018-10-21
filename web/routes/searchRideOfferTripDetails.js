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
	let date = req.body.date;
	let time = moment(req.body.time, "HH:mm").format("HH:mm:ss");

	var offset = moment.duration("04:00:00");
	var datentime = moment(date + " " + time);
	datentime.add(offset);

	var data = {
		userid: req.body.userid,
		startofferid: req.body.startofferid,
		endofferid: req.body.endofferid,
		pickuplocation: req.body.pickuplocation,
		destination: req.body.destination,
		pickupproximity: req.body.pickupproximity,
		destinationproximity: req.body.destinationproximity,
		datentime: datentime.format("YYYY-MM-DD HH:mm"),
		datentimerange: req.body.datentimerange,
		numrides: req.body.numrides,
		passengers: req.body.passengers,
		luggage: req.body.luggage,
		smoking: req.body.smoking,
		foodndrink: req.body.foodndrink,
		pets: req.body.pets,
		ac: req.body.ac,
		trip: JSON.parse(req.body.trip),
	};

	console.log("searchRideOffer edit: ");
	console.log(data);

	var options = {
		uri: "http://localhost:8080/ride/search/offer/alter",
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

	console.log("searchRideOffer join: ");
	console.log(data);

	var options = {
		uri: "http://localhost:8080/ride/join/offer",
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
