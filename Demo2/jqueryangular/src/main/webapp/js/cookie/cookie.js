

/*
 * Set a cookie and sent, include in POST to server.
 * @returns {undefined}
 */
$(function() {
    $("#btn").on("click", cookie.set);
    var c = $.cookie("animal");
    $("#cookie").append(" animal : " + c);
});

var cookie = (function() {

    return{
        set: function() {
            var animal = $('input[name=animal]:checked').val();
            // MUST have path to make cookie available to whole domain
            $.cookie("animal", animal, {expires: 7 ,path: "/"});
            var deferred = $.ajax({
                type: "POST",
                url: "http://localhost:8084/jquery/cookie",
                data: {animal: animal},
                /* xhrFields: {    // Possible need this if crossdomain
                 withCredentials: true
                 }*/
            });
            deferred.done(function() {
                alert("Check server output");
            });
            deferred.fail(function(msg) {
                alert("Server call failed " + msg);
            });
        }
    };
})();

/*
 
 
 
 
 
 
 
 
 
 
 */


