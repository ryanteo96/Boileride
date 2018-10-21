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
	var codePickup = localStorage.getItem("code");
	var objCode = JSON.parse(codePickup);
	$("#headerCode").html(objCode.codefordriver);
	console.log("code for driver is: " + objCode.codefordriver);

	$("#pickUpForm").submit(function(data) {
		data.preventDefault();
		var credentials = localStorage.getItem("credentials");
		var obj = JSON.parse(credentials);

		var editRequest = localStorage.getItem("editRequest");
		var edit = JSON.parse(editRequest);

		console.log(obj);
		//click submit to confirm the pickup
		$.post(
			"/myRides/myRequest/confirmpickup",
			{
				userid: obj.userid,
				cookie: obj.cookie,
				requestid: edit.requestid,
				code: $("#verifyPickupCode").val(),
			},
			function(res) {
				switch (res.body.result) {
					case 0: {
						window.location.href = "/myRides/myRequest";
						alert("Pick up confirmed!");
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
						alert("Not authorized to confirm code.");
						break;
					}
					case 5: {
						alert("Already confirmed.");
						break;
					}
					case 6: {
						alert("Wrong code.");
						break;
					}
				}
			},
		);
	});
});
