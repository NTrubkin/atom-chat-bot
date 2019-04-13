function register() {
    const DATA = {
        login: $('#loginTextBox').val(),
        password: $('#passwordTextBox').val()
    };

    $.ajax({
        type: 'POST',
        url: '/api/user',
        contentType: 'application/json',
        data: JSON.stringify(DATA),
        error: function () {
            alert('An error occurred');
        },
        success: function (data) {
            alert('Success');
        }
    });
}
