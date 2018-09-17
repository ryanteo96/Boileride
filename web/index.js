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
const home = require("./routes/home");
const myRides = require("./routes/myRides");
const settings = require("./routes/settings");

app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());
app.use(express.static("public"));

app.use("/", routes);
app.use("/signIn", signIn);
app.use("/signUp", signUp);
app.use("/signOut", signOut);
app.use("/home", home);
app.use("/myRides", myRides);
app.use("/settings", settings);

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
