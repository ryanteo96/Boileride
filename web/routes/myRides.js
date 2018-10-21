const express = require("express");
const path = require("path");
const request = require("request");
const moment = require("moment");

const router = express.Router();

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
router.get("/myRequest/pickup", function(req, res) {
	res.sendFile(path.join(__dirname, "../public/html/myRequestPickup.html"));
});
router.get("/myRequest/accepted", function(req, res) {
	res.sendFile(path.join(__dirname, "../public/html/myAcceptedRequest.html"));
});
router.get("/myOffer/edit", function(req, res) {
	res.sendFile(path.join(__dirname, "../public/html/myOfferEdit.html"));
});
router.get("/myOffer/cancel", function(req, res) {
	res.sendFile(path.join(__dirname, "../public/html/myOffer.html"));
});
router.get("/myOffer/pickup", function(req, res) {
	res.sendFile(path.join(__dirname, "../public/html/myOfferPickup.html"));
});
router.get("/myOffer/joined", function(req, res) {
	res.sendFile(path.join(__dirname, "../public/html/myJoinedOffer.html"));
});

//view request
router.post("/myRequest", function(req, res) {
	var data = {
		userid: req.body.userid,
	};

	var options = {
		uri: "http://localhost:8080/ride/view/request",
		json: data,
		method: "POST",
		headers: {
			"Content-Type": "application/json",
			Cookie: JSON.parse(req.body.cookie),
		},
	};

	console.log("myRequest: ");
	console.log(data);

	request(options, function(error, response) {
		if (response) {
			res.send(response);
		}
		return;
	});
});

//edit myrequest
router.post("/myRequest/edit", function(req, res) {
	let date = req.body.date;
	let time = moment(req.body.time, "HH:mm").format("HH:mm:ss");

	var offset = moment.duration("04:00:00");
	var datentime = moment(date + " " + time);
	datentime.add(offset);

	var data = {
		userid: req.body.userid,
		requestid: req.body.requestid,
		pickuplocation: req.body.pickuplocation,
		destination: req.body.destination,
		datentime: datentime,
		passengers: req.body.passengers,
		luggage: req.body.luggage,
		smoking: req.body.smoking,
		foodndrink: req.body.foodndrink,
		pets: req.body.pets,
		ac: req.body.ac,
		travelingtime: req.body.travelingtime,
		price: req.body.price,
	};

	console.log("myRequest edit: ");
	console.log(data);

	var options = {
		uri: "http://localhost:8080/ride/update/request",
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

//cancel myrequest
router.post("/myRequest/cancel", function(req, res) {
	let date = req.body.date;
	let time = moment(req.body.time, "HH:mm").format("HH:mm:ss");

	var data = {
		userid: req.body.userid,
		requestid: req.body.requestid,
	};

	var options = {
		uri: "http://localhost:8080/ride/cancel/request",
		json: data,
		method: "POST",
		headers: {
			"Content-Type": "application/json",
			Cookie: JSON.parse(req.body.cookie),
		},
	};

	console.log("myRequest cancel: ");
	console.log(data);

	request(options, function(error, response) {
		if (response) {
			res.send(response);
		}
		return;
	});
});

// myrequest get pickup code
router.post("/myRequest/pickup", function(req, res) {
	var data = {
		userid: req.body.userid,
		requestid: req.body.requestid,
	};

	var options = {
		uri: "http://localhost:8080/ride/request/pickup",
		json: data,
		method: "POST",
		headers: {
			"Content-Type": "application/json",
			Cookie: JSON.parse(req.body.cookie),
		},
	};

	console.log("myRequest pickup: ");
	console.log(data);

	request(options, function(error, response) {
		if (response) {
			res.send(response);
		}
		return;
	});
});

// myrequest to confirm pickup code
router.post("/myRequest/confirmpickup", function(req, res) {
	var data = {
		userid: req.body.userid,
		requestid: req.body.requestid,
		code: req.body.code,
	};

	console.log("myRequest confirm pickup: ");
	console.log(data);

	var options = {
		uri: "http://localhost:8080/ride/request/confirmpickup",
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

// view accepted request
router.post("/myRequest/accepted", function(req, res) {
	var data = {
		userid: req.body.userid,
	};

	var options = {
		uri: "http://localhost:8080/ride/view/acceptedrequest",
		json: data,
		method: "POST",
		headers: {
			"Content-Type": "application/json",
			Cookie: JSON.parse(req.body.cookie),
		},
	};

	console.log("myRequest accepted: ");
	console.log(data);

	request(options, function(error, response) {
		if (response) {
			res.send(response);
		}
		return;
	});
});

//cancel my accepted request
router.post("/myRequest/accepted/cancel", function(req, res) {
	let date = req.body.date;
	let time = moment(req.body.time, "HH:mm").format("HH:mm:ss");

	var data = {
		userid: req.body.userid,
		requestid: req.body.requestid,
	};

	var options = {
		uri: "http://localhost:8080/ride/cancel/acceptedrequest",
		json: data,
		method: "POST",
		headers: {
			"Content-Type": "application/json",
			Cookie: JSON.parse(req.body.cookie),
		},
	};

	console.log("myRequest accepted cancel: ");
	console.log(data);

	request(options, function(error, response) {
		if (response) {
			res.send(response);
		}
		return;
	});
});

// myrequest get pickup code
router.post("/myRequest/accepted/pickup", function(req, res) {
	var data = {
		userid: req.body.userid,
		requestid: req.body.requestid,
	};

	var options = {
		uri: "http://localhost:8080/ride/acceptedrequest/pickup",
		json: data,
		method: "POST",
		headers: {
			"Content-Type": "application/json",
			Cookie: JSON.parse(req.body.cookie),
		},
	};

	console.log("myRequest accepted pickup: ");
	console.log(data);

	request(options, function(error, response) {
		if (response) {
			res.send(response);
		}
		return;
	});
});

// accepted myrequest to confirm pickup code
router.post("/myRequest/accepted/confirmpickup", function(req, res) {
	var data = {
		userid: req.body.userid,
		requestid: req.body.requestid,
		code: req.body.code,
	};

	console.log("myRequest accepted confirmpickup: ");
	console.log(data);

	var options = {
		uri: "http://localhost:8080/ride/acceptedrequest/confirmpickup",
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

//view myOffer
router.post("/myOffer", function(req, res) {
	var data = {
		userid: req.body.userid,
	};

	console.log("myOffer: ");
	console.log(data);

	var options = {
		uri: "http://localhost:8080/ride/view/offer",
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

//edit myOffer
router.post("/myOffer/edit", function(req, res) {
	let date = req.body.date;
	let time = moment(req.body.time, "HH:mm").format("HH:mm:ss");

	var offset = moment.duration("04:00:00");
	var datentime = moment(date + " " + time);
	datentime.add(offset);

	var data = {
		userid: req.body.userid,
		offerid: req.body.offerid,
		pickuplocation: req.body.pickuplocation,
		destination: req.body.destination,
		datentime: datentime,
		seats: req.body.seats,
		luggage: req.body.luggage,
		smoking: req.body.smoking,
		foodndrink: req.body.foodndrink,
		pets: req.body.pets,
		ac: req.body.ac,
		travelingtime: req.body.travelingtime,
		price: req.body.price,
	};

	console.log("myOffer edit: ");
	console.log(data);

	var options = {
		uri: "http://localhost:8080/ride/update/offer",
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

//cancel myOffer
router.post("/myOffer/cancel", function(req, res) {
	var data = {
		userid: req.body.userid,
		offerid: req.body.offerid,
	};

	console.log("myOffer cancel: ");
	console.log(data);

	var options = {
		uri: "http://localhost:8080/ride/cancel/offer",
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

// myoffer get pickup code
router.post("/myOffer/pickup", function(req, res) {
	var data = {
		userid: req.body.userid,
		offerid: req.body.offerid,
	};

	console.log("myOffer pickup: ");
	console.log(data);

	var options = {
		uri: "http://localhost:8080/ride/offer/pickup",
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

// myoffer to confirm pickup code
router.post("/myOffer/confirmpickup", function(req, res) {
	var data = {
		userid: req.body.userid,
		offerid: req.body.offerid,
		joineduserid: req.body.joineduserid,
		code: req.body.code,
	};

	console.log("myOffer confirmpickup: ");
	console.log(data);

	var options = {
		uri: "http://localhost:8080/ride/offer/confirmpickup",
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

// view joined offer
router.post("/myOffer/joined", function(req, res) {
	var data = {
		userid: req.body.userid,
	};

	var options = {
		uri: "http://localhost:8080/ride/view/joinedoffer",
		json: data,
		method: "POST",
		headers: {
			"Content-Type": "application/json",
			Cookie: JSON.parse(req.body.cookie),
		},
	};

	console.log("myOffer joined: ");
	console.log(data);

	request(options, function(error, response) {
		if (response) {
			res.send(response);
		}
		return;
	});
});

//edit joined myOffer
router.post("/myOffer/joined/edit", function(req, res) {
	var data = {
		userid: req.body.userid,
		offeridlist: req.body.offeridlist,
		passenger: req.body.passenger,
		luggage: req.body.luggage,
	};

	console.log("myOffer joined edit: ");
	console.log(data);

	var options = {
		uri: "http://localhost:8080/ride/update/joinedoffer",
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

//cancel myOffer
router.post("/myOffer/joined/cancel", function(req, res) {
	var data = {
		userid: req.body.userid,
		offerid: req.body.offerid,
	};

	console.log("myOffer joined cancel: ");
	console.log(data);

	var options = {
		uri: "http://localhost:8080/ride/cancel/joinedoffer",
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

// myoffer get pickup code
router.post("/myOffer/joined/pickup", function(req, res) {
	var data = {
		userid: req.body.userid,
		offerid: req.body.offerid,
	};

	console.log("myOffer joined pickup: ");
	console.log(data);

	var options = {
		uri: "http://localhost:8080/ride/joinedoffer/pickup",
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

// myoffer to confirm pickup code
router.post("/myOffer/joined/confirmpickup", function(req, res) {
	var data = {
		userid: req.body.userid,
		offerid: req.body.offerid,
		code: req.body.code,
	};

	console.log("myOffer joined confirmpickup: ");
	console.log(data);

	var options = {
		uri: "http://localhost:8080/ride/joinedoffer/confirmpickup",
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
