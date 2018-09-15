// Dependencies
const express = require("express");
const path = require("path");
const fs = require("fs");
const http = require("http");
const https = require("https");

const app = express();

const routes = require("./routes/index");
const signIn = require("./routes/signIn");
const signUp = require("./routes/signUp");

app.use("/", routes);
app.use("/signIn", signIn);
app.use("/signUp", signUp);

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

// for local testing
const httpServer = http.createServer(app);

httpServer.listen(80, () => {
	console.log("HTTP Server running on port 80");
});
