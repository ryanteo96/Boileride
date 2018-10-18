$("html").hide();

var credentials = localStorage.getItem("credentials");
var obj = JSON.parse(credentials);

if (!obj) {
	window.location.href = "/signin";
}

$.post(
	"/authLoggedIn",
	{
		userid: obj.userid,
	},
	function(res) {
		switch (res.result) {
			case 0: {
				$("html").show();
				$("#nickname").text("Hello, " + res.nickname);
				break;
			}
			case 2: {
				window.location.href = "/signin";
				break;
			}
			default: {
				$("html").show();
				$("#nickname").text("Hello, " + res.nickname);
				break;
			}
		}
	},
);

$(document).ready(function() {
	var credentials = localStorage.getItem("credentials");
	var obj = JSON.parse(credentials);

	console.log(obj);

	$("#changeNicknameForm").submit(function(data) {
		data.preventDefault();

		$.post(
			"/settings/changeNickname",
			{
				userid: obj.userid,
				nickname: $("#newNickname").val(),
			},
			function(res) {
				switch (res.result) {
					case 0: {
						alert("Nickname successfully changed.");
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
					case 3: {
						alert("Invalid nickname.");
						break;
					}
					case 4: {
						alert("Invalid email.");
						break;
					}
					case 5: {
						alert("Invalid phone.");
						break;
					}
				}
			},
		);
	});

	$("#changeEmailForm").submit(function(data) {
		data.preventDefault();

		$.post(
			"/settings/changeEmail",
			{
				userid: obj.userid,
				email: $("#newEmail").val(),
			},
			function(res) {
				switch (res.result) {
					case 0: {
						localStorage.key = "credentials";
						localStorage.setItem(
							"credentials",
							JSON.stringify({
								userid: res.userid,
								email: $("#newEmail").val(),
							}),
						);

						window.location.href = "/verifyEmail";
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
					case 3: {
						alert("Invalid nickname.");
						break;
					}
					case 4: {
						alert("Invalid email.");
						break;
					}
					case 5: {
						alert("Invalid phone.");
						break;
					}
				}
			},
		);
	});

	$("#changePasswordForm").submit(function(data) {
		data.preventDefault();

		$.post(
			"/settings/changePassword",
			{
				userid: obj.userid,
				email: obj.email,
				newpassword: $("#newPassword").val(),
			},
			function(res) {
				switch (res.result) {
					case 0: {
						alert("Password successfully changed.");
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
					case 3: {
						alert("Invalid nickname.");
						break;
					}
					case 4: {
						alert("Invalid email.");
						break;
					}
					case 5: {
						alert("Invalid phone.");
						break;
					}
				}
			},
		);
	});

	$("#changePhoneForm").submit(function(data) {
		data.preventDefault();

		$.post(
			"/settings/changePhone",
			{
				userid: obj.userid,
				phone: $("#newPhone").val(),
			},
			function(res) {
				switch (res.result) {
					case 0: {
						alert("Phone Number successfully changed.");
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
					case 3: {
						alert("Invalid nickname.");
						break;
					}
					case 4: {
						alert("Invalid email.");
						break;
					}
					case 5: {
						alert("Invalid phone.");
						break;
					}
				}
			},
		);
	});
});
