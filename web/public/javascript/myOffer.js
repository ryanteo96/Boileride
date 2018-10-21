var offerList;

$("html").hide();

var credentials = localStorage.getItem("credentials");
var obj = JSON.parse(credentials);
var editOffer = localStorage.getItem("editOffer");
var edit = JSON.parse(editOffer);

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
	var credentials = localStorage.getItem("credentials");
	var obj = JSON.parse(credentials);
	// generateViewOfferList(test);
	console.log(obj.userid);

	$("#loading").modal({
		backdrop: "static", //remove ability to close modal with click
		keyboard: false, //remove option to close with keyboard
		show: true, // display loader
	});

	$("#loading").on("shown.bs.modal", function() {
		$.post(
			"/myRides/myOffer",
			{
				userid: obj.userid,
			},
			function(res) {
				switch (res.result) {
					case 0: {
						res.offerlist.sort(function(a, b) {
							var dateA = new Date(a.datentime),
								dateB = new Date(b.datentime);

							if (a.status - b.status) {
								return a.status - b.status;
							} else {
								return dateA - dateB;
							}
						});

						generateViewOfferList(res.offerlist);

						$.each(res.offerlist, function(i) {
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

						$("#firstslide").css("overflow-y", "hidden"); //hide first slide scroll
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
});

function generateViewOfferList(offerList) {
	console.log(offerList);

	$("#confirmCodeModal").modal("hide");
	var options = {
		valueNames: [
			{ data: ["offerid"] },
			"datentime",
			"datentimetext",
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
			"statustext",
			"offeruserstatus",
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

	myRideOfferList = new List("myRideOfferList", options);
	myRideOfferList.clear();

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
			statustext = "Ongoing";
		} else if (offerList[i].status == 1) {
			statustext = "Still Ongoing";
		} else if (offerList[i].status == 2) {
			statustext = "Cancelled";
		} else if (offerList[i].status == 3) {
			statustext = "Ended";
		}

		travelingtime = moment
			.duration(offerList[i].travelingtime, "seconds")
			.format("h [hrs], m [min]");

		var time = moment.duration("04:00:00");
		var datentime = moment(offerList[i].datentime);
		datentime.subtract(time);

		myRideOfferList.add({
			offerid: offerList[i].offerid,
			pickuplocation: offerList[i].pickuplocation,
			destination: offerList[i].destination,
			datentime: offerList[i].datentime,
			datentimetext: datentime.format("hh:mm A MMM DD, YYYY"),
			smoking: offerList[i].smoking,
			ac: offerList[i].ac,
			foodndrink: offerList[i].foodndrink,
			pets: offerList[i].pets,
			travelingtime: travelingtime,
			offeredbyname: offerList[i].offeredbyname,
			seats: offerList[i].seats,
			seatleft: offerList[i].seatleft,
			luggage: offerList[i].luggage,
			luggageleft: offerList[i].luggageleft,
			price: offerList[i].price,
			status: offerList[i].status,
			statustext: statustext,
			joinedby: offerList[i].joinedby,
			offeruserstatus: offerList[i].offeruserstatus,
			phone: offerList[i].phone,
			joinedbyname: offerList[i].joinedbyname,
		});

		//assign dynamic id
		$("#status").attr("id", "status" + i);
		$("#offer").attr("id", "offer" + i);
	}
}

function getItem(item) {
	//to reset the slide back to data-slide 0 everytime opening a modal
	$("#carouselExampleIndicators").carousel(0);
	var offerid = $(item).data("offerid");

	var credentials = localStorage.getItem("credentials");
	var obj = JSON.parse(credentials);

	localStorage.key = "editOffer";
	localStorage.setItem(
		"editOffer",
		JSON.stringify(myRideOfferList.get("offerid", offerid)[0]._values),
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

	$.post(
		"/myRides/myOffer/pickup",
		{
			userid: obj.userid,
			offerid: myRideOfferList.get("offerid", offerid)[0]._values.offerid,
		},
		function(res) {
			switch (res.result) {
				case 0: {
					// $("#headerCode").html("CODEFORPASSENGER");
					console.log("SUCCESS");
					$("#headerCode").html(res.code);
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
					// alert("Not authorized to get code.");
					break;
				}
			}
		},
	);

	//to create the list for passengers
	var passengers = {
		valueNames: [
			{ data: ["joinedby"] },
			"phone",
			"joinedbyname",
			"offeruserstatus",
		],
		item:
			'<li class="list-group-item items flex-column align-items-start pl-2 pr-2 border-0" ondblclick=getPassengerCode(this)>' +
			'<div class="card" style="width:700px" id="joined">' +
			'<div class="card-body">' +
			'<div class="row" style="font-size:20px">' +
			'<div class="row mb-2 d-flex w-100">' +
			'<i class="icons fas fa-user p-2 mr-5 col-3 text-left"> <small class="values joinedbyname p-2"></small></i>' +
			'<i class="icons fas fa-phone p-2 col-3 text-left"><small class="values phone p-2"></small></i>' +
			'<i class="icons fas p-2 col text-left"><small class="values offeruserstatus p-2"></small></i>' +
			"</div>" +
			"</div>" +
			"</div>" +
			"</li>",
	};

	passengersList = new List("passengersList", passengers);
	passengersList.clear();

	// passengers list inside modal
	console.log(
		"Joined passengers:" +
			myRideOfferList.get("offerid", offerid)[0]._values.joinedbyname,
	);
	var temparray;
	temparray = myRideOfferList.get("offerid", offerid)[0]._values.joinedbyname;
	//if its null then show no passenger
	if (temparray.length == 1 && temparray[0] == null) {
		$("#passengersListTitle").html("No one joined yet");
	} else {
		$("#passengersListTitle").html("Passengers");

		//loop to get passengers
		for (
			var j = 0;
			j <
			myRideOfferList.get("offerid", offerid)[0]._values.joinedby.length;
			j++
		) {
			// console.log(
			// 	"STATUS: " +
			// 		myRideOfferList.get("offerid", offerid)[0]._values
			// 			.offeruserstatus[j],
			// );
			var tempString;
			//1 == confirmed, 0 == not confirmed
			if (
				myRideOfferList.get("offerid", offerid)[0]._values
					.offeruserstatus[j] == 1
			) {
				tempString = "Ride Confirmed";
			} else {
				tempString = "Ride Pending";
			}
			passengersList.add({
				phone: myRideOfferList.get("offerid", offerid)[0]._values.phone[
					j
				],
				joinedby: myRideOfferList.get("offerid", offerid)[0]._values
					.joinedby[j],
				joinedbyname: myRideOfferList.get("offerid", offerid)[0]._values
					.joinedbyname[j],
				offeruserstatus: "Status: " + tempString,
			});
			console.log(
				"STATUS: " +
					myRideOfferList.get("offerid", offerid)[0]._values
						.offeruserstatus[j],
			);
			$("#joined").attr("id", "joined" + j);
		}
	}
}

function getPassengerCode(item) {
	$("#confirmCodeModal").modal("show");
	var joinedby = $(item).data("joinedby");

	$("#pickUpForm").submit(function(data) {
		data.preventDefault();

		var credentials = localStorage.getItem("credentials");
		var obj = JSON.parse(credentials);

		console.log($("#verifyPickupCode").val());

		$.post(
			"/myRides/myOffer/confirmPickup",
			{
				userid: obj.userid,
				offerid: edit.offerid,
				joineduserid: joinedby,
				code: $("#verifyPickupCode").val(),
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
}
