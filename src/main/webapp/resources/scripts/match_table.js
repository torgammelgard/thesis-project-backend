function updateDeleteBtn() {
    $('#deleteBtn').prop('disabled', ($("input:checked").length < 1));
}

$(function () {
    $("table").on('click', '.clickable-row', function(e) {
        $(this).toggleClass('active');
    });
    $("input[type=checkbox]").on("click", updateDeleteBtn);
    //$("#deleteBtn").on("click", sendForm);
});