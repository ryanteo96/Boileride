$(document).ready(function() {
	$("#verifyEmailForm").submit(function(data) {
		data.preventDefault();

		var credentials = localStorage.getItem("credentials");
		var obj = JSON.parse(credentials);

		console.log(obj);

		$.post(
			"/verifyEmail",
			{
				email: obj.email,
				code: $("#verifyCode").val(),
			},
			function(res) {
				switch (res.result) {
					case 0: {
						localStorage.key = "credentials";
						localStorage.setItem(
							"credentials",
							JSON.stringify({
								userid: res.userid,
							}),
						);

						window.location.href = "/home";
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
