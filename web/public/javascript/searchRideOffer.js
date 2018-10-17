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
		numrides: 1,
		offertid: 1,
		offeredby: "test",
		pickuplocation: "Earhart",
		destination: "Purdue",
		datentime: "1/1/1",
		seats: 4,
		seatsleft: 2,
		luggage: 4,
		luggageleft: 2,
		smoking: "true",
		foodndrink: "false",
		pets: "true",
		ac: "true",
		travelingtime: 3,
		price: 1,
		status: "true",
	},
	{
		numrides: 1,
		offertid: 2,
		offeredby: "test2",
		pickuplocation: "Hillenbrand",
		destination: "Purdue",
		datentime: "1/1/1",
		seats: 3,
		seatsleft: 2,
		luggage: 3,
		luggageleft: 2,
		smoking: "true",
		foodndrink: "false",
		pets: "false",
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
	generateSearchOfferList(test);
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
						generateSearchOfferList(res.offersearchlist);
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

function generateSearchOfferList(searchlist) {
	var options = {
		valueNames: [
			{ data: ["offertid"] },
			"datentime",
			"pickuplocation",
			"destination",
			"seatsleft",
			"smoking",
			"ac",
			"foodndrink",
			"pets",
			"luggage",
			"numrides",
			"travelingtime",
			"offeredby",
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
			'<i class="icons fas fa-users p-2 col text-center"><small class="values seatsleft p-2"></small></i>' +
			'<i class="icons fas fa-suitcase p-2 col text-center"><small class="values luggage p-2"></small></i>' +
			'<i class="icons fas fa-car p-2 col text-center"><small class="values numrides p-2"></small></i>' +
			'<i class="icons fas fa-smoking p-2 col text-center"><small class="values smoking p-2"></small></i>' +
			'<i class="icons fas fa-cookie-bite p-2 col text-center"><small class="values foodndrink p-2"></small></i>' +
			'<i class="icons fas fa-paw p-2 col text-center"><small class="values pets p-2"></small></i>' +
			'<i class="icons far fa-snowflake p-2 col text-center"><small class="values ac p-2"></small></i>' +
			'<i class="icons fas fa-stopwatch p-2 col text-center"><small class="values travelingtime p-2"></small></i>' +
			'<i class="icons fas fa-user-circle p-2 col text-center"><small class="values offeredby p-2"></small></i>' +
			'<i class="icons fas fa-money-bill p-2 col text-center"><small class="values price p-2"></small></i>' +
			"</div>" +
			"</div>" +
			"</li>",
	};

	offerlist = new List("searchRideOfferList", options);

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

		offerlist.add({
			offertid: searchlist[i].offertid,
			pickuplocation: searchlist[i].pickuplocation,
			destination: searchlist[i].destination,
			datentime: searchlist[i].datentime,
			seatsleft: searchlist[i].seatsleft,
			smoking: searchlist[i].smoking,
			ac: searchlist[i].ac,
			foodndrink: searchlist[i].foodndrink,
			pets: searchlist[i].pets,
			luggage: searchlist[i].luggage,
			numrides: searchlist[i].numrides,
			travelingtime: searchlist[i].travelingtime,
			offeredby: searchlist[i].offeredby,
			price: searchlist[i].price,
		});
	}
}

function getItem(item) {
	var offertid = $(item).data("offertid");

	$("#rideOfferModal").modal("show");

	$("#pickuplocationDetails").html(
		offerlist.get("offertid", offertid)[0]._values.pickuplocation,
	);

	$("#destinationDetails").html(
		offerlist.get("offertid", offertid)[0]._values.destination,
	);

	$("#seatsDetails").html(
		offerlist.get("offertid", offertid)[0]._values.seatsleft,
	);

	$("#luggageDetails").html(
		offerlist.get("offertid", offertid)[0]._values.luggage,
	);

	$("#numridesDetails").html(
		offerlist.get("offertid", offertid)[0]._values.numrides,
	);

	$("#smokingDetails").html(
		offerlist.get("offertid", offertid)[0]._values.smoking,
	);

	$("#foodndrinkDetails").html(
		offerlist.get("offertid", offertid)[0]._values.foodndrink,
	);

	$("#petsDetails").html(offerlist.get("offertid", offertid)[0]._values.pets);

	$("#acDetails").html(offerlist.get("offertid", offertid)[0]._values.ac);

	$("#travelingtimeDetails").html(
		offerlist.get("offertid", offertid)[0]._values.travelingtime,
	);

	$("#offeredbyDetails").html(
		offerlist.get("offertid", offertid)[0]._values.offeredby,
	);

	$("#priceDetails").html(
		offerlist.get("offertid", offertid)[0]._values.price,
	);
}
