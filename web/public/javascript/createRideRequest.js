function init() {
	var pickuplocation = document.getElementById("pickuplocation");
	var destination = document.getElementById("destination");

	var pickup_autocomplete = new google.maps.places.Autocomplete(
		pickuplocation,
	);

	var dest_autocomplete = new google.maps.places.Autocomplete(destination);

	var service = new google.maps.DistanceMatrixService();

	google.maps.event.addListener(
		pickup_autocomplete,
		"place_changed",
		function() {
			if (dest_autocomplete.getPlace()) {
				var pickup = pickup_autocomplete.getPlace();
				var destination = dest_autocomplete.getPlace();

				var origin = {
					lat: pickup.geometry.location.lat(),
					lng: pickup.geometry.location.lng(),
				};

				var dest = {
					lat: destination.geometry.location.lat(),
					lng: destination.geometry.location.lng(),
				};

				service.getDistanceMatrix(
					{
						origins: [origin],
						destinations: [dest],
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
			if (pickup_autocomplete.getPlace()) {
				var pickup = pickup_autocomplete.getPlace();
				var destination = dest_autocomplete.getPlace();

				var origin = {
					lat: pickup.geometry.location.lat(),
					lng: pickup.geometry.location.lng(),
				};

				var dest = {
					lat: destination.geometry.location.lat(),
					lng: destination.geometry.location.lng(),
				};

				service.getDistanceMatrix(
					{
						origins: [origin],
						destinations: [dest],
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
						}
					},
				);
			}
		},
	);
}

google.maps.event.addDomListener(window, "load", init);
