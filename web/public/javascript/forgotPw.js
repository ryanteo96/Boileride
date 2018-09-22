$(document).ready(function() {
	$("#forgotPwForm").submit(function(data) {
        data.preventDefault();
        
		$.post(
			"/verifyEmail",
			{
				email: obj.email,				
			},
			function(res) {
				console.log(res);

				switch (res) {
					case "0": {
                        window.location.href = "/resetPw";

						localStorage.key = "resetPwEmail";
						localStorage.setItem(
							"resetPwEmail",
							JSON.stringify({
								email: $("#forgotPwEmail").val()								
							}),
						);
						break;
					}
					case "1": {
						alert("User does not exist.");
						break;
					}					
				}
			},
		);
	});
});