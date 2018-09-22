$(document).ready(function() {
	$("#resetPwForm").submit(function(data) {
        data.preventDefault();        
		$.post(
			"/resetPw",
			{
				email: $("#resetEmail").val(),
				code: $("#resetCode").val(),
				newpassword: $("#resetNewPw").val(),
			},
			function(res) {
				console.log(res);
                
				switch (res) {
					case "0": {
						window.location.href = "/signIn";
						break;
					}
					case "1": {
						alert("User does not exist.");
						break;
					}
					case "2": {
						alert("Incorrect validation code.");
						break;
					}					
				}
			},
		);
	});
});

