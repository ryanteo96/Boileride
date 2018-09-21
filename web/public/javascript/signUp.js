$(document).ready(function() {
	$("#signUpForm").submit(function(data) {
		data.preventDefault();

		console.log("hi");

		$.post(
			"/signUp",
			{
				nickname: $("#nickname").val(),
				email: $("#email").val(),
				password: $("#password").val(),
				phone: $("#phone").val(),
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
								nickname: $("#nickname").val(),
								email: $("#email").val(),
								phone: $("#phone").val(),
							}),
						);
						break;
					}
					case "1": {
						alert("Invalid nickname.");
						break;
					}
					case "2": {
						alert("Invalid email.");
						break;
					}
					case "3": {
						alert("Email has been taken.");
						break;
					}
					case "4": {
						alert("Invalid phone number.");
						break;
					}
				}
			},
		);
	});
});
