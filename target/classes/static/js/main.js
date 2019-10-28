$(function () {
    const appendTask = function (data) {
        let taskCode = '<a href="#" class="task-link" data-id="' + data.id + '">' + data.note + '</a><br>';
        $('#task-list').append('<div>' + taskCode + '</div>');
        };

    //Loading tasks on load page
    $.get('/tasks/', function (response) {
        for(let i in response) {
            appendTask(response[i]);
        }
    })

    //Show adding task form
    $('#show-add-task-form').click(function () {
        $('#todo-form').css('display', 'flex');
    });

    //Closing adding task form
    $('#todo-form').click(function (event) {
        if(event.target === this) {
            $(this).css('display', 'none');
        }
    });

    //Getting task
    $(document).on('click', '.task-link', function () {
        let link = $(this);
        let taskId = link.data('id');
        $.ajax({
            method: "GET",
            url: '/tasks/' + taskId,
            success: function (response) {
                let code = '<span>Сделать до ' + response.date + ' </span>';
                link.parent().append(code);
            },
            error: function (response) {
                if(response.status == 404) {
                    alert('Заметка не найдена');
                }
            }
        });
        return false;
    })

    //Adding task
    $('#save-task').click(function () {
        let data = $('#todo-form form').serialize();
        $.ajax({
            method: "POST",
            url: '/tasks/',
            data: data,
            success: function (response) {
                $('#todo-form').css('display', 'none');
                let task = {};
                task.id = response;
                let dataArray = $('#todo-form form').serializeArray();
                for(let i in dataArray) {
                    task[dataArray[i]['name']] = dataArray[i]['value'];
                }
                    appendTask(task);
            }
        });
        return false;
    });
});