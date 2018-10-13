$(document).ready(function() {
	var codePickup = localStorage.getItem("code");
	var objCode = JSON.parse(codePickup);
	$("#headerCode").html(objCode.code);

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
				requestid: edit.requestid,
				code: $("#verifyPickupCode").val(),
			},
			function(res) {
				switch (res.result) {
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
