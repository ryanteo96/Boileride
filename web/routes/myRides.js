const express = require("express");
const path = require("path");
const router = express.Router();

router.get("/", function(req, res) {
	res.sendFile(path.join(__dirname, "../public/html/myRides.html"));
});

router.get("/myRequest", function(req,res){
	res.sendFile(path.join(__dirname, "../public/html/myRequest.html"));
	var data = {
		userid: req.body.userid,
	};

	console.log(data);

	//  temp server connection test
	// var options = {
	// 	uri: "http://localhost:8080/ride/view/request",
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

router.get("/myOffer", function(req,res){
	res.sendFile(path.join(__dirname, "../public/html/myOffer.html"));
	var data = {
		userid: req.body.userid,
	};
	console.log(data);

	//  temp server connection test
	// var options = {
	// 	uri: "http://localhost:8080/ride/view/offer",
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
