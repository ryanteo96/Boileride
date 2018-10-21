let pickup;
let destination;
let traveltime;
let price;
let basePrice;

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
		cookie: obj.cookie,
	},
	function(res) {
		switch (res.body.result) {
			case 0: {
				$("html").show();
				break;
			}
			case 2: {
				window.location.href = "/signin";
				break;
			}
			default: {
				$("html").show();
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

							basePrice = parseInt(results[0].distance.text);

							priceOutput.value =
								basePrice * $("#passengers").val();
							traveltime = results[0].duration.value;
							price = basePrice * $("#passengers").val();
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

							basePrice = parseInt(results[0].distance.text);

							priceOutput.value =
								basePrice * $("#passengers").val();
							traveltime = results[0].duration.value;
							price = basePrice * $("#passengers").val();
						}
					},
				);
			}
		},
	);
}

google.maps.event.addDomListener(window, "load", init);

$(document).ready(function() {
	$("#confirmEditBtn").prop("disabled", true);
	loadOriginalValue();
	$("#editRideRequestForm").submit(function(data) {
		data.preventDefault();

		var credentials = localStorage.getItem("credentials");
		var obj = JSON.parse(credentials);

		var editRequest = localStorage.getItem("editRequest");
		var edit = JSON.parse(editRequest);

		$.post(
			"/myRides/myRequest/edit",
			{
				userid: obj.userid,
				cookie: obj.cookie,
				requestid: edit.requestid,
				pickuplocation: pickup.value,
				destination: destination.value,
				date: $("#date").val(),
				time: $("#time").val(),
				passengers: $("#passengers").val(),
				luggage: $("#luggage").val(),
				smoking: $("#smoking").prop("checked"),
				foodndrink: $("#foodndrink").prop("checked"),
				pets: $("#pets").prop("checked"),
				ac: $("#ac").prop("checked"),
				travelingtime: traveltime,
				price: price,
			},
			function(res) {
				switch (res.body.result) {
					case 0: {
						alert("Ride Request successfully updated.");
						window.location.href = "/myRides/myRequest";
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
						alert("Not authorized to update.");
						break;
					}
					case 4: {
						alert("Ride not exist.");
						break;
					}
					case 5: {
						alert("Ride cancelled.");
						break;
					}
					case 6: {
						alert("Invalid pickup location.");
						break;
					}
					case 7: {
						alert("Invalid destination.");
					}
					case 8: {
						alert("Invalid datentime.");
					}
					case 9: {
						alert("Not enough points.");
					}
					case 10: {
						alert("Invalid price.");
					}
					case 11: {
						alert("Invalid traveling time.");
					}
				}
			},
		);
	});
});

function loadOriginalValue() {
	var editRequest = localStorage.getItem("editRequest");
	var obj = JSON.parse(editRequest);

	var time = moment.duration("04:00:00");
	var converteddatentime = moment(obj.datentime);
	converteddatentime.subtract(time);
	datentime = converteddatentime.format("YYYY-MM-DD HH:mm").split(" ");
	var date = datentime[0];
	var time = datentime[1];

	$("#pickuplocation").val(obj.pickuplocation);
	$("#destination").val(obj.destination);
	$("#date").val(date);
	$("#time").val(time);
	$("#passengers").val(obj.passengers);
	$("#luggage").val(obj.luggage);

	if (obj.smoking == "Yes") {
		$("#smoking").prop("checked", true);
	} else {
		$("#smoking").prop("checked", false);
	}

	if (obj.foodndrink == "Yes") {
		$("#foodndrink").prop("checked", true);
	} else {
		$("#foodndrink").prop("checked", false);
	}

	if (obj.pets == "Yes") {
		$("#pets").prop("checked", true);
	} else {
		$("#pets").prop("checked", false);
	}

	if (obj.ac == "Yes") {
		$("#ac").prop("checked", true);
	} else {
		$("#ac").prop("checked", false);
	}

	var service = new google.maps.DistanceMatrixService();

	pickup = document.getElementById("pickuplocation");
	destination = document.getElementById("destination");

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
				var travelTimeOutput = document.getElementById("traveltime");
				var priceOutput = document.getElementById("price");

				var results = response.rows[0].elements;
				travelTimeOutput.value = results[0].duration.text;

				basePrice = parseInt(results[0].distance.text);

				priceOutput.value = basePrice * $("#passengers").val();
				traveltime = results[0].duration.value;
				price = basePrice * $("#passengers").val();

				$("#confirmEditBtn").prop("disabled", false);
			}
		},
	);
}
