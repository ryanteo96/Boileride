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

let test = [
	{
		requestid: 1,
		requestedby: "test",
		pickuplocation: "Earhart",
		destination: "Purdue",
		datentime: "1/1/1",
		passengers: 4,
		luggage: 4,
		smoking: "true",
		foodndrink: "false",
		pets: "true",
		ac: "true",
		travelingtime: 3,
		price: 1,
		status: "true",
	},
	{
		requestid: 2,
		requestedby: "test2",
		pickuplocation: "Hillenbrand",
		destination: "Purdue",
		datentime: "1/1/1",
		passengers: 3,
		luggage: 3,
		smoking: "true",
		foodndrink: "false",
		pets: "true",
		ac: "true",
		travelingtime: 3,
		price: 100,
		status: "true",
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
	generateSearchRequestList(test);
	$("#searchRideRequestForm").submit(function(data) {
		data.preventDefault();

		var credentials = localStorage.getItem("credentials");
		var obj = JSON.parse(credentials);

		$.post(
			"/searchRideRequest",
			{
				userid: obj.userid,
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
				switch (res.result) {
					case 0: {
						console.log(res.requestsearchlist);
						generateSearchRequestList(res.requestsearchlist);
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

function generateSearchRequestList(searchlist) {
	var options = {
		valueNames: [
			{ data: ["requestid"] },
			"datentime",
			"pickuplocation",
			"destination",
			"passengers",
			"smoking",
			"ac",
			"foodndrink",
			"pets",
			"luggage",
			"travelingtime",
			"requestedby",
			"price",
		],
		item:
			'<li class="list-group-item items flex-column align-items-start pl-2 pr-2" ondblclick=getItem(this)>' +
			'<div class="row" style="font-size:20px">' +
			'<div class="mb-2 d-flex w-100">' +
			'<h5 class="mb-1 pickuplocation col text-left"></h5>' +
			'<i class="fas fa-arrow-right col-1 icons text-center"></i>' +
			'<h5 class="mb-1 destination col text-right"></h5>' +
			'<small class="datentime col-2 text-right"></small>' +
			"</div>" +
			'<div class="d-flex w-100">' +
			'<i class="icons fas fa-users p-2 col text-center"><small class="values passengers p-2"></small></i>' +
			'<i class="icons fas fa-suitcase p-2 col text-center"><small class="values luggage p-2"></small></i>' +
			'<i class="icons fas fa-smoking p-2 col text-center"><small class="values smoking p-2"></small></i>' +
			'<i class="icons fas fa-cookie-bite p-2 col text-center"><small class="values foodndrink p-2"></small></i>' +
			'<i class="icons fas fa-paw p-2 col text-center"><small class="values pets p-2"></small></i>' +
			'<i class="icons far fa-snowflake p-2 col text-center"><small class="values ac p-2"></small></i>' +
			'<i class="icons fas fa-stopwatch p-2 col text-center"><small class="values travelingtime p-2"></small></i>' +
			'<i class="icons fas fa-user-circle p-2 col text-center"><small class="values requestedby p-2"></small></i>' +
			'<i class="icons fas fa-money-bill p-2 col text-center"><small class="values price p-2"></small></i>' +
			"</div>" +
			"</div>" +
			"</li>",
	};

	requestlist = new List("searchRideRequestList", options);

	for (var i = 0; i < searchlist.length; i++) {
		if (searchlist[i].smoking == "true") {
			searchlist[i].smoking = "Yes";
		} else searchlist[i].smoking = "No";

		if (searchlist[i].ac == "true") {
			searchlist[i].ac = "Yes";
		} else searchlist[i].ac = "No";

		if (searchlist[i].foodndrink == "true") {
			searchlist[i].foodndrink = "Yes";
		} else searchlist[i].foodndrink = "No";

		if (searchlist[i].pets == "true") {
			searchlist[i].pets = "Yes";
		} else searchlist[i].pets = "No";

		requestlist.add({
			requestid: searchlist[i].requestid,
			pickuplocation: searchlist[i].pickuplocation,
			destination: searchlist[i].destination,
			datentime: searchlist[i].datentime,
			passengers: searchlist[i].passengers,
			smoking: searchlist[i].smoking,
			ac: searchlist[i].ac,
			foodndrink: searchlist[i].foodndrink,
			pets: searchlist[i].pets,
			luggage: searchlist[i].luggage,
			travelingtime: searchlist[i].travelingtime,
			requestedby: searchlist[i].requestedby,
			price: searchlist[i].price,
		});
	}
}

function getItem(item) {
	var requestid = $(item).data("requestid");

	$("#rideRequestModal").modal("show");

	$("#pickuplocationDetails").html(
		requestlist.get("requestid", requestid)[0]._values.pickuplocation,
	);

	$("#destinationDetails").html(
		requestlist.get("requestid", requestid)[0]._values.destination,
	);

	$("#passengersDetails").html(
		requestlist.get("requestid", requestid)[0]._values.passengers,
	);

	$("#luggageDetails").html(
		requestlist.get("requestid", requestid)[0]._values.luggage,
	);

	$("#numridesDetails").html(
		requestlist.get("requestid", requestid)[0]._values.numrides,
	);

	$("#smokingDetails").html(
		requestlist.get("requestid", requestid)[0]._values.smoking,
	);

	$("#foodndrinkDetails").html(
		requestlist.get("requestid", requestid)[0]._values.foodndrink,
	);

	$("#petsDetails").html(
		requestlist.get("requestid", requestid)[0]._values.pets,
	);

	$("#acDetails").html(requestlist.get("requestid", requestid)[0]._values.ac);

	$("#travelingtimeDetails").html(
		requestlist.get("requestid", requestid)[0]._values.travelingtime,
	);

	$("#requestedbyDetails").html(
		requestlist.get("requestid", requestid)[0]._values.requestedby,
	);

	$("#priceDetails").html(
		requestlist.get("requestid", requestid)[0]._values.price,
	);
}
