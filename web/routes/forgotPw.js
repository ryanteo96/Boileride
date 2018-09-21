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
	
	//var myJSON = JSON.stringify(data);
	// console.log(myJSON);
	//localStorageSet('email', myJSON);
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

	res.redirect("/resetPw");
});

module.exports = router;
