/*
 * Combining haschchange with AJAX
 */

// Using JQuery
$(function() {

    // Bind an event to window.onhashchange
    $(window).on("hashchange", listener.hashchange);
    // Since the event is only triggered when the hash changes, we need to trigger
    // the event now, to handle the hash the page may have loaded with.
    $(window).trigger("hashchange");

});


var listener = (function() {

    var suffix = ".xhtml";
    var path = "content/";

    return {
        hashchange: function() {
            var hash = location.hash;
            var page = path + hash.replace(/^#/, '') + suffix;
            $("#content").load(page, function(response, status, xhr) {
                if (status === "error") {
                    // Show something                  
                }
            });
            // Set selected
            $('#menu li').removeClass("current");
            $('a[href="' + hash + '"]').parent().addClass("current");
        }
    };

}());