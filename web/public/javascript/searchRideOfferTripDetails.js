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

function generateTripRideList(trip) {
	var options = {
		valueNames: [
			{ data: ["offertid"] },
			"offeredby",
			"datentime",
			"datentimetext",
			"pickuplocation",
			"destination",
			"seats",
			"seatsleft",
			"luggage",
			"luggageleft",
			"smoking",
			"ac",
			"foodndrink",
			"pets",
			"numrides",
			"travelingtime",
			"offeredby",
			"price",
			"status",
		],
		item:
			'<li class="list-group-item items flex-column align-items-start pl-2 pr-2 border-0" ondblclick=getItem(this)>' +
			'<div class="card" id="offer">' +
			'<div class="card-body">' +
			'<div class="row" style="font-size:20px">' +
			'<div class="row mb-2 d-flex w-100">' +
			'<h5 class="mb-1 pickuplocation col text-left"></h5>' +
			'<i class="fas fa-arrow-right col-1 icons text-center"></i>' +
			'<h5 class="mb-1 destination col text-right"></h5>' +
			'<small class="datentimetext col-2 text-right"></small>' +
			"</div>" +
			'<div class="row d-flex w-100 justify-content-around">' +
			'<i class="icons fas fa-users p-2 col text-center"><small class="values seatsleft p-2"></small></i>' +
			'<i class="icons fas fa-suitcase p-2 col text-center"><small class="values luggageleft p-2"></small></i>' +
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

	ridelist = new List("tripRideList", options);

	for (var i = 0; i < trip.length; i++) {
		if (trip[i].smoking == "true") {
			trip[i].smoking = "Yes";
		} else trip[i].smoking = "No";

		if (trip[i].ac == "true") {
			trip[i].ac = "Yes";
		} else trip[i].ac = "No";

		if (trip[i].foodndrink == "true") {
			trip[i].foodndrink = "Yes";
		} else trip[i].foodndrink = "No";

		if (trip[i].pets == "true") {
			trip[i].pets = "Yes";
		} else trip[i].pets = "No";

		travelingtime = moment
			.duration(trip[i].travelingtime, "seconds")
			.format("h [hrs], m [min]");

		var time = moment.duration("04:00:00");
		var datentime = moment(trip[i].datentime);
		datentime.subtract(time);

		ridelist.add({
			offertid: trip[i].offertid,
			pickuplocation: trip[i].pickuplocation,
			destination: trip[i].destination,
			datentime: trip[i].datentime,
			datentimetext: datentime.format("hh:mm A MMM DD, YYYY"),
			smoking: trip[i].smoking,
			ac: trip[i].ac,
			foodndrink: trip[i].foodndrink,
			pets: trip[i].pets,
			travelingtime: travelingtime,
			offeredby: trip[i].offeredby,
			seats: trip[i].seats,
			seatsleft: trip[i].seatleft,
			luggage: trip[i].luggage,
			luggageleft: trip[i].luggageleft,
			price: trip[i].price,
			status: trip[i].status,
		});

		console.log(trip[i]);

		ridelist.sort("datentime", { order: "asc" });
		ridelist.sort("status", { order: "desc" });
	}
}

function generateEditTripList(trip) {
	var options = {
		valueNames: [
			{ data: ["offertid"] },
			{ attr: "id", name: "id" },
			"pickuplocation",
			"destination",
		],
		item:
			'<li class="list-group-item items flex-column align-items-start pl-2 pr-2 border-0" ondblclick=getItem(this)>' +
			'<div class="form-check">' +
			'<input class="form-check-input id" type="checkbox" name="exampleRadios" value="option1">' +
			'<div class="row pl-2">' +
			'<label class="form-check-label pickuplocation" for="exampleRadios1"></label>' +
			'<i class="fas fa-arrow-right col-1 icons text-center"></i>' +
			'<label class="form-check-label destination" for="exampleRadios1"></label>' +
			"</div>" +
			"</li>",
	};

	ridelist = new List("editTripList", options);
	ridelist.clear();

	for (var i = 0; i < trip.length; i++) {
		ridelist.add({
			id: i,
			offertid: trip[i].offertid,
			pickuplocation: trip[i].pickuplocation,
			destination: trip[i].destination,
		});
	}
}

function getItem(item) {
	var offertid = $(item).data("offertid");

	localStorage.key = "rideDetails";
	localStorage.setItem(
		"rideDetails",
		JSON.stringify(ridelist.get("offertid", offertid)[0]._values),
	);

	$("#tripRideModal").modal("show");

	$("#pickuplocationDetails").html(
		ridelist.get("offertid", offertid)[0]._values.pickuplocation,
	);

	$("#destinationDetails").html(
		ridelist.get("offertid", offertid)[0]._values.destination,
	);

	$("#seatsDetails").html(
		ridelist.get("offertid", offertid)[0]._values.seats,
	);

	$("#seatsLeftDetails").html(
		ridelist.get("offertid", offertid)[0]._values.seatsleft,
	);

	$("#luggageDetails").html(
		ridelist.get("offertid", offertid)[0]._values.luggage,
	);

	$("#luggageLeftDetails").html(
		ridelist.get("offertid", offertid)[0]._values.luggageleft,
	);

	$("#numridesDetails").html(
		ridelist.get("offertid", offertid)[0]._values.numrides,
	);

	$("#smokingDetails").html(
		ridelist.get("offertid", offertid)[0]._values.smoking,
	);

	$("#foodndrinkDetails").html(
		ridelist.get("offertid", offertid)[0]._values.foodndrink,
	);

	$("#petsDetails").html(ridelist.get("offertid", offertid)[0]._values.pets);

	$("#acDetails").html(ridelist.get("offertid", offertid)[0]._values.ac);

	$("#travelingtimeDetails").html(
		ridelist.get("offertid", offertid)[0]._values.travelingtime,
	);

	$("#offerByDetails").html(
		ridelist.get("offertid", offertid)[0]._values.offeredby,
	);

	$("#priceDetails").html(
		ridelist.get("offertid", offertid)[0]._values.price,
	);
}

$(document).ready(function() {
	var trip = localStorage.getItem("tripDetails");
	var obj = JSON.parse(trip);

	var search = localStorage.getItem("search");
	var searchObj = JSON.parse(search);

	console.log(obj.rides);

	$("#numrides").text("Number of Rides: " + obj.numrides);
	$("#travelingtime").text("Total Travel Time: " + obj.duration);
	$("#price").text("Price: " + obj.price);

	generateTripRideList(obj.rides);

	$("#editTripDetailsBtn").click(function(data) {
		data.preventDefault();

		$("#editTripModal").modal("show");
		generateEditTripList(obj.rides);

		$("input[type=checkbox]").on("change", function(e) {
			if ($("input[type=checkbox]:checked").length > 2) {
				$(this).prop("checked", false);
				alert("Choose 2 rides.");
			}
		});
	});

	$("#editTripBtn").click(function(data) {
		data.preventDefault();

		if ($("input[type=checkbox]:checked").length < 2) {
			$(this).prop("checked", false);
			alert("Choose 2 rides.");
		} else {
			let startofferid;
			let endofferid;
			let first = true;

			for (var i = 0; i < obj.numrides; i++) {
				if ($("#" + i).is(":checked")) {
					if (first) {
						startofferid = obj.rides[i].offertid;
						first = false;
					} else {
						endofferid = obj.rides[i].offertid;
						first = true;
					}
				}
			}

			$.post(
				"/searchRideOfferTripDetails/edit",
				{
					userid: searchObj[0].userid,
					startofferid: startofferid,
					endofferid: endofferid,
					pickuplocation: searchObj[0].pickuplocation,
					destination: searchObj[0].destination,
					pickupproximity: searchObj[0].pickupproximity,
					destinationproximity: searchObj[0].destinationproximity,
					date: searchObj[0].date,
					time: searchObj[0].time,
					datentimerange: searchObj[0].datentimerange,
					passengers: searchObj[0].passengers,
					numrides: searchObj[0].numrides,
					luggage: searchObj[0].luggage,
					smoking: searchObj[0].smoking,
					foodndrink: searchObj[0].foodndrink,
					pets: searchObj[0].pets,
					ac: searchObj[0].ac,
					trip: JSON.stringify(obj.rides),
				},
				function(res) {
					switch (res.result) {
						case 0: {
							console.log(res.trips);
							localStorage.key = "searchResults";
							localStorage.setItem(
								"searchResults",
								JSON.stringify(res.trips),
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
					}
				},
			);
		}
	});

	$("#joinTripDetailsBtn").click(function(data) {
		data.preventDefault();

		$("#joinRideOfferModal").modal("show");
	});

	$("#joinTripBtn").click(function(data) {
		data.preventDefault();

		$.post(
			"/searchRideOfferTripDetails/join",
			{
				userid: searchObj[0].userid,
				offeridlist: JSON.stringify(obj.rides),
				passenger: $("#numPassenger").val(),
				luggage: $("#numLuggage").val(),
			},
			function(res) {
				switch (res.result) {
					case 0: {
						$("#loading").modal("hide"); // hide loader
						alert("Success.");
						window.location.href = "/home";
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
						alert("Ride does not exist.");
						break;
					}
					case 4: {
						$("#loading").modal("hide"); // hide loader
						alert("Cannot accept own offer.");
						break;
					}
					case 5: {
						$("#loading").modal("hide"); // hide loader
						alert("Already joined.");
						break;
					}
					case 6: {
						$("#loading").modal("hide"); // hide loader
						alert("Not enough points.");
						break;
					}
					case 7: {
						$("#loading").modal("hide"); // hide loader
						alert("Invalid number of passengers.");
						break;
					}
					case 8: {
						$("#loading").modal("hide"); // hide loader
						alert("Invalid number of luggage.");
						break;
					}
					case 9: {
						$("#loading").modal("hide"); // hide loader
						alert("Invalid number of passengers and luggage.");
						break;
					}
				}
			},
		);
	});
});
