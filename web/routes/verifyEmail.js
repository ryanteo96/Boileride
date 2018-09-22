const express = require("express");
const path = require("path");
const router = express.Router();

router.get("/", function(req, res) {
	res.sendFile(path.join(__dirname, "../public/html/verifyEmail.html"));
});

router.post("/", function(req, res) {
	var data = {
		email: req.body.email,
	    code: req.body.code
	};

	console.log(data);
    res.send("0")
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
	// 	return;
	// });
	
});

module.exports = router;
