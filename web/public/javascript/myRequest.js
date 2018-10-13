var requestList;

$(document).ready(function() {
	var credentials = localStorage.getItem("credentials");
	var obj = JSON.parse(credentials);
	//generateRequestList(requestlist);
	console.log(obj.userid);

	$.post(
		"/myRides/myRequest",
		{
			userid: obj.userid,
		},
		function(res) {
			switch (res.result) {
				case 0: {
					console.log("success");
					console.log(res.requestlist);
					generateRequestList(res.requestlist);
					$.each(res.requestlist, function(i) {
						if ($("#status" + i).text() == "Ongoing")
							$("#request" + i).addClass("border-success");

						if ($("#status" + i).text() == "Cancelled")
							$("#request" + i).addClass("border-danger");
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
			"/myRides/myRequest/cancel",
			{
				userid: obj.userid,
				requestid: edit.requestid,
			},
			function(res) {
				switch (res.result) {
					case 0: {
						window.location.href = "/myRides/myRequest/";
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

function generateRequestList(requestList) {
	var options = {
		valueNames: [
			{ data: ["requestid"] },
			"requestedby",
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
			'<i class="icons fas fa-user-circle p-2 col-sm-push text-center"><small class="values requestedby p-2"></small></i>' +
			'<i class="icons fas fa-money-bill p-2 col-sm-push text-center"><small class="values price p-2"></small></i>' +
			"</div>" +
			"</div>" +
			"</li>",
	};

	myRideRequestList = new List("myRideRequestList", options);

	for (var i = 0; i < requestList.length; i++) {
		if (requestList[i].smoking) {
			requestList[i].smoking = "Yes";
		} else requestList[i].smoking = "No";

		if (requestList[i].ac) {
			requestList[i].ac = "Yes";
		} else requestList[i].ac = "No";

		if (requestList[i].foodndrink) {
			requestList[i].foodndrink = "Yes";
		} else requestList[i].foodndrink = "No";

		if (requestList[i].pets) {
			requestList[i].pets = "Yes";
		} else requestList[i].pets = "No";

		if (requestList[i].status == 0) {
			requestList[i].status = "Ongoing";
		} else {
			requestList[i].status = "Cancelled";
		}

		myRideRequestList.add({
			requestid: requestList[i].requestid,
			pickuplocation: requestList[i].pickuplocation,
			destination: requestList[i].destination,
			datentime: requestList[i].datentime,
			passengers: requestList[i].passengers,
			smoking: requestList[i].smoking,
			ac: requestList[i].ac,
			foodndrink: requestList[i].foodndrink,
			pets: requestList[i].pets,
			luggage: requestList[i].luggage,
			travelingtime: requestList[i].travelingtime,
			requestedby: requestList[i].requestedby,
			price: requestList[i].price,
			status: requestList[i].status,
		});

		myRideRequestList.sort("datentime", { order: "asc" });
		myRideRequestList.sort("status", { order: "desc" });

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
			myRideRequestList.get("requestid", requestid)[0]._values,
		),
	);

	$("#myRideRequestModal").modal("show");

	$("#pickuplocationDetails").html(
		myRideRequestList.get("requestid", requestid)[0]._values.pickuplocation,
	);

	$("#destinationDetails").html(
		myRideRequestList.get("requestid", requestid)[0]._values.destination,
	);

	$("#passengersDetails").html(
		myRideRequestList.get("requestid", requestid)[0]._values.passengers,
	);

	$("#luggageDetails").html(
		myRideRequestList.get("requestid", requestid)[0]._values.luggage,
	);

	$("#numridesDetails").html(
		myRideRequestList.get("requestid", requestid)[0]._values.numrides,
	);

	$("#smokingDetails").html(
		myRideRequestList.get("requestid", requestid)[0]._values.smoking,
	);

	$("#foodndrinkDetails").html(
		myRideRequestList.get("requestid", requestid)[0]._values.foodndrink,
	);

	$("#petsDetails").html(
		myRideRequestList.get("requestid", requestid)[0]._values.pets,
	);

	$("#acDetails").html(
		myRideRequestList.get("requestid", requestid)[0]._values.ac,
	);

	$("#travelingtimeDetails").html(
		myRideRequestList.get("requestid", requestid)[0]._values.travelingtime,
	);

	$("#requestedbyDetails").html(
		myRideRequestList.get("requestid", requestid)[0]._values.requestedby,
	);

	$("#priceDetails").html(
		myRideRequestList.get("requestid", requestid)[0]._values.price,
	);
}
