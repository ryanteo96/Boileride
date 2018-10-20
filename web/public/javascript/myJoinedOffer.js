var joinedOfferList;

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
	},
	{
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
	},
];
/*============================== TEST DATA END HERE!!!! /*==============================*/

$(document).ready(function() {
	var credentials = localStorage.getItem("credentials");
	var obj = JSON.parse(credentials);

	console.log(obj.userid);
	/*============================== TEST DATA START HERE!!!! /*==============================*/
	// generateJoinedOfferList(test);
	// $("#status0").text("Ongoing");
	// if ($("#status0").text() == "Ongoing") {
	// 	$("#offer0").addClass("border-success");
	// 	$("#offer0").css("background", "#7BF08F");
	// }
	// if ($("#status1").text() == "Cancelled") {
	// 	$("#offer1").addClass("border-danger");
	// 	$("#offer1").css("background", "#F07B7B");
	// }
	/*============================== TEST DATA END HERE!!!! /*==============================*/

	$("#loading").modal({
		backdrop: "static", //remove ability to close modal with click
		keyboard: false, //remove option to close with keyboard
		show: true, // display loader
	});

	$("#loading").on("shown.bs.modal", function() {
		$.post(
			"/myRides/myOffer/joined",
			{
				userid: obj.userid,
			},
			function(res) {
				switch (res.result) {
					case 0: {
						console.log(res.joinedofferlist);
						if (res.joinedofferlist.length == 0) {
							$("#passengerExist").html(
								'<i class="icons fas fa-car mr-4"> You haven\'t joined any rides.',
							);
						}
						generateJoinedOfferList(res.joinedofferlist);
						$.each(res.joinedofferlist, function(i) {
							if ($("#status" + i).text() == "Ongoing") {
								$("#offer" + i).addClass("border-info");
								$("#offer" + i).addClass("bg-info");
							}

							if ($("#status" + i).text() == "Still Ongoing") {
								$("#offer" + i).addClass("border-success");
								$("#offer" + i).addClass("bg-success");
							}

							if ($("#status" + i).text() == "Cancelled") {
								$("#offer" + i).addClass("border-danger");
								$("#offer" + i).addClass("bg-danger");
							}

							if ($("#status" + i).text() == "Ended") {
								$("#offer" + i).addClass("border-secondary");
								$("#offer" + i).addClass("bg-secondary");
							}
						});

						$("#loading").modal("hide"); // hide loader
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
				}
			},
		);
	});

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

function generateJoinedOfferList(joinedOfferList) {
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
			"offeredbyname", //the guy who offered the ride
			"seatleft",
			"luggageleft",
			"price",
			"status",
			"statustext",
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
			'<small id="status" class="statustext text-right"></small>' +
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

	myRideJoinedOfferList = new List("myRideJoinedOfferList", options);

	for (var i = 0; i < joinedOfferList.length; i++) {
		if (joinedOfferList[i].smoking == true) {
			joinedOfferList[i].smoking = "Yes";
		} else joinedOfferList[i].smoking = "No";

		if (joinedOfferList[i].ac == true) {
			joinedOfferList[i].ac = "Yes";
		} else joinedOfferList[i].ac = "No";

		if (joinedOfferList[i].foodndrink == true) {
			joinedOfferList[i].foodndrink = "Yes";
		} else joinedOfferList[i].foodndrink = "No";

		if (joinedOfferList[i].pets == true) {
			joinedOfferList[i].pets = "Yes";
		} else joinedOfferList[i].pets = "No";

		if (joinedOfferList[i].status == 0) {
			statustext = "Ongoing";
		} else if (joinedOfferList[i].status == 1) {
			statustext = "Still Ongoing";
		} else if (joinedOfferList[i].status == 2) {
			statustext = "Cancelled";
		} else if (joinedOfferList[i].status == 3) {
			statustext = "Ended";
		}

		travelingtime = moment
			.duration(joinedOfferList[i].travelingtime, "seconds")
			.format("h [hrs], m [min]");

		var time = moment.duration("04:00:00");
		var datentime = moment(joinedOfferList[i].datentime);
		datentime.subtract(time);

		myRideJoinedOfferList.add({
			offerid: joinedOfferList[i].offerid,
			pickuplocation: joinedOfferList[i].pickuplocation,
			destination: joinedOfferList[i].destination,
			datentime: datentime.format("hh:mm A MMM DD, YYYY"),
			smoking: joinedOfferList[i].smoking,
			ac: joinedOfferList[i].ac,
			foodndrink: joinedOfferList[i].foodndrink,
			pets: joinedOfferList[i].pets,
			travelingtime: travelingtime,
			offeredbyname: joinedOfferList[i].offeredbyname,
			seatleft: joinedOfferList[i].seatleft,
			luggageleft: joinedOfferList[i].luggageleft,
			price: joinedOfferList[i].price,
			status: joinedOfferList[i].status,
			statustext: statustext,
		});

		myRideJoinedOfferList.sort("datentime", { order: "asc" });
		myRideJoinedOfferList.sort("status", { order: "asc" });

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
			myRideJoinedOfferList.get("offerid", offerid)[0]._values,
		),
	);

	$("#myRideOfferModal").modal("show");

	$("#pickuplocationDetails").html(
		myRideJoinedOfferList.get("offerid", offerid)[0]._values.pickuplocation,
	);

	$("#destinationDetails").html(
		myRideJoinedOfferList.get("offerid", offerid)[0]._values.destination,
	);

	$("#seatsDetails").html(
		myRideJoinedOfferList.get("offerid", offerid)[0]._values.seatleft,
	);

	$("#luggageDetails").html(
		myRideJoinedOfferList.get("offerid", offerid)[0]._values.luggageleft,
	);

	$("#numridesDetails").html(
		myRideJoinedOfferList.get("offerid", offerid)[0]._values.numrides,
	);

	$("#smokingDetails").html(
		myRideJoinedOfferList.get("offerid", offerid)[0]._values.smoking,
	);

	$("#foodndrinkDetails").html(
		myRideJoinedOfferList.get("offerid", offerid)[0]._values.foodndrink,
	);

	$("#petsDetails").html(
		myRideJoinedOfferList.get("offerid", offerid)[0]._values.pets,
	);

	$("#acDetails").html(
		myRideJoinedOfferList.get("offerid", offerid)[0]._values.ac,
	);

	$("#travelingtimeDetails").html(
		myRideJoinedOfferList.get("offerid", offerid)[0]._values.travelingtime,
	);

	$("#offeredbyDetails").html(
		myRideJoinedOfferList.get("offerid", offerid)[0]._values.offeredbyname,
	);

	$("#priceDetails").html(
		myRideJoinedOfferList.get("offerid", offerid)[0]._values.price,
	);
}
