let pickup;
let destination;

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
}

google.maps.event.addDomListener(window, "load", init);

$(document).ready(function() {
	$("#searchRideOfferForm").submit(function(data) {
		data.preventDefault();

		var credentials = localStorage.getItem("credentials");
		var obj = JSON.parse(credentials);

		$.post(
			"/searchRideOffer",
			{
				userid: obj.userid,
				pickuplocation: pickup.value,
				destination: destination.value,
				pickupproximity: $("#pickupproximity").val(),
				destinationproximity: $("#destinationproximity").val(),
				date: $("#date").val(),
				time: $("#time").val(),
				datentimerange: $("#datentimerange").val(),
				passengers: $("#passengers").val(),
				numrides: $("#numrides").val(),
				luggage: $("#luggage").val(),
				smoking: $("#smoking").prop("checked"),
				foodndrink: $("#foodndrink").prop("checked"),
				pets: $("#pets").prop("checked"),
				ac: $("#ac").prop("checked"),
			},
			function(res) {
				switch (res.result) {
					case 0: {
						console.log(res.offersearchlist);
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
						alert("Invalid pickup proximity.");
						break;
					}
					case 6: {
						alert("Invalid destination proximity.");
						break;
					}
					case 7: {
						alert("Invalid datentime.");
						break;
					}
					case 8: {
						alert("Invalid datentime range.");
						break;
					}
					case 8: {
						alert("No results found.");
						break;
					}
				}
			},
		);
	});
});
