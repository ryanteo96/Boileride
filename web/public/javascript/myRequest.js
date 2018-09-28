$(document).ready(function() {
	generateRequestList();
	$("#myRequest").click(function(data) {
		data.preventDefault();

		// var credentials = localStorage.getItem("credentials");
		// var obj = JSON.parse(credentials);

		// console.log(obj.userid);

		$.post(
			"/myRides/myRequest",
			{
				// userid: obj.userid,
				userid: "temp",
			},
			function(res) {
				switch (res.result) {
					case 0: {
						console.log("IM HERE!!");
						localStorage.key = "requestList";
						localStorage.setItem(
							"requestList",
							JSON.stringify({
								requestlist: res.requestlist,
							}),
						);
						break;
					}
					case 1: {
						alert("Invalid userid.");
						break;
					}
					case 2: {
						alert("User not logged in.");
						break;
					}
				}
			},
		);
	});
});

function generateRequestList() {
	var options = {
		valueNames: [
			"requestid",
			"requestedby",
			"travelingtime",
			"price",
			"datentime",
			"pickuplocation",
			"destination",
			"passengers",
			"luggage",
			"smoking",
			"foodndrink",
			"pets",
			"ac",
			"status",
		],
		item:
			"<li>" +
			'<div class="row" style="font-size:20px">' +
			'<div class="col-3">' +
			'<a class="requestid" style="font-weight: bold;"></a>' +
			"</div>" +
			'<div class="col-3">' +
			'<a class="requestedby" style="font-weight: bold;"></a>' +
			"</div>" +
			'<div class="col-3">' +
			'<a class="travelingtime" style="font-weight: bold;"></a>' +
			"</div>" +
			'<div class="col-3">' +
			'<a class="price" style="font-weight: bold;"></a>' +
			"</div>" +
			"</div>" +
			'<div class="row" style="font-size:20px">' +
			'<div class="col-3">' +
			'<a class="datentime" style="font-weight: bold;"></a>' +
			"</div>" +
			'<div class="col-3">' +
			'<a class="pickuplocation" style="font-weight: bold;"></a>' +
			"</div>" +
			'<div class="col-3">' +
			'<a class="destination" style="font-weight: bold;"></a>' +
			"</div>" +
			'<div class="col-3">' +
			'<a class="luggage" style="font-weight: bold;"></a>' +
			"</div>" +
			"</div>" +
			'<div class="row" style="font-size:20px">' +
			'<div class="col">' +
			'<a class="smoking" style="font-weight: bold;"></a>' +
			"</div>" +
			'<div class="col">' +
			'<a class="foodndrink" style="font-weight: bold;"></a>' +
			"</div>" +
			'<div class="col">' +
			'<a class="pets" style="font-weight: bold;"></a>' +
			"</div>" +
			'<div class="col">' +
			'<a class="ac" style="font-weight: bold;"></a>' +
			"</div>" +
			"</div>" +
			"</div>" +
			'<div class="row" style="font-size:20px">' +
			'<div class="col-3">' +
			'<a class="status" style="font-weight: bold;"></a>' +
			"</div>" +
			'<div class="col-3">' +
			'<a class="passengers" style="font-weight: bold;"></a>' +
			"</div>" +
			"</div>" +
			"</li>",
	};

	var requestList = new List("myRequestList", options);

	for (var i = 0; i < 10; i++) {
		requestList.add({
			requestid: "ID: " + i,
			requestedby: "Requested By: " + i,
			travelingtime: "Travel Time: " + i,
			price: "Price: $" + i,
			datentime: "Date: " + i,
			pickuplocation: "Pickup location: " + i,
			destination: "Destination: " + i,
			passengers: "No. of Passengers: " + i,
			luggage: "No. of Luggage: " + i,
			smoking: "Allow Smoking: " + i,
			foodndrink: "Allow Food And Drink: " + i,
			pets: "Allow Pets: " + i,
			ac: "Request AC: " + i,
			status: "Status: " + i,
		});
	}
}
