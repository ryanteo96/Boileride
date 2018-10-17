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
				console.log(res.nickname);
				console.log("SO WHAT IS INSIDE?" + res);
				$("html").show();
				localStorage.key = "nickname";
				localStorage.setItem(
					"nickname",
					JSON.stringify({
						nickname: res.nickname,
					}),
				);
				break;
			}
			case 2: {
				window.location.href = "/signin";
				break;
			}
			default: {
				$("html").show();
				break;
			}
		}
	},
);
