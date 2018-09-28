const express = require("express");
const path = require("path");
const request = require("request");

const router = express.Router();

router.get("/", function(req, res) {
	res.sendFile(path.join(__dirname, "../public/html/resetPw.html"));
});

router.post("/", function(req, res) {
	var data = {
		email: req.body.email,
		code: req.body.code,
		newpassword: req.body.newpassword,
	};

	console.log(data);

	var options = {
		uri: "http://localhost:8080/user/resetpassword",
		json: data,
		method: "POST",
		headers: {
			"Content-Type": "application/json",
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
