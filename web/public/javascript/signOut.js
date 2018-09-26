$(document).ready(function() {
	$("#signOutBtn").click(function(data) {
		data.preventDefault();

		var credentials = localStorage.getItem("credentials");
		var obj = JSON.parse(credentials);

		console.log(obj);

		$.post(
			"/signOut",
			{
				userid: obj.userid,
			},
			function(res) {
				switch (res.result) {
					case 0: {
						localStorage.clear();

						window.location.href = "/signIn";
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
