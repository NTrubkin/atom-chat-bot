function sendMessage() {
    let message = {
        text: $('#messageField').val()
    };

    $.ajax({
        type: 'POST',
        url: '/api/message',
        contentType: 'application/json',
        data: JSON.stringify(message),
        error: function () {
            alert('An error occurred');
        },
        success: function () {
            $('#messageField').val('');
        }
    });
}

function loadMessages() {
    $.ajax({
        type: 'GET',
        url: '/api/message',
        error: function () {
            alert('An error occurred');
        },
        success: putMessages
    });
}

function updateMessages() {
    $.ajax({
        type: 'GET',
        url: '/api/message/unread',
        error: function () {
            alert('An error occurred');
        },
        success: putMessages
    });
}

function putMessages(messages) {
    let field = $('#messageBlock');
    for (let i in messages) {
        field.html('<p>'+ messages[i].text + '</p>' + field.html())
    }
}

function clearMessages(messages) {
    $('#messageBlock').text('')
}

function sendOnEnter() {
    let input = document.getElementById("messageField");
    input.addEventListener("keyup", function(event) {
        if (event.keyCode === 13) {
            event.preventDefault();
            document.getElementById("sendBtn").click();
        }
    });
}
