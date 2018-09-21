const express = require("express");
const path = require("path");
const router = express.Router();

router.get("/", function(req, res) {
	res.sendFile(path.join(__dirname, "../public/html/signUp.html"));
});

router.post("/", function(req, res) {
	var data = {
		email: req.body.email,
		password: req.body.password,
		nickname: req.body.nickname,
		phone: req.body.phone,
	};

	res.send("0");
	console.log(data);

	// temp server connection test
	// var options = {
	// 	uri: "http://localhost:8080/user/signup",
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

	// res.redirect("/home");
});

module.exports = router;
