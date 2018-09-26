$(document).ready(function() {
	$("#signInForm").submit(function(data) {
		data.preventDefault();

		var credentials = localStorage.getItem("credentials");
		var obj = JSON.parse(credentials);

		$.post(
			"/signIn",
			{
				email: obj.email,
				code: $("#verifyCode").val(),
			},
			function(res) {
				console.log(res);

				switch (res) {
					case "0": {
						window.location.href = "/home";
						break;
					}
					case "1": {
						alert("Invalid email.");
						break;
					}
					case "2": {
						alert("Invalid verification code.");
						break;
					}
				}
			},
		);
	});
});
