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
		cookie: obj.cookie,
	},
	function(res) {
		switch (res.body.result) {
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

$(document).ready(function() {
	var credentials = localStorage.getItem("credentials");
	var obj = JSON.parse(credentials);

	console.log(obj.userid);

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
				cookie: obj.cookie,
			},
			function(res) {
				switch (res.body.result) {
					case 0: {
						console.log(res.body.joinedofferlist);
						if (res.body.joinedofferlist.length == 0) {
							$("#passengerExist").html(
								'<i class="icons fas fa-car mr-4"> You haven\'t joined any rides.',
							);
						}

						res.body.joinedofferlist.sort(function(a, b) {
							var dateA = new Date(a.datentime),
								dateB = new Date(b.datentime);

							if (a.status - b.status) {
								return a.status - b.status;
							} else {
								return dateA - dateB;
							}
						});

						generateJoinedOfferList(res.body.joinedofferlist);
						$.each(res.body.joinedofferlist, function(i) {
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

		$("#editOfferModal").modal("show");

		var editOffer = localStorage.getItem("editOffer");
		var edit = JSON.parse(editOffer);

		$("#numPassengers").val(edit.seats);
		$("#numLuggage").val(edit.luggage);
	});

	$("#editOfferDetailsBtn").click(function(data) {
		data.preventDefault();

		var credentials = localStorage.getItem("credentials");
		var obj = JSON.parse(credentials);

		var editOffer = localStorage.getItem("editOffer");
		var edit = JSON.parse(editOffer);

		var offeridlist = [];
		offeridlist.push(edit.offerid);
		console.log(offeridlist);

		$.post(
			"/myRides/myOffer/joined/edit",
			{
				userid: obj.userid,
				cookie: obj.cookie,
				offeridlist: JSON.stringify(offeridlist),
				passenger: $("#numPassenger").val(),
				luggage: $("#numLuggage").val(),
			},
			function(res) {
				switch (res.body.result) {
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
						alert("Not authorized to update.");
						break;
					}
					case 4: {
						alert("Ride not exist.");
						break;
					}
					case 5: {
						alert("Invalid number of passenger(s).");
						break;
					}
					case 6: {
						alert("Invalid number of luggage(s).");
						break;
					}
				}
			},
		);
	});

	$("#cancelOfferBtn").click(function(data) {
		data.preventDefault();

		var credentials = localStorage.getItem("credentials");
		var obj = JSON.parse(credentials);

		var editOffer = localStorage.getItem("editOffer");
		var edit = JSON.parse(editOffer);

		console.log(obj.userid);
		console.log(edit.offerid);

		$.post(
			"/myRides/myOffer/joined/cancel",
			{
				userid: obj.userid,
				cookie: obj.cookie,
				offerid: edit.offerid,
			},
			function(res) {
				switch (res.body.result) {
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
						break;
					}
					case 4: {
						alert("Ride not exist.");
						break;
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

		$("#confirmCodeModal").modal("show");

		$.post(
			"/myRides/myOffer/joined/pickup",
			{
				userid: obj.userid,
				cookie: obj.cookie,
				offerid: edit.offerid,
			},
			function(res) {
				switch (res.body.result) {
					case 0: {
						//store the code so can display it in confirm myRequestPickup
						localStorage.key = "code";
						localStorage.setItem(
							"code",
							JSON.stringify({
								code: res.body.code,
							}),
						);

						$("#headerCode").html(res.body.code);
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
						alert("Not authorized to get code.");
						break;
					}
				}
			},
		);
	});

	$("#pickUpForm").submit(function(data) {
		data.preventDefault();

		var credentials = localStorage.getItem("credentials");
		var obj = JSON.parse(credentials);

		var editOffer = localStorage.getItem("editOffer");
		var edit = JSON.parse(editOffer);

		console.log($("#verifyPickupCode").val());

		$.post(
			"/myRides/myOffer/joined/confirmpickup",
			{
				userid: obj.userid,
				cookie: obj.cookie,
				offerid: edit.offerid,
				code: $("#verifyPickupCode").val(),
			},
			function(res) {
				switch (res.body.result) {
					case 0: {
						window.location.href = "/myRides/myOffer/joined";
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
					}
					case 4: {
						alert("Not authorized to confirm code.");
					}
					case 5: {
						alert("Already confirmed.");
					}
					case 6: {
						alert("Wrong code.");
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
			"seat",
			"seatleft",
			"luggage",
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
			seats: joinedOfferList[i].joinedpassenger,
			seatleft: joinedOfferList[i].seatleft,
			luggage: joinedOfferList[i].joinedluggage,
			luggageleft: joinedOfferList[i].luggageleft,
			price: joinedOfferList[i].price,
			status: joinedOfferList[i].status,
			statustext: statustext,
		});

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
		myRideJoinedOfferList.get("offerid", offerid)[0]._values.seats,
	);

	$("#seatsLeftDetails").html(
		myRideJoinedOfferList.get("offerid", offerid)[0]._values.seatleft,
	);

	$("#luggageDetails").html(
		myRideJoinedOfferList.get("offerid", offerid)[0]._values.luggage,
	);

	$("#luggageLeftDetails").html(
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
