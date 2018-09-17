$(document).ready(function() {
	$("#btn-signIn").submit(function(event) {
		$.post("/signIn/test", $("#signInForm").serialize(), function(data) {
			console.log(data); //data is the response from the backend
		});

		event.preventDefault();
	});
});
