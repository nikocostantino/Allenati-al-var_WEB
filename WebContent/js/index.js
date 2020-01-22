window.addEventListener("load", function() {
	clearForm();
});

function clearForm(){
	setTimeout(function(){$('#username').val('');},50);
	setTimeout(function(){$('#pwd').val('');},50);
}



