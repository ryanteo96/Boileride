let pickup;
let destination;
let traveltime;
let price;

$("html").hide();

var credentials = localStorage.getItem("credentials");
var obj = JSON.parse(credentials);

if (!obj) {
	window.location.href = "/signin";
}

$.post(
	"/authLoggedIn",
	{
		userid: obj.userid,
	},
	function(res) {
		switch (res.result) {
			case 0: {
				$("html").show();
				$("#nickname").text("Hello, " + res.nickname);
				break;
			}
			case 2: {
				window.location.href = "/signin";
				break;
			}
			default: {
				$("html").show();
				$("#nickname").text("Hello, " + res.nickname);
				break;
			}
		}
	},
);

function init() {
	pickup = document.getElementById("pickuplocation");
	destination = document.getElementById("destination");

	var pickup_autocomplete = new google.maps.places.Autocomplete(
		pickuplocation,
		{ fields: ["place_id", "formatted_address"] },
	);

	var dest_autocomplete = new google.maps.places.Autocomplete(destination, {
		fields: ["place_id", "formatted_address"],
	});

	var service = new google.maps.DistanceMatrixService();

	google.maps.event.addListener(
		pickup_autocomplete,
		"place_changed",
		function() {
			if (destination.value) {
				service.getDistanceMatrix(
					{
						origins: [pickup.value],
						destinations: [destination.value],
						travelMode: "DRIVING",
						unitSystem: google.maps.UnitSystem.IMPERIAL,
						avoidHighways: false,
						avoidTolls: false,
					},
					function(response, status) {
						if (status !== "OK") {
							alert("Error was: " + status);
						} else {
							var travelTimeOutput = document.getElementById(
								"traveltime",
							);
							var priceOutput = document.getElementById("price");

							var results = response.rows[0].elements;
							travelTimeOutput.value = results[0].duration.text;

							priceOutput.value = parseInt(
								results[0].distance.text,
							);
							traveltime = results[0].duration.value;
							price = parseInt(results[0].distance.text);
						}
					},
				);
			}
		},
	);

	google.maps.event.addListener(
		dest_autocomplete,
		"place_changed",
		function() {
			if (pickup.value) {
				service.getDistanceMatrix(
					{
						origins: [pickup.value],
						destinations: [destination.value],
						travelMode: "DRIVING",
						unitSystem: google.maps.UnitSystem.IMPERIAL,
						avoidHighways: false,
						avoidTolls: false,
					},
					function(response, status) {
						if (status !== "OK") {
							alert("Error was: " + status);
						} else {
							var travelTimeOutput = document.getElementById(
								"traveltime",
							);
							var priceOutput = document.getElementById("price");

							var results = response.rows[0].elements;
							travelTimeOutput.value = results[0].duration.text;

							priceOutput.value = parseInt(
								results[0].distance.text,
							);
							traveltime = results[0].duration.value;
							price = parseInt(results[0].distance.text);
						}
					},
				);
			}
		},
	);
}

google.maps.event.addDomListener(window, "load", init);

$(document).ready(function() {
	$("#createRideOfferForm").submit(function(data) {
		data.preventDefault();

		var credentials = localStorage.getItem("credentials");
		var obj = JSON.parse(credentials);

		$.post(
			"/createRideOffer",
			{
				userid: obj.userid,
				pickuplocation: pickup.value,
				destination: destination.value,
				date: $("#date").val(),
				time: $("#time").val(),
				seats: $("#seats").val(),
				luggage: $("#luggage").val(),
				smoking: $("#smoking").prop("checked"),
				foodndrink: $("#foodndrink").prop("checked"),
				pets: $("#pets").prop("checked"),
				ac: $("#ac").prop("checked"),
				travelingtime: traveltime,
				price: price,
			},
			function(res) {
				switch (res.result) {
					case 0: {
						alert("Ride Offer successfully created.");
						window.location.href = "/home";
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
					case 3: {
						alert("Invalid pickup location.");
						break;
					}
					case 4: {
						alert("Invalid destination.");
						break;
					}
					case 5: {
						alert("Invalid datentime.");
						break;
					}
					case 6: {
						alert("Not enough points.");
						break;
					}
				}
			},
		);
	});
});
