function init() {
	var pickuplocation = document.getElementById("pickuplocation");
	var destination = document.getElementById("destination");

	var pickup_autocomplete = new google.maps.places.Autocomplete(
		pickuplocation,
	);
	var dest_autocomplete = new google.maps.places.Autocomplete(destination);
}

google.maps.event.addDomListener(window, "load", init);
