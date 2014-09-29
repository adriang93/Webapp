/*
 *  The THIS reference
 *   
 *  This is best viewed in Chrome Developer Tools (point at variables in code)!
 *  
 *  Add break point and reload page to inspect!
 * 
 */

// this in global code is window object
this.a = 10;
console.log(window.a === this.a);

// ---------------------------------------------------------------------------

// Global function invocation
function myGlobalFunc() {
    return this;
}
console.log(myGlobalFunc());

// --------------------------------------------------------------------
// Method invocation 
var foo = {x: 10};
var bar = {
    x: 20,
    test: function() {   // A method
        console.log(this === bar);
        console.log(this.x);
    }
};

// this is determined as bar object 
bar.test(); // true, 20

foo.test = bar.test;

// Now this is foo object!! 
foo.test(); // false, 10


// ------------------------------------------------------------------------

// Constructor invocation, see person.js  
// -------------------------------------------------------------------
// 
// Preserving this, stroed as self

var myObject = {
    value: 0,
    increment: function(inc) {
        // this is the object
        this.value += (typeof inc === 'number') ? inc : 1;
    },
    double: function() {
        var self = this;

        // Inner function doesn't share this with outer!
        // This bound to global object, design flaw!!1
        function helper() {
            console.log("This is" + this);
            console.log("Self is " + self);  // Outer object!
            self.value *= 2;
        }

        helper();
    }
};

myObject.increment(3);
console.log(myObject.double());
console.log(myObject.value);
