$(document).ready(function() {
	$("#signInForm").submit(function(data) {
		data.preventDefault();

		localStorage.key = "credentials";
		localStorage.setItem(
			"credentials",
			JSON.stringify({
				email: $("#emailSignIn").val(),
			}),
		);

		$.post(
			"/signIn",
			{
				email: $("#emailSignIn").val(),
				password: $("#passwordSignIn").val(),
			},
			function(res) {
				switch (res.result) {
					case 0: {
						localStorage.key = "credentials";
						localStorage.setItem(
							"credentials",
							JSON.stringify({
								email: $("#emailSignIn").val(),
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
