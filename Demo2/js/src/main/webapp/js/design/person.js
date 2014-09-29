
/***********************************************
 *  
 *  Emulating a CLASS for Person
 *  
 *  This demonstrates the pseudo-classical js +
 *  module pattern try to make this look lika a Java class
 *  There are other idioms (possible better).
 *  
 *  Usage, see below
 *  
 */

// Constructor function
var Person = function(name, age, sex){
    // Properties for the instance
    this.name = name; // this is the actual object
    this.age = age;
    this.sex = sex;
};

// Assigning a "module" to the prototype
// The assigned object is though shared by all 
// instances
Person.prototype = (function (){ 
        
    // get/set not really needed because attributes public
    // just for demonstration here
    return { 
        getName: function(){
            return this.name;  // this is the actual object
        },
        
        incAge: function() {
            this.age++;
        },
        
        getAge: function(){
            return this.age;
        },
        
        setName: function ( name ){  
            this.name = name;
        },
    }; // End anonymous object     
}()); // <---- call at once


// ---------- Using the "class" ----------
var p1 = new Person("Otto", 13, "hen");
var p2 = new Person("Fia", 13, "hen");

console.log(p1.getName());
p2.incAge();
console.log(p1.getAge());
console.log(p2.getAge());


