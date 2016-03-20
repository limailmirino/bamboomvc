
function showSpinner() {
	$("#spinner").show();
}

function hideSpinner() {
	$("#spinner").hide();
}

function deleteGroup(id) {

	if(!confirm("Sei sicuro di voler cancellare il gruppo?")) {
		return false;
	}
	
	alert("deleting...");
	
}

function getTd(id) {
	var $tr = $("#group" + id);
	var $td = $tr.find("td:first-child");
	
	return $td;
}

function editGroup(group) {
	var $td = getTd(group.id);
	var curVal = $td.text();
	$td.html('<input type="text" style="width: 100%" value="' + curVal + '" />')
	$td.find("input").blur(function() {
		saveData(group, this);
	}).keypress(function(e) {
		if(e.keyCode == 13) {
			this.blur();
		}
	}).focus();
	
}

function saveData(group, field) {
	showSpinner();
	$.post('update.html', {description: field.value, id: group.id, groupType: group.type}, function(data) {
		data = $.parseJSON(data);
		if(data.message == "OK") {
			$td = getTd(data.data.id);
			$td.html(data.data.description);
		} else {
			alert(data.error);
		}
		hideSpinner();
	});
	
}

function loadingScreenStop(){
    $("body").removeClass("loading");
}

function loadingScreenStart(){
    $("body").addClass("loading");
}