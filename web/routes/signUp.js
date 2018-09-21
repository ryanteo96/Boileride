const express = require("express");
const path = require("path");
const router = express.Router();

router.get("/", function(req, res) {
	res.sendFile(path.join(__dirname, "../public/html/signUp.html"));
});

router.post("/", function(req, res) {
	var data = {
		nickname: req.body.nickname,
		email: req.body.email,
		password: req.body.password,
		phone: req.body.phone
	};

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
	// 	return;
	// });

	res.redirect("/signIn");
});


module.exports = router;
