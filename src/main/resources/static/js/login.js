/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$("#register").submit(function(e){
    var form = $(this);
    var url = form.attr('action');
    $.ajax({
        url: url,
        type: 'POST',
        data: form.serialize(),
        success: function(){
            succesHandler("Account created");
            toggleView();
        },
        error: function(msg){
            error(msg.responseJSON.message);
        }
    });
    e.preventDefault();
});
function succesHandler(msg){
    $("#successHandler").empty();
    $("#successHandler").append(msg);
    $("#successHandler").fadeIn().delay(5000).fadeOut("slow");
}
function toggleView(){
    $("div.toggles").toggle("visibility");
}
