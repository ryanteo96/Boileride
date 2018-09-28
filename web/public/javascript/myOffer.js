$(document).ready(function() {
	generateOfferList();
	$("#myOffer").click(function(data) {
		data.preventDefault();

		var credentials = localStorage.getItem("credentials");
		var obj = JSON.parse(credentials);

		console.log(obj.userid);

		$.post(
			"/myRides/myOffer",
			{
				userid: obj.userid,
			},
			function(res) {
				switch (res.result) {
					case 0: {
						localStorage.key = "offerList";
						localStorage.setItem(
							"offerList",
							JSON.stringify({
								offerlist: res.offerlist,
							}),
						);
						window.location.href = "/myOffer";
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

function generateOfferList() {
	var options = {
		valueNames: [
			"offerid",
			"offeredby",
			"travelingtime",
			"seatsleft",
			"luggageleft",
			"price",
			"datentime",
			"pickuplocation",
			"destination",
			"seats",
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
			'<a class="offerid" style="font-weight: bold;"></a>' +
			"</div>" +
			'<div class="col-3">' +
			'<a class="offeredby" style="font-weight: bold;"></a>' +
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
			'<a class="seats" style="font-weight: bold;"></a>' +
			"</div>" +
			'<div class="col-3">' +
			'<a class="seatsleft" style="font-weight: bold;"></a>' +
			"</div>" +
			'<div class="col-3">' +
			'<a class="luggageleft" style="font-weight: bold;"></a>' +
			"</div>" +
			"</div>" +
			"</li>",
	};

	var offerList = new List("myOfferList", options);

	for (var i = 0; i < 10; i++) {
		offerList.add({
			offerid: "ID: " + i,
			offeredby: "Offered By: " + i,
			travelingtime: "Travel Time: " + i,
			price: "Price: $" + i,
			datentime: "Date: " + i,
			pickuplocation: "Pickup location: " + i,
			destination: "Destination: " + i,
			seats: "Available Seats: " + i,
			luggage: "Available Luggage Space: " + i,
			smoking: "Allow Smoking: " + i,
			foodndrink: "Allow Food And Drink: " + i,
			pets: "Allow Pets: " + i,
			ac: "Request AC: " + i,
			status: "Status: " + i,
			seatsleft: "Seats left: " + i,
			luggageleft: "Luggage space left: " + i,
		});
	}
}
