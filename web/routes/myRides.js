const express = require("express");
const path = require("path");
const router = express.Router();

router.get("/", function(req, res) {
	res.sendFile(path.join(__dirname, "../public/html/myRides.html"));
});

router.get("/myRequest", function(req,res){
	res.sendFile(path.join(__dirname, "../public/html/myRequest.html"));
});

router.get("/myOffer", function(req,res){
	res.sendFile(path.join(__dirname, "../public/html/myOffer.html"));
});

module.exports = router;
