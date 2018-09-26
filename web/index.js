// dependencies
const express = require("express");
const path = require("path");
const fs = require("fs");
const http = require("http");
const https = require("https");
const bodyParser = require("body-parser");

const app = express();
// route declarations
const routes = require("./routes/index");
const signIn = require("./routes/signIn");
const signUp = require("./routes/signUp");
const signOut = require("./routes/signOut");
const verifyEmail = require("./routes/verifyEmail");
const forgotPw = require("./routes/forgotPw");
const home = require("./routes/home");
const myRides = require("./routes/myRides");
const settings = require("./routes/settings");
const createRideOffer = require("./routes/createRideOffer");
const createRideRequest = require("./routes/createRideRequest");
const searchRideOffer = require("./routes/searchRideOffer");
const searchRideRequest = require("./routes/searchRideRequest");

app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());
app.use(express.static("public"));

app.use("/", routes);
app.use("/signIn", signIn);
app.use("/signUp", signUp);
app.use("/signOut", signOut);
app.use("/verifyEmail", verifyEmail);
app.use("/forgotPw", forgotPw);
app.use("/home", home);
app.use("/myRides", myRides);
app.use("/settings", settings);
app.use("/createRideOffer", createRideOffer);
app.use("/createRideRequest", createRideRequest);
app.use("/searchRideOffer", searchRideOffer);
app.use("/searchRideRequest", searchRideRequest);

// https setup
// const privateKey = fs.readFileSync(
// 	"/etc/letsencrypt/live/boileride.ryanteo96.tech/privkey.pem",
// 	"utf8",
// );
// const certificate = fs.readFileSync(
// 	"/etc/letsencrypt/live/boileride.ryanteo96.tech/cert.pem",
// 	"utf8",
// );
// const ca = fs.readFileSync(
// 	"/etc/letsencrypt/live/boileride.ryanteo96.tech/chain.pem",
// 	"utf8",
// );

// const credentials = {
// 	key: privateKey,
// 	cert: certificate,
// 	ca: ca,
// };

// const httpsServer = https.createServer(credentials, app);

// httpsServer.listen(443, () => {
// 	console.log("HTTPS Server running on port 443");
// });

// http setup for local testing
const httpServer = http.createServer(app);

httpServer.listen(80, () => {
	console.log("HTTP Server running on port 80");
});
