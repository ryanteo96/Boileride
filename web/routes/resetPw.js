const express = require("express");
const path = require("path");
const router = express.Router();

router.get("/", function(req, res) {
	res.sendFile(path.join(__dirname, "../public/html/resetPw.html"));
});


router.post("/", function(req, res) {
	var data = {
		email: req.body.email
	};
	console.log("print something" + req.body.email);
	console.log(data);

	// temp server connection test
	var options = {
		uri: "http://localhost:8080/user/resetpassword",
		json: data,
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
	};

	request(options, function(error, response) {
		console.log(error, response);
		return;
	});

	res.redirect("/home");
});

module.exports = router;
