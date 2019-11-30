$(document).ready(function () {
    $('#edit').toggle();

    $('#updTogBtn').on('click', function () {
        //input fields
        $('#edit').toggle();
        //profile detail
        $('#profile-comment-list').toggle();
    });
});