var requestList;

$(document).ready(function() {
	$("#myRequest").click(function(data) {
		data.preventDefault();

		// var credentials = localStorage.getItem("credentials");
		// var obj = JSON.parse(credentials);

		// console.log(obj.userid);

		$.post(
			"/myRides/myRequest",
			{
				// userid: obj.userid,
			},
			function(res) {
				switch (res.result) {
					case 0: {
						console.log("IM HERE!!");
						localStorage.key = "requestList";
						localStorage.setItem(
							"requestList",
							JSON.stringify({
								requestlist: res.requestlist,
							}),
						);
						generateRequestList();
						console.log(res.requestlist);
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

function generateRequestList() {
	var options = {
		valueNames: [
			{ data: ["requestid"] },
			"requestedby",
			"travelingtime",
			"price",
			"datentime",
			"pickuplocation",
			"destination",
			"passengers",
			// "luggage",
			// "smoking",
			// "foodndrink",
			// "pets",
			// "ac",
			// "status",
		],
		item:
			'<li class="requestList" ondblclick=showModal(this)>' +
			'<div class="row" style="font-size:20px">' +
			'<div class="col-4">' +
			'<a class="datentime" style="font-weight: bold;"></a>' +
			"</div>" +
			'<div class="col-4">' +
			'<a class="passengers" style="font-weight: bold;"></a>' +
			"</div>" +
			'<div class="col-4">' +
			'<a class="price" style="font-weight: bold;"></a>' +
			"</div>" +
			"</div>" +
			'<div class="row" style="font-size:20px">' +
			'<div class="col-4">' +
			'<a class="travelingtime" style="font-weight: bold;"></a>' +
			"</div>" +
			'<div class="col-4">' +
			'<a class="pickuplocation" style="font-weight: bold;"></a>' +
			"</div>" +
			'<div class="col-4">' +
			'<a class="destination" style="font-weight: bold;"></a>' +
			"</div>" +
			"</div>" +
			"</li>",
	};

	requestList = new List("myRequestList", options);

	for (var i = 0; i < 10; i++) {
		requestList.add({
			id: i,
			requestid: i,
			//requestedby: "Requested By: " + i,
			travelingtime: "Travel Time: " + i,
			price: "Price: $" + i,
			datentime: "Date and time: " + i,
			pickuplocation: "Pickup location: " + i,
			destination: "Destination: " + i,
			passengers: "No. of Passengers: " + i,
			// luggage: "No. of Luggage: " + i,
			// smoking: "Allow Smoking: " + i,
			// foodndrink: "Allow Food And Drink: " + i,
			// pets: "Allow Pets: " + i,
			// ac: "Request AC: " + i,
			// status: "Status: " + i,
		});
	}
}

function getItem(item) {
	var requestid = $(item).data("requestid");

	// alert(requestid);
	console.log(
		requestList.get("requestid", requestid)[0]._values.travelingtime,
	);
}

function showModal(item) {
	$("#myRequestModal").modal("show");
	var requestid = $(item).data("requestid");

	// alert(requestid);
	console.log(
		requestList.get("requestid", requestid)[0]._values.travelingtime,
	);
}
