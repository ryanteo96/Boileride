$(document).ready(function() {
	$("#myOffer").click(function(data) {
		data.preventDefault();

		var credentials = localStorage.getItem("credentials");
		var obj = JSON.parse(credentials);

		console.log(obj.userid);

		$.post(
			"/myRides/myOffer",
			{
				userid: obj.userid,
			},
			function(res) {
				switch (res.result) {
					case 0: {										
						localStorage.key = "offerList";
						localStorage.setItem(
							"offerList",
							JSON.stringify({
								offerlist: res.offerlist,							
							}),
						);
						window.location.href = "/myOffer";	
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
