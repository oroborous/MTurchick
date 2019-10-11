$(document).ready(function () {
    $('#edit').toggle();

    $('#updTogBtn').on('click', ToggleProfileFields);
});

function ToggleProfileFields() {
    //profile detail
    $('#comments').toggle();
    //input fields
    $('#edit').toggle();
}