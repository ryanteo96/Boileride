const express = require("express");
const path = require("path");
const router = express.Router();
const request = require("request");

router.get("/", function(req, res) {
	res.sendFile(path.join(__dirname, "../public/html/myRides.html"));
});

router.post("/myRequest", function(req,res){
	res.sendFile(path.join(__dirname, "../public/html/myRequest.html"));
	var data = {							
		userid: req.body.userid
	};
	var body = {
		result: 0
	}
	res.send(body);
	console.log("body is " + body);
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
	// 	res.send(response.body);	
	// 	return;
	// });
	
});

router.post("/myOffer", function(req,res){
	res.sendFile(path.join(__dirname, "../public/html/myOffer.html"));
	var data = {
		userid: req.body.userid
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
	//  res.send(response.body);
	// 	return;
	// });
});

module.exports = router;
