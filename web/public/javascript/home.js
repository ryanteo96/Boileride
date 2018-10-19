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
				console.log("nickname: " + res.nickname);
				$("html").show();
				$("#nickname").text("Hello, " + res.nickname + "!");
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
