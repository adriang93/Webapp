
/*
 *  Using HTML5 history API (object history, see below)
 */
$(function() {

    $('#menu a').each(function() {
        $(this).on("click", listener.click);
    });

    // JQuery has problems with this
    //$(window).on("popstate", listener.pop);

    // Use old style
    window.onpopstate = listener.pop;

    // Set default page
    $("#menu a[href='home']").click();

});

var listener = function() {
    var suffix = ".xhtml";
    var path = "content/";

    return {
        click: function(evt) {
            href = $(this).attr("href");
            swapContent(href);
            // Must put something in because of if in pop
            // onpopstate event at page load in Chrome
            history.pushState({"dummy": "dummy"}, null, href);
            // Stop navigation (link clicked!)
            evt.preventDefault();
        },
        pop: function(evt) {
            // Prevent swap on page load (evt.state is null)
            if (evt && evt.state) {
                // Because of sub dir content
                var path = location.pathname;
                var i = path.lastIndexOf("/");
                swapContent(path.substr(i + 1, path.length));
            }
        }
    };

    function swapContent(href) {
        if (href) {
            $("#content").load(path + href + suffix, function(response, status, xhr) {
                if (status === "error") {
                    // Show something                  
                }
            });
            // Mark selected
            $('#menu li').removeClass("current");
            $('a[href="' + href + '"]').parent().addClass("current");
        }

    }
}();
