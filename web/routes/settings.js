const express = require("express");
const path = require("path");
const router = express.Router();

router.get("/", function(req, res) {
	res.sendFile(path.join(__dirname, "../public/html/settings.html"));
});

router.post("/changeNickname", function(req, res) {
	var data = {
		nickname: req.body.nickname,
	};

	console.log(data);

	//  temp server connection test
	// var options = {
	// 	uri: "http://localhost:8080/user/update",
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
});

router.post("/changeEmail", function(req, res) {
	var data = {
		email: req.body.email,
	};

	console.log(data);

	//  temp server connection test
	// var options = {
	// 	uri: "http://localhost:8080/user/update",
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
});

router.post("/changePassword", function(req, res) {
	var data = {
		password: req.body.password,
	};

	console.log(data);

	//  temp server connection test
	// var options = {
	// 	uri: "http://localhost:8080/user/update",
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
});

router.post("/changePhone", function(req, res) {
	var data = {
		phone: req.body.phone,
	};

	console.log(data);

	//  temp server connection test
	// var options = {
	// 	uri: "http://localhost:8080/user/update",
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
});

module.exports = router;
