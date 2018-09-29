const express = require("express");
const path = require("path");
const moment = require("moment");

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
		travellingtime: req.body.travellingtime,
		price: req.body.price,
	};

	console.log(data);

	// temp server connection test
	// var options = {
	// 	uri: "http://localhost:8080/ride/offer",
	// 	json: data,
	// 	method: "POST",
	// 	headers: {
	// 		"Content-Type": "application/json",
	// 	},
	// };

	// request(options, function(error, response) {
	// 	console.log(error, response);
	// 	res.send(response.body);
	// 	return;
	// });

	res.redirect("/home");
});
module.exports = router;
