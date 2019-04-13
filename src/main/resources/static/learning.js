let tags = [];
let sortByName = false;

function learn() {
    let commandCodeField = $('#commandCodeField');
    let messageField = $('#messageField');

    if(commandCodeField.val() === '' || messageField.val() === '') return;

    let message = {
        commandCode: commandCodeField.val(),
        text: messageField.val()
    };

    $.ajax({
        type: 'POST',
        url: '/api/learning',
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

function loadCommandTags() {
    $('#commandCodeTags').html('');
    $('#messageFieldLabel').val('');

    $.ajax({
        type: 'GET',
        url: '/api/learning/command/' + $('#commandCodeField').val(),
        error: function () {
            $('#commandCodeFieldLabel').text('Command not found')
        },
        success: function (result) {
            tags = result;
            $('#commandCodeFieldLabel').text('Command: ' + $('#commandCodeField').val() + ', tags = ' + tags.length);
            sortByName ? sortTagsByName() : sortTagsByIdf();
            showTags();
        }
    })
    ;
}

function sortTagsByName() {
    sortByName = true;
    tags.sort(compareByName);
}

function sortTagsByIdf() {
    sortByName = false;
    tags.sort(compareByIdf);
}

function showTags() {
    let field = $('#commandCodeTags');
    field.html('');
    for (let i in tags) {
        field.html('<p>'+ tags[i].name + ' = ' + tags[i].idfValue + '</p>' + field.html())
    }
}

function compareByName(a,b) {
    if (a.name < b.name)
        return 1;
    if (a.name > b.name)
        return -1;
    return 0;
}

function compareByIdf(a,b) {
    if (a.idfValue < b.idfValue)
        return -1;
    if (a.idfValue > b.idfValue)
        return 1;
    return 0;
}

function recalculateIdf() {
    $.ajax({
        type: 'POST',
        url: '/api/learning/recalculate-idf',
        error: function () {
            alert('An error occurred');
        },
        success: function () {
            loadCommandTags();
        }
    });
}
