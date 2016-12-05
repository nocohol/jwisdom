var i = 100;
// normal function. it has a reference to variable i out of its execution scope.
var f = function() {
	var j = 200;
	return i+j;
}

console.log(f());

var f2 = function(i) {
	var j = 200;
	return i+j;
}

console.log(f2(150));

var f3 = function() {
	var j = 200;
	return function() {
		var i = 200;
		return i+j;
	}
}
console.log(f3()());

for (var i=0; i<10; i++) {
	setTimeout(function(){
		console.log(i); 
		// i is the reference out of the execution scope
		// it is not a closure
		// it will pring 10 for 10 times
	},0);
}

for (var i=0; i<10; i++) {
	(function(a) {
		setTimeout(function() {
			console.log(a);
		},0);
	})(i)
}

//closure
(function() {
	console.log(i);
})();

console.log(typeof []);

var f5 = function(a, b) {
	console.log(arguments);
};

f5(1);
