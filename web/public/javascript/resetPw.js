$(document).ready(function() {
	var password = document.getElementById("resetNewPw");
	var confirmPassword = document.getElementById("resetConfirmPw");

	function validatePassword() {
		if (password.value != confirmPassword.value) {
			confirmPassword.setCustomValidity("Passwords does not match.");
		} else {
			confirmPassword.setCustomValidity("");
		}
	}

	password.onchange = validatePassword;
	confirmPassword.onkeyup = validatePassword;

	var resetPwEmail = localStorage.getItem("resetPwEmail");
	var obj = JSON.parse(resetPwEmail);

	console.log(obj);

	$("#resetPwForm").submit(function(data) {
		data.preventDefault();
		$.post(
			"/resetPw",
			{
				email: obj.email,
				code: $("#resetCode").val(),
				newpassword: $("#resetNewPw").val(),
			},
			function(res) {
				switch (res.result) {
					case 0: {
						$("#chgPwModal").modal("show");
						break;
					}
					case 1: {
						alert("User does not exist.");
						break;
					}
					case 2: {
						alert("Incorrect validation code.");
						break;
					}
				}
			},
		);
	});
});
