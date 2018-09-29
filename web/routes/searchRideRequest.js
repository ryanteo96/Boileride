const express = require("express");
const path = require("path");
const moment = require("moment");

const router = express.Router();

router.get("/", function(req, res) {
	res.sendFile(path.join(__dirname, "../public/html/searchRideRequest.html"));
});

router.post("/", function(req, res) {
	let date = req.body.date;
	let time = moment(req.body.time, "HH:mm").format("HH:mm:ss");
	let dateRange = req.body.daterange;
	let timeRange = moment(req.body.timerange, "HH:mm").format("HH:mm:ss");

	console.log(dateRange);
	console.log(timeRange);

	if (!req.body.smoking) {
		req.body.smoking = "false";
	}

	if (!req.body.foodndrink) {
		req.body.foodndrink = "false";
	}

	if (!req.body.pets) {
		req.body.pets = "false";
	}

	if (!req.body.ac) {
		req.body.ac = "false";
	}

	// if (moment(date, "YYYY-MM-DD", true).isValid()) {
	// 	console.log("true");
	// } else {
	// 	console.log("false");
	// }

	var data = {
		userid: "temp",
		pickuplocation: req.body.pickuplocation,
		destination: req.body.destination,
		pickupproximity: req.body.pickupproximity,
		destinationproximity: req.body.destinationproximity,
		datentime: date + " " + time,
		datentimerange: dateRange + " " + timeRange,
		seats: req.body.seats,
		luggage: req.body.luggage,
		smoking: req.body.smoking,
		foodndrink: req.body.foodndrink,
		pets: req.body.pets,
		ac: req.body.ac,
	};

	console.log(data);

	// temp server connection test
	// var options = {
	// 	uri: "http://localhost:8080/ride/search/request",
	// 	json: data,
	// 	method: "POST",
	// 	headers: {
	// 		"Content-Type": "application/json",
	// 	},
	// };

	// request(options, function(error, response) {
	// 	console.log(error, response);
	// 	return;
	// });

	res.redirect("/home");
});
module.exports = router;
