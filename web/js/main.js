$(document).ready(function () {
    $('#ed-name').toggle();
    $('#ed-animal').toggle();
    $('#ed-motd').toggle();
    $('#li-updBtn').toggle();

    //Populate comment list with comments from DB
    PopulateComments();

    $('#updTogBtn').on('click', ToggleProfileFields);
    $('#updUsrBtn').on('click', UpdateProfile);
    $('#createComBtn').on('click', CreateComment);
});

function ToggleProfileFields() {
    //profile detail
    $('#pro-name').toggle();
    $('#pro-animal').toggle();
    $('#pro-motd').toggle();
    $('#li-togBtn').toggle();
    //input fields
    $('#ed-name').toggle();
    $('#ed-animal').toggle();
    $('#ed-motd').toggle();
    $('#li-updBtn').toggle();
}

function UpdateProfile() {
    //Validate input field values
    //Send input field values to DB
}

function CreateComment() {
    //Validate comment
    //Post to DB

}

function PopulateComments() {

}