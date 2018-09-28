const express = require("express");
const path = require("path");
const request = require("request");

const router = express.Router();

router.post("/", function(req, res) {
	var data = {
		userid: req.body.userid,
	};

	console.log(data);

	// var options = {
	// 	uri: "http://localhost:8080/user/logout",
	// 	json: data,
	// 	method: "POST",
	// 	headers: {
	// 		"Content-Type": "application/json",
	// 	},
	// };

	// request(options, function(error, response) {
	// 	if (response) {
	// 		res.send(response.body);
	// 	}
	// 	return;
	// });
});

module.exports = router;
