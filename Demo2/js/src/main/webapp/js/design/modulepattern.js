/* 
 *  The module pattern. We avoid single isolated top level functions
 *  Collect the functions in a module
 *
 */

// myNamespace is a ref to a single object
var myNamespace = (function() {

    // A private counter variable
    var myPrivateVar = 0;

    // A private function which logs any arguments
    myPrivateMethod = function(foo) {
        console.log(foo);
        console.log(this);  // Oh, oh .. this is window
    };
    
    // Return public parts as anonymous object
    // Possible store objects in a var befor returning
    return {
        // A public variable
        myPublicVar: "foo",
        // A public function utilizing privates
        myPublicFunction: function(bar) {
            console.log(this);
            // Increment our private counter
            myPrivateVar++;

            // Call our private method using bar
            myPrivateMethod(bar);

        }
    };

})(); // <--- run at once

// Usage (run in browser)
console.log(myNamespace);
console.log(myNamespace.myPublicVar);
console.log(myNamespace.myPublicFunction("junk"));

