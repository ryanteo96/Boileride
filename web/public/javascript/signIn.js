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
				switch (res.result) {
					case 0: {
						window.location.href = "/home";

						localStorage.key = "credentials";
						localStorage.setItem(
							"credentials",
							JSON.stringify({
								email: $("#emailSignIn").val(),
							}),
						);
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
