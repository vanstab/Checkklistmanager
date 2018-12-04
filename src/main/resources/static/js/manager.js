/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* global ctx */

//run on file call
$(function () {
    var token = $("meta[name='_csrf'").attr("content");
    var header = $("meta[name='_csrf_header'").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});
getManagerTeam();

//Gets managers team and sets up team table
function getManagerTeam() {
    var url = ctx + 'manager/team';
    $.ajax({
        url: url,
        type: 'GET',
        dataType: 'json',
        success: function (userpositions) {
            for (i = 0; i < userpositions.length; i++) {
                addPosition(userpositions[i])
            }
            $("#Team tbody").append("<tr><td class='center' colspan='4'><input class='button' type='image' src='"+ctx+"images/newButton.png' onClick='addRow(this)'/></td></tr>");
        },
        error: function(msg){
            error(msg.responseJSON.message);
        }
    });
}

//Setups tables with team member data
function getPostionTasks(position) {
    var row = position.parentNode.parentNode;
    var url = ctx + 'manager/team/tasks';
    var positionId = row.cells[0].innerHTML;
    $.ajax({
        url: url,
        type: 'GET',
        dataType: 'json',
        data: {positionId: positionId},
        success: function (tasks) {
            $(".taskTable tbody").empty();
            for (i = 0; i < tasks.length; i++) {
                addTask(tasks[i]);
            }
            $("#taskTablesDiv").attr('data-positionid', positionId);
            $(".taskTable tbody").append("<tr><td class='center' colspan='3'><input class='button'  type='image' src='"+ctx+"images/newButton.png' onClick='addRow(this)'/></td></tr>");
            if($(".hiddenSectionOnLoad").is(":hidden")){
                $(".hiddenSectionOnLoad").toggle();
            }
        },
        error: function(msg){
            error(msg.responseJSON.message);
        }
    });
}

//Generic add row to table for new entity, save/delete function based on table class name
function addRow(button) {
    if ($(button).attr('disable') === 'disable')
        return;
    $(button).attr('disable', 'disable');
    var table = button.parentNode.parentNode.parentNode.parentNode.getAttribute("id");
    var headers = $("#" + table + " thead th");
    var rowCount = $("#" + table + " tbody tr").length;
    var rowHtml = "<tr><td class='hiddenColumn'>" + rowCount + "</td>";
    for (i = 0; i < headers.length; i++) {
        if (headers[i].getAttribute("data-ignorAddRow") == null)
            if(headers[i].getAttribute("data-colspan")==null)
                rowHtml += "<td><input type='text' id='" + (headers[i].innerText).replace(' ','_') + "' name='" + headers[i].innerText + "' placeholder='" + headers[i].innerText + "'/></td>";
            else rowHtml += "<td colspan='"+headers[i].getAttribute("data-colspan")+ "'><input type='text' id='" + (headers[i].innerText).replace(' ','_') + "' name='" + headers[i].innerText + "' placeholder='" + headers[i].innerText + "'/></td>";
            
        else if (headers[i].getAttribute("data-ignorAddRow") == 'true' && headers[i].getAttribute("data-ignorAddRowPlaceholder")!= 'true')
            rowHtml += "<td></td>";
    }
    rowHtml += "<td style='float:right'><input class='button' type='image' src='"+ctx+"images/saveButton.png' onClick='save(this)'/></td>";
    rowHtml += "</tr>";
    if (rowCount > 1)
        $("#" + table + " tbody tr").eq(rowCount - 2).after(rowHtml);
    else
        $("#" + table + " tbody tr").eq(rowCount - 1).before(rowHtml);
    $(button).attr('disable', 'enable');
}
function save(button) {
    if ($(button).attr('disable') === 'disable')
        return;
    $(button).attr('disable', 'disable');
    
    var table = button.parentNode.parentNode.parentNode.parentNode.getAttribute("class");
    if (table.includes('task')) {
        if(!saveTask(button))
                $(button).attr('disable', 'enable');
    } else if (table.includes('team')) {
        if(!saveTeam(button))
            $(button).attr('disable', 'enable');
    }
}
function deleteSelected(button) {
    if ($(button).attr('disable') === 'disable')
        return;
    $(button).attr('disable', 'disable');
    var table = button.parentNode.parentNode.parentNode.parentNode.getAttribute("class");
    if (table.includes('task')) {
        deleteTask(button);
    } else if (table.includes('team')) {
        deleteTeam(button);
    }
}

//funcitons related to Tasks
function saveTask(data) {
    var row = data.parentNode.parentNode;
    var newTask = {};
    var position = {};
    position.positionId = parseInt($("#taskTablesDiv").attr('data-positionid'));
    newTask.completed = false;
    newTask.day = row.parentNode.parentNode.getAttribute("id");
    newTask.position = position;
    var cells = $(row).children();
    newTask.description = ($(cells)).find('#Details').val();
    var url = ctx + 'manager/team/tasks';
    $.ajax({
        url: url,
        type: 'POST',
        contentType: "application/json; charset=utf-8",
        dataType: 'json',
        data: JSON.stringify(newTask),
        success: function (task) {
            row.parentNode.removeChild(row);
            addTask(task, true);
            return true;
        },
        error: function(msg){
            error(msg.responseJSON.message);
            return false;
        }
    });
}
function addTask(task, newTask) {
    if (newTask === true) {
        var rowCount = $("#" + task.day + " tbody tr").length;
        if (rowCount > 1) {
            $("#" + task.day + " tbody tr").eq(rowCount - 2).after(taskHTML(task));
        } else {
            $("#" + task.day + " tbody tr:last").before(taskHTML(task));
        }
    } else if (task.completed === false) {
        $("#" + task.day + " tbody").append(taskHTML(task));
    } else {
        $("#" + task.day + " tbody").append(taskHTML(task));
    }
}
function deleteTask(data) {
    var row = data.parentNode.parentNode;
    var cells = $(row).children();
    var url = ctx + 'manager/team/tasks?taskId=' + cells[0].innerText;
    $.ajax({
        url: url,
        dataType: 'json',
        type: 'DELETE',
        success: function () {
            row.parentNode.removeChild(row);
        },
        error: function(msg){
            error(msg.responseJSON.message);
        }
    });
}

//functions related to team
function saveTeam(data) {
    var row = data.parentNode.parentNode;
    var cells = $(row).children();
    var userposition = {};
    var position = {};
    var user = {};
    position.team_name = ($(cells)).find('#Team_Name').val();
    position.position_name= ($(cells)).find('#Position').val();
    user.username =($(cells)).find('#User_Name').val();
    userposition.position=position;
    userposition.user=user;
    var url = ctx + 'manager/team/position';
    $.ajax({
        url: url,
        type: 'POST',
        contentType: "application/json; charset=utf-8",
        dataType: 'json',
        data: JSON.stringify(userposition),
        success: function (userposition) {
            row.parentNode.removeChild(row);
            addPosition(userposition, true);
            return true;
        },
        error: function(msg){
            error(msg.responseJSON.message);
            return false;
        }
    });
}
function deleteTeam(data) {
    var row = data.parentNode.parentNode;
    var cells = $(row).children();
    var url = ctx + 'manager/team/position?positionId=' + cells[0].innerText;
    $.ajax({
        url: url,
        dataType: 'json',
        type: 'DELETE',
        success: function () {
            row.parentNode.removeChild(row);
        },
        error: function(msg){
            error(msg.responseJSON.message);
        }
    });
}


function addPosition(userposition, newPosition){
    if (newPosition === true) {
        var rowCount = $("#Team tbody tr").length;
        if (rowCount > 1) {
            $("#Team tbody tr").eq(rowCount - 2).after(userpositionHTML(userposition));
        }
        else {
            $("#Team tbody tr:last").before(userpositionHTML(userposition));
        }
    }
    else $("#Team tbody").append(userpositionHTML(userposition)); 
}

function userpositionHTML(userposition){
    return "<tr>" +
    "<td class='hiddenColumn'>" + userposition.position.positionId + "</td>" +
    "<td data-userName='" + userposition.user.user_name + "'>" + userposition.user.name + "</td>" +
    "<td>" + userposition.position.position_name + "</td>" +
    "<td>" + userposition.position.team_name + "</td>" +
    "<td style='float:right'>"+
    "<input class='button' type='image' src='"+ctx+"images/getButton.png'  onclick='getPostionTasks(this)' value='Get Tasks'/>" +
    "<input class='button' type='image' src='"+ctx+"images/editButton.png' onclick='update(this)' value='Update'/>" +
    "<input class='button' type='image' src='"+ctx+"images/deleteButton.png' onclick='deleteSelected(this)' value='Remove'/>"+
    "</td>" +
    "</tr>";
}
function taskHTML(task) {
    return "<tr class='taskRow'>" +
            "<td class='hiddenColumn'>" + task.taskId + "</td>" +
            "<td class='checkbox'><input type='checkbox' disabled/></td>" +
            "<td>" + task.description + "</td>" +
            "<td style='float:right'><input type='image' src='"+ctx+"images/deleteButton.png' class='button' onClick='deleteSelected(this)'/></td>" +
            "</tr>";
}
