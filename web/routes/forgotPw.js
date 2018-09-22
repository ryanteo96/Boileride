const express = require("express");
const path = require("path");
const router = express.Router();

router.get("/", function(req, res) {
	res.sendFile(path.join(__dirname, "../public/html/forgotPw.html"));
});

router.post("/", function(req, res) {
	var data = {
		email: req.body.email,			
	};
	
	res.send("0");
	console.log(data);

	// temp server connection test
	// var options = {
	// 	uri: "http://localhost:8080/user/forgotpassword",
	// 	json: data,
	// 	method: "POST",
	// 	headers: {
	// 		"Content-Type": "application/json",
	// 	},
	// };

	// request(options, function(error, res) {
	// 	console.log(error, res);
	// 	return;
	// });
});

module.exports = router;
