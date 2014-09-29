/*
 *  STRINGS 
 *   
 *  This is best viewed in Chrome Developer Tools (point at variables in code)!
 *  
 *  Add break point and reload page to inspect!
 * 
 */
var url = "https://www.google.com/webhp?sourceid=chrome-instant&ion=1&ie=UTF-8#q=computer+sweden";
// or 'https://www.google.com/webhp?sourceid=chrome-instant&ion=1&ie=UTF-8#q=computer+sweden';
// or new String(...) 

// Basic access and concatenation
var ch1 = url[7];
var ch2 = url.charAt(7);
var len = url.length;
var index = url.indexOf("google");
var lastIndex = url.lastIndexOf("/");
var substr = url.substr(lastIndex, len);
console.log("Index was " + index);  //Concat
// ... And so on, most (all?) the Java methods are there, see MDN


/*
 * Using regexp
 */
// When the regular expression will remain constant
var re1 = /ab+c/;
// Don't know the pattern, getting it from another source, such as user input.
var re2 = new RegExp("ab+c");

/*
 * Special chars (see MDN)
 * ^ beginning 
 * $ end
 * \ escape special char
 * * preceeding 0 .. n times
 * + preceeding 1..n times
 * ? preceeding 0-1 times
 * . any single char (not newline)
 * x|y  either x or y
 * [xyz] on on the chars in the set 
 *
 * Used with string function 
 *---------------------------
 * test	
 * match	
 * search	
 * replace
 * split	
 *
 */
