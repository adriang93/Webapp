/********************************* 
 *  
 *   Some code to update page and do AJAX calls using jQuery
 *   (better use Angular)
 */
// Connect controls (buttons etc.) on page to listener below
// Using JQuery style
$(function(){
    $('#lnkPrev').on('click', control.linkListener);
    $('#lnkNext').on('click', control.linkListener);
    control.init(); 
});

/*
 *   Listeners for the controls on person.html
 *   This is a singleton (no contructor function)
 */   
var control = (function (){
    
    var BASE_URI = "http://localhost:8080/hateoas/webresources/persons"; 
    var prev;
    var next;
   
    function updateLinks( xml ){
        prev = $(xml).find("link[rel='prev']").attr("href");
        next = $(xml).find("link[rel='next']").attr("href");      
        $("#lnkPrev").attr("href", prev );
        $("#lnkNext").attr("href", next );     
    }
    
    // Crude DOM manipulation
    function listPersons( xml ){
        var table = $("#persons");
        table.empty();
        $(xml).find("linkedperson").each(function(){
            var person = $(this).text();
            var href = $(this).find("link[rel='car']").attr("href");
            console.log(person);
            console.log(href);
            var row = "<tr><td>" + person + "</td><td><a href='" + href + "'> Car</a></td></tr>";
            table.append(row);
        });
    }
    
    // Listeners
    return {
        init: function(){
            var deferred = $.get(BASE_URI + "?start=0&count=2");
            deferred.done(function(xml){
                updateLinks(xml);
                listPersons( xml );               
            }); 
            deferred.fail(function(){
                alert("Failed");
            });
        },
        
        linkListener: function(e){
            // Don't use link for navigation
            // else we'll get raw XML
            e.preventDefault(); 
            var deferred;
            if( this.id === "lnkNext"){              
                deferred = $.get(next);             
                deferred.done(function(xml){
                    updateLinks(xml);
                    listPersons(xml);
                }); 
                deferred.fail(function(){
                    alert("Failed");
                });
            } else if( this.id === "lnkPrev"){  
                deferred = $.get(prev);
                deferred.done(function(xml){
                    updateLinks(xml);
                    listPersons(xml);
                }); 
                deferred.fail(function(){
                    alert("Failed");
                });
            }  
        }
    } ;      
}()); 
