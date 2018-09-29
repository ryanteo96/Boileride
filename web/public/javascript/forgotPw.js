$(document).ready(function() {
	$("#forgotPwForm").submit(function(data) {
		data.preventDefault();

		$.post(
			"/forgotPw",
			{
				email: $("#forgotPwEmail").val(),
			},
			function(res) {
				switch (res.result) {
					case 0: {
						localStorage.key = "resetPwEmail";
						localStorage.setItem(
							"resetPwEmail",
							JSON.stringify({
								email: $("#forgotPwEmail").val(),
							}),
						);

						window.location.href = "/resetPw";
						break;
					}
					case 1: {
						alert("User does not exist.");
						break;
					}
				}
			},
		);
	});
});
