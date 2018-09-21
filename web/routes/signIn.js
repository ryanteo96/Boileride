const express = require("express");
const path = require("path");
const bodyParser = require("body-parser");
const request = require("request");
const bcrypt = require("bcrypt");
const crypto = require("crypto-js/sha3");
const asyncHandler = require("express-async-handler");

const router = express.Router();

router.get("/", function(req, res) {
	res.sendFile(path.join(__dirname, "../public/html/signIn.html"));
});

router.post(
	"/",
	asyncHandler(async (req, res, next) => {
		var data = {
			email: req.body.email,
			password: crypto(req.body.email + req.body.password).toString(),
		};

		res.send("0");
		console.log(data);

		// temp server connection test
		// var options = {
		// 	uri: "http://localhost:8080/user/login",
		// 	json: data,
		// 	method: "POST",
		// 	headers: {
		// 		"Content-Type": "application/json",
		// 	},
		// };

		// request(options, function(error, response) {
		// 	console.log(error, response);
		// 	res.send(response.result);
		// 	return;
		// });
	}),
);

module.exports = router;
