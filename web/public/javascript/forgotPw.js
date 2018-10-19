$(document).ready(function() {
	$("#forgotPwForm").submit(function(data) {
		data.preventDefault();

		$("#loading").modal({
			backdrop: "static", //remove ability to close modal with click
			keyboard: false, //remove option to close with keyboard
			show: true, // display loader
		});

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

						$("#loading").on("shown.bs.modal", function() {
							$("#loading").modal("hide"); // hide loader
						});

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
