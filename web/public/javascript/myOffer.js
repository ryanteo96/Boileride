var offerList;

$(document).ready(function() {
	generateViewOfferList();
	var credentials = localStorage.getItem("credentials");
	var obj = JSON.parse(credentials);
	$.post(
		"/myRides/myOffer",
		{
			userid: obj.userid,
		},
		function(res) {
			switch (res.result) {
				case 0: {
					console.log(res.offerlist);
					localStorage.key = "offerlist";
					localStorage.setItem(
						"offerlist",
						JSON.stringify({
							offerlist: res.offerlist,
						}),
					);
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
			}
		},
	);
});

$("#myOffer").click(function(data) {
	data.preventDefault();

	console.log(obj.userid);

	$("#editOfferBtn").click(function(data) {
		data.preventDefault();
		window.location.href = "/myRides/myOffer/edit";
	});

	$("#cancelOfferBtn").click(function(data) {
		data.preventDefault();
		var credentials = localStorage.getItem("credentials");
		var obj = JSON.parse(credentials);
		console.log(obj.userid);
		$.post(
			"/myRides/myOffer/cancel",
			{
				userid: obj.userid,
				offerid: obj.offerid,
			},
			function(res) {
				switch (res.result) {
					case 0: {
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
						alert("Not authorized to cancel.");
					}
					case 4: {
						alert("Ride not exist.");
					}
					case 5: {
						alert("Ride already cancelled.");
					}
				}
			},
		);
	});
});

function generateViewOfferList(offerList) {
	var options = {
		valueNames: [
			{ data: ["offerid"] },
			"datentime",
			"pickuplocation",
			"destination",
			"smoking",
			"ac",
			"foodndrink",
			"pets",
			"travelingtime",
			"offeredby",
			"seatleft",
			"luggageleft",
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
			'<i class="icons fas fa-users p-2 col text-center"><small class="values seatleft p-2"></small></i>' +
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

	offerList = new List("myRideOfferList", options);

	for (var i = 0; i < offerList.length; i++) {
		if (offerList[i].smoking == true) {
			offerList[i].smoking = "Yes";
		} else offerList[i].smoking = "No";

		if (offerList[i].ac == true) {
			offerList[i].ac = "Yes";
		} else offerList[i].ac = "No";

		if (offerList[i].foodndrink == true) {
			offerList[i].foodndrink = "Yes";
		} else offerList[i].foodndrink = "No";

		if (offerList[i].pets == true) {
			offerList[i].pets = "Yes";
		} else offerList[i].pets = "No";

		offerList.add({
			offerid: offerList[i].offerid,
			pickuplocation: offerList[i].pickuplocation,
			destination: offerList[i].destination,
			datentime: offerList[i].datentime,
			smoking: offerList[i].smoking,
			ac: offerList[i].ac,
			foodndrink: offerList[i].foodndrink,
			pets: offerList[i].pets,
			travelingtime: offerList[i].travelingtime,
			offeredby: offerList[i].offeredby,
			seatleft: offerList[i].seatleft,
			luggageleft: offerList[i].luggageleft,
			price: offerList[i].price,
		});
	}
}

function getItem(item) {
	var offerid = $(item).data("offerid");

	$("#myRideOfferModal").modal("show");

	$("#pickuplocationDetails").html(
		offerList.get("offerid", offerid)[0]._value.pickuplocation,
	);

	$("#destinationDetails").html(
		offerList.get("offerid", offerid)[0]._value.destination,
	);

	$("#seatsDetails").html(
		offerList.get("offerid", offerid)[0]._value.seatleft,
	);

	$("#luggageDetails").html(
		offerList.get("offerid", offerid)[0]._value.luggageleft,
	);

	$("#numridesDetails").html(
		offerList.get("offerid", offerid)[0]._value.numrides,
	);

	$("#smokingDetails").html(
		offerList.get("offerid", offerid)[0]._value.smoking,
	);

	$("#foodndrinkDetails").html(
		offerList.get("offerid", offerid)[0]._value.foodndrink,
	);

	$("#petsDetails").html(offerList.get("offerid", offerid)[0]._value.pets);

	$("#acDetails").html(offerList.get("offerid", offerid)[0]._value.ac);

	$("#travelingtimeDetails").html(
		offerList.get("offerid", offerid)[0]._value.travelingtime,
	);

	$("#offeredbyDetails").html(
		offerList.get("offerid", offerid)[0]._value.offeredby,
	);

	$("#priceDetails").html(offerList.get("offerid", offerid)[0]._value.price);
}
