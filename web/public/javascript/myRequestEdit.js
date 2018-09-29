let pickup;
let destination;
let traveltime;
let price;

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

							if (results[0].distance.text.indexOf(".") >= 0) {
								var arr = results[0].distance.text.split(".");
							} else {
								var arr = results[0].distance.text.split(" mi");
							}

							priceOutput.value = arr[0];
							traveltime = results[0].duration.value;
							price = arr[0];
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

							if (results[0].distance.text.indexOf(".") >= 0) {
								var arr = results[0].distance.text.split(".");
							} else {
								var arr = results[0].distance.text.split(" mi");
							}

							priceOutput.value = arr[0];
							traveltime = results[0].duration.value;
							price = arr[0];
						}
					},
				);
			}
		},
	);
}

google.maps.event.addDomListener(window, "load", init);

$(document).ready(function() {
	loadOriginalValue();
	$("#createRideRequestForm").submit(function(data) {
		data.preventDefault();

		var credentials = localStorage.getItem("credentials");
		var obj = JSON.parse(credentials);

		var editRequest = localStorage.getItem("editRequest");
		var edit = JSON.parse(editRequest);

		$.post(
			"/myRides/myRequest/edit",
			{
				userid: obj.userid,
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
				switch (res.result) {
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
				}
			},
		);
	});
});

function loadOriginalValue() {
	// var requestlist = localStorage.getItem("test");
	var editRequest = localStorage.getItem("editRequest");
	var obj = JSON.parse(editRequest);

	var datentime = obj.datentime.split(" ");
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

	$("#traveltime").val(obj.travelingtime);
	$("#price").val(obj.price);
}
