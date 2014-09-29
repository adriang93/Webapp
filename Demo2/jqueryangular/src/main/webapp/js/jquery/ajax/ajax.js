/*
 * 
 *   AJAX using JQuery
 *   Many more ways, see JQuery API
 *   
 *   AjaxServlet must run!
 * 
 */
$(function(){
    $("#get").on("click", ajax.get);
    $("#post").on("click", ajax.post); 
});

var ajax = (function(){
    
    var path="../../../AjaxServlet";
    
    return{
        get: function(){
            var deferred = $.getJSON(path);  
            x = 1;
            deferred.done(function( json ){
                alert("Done " + json.value);
            });
            deferred.fail(function(status){
                alert("Fail" + status); 
            });
        }, 
        post: function(){
            var data = $("#txt").val();
            var deferred = $.post(path, {
                input: data
            });
            deferred.done(function( json ){
                alert("Done " + json.value);
                alert("Response status " + deferred.status);
               // alert("Single Response header " + deferred.getResponseHeaders("Content-Type"));
                alert("Response headers " + deferred.getAllResponseHeaders());
                
            });
            deferred.fail(function(){
                alert("Fail");
            });
        }
    }  
})();


