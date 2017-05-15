function updateDeleteBtn() {
    $('#deleteBtn').prop('disabled', ($("input:checked").length < 1));
}

$(function () {
    $("table").on('click', '.clickable-row', function(e) {
        $(this).toggleClass('active');
        $(this).find('input[type=checkbox]').prop('checked', function(i, val){ 
        	return !val;
        });
        updateDeleteBtn();
    });
    $("input[type=checkbox]").on("click", function() {
    	$(this).prop('checked', function(i, val) {
    		return !val;
    	});
    	updateDeleteBtn();
    });
});