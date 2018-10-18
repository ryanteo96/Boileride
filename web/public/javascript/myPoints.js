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
				$("#nickname").text("Hello, " + res.nickname);
				break;
			}
			case 2: {
				window.location.href = "/signin";
				break;
			}
			default: {
				$("html").show();
				$("#nickname").text("Hello, " + res.nickname);
				break;
			}
		}
	},
);

$(document).ready(function() {
	var credentials = localStorage.getItem("credentials");
	var obj = JSON.parse(credentials);

	console.log(obj.userid);

	$("#currentPoint").text("100");
	$("#reservePoint").text("100");

	$.post(
		"/myPoints",
		{
			userid: obj.userid,
		},
		function(res) {
			switch (res.result) {
				case 0: {
					// $("#currentPoint").text(res.points);
					// $("#reservePoint").text(res.reserve);
					console.log("here");
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

	$.post(
		"/myPoints/myTransactions",
		{
			userid: obj.userid,
		},
		function(res) {
			switch (res.result) {
				case 0: {
					console.log("here");
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
