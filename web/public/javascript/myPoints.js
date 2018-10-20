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

function generateTransactionList(transactionlist) {
	var options = {
		valueNames: [
			{ data: ["transactionid"] },
			"touserid",
			"tousername",
			"fromuserid",
			"fromusername",
			"datentime",
			"datentimetext",
			"amount",
			"description",
		],
		item:
			'<li class="list-group-item items flex-column align-items-start pl-2 pr-2 border-0" ondblclick=getItem(this)>' +
			'<div class="card" id="transaction">' +
			'<div class="card-body">' +
			'<div class="row" style="font-size:20px">' +
			'<div class="row mb-2 d-flex w-100">' +
			'<h5 class="mb-1 fromusername col text-left"></h5>' +
			'<i class="fas fa-arrow-right col-1 icons text-center"></i>' +
			'<h5 class="mb-1 tousername col text-right"></h5>' +
			'<small class="amount col-2 text-right"></small>' +
			'<small class="datentimetext col-2 text-right"></small>' +
			"</div>" +
			"</li>",
	};

	transactionList = new List("transactionList", options);

	for (var i = 0; i < transactionlist.length; i++) {
		var time = moment.duration("04:00:00");
		var datentime = moment(transactionlist[i].datentime);
		datentime.subtract(time);

		transactionList.add({
			transactionid: transactionlist[i].transactionid,
			touserid: transactionlist[i].touserid,
			tousername: transactionlist[i].tousername,
			fromuserid: transactionlist[i].fromuserid,
			fromusername: transactionlist[i].fromusername,
			datentime: transactionlist[i].datentime,
			datentimetext: datentime.format("hh:mm A MMM DD, YYYY"),
			amount: transactionlist[i].amount,
			description: transactionlist[i].description,
		});

		transactionList.sort("datentime", { order: "desc" });
	}
}

function getItem(item) {
	var transactionid = $(item).data("transactionid");

	localStorage.key = "transaction";
	localStorage.setItem(
		"transaction",
		JSON.stringify(
			transactionList.get("transactionid", transactionid)[0]._values,
		),
	);

	$("#transactionModal").modal("show");

	$("#description").html(
		transactionList.get("transactionid", transactionid)[0]._values
			.description,
	);
}

$(document).ready(function() {
	var credentials = localStorage.getItem("credentials");
	var obj = JSON.parse(credentials);

	console.log(obj.userid);

	$("#loading").modal({
		backdrop: "static", //remove ability to close modal with click
		keyboard: false, //remove option to close with keyboard
		show: true, // display loader
	});

	$("#loading").on("shown.bs.modal", function() {
		$.post(
			"/myPoints",
			{
				userid: obj.userid,
			},
			function(res) {
				switch (res.result) {
					case 0: {
						$("#currentPoint").text(res.points);
						$("#reservePoint").text(res.reserve);

						$("#loading").modal("hide"); // hide loader
						break;
					}
					case 1: {
						$("#loading").modal("hide"); // hide loader
						alert("Invalid userid.");
						break;
					}
					case 2: {
						$("#loading").modal("hide"); // hide loader
						alert("User not logged in.");
						break;
					}
				}
			},
		);

		$.post(
			"/myPoints/myTransactions",
			{
				userid: obj.userid,
			},
			function(res) {
				switch (res.result) {
					case 0: {
						generateTransactionList(res.transactionlist);
						$("#loading").modal("hide"); // hide loader
						break;
					}
					case 1: {
						$("#loading").modal("hide"); // hide loader
						alert("Invalid userid.");
						break;
					}
					case 2: {
						$("#loading").modal("hide"); // hide loader
						alert("User not logged in.");
						break;
					}
				}
			},
		);
	});
});
