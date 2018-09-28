let offerList;
let test = [
	{
		offerid: 1,
		offeredby: "test",
		pickuplocation: "Earhart",
		destination: "Purdue",
		datentime: "1/1/1",
		smoking: "true",
		foodndrink: "false",
		pets: "true",
		ac: "true",
		travelingtime: 3,
		price: 1,
		seatleft: 1,
		luggageleft: 1,
		status: "true",
	},
	{
		offerid: 2,
		offeredby: "test2",
		pickuplocation: "Hillenbrand",
		destination: "Purdue",
		datentime: "1/1/1",
		smoking: "true",
		foodndrink: "false",
		pets: "true",
		ac: "true",
		travelingtime: 3,
		price: 100,
		seatleft: 2,
		luggageleft: 3,
		status: "true",
	},
];

$(document).ready(function() {
	generateViewOfferList(test);
	$("#myOffer").click(function(data) {
		data.preventDefault();

		console.log(obj.userid);

		$.post(
			"/myRides/myOffer",
			{
				userid: obj.userid,
			},
			function(res) {
				switch (res.result) {
					case 0: {
						generateViewOfferList(res.offerlist);
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
});

function generateViewOfferList(viewOfferList) {
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

	offerList = new List("viewMyOfferList", options);

	for (var i = 0; i < viewOfferList.length; i++) {
		if (viewOfferList[i].smoking == "true") {
			viewOfferList[i].smoking = "Yes";
		} else viewOfferList[i].smoking = "No";

		if (viewOfferList[i].ac == "true") {
			viewOfferList[i].ac = "Yes";
		} else viewOfferList[i].ac = "No";

		if (viewOfferList[i].foodndrink == "true") {
			viewOfferList[i].foodndrink = "Yes";
		} else viewOfferList[i].foodndrink = "No";

		if (viewOfferList[i].pets == "true") {
			viewOfferList[i].pets = "Yes";
		} else viewOfferList[i].pets = "No";

		offerList.add({
			offerid: viewOfferList[i].offerid,
			pickuplocation: viewOfferList[i].pickuplocation,
			destination: viewOfferList[i].destination,
			datentime: viewOfferList[i].datentime,
			smoking: viewOfferList[i].smoking,
			ac: viewOfferList[i].ac,
			foodndrink: viewOfferList[i].foodndrink,
			pets: viewOfferList[i].pets,
			travelingtime: viewOfferList[i].travelingtime,
			offeredby: viewOfferList[i].offeredby,
			seatleft: viewOfferList[i].seatleft,
			luggageleft: viewOfferList[i].luggageleft,
			price: viewOfferList[i].price,
		});
	}
}

function getItem(item) {
	var offerid = $(item).data("offerid");

	$("#myOfferModal").modal("show");

	$("#pickuplocationDetails").html(
		offerList.get("offerid", offerid)[0]._values.pickuplocation,
	);

	$("#destinationDetails").html(
		offerList.get("offerid", offerid)[0]._values.destination,
	);

	$("#passengersDetails").html(
		offerList.get("offerid", offerid)[0]._values.passengers,
	);

	$("#luggageDetails").html(
		offerList.get("offerid", offerid)[0]._values.luggage,
	);

	$("#numridesDetails").html(
		offerList.get("offerid", offerid)[0]._values.numrides,
	);

	$("#smokingDetails").html(
		offerList.get("offerid", offerid)[0]._values.smoking,
	);

	$("#foodndrinkDetails").html(
		offerList.get("offerid", offerid)[0]._values.foodndrink,
	);

	$("#petsDetails").html(offerList.get("offerid", offerid)[0]._values.pets);

	$("#acDetails").html(offerList.get("offerid", offerid)[0]._values.ac);

	$("#travelingtimeDetails").html(
		offerList.get("offerid", offerid)[0]._values.travelingtime,
	);

	$("#requestedbyDetails").html(
		offerList.get("offerid", offerid)[0]._values.requestedby,
	);

	$("#priceDetails").html(offerList.get("offerid", offerid)[0]._values.price);
}
