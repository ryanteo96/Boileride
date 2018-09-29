var requestList;

let test = [
	{
		requestid: 1,
		requestedby: "test",
		pickuplocation: "Earhart",
		destination: "Purdue",
		datentime: "1/1/1",
		passengers: 4,
		luggage: 4,
		smoking: "true",
		foodndrink: "false",
		pets: "true",
		ac: "true",
		travelingtime: 3,
		price: 1,
		status: "true",
	},
	{
		requestid: 2,
		requestedby: "test2",
		pickuplocation: "Hillenbrand",
		destination: "Purdue",
		datentime: "1/1/1",
		passengers: 3,
		luggage: 3,
		smoking: "true",
		foodndrink: "false",
		pets: "true",
		ac: "true",
		travelingtime: 3,
		price: 100,
		status: "true",
	},
];

$(document).ready(function() {
	generateRequestList(test);
	localStorage.key = "test";
	localStorage.setItem(
		"test",
		JSON.stringify({
			requestid: 1,
			requestedby: "test",
			pickuplocation: "Earhart",
			destination: "Purdue",
			datentime: "1/1/1",
			passengers: 4,
			luggage: 4,
			smoking: "true",
			foodndrink: "false",
			pets: "true",
			ac: "true",
			travelingtime: 3,
			price: 1,
			status: "true",
		}),
	);
	$("#myRequest").click(function(data) {
		data.preventDefault();

		var credentials = localStorage.getItem("credentials");
		var obj = JSON.parse(credentials);

		console.log(obj.userid);

		$.post(
			"/myRides/myRequest",
			{
				userid: obj.userid,
			},
			function(res) {
				switch (res.result) {
					case 0: {
						console.log(res.requestlist);
						generateRequestList(res.requestlist);
						localStorage.key = "requestlist";
						localStorage.setItem(
							"requestlist",
							JSON.stringify({
								requestlist: res.requestlist,
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

	$("#editRequestBtn").click(function(data) {
		data.preventDefault();
		window.location.href = "/myRides/myRequest/edit";
	});

	$("#cancelRequestBtn").click(function(data) {
		data.preventDefault();

		var credentials = localStorage.getItem("credentials");
		var obj = JSON.parse(credentials);

		console.log(obj.userid);

		$.post(
			"/myRides/myRequest",
			{
				userid: obj.userid,
				requestid: obj.requestid,
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
			'<li class="list-group-item items flex-column align-items-start pl-2 pr-2" ondblclick=getItem(this)>' +
			'<div class="row" style="font-size:20px">' +
			'<div class="mb-2 d-flex w-100">' +
			'<h5 class="mb-1 pickuplocation col text-left"></h5>' +
			'<i class="fas fa-arrow-right col-1 icons text-center"></i>' +
			'<h5 class="mb-1 destination col text-right"></h5>' +
			'<small class="datentime col-2 text-right"></small>' +
			"</div>" +
			'<div class="d-flex w-100">' +
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

	myRideRequestList = new List("myRideRequestList", options);

	for (var i = 0; i < requestList.length; i++) {
		if (requestList[i].smoking == "true") {
			requestList[i].smoking = "Yes";
		} else requestList[i].smoking = "No";

		if (requestList[i].ac == "true") {
			requestList[i].ac = "Yes";
		} else requestList[i].ac = "No";

		if (requestList[i].foodndrink == "true") {
			requestList[i].foodndrink = "Yes";
		} else requestList[i].foodndrink = "No";

		if (requestList[i].pets == "true") {
			requestList[i].pets = "Yes";
		} else requestList[i].pets = "No";

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
		});
	}
}

function getItem(item) {
	var requestid = $(item).data("requestid");

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
