/* 
 * JQuery DOM CSS manipulation
 * 
 * Run in debugger 
 * 
 * 
 */
// First defer execution
$(function() {

    /*
     * Find elements (many more methods)
     */
    var ul = $("#myList");  // Use CSS selector to find 
    console.log(ul !== null);
    console.log(ul);

    console.log($("#myList").parent());

    // Find children of and traverse
    $("#myList").children().each(function(index, element) {
        // What's this?
        console.log($(this).text());
    });
    // How to find elements from node sets
    // NOTE: firstChild is the ORIGINAL DOM element *not* wrapped
    // by JQuery (so no fancy methods..)
    var firstChild = $("#myList").children().get(0);
    console.log(firstChild);

    /*
     * Insert and remove elements (many more methods)
     */
    // Insert inside other element
    $("#myDiv").append("<input type='button' value='Inside'/>");
    $("#myDiv").empty();  //Delete all children

    // After before element
    $("#myDiv").before("<input type='button' value='Before' class='btn'/>");
    $("#myDiv").after("<input type='button' value='After' class='btn'/>");
    $("input.btn").remove();   // Also empty();


    /*
     * CSS (many more methods)
     */
    // Overcomplicated selector just to show possibilities
    $("ul li:first-child").css("text-decoration", "underline");

    $("#myList").children().each(function(index, element) {
        $(this).css("color", "red");
        console.log($(this).text());
    });

    // -------------------------------------------------------------------

});