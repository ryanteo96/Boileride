const express = require("express");
const path = require("path");
const router = express.Router();
const request = require("request");

router.get("/", function(req, res) {
	res.sendFile(path.join(__dirname, "../public/html/myRides.html"));
});
router.get("/myRequest", function(req, res) {
	res.sendFile(path.join(__dirname, "../public/html/myRequest.html"));
});
router.get("/myOffer", function(req, res) {
	res.sendFile(path.join(__dirname, "../public/html/myOffer.html"));
});
router.get("/myRequest/edit", function(req, res) {
	res.sendFile(path.join(__dirname, "../public/html/myRequestEdit.html"));
});
router.get("/myRequest/cancel", function(req, res) {
	res.sendFile(path.join(__dirname, "../public/html/myRequest.html"));
});

router.post("/myRequest", function(req, res) {
	var data = {
		userid: req.body.userid,
		// userid: "temp",
	};
	var body = {
		result: 0,
	};

	res.send(body);
	console.log("user id is now " + data);

	//  temp server connection test
	// var options = {
	// 	uri: "http://localhost:8080/ride/view/request",
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
});

router.post("/myRequest/edit", function(req, res) {
	let date = req.body.date;
	let time = moment(req.body.time, "HH:mm").format("HH:mm:ss");

	var data = {
		userid: req.body.userid,
		requestid: req.body.requestid,
		pickuplocation: req.body.pickuplocation,
		destination: req.body.destination,
		datentime: req.body.datentime,
		passengers: req.body.passengers,
		luggage: req.body.luggage,
		smoking: req.body.smoking,
		foodndrink: req.body.foodndrink,
		pets: req.body.pets,
		ac: req.body.ac,
		travelingtime: req.body.travelingtime,
		price: req.body.price,
	};

	//  temp server connection test
	// var options = {
	// 	uri: "http://localhost:8080/ride/update/request",
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
});

router.post("/myRequest/edit", function(req, res) {
	let date = req.body.date;
	let time = moment(req.body.time, "HH:mm").format("HH:mm:ss");

	var data = {
		userid: req.body.userid,
		requestid: req.body.requestid,
	};

	//  temp server connection test
	// var options = {
	// 	uri: "http://localhost:8080/ride/cancel/request",
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
});

router.post("/myOffer", function(req, res) {
	var data = {
		userid: req.body.userid,
	};
	console.log(data);

	//  temp server connection test
	var options = {
		uri: "http://localhost:8080/ride/view/offer",
		json: data,
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
	};

	request(options, function(error, response) {
		console.log(error, response);
		res.send(response.body);
		return;
	});
});

router.post("/myRequest/cancelRide", function(req, res) {
	var data = {
		userid: req.body.userid,
		requestid: req.body.requestid,
	};
	console.log(data);

	//  temp server connection test
	var options = {
		uri: "http://localhost:8080/ride/cancel/request",
		json: data,
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
	};

	request(options, function(error, response) {
		console.log(error, response);
		res.send(response.body);
		return;
	});
});

module.exports = router;
