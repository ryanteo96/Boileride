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

function generateSearchOfferList(searchlist) {
	var options = {
		valueNames: [
			{ data: ["tripid"] },
			"duration",
			"rides",
			"offeredby",
			"datentime",
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
			'<small class="datentime col-2 text-right"></small>' +
			"</div>" +
			'<div class="row d-flex w-100 justify-content-around">' +
			'<i class="icons fas fa-car p-2 col text-center"><small class="values numrides p-2"></small></i>' +
			'<i class="icons fas fa-stopwatch p-2 col text-center"><small class="values duration p-2"></small></i>' +
			'<i class="icons fas fa-money-bill p-2 col text-center"><small class="values price p-2"></small></i>' +
			// '<i class="icons fas fa-users p-2 col text-center"><small class="values seatsleft p-2"></small></i>' +
			// '<i class="icons fas fa-suitcase p-2 col text-center"><small class="values luggage p-2"></small></i>' +
			// '<i class="icons fas fa-smoking p-2 col text-center"><small class="values smoking p-2"></small></i>' +
			// '<i class="icons fas fa-cookie-bite p-2 col text-center"><small class="values foodndrink p-2"></small></i>' +
			// '<i class="icons fas fa-paw p-2 col text-center"><small class="values pets p-2"></small></i>' +
			// '<i class="icons far fa-snowflake p-2 col text-center"><small class="values ac p-2"></small></i>' +
			// '<i class="icons fas fa-user-circle p-2 col text-center"><small class="values offeredby p-2"></small></i>' +
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

		totalprice = 0;

		for (var j = 0; j < searchlist[i].rides.length; j++) {
			totalprice += searchlist[i].rides[j].price;
		}

		duration = moment
			.duration(searchlist[i].duration, "seconds")
			.format("h [hrs], m [min]");

		var time = moment.duration("04:00:00");
		var datentime = moment(searchlist[i].rides[0].datentime);
		// datentime.subtract(time);

		offerlist.add({
			tripid: i,
			duration: duration,
			rides: searchlist[i].rides,
			pickuplocation: searchlist[i].rides[0].pickuplocation,
			destination:
				searchlist[i].rides[searchlist[i].rides.length - 1].destination,
			numrides: searchlist[i].rides.length,
			datentime: datentime.format("hh:mm A MMM DD, YYYY"),
			price: totalprice,
		});
	}
}

function getItem(item) {
	var tripid = $(item).data("tripid");

	localStorage.key = "tripDetails";
	localStorage.setItem(
		"tripDetails",
		JSON.stringify(offerlist.get("tripid", tripid)[0]._values),
	);

	window.location.href = "/searchRideOfferTripDetails";
}

$(document).ready(function() {
	var searchResults = localStorage.getItem("searchResults");
	var obj = JSON.parse(searchResults);

	console.log(obj);
	generateSearchOfferList(obj);
});
