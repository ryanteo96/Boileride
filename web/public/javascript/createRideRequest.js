let traveltime;
let pickup;
let destination;

function init() {
	pickup = document.getElementById("pickuplocation");
	destination = document.getElementById("destination");

	var pickup_autocomplete = new google.maps.places.Autocomplete(
		pickuplocation,
	);

	var dest_autocomplete = new google.maps.places.Autocomplete(destination);

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
						unitSystem: google.maps.UnitSystem.METRIC,
						avoidHighways: false,
						avoidTolls: false,
					},
					function(response, status) {
						if (status !== "OK") {
							alert("Error was: " + status);
						} else {
							var output = document.getElementById("traveltime");
							var results = response.rows[0].elements;
							output.value = results[0].duration.text;
							traveltime = results[0].duration.value;
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
						unitSystem: google.maps.UnitSystem.METRIC,
						avoidHighways: false,
						avoidTolls: false,
					},
					function(response, status) {
						if (status !== "OK") {
							alert("Error was: " + status);
						} else {
							var output = document.getElementById("traveltime");
							var results = response.rows[0].elements;
							output.value = results[0].duration.text;
							traveltime = results[0].duration.value;
						}
					},
				);
			}
		},
	);
}

google.maps.event.addDomListener(window, "load", init);
