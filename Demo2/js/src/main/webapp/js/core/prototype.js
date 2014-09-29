/*
 *  CONSTRUCTOR, PROTOTYPE and PROTOTYPE CHAIN
 *   
 *  This is best viewed in Chrome Developer Tools (point at variables in code)!
 *  
 *  Add break point and reload page to inspect!
 *  
 */

// A constructor function. Nothing special 
// (leading upper case just to indicate)
function MyConstructor() {}

// All functions have a **PROTOTYPE PROPERTY**
// Pointing to an unnamed automatically created
// object 
console.log( MyConstructor.prototype );

// Use contructor function to create object
var myObject = new MyConstructor();

// Now the created object has got an implicit reference
// to the contructor prototype property. This
// is the **OBJECT PROTOTYPE**. All objects have (except Object)!
// In Chrome debugger object prototype is displayed __proto__
// Get object prototype for myObject (using the built-in Object)
console.log(Object.getPrototypeOf(myObject));
// So contructor.prototype == object.getPrototypeOf

// Follow the prototype chain
console.log(Object.getPrototypeOf(Object.getPrototypeOf(myObject)));
// Finding prototype of Object (null the top level)
console.log(MyConstructor.prototype.prototype );

// ** REALLY CONFUSING **
// Because MyContructor is an object (functions are objects)
// it also has an object prototype
console.log(Object.getPrototypeOf(MyConstructor));
// Which has an object property
console.log(Object.getPrototypeOf(Object.getPrototypeOf(MyConstructor)));

// Now assign something completely different
// to the prototype
MyConstructor.prototype = (function(){
   return {
       hello : function(){
           return "Hello";
       }
   }; 
})(); //<-- call at once

// Previously created objects doesn't' change
console.log(Object.getPrototypeOf(myObject));

// Create objects
var o1 = new MyConstructor();
console.log(Object.getPrototypeOf(o1));
console.log(o1.hello());
var o2 = new MyConstructor();
console.log(o2.hello());

