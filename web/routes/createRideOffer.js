const express = require("express");
const path = require("path");
const moment = require("moment");
const request = require("request");

const router = express.Router();

router.get("/", function(req, res) {
	res.sendFile(path.join(__dirname, "../public/html/createRideOffer.html"));
});

router.post("/", function(req, res) {
	let date = req.body.date;
	let time = moment(req.body.time, "HH:mm").format("HH:mm:ss");

	var data = {
		userid: req.body.userid,
		pickuplocation: req.body.pickuplocation,
		destination: req.body.destination,
		datentime: date + " " + time,
		seats: req.body.seats,
		luggage: req.body.luggage,
		smoking: req.body.smoking,
		foodndrink: req.body.foodndrink,
		pets: req.body.pets,
		ac: req.body.ac,
		travelingtime: req.body.travelingtime,
		price: req.body.price,
	};

	console.log(data);

	var options = {
		uri: "http://localhost:8080/ride/offer",
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
