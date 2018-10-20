const express = require("express");
const path = require("path");
const moment = require("moment");
const request = require("request");

const router = express.Router();

router.get("/", function(req, res) {
	res.sendFile(
		path.join(__dirname, "../public/html/searchRideRequestResults.html"),
	);
});

module.exports = router;
