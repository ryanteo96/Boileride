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

$(document).ready(function() {
	var searchResults = localStorage.getItem("searchResults");
	var obj = JSON.parse(searchResults);

	generateSearchRequestList(obj);

	$("#acceptRideRequestBtn").click(function(data) {
		data.preventDefault();

		var credentials = localStorage.getItem("credentials");
		var obj = JSON.parse(credentials);

		var request = localStorage.getItem("currentRequest");
		var req = JSON.parse(request);

		console.log(obj.userid);

		$.post(
			"/searchRideRequest/accept",
			{
				userid: obj.userid,
				requestid: req.requestid,
			},
			function(res) {
				switch (res.result) {
					case 0: {
						alert("Success");
						window.location.href = "/home";
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
						alert("Cannot accept your own request");
						break;
					}
					case 5: {
						alert("Ride already accepted.");
						break;
					}
					case 6: {
						alert("Not enough points.");
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
			'<li class="list-group-item items flex-column align-items-start pl-2 pr-2 border-0" ondblclick=getItem(this)>' +
			'<div class="card" id="request">' +
			'<div class="card-body">' +
			'<div class="row" style="font-size:20px">' +
			'<div class="row mb-2 d-flex w-100">' +
			'<h5 class="mb-1 pickuplocation col text-left"></h5>' +
			'<i class="fas fa-arrow-right col-1 icons text-center"></i>' +
			'<h5 class="mb-1 destination col text-right"></h5>' +
			'<small class="datentime col-2 text-right"></small>' +
			"</div>" +
			'<div class="row d-flex w-100 justify-content-around">' +
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

	localStorage.key = "currentRequest";
	localStorage.setItem(
		"currentRequest",
		JSON.stringify(requestlist.get("requestid", requestid)[0]._values),
	);

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
