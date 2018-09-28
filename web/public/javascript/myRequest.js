$(document).ready(function() {
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
						console.log("IM HERE!!");	
						localStorage.key = "requestList";
						localStorage.setItem(
							"requestList",
							JSON.stringify({
								requestlist: res.requestlist
							}),
						);		
						window.location.href = "/myRequest";			
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
