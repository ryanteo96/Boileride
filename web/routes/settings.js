const express = require("express");
const path = require("path");
const router = express.Router();

router.get("/", function(req, res) {
	res.sendFile(path.join(__dirname, "../public/html/settings.html"));
});

router.post("/changeNickname", function(req, res) {
	var data = {
		userid: "temp",
		email: "",
		password: "",
		nickname: req.body.nickname,
		phone: "",
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
		userid: "temp",
		email: req.body.email,
		password: "",
		nickname: "",
		phone: "",
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
		userid: "temp",
		email: "",
		password: req.body.password,
		nickname: "",
		phone: "",
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
		userid: "temp",
		email: "",
		password: "",
		nickname: "",
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
