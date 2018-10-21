let pickup;
let destination;
let offerlist;

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

let test = [
	{
		duration: 10,
		rides: [
			{
				offertid: 1,
				offeredby: "test",
				pickuplocation: "test2",
				destination: "test3",
				datentime: "2018/01/01",
				seats: 4,
				seatsleft: 2,
				luggage: 4,
				luggageleft: 2,
				smoking: "true",
				foodndrink: "false",
				pets: "false",
				ac: "true",
				travelingtime: 3,
				price: 100,
				status: "true",
			},
			{
				offertid: 2,
				offeredby: "test4",
				pickuplocation: "test3",
				destination: "test4",
				datentime: "2018/01/01",
				seats: 3,
				seatsleft: 1,
				luggage: 3,
				luggageleft: 1,
				smoking: "false",
				foodndrink: "false",
				pets: "false",
				ac: "true",
				travelingtime: 5,
				price: 200,
				status: "true",
			},
			{
				offertid: 3,
				offeredby: "test7",
				pickuplocation: "test4",
				destination: "test5",
				datentime: "2018/01/01",
				seats: 3,
				seatsleft: 1,
				luggage: 3,
				luggageleft: 1,
				smoking: "false",
				foodndrink: "false",
				pets: "false",
				ac: "true",
				travelingtime: 5,
				price: 200,
				status: "true",
			},
		],
	},
	{
		duration: 20,
		rides: [
			{
				offertid: 5,
				offeredby: "test3",
				pickuplocation: "test10",
				destination: "test11",
				datentime: "2018/01/01",
				seats: 4,
				seatsleft: 2,
				luggage: 4,
				luggageleft: 2,
				smoking: "true",
				foodndrink: "false",
				pets: "false",
				ac: "true",
				travelingtime: 3,
				price: 700,
				status: "true",
			},
			{
				offertid: 6,
				offeredby: "test5",
				pickuplocation: "test11",
				destination: "test12",
				datentime: "2018/01/01",
				seats: 3,
				seatsleft: 1,
				luggage: 3,
				luggageleft: 1,
				smoking: "false",
				foodndrink: "false",
				pets: "false",
				ac: "true",
				travelingtime: 5,
				price: 200,
				status: "true",
			},
			{
				offertid: 7,
				offeredby: "test9",
				pickuplocation: "test12",
				destination: "test13",
				datentime: "2018/01/01",
				seats: 3,
				seatsleft: 1,
				luggage: 3,
				luggageleft: 1,
				smoking: "false",
				foodndrink: "false",
				pets: "false",
				ac: "true",
				travelingtime: 5,
				price: 200,
				status: "true",
			},
		],
	},
];

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
	// localStorage.key = "searchResults";
	// localStorage.setItem("searchResults", JSON.stringify(test));

	// window.location.href = "/searchRideOfferResults";

	$("#searchRideOfferForm").submit(function(data) {
		data.preventDefault();

		var credentials = localStorage.getItem("credentials");
		var obj = JSON.parse(credentials);

		$("#loading").modal({
			backdrop: "static", //remove ability to close modal with click
			keyboard: false, //remove option to close with keyboard
			show: true, // display loader
		});

		var search = [
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
		];

		localStorage.key = "search";
		localStorage.setItem("search", JSON.stringify(search));

		$("#loading").on("shown.bs.modal", function() {
			$.post(
				"/searchRideOffer",
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
					passengers: $("#passengers").val(),
					numrides: $("#numrides").val(),
					luggage: $("#luggage").val(),
					smoking: $("#smoking").prop("checked"),
					foodndrink: $("#foodndrink").prop("checked"),
					pets: $("#pets").prop("checked"),
					ac: $("#ac").prop("checked"),
				},
				function(res) {
					switch (res.body.result) {
						case 0: {
							res.body.trips.sort(function(a, b) {
								var dateA = new Date(a.rides[0].datentime),
									dateB = new Date(b.rides[0].datentime);

								console.log(a.rides[0].datentime);

								if (a.duration - b.duration) {
									return a.duration - b.duration;
								} else {
									return dateA - dateB;
								}
							});

							localStorage.key = "searchResults";
							localStorage.setItem(
								"searchResults",
								JSON.stringify(res.body.trips),
							);

							$("#loading").modal("hide"); // hide loader
							window.location.href = "/searchRideOfferResults";
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

	$("#joinRideBtn").click(function(data) {
		data.preventDefault();

		var credentials = localStorage.getItem("credentials");
		var obj = JSON.parse(credentials);

		$.post(
			"/searchRideOffer/accept",
			{
				userid: obj.userid,
				cookie: obj.cookie,
				offeridlist: "temp",
				passenger: $("#numPassenger").val(),
				luggage: $("#numLuggage").val(),
			},
			function(res) {
				switch (res.body.result) {
					case 0: {
						alert("Success.");
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
						alert("Ride does not exist.");
						break;
					}
					case 4: {
						alert("Cannot join your own offer.");
						break;
					}
					case 5: {
						alert("Already joined.");
						break;
					}
					case 6: {
						alert("Not enough points.");
						break;
					}
					case 7: {
						alert("Invalid number of passengers.");
						break;
					}
					case 8: {
						alert("Invalid number of luggage.");
						break;
					}
					case 9: {
						alert("Invalid number of passengers and luggage.");
						break;
					}
				}
			},
		);
	});
});
