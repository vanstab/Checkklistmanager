/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).on('click', function(event){
    var trigger = $("#sideMenu")
    if(!trigger.is(event.target) && trigger.has(event.target).length===0)
        $(".sideMenu").fadeOut();
});

function error(msg){
    $("#errorHandler").empty();
    $("#errorHandler").append(msg);
    $("#errorHandler").fadeIn().delay(5000).fadeOut("slow");
}

function viewMenu(){
    $(".sideMenu").fadeToggle()
}