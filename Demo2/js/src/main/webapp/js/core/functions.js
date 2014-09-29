/*
 *  FUNCTIONS 
 *   
 *  This is best viewed in Chrome Developer Tools (point at variables in code)!
 *  
 *  Add break point and reload page to inspect!
 * 
 */
// Standalone functions 
// A named function with parameters (no types)
// Function belongs to global object (window)
function add(a, b) {
    return a + b;  
}
var sum = window.add(1, 2);  // Call (window optional)

// Hidden array-like variable "arguments"
// Contains all parameters
function argsTest(){
    for (var i = 0, j = arguments.length; i < j; i++){
        console.log(arguments[i]+' ');
    }
}
argsTest(1,2,3);  // Params will end up in "arguments"

// Functions are objects possible to store reference in variable
var sub = function(a, b) {
    return a - b;
};
var diff = sub(4, 3); // Call by applying "(...) to reference"

// Return an anonymous object
function returnObject(a) {
    return {// The object
        val: a
    };
}
var o = returnObject(5);
console.log(o.a);

// Return anonymous function (closure)
function returnFunction(a) {
    return function(b) {
        return a * b;  // Will remember 'a' after outer function finished.
    };
}
var f = returnFunction(5);
console.log(f(3));

// Function as param
function callBack(f, a, b) {
    return f(a, b);
}
console.log(callBack(add, 1, 3));

// Execut function immediately
var executedAtOnce = (function() {
    return 999;
})();  // <-------- at once
console.log(executedAtOnce);

// A function as a member of an object (everything public)
var objectWithMethod = {
    val: 111,  // These are property assignments
    doIt: function() {
        return this.val;  // See this.js
    }
};
console.log(objectWithMethod.doIt());

// Update object with a function (i.e. let
// object have a new method
objectWithMethod.func = function(a) {
    return a;
};

// Everything inside a function is private
// So this is useless
var funcFunc = function(){
    // Function body must be statements 
    // or function declarations ...
    var val = 11;                   
    function getVal(){
        return val;
    };
};
// Exception
// console.log(funcFunc.getVal());
// Undefined
console.log(funcFunc.val);

// Returning complex object with two functions.
// sayer is reference to the anonymous object returned
// see module.js
var sayer = (function() {
    return {
        sayHello: function(a) {
            return "hello " + a;
        },
        sayGoodbye: function(a) {
            return "goodbye" + a;
        }
    };
})(); // <------ call at once
console.log(sayer.sayHello("Pelle"));


// Using a contructor to create a function
var f = new Function();    
console.log(f());

// Functions have properties
console.log(add.constructor);
console.log(add.length);
console.log(add.prototype);