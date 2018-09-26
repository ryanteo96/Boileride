const express = require("express");
const path = require("path");
const request = require("request");

const router = express.Router();

router.get("/", function(req, res) {
	res.sendFile(path.join(__dirname, "../public/html/verifyEmail.html"));
});

router.post("/", function(req, res) {
	var data = {
		email: req.body.email,
		code: req.body.code,
	};

	console.log(data);

	// temp server connection test
	var options = {
		uri: "http://localhost:8080/user/verifyemail",
		json: data,
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
	};

	request(options, function(error, response) {
		res.send(response.body);
		return;
	});
});

module.exports = router;
