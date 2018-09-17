const express = require("express");
const path = require("path");
const bodyParser = require("body-parser");
const router = express.Router();

router.get("/", function(req, res) {
	res.sendFile(path.join(__dirname, "../public/html/signIn.html"));
});

router.post("/test", function(req, res) {
	let test = req.body;
	console.log(test);
	res.redirect("/home");
});

module.exports = router;
