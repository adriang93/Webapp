/*
 * Exploring the hashChangeEvent
 */

// Using JQuery
$(function() {

    // Bind an event to window.onhashchange 
    $(window).on("hashchange", function() {
        var hash = location.hash;

        // Set the page title based on the hash.
        document.title = 'The hash is ' + (hash.replace(/^#/, '') || 'blank') + '.';
    });


    // Since the event is only triggered when the hash changes, we need to trigger
    // the event now, to handle the hash the page may have loaded with.
    $(window).trigger("hashchange");

});


