var offerList;

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

/*============================== TEST DATA START HERE!!!! /*==============================*/
let test = [
	{
		offerid: 2,
		datentime: 2020 / 8 / 10,
		pickuplocation: "my house",
		destination: "your house",
		smoking: "true",
		ac: "true",
		foodndrink: "false",
		pets: "true",
		travelingtime: "100000000",
		offeredbyname: "Shinigami",
		seatleft: 5,
		luggageleft: 2,
		price: 9000,
		status: "Ongoing",
		joinedby: [4, 5, 6],
		requestuserstatus: 0,
		phone: [123456789, 456789123, 654987321],
		joinedbyname: ["a", "b", "c"],
	},
	{
		offerid: 3,
		datentime: 9999 / 8 / 10,
		pickuplocation: "your house",
		destination: "my house",
		smoking: "true",
		ac: "true",
		foodndrink: "false",
		pets: "true",
		travelingtime: "57564651",
		offeredbyname: "shingekinokyojin",
		seatleft: 5,
		luggageleft: 2,
		price: 98987987,
		status: "Cancelled",
		joinedby: [1, 2, 3],
		requestuserstatus: 1,
		phone: [987654321, 159874632, 34785169],
		joinedbyname: ["c", "d", "e"],
	},
];
/*============================== TEST DATA END HERE!!!! /*==============================*/

$(document).ready(function() {
	var credentials = localStorage.getItem("credentials");
	var obj = JSON.parse(credentials);
	// generateViewOfferList(test);
	console.log(obj.userid);

	$.post(
		"/myRides/myOffer",
		{
			userid: obj.userid,
		},
		function(res) {
			switch (res.result) {
				case 0: {
					// console.log(res.offerlist);
					generateViewOfferList(res.offerlist);
					$.each(res.offerlist, function(i) {
						if ($("#status" + i).text() == "Ongoing") {
							$("#offer" + i).addClass("border-success");
							$("#offer" + i).css("background", "#7BF08F");
						}

						if ($("#status" + i).text() == "Cancelled") {
							$("#offer" + i).addClass("border-danger");
							$("#offer" + i).css("background", "#F07B7B");
						}

						if ($("#requestuserstatus").text() == "Confirmed") {
							$("#confirmStatus").html("Ride Confirmed");
							$("#confirmStatus").css("font-color", "green");
						} else {
							$("#confirmStatus").html("Ride Pending");
							$("#confirmStatus").css("font-color", "orange");
						}
					});

					$("#firstslide").css("overflow-y", "hidden"); //hide first slide scroll

					// generatePassengersList(res.offerlist);
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

	$("#editOfferBtn").click(function(data) {
		data.preventDefault();
		window.location.href = "/myRides/myOffer/edit";
	});

	$("#cancelOfferBtn").click(function(data) {
		data.preventDefault();

		var credentials = localStorage.getItem("credentials");
		var obj = JSON.parse(credentials);

		var editOffer = localStorage.getItem("editOffer");
		var edit = JSON.parse(editOffer);

		console.log(obj.userid);
		console.log(obj.offerid);

		$.post(
			"/myRides/myOffer/cancel",
			{
				userid: obj.userid,
				offerid: edit.offerid,
			},
			function(res) {
				switch (res.result) {
					case 0: {
						window.location.href = "/myRides/myOffer/";
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

	//get pickup code
	$("#getPickUpBtn").click(function(data) {
		var credentials = localStorage.getItem("credentials");
		var obj = JSON.parse(credentials);

		var editOffer = localStorage.getItem("editOffer");
		var edit = JSON.parse(editOffer);
		//go to the code confirmation page
		window.location.href = "/myRides/myOffer/pickup";

		$.post(
			"/myRides/myOffer/pickup",
			{
				userid: obj.userid,
				offerid: edit.offerid,
			},
			function(res) {
				switch (res.result) {
					case 0: {
						//store the code so can display it in confirm myRequestPickup
						localStorage.key = "code";
						localStorage.setItem(
							"code",
							JSON.stringify({
								// code: res.code,
								codeforpassenger: "CODEFORPASSENGER",
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
					case 3: {
						alert("Invalid offerid.");
						break;
					}
					case 4: {
						alert("Not authorized to get code.");
						break;
					}
				}
			},
		);
	});
});

function generateViewOfferList(offerList) {
	$("#testmodal").modal("hide");
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
			"offeredbyname", //my own name as user?
			"seatleft",
			"luggageleft",
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
			'<small id="status" class="status text-right"></small>' +
			"</div>" +
			'<div class="row d-flex w-100 justify-content-around">' +
			'<i class="icons fas fa-users p-2 col text-center"><small class="values seatleft p-2"></small></i>' +
			'<i class="icons fas fa-suitcase p-2 col text-center"><small class="values luggageleft p-2"></small></i>' +
			'<i class="icons fas fa-smoking p-2 col text-center"><small class="values smoking p-2"></small></i>' +
			'<i class="icons fas fa-cookie-bite p-2 col text-center"><small class="values foodndrink p-2"></small></i>' +
			'<i class="icons fas fa-paw p-2 col text-center"><small class="values pets p-2"></small></i>' +
			'<i class="icons far fa-snowflake p-2 col text-center"><small class="values ac p-2"></small></i>' +
			'<i class="icons fas fa-stopwatch p-2 col text-center"><small class="values travelingtime p-2"></small></i>' +
			'<i class="icons fas fa-user-circle p-2 col text-center"><small class="values offeredbyname p-2"></small></i>' +
			'<i class="icons fas fa-money-bill p-2 col text-center"><small class="values price p-2"></small></i>' +
			"</div>" +
			"</div>" +
			"</li>",
	};

	myRideOfferList = new List("myRideOfferList", options);

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

		if (offerList[i].status == 0) {
			offerList[i].status = "Ongoing";
		} else {
			offerList[i].status = "Cancelled";
		}

		// check if the offer is confirmed
		if (offerList[i].offeruserstatus == 0) {
			offerList[i].offeruserstatus = "Not confirmed";
		} else {
			offerList[i].offeruserstatus = "Confirmed";
		}
		myRideOfferList.add({
			offerid: offerList[i].offerid,
			pickuplocation: offerList[i].pickuplocation,
			destination: offerList[i].destination,
			datentime: offerList[i].datentime,
			smoking: offerList[i].smoking,
			ac: offerList[i].ac,
			foodndrink: offerList[i].foodndrink,
			pets: offerList[i].pets,
			travelingtime: offerList[i].travelingtime,
			offeredbyname: offerList[i].offeredbyname,
			seatleft: offerList[i].seatleft,
			luggageleft: offerList[i].luggageleft,
			price: offerList[i].price,
			status: offerList[i].status,

			joinedby: offerList[i].joinedby,
			requestuserstatus: offerList[i].requestuserstatus,
			phone: offerList[i].phone,
			joinedbyname: offerList[i].joinedbyname,
		});

		myRideOfferList.sort("datentime", { order: "asc" });
		myRideOfferList.sort("status", { order: "desc" });

		$("#status").attr("id", "status" + i);
		$("#offer").attr("id", "offer" + i);
	}
}

function getItem(item) {
	var offerid = $(item).data("offerid");

	localStorage.key = "editOffer";
	localStorage.setItem(
		"editOffer",
		JSON.stringify(
			myRideOfferList.get("offerid", offerid)[0]._values.offerid,
		),
	);

	$("#myRideOfferModal").modal("show");

	$("#pickuplocationDetails").html(
		myRideOfferList.get("offerid", offerid)[0]._values.pickuplocation,
	);

	$("#destinationDetails").html(
		myRideOfferList.get("offerid", offerid)[0]._values.destination,
	);

	$("#seatsDetails").html(
		myRideOfferList.get("offerid", offerid)[0]._values.seatleft,
	);

	$("#luggageDetails").html(
		myRideOfferList.get("offerid", offerid)[0]._values.luggageleft,
	);

	$("#numridesDetails").html(
		myRideOfferList.get("offerid", offerid)[0]._values.numrides,
	);

	$("#smokingDetails").html(
		myRideOfferList.get("offerid", offerid)[0]._values.smoking,
	);

	$("#foodndrinkDetails").html(
		myRideOfferList.get("offerid", offerid)[0]._values.foodndrink,
	);

	$("#petsDetails").html(
		myRideOfferList.get("offerid", offerid)[0]._values.pets,
	);

	$("#acDetails").html(myRideOfferList.get("offerid", offerid)[0]._values.ac);

	$("#travelingtimeDetails").html(
		myRideOfferList.get("offerid", offerid)[0]._values.travelingtime,
	);

	$("#offeredbyDetails").html(
		myRideOfferList.get("offerid", offerid)[0]._values.offeredbyname,
	);

	$("#priceDetails").html(
		myRideOfferList.get("offerid", offerid)[0]._values.price,
	);

	//to create the list for passengers
	var passengers = {
		valueNames: [
			{ data: ["joinedby"] },
			"requestuserstatus",
			"phone",
			"joinedbyname",
		],
		item:
			'<li class="list-group-item items flex-column align-items-start pl-2 pr-2 border-0" ondblclick=getPassengerCode()>' +
			'<div class="card" style="width:700px" id="joined">' +
			'<div class="card-body">' +
			'<div class="row" style="font-size:20px">' +
			'<div class="row mb-2 d-flex w-100">' +
			'<h5 class="mb-1 joinedbyname col text-left"></h5>' +
			'<h5 class="mb-1 phone col text-right"></h5>' +
			"</div>" +
			"</div>" +
			"</div>" +
			"</li>",
	};

	passengersList = new List("passengersList", passengers);
	passengersList.clear();

	// passengers list inside modal
	console.log(
		"offer id:" +
			myRideOfferList.get("offerid", offerid)[0]._values.joinedbyname,
	);
	//if its null then show no passenger
	if (
		myRideOfferList.get("offerid", offerid)[0]._values.joinedbyname[0] ==
		null
	) {
		$("#passengersListTitle").text("No one joined yet.");
	} else {
		for (
			var j = 0;
			j <
			myRideOfferList.get("offerid", offerid)[0]._values.joinedby.length;
			j++
		) {
			passengersList.add({
				phone: myRideOfferList.get("offerid", offerid)[0]._values.phone[
					j
				],
				joinedby: myRideOfferList.get("offerid", offerid)[0]._values
					.joinedby[j],
				joinedbyname: myRideOfferList.get("offerid", offerid)[0]._values
					.joinedbyname[j],
			});
			$("#joined").attr("id", "joined" + j);
		}
	}
}

function getPassengerCode() {
	$("#testmodal").modal("show");
}
