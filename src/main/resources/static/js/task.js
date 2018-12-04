/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* global ctx */

$(function(){
    var token=$("meta[name='_csrf'").attr("content");
    var header=$("meta[name='_csrf_header'").attr("content");
    $(document).ajaxSend(function(e,xhr,options){
        xhr.setRequestHeader(header,token);
    });
});


getUserTasks();
function getUserTasks(){
    var url = ctx+'tasks';
    $.ajax({
        url:url,
        type: 'GET',
        dataType: 'json',
        success:function(data){
            setUpTables(data);
        }
        
    });
}
function setUpTables(input){
    for(i = 0; i < input.length; i++){
        if(input[i].completed === false){
            $("#"+input[i].day+" tbody").append(
                    "<tr>"+
                    "<td class=\"hiddenColumn\">" + input[i].taskId+ "</td>"+
                    "<td><input class='checkbox' type=\"checkbox\" onclick=\"updateCompleted(this)\"></td>"+
                    " <td>"+ input[i].description+"</td>"+
                    "</tr>");
        }
        else{
            $("#"+input[i].day+" tbody").append(
                    "<tr>"+
                    "<td class=\"hiddenColumn\">" + input[i].taskId+ "</td>"+
                    "<td><input class='checkbox' type=\"checkbox\" checked=\"checked\" onclick=\"updateCompleted(this)\"/></td>"+
                    "<td>"+ input[i].description+"</td>"+
                    "</tr>");
        }
    }
}

function updateCompleted(box){
    var row = box.parentNode.parentNode;  
    $.ajax({
        url: ctx+'tasks/completed',
        type: 'put',
        data:{taskID:row.cells[0].innerHTML, completed:box.checked}
    });
}
