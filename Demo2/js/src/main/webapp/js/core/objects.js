/*
 *  OBJECT and ARRAY LITERALS 
 *   
 *  This is best viewed in Chrome Developer Tools (point at variables in code)!
 *  
 *  Add break point and reload page to inspect!
 * 
 */
// An empty unnamed object (useless). An object literal.
{
}

// A variable holding a reference to an empty
// anonymous object (A_ prefix is just to easy locate in Chrome debugger) 
var A_emptyObject = {};  // Always use ';' (not really needed)

console.log( A_emptyObject === window.A_emptyObject);

/*
 * NOTE: Equality very complicated in JS, always use === or !===
 */

// Objects are like maps in Java (key/value).  
// All attributes public.
var A_Object = {
    val: 7, // property, note comma
    str: 'qwerty'
};

// Chrome outputs type in italics
console.log(A_Object);

// Objects are dynamic, add property/value
A_Object.other = 88;

// Two diffrent retrivals (everything public!!!)
var A_usedot = A_Object.str;
var A_usekey = A_Object['str'];
// UNCOMMENT: Not found TypeError
//var notfound = anObject['date'];

// Because the objects are declared right off in 
// a JS file they will belong to the top level "Global" scope
// Of course very bad, more later...
// Global scope is called "Window" if running JS in a browser
// *** NOTE: IF NOT USING 'var' any variable will end up in the global scope
// NO MATTER vere it's declared (used) ***. 
// Always use 'var' 
var A_Global = window.A_Object.str;
// For short: Owner of the object is window.

// Loop through object properties
for (var prop in A_Object) {
    console.log(prop + ":" + A_Object[prop]);
}

// Object has two subobjects a and b
var innerObject = {
    a: {
        aVal: 11
    },
    b: {
        bVal: 22
    }
};

console.log( innerObject.a.aVal);
console.log( innerObject.b.bVal);

// Explicit use of contructor to create object, see prototype.js
var A_ctorObject = new Object();
// Augment object
A_ctorObject.a = 999;
console.log( A_ctorObject.a );

// Array literal
var A_array = ["yo", "whadup", "?"];
console.log(A_array[0]);
console.log(A_array[1]);
console.log(A_array.length);

// Explicit create, see prototype.js
// NOTE: We will not explicitely traverse arrays, use JQuery!
var A_ctorArray = new Array();
A_ctorArray[0] = "anything";
console.log(A_ctorArray[0]);