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
				console.log(res);

				switch (res) {
					case "0": {
						window.location.href = "/home";

						localStorage.key = "credentials";
						localStorage.setItem(
							"credentials",
							JSON.stringify({
								email: $("#emailSignIn").val(),
								password: $("#passwordSignIn").val(),
							}),
						);
						break;
					}
					case "1": {
						alert("User does not exist.");
						break;
					}
					case "2": {
						alert("Invalid password.");
						break;
					}
				}
			},
		);
	});
});

