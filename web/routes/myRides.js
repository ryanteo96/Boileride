const express = require("express");
const path = require("path");
const router = express.Router();
const request = require("request");

router.get("/", function(req, res) {
	res.sendFile(path.join(__dirname, "../public/html/myRides.html"));
});
router.get("/myRequest", function(req, res) {
	res.sendFile(path.join(__dirname, "../public/html/myRequest.html"));
});
router.get("/myOffer", function(req, res) {
	res.sendFile(path.join(__dirname, "../public/html/myOffer.html"));
});

router.post("/myRequest", function(req,res){
	var data = {							
		// userid: req.body.userid
		userid:"temp"
	};
	var body = {
		result: 0
	}
	res.send(body);
	console.log("body is " + body);
	
	console.log("user id is now " + data);

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
