var acceptedRequestList;

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
		requestedbyname: "adwad",
		datentime: "1/1/1",
		pickuplocation: "tesawdwadt1",
		destination: "tesadwawdt2",
		passengers: 2,
		luggage: 1,
		smoking: "true",
		foodndrink: "false",
		pets: "true",
		ac: "true",
		travelingtime: 5,
		price: 1,
		status: "Ongoing",
	},
	{
		requestedbyname: "test",
		datentime: "1/1/1",
		pickuplocation: "test1",
		destination: "test2",
		passengers: 2,
		luggage: 1,
		smoking: "true",
		foodndrink: "false",
		pets: "true",
		ac: "true",
		travelingtime: 5,
		price: 1,
		status: "Cancelled",
	},
];
/*============================== TEST DATA END HERE!!!! /*==============================*/

$(document).ready(function() {
	var credentials = localStorage.getItem("credentials");
	var obj = JSON.parse(credentials);
	console.log(obj.userid);
	/*============================== TEST DATA START HERE!!!! /*==============================*/
	// generateAcceptedRequestList(test);
	// $("#status0").text("Ongoing");
	// if ($("#status0").text() == "Ongoing") {
	// 	$("#request0").addClass("border-success");
	// 	$("#request0").css("background", "#7BF08F");
	// }
	// if ($("#status1").text() == "Cancelled") {
	// 	$("#request1").addClass("border-danger");
	// 	$("#request1").css("background", "#F07B7B");
	// }
	// /*============================== TEST DATA END HERE!!!! /*==============================*/

	$.post(
		"/myRides/myRequest/accepted",
		{
			userid: obj.userid,
		},
		function(res) {
			switch (res.result) {
				case 0: {
					console.log(res.acceptedrequestlist);
					if (res.acceptedrequestlist.length == 0) {
						$("#rideExist").html(
							'<i class="icons fas fa-users mr-4"> You haven\'t accepted any rides.',
						);
					}
					generateAcceptedRequestList(res.acceptedrequestlist);
					$.each(res.acceptedrequestlist, function(i) {
						if ($("#status" + i).text() == "Ongoing") {
							$("#request" + i).addClass("border-success");
							$("#request" + i).css("background", "#7BF08F");
						}
						if ($("#status" + i).text() == "Cancelled") {
							$("#request" + i).addClass("border-danger");
							$("#request" + i).css("background", "#F07B7B");
						}
					});
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

	$("#editRequestBtn").click(function(data) {
		data.preventDefault();
		window.location.href = "/myRides/myRequest/edit";
	});

	$("#cancelRequestBtn").click(function(data) {
		data.preventDefault();

		var credentials = localStorage.getItem("credentials");
		var obj = JSON.parse(credentials);

		var editRequest = localStorage.getItem("editRequest");
		var edit = JSON.parse(editRequest);

		console.log(obj.userid);

		$.post(
			"/myRides/myRequest/accepted/cancel",
			{
				userid: obj.userid,
				requestid: edit.requestid,
			},
			function(res) {
				switch (res.result) {
					case 0: {
						window.location.href = "/myRides/myRequest/accepted";
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

		var editRequest = localStorage.getItem("editRequest");
		var edit = JSON.parse(editRequest);
		//go to the code confirmation page
		window.location.href = "/myRides/myRequest/pickup";

		$.post(
			"/myRides/myRequest/pickup",
			{
				userid: obj.userid,
				requestid: edit.requestid,
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
								codefordriver: "CODEFORDRIVER",
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
						alert("Invalid requestid.");
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

function generateAcceptedRequestList(acceptedRequestList) {
	var options = {
		valueNames: [
			{ data: ["requestid"] },
			"requestedbyname",
			"datentime",
			"pickuplocation",
			"destination",
			"passengers",
			"luggage",
			"smoking",
			"foodndrink",
			"pets",
			"ac",
			"travelingtime",
			"price",
			"status",
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
			'<small id="status" class="status text-right"></small>' +
			"</div>" +
			'<div class="row d-flex w-100 justify-content-around">' +
			'<i class="icons fas fa-users p-2 col-sm-push text-center"><small class="values passengers p-2"></small></i>' +
			'<i class="icons fas fa-suitcase p-2 col-sm-push text-center"><small class="values luggage p-2"></small></i>' +
			'<i class="icons fas fa-smoking p-2 col-sm-push text-center"><small class="values smoking p-2"></small></i>' +
			'<i class="icons fas fa-cookie-bite p-2 col-sm-push text-center"><small class="values foodndrink p-2"></small></i>' +
			'<i class="icons fas fa-paw p-2 col-sm-push text-center"><small class="values pets p-2"></small></i>' +
			'<i class="icons far fa-snowflake p-2 col-sm-push text-center"><small class="values ac p-2"></small></i>' +
			'<i class="icons fas fa-stopwatch p-2 col-sm-push text-center"><small class="values travelingtime p-2"></small></i>' +
			'<i class="icons fas fa-user-circle p-2 col-sm-push text-center"><small class="values requestedbyname p-2"></small></i>' +
			'<i class="icons fas fa-money-bill p-2 col-sm-push text-center"><small class="values price p-2"></small></i>' +
			"</div>" +
			"</div>" +
			"</li>",
	};

	myRideAcceptedRequestList = new List("myRideAcceptedRequestList", options);

	for (var i = 0; i < acceptedRequestList.length; i++) {
		if (acceptedRequestList[i].smoking) {
			acceptedRequestList[i].smoking = "Yes";
		} else acceptedRequestList[i].smoking = "No";

		if (acceptedRequestList[i].ac) {
			acceptedRequestList[i].ac = "Yes";
		} else acceptedRequestList[i].ac = "No";

		if (acceptedRequestList[i].foodndrink) {
			acceptedRequestList[i].foodndrink = "Yes";
		} else acceptedRequestList[i].foodndrink = "No";

		if (acceptedRequestList[i].pets) {
			acceptedRequestList[i].pets = "Yes";
		} else acceptedRequestList[i].pets = "No";

		if (acceptedRequestList[i].status == 0) {
			acceptedRequestList[i].status = "Ongoing";
		} else {
			acceptedRequestList[i].status = "Cancelled";
		}

		myRideAcceptedRequestList.add({
			requestid: acceptedRequestList[i].requestid,
			pickuplocation: acceptedRequestList[i].pickuplocation,
			destination: acceptedRequestList[i].destination,
			datentime: acceptedRequestList[i].datentime,
			passengers: acceptedRequestList[i].passengers,
			smoking: acceptedRequestList[i].smoking,
			ac: acceptedRequestList[i].ac,
			foodndrink: acceptedRequestList[i].foodndrink,
			pets: acceptedRequestList[i].pets,
			luggage: acceptedRequestList[i].luggage,
			travelingtime: acceptedRequestList[i].travelingtime,
			requestedbyname: acceptedRequestList[i].requestedbyname,
			price: acceptedRequestList[i].price,
			status: acceptedRequestList[i].status,
		});

		myRideAcceptedRequestList.sort("datentime", { order: "asc" });
		myRideAcceptedRequestList.sort("status", { order: "desc" });

		$("#status").attr("id", "status" + i);
		$("#request").attr("id", "request" + i);
	}
}

function getItem(item) {
	var requestid = $(item).data("requestid");

	localStorage.key = "editRequest";
	localStorage.setItem(
		"editRequest",
		JSON.stringify(
			myRideAcceptedRequestList.get("requestid", requestid)[0]._values,
		),
	);

	$("#myRideRequestModal").modal("show");

	$("#pickuplocationDetails").html(
		myRideAcceptedRequestList.get("requestid", requestid)[0]._values
			.pickuplocation,
	);

	$("#destinationDetails").html(
		myRideAcceptedRequestList.get("requestid", requestid)[0]._values
			.destination,
	);

	$("#passengersDetails").html(
		myRideAcceptedRequestList.get("requestid", requestid)[0]._values
			.passengers,
	);

	$("#luggageDetails").html(
		myRideAcceptedRequestList.get("requestid", requestid)[0]._values
			.luggage,
	);

	$("#numridesDetails").html(
		myRideAcceptedRequestList.get("requestid", requestid)[0]._values
			.numrides,
	);

	$("#smokingDetails").html(
		myRideAcceptedRequestList.get("requestid", requestid)[0]._values
			.smoking,
	);

	$("#foodndrinkDetails").html(
		myRideAcceptedRequestList.get("requestid", requestid)[0]._values
			.foodndrink,
	);

	$("#petsDetails").html(
		myRideAcceptedRequestList.get("requestid", requestid)[0]._values.pets,
	);

	$("#acDetails").html(
		myRideAcceptedRequestList.get("requestid", requestid)[0]._values.ac,
	);

	$("#travelingtimeDetails").html(
		myRideAcceptedRequestList.get("requestid", requestid)[0]._values
			.travelingtime,
	);

	$("#requestedbyDetails").html(
		myRideAcceptedRequestList.get("requestid", requestid)[0]._values
			.requestedbyname,
	);

	$("#priceDetails").html(
		myRideAcceptedRequestList.get("requestid", requestid)[0]._values.price,
	);
}
