let pickup;
let destination;
let requestList;

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
}

google.maps.event.addDomListener(window, "load", init);

$(document).ready(function() {
	$("#searchRideRequestForm").submit(function(data) {
		data.preventDefault();

		var credentials = localStorage.getItem("credentials");
		var obj = JSON.parse(credentials);

		$("#loading").modal({
			backdrop: "static", //remove ability to close modal with click
			keyboard: false, //remove option to close with keyboard
			show: true, // display loader
		});

		$("#loading").on("shown.bs.modal", function() {
			$.post(
				"/searchRideRequest",
				{
					userid: obj.userid,
					cookie: obj.cookie,
					pickuplocation: pickup.value,
					destination: destination.value,
					pickupproximity: $("#pickupproximity").val(),
					destinationproximity: $("#destinationproximity").val(),
					date: $("#date").val(),
					time: $("#time").val(),
					datentimerange: $("#datentimerange").val(),
					seats: $("#seats").val(),
					luggage: $("#luggage").val(),
					smoking: $("#smoking").prop("checked"),
					foodndrink: $("#foodndrink").prop("checked"),
					pets: $("#pets").prop("checked"),
					ac: $("#ac").prop("checked"),
				},
				function(res) {
					switch (res.body.result) {
						case 0: {
							console.log(res.body.rides);
							localStorage.key = "searchResults";
							localStorage.setItem(
								"searchResults",
								JSON.stringify(res.body.rides),
							);

							$("#loading").modal("hide"); // hide loader
							window.location.href = "/searchRideRequestResults";
							break;
						}
						case 1: {
							$("#loading").modal("hide"); // hide loader
							alert("Invalid userid.");
							break;
						}
						case 2: {
							$("#loading").modal("hide"); // hide loader
							alert("User not logged in.");
							break;
						}
						case 3: {
							$("#loading").modal("hide"); // hide loader
							alert("Invalid pickup location.");
							break;
						}
						case 4: {
							$("#loading").modal("hide"); // hide loader
							alert("Invalid destination.");
							break;
						}
						case 5: {
							$("#loading").modal("hide"); // hide loader
							alert("Invalid pickup proximity.");
							break;
						}
						case 6: {
							$("#loading").modal("hide"); // hide loader
							alert("Invalid destination proximity.");
							break;
						}
						case 7: {
							$("#loading").modal("hide"); // hide loader
							alert("Invalid datentime.");
							break;
						}
						case 8: {
							$("#loading").modal("hide"); // hide loader
							alert("Invalid datentime range.");
							break;
						}
						case 9: {
							$("#loading").modal("hide"); // hide loader
							alert("No results found.");
							break;
						}
						default: {
							$("#loading").modal("hide"); // hide loader
							alert("Invalid request.");
							break;
						}
					}
				},
			);
		});
	});
});
