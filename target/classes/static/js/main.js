$(function(){
    appendTask = function(data){
        var taskCode = '<a href="#" class="task-link" data-id="' + data.id + '">' + data.name + '</a><span>   </span><a href="#" class="task-del" data-id="' + data.id + '">delete</a><br>';
        $('#task-list').append('<div class="task">' + taskCode + '</div>');
    };

//    function loadTasks() {
//        $.get('/tasks/', function (response) {
//           // $('#task-list').html('');
//            for (i in response) {
//                appendTask(response[i]);
//            }
//        });
//    }

    $('#todo-form').addClass('not-visible')

//Loading tasks on load page
    //loadTasks();

    //Show adding task form
    $('#show-add-task-form').click(function(){
        $('#todo-form').css('display', 'flex');
    });

    //Show tasks
    $('#show-tasks').click(function(){
        loadTasks();
    });

    //Closing adding task form
    $('#todo-form').click(function(event){
        if(event.target === this) {
            $(this).css('display', 'none');
        }
    });

    //Getting task
    $(document).on('click','.task-link', function(){
        var link = $(this);
        var taskId = link.data('id');
        $.ajax({
            method: "GET",
            url: '/tasks/' + taskId,
            success: function(response){
                var code = '<span> Приоритет:' + response.priority + '</span>';
                link.parent().append(code);
            },
            error: function(response){
                if(response.status == 404) {
                    alert('Задача не найдена!');
                }
            }
        });
        return false;
    });

    //Remove task
    $(document).on('click', '.task-del', function(){
        var link = $(this);
        var taskId = link.data('id');
        $.ajax({
            method: "DELETE",
            url: '/tasks/' + taskId,
            success: function(response){
                link.parent().remove();
            },
            error: function(response){
                if(response.status == 404) {
                    alert('произожла задница!');
                }
            }
        });
        return true;
    });

    //Adding task
    $('#save-task').click(function(){
        var data = $('#todo-form form').serialize();
        $.ajax({
            method: "POST",
            url: '/tasks/',
            data: data,
            success: function(response){
                $('#todo-form').css('display', 'none');
                var task = {};
                task.id = response;
                var dataArray = $('#todo-form form').serializeArray();
                for(i in dataArray) {
                    task[dataArray[i]['name']] = dataArray[i]['value'];
                }
                appendTask(task);
            }
        });
        return false;
    });
});