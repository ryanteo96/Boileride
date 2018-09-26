$(document).ready(function() {
	var password = document.getElementById("password");
	var confirmPassword = document.getElementById("confirmPassword");

	function validatePassword() {
		if (password.value != confirmPassword.value) {
			confirmPassword.setCustomValidity("Passwords does not match.");
		} else {
			confirmPassword.setCustomValidity("");
		}
	}

	password.onchange = validatePassword;
	confirmPassword.onkeyup = validatePassword;

	$("#signUpForm").submit(function(data) {
		data.preventDefault();

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

				switch (res.result) {
					case 0: {
						window.location.href = "/verifyEmail";
						break;
					}
					case 1: {
						alert("Invalid nickname.");
						break;
					}
					case 2: {
						alert("Invalid email.");
						break;
					}
					case 3: {
						alert("Email has been taken.");
						break;
					}
					case 4: {
						alert("Invalid phone number.");
						break;
					}
				}
			},
		);
	});
});
