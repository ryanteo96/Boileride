$(document).ready(function() {
	$("#myRequest").click(function(data) {
		data.preventDefault();

		// var credentials = localStorage.getItem("credentials");
		// var obj = JSON.parse(credentials);

		// console.log(obj.userid);

		$.post(
			"/myRides/myRequest",
			{
				// userid: obj.userid,
				userid:"temp"
			},
			function(res) {
				switch (res.result) {
					case 0: {
						console.log("IM HERE!!");	
						localStorage.key = "requestList";
						localStorage.setItem(
							"requestList",
							JSON.stringify({
								requestlist: res.requestlist
							}),
						);		
						generateList();			
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
				}
			},
		);
	});
});

function generateList(){
	var options = {
		valueNames: [ 'name', 'born' ],
		item: '<li><h3 class="name"></h3><p class="born"></p></li>'
	};
	
	var values = [{
		name: 'Jonny Strömberg',
		born: 1986
	},
	{
		name: 'Jonas Arnklint',
		born: 1985
	},
	{
		name: 'Martina Elm',
		born: 1986
	}];
	
	var userList = new List('users', options, values);
	
	userList.add({
	name: "Gustaf Lindqvist",
	born: 1983
	});
}