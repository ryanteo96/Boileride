const express = require("express");
const path = require("path");
const bodyParser = require("body-parser");
const router = express.Router();

router.get("/", function(req, res) {
	res.sendFile(path.join(__dirname, "../public/html/signIn.html"));
});


router.post("/", function(req, res) {
	var data = {
		email: req.body.email,
		password: req.body.password,
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

	res.redirect("/home");
});

module.exports = router;