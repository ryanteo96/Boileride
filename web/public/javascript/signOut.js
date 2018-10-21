$(document).ready(function() {
	$("#signOutBtn").click(function(data) {
		data.preventDefault();

		var credentials = localStorage.getItem("credentials");
		var obj = JSON.parse(credentials);

		$.post(
			"/signOut",
			{
				userid: obj.userid,
				cookie: obj.cookie,
			},
			function(res) {
				switch (res.body.result) {
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
