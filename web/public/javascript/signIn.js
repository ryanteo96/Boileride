$(document).ready(function() {
	$("#signInForm").submit(function(data) {
		data.preventDefault();

		$.post(
			"/signIn",
			{
				email: $("#emailSignIn").val(),
				password: $("#passwordSignIn").val(),
			},
			function(res) {
				switch (res.body.result) {
					case 0: {
						localStorage.key = "credentials";
						localStorage.setItem(
							"credentials",
							JSON.stringify({
								userid: res.body.userid,
								email: $("#emailSignIn").val(),
								cookie: JSON.stringify(
									res.headers["set-cookie"],
								),
							}),
						);

						window.location.href = "/home";
						break;
					}
					case 1: {
						alert("User does not exist.");
						break;
					}
					case 2: {
						alert("Invalid password.");
						break;
					}
				}
			},
		);
	});
});
