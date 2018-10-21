$(document).ready(function() {
	$("#verifyEmailForm").submit(function(data) {
		data.preventDefault();

		var credentials = localStorage.getItem("credentials");
		var obj = JSON.parse(credentials);

		$.post(
			"/verifyEmail",
			{
				email: obj.email,
				code: $("#verifyCode").val(),
			},
			function(res) {
				switch (res.body.result) {
					case 0: {
						localStorage.key = "credentials";
						localStorage.setItem(
							"credentials",
							JSON.stringify({
								userid: res.body.userid,
								email: obj.email,
								cookie: JSON.stringify(
									res.headers["set-cookie"],
								),
							}),
						);

						if (res.body.userid) {
							window.location.href = "/settings";
						} else {
							window.location.href = "/home";
						}

						break;
					}
					case 1: {
						alert("Invalid email.");
						break;
					}
					case 2: {
						alert("Invalid verification code.");
						break;
					}
				}
			},
		);
	});
});
